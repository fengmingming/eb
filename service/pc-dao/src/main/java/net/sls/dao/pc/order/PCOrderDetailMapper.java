package net.sls.dao.pc.order;

import java.util.List;
import java.util.Map;

import net.sls.dto.pc.order.OrderDetail;

import org.apache.ibatis.annotations.Param;

public interface PCOrderDetailMapper {

	List<Map<Object, Object>> selectOrderDetailList(@Param("orderId") Long orderId);

	/**
	 * @Description: TODO 插入定单详情
	 * @param orderDetail
	 * @return int
	 * @date 2015年1月7日 下午7:13:24
	 */
	int insert(OrderDetail orderDetail);
	
	/**
	 * 
	 * 批量确认收货
	 * @param orderId 订单id
	 * */
	void confirmAll(long orderId);
	
	/**
	 * 
	 * 批量确认收货
	 * @param detailId 单个确认收货
	 * */
	void confirm(long detailId);
	
	/**
	 * 
	 * 下单专用，新增订单明细
	 * */
	int insertBatch(List<OrderDetail> orderDetail);
	/**@author chenlianjie
	 * 我的订单查看订单里的订单商品详情
	 * @param orderId
	 * @return
	 */
	List<Map<Object,Object>> selectOrderDetailInfoList(@Param("orderId") Long orderId);

	List<OrderDetail> selectOrderDetailListByOrderId(@Param("orderId") Long orderId);

	/**
	 * 根据订单ids的到订单详情
	 * @param orderIds
	 * @return
	 */
	List<Map<Object, Object>> selectOrderDetailByOrderIds(@Param("orderIds") List<Long> orderIds);
	
	/**
	 * 根据订单详情id获取退换货订单信息
	 * @param userId
	 * @param createUserId
	 * @param orderDetailId
	 * @return
	 */
	Map<Object, Object> getReturnOrderInfo(@Param("userId") Long userId, @Param("createUserId") Long createUserId, @Param("orderDetailId") Long orderDetailId);
	
	/**
	 * 根据订单详情id获取退换货订单信息(保存)
	 * @param orderDetailId
	 * @return
	 */
	Map<Object, Object> getOrderInfoByOrderDetailId(@Param("orderDetailId") Long orderDetailId);
	
	/**
	 * 根据订单id修改订单详情为参与退货
	 * @param orderDetailId
	 * @return
	 */
	int updateOrderDetailByid(@Param("orderDetailId") Long orderDetailId, @Param("isRefund") Integer isRefund);
}
