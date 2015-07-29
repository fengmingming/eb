package net.sls.dto.pc.order;

import java.io.Serializable;
import java.util.List;

import net.sls.dto.pc.product.OrderStockDto;
import net.sls.dto.pc.user.User;

public class OrderBeanDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Orders order;
	
	private OrderPay orderPay;
	
	private DeliveryAddress address;
	
	private List<OrderDetail> details;
	
	private User createOrderUser;

	private long payUsrId;

	public long getPayUsrId() {
		return payUsrId;
	}

	public void setPayUsrId(long payUsrId) {
		this.payUsrId = payUsrId;
	}

	private List<OrderStockDto> stockList;
	
	private List<OrderStockDto> limitList;
	
	private List<OrderActGoodsInfo> actGoodsList;
	
	private Long userCouponId;
	
	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public OrderPay getOrderPay() {
		return orderPay;
	}

	public void setOrderPay(OrderPay orderPay) {
		this.orderPay = orderPay;
	}

	public DeliveryAddress getAddress() {
		return address;
	}

	public void setAddress(DeliveryAddress address) {
		this.address = address;
	}

	public List<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}

	public User getCreateOrderUser() {
		return createOrderUser;
	}

	public void setCreateOrderUser(User createOrderUser) {
		this.createOrderUser = createOrderUser;
	}

	public List<OrderStockDto> getStockList() {
		return stockList;
	}

	public void setStockList(List<OrderStockDto> stockList) {
		this.stockList = stockList;
	}

	public List<OrderStockDto> getLimitList() {
		return limitList;
	}

	public void setLimitList(List<OrderStockDto> limitList) {
		this.limitList = limitList;
	}

	public List<OrderActGoodsInfo> getActGoodsList() {
		return actGoodsList;
	}

	public void setActGoodsList(List<OrderActGoodsInfo> actGoodsList) {
		this.actGoodsList = actGoodsList;
	}

	public Long getUserCouponId() {
		return userCouponId;
	}

	public void setUserCouponId(Long userCouponId) {
		this.userCouponId = userCouponId;
	}
}
