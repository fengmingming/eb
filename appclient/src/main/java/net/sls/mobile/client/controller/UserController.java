package net.sls.mobile.client.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sls.dto.pc.user.User;
import net.sls.mobile.client.annotation.NoNeedUserLogin;
import net.sls.mobile.client.util.Constants;
import net.sls.service.pc.act.ICouponService;
import net.sls.service.pc.product.IShopCartService;
import net.sls.service.pc.user.IUserService;
import net.sls.service.tpi.ISmsSenderService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
				Object cart_info = SessionUtil.get(Constants.COOKIE_CART);
				if(cart_info != null){
					String cartCookie = cart_info.toString();
					reqBo.setParam(Constants.COOKIE_CART, cartCookie);
					IShopCartService is = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_SHOPCART, IShopCartService.class);
					is.merge(reqBo);
				}
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
	@ResponseBody
	public ResBo<?> logout(){
		//清除session中的用户信息
		SessionUtil.delete(Constants.SESSION_USER_INFO);
		SessionUtil.delete(Constants.SESSION_USER_ID);
		return new ResBo<Object>();
	}
	
	//发送手机验证码
	@NoNeedUserLogin
	@RequestMapping(value = "sendMobileCode.htm")
	@ResponseBody
	public ResBo<?> sendMobileCode(@RequestParam(value="mobile") String mobile) throws IOException{
		String code = String.valueOf((int)(Math.random()*1000000));
		for(int index=code.length(); index<Constants.MOBILE_CODE_COUNT; index++){
			code = "0" + code; //验证码不足6位，用0补齐
		}
		ReqBo reqBo = new ReqBo();
		reqBo.setParam("message", code);
		reqBo.setParam("mobile", mobile);
		ISmsSenderService ss = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_SMSSENDER, ISmsSenderService.class);
		ss.send(reqBo);
		//将用户的手机验证码信息放入session
		SessionUtil.replace(mobile, code);
		return new ResBo<Object>();
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
	public ResBo<?> resetPwd(@RequestParam("password") String password,@RequestParam("mobile") String mobile, @RequestParam("code") String code) throws UnsupportedEncodingException{
		ResBo<?> rb = new ResBo<Object>();
		ReqBo reqBo = new ReqBo();
		reqBo.setParam("mobile", mobile);
		IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
		ResBo<User> resBo = us.getUserBymobile(reqBo);
		if(resBo.getResult() == null){
			rb.setSuccess(false);
			rb.setErrMsg("用户不存在");
			return rb;
		}else if(!code.equals((String)SessionUtil.get(mobile))){
			rb.setSuccess(false);
			rb.setErrMsg("手机验证码不正确");
			return rb;
		}else {
			ResBo<User> resBo_r = us.getUserBymobile(reqBo);
			User user = resBo_r.getResult();
			if(password.equals(user.getPayPassword())){
				rb.setSuccess(false);
				rb.setErrMsg("和支付密码重复");
				return rb;
			}else{
				user.setPassword(password);
				reqBo.setReqModel(user);
				resBo = us.updateUser(reqBo);
				if(resBo.getResult() != null){
					if(user.getMemberType().intValue() == MemberTypeEnum.MemberType_1.getCode()){
						//将用户信息放入session
						SessionUtil.replace(Constants.SESSION_USER_INFO, user);
						SessionUtil.replace(Constants.SESSION_USER_ID, user.getId());
						SessionUtil.replace(Constants.SESSION_PROVINCEID, user.getProvince());
						SessionUtil.replace(Constants.SESSION_CITYID, user.getCity());
						SessionUtil.replace(Constants.SESSION_CITYCODE, user.getPavilionCode().substring(0, 6)); //亭子code的前6位是城市code
						//合并购物车，清空cookie，无cookie无需合并
						reqBo.setParam("userId", user.getId());
						Object cart_info = SessionUtil.get(Constants.COOKIE_CART);
						if(cart_info != null){
							String cartCookie = cart_info.toString();
							reqBo.setParam(Constants.COOKIE_CART, cartCookie);
							IShopCartService is = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_SHOPCART, IShopCartService.class);
							is.merge(reqBo);
						}
					}
					SessionUtil.delete(mobile);
					SessionUtil.delete(Constants.IMG_VALIDATE);
				}
			}
		}			
		return rb;				
	}
	
	/**
	 * 注册
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "register.htm")
	@ResponseBody
	public ResBo<?> regist(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute User user) throws UnsupportedEncodingException{
		ReqBo reqBo = new ReqBo(request);
		ResBo<?> rb = validatUsernameAndMobile(user.getUserName(),user.getMobile());
		if(!rb.isSuccess()){
			return rb;
		}
		ResBo<?> resBo = new ResBo<Object>();
		String imgCode = (String)SessionUtil.get(Constants.IMG_VALIDATE);
		if(imgCode == null||!imgCode.equals(reqBo.getParamStr("imgCode"))){
			resBo.setSuccess(false);
			resBo.setErrMsg("图形验证码错误");
			return resBo;
		}
		if(user.getMobile() != null){
			String mobileCode = (String)SessionUtil.get(user.getMobile());
			if(mobileCode == null||!mobileCode.equalsIgnoreCase(reqBo.getParamStr("mobileCode"))){
				resBo.setSuccess(false);
				resBo.setErrMsg("短信验证码错误");
				return resBo;
			}
		}
		user.setProvince((Integer)SessionUtil.get(Constants.SESSION_PROVINCEID));
		user.setCity((Integer)SessionUtil.get(Constants.SESSION_CITYID));
		user.setPavilionCode((String)SessionUtil.get(Constants.SESSION_CITYCODE)); //用亭子code保存city的code，当用户修改个人信息后，会变成亭子code
		IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
		ResBo<User> result = us.register(new ReqBo(user));
		if(result.isSuccess() && result.getResult() != null){
			User newUser = result.getResult();
			SessionUtil.replace(Constants.SESSION_USER_INFO, newUser);
			SessionUtil.replace(Constants.SESSION_USER_ID, newUser.getId());
			ICouponService cs = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_COUPON, ICouponService.class);
			cs.saveCoupon(new ReqBo().setParam("userId", newUser.getId()));	
		}else{
			resBo.setSuccess(false);
			resBo.setErrMsg(result.getErrMsg());
			return resBo;
		}
		//清除session中用户的手机验证码信息
		if(user.getMobile() != null){
			SessionUtil.delete(user.getMobile());
		}
		return resBo;
	}
	
	private ResBo<?> validatUsernameAndMobile(String username,String mobile){
		IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
		ResBo<?> resBo = us.selectUserNameIsExist(new ReqBo().setParam("userName", username));
		if(!resBo.isSuccess()){
			return resBo;
		}
		if(mobile != null){
			resBo = us.selectUserMobileIsExist(new ReqBo().setParam("mobile", mobile));
			return resBo;
		}
		return resBo;
	}
	
	
	
	/**
	 * 判断用户是否登录
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "isUserLogin.htm")
	@ResponseBody
	public ResBo<?> isUserLogin(HttpServletRequest request, HttpServletResponse response) throws IOException{
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		ResBo<Object> resBo = new ResBo<Object>();
		if(user != null){
			return resBo;
		}else{
			resBo.setSuccess(false);
			return resBo;
		}
	}
	
	/**
	 * 
	 * 更改手机号
	 * 
	 * */
	@RequestMapping("bindmobile.htm")
	@ResponseBody
	public ResBo<?> bindMobile(@RequestParam("mobile")String mobile,@RequestParam("code") String code){
		ResBo<?> resBo = new ResBo<Object>();
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		String mobileCode;
		if(user.getMobile() != null){
			mobileCode = (String)SessionUtil.get(user.getMobile());
		}else{
			mobileCode = (String)SessionUtil.get(mobile);
		}
		if(!code.equalsIgnoreCase(mobileCode)){
			resBo.setSuccess(false);
			resBo.setErrMsg("短信验证码错误");
			return resBo;
		}
		User u = new User();
		u.setId(user.getId());
		u.setMobile(mobile);
		IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
		ResBo<?> rb = us.updateUser(new ReqBo(u));
		if(!rb.isSuccess()){
			resBo.setSuccess(false);
			resBo.setErrMsg(rb.getErrMsg());
		}
		user.setMobile(mobile);
		return resBo;
	}
	
	/**
	 * 
	 * 设置支付密码
	 * 
	 * */
	@RequestMapping("setPayPass.htm")
	@ResponseBody
	public ResBo<?> setPayPass(@RequestParam("pass")String pass,@RequestParam("code")String code){
		ResBo<?> resBo = new ResBo<Object>();
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		String mcode = (String)SessionUtil.get(user.getMobile());
		if(!code.equalsIgnoreCase(mcode)){
			resBo.setSuccess(false);
			resBo.setErrMsg("短信验证码错误");
			return resBo;
		}
		User u = new User();
		u.setId(user.getId());
		u.setPayPassword(pass);
		IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
		ResBo<?> rb = us.updateUser(new ReqBo(u));
		if(!rb.isSuccess()){
			resBo.setSuccess(false);
			resBo.setErrMsg(rb.getErrMsg());
		}
		return resBo;
	}
	
	@RequestMapping("isExistMobile.htm")
	@ResponseBody
	public ResBo<Boolean> isExistMobile(){
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		ResBo<Boolean> resBo = new ResBo<Boolean>();
		if(user.getMobile() != null){
			resBo.setResult(true);
			return resBo;
		}
		IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
		ResBo<User> rb = us.getUserByUserId(new ReqBo().setParam("userId", user.getId()));
		if(rb.isSuccess()&&rb.getResult().getMobile() == null){
			resBo.setResult(false);
			return resBo;
		}
		resBo.setResult(true);
		return resBo;
	}
	
	@RequestMapping("sendMCToUM.htm")
	@ResponseBody
	public ResBo<?> sendMCToUM() throws IOException{
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		return sendMobileCode(user.getMobile());
	}
}
