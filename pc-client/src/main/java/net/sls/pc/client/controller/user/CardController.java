package net.sls.pc.client.controller.user;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sls.dto.pc.user.AmountOrder;
import net.sls.dto.pc.user.User;
import net.sls.pc.client.util.Constants;
import net.sls.service.pc.user.IAmountOrderService;
import net.sls.service.pc.user.IUserService;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import util.httpclient.HttpRequestUtil;
import framework.web.ReqBo;
import framework.web.ResBo;


@Controller
@RequestMapping("card")
public class CardController {
	
	/**
	 * 充值卡充值
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("cardRecharge.htm")
	public String cardRecharge(HttpServletRequest request, Model model){
		model.addAttribute(Constants.MENU_ID, "card_recharge");
		return "card/card_recharge";
	}
	
	/**
	 * 根据充值卡卡号查询用户信息
	 * @param request
	 * @param 
	 * @return
	 */
	@RequestMapping("getUserByCardNum.htm")
	@ResponseBody
	public ResBo<User> getUserByCardNum(HttpServletRequest request){
		ReqBo reqBo = new ReqBo(request);
		IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);		
		ResBo<User> resBo =  us.getUserByCardNum(reqBo);
		if(resBo.getResult() != null){
			SessionUtil.replace(Constants.SESSION_ORDER_OWNER, resBo.getResult());
		}		
		return resBo;
	}
	
	@RequestMapping("toCard.htm")
	public String toCard(HttpServletRequest request, Model model){
		model.addAttribute("menu_id", "binding");
		return "card/bind_card";
	}
	@RequestMapping("reportLoss.htm")
	public String reportLoss(HttpServletRequest request, Model model){
		model.addAttribute("menu_id", "report_loss");
		model.addAttribute("flag", "update_card");
		return "card/bind_card";
	}
	/**
	 * 检测卡号和用户 是否匹配
	 */
	@RequestMapping("checkSwingCard.htm")
	@ResponseBody
	public String checkSwingCard(@RequestParam("cardNum") String cardNum) {
		String result = "true";
		//if(SessionUtil.get("cardUser") == null || !((User)SessionUtil.get("cardUser")).getBindCardNum().equals(cardNum)){
		if(SessionUtil.get(Constants.SESSION_ORDER_OWNER) == null || !((User)SessionUtil.get(Constants.SESSION_ORDER_OWNER)).getBindCardNum().equals(cardNum)){
			result = "false";
		}
		return result;
	}
	/**
	 * 账户资金充值
	 */
	@RequestMapping("recharge.htm")
	public void recharge(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//调用资金充值接口(状态为未完成，充值完成后为完成)
		ReqBo reqBo = new ReqBo(request);
		if(request.getParameter("payType") != null && request.getParameter("payType").equals("balance")){
			String json = "";
			if(!request.getParameter("payPwd").equals(((User)SessionUtil.get(Constants.SESSION_USER_INFO)).getPassword())){
				json = "{\"state\": "+false+",\"money\": \"支付密码错误\"}";
			}else{
				Long pid = (Long)SessionUtil.get(Constants.SESSION_USER_ID);
				reqBo.setParam("userId", pid);
				IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
				BigDecimal pamount = us.getUserByUserId(reqBo).getResult().getAmount();
				if(pamount.compareTo(reqBo.getParamDecimal("money")) == -1){
					json = "{\"state\": "+false+",\"money\": \"当前用户余额不足\"}";
				}else{
					Long id = ((User)SessionUtil.get(Constants.SESSION_ORDER_OWNER)).getId();
					BigDecimal amount = ((User)SessionUtil.get(Constants.SESSION_ORDER_OWNER)).getAmount();
					SessionUtil.delete(Constants.SESSION_ORDER_OWNER);
					reqBo.setParam("id", id);					
					reqBo.setParam("pamount", pamount);
					reqBo.setParam("amount", amount);					
					ResBo<String> resBo = us.updateUserAmountByUser(reqBo);		
					json = "{\"state\": "+resBo.isSuccess()+", \"money\": "+resBo.getResult()+"}";
				}								
			}		
			response.getWriter().print(json);
			response.setContentType("application/json;charset=UTF-8");
		}else{
			AmountOrder amountOrder = new AmountOrder(); 
			amountOrder.setUserId(((User)SessionUtil.get(Constants.SESSION_ORDER_OWNER)).getId());
			SessionUtil.delete(Constants.SESSION_ORDER_OWNER);
			BigDecimal bd = new BigDecimal(request.getParameter("money"));
			amountOrder.setRemark("资金充值（代）");
			amountOrder.setMoney(bd);
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
				response.getWriter().print(HttpRequestUtil.sendHttpRequest(Constants.ALIAPY_URL, param, HttpRequestUtil.POST));
				response.setContentType("text/html;charset=UTF-8");
			}
						
		}		
	}
	
	/**
	 * 根据手机号绑定卡号
	 * @param request
	 * @param 
	 * @return
	 */
	@RequestMapping("bindCardNum.htm")
	@ResponseBody
	public ResBo<User> bindCardNumByMobile(HttpServletRequest request){
		ResBo<User> userRes = new ResBo<User>();
		ReqBo reqBo = new ReqBo(request);
		IUserService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER,IUserService.class);
		userRes = us.getUserBymobile(reqBo);
		User user = userRes.getResult();
		user.setBindCardNum(reqBo.getParamStr("bindCardNum"));
		reqBo.setReqModel(user);
		userRes = us.updateUser(reqBo);
		userRes.setResult(new User());
		return userRes;
	}
	
}
