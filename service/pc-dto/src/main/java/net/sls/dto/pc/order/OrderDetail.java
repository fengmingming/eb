package net.sls.dto.pc.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long orderId;

    private Long providerId;

    private Long goodsId;

    private BigDecimal price;

    private BigDecimal marketPrice;
    
    private BigDecimal effPay;

    private Integer number;

    private Integer isDelivery;

    private Date createTime;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal pirce) {
		this.price = pirce;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public BigDecimal getEffPay() {
		return effPay;
	}

	public void setEffPay(BigDecimal effPay) {
		this.effPay = effPay;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getIsDelivery() {
		return isDelivery;
	}

	public void setIsDelivery(Integer isDelivery) {
		this.isDelivery = isDelivery;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
