package net.sls.dao.pc.order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import net.sls.dto.pc.order.Orders;

import org.apache.ibatis.annotations.Param;

public interface PCOrdersMapper {

	long countOrdersByFilter(@Param("state") Integer state,@Param("userId") Long userId,@Param("orderNum") String orderNum,@Param("timeType") Integer timeType,@Param("status") List<Integer> status,@Param("isPaid") Integer isPaid);

	List<Map<Object, Object>> selectOrdersListsByUserId(@Param("state") Integer state,@Param("sort") String sort,@Param("column") String column,@Param("userId") Long userId,@Param("orderNum") String orderNum ,@Param("timeType") Integer timeType,@Param("status") List<Integer> status,@Param("isPaid") Integer isPaid,@Param("start") Integer start,@Param("number") Integer number);

	/**@author wangshaohui
     * @Description: TODO 插入定单
     * @param record
     * @return int 返回插入生成的定单id
     * @date 2015年1月5日 下午7:11:50
     */
    int insert(Orders record);
    /**
     * 代购查询
     * @param userId
     * @param orderNum
     * @param mobile
     * @param timeType
     * @param status
     * @param isPaid
     * @return
     */
	List<Map<Object, Object>> selectPurchasAgentsOrders(@Param("state") Integer state,@Param("sort") String sort,@Param("column") String column,@Param("pavilionId") long pavilionId,
			@Param("mobile") String mobile,@Param("orderNum") String orderNum,@Param("timeType") Integer timeType,@Param("status") List<Integer> status,
			@Param("isPaid") Integer isPaid,@Param("start") Integer start,@Param("number") Integer number);
	/**
	 * 亭子订单
	 * @param state
	 * @param sort
	 * @param column
	 * @param pavilionId
	 * @param mobile
	 * @param orderNum
	 * @param timeType
	 * @param status
	 * @param isPaid
	 * @param start
	 * @param number
	 * @return 
	 */
	List<Map<Object, Object>> selectPavilionOrders(@Param("state") Integer state,@Param("sort") String sort,@Param("column") String column,@Param("userId") long userId,@Param("pavilionId") Integer pavilionId,
			@Param("mobile") String mobile,@Param("orderNum") String orderNum,@Param("timeType") Integer timeType,@Param("status") List<Integer> status,
			@Param("isPaid") Integer isPaid,@Param("start") Integer start,@Param("number") Integer number);
	/**
     * 代收查询
     * @param userId
     * @param orderNum
     * @param mobile
	 * @param orderNum 
     * @param timeType
     * @param status
     * @param isPaid
     * @return
     */
	List<Map<Object, Object>> selectCollectOrders(@Param("state") Integer state,@Param("sort") String sort,@Param("column") String column,@Param("pavilionId") Integer pavilionId,
			@Param("mobile") String mobile,@Param("orderNum") String orderNum, @Param("timeType") Integer timeType,@Param("status") List<Integer> status,
			@Param("isPaid") Integer isPaid,@Param("start") Integer start,@Param("number") Integer number);

	long countPurchasAgentsOrders(@Param("state") Integer state,@Param("pavilionId") long pavilionId,
			@Param("mobile") String mobile,@Param("orderNum") String orderNum, @Param("timeType") Integer timeType,@Param("status") List<Integer> status,
			@Param("isPaid") Integer isPaid);

	long countCollectOrders(@Param("state") Integer state,@Param("pavilionId") Integer pavilionId,
			@Param("mobile") String mobile,@Param("orderNum") String orderNum, @Param("timeType") Integer timeType,@Param("status") List<Integer> status,
			@Param("isPaid") Integer isPaid);
	/**
	 * 亭子订单数量
	 * @param state
	 * @param pavilionId
	 * @param mobile
	 * @param orderNum
	 * @param timeType
	 * @param status
	 * @param isPaid
	 * @return
	 */
	long countPavilionOrders(@Param("state") Integer state,@Param("userId") long userId,@Param("pavilionId") Integer pavilionId,
			@Param("mobile") String mobile,@Param("orderNum") String orderNum, @Param("timeType") Integer timeType,@Param("status") List<Integer> status,
			@Param("isPaid") Integer isPaid);
	/**
	 * 我的订单查看订单
	 * @param userId
	 * @param id
	 * @return
	 */
	Map<Object, Object> selectOrderInfo(@Param("userId") Long userId,@Param("id") Long id);
	/**
	 * 亭子订单查看订单
	 * @param pavilionId
	 * @param id
	 * @return
	 */
	Map<Object, Object> getPavilionOrderInfo(@Param("id") Long id);
	
	/**
	 * 待付款统计
	 * @param userId
	 * @return
	 */
	Integer selectOrderByIsPaid1(@Param("userId") Long userId);
	/**
	 * 待确认收货统计
	 * @param userId
	 * @return
	 */
	Integer selectOrderByStatus4(@Param("userId") Long userId);
	
	/**
	 * 修改订单状态
	 * */
	int updateOrderState(@Param("orderId")long orderId,@Param("state")Integer state);
	
	int cancelOrder(long orderId);
	
	int updateOrderStatus(@Param("orderId")long orderId,@Param("status")Integer status);
	/**
	 * 根据订单号获取订单
	 * @param orderNum
	 * @return
	 */
	Orders selectOrdersByOrderNum(@Param("orderNum") String orderNum);
	
	/**
	 * 确认付款
	 * */
	int updateConfirmPaid(@Param("orderId")long orderId,@Param("price")BigDecimal price);
	
	Orders selectOrdersByOrderId(long orderId);
	/**
	 * 代收订单里查看订单
	 * @param userId
	 * @param id
	 * @param pavilionId
	 * @return
	 */
	Map<Object, Object> selectCollectOrderInfo(@Param("userId") Long userId,@Param("id") Long id,
			@Param("pavilionId") Integer pavilionId);
	/**
	 * 代购订单里查看订单
	 * @param userId
	 * @param id
	 * @param pavilionId
	 * @return
	 */
	Map<Object, Object> selectPurchasAgentsOrderInfo(@Param("userId") Long userId,@Param("id") Long id,
			@Param("pavilionId") Integer pavilionId);

	/**
	 * 得到当日代收订单(昨天19点以后到当前时间)
	 * @param pavilionId
	 * @return
	 */
	List<Map<Object, Object>> getTodayDsOrders(@Param("pavilionId") Integer pavilionId);

	/**
	 * 得到当日代购订单(昨天19点以后到当前时间)
	 * @param pavilionId
	 * @return
	 */
	List<Map<Object, Object>> getTodayDgOrders(@Param("userId") long userId);
	
	/**
	 * 查询今日支出（只统计余额的支出） 
	 * @param userId
	 * @return
	 */
	BigDecimal selectTodayTotalByUserId(@Param("userId") Long userId);
}
