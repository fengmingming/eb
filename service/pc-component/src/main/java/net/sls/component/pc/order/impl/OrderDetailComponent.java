package net.sls.component.pc.order.impl;

import java.util.List;
import java.util.Map;

import net.sls.component.pc.order.IOrderDetailComponent;
import net.sls.dao.pc.order.PCOrderDetailMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailComponent implements IOrderDetailComponent{

	@Autowired
	private PCOrderDetailMapper orderDetailMapper;
	@Override
	public List<Map<Object, Object>> selectOrderDetailList(Long orderId) {
		List<Map<Object, Object>> lsit=orderDetailMapper.selectOrderDetailList(orderId);
		return lsit;
	}
	@Override
	public List<Map<Object,Object>> selectOrderDetailInfoList(Long orderId) {
		return orderDetailMapper.selectOrderDetailInfoList(orderId);
	}
	@Override
	public boolean updateConfirmDetail(long detailId) {
		orderDetailMapper.confirm(detailId);
		return true;
	}
	
	@Override
	public List<Map<Object, Object>> selectOrderDetailByOrderIds(List<Long> orderIds){
		return orderDetailMapper.selectOrderDetailByOrderIds(orderIds);
	}
	@Override
	public Map<Object, Object> getReturnOrderInfo(Long userId,
			Long createUserId, Long orderDetailId) {		
		return orderDetailMapper.getReturnOrderInfo(userId, createUserId, orderDetailId);
	}
	@Override
	public Map<Object, Object> getOrderInfoByOrderDetailId(Long orderDetailId) {	
		return  orderDetailMapper.getOrderInfoByOrderDetailId(orderDetailId);
	}
}
