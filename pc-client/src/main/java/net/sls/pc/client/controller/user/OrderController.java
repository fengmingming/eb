package net.sls.pc.client.controller.user;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sls.dto.pc.act.Buyer;
import net.sls.dto.pc.order.OrderRefund;
import net.sls.dto.pc.order.ResOrder;
import net.sls.dto.pc.user.User;
import net.sls.dto.pc.user.UserAddress;
import net.sls.pc.client.util.Constants;
import net.sls.service.pc.order.IOrderService;
import net.sls.service.pc.product.IPavilionInfoService;
import net.sls.service.pc.user.IUserAddressService;
import net.sls.service.pc.user.IUserService;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import util.httpclient.HttpRequestUtil;
import util.httpclient.HttpSend;
import util.model.DeliveryTypeEnum;
import util.model.OrderStatusEnum;
import util.model.PayEnum;
import framework.jredis.DefaultJredis;
import framework.jredis.IJredis;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * 订单信息
 * @author huzeyu 2015-01-13
 *
 */
@Controller
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	private IJredis redis;
	/**
	 * 提交订单
	 * @param request
	 * @param payType
	 * @param deliveryType
	 * @param payPassword
	 * @return
	 */
	@RequestMapping(value = "commitOrder.htm")
	@ResponseBody
	public ResBo<ResOrder> commitOrder(HttpServletRequest request, 
			@ModelAttribute("buyer")Buyer buyer){
		ReqBo reqBo = new ReqBo(request);
	    User user = (User)SessionUtil.get("user");
	    buyer.setCreateUserId(user.getId());
	    buyer.setIsCommonUser(Boolean.TRUE);
	    User owner = user;
	    if(user.getMemberType().intValue() == 2) {
	    	owner = (User)SessionUtil.get(Constants.SESSION_ORDER_OWNER);
	    	buyer.setIsCommonUser(Constants.USER_TPYE_COMMON == owner.getMemberType().intValue());
	    	if(buyer.getDeliveryType() == DeliveryTypeEnum.DeliveryType_1.getType()){
		        buyer.setProvinceId(user.getProvince());
		        buyer.setCityId(user.getCity());
		        buyer.setDistrictId(user.getDistrict());
		        buyer.setCommunityId(user.getCommunity());
		        buyer.setPavilionId(user.getPavilionId());
	    	} else if (buyer.getDeliveryType() == DeliveryTypeEnum.DeliveryType_2.getType()) {
		        reqBo.setParam("userId", owner.getId());
		        reqBo.setParam("page", 1);
		        reqBo.setParam("rows", 3);
		        IUserAddressService us = (IUserAddressService)FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USERADDRESS, IUserAddressService.class);
		        ResBo<Pager<List<UserAddress>>> reqAddress = us.selectUserAddressListsByUserId(reqBo);
		        if((reqAddress.isSuccess()) && (reqAddress.getResult() != null)) {
		        	for (UserAddress address : reqAddress.getResult().getEntry()) {
			            if(address.getIsdefault().booleanValue()) {
			            	buyer.setAddressId(address.getId());
			            	buyer.setProvinceId(address.getProvince());
				            buyer.setCityId(address.getCity());
				            buyer.setDistrictId(address.getDistrict());
				            buyer.setCommunityId(address.getCommunity());
				            buyer.setPavilionId(address.getPavilionId());
				            buyer.setMobile(address.getMobile());
				            buyer.setReceiver(address.getReceiver());
				            buyer.setRemark(address.getAddressDetail());
				            break;
			            }
		          }
		      }
	      }
	    }
    	buyer.setUserId(owner.getId());

	    if(buyer.getPayType().intValue() == PayEnum.Balance.getId() || 
	    		buyer.getPayType().intValue() == PayEnum.Balance2.getId()){
	    	buyer.setBalance(true);
	    }

	    reqBo.setReqModel(buyer);
	    IOrderService os = (IOrderService)FindServiceUtil.findRemoteService(Constants.SERVICE_URL_ORDER, IOrderService.class);
	    return os.insertOrderCommit(reqBo);
	}
	
	/**
	 * 跳转到支付页面
	 * @param orderId
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "toPayment.htm")
	public void toPayment(HttpServletResponse response, 
			@RequestParam("orderNum")String orderNum, @RequestParam("payPrice")String payPrice) throws IOException{
		orderNum = Constants.ALIAPY_PREFIX_ORDER + orderNum;
		String title = new String(new String("订单支付").getBytes(), "iso-8859-1");
		Map<String, String> param = new HashMap<String, String>();
		param.put(Constants.ALIAPY_PARAM_TRADENO, orderNum);
		param.put(Constants.ALIAPY_PARAM_SUBJECT, title);
		param.put(Constants.ALIAPY_PARAM_TOTALFEE, payPrice);
		param.put(Constants.ALIAPY_PARAM_BODY, title);
//		param.put(Constants.ALIAPY_PARAM_RETURNURL, Constants.ALIAPY_PARAM_RETURNURL_VALUE);
//		param.put(Constants.ALIAPY_PARAM_RETURNURL, Constants.ALIAPY_PARAM_RETURNURL_VALUE);
		response.getWriter().print(HttpRequestUtil.sendHttpRequest(Constants.ALIAPY_URL, param, HttpRequestUtil.POST));
		response.setContentType("text/html;charset=UTF-8");
	}
	
	/**
	 * 跳转到支付结果页面
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "paymentResult.htm")
	public ModelAndView paymentResult(@RequestParam("orderId")String orderId, @RequestParam("orderNum")String orderNum){
		ModelAndView view = new ModelAndView("order/order_payment_result");
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		if(user.getMemberType() == Constants.USER_TPYE_COMMON){
			view.addObject("order_type", Constants.MENU_MY_ORDER);
		}else{
			view.addObject("order_type", Constants.MENU_DG_ORDER);
		}
		view.addObject("orderId", orderId);
		view.addObject("orderNum", orderNum);
		return view;
	}
	
	/**
	 * 获得亭子订单
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("getPavilionOrders.htm")
	public String getPavilionOrders(HttpServletRequest request, Model model){
		IOrderService os = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_ORDER, IOrderService.class);
		ReqBo reqBo = new ReqBo(request);
		Long userId = (Long)SessionUtil.get(Constants.SESSION_USER_ID);
		reqBo.setParam("userId", userId);
		reqBo.setParam("page", 1);
		reqBo.setParam("rows", Constants.ORDER_NUM_PAGE);	
		reqBo.setParam("timeType", null);
		reqBo.setParam("status", null);
		reqBo.setParam("state", null);
		reqBo.setParam("isPaid", null);
		reqBo.setParam("column", "createTime");
		reqBo.setParam("sort", Constants.ORDERBY_DESC);
		Pager<List<Map<Object, Object>>> pager = os.getPavilionOrders(reqBo).getResult();
		List<Map<Object,Object>> pavilionOrders = new ArrayList<Map<Object,Object>>();	
		pavilionOrders = pager.getEntry();
		model.addAttribute("pavilionOrders", pavilionOrders);
		model.addAttribute("page", reqBo.getParam("page"));
		model.addAttribute("total_page", (pager.getTotal()+Constants.ORDER_NUM_PAGE-1)/Constants.ORDER_NUM_PAGE);		
		model.addAttribute(Constants.MENU_ID, Constants.MENU_P_ORDER);
		return "order/pavilion_order";			
	}
	/**
	 * 通过手机号、状态、时间、订单类型查询亭子订单
	 * @param moible 订单编号/手机号
	 * @param status 订单状态
	 * @param timeType 时间
	 * @param orderType 代收或代购
	 */
	@RequestMapping("queryPavilionOrders.htm")
	public String queryPavilionOrders(HttpServletRequest request, Model model){
		IOrderService os = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_ORDER, IOrderService.class);
		ReqBo reqBo = new ReqBo(request);	
		Long userId = (Long)SessionUtil.get(Constants.SESSION_USER_ID);	
		reqBo.setParam("userId", userId);
		reqBo.setParam("isPaid", null);
		reqBo.setParam("state", null);
		if(reqBo.getParam("page") == null || !reqBo.getParam("page").toString().matches("[1-9][0-9]*")){
			reqBo.setParam("page", 1);
		}
		if(reqBo.getParam("timeType") != null && !reqBo.getParam("timeType").toString().matches("[0-9]+")){
			reqBo.setParam("timeType", null);
		}		
		if(reqBo.getParam("status") != null){
			if(reqBo.getParam("status").toString().equals("1")){
				reqBo.setParam("state", 1);
				reqBo.setParam("isPaid", reqBo.getParam("status"));
				reqBo.setParam("status", null);
			}else if(reqBo.getParam("status").toString().equals("2")){
				reqBo.setParam("state", reqBo.getParam("status"));
				reqBo.setParam("status", null);
			}else {
				List<Integer> status = new ArrayList<Integer>();
				if(reqBo.getParam("status").toString().equals("5")){
					reqBo.setParam("state", 1);
					reqBo.setParam("isPaid", "2");
					status.add(0, Integer.parseInt(request.getParameter("status")));
					reqBo.setParam("status", status);
				}else if(reqBo.getParam("status").toString().equals("3")){
					reqBo.setParam("state", 1);
					reqBo.setParam("isPaid", "2");
					status.add(0, 1);status.add(1, 2);status.add(1, 3);status.add(1, 4);					
					reqBo.setParam("status", status);
				}else{
					if(!reqBo.getParam("status").toString().matches("[0-9]+")){
						status.add(0, 0);
						reqBo.setParam("status", status);
					}
				}
			}			
		}
		reqBo.setParam("rows", Constants.ORDER_NUM_PAGE);		
		//判断是手机号还是订单
		int length = 0;
		if(reqBo.getParam("mobile") != null){
			length = request.getParameter("mobile").length();
			if(length > 0){
				if(length == 11){
					reqBo.setParam("mobile", reqBo.getParam("mobile"));
				}else{
					reqBo.setParam("orderNum", reqBo.getParam("mobile"));
					reqBo.setParam("mobile", null);
				}			
			}
		}				
		reqBo.setParam("column", "createTime");
		reqBo.setParam("sort", Constants.ORDERBY_DESC);
		Pager<List<Map<Object, Object>>> pager = new Pager<List<Map<Object, Object>>>();
		if(reqBo.getParamInt("orderType") != null){
			if(reqBo.getParamInt("orderType") == 1){
				pager = os.getCollectOrders(reqBo).getResult();
			}else if(reqBo.getParamInt("orderType") == 2){
				pager = os.selectPurchasAgentsOrders(reqBo).getResult();
			}else{
				pager = os.getPavilionOrders(reqBo).getResult();
			}
		}else{
			pager = os.getPavilionOrders(reqBo).getResult();
		}		
		List<Map<Object,Object>> pavilionOrders = new ArrayList<Map<Object,Object>>();	
		long total_page = (pager.getTotal()+Constants.ORDER_NUM_PAGE-1)/Constants.ORDER_NUM_PAGE;
		pavilionOrders = pager.getEntry();		
		model.addAttribute("pavilionOrders",pavilionOrders);
		Map<String,Object> query = new HashMap<String,Object>();
		query.put("mobile", reqBo.getParam("mobile"));
		query.put("length", length);
		query.put("orderType", reqBo.getParam("orderType"));
		query.put("status", request.getParameter("status"));
		query.put("time", reqBo.getParam("timeType"));
		model.addAttribute("page", reqBo.getParam("page"));
		model.addAttribute("total_page", total_page);
		model.addAttribute("query",query);	
		model.addAttribute(Constants.MENU_ID, Constants.MENU_P_ORDER);
		return "order/pavilion_order";
	}
	/**
	 * 获得用户订单
	 */
	@RequestMapping("getOrderList.htm")
	public String getOrderList(HttpServletRequest request, Model model){			
		IOrderService os = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_ORDER, IOrderService.class);
		ReqBo reqBo = new ReqBo(request);
		Long userId = (Long)SessionUtil.get(Constants.SESSION_USER_ID);
		reqBo.setParam("userId", userId);
		reqBo.setParam("page", 1);
		reqBo.setParam("rows", Constants.ORDER_NUM_PAGE);	
		reqBo.setParam("timeType", null);
		reqBo.setParam("status", null);
		reqBo.setParam("state", null);
		reqBo.setParam("isPaid", null);
		reqBo.setParam("column", "createTime");
		reqBo.setParam("sort", Constants.ORDERBY_DESC);
		List<Map<Object,Object>> orderList = new ArrayList<Map<Object,Object>>();
		Pager<List<Map<Object, Object>>> pager = os.selectOrderListsByUserId(reqBo).getResult();
		orderList = pager.getEntry();
		model.addAttribute("orderList", orderList);
		model.addAttribute("page", reqBo.getParam("page"));
		model.addAttribute("total_page", (pager.getTotal()+Constants.ORDER_NUM_PAGE-1)/Constants.ORDER_NUM_PAGE);
		model.addAttribute(Constants.MENU_ID, Constants.MENU_MY_ORDER);
		return "order/my_order_list";		
	}	
	/**
	 * 通过订单号、状态、时间查询用户订单
	 * @param orderNum 订单编号
	 * @param status 订单状态
	 * @param timeType 时间
	 */
	@RequestMapping("queryOrderList.htm")
	public String queryOrderList(HttpServletRequest request, Model model){
		IOrderService os = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_ORDER, IOrderService.class);
		ReqBo reqBo = new ReqBo(request);	
		Long userId = (Long)SessionUtil.get(Constants.SESSION_USER_ID);	
		reqBo.setParam("userId", userId);
		reqBo.setParam("isPaid", null);
		reqBo.setParam("state", null);
		if(reqBo.getParam("page") == null || !reqBo.getParam("page").toString().matches("[1-9][0-9]*")){
			reqBo.setParam("page", 1);
		}
		if(reqBo.getParam("timeType") != null && !reqBo.getParam("timeType").toString().matches("[0-9]+")){
			reqBo.setParam("timeType", null);
		}
		if(reqBo.getParam("status") != null){
			if(reqBo.getParam("status").toString().equals("1")){
				reqBo.setParam("state", 1);
				reqBo.setParam("isPaid", reqBo.getParam("status"));
				reqBo.setParam("status", null);
			}else if(reqBo.getParam("status").toString().equals("2")){
				reqBo.setParam("state", reqBo.getParam("status"));
				reqBo.setParam("status", null);
			}else {
				List<Integer> status = new ArrayList<Integer>();
				if(reqBo.getParam("status").toString().equals("5")){
					reqBo.setParam("state", 1);
					reqBo.setParam("isPaid", "2");
					status.add(0, Integer.parseInt(request.getParameter("status")));
					reqBo.setParam("status", status);
				}else if(reqBo.getParam("status").toString().equals("3")){
					reqBo.setParam("state", 1);
					reqBo.setParam("isPaid", "2");
					status.add(0, 1);status.add(1, 2);status.add(1, 3);status.add(1, 4);					
					reqBo.setParam("status", status);
				}else{
					if(!reqBo.getParam("status").toString().matches("[0-9]+")){
						status.add(0, 0);
						reqBo.setParam("status", status);
					}
				}
			}			
		}
		reqBo.setParam("rows", Constants.ORDER_NUM_PAGE);
		reqBo.setParam("column", "createTime");
		reqBo.setParam("sort", Constants.ORDERBY_DESC);
		Map<String,Object> query = new HashMap<String,Object>();
		query.put("status", request.getParameter("status"));
		query.put("time", reqBo.getParam("timeType"));			
		List<Map<Object,Object>> orderList = new ArrayList<Map<Object,Object>>();
		Pager<List<Map<Object, Object>>> pager = os.selectOrderListsByUserId(reqBo).getResult();
		long total_page = (pager.getTotal()+Constants.ORDER_NUM_PAGE-1)/Constants.ORDER_NUM_PAGE;		
		orderList = pager.getEntry();	
		model.addAttribute("orderList",orderList);	
		model.addAttribute("page", reqBo.getParam("page"));
		model.addAttribute("total_page", total_page);
		model.addAttribute("query",query);
		model.addAttribute(Constants.MENU_ID, Constants.MENU_MY_ORDER);
		return "order/my_order_list";
	}
	/**
	 * 查看我的订单（详情）
	 */
	@RequestMapping("orderDetail.htm")
	public String orderDetail(HttpServletRequest request, Model model){
		IOrderService os = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_ORDER, IOrderService.class);
		ReqBo reqBo = new ReqBo(request);
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		reqBo.setParam("userId", user.getId());		
		if(reqBo.getParam("id") == null || !reqBo.getParam("id").toString().matches("[0-9]+")){
			reqBo.setParam("id", 0);
		}
		Map<Object,Object> orderDetail = new HashMap<Object,Object>();	
		ResBo<Map<Object, Object>> orderInfo = os.getOrderInfo(reqBo);
		if(orderInfo.getResult() == null){
			return "redirect:/main/index.htm";
		}else{
			orderDetail = orderInfo.getResult();
			model.addAttribute("orderDetail",orderDetail);
			//获得亭子信息
			if(orderDetail.get("pavilionId") != null){
				IPavilionInfoService ps = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_PAVILION, IPavilionInfoService.class);
				ReqBo reqBoP = new ReqBo();
				reqBoP.setParam("id", orderDetail.get("pavilionId"));
				Map<Object, Object> pavilionInfo = ps.getPavilionInfoById(reqBoP).getResult();
				model.addAttribute("pavilionInfo",pavilionInfo);
			}
			DecimalFormat df = new DecimalFormat("0.00");
			String point = df.format(((BigDecimal)orderDetail.get("amount")).multiply(new BigDecimal("0.05"))); 				
			model.addAttribute("point", point);
			model.addAttribute("memberType", user.getMemberType());
			model.addAttribute(Constants.MENU_ID, Constants.MENU_MY_ORDER);					
			return "order/my_order_detail";
		}		
	}
	
	/**
	 * 根据手机号验证用户是否存在，若存在放入缓存
	 * @param request
	 * @param mobile
	 * @return
	 */
	@RequestMapping({"getUsrByPhone.htm"})
    @ResponseBody
    public ResBo<User> getUsrByPhone(HttpServletRequest request, @RequestParam("mobile") String mobile){
	    ReqBo reqBo = new ReqBo(request);
	    reqBo.setParam("mobile", mobile);
	    IUserService us = (IUserService)FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USER, IUserService.class);
	    ResBo<User> resBo = us.getUserBymobile(reqBo);
	    if(resBo.getResult() != null){
	    	SessionUtil.replace(Constants.SESSION_ORDER_OWNER, resBo.getResult());
	    }
	    return resBo;
	}
	
	/**
	 * 查看亭子订单（详情）
	 */
	@RequestMapping("pavilionOrderInfo.htm")
	public String pavilionOrderInfo(HttpServletRequest request, Model model){
		IOrderService os = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_ORDER, IOrderService.class);
		ReqBo reqBo = new ReqBo(request);
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);		
		if(reqBo.getParam("id") == null || !reqBo.getParam("id").toString().matches("[0-9]+")){
			reqBo.setParam("id", 0);
		}
		reqBo.setParam("pavilionId", user.getPavilionId());
		Map<Object,Object> orderDetail = new HashMap<Object,Object>();	
		ResBo<Map<Object, Object>> orderInfo = os.getPavilionOrderInfo(reqBo);
		if(orderInfo.getResult() == null){
			return "redirect:/main/index.htm";
		}else{
			orderDetail = orderInfo.getResult();
			model.addAttribute("orderDetail",orderDetail);
			//获得亭子信息
			IPavilionInfoService ps = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_PAVILION, IPavilionInfoService.class);
			ReqBo reqBoP = new ReqBo();
			reqBoP.setParam("id", orderDetail.get("pavilionId"));
			Map<Object, Object> pavilionInfo = ps.getPavilionInfoById(reqBoP).getResult();
			model.addAttribute("pavilionInfo",pavilionInfo);		
			DecimalFormat df = new DecimalFormat("0.00");
			String point = df.format(((BigDecimal)orderDetail.get("amount")).multiply(new BigDecimal("0.05"))); 				
			model.addAttribute("point", point);		
			model.addAttribute("memberType", user.getMemberType());
			model.addAttribute(Constants.MENU_ID, Constants.MENU_P_ORDER);			
			return "order/my_order_detail";
		}		
	}
	/**
	 * 确认收货
	 * @param id 订单id
	 */
	@RequestMapping(value="confirmReceipt.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> confirmReceipt(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		ReqBo reqBo = new ReqBo(request);
		IOrderService os = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_ORDER, IOrderService.class);
		Boolean bn = os.confirmOrder(reqBo).getResult();
		if(bn){
			result.put("status", OrderStatusEnum.status_5.getStatus());
			result.put("content", "已完成");
		}else{
			result.put("status", 0);
		}
		return result;
	}
	/**
	 * 确认收货(订单详情单个收货)
	 * @param id 详情id
	 */
	@RequestMapping(value="confirmOrderDetail.htm", method = RequestMethod.POST)
	@ResponseBody
	public String confirmOrderDetail(HttpServletRequest request){
		String result = "0";
		ReqBo reqBo = new ReqBo(request);
		IOrderService os = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_ORDER, IOrderService.class);
		Boolean bn = os.confirmOrderDetail(reqBo).getResult();
		if(bn){
			result = "1";
		}
		return result;
	}
	/**
	 * 取消订单
	 * @param orderId 订单id
	 */
	@RequestMapping(value="cancleOrder.htm", method = RequestMethod.POST)
	@ResponseBody
	public String cancleOrder(HttpServletRequest request){
		String result = "0";
		ReqBo reqBo = new ReqBo(request);
		Long userId = (Long)SessionUtil.get(Constants.SESSION_USER_ID);
		reqBo.setParam("userId", userId);
		IOrderService os = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_ORDER, IOrderService.class);
		ResBo<Boolean> resBo = os.cancelOrder(reqBo);		
		if(resBo.getErrCode() == 40){
			result = "订单不存在";
		}else if(resBo.getResult()){
			result = "1";
		}
		return result;
	}
	
	/**
	 * 导出订单
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="exportOrder.htm")
	@ResponseBody
	public void exportOrder(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Workbook workbook = new XSSFWorkbook();
		this.createSheet(workbook, "ds");
		this.createSheet(workbook, "dg");
		response.setHeader("Content-Type", "application/force-download");
		response.setHeader("Content-Type", "application/vnd.ms-excel;charset=utf8");
		response.setHeader("Content-disposition", "attachment; filename=exportOrder"
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx");
		workbook.write(response.getOutputStream());
		response.getOutputStream().flush();
	}
	
	@SuppressWarnings("unchecked")
	private void createSheet(Workbook wb, String orderType){
		Sheet sheet = null;  
		if(orderType.equals("ds")){
			sheet = wb.createSheet("代收订单");
		}else{
			sheet = wb.createSheet("代购订单");
		}
		//表格头信息
        Row header = sheet.createRow((int)0);
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        createCell(header, (short)0, style, "订单号");
        createCell(header, (short)1, style, "下单时间");
        createCell(header, (short)2, style, "商品名称");
        createCell(header, (short)3, style, "姓名");
        createCell(header, (short)4, style, "订单状态");
        createCell(header, (short)5, style, "订单金额");

        ReqBo reqBo = new ReqBo();
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		reqBo.setParam("pavilionId", user.getPavilionId());
		reqBo.setParam("userId", user.getId());
		IOrderService os = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_ORDER, IOrderService.class);
		ResBo<List<Map<Object, Object>>> orderRes = null;
		if(orderType.equals("ds")){
			orderRes = os.getTodayDsOrders(reqBo);
		}else{
			orderRes = os.getTodayDgOrders(reqBo);
		}
        List<Map<Object, Object>> orders = orderRes.getResult();
        if(orders != null && orders.size() > 0) {
            Map<Long, Map<Object, Object>> orderIdMaps = new HashMap<Long, Map<Object, Object>>();
	        for(Map<Object, Object> order : orders){
	        	orderIdMaps.put((Long)order.get("id"), order);
	        }
	        reqBo.setReqModel(new ArrayList<Long>(orderIdMaps.keySet()));
	        ResBo<List<Map<Object, Object>>> orderDetailRes = os.selectOrderDetailByOrderIds(reqBo);
	        List<Map<Object, Object>> orderDetails = orderDetailRes.getResult();
	        if(orderDetails != null && orderDetails.size() > 0){
		        for(Map<Object, Object> orderDetail : orderDetails){
		        	Long orderId = (Long)orderDetail.get("orderId");
		        	if(orderIdMaps.containsKey(orderId)){
		        		Map<Object, Object> order = orderIdMaps.get(orderId);
		        		if(order.get("details") == null){
		        			List<Map<Object, Object>> details = new ArrayList<Map<Object, Object>>();
		        			details.add(orderDetail);
			        		order.put("details", details);
		        		}else{
		        			((List<Map<Object, Object>>)order.get("details")).add(orderDetail);
		        		}
		        	}
		        }
	        }
	        int col = 1;
	        for(Map<Object, Object> order : orders){
	        	Row row = sheet.createRow(col++);
	        	createCell(row, (short)0, null, (String)order.get("orderNum"));
	        	createCell(row, (short)1, null, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.get("createTime")));
	        	int index = 0;
	        	for(Map<Object, Object> detail : (List<Map<Object, Object>>)order.get("details")){
	        		if(index == 0){
	        			createCell(row, (short)2, null, (String)detail.get("goodsName"));
	        		}else{
		        		Row newRow = sheet.createRow(col++);
		        		createCell(newRow, (short)0, null, "");
			        	createCell(newRow, (short)1, null, "");
		        		createCell(newRow, (short)2, null, (String)detail.get("goodsName"));
		        		createCell(newRow, (short)3, null, "");
			        	createCell(newRow, (short)4, null, "");
			        	createCell(newRow, (short)5, null, "");
	        		}
	        		index++;
	        	}
	        	createCell(row, (short)3, null, (String)order.get("receiver"));
	        	//订单状态
			   	String status = " ";
			   	if(((Integer)order.get("isPaid")).intValue() == 1){
			   		status = "未付款";
			   	}else{
			   		status = "已付款";
			   	}
			   	switch(((Integer)order.get("status")).intValue()){
			   		case 4: status = "已发货"; break;
			   		case 5: status = "已完成"; break;
			   		case 6: status = "退货中"; break;
			   		case 7: status = "退货完成"; break;
			   	}
			   	if(((Integer)order.get("state")).intValue() == 2){
			   		status = "已取消";
			   	}
	        	createCell(row, (short)4, null, status);
	        	createCell(row, (short)5, null, String.valueOf(order.get("orderPrice")));
	        }
        }
	}

	private void createCell(Row row, short col, CellStyle style, String value) {
		Cell cell = row.createCell(col);
        cell.setCellValue(value);
        cell.setCellStyle(style);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	}
	
	/**
	 * 根据订单详情id获取退换货订单信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("getReturnOrderInfo.htm")
	public String getReturnOrderInfo(HttpServletRequest request, Model model){
		ReqBo reqBo = new ReqBo(request);
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		this.redis.setValue(request.getSession().getId(), request.getSession().getId(), 1800000);
		if(user.getMemberType() == 2){
			Long createUserId = user.getId();
			reqBo.setParam("createUserId", createUserId);
		}else{
			Long userId = user.getId();
			reqBo.setParam("userId", userId);
		}
		IOrderService os = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_ORDER, IOrderService.class);
		ResBo<Map<Object, Object>> resBo = os.getReturnOrderInfo(reqBo);
		model.addAttribute("orderInfo", resBo.getResult());
		model.addAttribute("sessionid", request.getSession().getId());
		return "order/goods_refund";
	}
	
	/**
	 * 申请退货
	 * @param orderRefund
	 * @param request
	 * @return isSuccess:ture result:1   isSuccess:false result:其他
	 */
	@RequestMapping("saveReturnGoods.htm")
	@ResponseBody
	public ResBo<Integer> saveReturnGoods(@ModelAttribute OrderRefund orderRefund, HttpServletRequest request){
		ReqBo reqBo = new ReqBo(request);
		OrderRefund or = orderRefund;
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		if(user.getAccountType() == 2){
			or.setOrigin(2);
		}else{
			or.setOrigin(1);
		}
		IOrderService os = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_ORDER, IOrderService.class);
		ResBo<Map<Object, Object>> resBo  = os.getOrderInfoByOrderDetailId(reqBo);
		Map<Object, Object> map = resBo.getResult();
		ResBo<Integer> resBo2 = null;
		if(map == null){
			return null;
		}else{
			if(map.get("account") != null){
				or.setAccount(map.get("account").toString());
			}
			or.setOrderId(Long.parseLong(map.get("id").toString()));
			or.setRefundPrice(new BigDecimal(map.get("effPay").toString()).multiply(new BigDecimal(map.get("number").toString())));
			or.setState(1);
			reqBo.setReqModel(or);
			resBo2 = os.saveReturnGoods(reqBo);
		}		
		return resBo2;
	}
	
	/**
	 * 退换货取消申请
	 * @param request
	 * @return
	 */
	@RequestMapping("cancleReturnGoods.htm")
	@ResponseBody
	public ResBo<Integer> cancleReturnGoods(HttpServletRequest request){
		ReqBo reqBo = new ReqBo(request);
		IOrderService os = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_ORDER, IOrderService.class);
		reqBo.setParam("state", 127);
		reqBo.setParam("isRefund", 0);
		ResBo<Integer> resBo = os.updateReturnGoodsByODId(reqBo);		
		return resBo;
	}
	
	/**
	 * 删除退换货图片
	 * @param request
	 * @return
	 */
	@RequestMapping("deleteImg.htm")
	@ResponseBody
	public ResBo<Object> deleteReturnGoodsImg(HttpServletRequest request){
		ReqBo reqBo = new ReqBo(request);
		String token = reqBo.getParamStr("token");		// 发送到的手机号
		String uri = reqBo.getParamStr("uri");
		StringBuilder sb = new StringBuilder();
		sb.append("token=").append(token).append("&uri=").append(uri);
		String strRes = new String();
		strRes = HttpSend.postSend(Constants.IMGHOSTDEL, sb.toString());
		ResBo<Object> rsb = new ResBo<Object>(); 
		rsb.setResult(strRes);	
		return rsb;
	}
	
	
}
