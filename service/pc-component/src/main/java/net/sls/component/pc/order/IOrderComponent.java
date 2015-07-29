package net.sls.component.pc.order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import net.sls.dto.pc.order.DeliveryAddress;
import net.sls.dto.pc.order.OrderBeanDto;
import net.sls.dto.pc.order.OrderDetail;
import net.sls.dto.pc.order.OrderLog;
import net.sls.dto.pc.order.OrderPay;
import net.sls.dto.pc.order.Orders;
import util.model.OrderStateEnum;
import util.model.OrderStatusEnum;
import framework.web.Pager;

public interface IOrderComponent {
	/**@author chenlianjie
	 * 我的订单
	 * @param sort 
	 * @param column 
	 * @param userId
	 * @param orderNum
	 * @param status
	 * @param isPaid
	 * @param start
	 * @param number
	 * @return
	 */
	Pager<List<Map<Object, Object>>> selectOrderListsByUserId(Integer state,String sort, String column, Long userId,
			String orderNum, Integer timeType,List<Integer> statusList, Integer isPaid, Integer start, Integer number);

	int insertOrder(Orders order,OrderDetail orderDetail,OrderPay orderPay,DeliveryAddress deliveryAddress);
	/**@author chenlianjie
	 * 代购订单
	 * @param userId
	 * @param orderNum 
	 * @param orderNum
	 * * @param mobile
	 * @param status
	 * @param isPaid
	 * @param start
	 * @param number
	 * @return
	 */
	Pager<List<Map<Object, Object>>> selectPurchasAgentsOrders(Integer state,String sort, String column,Long userId,
			 String mobile, String orderNum, Integer timeType,List<Integer> statuslist, Integer isPaid, Integer start, Integer number);
	/**
	 * 亭子订单
	 * @param state
	 * @param sort
	 * @param column
	 * @param userId
	 * @param mobile
	 * @param orderNum
	 * @param timeType
	 * @param statuslist
	 * @param isPaid
	 * @param start
	 * @param number
	 * @return
	 */
	Pager<List<Map<Object, Object>>> selectPavilionOrders(Integer state,String sort, String column,Long userId,
			 String mobile, String orderNum, Integer timeType,List<Integer> statuslist, Integer isPaid, Integer start, Integer number);
	/**@author chenlianjie
	 * 代收订单
	 * @param userId
	 * @param orderNum 
	 * @param orderNum
	 * * @param mobile
	 * @param status
	 * @param isPaid
	 * @param start
	 * @param number
	 * @return
	 */
	Pager<List<Map<Object, Object>>> getCollectOrders(Integer state,String sort, String column,Long userId,
			 String mobile, String orderNum, Integer timeType,List<Integer> statuslist, Integer isPaid, Integer start, Integer number);

	/**
	 * 亭子订单
	 */
	/**
	 * @param order
	 * @param settle
	 * @param orderPay
	 * @param productDtoList
	 * @return SettlementsDto
	 * @date 2015年1月9日 下午5:35:46
	 */
	void insertOrder(OrderBeanDto dto);
	
	void insertOrderLog(OrderLog log);
	/**
	 * 查看我的订单订单详情
	 * @param paramLong
	 * @param paramLong2
	 * @return
	 */
	Map<Object, Object> selectOrderInfo(Long userId, Long id);
	/**
	 * 亭子订单查看订单
	 * @param pavlilionId
	 * @param id
	 * @return
	 */
	Map<Object, Object> getPavilionOrderInfo(Long id);
	/**
	 * 待付款
	 * @param userId
	 * @return
	 */
	Integer selectOrderByIsPaid1(Long userId);
	/**
	 * 待确认收货
	 * @param userId
	 * @return
	 */
	Integer selectOrderByStatus4(Long userId);
	
	/**
	 * 修改订单state
	 * */
	boolean updateOrderState(long orderId,OrderStateEnum e);
	/**
	 * 取消订单
	 * */
	boolean deleteOrder(long orderId,long userId);

	/**
	 * 修改订单状态
	 * */
	boolean updateOrderStatus(long orderId,OrderStatusEnum e);

	Orders selectOrdersByOrderNum(String orderNum);
	
	boolean updateConfirmPaid(long orderId,BigDecimal price);
	
	Orders selectOrdersById(long orderId);

	Map<Object, Object> selectCollectOrderInfo(Long userId, Long id,
			Integer pavilionId);

	Map<Object, Object> selectPurchasAgentsOrderInfo(Long userId, Long id,
			Integer pavilionId);

	/**
	 * 得到今日的代收订单(昨天19点以后到当前时间)
	 * @param pavilionId
	 * @return
	 */
	List<Map<Object, Object>> getTodayDsOrders(Integer pavilionId);

	/**
	 * 得到今日的代购订单(昨天19点以后到当前时间)
	 * @param pavilionId
	 * @return
	 */
	List<Map<Object, Object>> getTodayDgOrders(long userId);
	
	/**
	 * 查询亭子用户今日消费总额(余额)
	 * @param userId
	 * @return
	 */
	BigDecimal selectTodayTotalByUserId(Long userId);
	
	/**
	 * alipay支付成功添加用户账户
	 * @param account
	 * @param orderId
	 * @return
	 */
	int updateOrderPayByOrderId(String account, Long orderId);
}
