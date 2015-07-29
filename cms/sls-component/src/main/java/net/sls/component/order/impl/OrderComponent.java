package net.sls.component.order.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sls.businessconstant.BusinessContant;
import net.sls.component.order.IOrderComponent;
import net.sls.component.user.IMemberComponent;
import net.sls.dao.ext.order.OrderActGoodsInfoMapperExt;
import net.sls.dao.ext.order.OrderLogMapperExt;
import net.sls.dao.ext.order.OrderMapperExt;
import net.sls.dao.ext.order.OrderRefundMapperExt;
import net.sls.dao.order.DeliveryAddressMapper;
import net.sls.dao.order.OrderDetailMapper;
import net.sls.dao.order.OrderLogMapper;
import net.sls.dao.order.OrderPayMapper;
import net.sls.dao.order.OrderRefundMapper;
import net.sls.dao.order.OrdersMapper;
import net.sls.dao.user.UserMapper;
import net.sls.dto.ext.order.ExportOrderQueryDto;
import net.sls.dto.order.DeliveryAddress;
import net.sls.dto.order.OrderDetail;
import net.sls.dto.order.OrderLog;
import net.sls.dto.order.OrderLogExample;
import net.sls.dto.order.OrderPay;
import net.sls.dto.order.OrderRefund;
import net.sls.dto.order.Orders;
import net.sls.dto.user.CmsUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.framework.SessionUtil;
import util.model.ComboxDto;
import util.model.IsPaidEnum;
import util.model.OrderStatusEnum;
import framework.exception.BusinessException;
import framework.web.Pager;

@Component
public class OrderComponent implements IOrderComponent {

	@Autowired
	private OrderMapperExt orderMapperExt;
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private DeliveryAddressMapper deliveryAddressMapper;
	@Autowired
	private OrderLogMapper orderLogMapper;
	@Autowired
	private OrderLogMapperExt orderLogMapperExt;
	@Autowired
	private OrderActGoodsInfoMapperExt oagiext;
	@Autowired
	private OrderPayMapper opm;
	@Autowired
	private OrderDetailMapper odm;
	@Autowired
	private OrderRefundMapperExt refund;
	@Autowired
	private OrderRefundMapper oreMapper;
	@Autowired
	private IMemberComponent mem;
	
	@Override
	public Pager<List<Map<Object, Object>>> selectOrderList(String code,
			String userName, String mobile, String orderNum, Integer isPaid,
			Integer state, Integer status, Integer pavilionId, Date startDate,
			Date endDate, Integer isPaviOrder, Integer isMobile, Integer type,
			Integer start, Integer number) {
		long count = orderMapperExt.countOrdersByUserFilter(code, userName,
				mobile, orderNum, isPaid, state, status, pavilionId, startDate,
				endDate, isPaviOrder, isMobile, type);
		List<Map<Object, Object>> list = orderMapperExt
				.selectOrdersByUserFilter(code, userName, mobile, orderNum,
						isPaid, state, status, pavilionId, startDate, endDate,
						isPaviOrder, isMobile, type, (start - 1) * number,
						number);
		Pager<List<Map<Object, Object>>> pager = new Pager<List<Map<Object, Object>>>(
				list, count);
		return pager;
	}

	@Override
	public Orders selectOrderInfo(Long id) {
		return ordersMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateOrderStatus(Orders order) {
		if (order.getIsPaid() != null
				&& IsPaidEnum.IsPaid_2.getCode() == order.getIsPaid()) {
			return orderMapperExt.updateOrderPaid(order.getId());
		}
		return orderMapperExt.updateOrderStateOrStatus(order);
	}

	@Override
	public OrderLog insertOrdersLog(OrderLog orderLog) {
		int i = orderLogMapperExt.insertOrdersLog(orderLog);
		if (i != 1) {
			throw new BusinessException(11L);
		}
		return orderLog;
	}

	@Override
	public Pager<List<OrderLog>> selectOrderLog(long orderId) {
		OrderLogExample e = new OrderLogExample();
		e.createCriteria().andOrderIdEqualTo(orderId);
		return new Pager<List<OrderLog>>(orderLogMapper.selectByExample(e),
				(long) orderLogMapper.countByExample(e));
	}

	@Override
	public List<ExportOrderQueryDto> selectExportExcel(String code,
			String userName, String mobile, String orderNum, Integer isPaid,
			Integer state, Integer status, Integer pavilionId, Date startDate,
			Date endDate, Integer isPaviOrder, Integer isMobile, Integer type,
			Integer hasDetail) {
		return orderMapperExt.selectExportExcel(code, userName, mobile,
				orderNum, isPaid, state, status, pavilionId, startDate,
				endDate, isPaviOrder, isMobile, type, hasDetail);
	}

	@Override
	public List<ComboxDto> selectPavilionComboxDto(String name, String areaCode) {
		return orderMapperExt.selectPavilionComboxDto(name, areaCode);
	}

	@Override
	public List<Map<String, Object>> selectOrderActGoodsInfo(long orderId) {
		return oagiext.selectOrderActGoodsInfo(orderId);
	}

	@Override
	public int updateDeliveryAddress(DeliveryAddress deliveryAddress) {
		return deliveryAddressMapper
				.updateByPrimaryKeySelective(deliveryAddress);
	}

	@Override
	public Map<String, Object> selectOrderCoupon(long orderId) {
		return orderMapperExt.selectOrderCoupon(orderId);
	}

	@Override
	public Map<String, Object> selectOrderInfoByOrderNum(String orderNum) {
		return orderMapperExt.orderInfoByOrderNum(orderNum);
	}

	@Override
	public Orders insertOrderInfo(Orders orders, List<OrderDetail> ods,DeliveryAddress da, OrderPay op) {
		int i = orderMapperExt.insertOrders(orders);
		if(i != 1){
			throw new BusinessException(35L);
		}
		long orderId = orders.getId();
		da.setOrderId(orderId);
		i = deliveryAddressMapper.insertSelective(da);
		if(i != 1){
			throw new BusinessException(35L);
		}
		op.setOrderId(orderId);
		i = opm.insertSelective(op);
		if(i != 1){
			throw new BusinessException(35L);
		}
		for(OrderDetail od:ods){
			od.setOrderId(orderId);
			i = odm.insertSelective(od);
			if(i != 1){
				throw new BusinessException(35L);
			}
		}
		OrderLog ol = new OrderLog();
		ol.setCreateTime(new Date());
		CmsUser cu = (CmsUser) SessionUtil.get(BusinessContant.CMSUSER);
		ol.setOperId(cu.getId());
		ol.setOperType(BusinessContant.ORDEROPTYPE_CMS);
		ol.setOperName(cu.getName());
		ol.setOrderId(orderId);
		ol.setRemark("后台下单");
		i = orderLogMapperExt.insertOrdersLog(ol);
		if(i != 1){
			throw new BusinessException(35L);
		}
		return orders;
	}

	@Override
	public Pager<List<Map<String, Object>>> selectRefunds(String orderNum,
			Integer state, Integer type, Date startDate, Date endDate,
			int start, int number) {
		List<Map<String,Object>> list = refund.refunds(orderNum, state, type, startDate, endDate, (start-1)*number, number);
		long count = refund.countRefunds(orderNum, state, type, startDate, endDate);
		return new Pager<List<Map<String,Object>>>(list,count);
	}

	@Override
	public void updateRefund(OrderRefund ore) {
		int i = oreMapper.updateByPrimaryKeySelective(ore);
		if(i != 1){
			throw new BusinessException(2L);
		}
		if(ore.getState() != null && ore.getState().equals(127)){
			ore = oreMapper.selectByPrimaryKey(ore.getId());
			long c = refund.isExistRefund(ore.getOrderId());
			long d = refund.hdExistRefund(ore.getOrderId());
			Orders o = new Orders();
			o.setId(ore.getOrderId());
			if(c == 0){
				if(d == 0){
					o.setStatus(OrderStatusEnum.status_5.getStatus());
				}else{
					o.setStatus(OrderStatusEnum.status_7.getStatus());
				}
				i = ordersMapper.updateByPrimaryKeySelective(o);
				if(i != 1){
					throw new BusinessException(2L);
				}
			}
		}
	}

	@Override
	public void updateCompleteRefund(long refundId, int type) {
		OrderRefund ore = oreMapper.selectByPrimaryKey(refundId);
		Orders order = ordersMapper.selectByPrimaryKey(ore.getOrderId());
		if(order.getState() != 1){
			throw new BusinessException(37L);
		}
		if(ore.getState() != 1){
			throw new BusinessException(38L);
		}
		if(type == 1&&ore.getType() == 1&&ore.getMoneyWay() == 1){
			mem.updateUserAmountOrderRefund(order.getUserId(), ore.getRefundPrice(), order.getOrderNum());
		}
		ore.setState(2);
		int i = oreMapper.updateByPrimaryKeySelective(ore);
		if(i != 1){
			throw new BusinessException(2L);
		}
		long c = refund.isExistRefund(ore.getOrderId());
		long d = refund.hdExistRefund(ore.getOrderId());
		Orders o = new Orders();
		o.setId(ore.getOrderId());
		if(c == 0){
			if(d == 0){
				o.setStatus(OrderStatusEnum.status_5.getStatus());
			}else{
				o.setStatus(OrderStatusEnum.status_7.getStatus());
			}
			i = ordersMapper.updateByPrimaryKeySelective(o);
			if(i != 1){
				throw new BusinessException(2L);
			}
		}
	}

	@Override
	public List<Map<String, Object>> selectRefundExcel(String orderNum,
			Integer state, Integer type, Date startDate, Date endDate) {
		return refund.refundExcel(orderNum, state, type, startDate, endDate);
	}

}
