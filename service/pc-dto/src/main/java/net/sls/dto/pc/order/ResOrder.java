package net.sls.dto.pc.order;

import java.io.Serializable;
import java.math.BigDecimal;

public class ResOrder implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long orderId;
	private String orderNum;
	private BigDecimal payPrice;
	private Integer payType;
	private String cart;
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public BigDecimal getPayPrice() {
		return payPrice;
	}
	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getCart() {
		return cart;
	}
	public void setCart(String cart) {
		this.cart = cart;
	}
	
}
