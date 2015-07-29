package net.sls.dto.ext.order;

import java.math.BigDecimal;
import java.util.Date;

public class ExportOrderQueryDto {
	
	private String orderNum;
	private Integer status;
	private Integer state;
	private Integer isPaid;
	private Date createtime;
	private BigDecimal amount;
	private BigDecimal payPrice;
	private BigDecimal paidPrice;
	private BigDecimal discutPrice;
	private BigDecimal useBalancePrice;
	private Integer deliveryType;
	private Integer channelId;
	private Long userId;
	private Long createUserId;
	private String pavilionName;
	private String pavilionCode;
	private Date payTime;
	private String payName;
	private String userName;
	private BigDecimal price;
	private String sku;
	private Integer number;
	private String providerName;
	private String goodsName;
	private Long goodsId;
	private Long oneId;
	private Long twoId;
	private Long threeId;
	private Integer isDelivery;
	private Integer shopType;
	private String remark;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getIsPaid() {
		return isPaid;
	}
	public void setIsPaid(Integer isPaid) {
		this.isPaid = isPaid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getPayPrice() {
		return payPrice;
	}
	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
	}
	public BigDecimal getPaidPrice() {
		return paidPrice;
	}
	public void setPaidPrice(BigDecimal paidPrice) {
		this.paidPrice = paidPrice;
	}
	public BigDecimal getDiscutPrice() {
		return discutPrice;
	}
	public void setDiscutPrice(BigDecimal discutPrice) {
		this.discutPrice = discutPrice;
	}
	public BigDecimal getUseBalancePrice() {
		return useBalancePrice;
	}
	public void setUseBalancePrice(BigDecimal useBalancePrice) {
		this.useBalancePrice = useBalancePrice;
	}
	public Integer getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(Integer deliveryType) {
		this.deliveryType = deliveryType;
	}
	public String getPavilionName() {
		return pavilionName;
	}
	public void setPavilionName(String pavilionName) {
		this.pavilionName = pavilionName;
	}
	public String getPavilionCode() {
		return pavilionCode;
	}
	public void setPavilionCode(String pavilionCode) {
		this.pavilionCode = pavilionCode;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		this.payName = payName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public Long getOneId() {
		return oneId;
	}
	public void setOneId(Long oneId) {
		this.oneId = oneId;
	}
	public Long getTwoId() {
		return twoId;
	}
	public void setTwoId(Long twoId) {
		this.twoId = twoId;
	}
	public Long getThreeId() {
		return threeId;
	}
	public void setThreeId(Long threeId) {
		this.threeId = threeId;
	}
	public Integer getIsDelivery() {
		return isDelivery;
	}
	public void setIsDelivery(Integer isDelivery) {
		this.isDelivery = isDelivery;
	}
	public Integer getShopType() {
		return shopType;
	}
	public void setShopType(Integer shopType) {
		this.shopType = shopType;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
