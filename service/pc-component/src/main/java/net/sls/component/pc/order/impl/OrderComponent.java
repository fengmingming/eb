package net.sls.component.pc.order.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sls.commons.businessconstant.BusinessContant;
import net.sls.component.pc.order.IOrderComponent;
import net.sls.component.pc.product.IGoodsStockComponent;
import net.sls.dao.pc.activity.PCUserCouponMapper;
import net.sls.dao.pc.order.PCDeliveryAddressMapper;
import net.sls.dao.pc.order.PCOrderActGoodsInfoMapper;
import net.sls.dao.pc.order.PCOrderDetailMapper;
import net.sls.dao.pc.order.PCOrderLogMapper;
import net.sls.dao.pc.order.PCOrderPayMapper;
import net.sls.dao.pc.order.PCOrdersMapper;
import net.sls.dao.pc.product.PCGoodsAreaMapper;
import net.sls.dao.pc.product.PCGoodsMapper;
import net.sls.dao.pc.user.PCAmountLogMapper;
import net.sls.dao.pc.user.PCUserMapper;
import net.sls.dto.pc.order.DeliveryAddress;
import net.sls.dto.pc.order.OrderActGoodsInfo;
import net.sls.dto.pc.order.OrderBeanDto;
import net.sls.dto.pc.order.OrderDetail;
import net.sls.dto.pc.order.OrderLog;
import net.sls.dto.pc.order.OrderPay;
import net.sls.dto.pc.order.Orders;
import net.sls.dto.pc.user.AmountLog;
import net.sls.dto.pc.user.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.model.AmountLogTypeEnum;
import util.model.OrderStateEnum;
import util.model.OrderStatusEnum;
import framework.exception.BusinessException;
import framework.web.Pager;

@Component
public class OrderComponent implements IOrderComponent{
	
	private Logger log = LoggerFactory.getLogger(OrderComponent.class);
	
	@Autowired
	private PCUserMapper userMapper;
	
	@Autowired
	private PCGoodsMapper goodsMapper;

	@Autowired
	private PCOrdersMapper ordersMapper;
	
	@Autowired
	private PCOrderLogMapper orderLogMapper;
	
	@Autowired
	private PCOrderDetailMapper orderDetailMapper;
	
	@Autowired
	private PCOrderPayMapper orderPayMapper;
	
	@Autowired
	PCGoodsAreaMapper goodsAreaMapper;
	
	@Autowired
	private IGoodsStockComponent goodsStockComponent;
	
	@Autowired
	private PCDeliveryAddressMapper deliveryAddressMapper;
	
	@Autowired
	private PCAmountLogMapper amountLogMapper;
	
	@Autowired
	private PCOrderActGoodsInfoMapper oagMapper;
	
	@Autowired
	private PCUserCouponMapper uCouponMapper;
	
	@Override
	public Pager<List<Map<Object, Object>>> selectOrderListsByUserId(Integer state,String sort, String column,
			Long userId,
			String orderNum, Integer timeType,List<Integer> status, Integer isPaid, Integer start, Integer number) {
		long count = ordersMapper.countOrdersByFilter(state,userId,orderNum,timeType,status, isPaid);
		List<Map<Object, Object>> list = ordersMapper.selectOrdersListsByUserId(state,sort,column,userId,orderNum, timeType,status, isPaid, (start-1)*number, number);
		Pager<List<Map<Object, Object>>> pager = new Pager<List<Map<Object, Object>>>();
		pager.setEntry(list);
		pager.setTotal(count);
		return pager;
	}

	@Override
	public int insertOrder(Orders order, OrderDetail orderDetail,
			OrderPay orderPay, DeliveryAddress deliveryAddress) {
		//如果record==3的话说明三条记录插入成功
		int record = 0;
		//插入定单
		Long orderId = (long)ordersMapper.insert(order);
		//插入定单详情
		orderDetail.setOrderId(orderId);
		record+=orderDetailMapper.insert(orderDetail);
		//插入支付方式
		orderPay.setOrderId(orderId);
		record+=orderPayMapper.insert(orderPay);
		//插入派送地址
		deliveryAddress.setOrderId(orderId);
		record+=deliveryAddressMapper.insert(deliveryAddress);
		
		return record;
	}

	@Override
	public Pager<List<Map<Object, Object>>> selectPurchasAgentsOrders(Integer state,String sort, String column,
			Long userId,  String mobile,String orderNum, Integer timeType,
			List<Integer> status, Integer isPaid, Integer start, Integer number) {
		User user=new User();
		user=userMapper.selectUserByid(userId);
		long count = ordersMapper.countPurchasAgentsOrders(state,user.getId(), mobile,orderNum,timeType,status, isPaid);
		List<Map<Object, Object>> list = ordersMapper.selectPurchasAgentsOrders(state,sort,column,user.getId(), mobile,orderNum,timeType,status, isPaid, (start-1)*number, number);
		Pager<List<Map<Object, Object>>> pager = new Pager<List<Map<Object, Object>>>();
		pager.setEntry(list);
		pager.setTotal(count);
		return pager;
	}

	@Override
	public Pager<List<Map<Object, Object>>> getCollectOrders(Integer state,String sort, String column,Long userId,
			String mobile,String orderNum, Integer timeType, List<Integer> status, Integer isPaid,
			Integer start, Integer number) {
		User user=new User();
		user=userMapper.selectUserByid(userId);
		long count = ordersMapper.countCollectOrders(state,user.getPavilionId(), mobile,orderNum,timeType,status, isPaid);
		List<Map<Object, Object>> list = ordersMapper.selectCollectOrders(state,sort,column,user.getPavilionId(), mobile,orderNum,timeType,status, isPaid, (start-1)*number, number);
		Pager<List<Map<Object, Object>>> pager = new Pager<List<Map<Object, Object>>>();
		pager.setEntry(list);
		pager.setTotal(count);
		return pager;
	}
	
	@Override
	public void insertOrder(OrderBeanDto dto) {
		Orders orders = dto.getOrder();
		List<OrderDetail> ods = dto.getDetails();
		DeliveryAddress address = dto.getAddress();
		OrderPay op = dto.getOrderPay();
		long payUsrId = dto.getPayUsrId();
		int i = ordersMapper.insert(orders);
		if(i!=1){
			log.error("insert order failed cart"+orders.getCart());
			throw new BusinessException(31L);
		}
		if(BigDecimal.ZERO.compareTo(orders.getUseBalancePrice()) < 0){
			// 通过付款的usrId 来支付
			User user = userMapper.selectUserByid(payUsrId);
			i = userMapper.updateUserBalance(payUsrId, orders.getUseBalancePrice());
			if(i!=1){
				log.error("update user balance failed userId"+orders.getCreateUserId());
				throw new BusinessException(31L);
			}
			AmountLog alog = new AmountLog();
			alog.setCreateTime(orders.getCreateTime());
			alog.setMoney(orders.getUseBalancePrice());
			alog.setCurAmount(user.getAmount().subtract(orders.getUseBalancePrice()));
			alog.setOperator(payUsrId);
			alog.setRemark(orders.getOrderNum());
			alog.setUserId(payUsrId);
			alog.setType(AmountLogTypeEnum.out.getCode());
			i = amountLogMapper.insertAmountLog(alog);
			if(i!=1){
				log.error("insert amountlog failed userId"+orders.getCreateUserId());
				throw new BusinessException(31L);
			}
		}
		long orderId = orders.getId();
		address.setOrderId(orderId);
		i = deliveryAddressMapper.insert(address);
		if(i != 1){
			log.error("insert orderAddress failed address userId = "+orders.getUserId());
			throw new BusinessException(31L);
		}
		op.setOrderId(orderId);
		i = orderPayMapper.insert(op);
		if(i!=1){
			log.error("insert orderPay failed address payCode = "+op.getPayCode());
			throw new BusinessException(31L);
		}
		for(OrderDetail detail:ods){
			detail.setOrderId(orderId);
		}
		i = orderDetailMapper.insertBatch(ods);
		if(i!=ods.size()){
			log.error("insert orderDetail failed");
			throw new BusinessException(31L);
		}
		//减库存
		try{
			if(dto.getStockList().size() > 0){
				goodsMapper.updateGoodsStcokBatch(dto.getStockList());
			}
			if(dto.getLimitList().size() > 0){
				goodsAreaMapper.updateGoodsAreaBatch(dto.getLimitList());
			}
		}catch(Exception e){
			log.error("update stock limit faild",e);
			throw new BusinessException(36L);
		}
		//优惠券
		if(dto.getUserCouponId() != null){
			i = uCouponMapper.updateUserCouponById(dto.getUserCouponId(), orderId, orders.getOrderNum());
			if(i != 1){
				throw new BusinessException(51L);
			}
		}
		//增加活动商品的购买记录（普通用户）
		if(dto.getActGoodsList() != null && dto.getActGoodsList().size() > 0){
			for(OrderActGoodsInfo oag : dto.getActGoodsList()){
				oag.setOrderId(orderId);
				oag.setUserId(orders.getUserId());
				oagMapper.insert(oag);
			}
		}
	}

	/**@author wangshaohui
	 * @Description: 得到单元商品中不足库存的最小值
	 * @param stockNums
	 * @return int
	 * @date 2015年1月9日 上午10:22:20
	 */
	public static int getMin(List<Integer> stockNums) {
		Object[] objs = stockNums.toArray();
		Arrays.sort(objs);
		int minStockNum = Integer.valueOf(String.valueOf(objs[0]));
		return minStockNum;
	}

	@Override
	public Map<Object, Object> selectOrderInfo(Long userId, Long id) {
		return ordersMapper.selectOrderInfo(userId,id);
	}

	@Override
	public Integer selectOrderByIsPaid1(Long userId) {
		return ordersMapper.selectOrderByIsPaid1(userId);
	}

	@Override
	public Integer selectOrderByStatus4(Long userId) {
		return ordersMapper.selectOrderByStatus4(userId);
	}

	@Override
	public void insertOrderLog(OrderLog orderLog) {
		try{
			orderLogMapper.insert(orderLog);
		}catch(Exception e){
			log.error("insert orderLog failed",e);
		}
	}

	@Override
	public boolean updateOrderState(long orderId,OrderStateEnum e) {
		if(ordersMapper.updateOrderState(orderId, e.getCode()) == 1){
			return true;
		}
		return false;
	}

	@Override
	public boolean updateOrderStatus(long orderId,OrderStatusEnum e) {
		if(ordersMapper.updateOrderStatus(orderId, e.getStatus()) == 1){
			if(e == OrderStatusEnum.status_5){
				orderDetailMapper.confirmAll(orderId);
			}
			return true;
		}
		return false;
	}

	@Override
	public Orders selectOrdersByOrderNum(String orderNum) {
		return ordersMapper.selectOrdersByOrderNum(orderNum);
	}

	@Override
	public boolean deleteOrder(long orderId,long userId) {
		if(ordersMapper.cancelOrder(orderId) == 1){
			Orders order = ordersMapper.selectOrdersByOrderId(orderId);
			List<OrderDetail> orderDetailList=orderDetailMapper.selectOrderDetailListByOrderId(orderId);
			int j = goodsStockComponent.updateGoodsStockAdd(orderDetailList);
			if(j == 0){
				throw new BusinessException(9L);
			}
			if(order.getIsPaid() == BusinessContant.ORDERISPAID_2 && order.getPaidPrice().compareTo(BigDecimal.ZERO) > 0){
                long payOrderUsrId = order.getCreateUserId();
                if(order.getIsCardBuy() == 1) //如使用卡付则返回用户余额
                    payOrderUsrId = order.getUserId();

				User user = userMapper.selectUserByid(payOrderUsrId);
					AmountLog log = new AmountLog();
					log.setCreateTime(new Date());
					log.setCurAmount(user.getAmount().add(order.getUseBalancePrice()));
					log.setMoney(order.getUseBalancePrice());
					log.setOperator(userId);
					log.setUserId(payOrderUsrId);       //退款到付款的账户中
					log.setType(AmountLogTypeEnum.in.getCode());
					log.setRemark(BusinessException.getMessage(43L, order.getOrderNum()));
					
					j = amountLogMapper.insertAmountLog(log);
					if(j == 0){
						throw new BusinessException(9L);
					}
					j = userMapper.updateUserAmount(payOrderUsrId, user.getAmount(), order.getPaidPrice());
					if(j == 0){
						throw new BusinessException(9L);
					}
			}
			//取消使用的优惠券
			j = uCouponMapper.updateUserCouponByOrderId(orderId);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateConfirmPaid(long orderId, BigDecimal price) {
		if(ordersMapper.updateConfirmPaid(orderId, price) == 1){
			return true;
		}
		return false;
	}

	@Override
	public Orders selectOrdersById(long orderId) {
		return ordersMapper.selectOrdersByOrderId(orderId);
	}

	@Override
	public Map<Object, Object> selectCollectOrderInfo(Long userId, Long id,
			Integer pavilionId) {
		return ordersMapper.selectCollectOrderInfo(userId,id, pavilionId);
	}

	@Override
	public Map<Object, Object> selectPurchasAgentsOrderInfo(Long userId,
			Long id, Integer pavilionId) {
		return ordersMapper.selectPurchasAgentsOrderInfo(userId,id, pavilionId);
	}

	@Override
	public Pager<List<Map<Object, Object>>> selectPavilionOrders(Integer state,
			String sort, String column, Long userId, String mobile,
			String orderNum, Integer timeType, List<Integer> status,
			Integer isPaid, Integer start, Integer number) {
		User user = new User();
		user = userMapper.selectUserByid(userId);
		Pager<List<Map<Object, Object>>> pager = new  Pager<List<Map<Object, Object>>>();
		long count = ordersMapper.countPavilionOrders(state,userId,user.getPavilionId(), mobile, orderNum, timeType, status, isPaid);
		List<Map<Object, Object>> list = ordersMapper.selectPavilionOrders(state,sort,column,userId,user.getPavilionId(), mobile,orderNum,timeType,status, isPaid, (start-1)*number, number);
		pager.setEntry(list);
		pager.setTotal(count);
		return pager;
	}

	@Override
	public Map<Object, Object> getPavilionOrderInfo(Long id) {
		return  ordersMapper.getPavilionOrderInfo(id);
	}
	
	@Override
	public List<Map<Object, Object>> getTodayDsOrders(Integer pavilionId){
		return  ordersMapper.getTodayDsOrders(pavilionId);
	}
	
	@Override
	public List<Map<Object, Object>> getTodayDgOrders(long userId){
		return  ordersMapper.getTodayDgOrders(userId);
	}
	
	@Override
	public BigDecimal selectTodayTotalByUserId(Long userId) {
		return ordersMapper.selectTodayTotalByUserId(userId);
	}

	@Override
	public int updateOrderPayByOrderId(String account, Long orderId) {
		
		return orderPayMapper.updateOrderPayByOrderId(account, orderId);
	}
}
