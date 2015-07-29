package net.sls.pc.client.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sls.dto.pc.order.Orders;
import net.sls.dto.pc.user.AmountBeanDto;
import net.sls.dto.pc.user.AmountOrder;
import net.sls.pc.client.annotation.NoNeedUserLogin;
import net.sls.pc.client.util.Constants;
import net.sls.service.pc.order.IOrderService;
import net.sls.service.pc.user.IAmountOrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;

import com.alipay.util.AlipayCore;
import com.alipay.util.AlipayNotify;

import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * 支付宝支付成功后跳转到的controller
 *
 */
@Controller
@RequestMapping("luoyang")
//@RequestMapping("alipay")
public class PayController {
	Logger log = LoggerFactory.getLogger(PayController.class);
	
	/**
	 * 支付宝返回的return_url
	 * @param request
	 * @return
	 */
	@NoNeedUserLogin
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="return_url.htm")
    public ModelAndView returnUrl(HttpServletRequest request){
		ModelAndView view = new ModelAndView("alipay/success");
		
		log.info("\r\n============== Starting Logging at return url ==============");
		
		try{
			//获取支付宝GET过来反馈信息
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
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			
			
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//商户订单号

			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//支付宝交易号

			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
			String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");

			String sWord = "out_trade_no=" + out_trade_no + "\n trade_no=" + trade_no 
					+"trade_status="+trade_status
					+ "\n notify接收到的参数：" + AlipayCore.createLinkString(params);
			sWord = sWord+"\r\n[excute before validate params.]";
			log.info(sWord);
			
			//计算得出通知验证结果
			boolean verify_result = AlipayNotify.verify(params);
			if(verify_result){//验证成功				
				
				if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
					
					log.info("\r\nTRADE_FINISHED or TRADE_SUCCESS");
					
					//判断该笔订单是否在商户网站中已经做过处理
						//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						//如果有做过处理，不执行商户的业务程序
					String prefix = out_trade_no.split(Constants.ALIAPY_PREFIX_SPLIT)[0];
					String identify = out_trade_no.split(Constants.ALIAPY_PREFIX_SPLIT)[1];
					log.info("\r\n订单前缀："+prefix+",订单号："+identify);
					//订单付款
					if(Constants.ALIAPY_PREFIX_ORDER.equals(prefix + Constants.ALIAPY_PREFIX_SPLIT)){
						log.info("\r\n订单付款");
						view = new ModelAndView("order/order_payment_result");
						view.addObject("orderNum", identify);
						//修改订单支付状态
						ReqBo reqBo = new ReqBo(request);
						reqBo.setParam("orderNum", identify);
						IOrderService os = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_ORDER, IOrderService.class);
						ResBo<Orders> orderRes = os.getOrderByOrderNum(reqBo);
						if(orderRes.isSuccess() && orderRes.getResult() != null){
							Orders order = orderRes.getResult();
							//只有订单处于未付款状态才需要修改
							if(order.getIsPaid().intValue() == 1){
								log.info("\r\n订单状态为未付款");
								log.info("\r\n订单Id："+order.getId());
								log.info("\r\n订单价格："+total_fee);
								view.addObject("orderId", order.getId());
								reqBo.setParam("orderId", order.getId());
								BigDecimal total = new BigDecimal(total_fee);
								total.setScale(2);
								reqBo.setParam("price", total);
								ResBo<Boolean> result = os.confirmPaid(reqBo);
								if(result.isSuccess() && result.getResult() != null && result.getResult().booleanValue()){
									log.info("\r\n修改订单支付状态成功");
									int i = os.updateOrderPayByOrderId(reqBo).getResult();
									if(i == 1){
										log.info("\r\n增加用户支付账户成功");
									}else{
										log.info("\r\n增加用户支付账户失败");
									}
									
								}else{
									log.info("\r\n修改订单支付状态失败");
								}
							}
						}
					}
					//账户充值
					if(Constants.ALIAPY_PREFIX_RECHARGE.equals(prefix + Constants.ALIAPY_PREFIX_SPLIT)){
						log.info("\r\n账户充值");
						view = new ModelAndView("account/recharge_result");
						//修改账户账户充值状态
						IAmountOrderService as = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_AMOUNTORDER, IAmountOrderService.class);
						ReqBo reqBo = new ReqBo(request);
						reqBo.setParam("voucherOrderNum", identify);
						ResBo<AmountOrder> amountOrderRes = as.getRechargeByVoucherOrderNum(reqBo);
						if(amountOrderRes.isSuccess() && amountOrderRes.getResult() != null){
							if(amountOrderRes.getResult().getStatus().intValue() == 0){
								log.info("\r\n账户状态为未充值成功，进行充值");
								AmountBeanDto amountBeanDto = new AmountBeanDto();
								Long userId = (Long)SessionUtil.get(Constants.SESSION_USER_ID);
								amountBeanDto.setUserId(userId);
								BigDecimal total = new BigDecimal(total_fee);
								total.setScale(2);
								amountBeanDto.setMoney(total);
								amountBeanDto.setThirdOrderNum(trade_no);
								amountBeanDto.setVoucherOrderNum(identify);
								reqBo.setReqModel(amountBeanDto);
								log.info("\r\n充值："+total_fee);
								ResBo<AmountOrder> amountOrder = as.rechargeAfterAmount(reqBo);
								if(amountOrder.isSuccess() && amountOrder.getResult() != null){
									log.info("\r\n账户充值成功");
								}else{
									log.info("\r\n账户充值失败");
								}
							}else{
								log.info("\r\n账户状态为已充值成功，不需要进行充值");
							}
						}
					}
				}	
				log.info("\r\n验证成功");
				
			}else{		
				log.info("\r\n验证失败");
			}	
			
			log.info("\r\n============== Leaving Log at return url ==============");
					
		}catch(Exception e){
			e.printStackTrace();
		}		
        return view;
    }

	/**
	 * 支付宝返回的notify_url
	 * @param request
	 * @param response
	 * @return
	 */
	@NoNeedUserLogin
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "notify_url.htm", method = RequestMethod.POST)
	@ResponseBody
	public String notifyUrl(HttpServletRequest request, HttpServletResponse response) {
		
		log.info("\r\n============== Starting Logging at notify ==============");
		
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}		
	
		//获取支付宝的通知返回参数，如下代码支持中文，但需处理编码转换异常//
		//商户订单号
		//String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//支付宝交易号
		//String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//交易状态
		//String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		
		String outTradeNo = request.getParameter("out_trade_no");
		String tradeNo = request.getParameter("trade_no");
		String tradeStatus = request.getParameter("trade_status");
		// String notifyId = request.getParameter("notify_id");
		String totalFee = request.getParameter("total_fee");
		
		if (AlipayNotify.verify(params)) {// 验证成功
			if (tradeStatus.equals("TRADE_FINISHED") || tradeStatus.equals("TRADE_SUCCESS")) 
			{
				boolean success = false;
				
				String prefix = outTradeNo.split(Constants.ALIAPY_PREFIX_SPLIT)[0];
				String identify = outTradeNo.split(Constants.ALIAPY_PREFIX_SPLIT)[1];
				log.info("\r\n订单前缀："+prefix+",订单号："+identify);
				//订单付款
				if(Constants.ALIAPY_PREFIX_ORDER.equals(prefix + Constants.ALIAPY_PREFIX_SPLIT)){
					log.info("\r\n订单付款");
					//修改订单状态
					ReqBo reqBo = new ReqBo(request);
					reqBo.setParam("orderNum", identify);
					IOrderService os = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_ORDER, IOrderService.class);
					ResBo<Orders> orderRes = os.getOrderByOrderNum(reqBo);
					if(orderRes.isSuccess() && orderRes.getResult() != null){
						Orders order = orderRes.getResult();
						//只有订单处于未付款状态才需要修改
						if(order.getIsPaid().intValue() == 1){
							log.info("\r\n订单状态为未付款");
							reqBo.setParam("orderId", order.getId());
							BigDecimal total = new BigDecimal(totalFee);
							total.setScale(2);
							reqBo.setParam("price", total);
							ResBo<Boolean> result = os.confirmPaid(reqBo);
							if(result.isSuccess() && result.getResult() != null && result.getResult()){
								log.info("\r\n订单付款成功");
								return "success";
							}
							log.info("\r\n订单付款失败");
							return "fail";
						}
						log.info("\r\n订单状态为已付款");
						return "success";
					}
				}
				//账户充值
				if(Constants.ALIAPY_PREFIX_RECHARGE.equals(prefix + Constants.ALIAPY_PREFIX_SPLIT)){
					log.info("\r\n账户充值");
					IAmountOrderService as = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_AMOUNTORDER, IAmountOrderService.class);
					//修改账户充值状态
					ReqBo reqBo = new ReqBo(request);
					reqBo.setParam("voucherOrderNum", identify);
					ResBo<AmountOrder> amountOrderRes = as.getRechargeByVoucherOrderNum(reqBo);
					if(amountOrderRes.isSuccess() && amountOrderRes.getResult() != null){
						if(amountOrderRes.getResult().getStatus().intValue() == 0){
							log.info("\r\n账户状态为未充值成功，进行充值");
							AmountBeanDto amountBeanDto = new AmountBeanDto();
							Long userId = (Long)SessionUtil.get(Constants.SESSION_USER_ID);
							amountBeanDto.setUserId(userId);
							BigDecimal total = new BigDecimal(totalFee);
							total.setScale(2);
							amountBeanDto.setMoney(total);
							amountBeanDto.setThirdOrderNum(tradeNo);
							amountBeanDto.setVoucherOrderNum(identify);
							reqBo.setReqModel(amountBeanDto);
							log.info("\r\n充值："+totalFee);
							ResBo<AmountOrder> amountOrder = as.rechargeAfterAmount(reqBo);
							if(amountOrder.isSuccess()){
								log.info("\r\n账户充值成功");
								return "success";
							}else{
								log.info("\r\n账户充值失败");
								return "fail";
							}
						}else{
							log.info("\r\n账户已充值成功");
							return "success";
						}
					}
				}
				
				if (!success){
					log.info("\r\n============== end Log at notify (fail) ==============");
					return "fail";
				}
				log.info("\r\n============== end Log at notify ==============");
			}
			return "success";
		} else {// 验证失败
			return "fail";
		}

	}
}
