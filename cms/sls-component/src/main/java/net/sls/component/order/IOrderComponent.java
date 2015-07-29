package net.sls.component.order;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sls.dto.ext.order.ExportOrderQueryDto;
import net.sls.dto.order.DeliveryAddress;
import net.sls.dto.order.OrderDetail;
import net.sls.dto.order.OrderLog;
import net.sls.dto.order.OrderPay;
import net.sls.dto.order.OrderRefund;
import net.sls.dto.order.Orders;
import util.model.ComboxDto;
import framework.web.Pager;

public interface IOrderComponent {
	/**
	 * 
	 * 根据条件查询会员用户订单信息列表
	 * */
	public Pager<List<Map<Object, Object>>> selectOrderList(String code,String userName,
			String mobile, String orderNum, Integer isPaid, Integer state,
			Integer status, Integer pavilionId, Date startDate, Date endDate,Integer isPaviOrder,Integer isMobile,
			Integer type, Integer start, Integer number);

	public List<ExportOrderQueryDto> selectExportExcel(String code,String userName,
			String mobile, String orderNum, Integer isPaid, Integer state,
			Integer status, Integer pavilionId, Date startDate, Date endDate,Integer isPaviOrder,Integer isMobile,
			Integer type,Integer hasDetail);

	/**
	 * 
	 * 根据订单Id条件用户订单信息列表
	 * 
	 * @return
	 * */
	public Orders selectOrderInfo(Long id);

	/**
	 * 更新订单状态
	 * 
	 * @param paramLong
	 * @return
	 */
	public int updateOrderStatus(Orders order);

	/**
	 * 记录订单状态更新日志
	 * 
	 * @param paramLong
	 * @return
	 */
	public OrderLog insertOrdersLog(OrderLog orderLog);

	public Pager<List<OrderLog>> selectOrderLog(long orderId);
	
	public List<ComboxDto> selectPavilionComboxDto(String name,String areaCode);
	
	public List<Map<String,Object>> selectOrderActGoodsInfo(long orderId);

	/**
	 * 更新订单详情
	 * 
	 * @param DeliveryAddress
	 * @return
	 */
	public int updateDeliveryAddress(DeliveryAddress deliveryAddress);
	
	
	/**
	 * 获得订单使用的用户红包
	 * */
	public Map<String,Object> selectOrderCoupon(long orderId);
	
	public Map<String,Object> selectOrderInfoByOrderNum(String orderNum);
	
	public Orders insertOrderInfo(Orders orders,List<OrderDetail> ods,DeliveryAddress da,OrderPay op);
	
	public Pager<List<Map<String,Object>>> selectRefunds(String orderNum,Integer state,Integer type,Date startDate,Date endDate,int start,int number);
	
	public void updateRefund(OrderRefund ore);
	
	public void updateCompleteRefund(long refundId,int type);
	
	public List<Map<String,Object>> selectRefundExcel(String orderNum,Integer state,Integer type,Date startDate,Date endDate);
}
