package net.sls.dao.pc.order;

import java.math.BigDecimal;

import net.sls.dto.pc.order.OrderRefund;

import org.apache.ibatis.annotations.Param;



public interface PCOrderRefundMapper {
    /**
     * 插入退货订单表
     * @return
     */
	int insertOrderRefund(@Param("orderId") Long orderId, @Param("orderDetailId") Long orderDetailId, @Param("origin") Integer origin, @Param("type") Integer type, @Param("refundPrice") BigDecimal refundPrice, @Param("moneyWay") Integer moneyWay, @Param("account") String account, @Param("remark") String remark, @Param("photoUrl1") String photoUrl1, @Param("photoUrl2") String photoUrl2, @Param("photoUrl3") String photoUrl3, @Param("pickupWay") Integer pickupWay, @Param("deliveryType") Integer deliveryType, @Param("provinceIdT") Integer provinceIdT, @Param("cityIdT") Integer cityIdT, @Param("districtIdT") Integer districtIdT, @Param("communityIdT") Integer communityIdT, @Param("mobileF") String mobileF, @Param("pavilionIdT") Integer pavilionIdT, @Param("provinceIdF") Integer provinceIdF, @Param("cityIdF") Integer cityIdF, @Param("districtIdF") Integer districtIdF, @Param("communityIdF") Integer communityIdF, @Param("pavilionIdF") Integer pavilionIdF, @Param("remarkT") String remarkT, @Param("receiverT") String receiverT, @Param("mobileT") String mobileT, @Param("remarkF") String remarkF, @Param("receiverF") String receiverF);
	
	/**
	 * 更新退货订单表
	 * @return
	 */
	int updateOrderRefund(@Param("id") Long id,@Param("state") Integer state, @Param("origin") Integer origin, @Param("type") Integer type, @Param("refundPrice") BigDecimal refundPrice, @Param("moneyWay") Integer moneyWay, @Param("account") String account, @Param("remark") String remark, @Param("photoUrl1") String photoUrl1, @Param("photoUrl2") String photoUrl2, @Param("photoUrl3") String photoUrl3, @Param("pickupWay") Integer pickupWay, @Param("deliveryType") Integer deliveryType, @Param("provinceIdT") Integer provinceIdT, @Param("cityIdT") Integer cityIdT, @Param("districtIdT") Integer districtIdT, @Param("communityIdT") Integer communityIdT, @Param("mobileF") String mobileF, @Param("pavilionIdT") Integer pavilionIdT, @Param("provinceIdF") Integer provinceIdF, @Param("cityIdF") Integer cityIdF, @Param("districtIdF") Integer districtIdF, @Param("communityIdF") Integer communityIdF, @Param("pavilionIdF") Integer pavilionIdF, @Param("remarkT") String remarkT, @Param("receiverT") String receiverT, @Param("mobileT") String mobileT, @Param("remarkF") String remarkF, @Param("receiverF") String receiverF);
	
	/**
	 * 根据订单id和订单详情id查询该订单是否退货
	 */
	OrderRefund selectOrderRefundByODId(@Param("orderId") Long orderId, @Param("orderDetailId") Long orderDetailId);
	
	/**
	 * 取消退换货修改状态
	 * @param orderDetailId
	 * @param state
	 * @return
	 */
	int updateOrderRefundState(@Param("orderId") Long orderId, @Param("orderDetailId") Long orderDetailId, @Param("state") Integer state);
	
	/**
	 * 根据订单id查询订单状态
	 * @param orderId
	 * @return
	 */
	int selectOrderRefundByOrderId(@Param("orderId") Long orderId, @Param("state") int state);
}