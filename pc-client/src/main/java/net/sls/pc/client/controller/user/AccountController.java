package net.sls.pc.client.controller.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sls.dto.pc.pay.PrepaidCard;
import net.sls.dto.pc.user.AmountOrder;
import net.sls.dto.pc.user.User;
import net.sls.pc.client.annotation.NoNeedUserLogin;
import net.sls.pc.client.util.Constants;
import net.sls.service.pc.user.IAmountLogService;
import net.sls.service.pc.user.IAmountOrderService;
import net.sls.service.pc.user.IUserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import util.httpclient.HttpRequestUtil;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * 账户资金充值
 * @author sls-28
 *
 */
@Controller
@RequestMapping("account")
public class AccountController {
	/**
	 * 进入账户资金充值页面
	 * @return
	 */
	@RequestMapping(value = "toRecharge.htm")
	public ModelAndView toRecharge(){
		ModelAndView view = new ModelAndView("account/account_recharge");
		view.addObject(Constants.MENU_ID, Constants.MENU_ACCOUNT_RECHARGE);
		return view;
	}
	
	/**
	 * 账户资金充值
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "recharge.htm")
	public void recharge(HttpServletRequest request, HttpServletResponse response, 
			AmountOrder amountOrder) throws IOException{
		//调用资金充值接口(状态为未完成，充值完成后为完成)
		ReqBo reqBo = new ReqBo(request);
		amountOrder.setUserId((Long)SessionUtil.get(Constants.SESSION_USER_ID));
		reqBo.setReqModel(amountOrder);
		IAmountOrderService as = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_AMOUNTORDER, IAmountOrderService.class);
		ResBo<AmountOrder> amountOrderRes = as.rechargeBeforeAmount(reqBo);
		//调用支付宝接口
		if(amountOrderRes.isSuccess() && amountOrderRes.getResult() != null){
			AmountOrder order = amountOrderRes.getResult();
			String orderNum = Constants.ALIAPY_PREFIX_RECHARGE + order.getVoucherOrderNum();
			String title = new String(new String("资金充值").getBytes(), "iso-8859-1");
			Map<String, String> param = new HashMap<String, String>();
			param.put(Constants.ALIAPY_PARAM_TRADENO, orderNum);
			param.put(Constants.ALIAPY_PARAM_SUBJECT, title);
			param.put(Constants.ALIAPY_PARAM_TOTALFEE, order.getMoney().toString());
			param.put(Constants.ALIAPY_PARAM_BODY, title);
//			param.put(Constants.ALIAPY_PARAM_RETURNURL, Constants.ALIAPY_PARAM_RETURNURL_VALUE);
//			param.put(Constants.ALIAPY_PARAM_RETURNURL, Constants.ALIAPY_PARAM_RETURNURL_VALUE);
			response.getWriter().print(HttpRequestUtil.sendHttpRequest(Constants.ALIAPY_URL, param, HttpRequestUtil.POST));
			response.setContentType("text/html;charset=UTF-8");
		}
	}
	
	/**
	 * 充值卡充值
	 * @param password
	 * @param captcha
	 * @request result 1充值失败  2密码不正确   3验证码不正确   4充值成功
	 */
	@RequestMapping(value = "rechargeCard.htm")
	@ResponseBody
	public String rechargeCard(HttpServletRequest request){
		String result = null;
		ReqBo reqBo = new ReqBo(request);
		Long id = (Long)SessionUtil.get(Constants.SESSION_USER_ID);
		reqBo.setParam("userId", id);
		String captcha = request.getParameter("captcha");
		IAmountOrderService as = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_AMOUNTORDER, IAmountOrderService.class);		
		if(!captcha.equalsIgnoreCase((String)SessionUtil.get(Constants.IMG_VALIDATE))){
			result = "3";
		}else{
			ResBo<PrepaidCard> resBo = as.rechargeCardAmount(reqBo);
			if(resBo.getErrCode() == 44L){
				result = "2";
			}else if(!resBo.isSuccess()){
				result = "1";
			}else{
				result = resBo.getResult().getParValue().toString();
			}
		}				
		return result;		
	}
	
	/**
	 * 进入安全中心页
	 */
	@RequestMapping(value = "toAccountCenter.htm")
	public ModelAndView toAccountCenter(){
		ModelAndView view = new ModelAndView("account/account_center");
		Long id = (Long)SessionUtil.get(Constants.SESSION_USER_ID);
		IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
		ReqBo reqBo = new ReqBo();
		reqBo.setParam("id", id);
		int payExist = us.getPayPasswordIsExist(reqBo).getResult();
		view.addObject("payExist", payExist);
		view.addObject(Constants.MENU_ID, Constants.MENU_ACCOUNT_SAFE);
		return view;
	}
	
	/**
	 * 修改登陆密码第一步
	 */
	@RequestMapping("modifyLoginPwd1.htm")
	public String modifyLoginPwd1(Model model){
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		String mobile = user.getMobile();
		String str1 = mobile.substring(0, 3);
		String str2 = mobile.substring(8);
		model.addAttribute("mobile", str1+"*****"+str2);
		model.addAttribute(Constants.MENU_ID, Constants.MENU_ACCOUNT_SAFE);
		return "account/account_center_one";
	}
	
	/**
	 * 修改登陆密码第二步
	 */
	@RequestMapping(value="modifyLoginPwd2.htm")
	public String modifyLoginPwd2(@RequestParam(value="token",required=false) String token, Model model){
		if(SessionUtil.get("token") != null){
			if(SessionUtil.get("token").toString().equals(token)){
				SessionUtil.delete("token");
				model.addAttribute(Constants.MENU_ID, Constants.MENU_ACCOUNT_SAFE);
				return "account/account_center_two";
			}else{
				return "redirect:/account/modifyLoginPwd1.htm";
			}
		}
		else{
			return "redirect:/account/modifyLoginPwd1.htm";
		}		
	}
	
	/**
	 * 修改登陆密码第三步
	 */
	@RequestMapping("modifyLoginPwd3.htm")
	public String modifyLoginPwd3(HttpServletRequest request, Model model){
		model.addAttribute(Constants.MENU_ID, Constants.MENU_ACCOUNT_SAFE);
		return "account/account_center_three";
	}
	
	/**
	 * 进入我的钱包页
	 */
	@RequestMapping(value = "toMyWallet.htm")
	public ModelAndView toMyWallet(HttpServletRequest request) {
		ReqBo reqBo = new ReqBo(request);
		ModelAndView view = new ModelAndView("account/my_wallet");
		IAmountLogService is = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_AMOUNTLOG, IAmountLogService.class);
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		reqBo.setParam("userId", user.getId());
		int num = Constants.WALLETS_NUM_PAGE;
		reqBo.setParam("rows", num);//每页显示10条记录
		
		if (reqBo.getParam("type") == null || "".equals(reqBo.getParam("type")) || !reqBo.getParam("type").toString().matches("[1,2]")) {
			reqBo.setParam("type", null);
		}
		if (reqBo.getParam("page") == null || !reqBo.getParam("page").toString().matches("[1-9][0-9]*")) {
			reqBo.setParam("page", 1);
		}
		
		ResBo<Pager<List<Map<Object,Object>>>> r = is.getAmountLogByUserId(reqBo);
		Pager<List<Map<Object,Object>>> p = r.getResult();
		List<Map<Object,Object>> l = p.getEntry();
		long total_num = p.getTotal();
		long total_page = (total_num + num - 1) / num;
		
//		if (reqBo.getParamLong("page") > total_page) {//如果当前超过总页数，则默认跳到最后一页
//			reqBo.setParam("page", total_page);
//			l = is.getAmountLogByUserId(reqBo).getResult().getEntry();
//		}
		view.addObject("amountLogList", l);
		view.addObject("type", reqBo.getParam("type"));
		view.addObject("page", reqBo.getParam("page"));
		view.addObject("total_page", total_page);
		
		IUserService ius = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
		User nuser = ius.getUserByUserId(reqBo).getResult();
		view.addObject("accountType", nuser.getAccountType());
		view.addObject("amount", nuser.getAmount());
		
		view.addObject(Constants.MENU_ID, Constants.MENU_MY_WALLET);
		return view;
	}
	
	/**
	 * 修改密码-验证码检查
	 * @param captcha
	 * @param picCode
	 * @return 
	 */
	@RequestMapping(value="checkCaptcha.htm", method=RequestMethod.POST)
	@ResponseBody
	public String checkCaptcha(@RequestParam("captcha") String captcha, @RequestParam("picCode") String picCode){
		String result = "0";
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		if(!picCode.equalsIgnoreCase((String)SessionUtil.get(Constants.IMG_VALIDATE)) && !captcha.equals((String)SessionUtil.get(user.getMobile()))){
			result = "3";	
		}else if(!picCode.equalsIgnoreCase((String)SessionUtil.get(Constants.IMG_VALIDATE))){
			result = "1";	
		}else if(!captcha.equals((String)SessionUtil.get(user.getMobile()))){
			result = "2";	
		}else{
			String token = String.valueOf(((int)(Math.random()*100000)));			
			SessionUtil.replace("token", token);
			result = token;
		}
		return result;
	}
	
	/**
	 * 修改登录密码
	 * @param oldPassword
	 * @param password
	 * @return 
	 */
	@RequestMapping(value="modifyLoginPwd.htm", method=RequestMethod.POST)
	@ResponseBody
	public String modifyLoginPwd(@RequestParam("oldPassword") String oldPassword, @RequestParam("password") String password){
		String result = "1";
		Long userId = (Long)SessionUtil.get(Constants.SESSION_USER_ID);
		IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
		ReqBo reqBo = new ReqBo();
		reqBo.setParam("userId", userId);
		ResBo<User> resBo_pay  = us.getUserByUserId(reqBo);
		User user = resBo_pay.getResult();
		String payPwd = user.getPayPassword();
		if(payPwd != null){
			if(payPwd.equals(password)){
				result = "2";
				return result;
			}		
		}
		if(SessionUtil.get(user.getMobile()) == null){
			result = "7";
		}else{
			//ReqBo reqBo = new ReqBo();
			reqBo.setParam("oldPassword", oldPassword);
			reqBo.setParam("password", password);
			reqBo.setParam("id", userId);
			//IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
			ResBo<User> resBo = us.updatePassword(reqBo);
			result = String.valueOf(resBo.getErrCode());
			if(result.equals("0")){
				SessionUtil.delete(user.getMobile());
			}
		}			
		return result;
	}
	
	/**
	 * 修改支付密码第一步
	 */
	@RequestMapping("modifyPayPwd1.htm")
	public String modifyPayPwd1(Model model){
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		String mobile = user.getMobile();
		String str1 = mobile.substring(0, 3);
		String str2 = mobile.substring(8);
		model.addAttribute("mobile", str1+"*****"+str2);
		model.addAttribute(Constants.MENU_ID, Constants.MENU_ACCOUNT_SAFE);
		return "account/account_one";
	}
	
	/**
	 * 修改支付密码第二步
	 */
	@RequestMapping(value="modifyPayPwd2.htm")
	public String modifyPayPwd2(@RequestParam(value="token",required=false) String token, Model model){
		if(SessionUtil.get("token") != null){
			if(SessionUtil.get("token").toString().equals(token)){
				SessionUtil.delete("token");
				model.addAttribute(Constants.MENU_ID, Constants.MENU_ACCOUNT_SAFE);
				return "account/account_two";
			}else{
				return "redirect:/account/modifyPayPwd1.htm";
			}
		}
		else{
			return "redirect:/account/modifyPayPwd1.htm";
		}		
	}
	
	/**
	 * 修改支付密码第三步
	 */
	@RequestMapping("modifyPayPwd3.htm")
	public String modifyPayPwd3(Model model){
		model.addAttribute(Constants.MENU_ID, Constants.MENU_ACCOUNT_SAFE);
		return "account/account_three";
	}
	
	/**
	 * 修改支付密码
	 * @param captcha
	 * @return 
	 */
	@RequestMapping(value="modifyPayPwd.htm", method=RequestMethod.POST)
	@ResponseBody
	public String modifyPayPwd(@RequestParam("password") String password){
		String result = "0";
		
		Long userId = (Long)SessionUtil.get(Constants.SESSION_USER_ID);
		IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
		ReqBo reqBo = new ReqBo();
		reqBo.setParam("userId", userId);
		ResBo<User> resBo_pay  = us.getUserByUserId(reqBo);
		User user = resBo_pay.getResult();
		
		String loginPwd = user.getPassword();
		if(loginPwd.equals(password)){
			result = "2";
		}else if(SessionUtil.get(user.getMobile()) == null){
			result = "7";
		}else{			
			//ReqBo reqBo = new ReqBo();
			reqBo.setParam("payPassword", password);
			reqBo.setParam("id", userId);
			//IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
			ResBo<User> resBo = us.updatePayPassword(reqBo);
			if(resBo.isSuccess()){
				SessionUtil.delete(user.getMobile());
				result = "1";
			}
		}
		return result;
	}
	
	/**
	 * 找回密码
	 */
	@NoNeedUserLogin
	@RequestMapping(value="resetPwd1.htm")
	public String resetPwd1(){
		return "account/reset_password_one";
	}
	
	@NoNeedUserLogin
	@RequestMapping(value="resetPwd2.htm")
	public String resetPwd2(@RequestParam(value="token",required=false) String token, Model model){
		if(SessionUtil.get("token") != null){
			if(SessionUtil.get("token").toString().equals(token)){
				SessionUtil.delete("token");
				return "account/reset_password_two";
			}else{
				return "account/reset_password_one";
			}
		}
		else{
			return "account/reset_password_one";
		}
	}
	
	@NoNeedUserLogin
	@RequestMapping(value="resetPwd3.htm")
	public String resetPwd3(){
		return "account/reset_password_three";
	}
	
	/**
	 * 找回密码验证
	 */
	@NoNeedUserLogin
	@RequestMapping(value="checkMess.htm", method=RequestMethod.POST)
	@ResponseBody
	public String checkMess(@RequestParam("mobile") String mobile, @RequestParam("captcha") String captcha, @RequestParam("picCode") String picCode){
		String result = "0";
		ReqBo reqBo = new ReqBo();
		reqBo.setParam("mobile", mobile);
		IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
		ResBo<User> resBo = us.getUserBymobile(reqBo);
		if(resBo.getResult() == null){
			result = "1";
		}else if(!captcha.equals((String)SessionUtil.get(mobile))){
			result = "2";
		}else if(!picCode.equals((String)SessionUtil.get(Constants.IMG_VALIDATE))){
			result = "3";
		}else{
			SessionUtil.delete(mobile);
			SessionUtil.replace("mobile", mobile);
			String token = String.valueOf(((int)(Math.random()*100000)));			
			SessionUtil.replace("token", token);
			result = token;
		}
		return result;
	}
	
	/**
	 * 重设新密码
	 */
	@NoNeedUserLogin
	@RequestMapping(value="resetPwd.htm", method=RequestMethod.POST)
	@ResponseBody
	public String resetPwd(@RequestParam("password") String password){
		String result = "0";		
		ReqBo reqBo = new ReqBo();	
		if(SessionUtil.get("mobile") == null){
			result = "7";
			return result;
		}		
		reqBo.setParam("mobile", SessionUtil.get("mobile"));
		IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
		ResBo<User> resBo = us.getUserBymobile(reqBo);
		User user = resBo.getResult();
		if(password.equals(user.getPayPassword())){
			result = "2";
			return result;
		}
		user.setPassword(password);
		reqBo.setReqModel(user);
		resBo = us.updateUser(reqBo);
		if(resBo.getResult() != null){
			result = "1";
			SessionUtil.delete("mobile");
		}
		return result;				
	}
}
