package net.sls.pc.client.controller.user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sls.dto.pc.shopcart.ShopCartDto;
import net.sls.dto.pc.user.User;
import net.sls.pc.client.annotation.NoNeedUserLogin;
import net.sls.pc.client.util.Constants;
import net.sls.pc.client.util.CookieUtil;
import net.sls.service.pc.act.ICouponService;
import net.sls.service.pc.order.IOrderService;
import net.sls.service.pc.product.IPavilionInfoService;
import net.sls.service.pc.product.IShopCartService;
import net.sls.service.pc.user.IMyFavoriteService;
import net.sls.service.pc.user.IUserService;
import net.sls.service.tpi.ISmsSenderService;

import org.apache.http.annotation.NotThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * 
 * 前端用户功能
 * 
 * */
@Controller
@RequestMapping("user")
public class UserController {
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "login.htm")
	@ResponseBody
	public ResBo<User> login(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		ReqBo reqBo = new ReqBo(request);
		IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
		ResBo<User> userRes = us.login(reqBo);
		if(userRes.isSuccess() && userRes.getResult() != null){
			User user = userRes.getResult();
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
		return userRes;
	}
	
	/**
	 * 注销登录
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "logout.htm")
	@ResponseBody
	public void logout(HttpServletRequest request){
		//清除session中的用户信息
		SessionUtil.delete(Constants.SESSION_USER_INFO);
		SessionUtil.delete(Constants.SESSION_USER_ID);
		//跳转到主页
		//return new ModelAndView(new RedirectView(Constants.SERVER_URL + "/main/index.htm"));
	}
	
	/**
	 * 进入注册页
	 * @param request
	 * @return
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "toRegist.htm")
	public ModelAndView toRegist(HttpServletRequest request){
		return new ModelAndView("regist/regist");
	}
	
		/**
	 * 进入注册页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "toUserRegist.htm")
	public ModelAndView toUserRegist(HttpServletRequest request){
		ModelAndView view = new ModelAndView("regist/user_regist");
		view.addObject(Constants.MENU_ID, Constants.MENU_USER_REGIST);
		return view;
	}
	
	/**
	 * 注册
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "regist.htm")
	public ModelAndView regist(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute User user) throws UnsupportedEncodingException{
		ReqBo reqBo = new ReqBo(request);
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
			cs.saveCoupon(reqBo);			
		}else{
			request.setAttribute("result", "fail");
		}
		//清除session中用户的手机验证码信息
		SessionUtil.delete(user.getMobile());
		return new ModelAndView("regist/regist_result");
	}
	
	/**
	 * 注册ajax
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "registAjax.htm")
	public void registAjax(HttpServletRequest request, HttpServletResponse response) throws IOException{
		ReqBo reqBo = new ReqBo(request);
		User user=new User();
		user.setUserName(reqBo.getParamStr("userName"));
		user.setPassword(reqBo.getParamStr("password"));
		user.setMobile(reqBo.getParamStr("mobile"));
		User politionUser = (User) SessionUtil.get(Constants.SESSION_USER_INFO);
		user.setProvince(politionUser.getProvince());
		user.setCity(politionUser.getCity());
		user.setDistrict(politionUser.getDistrict());
		user.setCommunity(politionUser.getCommunity());
		user.setPavilionId(politionUser.getPavilionId());
		user.setPavilionCode(politionUser.getPavilionCode()); //用亭子code保存city的code，当用户修改个人信息后，会变成亭子code
		reqBo.setReqModel(user);
		IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
		ResBo<User> result = us.register(reqBo);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(result.isSuccess());
	}
	
	/**
	 * 判断手机号是否重复
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "ismobileExist.htm")
	public void ismobileExist(HttpServletRequest request,
			HttpServletResponse response, String mobile, String userName) throws IOException{
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("mobile", mobile);
		IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
		ResBo<String> result = us.selectUserMobileIsExist(reqBo);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(result.isSuccess());
	}
	
	/**
	 * 判断用户名是否重复
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "isUserNameExist.htm")
	public void isUserNameExist(HttpServletRequest request,
			HttpServletResponse response, String mobile, String userName) throws IOException{
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("userName", userName);
		IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
		ResBo<String> result = us.selectUserNameIsExist(reqBo);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(result.isSuccess());
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
	 * 发送手机验证码，6位的随机数(无需登录)
	 * @param request
	 * @param mobile
	 * @throws IOException 
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "sendMobileCode.htm")
	public void sendMobileCode(HttpServletRequest request, HttpServletResponse response, String mobile) throws IOException{
		String code = String.valueOf((int)(Math.random()*1000000));
		for(int index=code.length(); index<Constants.MOBILE_CODE_COUNT; index++){
			code = "0" + code; //验证码不足6位，用0补齐
		}
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("mobile", mobile);
		reqBo.setParam("message", code);
		ISmsSenderService ss = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_SMSSENDER, ISmsSenderService.class);
		ss.send(reqBo);
		//将用户的手机验证码信息放入session
		SessionUtil.replace(mobile, code);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print("true");
	}
	
	/**
	 * 发送手机验证码，6位的随机数(需要登录)
	 * @param request
	 * @param mobile
	 * @throws IOException 
	 */
	@RequestMapping(value = "sendMobileCode4Login.htm")
	public void sendMobileCode(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String mobile = ((User)SessionUtil.get(Constants.SESSION_USER_INFO)).getMobile();
		String code = String.valueOf((int)(Math.random()*1000000));
		for(int index=code.length(); index<Constants.MOBILE_CODE_COUNT; index++){
			code = "0" + code; //验证码不足6位，用0补齐
		}
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("mobile", mobile);
		reqBo.setParam("message", code);
		ISmsSenderService ss = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_SMSSENDER, ISmsSenderService.class);
		ss.send(reqBo);
		//将用户的手机验证码信息放入session
		SessionUtil.replace(mobile, code);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print("true");
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
	 * 判断手机验证码是否正确(需要登录)
	 * @param request
	 * @param response
	 * @param code
	 * @throws IOException
	 */
	@RequestMapping(value = "isMobileCodeCorrect4Login.htm")
	public void isMobileCodeCorrect(HttpServletRequest request,
			HttpServletResponse response, String mobileCode) throws IOException{
		String mobile = ((User)SessionUtil.get(Constants.SESSION_USER_INFO)).getMobile();
		String correctCode = (String)SessionUtil.get(mobile);
		response.setContentType("text/html;charset=UTF-8");
		if(mobileCode.equals(correctCode)){
			response.getWriter().print("true");
		}else{
			response.getWriter().print("false");
		}
	}
	
	/**
	 * 进入个人信息页
	 */
	@RequestMapping(value = "toUserInfo.htm")
	public ModelAndView toUserInfo(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("user/user_info");
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("id", user.getPavilionId());
		IPavilionInfoService ps = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_PAVILION,IPavilionInfoService.class);
		ResBo<Map<Object,Object>> pavilionInfo = ps.getPavilionInfoById(reqBo);
		if(pavilionInfo.isSuccess() && pavilionInfo.getResult() != null){
			view.addObject("pavilionPhone", pavilionInfo.getResult().get("mobile"));
		}
		view.addObject(Constants.MENU_ID, Constants.MENU_USER_INFO);
		return view;
	}
	
	/**
	 * 修改用户信息
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "updateUser.htm")
	@ResponseBody
	public void updateUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user")User user) throws IOException {
		ReqBo reqBo = new ReqBo(request);
		reqBo.setReqModel(user);
		IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER,IUserService.class);
		ResBo<User> userRes = us.updateUser(reqBo);
		if(userRes.isSuccess() &&userRes.getResult() != null){
			response.getWriter().print("success");
			SessionUtil.replace(Constants.SESSION_USER_INFO, userRes.getResult());
			SessionUtil.get(Constants.SESSION_USER_INFO);
		}else{
			response.getWriter().print("fail");
		}
		response.setContentType("text/html;charset=UTF-8");
	}
	
	/**
	 * 进入我的手拉手页
	 */
	@RequestMapping(value = "toMysls.htm")
	public ModelAndView toMysls(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("user/my_sls");
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("userId", (Long)SessionUtil.get(Constants.SESSION_USER_ID));
		IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
		ResBo<User> userRes = us.getUserByUserId(reqBo);
		if(userRes.isSuccess() && userRes.getResult() != null){
			//用户信息
			User user = userRes.getResult();
			view.addObject("userName", user.getUserName());
			view.addObject("amount", user.getAmount());
			view.addObject("points", user.getPoints());
			
			IOrderService os = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_ORDER, IOrderService.class);
			reqBo.setParam("rows", 3);
			reqBo.setParam("page", 1);
			reqBo.setParam("column", "createTime");
			reqBo.setParam("sort", Constants.ORDERBY_DESC);
			//普通用户
			if(user.getMemberType() == Constants.USER_TPYE_COMMON){
				//我的订单
				view.addObject("myOrders", os.selectOrderListsByUserId(reqBo));
				//我关注的商品
				reqBo.setParam("rows", 0);
				IMyFavoriteService ms = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_MYFAVORITE, IMyFavoriteService.class);
				view.addObject("myFavorites", ms.selectMyFavoriteListsByUserId(reqBo));
			}
			//亭子用户
			if(user.getMemberType() == Constants.USER_TPYE_PAVILION){
				//订单管理
				view.addObject("pavilionOrders", os.getPavilionOrders(reqBo));
				//今日消费余额（元）
				IOrderService orderService = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_ORDER, IOrderService.class);
				ResBo<BigDecimal> TodayTotal = orderService.getTodayTotalByUserId(reqBo);
				if(TodayTotal.getResult()!=null){
					view.addObject("todayTotal", TodayTotal.getResult());
				}else {
					view.addObject("todayTotal", "0.00");
				}
			}
		}
		view.addObject(Constants.MENU_ID, Constants.MENU_MY_SLS);
		return view;
	}
	
	/**
	 * 判断支付密码是否正确
	 * @param request
	 * @param response
	 * @param payPassword
	 * @throws IOException
	 */
	@RequestMapping(value = "isPayPasswordCorrect.htm")
	@ResponseBody
	public void isPayPasswordCorrect(HttpServletRequest request,
			HttpServletResponse response, String payPassword) throws IOException{
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("userId", (Long)SessionUtil.get(Constants.SESSION_USER_ID));
		IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER,IUserService.class);
		ResBo<User> user = us.getUserByUserId(reqBo);
		if(user.isSuccess() && user.getResult() != null &&
				payPassword.equals(user.getResult().getPayPassword())){
			response.getWriter().print("true");
		}else{
			response.getWriter().print("false");
		}
		response.setContentType("text/html;charset=UTF-8");
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
	
	/**
	 * 亭子用户所属亭子所有用户
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("consumer.htm")
	public String consumer(HttpServletRequest request, Model model){
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		ResBo<Pager<List<User>>> resBo = new ResBo<Pager<List<User>>>();
		ReqBo reqBo = new ReqBo(request);
		if(reqBo.getParam("page") == null || !reqBo.getParam("page").toString().matches("[1-9][0-9]*")){
			reqBo.setParam("page", 1);
		}
		reqBo.setParam("rows", Constants.ORDER_NUM_PAGE);		
		IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER,IUserService.class);	
		if(user.getMemberType() == 2){
			if(user.getPavilionId() != null){
				reqBo.setParam("pid", user.getPavilionId());
				resBo = us.getConsumerByPavilionId(reqBo);
				model.addAttribute("consumer", resBo.getResult().getEntry());
				model.addAttribute("total_page", (resBo.getResult().getTotal() + Constants.ORDER_NUM_PAGE-1)/Constants.ORDER_NUM_PAGE);
				model.addAttribute("page", reqBo.getParamInt("page"));
				model.addAttribute(Constants.MENU_ID, "consumer");
				return "/user/consumer";
			}else{
				return "redirect:/main/index.htm";
			}
		}else{
			return "redirect:/main/index.htm";
		}				
	}
	/**
	 * 根据用户名或手机号查询用户
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("nameMobile.htm")
	public String getUserByNameMobile(HttpServletRequest request, Model model) throws UnsupportedEncodingException{
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		if(user.getMemberType() == 1){
			return "redirect:/main/index.htm";
		}else{
			ReqBo reqBo = new ReqBo();
			String nameMobile = Constants.parseUTF8(request.getParameter("nameMobile"));
			reqBo.setParam("nameMobile", nameMobile);
			reqBo.setParam("pid", user.getPavilionId());
			IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER,IUserService.class);
			ResBo<List<User>> resBo = us.getConsumerByNameMobile(reqBo);
			model.addAttribute("page", 1);
			model.addAttribute("total_page", 1);
			model.addAttribute("consumer", resBo.getResult());
			model.addAttribute(Constants.MENU_ID, "consumer");
			return "/user/consumer";
		}		
	}
}
