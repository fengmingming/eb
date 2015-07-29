package net.sls.component.pc.order;

import java.util.List;
import java.util.Map;

public interface IOrderDetailComponent {

	List<Map<Object, Object>> selectOrderDetailList(Long orderId);

	List<Map<Object,Object>> selectOrderDetailInfoList(Long orderId);
	
	boolean updateConfirmDetail(long detailId);
	
	/**
	 * 根据订单ids得到订单详细信息
	 * @param orderIds
	 * @return
	 */
	List<Map<Object, Object>> selectOrderDetailByOrderIds(List<Long> orderIds);
	
	/**
	 * 根据订单详情id获取退换货订单信息（展示）
	 * @param userId
	 * @param createUserId
	 * @param orderDetailId
	 * @return
	 */
	Map<Object, Object> getReturnOrderInfo(Long userId, Long createUserId, Long orderDetailId);
	
	/**
	 * 根据订单详情id获取退换货订单信息(保存)
	 * @param orderDetailId
	 * @return
	 */
	Map<Object, Object> getOrderInfoByOrderDetailId(Long orderDetailId);
}
