package net.sls.controller.order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sls.businessconstant.BusinessContant;
import net.sls.dto.ext.order.CommitOrder;
import net.sls.dto.order.OrderLog;
import net.sls.dto.order.OrderRefund;
import net.sls.dto.order.Orders;
import net.sls.service.order.IOrderDetailService;
import net.sls.service.order.IOrderService;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import util.model.ComboxDto;
import framework.exception.BusinessException;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * 
 * 后台订单管理功能
 * 
 * */
@Controller
@RequestMapping("order")
public class OrderManageController {
	
	@InitBinder
	protected void parseDate(HttpServletRequest request, ServletRequestDataBinder binder){
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	}
	
	@RequestMapping("search.htm")
	public String initmemberSearch(){
		return "orderManage/searchOrders";
	}
	/**
	 * 订单查询
	 * @param request
	 * @return
	 */
	@RequestMapping(value="searchOrders.htm")
	@ResponseBody
	public ResBo<Pager<List<Map<Object,Object>>>> getOrders(HttpServletRequest request,@RequestParam(value="startDate",required=false) Date startDate,@RequestParam(value="endDate",required=false) Date endDate){   
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("startDate", startDate);
		reqBo.setParam("endDate", endDate);
		reqBo.setParam(BusinessContant.OPERAREAID, SessionUtil.get(BusinessContant.OPERAREAID));
		IOrderService os = FindServiceUtil.findService(IOrderService.class);
		return os.selectOrderList(reqBo);
	}
	
	@RequestMapping("excel.htm")
	public void excel(HttpServletRequest request,HttpServletResponse res,@RequestParam(value="startDate",required=false) Date startDate,@RequestParam(value="endDate",required=false) Date endDate) throws Exception{
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("startDate", startDate);
		reqBo.setParam("endDate", endDate);
		reqBo.setParam(BusinessContant.OPERAREAID, SessionUtil.get(BusinessContant.OPERAREAID));
		IOrderService os = FindServiceUtil.findService(IOrderService.class);
		ResBo<Workbook> resBo = os.exportExcel(reqBo);
		if(resBo.isSuccess()){
			res.setHeader("Content-Type","application/force-download");   
	        res.setHeader("Content-Type","application/vnd.ms-excel");   
	        res.setHeader("Content-disposition", "attachment; filename="+UUID.randomUUID().toString()+".xlsx");
			Workbook book = resBo.getResult();
			book.write(res.getOutputStream());
			res.getOutputStream().flush();
		}else{
			throw new BusinessException(18L);
		}
	}
	
	
	@RequestMapping("initorderdetail.htm")
	public String initOrderDetailSearch(@RequestParam("orderId") long orderId,Model model){
		IOrderService os = FindServiceUtil.findService(IOrderService.class);
		model.addAttribute("orderId", orderId);
		model.addAllAttributes(os.selectOrderDetail(new ReqBo().setParam("orderId", orderId)).getResult());
		return "orderManage/ordersDetials";
	}
	/**
	 * 订单详情查询
	 * @param request
	 * @return
	 */
	@RequestMapping("orderdetail.htm")
	@ResponseBody
	public ResBo<Pager<List<Map<String,Object>>>> getOrderDetails(HttpServletRequest request){   
		ReqBo reqBo = new ReqBo(request);
		IOrderDetailService ods = FindServiceUtil.findService(IOrderDetailService.class);
		return ods.selectOrderDetailList(reqBo);
	}
	
	/**
	 * 查询订单操作日志
	 * */
	@RequestMapping("orderlog.htm")
	@ResponseBody
	public ResBo<Pager<List<OrderLog>>> getOrderLog(@RequestParam("orderId") long orderId){
		IOrderService os = FindServiceUtil.findService(IOrderService.class);
		return os.selectOrderLog(new ReqBo().setParam("orderId", orderId));
	}
	
	/**
	 * 修改订单状态
	 * */
	@RequestMapping("updatestatus.htm")
	@ResponseBody
	public ResBo<?> updateOrderStatus(@RequestParam("id") long orderId,@RequestParam("state") int state,@RequestParam("status") int status){
		Orders order = new Orders();
		order.setId(orderId);
		order.setState(state);
		order.setStatus(status);
		IOrderService os = FindServiceUtil.findService(IOrderService.class);
		return os.updateOrderStatus(new ReqBo().setReqModel(order));
	}
	
	/**
	 * 修改订单详情
	 * */
	@RequestMapping("updateOrderAddress.htm")
	@ResponseBody
	public ResBo<?> updateOrderAddress(HttpServletRequest request){
		ReqBo reqBo = new ReqBo(request);
		IOrderService os = FindServiceUtil.findService(IOrderService.class);
		ResBo<?> resBo = os.updateDeliveryAddress(reqBo);
		return resBo;
	}
	
	/**
	 * 订单详情里支付功能
	 * @param request
	 * @return
	 */
	@RequestMapping("payop.htm")
	@ResponseBody
	public ResBo<?> payOrderInfo(@RequestParam("orderId") long orderId){   
		Orders order = new Orders();
		order.setId(orderId);
		order.setIsPaid(BusinessContant.ORDERISPAID_2);
		IOrderService os = FindServiceUtil.findService(IOrderService.class);
		return os.updateOrderPaidStatus(new ReqBo().setReqModel(order));
	}
	
	@RequestMapping("initexport.htm")
	public String exportOrders(){
		return "orderManage/export";
	}
	
	@RequestMapping("pavilion.htm")
	@ResponseBody
	public ResBo<List<ComboxDto>> getPavilionCombox(@RequestParam(value="q",required=false) String name){
		IOrderService os = FindServiceUtil.findService(IOrderService.class);
		return os.selectPavilionCombox(new ReqBo().setParam("name", name).setParam("areaCode", SessionUtil.get(BusinessContant.OPERAREAID)));
	}
	
	@RequestMapping("actinfo.htm")
	@ResponseBody
	public ResBo<List<Map<String,Object>>> selectOrderGoodsActInfo(@RequestParam("orderId")long orderId){
		IOrderService os = FindServiceUtil.findService(IOrderService.class);
		return os.selectOrderActGoodsInfo(new ReqBo().setParam("orderId", orderId));
	}
	
	@RequestMapping("backorder.htm")
	public String backorder(){
		return "orderManage/backorder";
	}
	
	@RequestMapping("info.htm")
	@ResponseBody
	public ResBo<?> orderInfoByOrderNum(@RequestParam("orderNum") String orderNum){
		IOrderService os = FindServiceUtil.findService(IOrderService.class);
		return os.orderInfoByOrderNum(new ReqBo().setParam("orderNum", orderNum));
	}
	
	@RequestMapping(value="commit.htm")
	@ResponseBody
	public ResBo<?> commitOrder(@RequestBody CommitOrder co){
		IOrderService os = FindServiceUtil.findService(IOrderService.class);
		return os.commitOrder(new ReqBo(co));
	}
	
	@RequestMapping("refund.htm")
	public String refund(){
		return "orderManage/orderRefund";
	}
	
	@RequestMapping("refunds.htm")
	@ResponseBody
	public ResBo<Pager<List<Map<String,Object>>>> refunds(HttpServletRequest req){
		IOrderService os = FindServiceUtil.findService(IOrderService.class);
		return os.refunds(new ReqBo(req));
	}
	
	@RequestMapping("updateRefund.htm")
	@ResponseBody
	public ResBo<?> updateRefund(@ModelAttribute OrderRefund ore){
		IOrderService os = FindServiceUtil.findService(IOrderService.class);
		return os.updateRefund(new ReqBo().setReqModel(ore));
	}
	
	@RequestMapping("completeRefund.htm")
	@ResponseBody
	public ResBo<?> completeRefund(HttpServletRequest req){
		IOrderService os = FindServiceUtil.findService(IOrderService.class);
		return os.completeRefund(new ReqBo(req));
	}
	
	@RequestMapping("refundexcel.htm")
	public void refundexcel(HttpServletRequest req,HttpServletResponse res)throws Exception{
		ReqBo reqBo = new ReqBo(req);
		IOrderService os = FindServiceUtil.findService(IOrderService.class);
		ResBo<Workbook> resBo = os.refundExcel(reqBo);
		if(resBo.isSuccess()){
			res.setHeader("Content-Type","application/force-download");   
	        res.setHeader("Content-Type","application/vnd.ms-excel");   
	        res.setHeader("Content-disposition", "attachment; filename=excel.xlsx");
			Workbook book = resBo.getResult();
			book.write(res.getOutputStream());
			res.getOutputStream().flush();
		}else{
			throw new BusinessException(18L);
		}
	}
}
