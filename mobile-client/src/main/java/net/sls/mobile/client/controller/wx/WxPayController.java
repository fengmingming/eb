package net.sls.mobile.client.controller.wx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sls.dto.pc.order.Orders;
import net.sls.mobile.client.annotation.NoNeedUserLogin;
import net.sls.mobile.client.util.Constants;
import net.sls.service.pc.order.IOrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import util.framework.FindServiceUtil;
import util.httpclient.HttpRequestUtil;
import util.json.JsonUtil;

import com.wx.config.WxPayConfig;
import com.wx.util.WxPayUtil;

import framework.web.ReqBo;
import framework.web.ResBo;

@Controller
@RequestMapping("wx")
public class WxPayController {
	Logger log = LoggerFactory.getLogger(WxPayController.class);
	/**
	 * 获取appid
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("getAppid.htm")
	@ResponseBody
	public Map<String, String> getAppid(HttpServletResponse response) throws IOException{
		Map<String, String> map = new HashMap<String, String>();
		map.put("appid", WxPayConfig.app_id);
		return map;
	}
	
	/**
	 * 获得openid
	 * @param request
	 * @param model
	 * @return
	 */
	@NoNeedUserLogin
	@SuppressWarnings("rawtypes")
	@RequestMapping("getOpenid.htm")
	public String getOpenid(HttpServletRequest request, Model model){
		log.info("\r\n======================start wxpay========================");
		String code = request.getParameter("code");		
		String state = request.getParameter("state");
		Map<String, String> map = new HashMap<String, String>();
		map.put("appid", WxPayConfig.app_id);
		map.put("secret", WxPayConfig.app_secret);
		map.put("code", code);
		map.put("grant_type", "authorization_code");		
		String json = HttpRequestUtil.sendHttpRequest(WxPayConfig.openid_url, map, HttpRequestUtil.POST);
		HashMap hm = JsonUtil.parseJson(json, HashMap.class);	
		HashMap order_info = JsonUtil.parseJson(state, HashMap.class);
		model.addAttribute("openid", hm.get("openid"));
		//SessionUtil.set("total_fee", order_info.get("total_free"));
		//SessionUtil.set("order_num", order_info.get("order_num"));orderId
		model.addAttribute("total_fee", order_info.get("total_fee"));
		model.addAttribute("order_num", order_info.get("order_num"));	
		model.addAttribute("orderId", order_info.get("orderId"));
		model.addAttribute("body", WxPayConfig.body);				
		log.info("\r\n======微信返回code:"+code+"订单号:"+order_info.get("order_num")+"用户openid:"+hm.get("openid")+"orderId"+order_info.get("orderId"));
		log.info("\r\n======================first 根据code获得openid ========================");
		return "wx/wxPay";
	}
	/**
	 * 获取prepayid
	 * @param request
	 * @return
	 */
	@RequestMapping("getPrepayid.htm")
	@ResponseBody
	public Map<String, Object> getPrepayid(HttpServletRequest request){
		String total_fee = request.getParameter("total_fee");
		String order_num = request.getParameter("order_num");
		//获取客户端ip（反向代理获取不到）
		String spbill_create_ip = request.getRemoteAddr();
		String openid = request.getParameter("openid");
		String nonce_str = WxPayUtil.CreateNoncestr();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 设置预支付参数  (按照ASCII码从小到大排列)
	    SortedMap<String, String> signParams = new TreeMap<String, String>();  
	    signParams.put("appid", WxPayConfig.app_id); 
	    signParams.put("attach", spbill_create_ip); 
	    signParams.put("body", WxPayConfig.body); 
	    signParams.put("mch_id", WxPayConfig.mchid); 
	    signParams.put("notify_url", WxPayConfig.notify_url); 
	    signParams.put("nonce_str", nonce_str);  
	    signParams.put("out_trade_no", order_num); 
	    signParams.put("openid", openid);  
	    signParams.put("spbill_create_ip", spbill_create_ip); 
	    signParams.put("total_fee", total_fee); 	   
	    signParams.put("trade_type", "JSAPI");  	    	    
	    String sign = "";  
	    try {  
	        sign = WxPayUtil.createSign(signParams, WxPayConfig.key);  
	    } catch (Exception e) {  	    	
	        e.printStackTrace();  
	    }  
	    // 增加非参与签名的额外参数  
	    
	    log.info("\r\n======用户支付签名:"+sign);
	    
	    signParams.put("sign", sign);  
	    String prePayId = WxPayUtil.getPrePayId(signParams);// 预支付订单号 
	    	    
	    log.info("\r\n======prepayid:"+prePayId);
	    log.info("\r\n======================second 根据openid获得prepayid ========================");	    
	    if(prePayId.equals("")){
	    	map.put("state", false);
	    	map.put("message", "统一支付接口获取预支付订单出错");
		}else{
			SortedMap<String, String> paySignParams = new TreeMap<String, String>(); 
			String timeStamp = String.valueOf(new Date().getTime());
			paySignParams.put("appId", WxPayConfig.app_id);
			paySignParams.put("nonceStr", nonce_str);
			paySignParams.put("package", "prepay_id="+prePayId);
			paySignParams.put("signType", "MD5");
			paySignParams.put("timeStamp", timeStamp);
			//根据预支付id生成支付签名
			String paySign = WxPayUtil.createSign(paySignParams, WxPayConfig.key);
			log.info("\r\n======根据prepayid的得到的paySign:"+paySign);
			map.put("state", true);
		    //Map<String, String> mp = new HashMap<String, String>();
		    map.put("appId", WxPayConfig.app_id);
		    map.put("timeStamp", timeStamp);
		    map.put("nonceStr", nonce_str);
		    map.put("packages", "prepay_id="+prePayId);
		    map.put("signType", "MD5");
		    map.put("paySign", paySign);
		   // map.put("message", mp);
		}
	    return map;
	}
	/**
	 * 微信支付回调函数
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@NoNeedUserLogin
	@RequestMapping("execute.htm")
	public void execute(HttpServletRequest request, HttpServletResponse response){
		log.info("\r\n====================== 回调开始 ========================");
		try{
			InputStream is = null;
			StringBuffer sb = new StringBuffer();	 
			is = request.getInputStream();	
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));		
			String buffer = null;			  	
			while ((buffer = br.readLine()) != null){
				sb.append(buffer);
			}		
			String result = sb.toString();
			log.info("\r\n=======微信返回参数xml格式:"+result);
			//转换为map
		   // Map<String, String> map=new HashMap<String, String>();
			SortedMap<String, String> map =  WxPayUtil.doXMLParse(result);
		    if(map != null){
		    	 if(map.get("return_code").equalsIgnoreCase("SUCCESS")){
		    		String total_fee = map.get("total_fee");
		    		String orderNum = map.get("out_trade_no");    		
		   		    String signreturn=map.get("sign");
		   		 	/***根据返回值生成签名,生成规则和提交到微信的时候规则一致**/
					//SortedMap<String, String> signParams = (SortedMap<String, String>)map;
					
					/*
					signParams.put("appid", WxPayConfig.app_id); 
				    signParams.put("attach", map.get("attach")); 
				    signParams.put("body", WxPayConfig.body); 
				    signParams.put("mch_id", WxPayConfig.mchid); 
				    signParams.put("notify_url", WxPayConfig.notify_url); 
				    signParams.put("nonce_str", map.get("nonce_str"));  
				    signParams.put("out_trade_no", orderNum); 
				    signParams.put("openid", map.get("openid"));  
				    signParams.put("spbill_create_ip", map.get("attach")); 
				    signParams.put("total_fee", total_fee); 	   
				    signParams.put("trade_type", "JSAPI");
				    */
					String sign = WxPayUtil.createSign(map, WxPayConfig.key);
					log.info("\r\n======根据回调参数生成的sign签名:"+sign);
					/**比较签名**/
		   		    if(signreturn.equalsIgnoreCase(sign) && map.get("result_code").equalsIgnoreCase("SUCCESS")){
		   		    	/***处理自己的业务*/
		   		    	log.info("\r\n=====签名验证成功开始业务处理");
		   		    	IOrderService os = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_ORDER, IOrderService.class);
		   		    	ReqBo reqBo = new ReqBo();
		   		    	reqBo.setParam("orderNum", orderNum);
		   		    	ResBo<Orders> orderRes = os.getOrderByOrderNum(reqBo);
		   		    	if(orderRes.isSuccess() && orderRes.getResult() != null){
		   		    		Orders order = orderRes.getResult();
		   		    		reqBo.setParam("orderId", order.getId());
		   		    		BigDecimal total = new BigDecimal(total_fee);
		   		    		BigDecimal price = total.divide(new BigDecimal("100"));
		   		    		log.info("\r\n=====金额除以100:"+price);
		   		    		reqBo.setParam("price", price);
		   		    		ResBo<Boolean> rb = os.confirmPaid(reqBo);
							if(rb.isSuccess() && rb.getResult() != null && rb.getResult().booleanValue()){
								log.info("\r\n====修改订单支付状态成功");
							}else{
								log.info("\r\n====修改订单支付状态失败");
							}
		   		    	}
		   		    	response.getWriter().write(WxPayUtil.setXML("SUCCESS", ""));
		   		    }else{
		   		    	response.getWriter().write(WxPayUtil.setXML("FAIL", "签名失败"));
		   		    	log.info("\r\n签名验证失败，或者result_code为FAIL");
		   		    }		   		    
		    	 } else{
		    		 log.info("\r\n通信失败return_code为FAIL");
		    	 }
			}else{
				log.info("\r\n返回map为空");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			log.info("\r\n====================== 回调结束 ========================\r\n\r\n");
		}
	}
	
	//微信支付成功页面
	@NoNeedUserLogin
	@RequestMapping(value = "wxPayResult.htm")
	public String wxPayResult(@RequestParam("orderId") String orderId,Model model){
		model.addAttribute("orderId", orderId);
		return "wx/wxPayResult";
	}
}
