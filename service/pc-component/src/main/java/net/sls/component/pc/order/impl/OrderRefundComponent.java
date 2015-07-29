package net.sls.component.pc.order.impl;

import net.sls.component.pc.order.IOrderRefundComponent;
import net.sls.dao.pc.order.PCOrderDetailMapper;
import net.sls.dao.pc.order.PCOrderRefundMapper;
import net.sls.dao.pc.order.PCOrdersMapper;
import net.sls.dto.pc.order.OrderRefund;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.model.OrderStatusEnum;
import framework.exception.BusinessException;



@Component
public class OrderRefundComponent implements IOrderRefundComponent{
	
	
	@Autowired
	private PCOrderRefundMapper orderRefundMapper;
	
	@Autowired
	private PCOrderDetailMapper orderDetailMapper;
	
	@Autowired
	private PCOrdersMapper orderMapper;

	@Override
	public int saveReturnGoods(OrderRefund orderRefund) {		
		Long orderDetailId = orderRefund.getOrderDetailId();
		//设置订单详情为退货中 1
		int i = orderDetailMapper.updateOrderDetailByid(orderDetailId, 1);
		if(i != 1){
			throw new BusinessException(53L);
		}else{
			i = orderMapper.updateOrderStatus(orderRefund.getOrderId(), OrderStatusEnum.status_6.getStatus());
			if(i != 1){
				throw new BusinessException(9L);
			}else{
				OrderRefund or = orderRefundMapper.selectOrderRefundByODId(orderRefund.getOrderId(), orderDetailId);
				if(or != null){
					i = orderRefundMapper.updateOrderRefund(or.getId(),orderRefund.getState(),orderRefund.getOrigin(), orderRefund.getType(), orderRefund.getRefundPrice(), orderRefund.getMoneyWay(), orderRefund.getAccount(), orderRefund.getRemark(), orderRefund.getPhotoUrl1(), orderRefund.getPhotoUrl2(), orderRefund.getPhotoUrl3(), orderRefund.getPickupWay(), orderRefund.getDeliveryType(), orderRefund.getProvinceIdT(), orderRefund.getCityIdT(), orderRefund.getDistrictIdT(), orderRefund.getCommunityIdT(), orderRefund.getMobileF(), orderRefund.getPavilionIdT(), orderRefund.getProvinceIdF(), orderRefund.getCityIdF(), orderRefund.getDistrictIdF(), orderRefund.getCommunityIdF(), orderRefund.getPavilionIdF(), orderRefund.getRemarkT(), orderRefund.getReceiverT(), orderRefund.getMobileT(), orderRefund.getRemarkF(), orderRefund.getReceiverF());
					if(i != 1){
						throw new BusinessException(9L);
					}
				}else{
					i = orderRefundMapper.insertOrderRefund(orderRefund.getOrderId(), orderDetailId, orderRefund.getOrigin(), orderRefund.getType(), orderRefund.getRefundPrice(), orderRefund.getMoneyWay(), orderRefund.getAccount(), orderRefund.getRemark(), orderRefund.getPhotoUrl1(), orderRefund.getPhotoUrl2(), orderRefund.getPhotoUrl3(), orderRefund.getPickupWay(), orderRefund.getDeliveryType(), orderRefund.getProvinceIdT(), orderRefund.getCityIdT(), orderRefund.getDistrictIdT(), orderRefund.getCommunityIdT(), orderRefund.getMobileF(), orderRefund.getPavilionIdT(), orderRefund.getProvinceIdF(), orderRefund.getCityIdF(), orderRefund.getDistrictIdF(), orderRefund.getCommunityIdF(), orderRefund.getPavilionIdF(), orderRefund.getRemarkT(), orderRefund.getReceiverT(), orderRefund.getMobileT(), orderRefund.getRemarkF(), orderRefund.getReceiverF());
					if(i != 1){
						throw new BusinessException(54L);
					}
				}
				
			}
			
		}
		return i;
	}

	@Override
	public int updateReturnGoodsByODId(Long orderId, Long orderDetailId, int isRefund, int state) {
		int i = orderDetailMapper.updateOrderDetailByid(orderDetailId, isRefund);
		if(i != 1){
			throw new BusinessException(9L);
		}else{
			i = orderRefundMapper.updateOrderRefundState(orderId, orderDetailId, state);
			if(i != 1){
				throw new BusinessException(9L);
			}else{
				//查询是否有退货中的商品
				int j = orderRefundMapper.selectOrderRefundByOrderId(orderId, 1);
				if(j > 0){
					i = orderMapper.updateOrderStatus(orderId, OrderStatusEnum.status_6.getStatus());
					if(i != 1){
						throw new BusinessException(9L);
					}
				}else{
					//查询是否有退货完成的商品
					j = orderRefundMapper.selectOrderRefundByOrderId(orderId, 2);
					if(j > 0){
						i = orderMapper.updateOrderStatus(orderId, OrderStatusEnum.status_7.getStatus());
						if(i != 1){
							throw new BusinessException(9L);
						}
					}else{
						i = orderMapper.updateOrderStatus(orderId, OrderStatusEnum.status_5.getStatus());
						if(i != 1){
							throw new BusinessException(9L);
						}
					}
				}
			}
			
		}
		return i;
	}	
}
