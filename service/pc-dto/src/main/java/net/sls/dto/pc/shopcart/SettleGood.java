package net.sls.dto.pc.shopcart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sls.dto.pc.act.UserCoupon;

public class SettleGood implements Serializable{

	private static final long serialVersionUID = 1L;

	private long id;
	
    private String sku;

    private String goodsName;

    private int isSale;

    private BigDecimal price = BigDecimal.ZERO;

    private BigDecimal marketPrice = BigDecimal.ZERO;
    
    private BigDecimal effPay = BigDecimal.ZERO;

    private String photoUrl;
    
    private int virtualNumber;
    
    private int actNumber = Integer.MAX_VALUE;
    
    private long providerId;
    
    private int limittype;
    
    private int number = 1;
    
    private boolean isLimit = false;
    
    private int limitNumber = Integer.MAX_VALUE;
    
    private List<UserCoupon> canUseCouponList = new ArrayList<UserCoupon>();
    
    private Integer isUseCoupon;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getIsSale() {
		return isSale;
	}

	public void setIsSale(int isSale) {
		this.isSale = isSale;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public int getVirtualNumber() {
		return virtualNumber;
	}

	public void setVirtualNumber(int virtualNumber) {
		this.virtualNumber = virtualNumber;
	}

	public int getLimittype() {
		return limittype;
	}

	public void setLimittype(int limittype) {
		this.limittype = limittype;
	}

	public boolean isLimit() {
		return isLimit;
	}

	public void setLimit(boolean isLimit) {
		this.isLimit = isLimit;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getLimitNumber() {
		return limitNumber;
	}

	public void setLimitNumber(int limitNumber) {
		this.limitNumber = limitNumber;
	}
	
	public int getMinNumber(){
		if(this.isLimit){
			int number = Math.min(this.virtualNumber, this.limitNumber);
			return Math.min(this.actNumber, number);
		}
		return this.virtualNumber;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public int getActNumber() {
		return actNumber;
	}

	public void setActNumber(int actNumber) {
		this.actNumber = actNumber;
	}

	public List<UserCoupon> getCanUseCouponList() {
		return canUseCouponList;
	}

	public void setCanUseCouponList(List<UserCoupon> canUseCouponList) {
		this.canUseCouponList = canUseCouponList;
	}

	public Integer isUseCoupon() {
		return isUseCoupon;
	}

	public void setUseCoupon(Integer isUseCoupon) {
		this.isUseCoupon = isUseCoupon;
	}
}
