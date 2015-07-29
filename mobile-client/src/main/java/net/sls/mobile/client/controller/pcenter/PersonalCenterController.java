package net.sls.mobile.client.controller.pcenter;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sls.dto.pc.act.Coupon;
import net.sls.dto.pc.user.User;
import net.sls.dto.pc.user.UserAddress;
import net.sls.mobile.client.annotation.NoNeedUserLogin;
import net.sls.mobile.client.util.Constants;
import net.sls.service.pc.act.ICouponService;
import net.sls.service.pc.order.IOrderService;
import net.sls.service.pc.user.IAmountLogService;
import net.sls.service.pc.user.IAmountOrderService;
import net.sls.service.pc.user.IUserAddressService;
import net.sls.service.pc.user.IUserService;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipayCore;
import com.alipay.util.AlipayNotify;
import com.alipay.util.AlipaySubmit;
import com.alipay.util.UtilDate;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * 
 * 用户中心
 * @author fengmingming 20150421
 * @see #index 用户中心首页
 * @see #myWallet 我的钱包页
 * @see #accountDetails 钱包明细页
 * @see #accountDetails 分页查询钱包明细
 * 
 * */
@Controller
@RequestMapping("pcenter")
public class PersonalCenterController {

	@RequestMapping("index.htm")
	public String index(Model model){
		IUserService userSer = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
		ResBo<User> resBo = userSer.getUserByUserId(new ReqBo().setParam("userId", SessionUtil.get(Constants.SESSION_USER_ID)));
		if(resBo.isSuccess()){
			model.addAttribute("user", resBo.getResult());
		}
		return "user/my_sls";
	}
	
	@RequestMapping("/wallet.htm")
	public String myWallet(Model model){
		IUserService userSer = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
		ResBo<User> resBo = userSer.getUserByUserId(new ReqBo().setParam("userId", SessionUtil.get(Constants.SESSION_USER_ID)));
		if(resBo.isSuccess()){
			model.addAttribute("user", resBo.getResult());
		}
		return "user/my_wallet";
	}
	
	@RequestMapping("/accountDetails.htm")
	public String accountDetails(Model model){
		return "user/pay_list";
	}
	
	
	/**
	 * 
	 * 
	 * 
	 * */
	@RequestMapping("/accountDetails/list.htm")
	@ResponseBody
	public ResBo<Pager<List<Map<Object, Object>>>> accountDetails(HttpServletRequest req,Model model){
		IAmountLogService logSer = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_AMOUNTLOG, IAmountLogService.class);
		return logSer.getAmountLogByUserId(new ReqBo(req).setParam("userId", SessionUtil.get(Constants.SESSION_USER_ID)));
	}
	
	@RequestMapping("/orders.htm")
	public String orders(@RequestParam("type")int type,Model model){
		model.addAttribute("type", type);
		return "order/order_center";
	}
	
	@RequestMapping("/orders/list.htm")
	@ResponseBody
	public ResBo<Pager<List<Map<Object, Object>>>> ordersList(HttpServletRequest req,Model model){
		IOrderService orderSer = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_ORDER, IOrderService.class);
		ReqBo reqBo = new ReqBo();
		reqBo.setParam("userId", SessionUtil.get(Constants.SESSION_USER_ID));
		String status = req.getParameter("status");
		String isPaid = req.getParameter("isPaid");
		String state = req.getParameter("state");
		if(status != null){
			List<Integer> list = new ArrayList<Integer>();
			for(String s:status.split(",")){
				list.add(Integer.parseInt(s));
			}
			reqBo.setParam("status", list);
		}
		if(isPaid != null){
			reqBo.setParam("isPaid", Integer.parseInt(isPaid));
		}
		if(state != null){
			reqBo.setParam("state", Integer.parseInt(state));
		}
		reqBo.setParam("page", req.getParameter("page"));
		reqBo.setParam("rows", req.getParameter("rows"));
		reqBo.setParam("column", "createTime");
		reqBo.setParam("sort", Constants.ORDERBY_DESC);
		return orderSer.selectOrderListsByUserId(reqBo);
	}
	
	@RequestMapping("/orders/detail/{orderId}.htm")
	public String ordersDetail(@PathVariable("orderId") long orderId,Model model){
		IOrderService orderSer = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_ORDER, IOrderService.class);
		ResBo<Map<Object,Object>> resBo = orderSer.getOrderInfo(new ReqBo().setParam("id", orderId));
		if(resBo.isSuccess()){
			model.addAttribute("re", resBo.getResult());
		}
		return "order/order_detail";
	}
	
	@RequestMapping("/orders/delete/{orderId}.htm")
	@ResponseBody
	public ResBo<?> deleteOrders(@PathVariable("orderId") long orderId){
		IOrderService orderSer = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_ORDER, IOrderService.class);
		return orderSer.cancelOrder(new ReqBo().setParam("orderId", orderId).setParam("userId", SessionUtil.get(Constants.SESSION_USER_ID)));
	}
	
	/**
	 * 
	 * 支付宝支付请求
	 * 1.通过http协议请求支付宝，并从返回结果中获得 token
	 * 2.更加token组装支付宝请求url，将用户浏览器重定向到支付宝支付界面
	 * @param orderId 订单id，orderNum 订单号，money 支付金额
	 * @return void
	 * 
	 * */
	@RequestMapping("pay.htm")
	public void pay(@RequestParam("orderId")long orderId,@RequestParam("orderNum") String orderNum,@RequestParam("money") String money,HttpServletResponse res) throws Exception{
		//支付宝网关地址
		String ALIPAY_GATEWAY_NEW = "http://wappaygw.alipay.com/service/rest.htm?";
		//返回格式
		String format = "xml";
		//必填，不需要修改
		//返回格式
		String v = "2.0";
		//必填，不需要修改
		//请求号
		String req_id = UtilDate.getOrderNum();
		//必填，须保证每次请求都是唯一
		//req_data详细信息
		//服务器异步通知页面路径
		String notify_url = "http://m.365020.com/pcenter/alipaynotify/"+orderId+".htm";
		//需http://格式的完整路径，不能加?id=123这类自定义参数
		//页面跳转同步通知页面路径
		String call_back_url = "http://m.365020.com/pcenter/alipaycallback/"+orderId+".htm";
		//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
		//操作中断返回地址
		String merchant_url = "http://m.365020.com/pcenter/index.htm";
		//用户付款中途退出返回商户的地址。需http://格式的完整路径，不允许加?id=123这类自定义参数
		//商户订单号
		String out_trade_no = orderNum;
		//商户网站订单系统中唯一订单号，必填
		//订单名称
		String subject = orderNum;
		//必填
		//付款金额
		String total_fee = money;
		//必填
		//请求业务参数详细
		String req_dataToken = "<direct_trade_create_req><notify_url>" + notify_url + "</notify_url><call_back_url>" + call_back_url + "</call_back_url><seller_account_name>" + AlipayConfig.seller_email + "</seller_account_name><out_trade_no>" + out_trade_no + "</out_trade_no><subject>" + subject + "</subject><total_fee>" + total_fee + "</total_fee><merchant_url>" + merchant_url + "</merchant_url></direct_trade_create_req>";
		//必填
		//把请求参数打包成数组
		Map<String, String> sParaTempToken = new HashMap<String, String>();
		sParaTempToken.put("service", "alipay.wap.trade.create.direct");
		sParaTempToken.put("partner", AlipayConfig.partner);
		sParaTempToken.put("_input_charset", AlipayConfig.input_charset);
		sParaTempToken.put("sec_id", AlipayConfig.sign_type);
		sParaTempToken.put("format", format);
		sParaTempToken.put("v", v);
		sParaTempToken.put("req_id", req_id);
		sParaTempToken.put("req_data", req_dataToken);
		//建立请求
		String sHtmlTextToken = AlipaySubmit.buildRequest(ALIPAY_GATEWAY_NEW,"", "",sParaTempToken);
		//URLDECODE返回的信息
		sHtmlTextToken = URLDecoder.decode(sHtmlTextToken,AlipayConfig.input_charset);
		//获取token
		String request_token = AlipaySubmit.getRequestToken(sHtmlTextToken);
		//业务详细
		String req_data = "<auth_and_execute_req><request_token>" + request_token + "</request_token></auth_and_execute_req>";
		//必填
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "alipay.wap.auth.authAndExecute");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("sec_id", AlipayConfig.sign_type);
		sParaTemp.put("format", format);
		sParaTemp.put("v", v);
		sParaTemp.put("req_data", req_data);
		Map<String, String> sPara = AlipayCore.paraFilter(sParaTemp);
		//生成签名结果
        String mysign = AlipaySubmit.buildRequestMysign(sPara);

        //签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", mysign);
        if(! sPara.get("service").equals("alipay.wap.trade.create.direct") && ! sPara.get("service").equals("alipay.wap.auth.authAndExecute")) {
        	sPara.put("sign_type", AlipayConfig.sign_type);
        }
        StringBuilder sb = new StringBuilder(ALIPAY_GATEWAY_NEW+"_input_charset=" + AlipayConfig.input_charset);
        for(String key:sPara.keySet()){
        	sb.append("&");
        	sb.append(key);
        	sb.append("=");
        	sb.append(sPara.get(key));
        }
        res.sendRedirect(sb.toString());
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/alipaycallback/{orderId}.htm")
	public String alipayCallBack(@PathVariable("orderId")long orderId,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		String result = new String(request.getParameter("result").getBytes("ISO-8859-1"),"UTF-8");
		String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
		boolean verify_result = AlipayNotify.verifyReturn(params);
		if("success".equalsIgnoreCase(result)){
			if(verify_result){
				IOrderService orderSer = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_ORDER, IOrderService.class);
				orderSer.getOrderInfo(new ReqBo());
				ResBo<Boolean> resBo = orderSer.confirmPaid(new ReqBo().setParam("orderId", orderId).setParam("price", new BigDecimal(total_fee)));
				if(resBo.isSuccess()&&resBo.getResult()){
					return "";
				}
			}
		}
		return "";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/alipaynotify/{orderId}.htm")
	@NoNeedUserLogin
	public void notify(@PathVariable("orderId")long orderId,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		//RSA签名解密
	   	if(AlipayConfig.sign_type.equals("0001")) {
	   		params = AlipayNotify.decrypt(params);
	   	}
		Document doc_notify_data = DocumentHelper.parseText(params.get("notify_data"));
		String total_fee = doc_notify_data.selectSingleNode( "//notify/total_fee" ).getText();
		String trade_status = doc_notify_data.selectSingleNode( "//notify/trade_status" ).getText();
		if(AlipayNotify.verifyNotify(params)){
			if(trade_status.equals("TRADE_FINISHED")||trade_status.equals("TRADE_SUCCESS")){
				IOrderService orderSer = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_ORDER, IOrderService.class);
				orderSer.getOrderInfo(new ReqBo());
				ResBo<Boolean> resBo = orderSer.confirmPaid(new ReqBo().setParam("orderId", orderId).setParam("price", new BigDecimal(total_fee)));
				if(resBo.isSuccess()&&resBo.getResult()){
					response.getWriter().write("success");
					return;
				}
			}
			response.getWriter().println("fail");
		}else{
			response.getWriter().println("fail");
		}
	}
	
	@RequestMapping("recharge/index.htm")
	public String recharge_index(){
		return "user/recharge";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("recharge/card.htm")
	@ResponseBody
	public ResBo<?> recharge_card(HttpServletRequest req){
		if(req.getParameter("vericode")==null||!req.getParameter("vericode").equals(SessionUtil.get(Constants.IMG_VALIDATE))){
			return new ResBo("验证码错误");
		}
		ReqBo reqBo = new ReqBo();
		reqBo.setParam("userId", SessionUtil.get(Constants.SESSION_USER_ID));
		reqBo.setParam("password", req.getParameter("cardNum"));
		IAmountOrderService ser = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_AMOUNTORDER, IAmountOrderService.class);
		return ser.rechargeCardAmount(reqBo);
	}
	
	@RequestMapping("address/index.htm")
	public String addressIndex(Model model){
		ReqBo reqBo = new ReqBo();
		reqBo.setParam("userId", (Long)SessionUtil.get(Constants.SESSION_USER_ID));
		reqBo.setParam("page", 1);
		reqBo.setParam("rows", 3);
		IUserAddressService ius = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USERADDRESS, IUserAddressService.class);
		ResBo<Pager<List<UserAddress>>> reqAddress = ius.selectUserAddressListsByUserId(reqBo);
		model.addAttribute("addresslist", reqAddress.getResult().getEntry());
		return "user/address";
	}
	
	@RequestMapping("address/addOrup.htm")
	public String addressUpOrAd(@RequestParam(value="addressId",required=false) Long id,Model model){
		if(id != null){
			IUserAddressService ius = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USERADDRESS, IUserAddressService.class);
			ResBo<UserAddress> resBo = ius.getUserAddressById(new ReqBo().setParam("id", id));
			model.addAttribute("address", resBo.getResult());
		}
		return "user/address_add";
	}
	
	/**
	 * 确认收货
	 * */
	@RequestMapping("/orders/delivery.htm")
	@ResponseBody
	public ResBo<?> delivery(@RequestParam("orderId")long orderId){
		IOrderService orderSer = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_ORDER, IOrderService.class);
		return orderSer.confirmOrder(new ReqBo().setParam("orderId",orderId));
	}
	
	/**
	 * 我的优惠劵 
	 * @param request
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value="coupon/myCoupon.htm")
    public String getMyCouponByUserId(HttpServletRequest request,Model model){
		ReqBo reqBo = new ReqBo(request);
		int type = 0;	
		if(reqBo.getParam("type") != null){
			type = reqBo.getParamInt("type");
		}		
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		reqBo.setParam("userId", user.getId());
		//int rows = Constants.GOODS_NUM_PAGE;
		int rows = 8;
		reqBo.setParam("rows", rows);		
		reqBo.setParam("page", 1);
		ResBo<Pager<List<Map<Object, Object>>>> resBo = null;
	    ICouponService couponS = (ICouponService)FindServiceUtil.findRemoteService(Constants.SERVICE_URL_COUPON, ICouponService.class);
	    if(type == 1){
	    	 resBo = couponS.selectUnUseCouponListsByUserId(reqBo);
	    }else if(type == 2){
	    	resBo = couponS.selectUseCouponListsByUserId(reqBo);
	    }else if(type == 3){
	    	resBo = couponS.selectExpireCouponListsByUserId(reqBo);
	    }else{
	    	resBo = null;
	    }
	    Pager<List<Map<Object,Object>>> p = resBo.getResult();
		List<Map<Object,Object>> l = p.getEntry();
		model.addAttribute("coupon", l);	
		model.addAttribute("type", type); 
		return "user/my_coupon";			    
	}
	
	/**
	 * 未使用优惠劵  ajax
	 * @param request
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value="coupon/myCouponAjax.htm")
	@ResponseBody
    public Pager<List<Map<Object,Object>>> getMyCouponByUserId(HttpServletRequest request){
		ReqBo reqBo = new ReqBo(request);
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		reqBo.setParam("userId", user.getId());
		int type = reqBo.getParamInt("type");
		//int rows = Constants.GOODS_NUM_PAGE;
		int rows = 8;
		reqBo.setParam("rows", rows);
	    ICouponService couponS = (ICouponService)FindServiceUtil.findRemoteService(Constants.SERVICE_URL_COUPON, ICouponService.class);	   
	    ResBo<Pager<List<Map<Object, Object>>>> resBo = null;	   
	    if(type == 1){
	    	resBo = couponS.selectUnUseCouponListsByUserId(reqBo);
	    }else if(type == 2){
	    	resBo = couponS.selectUseCouponListsByUserId(reqBo);
	    }else{
	    	resBo = couponS.selectExpireCouponListsByUserId(reqBo);
	    }
	    Pager<List<Map<Object,Object>>> p = resBo.getResult();	
		long total_num = p.getTotal();
		long total_page = (total_num + rows - 1) / rows;
		p.setTotal(total_page);
		return p;    
	}
	
	/**
	 * 根据code获取优惠券
	 * @param request
	 * @return
	 */
	@RequestMapping(value="coupon/getCouponByCode.htm")
	@ResponseBody
	public Coupon getCouponByCode(HttpServletRequest request){
		ReqBo reqBo = new ReqBo(request);
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		Coupon coupon = null;	
		reqBo.setParam("id", user.getId());
		ICouponService couponS = (ICouponService)FindServiceUtil.findRemoteService(Constants.SERVICE_URL_COUPON, ICouponService.class);
		coupon = couponS.selectCouponByCode(reqBo).getResult();
		if(coupon != null){
			int i = couponS.updateUserCouponByCode(reqBo).getResult();
			if(i != 1){
				coupon = null;
			}
		}		
		return coupon;
	}
}
