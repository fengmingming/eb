package net.sls.component.pc.order;

import net.sls.dto.pc.order.OrderRefund;



public interface IOrderRefundComponent {
	/**
	 * 保存退换货订单信息
	 * @param orderRefund
	 * @return
	 */
	int saveReturnGoods(OrderRefund orderRefund);
	
	/**
	 * 取消退换货申请
	 * @param orderDetailId
	 * @return
	 */
	int updateReturnGoodsByODId(Long orderId, Long orderDetailId, int isRefund, int state);
}