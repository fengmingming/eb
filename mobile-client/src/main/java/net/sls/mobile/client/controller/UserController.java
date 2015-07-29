package net.sls.mobile.client.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sls.dto.mobile.shopcart.ShopCartDto;
import net.sls.dto.mobile.user.User;
import net.sls.mobile.client.annotation.NoNeedUserLogin;
import net.sls.mobile.client.util.Constants;
import net.sls.mobile.client.util.CookieUtil;
import net.sls.service.mobile.act.ICouponService;
import net.sls.service.mobile.product.IShopCartService;
import net.sls.service.mobile.user.IUserService;
import net.sls.service.tpi.ISmsSenderService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import util.model.MemberTypeEnum;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * @author dyh 用户功能页
 *
 */
@Controller
@RequestMapping("user")
public class UserController {
	//登录页
	@NoNeedUserLogin
	@RequestMapping(value = "login.htm")
	public ModelAndView index(HttpServletRequest request) {
		//flag是为了让点击我的手拉手登陆后直接跳转的标记
		String flag = "false";
		ModelAndView view = new ModelAndView("user/login");
		if(request.getParameter("flag") != null){
			flag = request.getParameter("flag");
		}		
		view.addObject("flag", flag);
		return view;
	}
	//注册页
	@NoNeedUserLogin
	@RequestMapping(value = "regist.htm")
	public ModelAndView regist(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("user/regist");
		return view;
	}
	
	//找回密码页
	@NoNeedUserLogin
	@RequestMapping(value = "findPassword.htm")
	public ModelAndView findPassword(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("user/find_password");
		return view;
	}
		
	//登陆验证
	@NoNeedUserLogin
	@RequestMapping("loginValidate.htm")
	@ResponseBody
	public ResBo<User> loginValidate(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		ReqBo reqBo = new ReqBo(request);
		IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
		ResBo<User> userRes = us.login(reqBo);
		if(userRes.isSuccess() && userRes.getResult() != null){
			User user = userRes.getResult();
			if(user.getMemberType().intValue() == MemberTypeEnum.MemberType_1.getCode()){
				//将用户信息放入session
				SessionUtil.replace(Constants.SESSION_USER_INFO, user);
				SessionUtil.replace(Constants.SESSION_USER_ID, user.getId());
				SessionUtil.replace(Constants.SESSION_PROVINCEID, user.getProvince());
				SessionUtil.replace(Constants.SESSION_CITYID, user.getCity());
				SessionUtil.replace(Constants.SESSION_CITYCODE, user.getPavilionCode().substring(0, 6)); //亭子code的前6位是城市code
				//合并购物车，清空cookie，无cookie无需合并
				reqBo.setParam("userId", user.getId());
				Cookie cookie = CookieUtil.getCookie(request, Constants.COOKIE_CART);
				if(cookie != null){
					String cartCookie = cookie.getValue();
					reqBo.setParam(Constants.COOKIE_CART, cartCookie);
					IShopCartService is = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_SHOPCART, IShopCartService.class);
					ResBo<ShopCartDto> ShopCartDto = is.merge(reqBo);
					if(ShopCartDto.isSuccess()){
						CookieUtil.clearCookie(request, response, Constants.COOKIE_CART);
					}
				}
				//最后登录的用户的cookie，用于设置下次用户登录时的登录名
				SessionUtil.replace(Constants.COOKIE_LAST_USER, reqBo.getParamStr("mobile"));
				CookieUtil.setCookie(request, response, Constants.COOKIE_LAST_USER, 
						URLEncoder.encode(reqBo.getParamStr("mobile"), "UTF-8"));
			}else{
				//亭子用户不能在手机端登录
				userRes.setResult(null);
				userRes.setSuccess(false);
			}
		}
		return userRes;
	}
	
	//退出
	@RequestMapping("logout.htm")
	public String logout(){
		//清除session中的用户信息
		SessionUtil.delete(Constants.SESSION_USER_INFO);
		SessionUtil.delete(Constants.SESSION_USER_ID);
		return "redirect:/index.htm";
	}
	
	//找回密码验证手机
	@NoNeedUserLogin
	@RequestMapping(value = "ismobileExist.htm")
	public void ismobileExist(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		ReqBo reqBo = new ReqBo(request);
		IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
		ResBo<String> result = us.selectUserMobileIsExist(reqBo);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(result.isSuccess());
	}
	
	//发送手机验证码
	@NoNeedUserLogin
	@RequestMapping(value = "sendMobileCode.htm")
	public void sendMobileCode(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="mobile") String mobile) throws IOException{
		String code = String.valueOf((int)(Math.random()*1000000));
		for(int index=code.length(); index<Constants.MOBILE_CODE_COUNT; index++){
			code = "0" + code; //验证码不足6位，用0补齐
		}
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("message", code);
		ISmsSenderService ss = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_SMSSENDER, ISmsSenderService.class);
		ss.send(reqBo);
		//将用户的手机验证码信息放入session
		SessionUtil.replace(mobile, code);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print("true");
	}
	
	/**
	 * 找回密码
	 * @param password captcha code mobile
	 * @return result 1用户不存在  2手机验证码不正确 3图形验证码不正确  4和支付密码重复 5修改成功
	 * @throws UnsupportedEncodingException 
	 */
	@NoNeedUserLogin
	@RequestMapping(value="resetPwd.htm", method=RequestMethod.POST)
	@ResponseBody
	public String resetPwd(HttpServletRequest request, HttpServletResponse response, @RequestParam("password") String password,@RequestParam("mobile") String mobile, @RequestParam("captcha") String captcha, @RequestParam("code") String code) throws UnsupportedEncodingException{
		String result = "0";	
		ReqBo reqBo = new ReqBo();
		reqBo.setParam("mobile", mobile);
		IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
		ResBo<User> resBo = us.getUserBymobile(reqBo);
		if(resBo.getResult() == null){
			result = "1";
		}else if(!captcha.equals((String)SessionUtil.get(mobile))){
			result = "2";
		}else if(!code.equals((String)SessionUtil.get(Constants.IMG_VALIDATE))){
			result = "3";
		}else {
			ResBo<User> resBo_r = us.getUserBymobile(reqBo);
			User user = resBo_r.getResult();
			if(password.equals(user.getPayPassword())){
				result = "4";
			}else{
				user.setPassword(password);
				reqBo.setReqModel(user);
				resBo = us.updateUser(reqBo);
				if(resBo.getResult() != null){
					result = "5";
					if(user.getMemberType().intValue() == MemberTypeEnum.MemberType_1.getCode()){
						//将用户信息放入session
						SessionUtil.replace(Constants.SESSION_USER_INFO, user);
						SessionUtil.replace(Constants.SESSION_USER_ID, user.getId());
						SessionUtil.replace(Constants.SESSION_PROVINCEID, user.getProvince());
						SessionUtil.replace(Constants.SESSION_CITYID, user.getCity());
						SessionUtil.replace(Constants.SESSION_CITYCODE, user.getPavilionCode().substring(0, 6)); //亭子code的前6位是城市code
						//合并购物车，清空cookie，无cookie无需合并
						reqBo.setParam("userId", user.getId());
						Cookie cookie = CookieUtil.getCookie(request, Constants.COOKIE_CART);
						if(cookie != null){
							String cartCookie = cookie.getValue();
							reqBo.setParam(Constants.COOKIE_CART, cartCookie);
							IShopCartService is = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_SHOPCART, IShopCartService.class);
							ResBo<ShopCartDto> ShopCartDto = is.merge(reqBo);
							if(ShopCartDto.isSuccess()){
								CookieUtil.clearCookie(request, response, Constants.COOKIE_CART);
							}
						}
						//最后登录的用户的cookie，用于设置下次用户登录时的登录名
						SessionUtil.replace(Constants.COOKIE_LAST_USER, reqBo.getParamStr("mobile"));
						CookieUtil.setCookie(request, response, Constants.COOKIE_LAST_USER, 
								URLEncoder.encode(reqBo.getParamStr("mobile"), "UTF-8"));
						}
						SessionUtil.delete(mobile);
						SessionUtil.delete(Constants.IMG_VALIDATE);
				}
			}
		}			
		return result;				
	}
	
	/**
	 * 注册
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "register.htm")
	public ModelAndView regist(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute User user) throws UnsupportedEncodingException{
		ReqBo reqBo = new ReqBo(request);
		user.setUserName(user.getMobile());
		user.setProvince((Integer)SessionUtil.get(Constants.SESSION_PROVINCEID));
		user.setCity((Integer)SessionUtil.get(Constants.SESSION_CITYID));
		user.setPavilionCode((String)SessionUtil.get(Constants.SESSION_CITYCODE)); //用亭子code保存city的code，当用户修改个人信息后，会变成亭子code
		reqBo.setReqModel(user);
		IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
		ResBo<User> result = us.register(reqBo);
		if(result.isSuccess() && result.getResult() != null){
			request.setAttribute("result", "success");
			//将用户信息放入session，用户登录，模拟用户登录
			User newUser = result.getResult();
			SessionUtil.replace(Constants.SESSION_USER_INFO, newUser);
			SessionUtil.replace(Constants.SESSION_USER_ID, newUser.getId());
			//最后登录的用户的cookie，用于设置下次用户登录时的登录名
			SessionUtil.replace(Constants.COOKIE_LAST_USER, newUser.getUserName());
			CookieUtil.setCookie(request, response, Constants.COOKIE_LAST_USER, 
					URLEncoder.encode(newUser.getUserName(), "UTF-8"));
			ICouponService cs = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_COUPON, ICouponService.class);
			//注册用户领取优惠券
			reqBo.setParam("userId", newUser.getId());
			//cs.saveCoupon(reqBo);	
		}else{
			request.setAttribute("result", "fail");
		}
		//清除session中用户的手机验证码信息
		SessionUtil.delete(user.getMobile());
		return new ModelAndView("index");
	}
	
	/**
	 * 判断手机验证码是否正确(无需登录)
	 * @param request
	 * @param response
	 * @param code
	 * @throws IOException
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "isMobileCodeCorrect.htm")
	public void isMobileCodeCorrect(HttpServletRequest request,
			HttpServletResponse response, String mobile, String mobileCode) throws IOException{
		String correctCode = (String)SessionUtil.get(mobile);
		response.setContentType("text/html;charset=UTF-8");
		if(mobileCode.equals(correctCode)){
			response.getWriter().print("true");
		}else{
			response.getWriter().print("false");
		}
	}
	
	/**
	 * 判断验证码是否正确
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "isCodeCorrect.htm")
	public void isCodeCorrect(HttpServletRequest request,
			HttpServletResponse response, String code) throws IOException{
		String correctCode = (String)SessionUtil.get(Constants.IMG_VALIDATE);
		response.setContentType("text/html;charset=UTF-8");
		if(correctCode.equalsIgnoreCase(code)){
			response.getWriter().print("true");
		}else{
			response.getWriter().print("false");
		}
	}
	
	/**
	 * 判断用户是否登录
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "isUserLogin.htm")
	public void isUserLogin(HttpServletRequest request, HttpServletResponse response) throws IOException{
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		if(user != null){
			response.getWriter().print("true");
		}else{
			response.getWriter().print("false");
		}
		response.setContentType("text/html;charset=UTF-8");
	}
	
	//个人中心我的手拉手页
	@NoNeedUserLogin
	@RequestMapping(value = "my_sls.htm")
	public ModelAndView mySls(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("user/my_sls");
		return view;
	}
	
	//我的钱包页
	@NoNeedUserLogin
	@RequestMapping(value = "my_wallet.htm")
	public ModelAndView myWallet(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("user/my_wallet");
		return view;
	}
	
	//收支明细页
	@NoNeedUserLogin
	@RequestMapping(value = "pay_list.htm")
	public ModelAndView payList(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("user/pay_list");
		return view;
	}
	
	//充值页
	@NoNeedUserLogin
	@RequestMapping(value = "recharge.htm")
	public ModelAndView recharge(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("user/recharge");
		return view;
	}
}
