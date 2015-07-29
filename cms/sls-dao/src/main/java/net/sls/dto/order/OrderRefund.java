package net.sls.dto.order;

import java.math.BigDecimal;
import java.util.Date;

public class OrderRefund {
    private Long id;

    private Long orderId;

    private Long newOrderId;

    private Long orderDetailId;

    private Integer state;

    private Integer origin;

    private Date createTime;

    private Integer type;

    private BigDecimal refundPrice;

    private Integer moneyWay;

    private String account;

    private String remark;

    private String photoUrl1;

    private String photoUrl2;

    private String photoUrl3;

    private Integer pickupWay;

    private Integer deliveryType;

    private Integer provinceIdT;

    private Integer cityIdT;

    private Integer districtIdT;

    private Integer communityIdT;

    private Integer pavilionIdT;

    private String remarkT;

    private String receiverT;

    private String mobileT;

    private Integer provinceIdF;

    private Integer cityIdF;

    private Integer districtIdF;

    private Integer communityIdF;

    private Integer pavilionIdF;

    private String remarkF;

    private String receiverF;

    private String mobileF;

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

    public Long getNewOrderId() {
        return newOrderId;
    }

    public void setNewOrderId(Long newOrderId) {
        this.newOrderId = newOrderId;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getOrigin() {
        return origin;
    }

    public void setOrigin(Integer origin) {
        this.origin = origin;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getRefundPrice() {
        return refundPrice;
    }

    public void setRefundPrice(BigDecimal refundPrice) {
        this.refundPrice = refundPrice;
    }

    public Integer getMoneyWay() {
        return moneyWay;
    }

    public void setMoneyWay(Integer moneyWay) {
        this.moneyWay = moneyWay;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPhotoUrl1() {
        return photoUrl1;
    }

    public void setPhotoUrl1(String photoUrl1) {
        this.photoUrl1 = photoUrl1 == null ? null : photoUrl1.trim();
    }

    public String getPhotoUrl2() {
        return photoUrl2;
    }

    public void setPhotoUrl2(String photoUrl2) {
        this.photoUrl2 = photoUrl2 == null ? null : photoUrl2.trim();
    }

    public String getPhotoUrl3() {
        return photoUrl3;
    }

    public void setPhotoUrl3(String photoUrl3) {
        this.photoUrl3 = photoUrl3 == null ? null : photoUrl3.trim();
    }

    public Integer getPickupWay() {
        return pickupWay;
    }

    public void setPickupWay(Integer pickupWay) {
        this.pickupWay = pickupWay;
    }

    public Integer getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Integer getProvinceIdT() {
        return provinceIdT;
    }

    public void setProvinceIdT(Integer provinceIdT) {
        this.provinceIdT = provinceIdT;
    }

    public Integer getCityIdT() {
        return cityIdT;
    }

    public void setCityIdT(Integer cityIdT) {
        this.cityIdT = cityIdT;
    }

    public Integer getDistrictIdT() {
        return districtIdT;
    }

    public void setDistrictIdT(Integer districtIdT) {
        this.districtIdT = districtIdT;
    }

    public Integer getCommunityIdT() {
        return communityIdT;
    }

    public void setCommunityIdT(Integer communityIdT) {
        this.communityIdT = communityIdT;
    }

    public Integer getPavilionIdT() {
        return pavilionIdT;
    }

    public void setPavilionIdT(Integer pavilionIdT) {
        this.pavilionIdT = pavilionIdT;
    }

    public String getRemarkT() {
        return remarkT;
    }

    public void setRemarkT(String remarkT) {
        this.remarkT = remarkT == null ? null : remarkT.trim();
    }

    public String getReceiverT() {
        return receiverT;
    }

    public void setReceiverT(String receiverT) {
        this.receiverT = receiverT == null ? null : receiverT.trim();
    }

    public String getMobileT() {
        return mobileT;
    }

    public void setMobileT(String mobileT) {
        this.mobileT = mobileT == null ? null : mobileT.trim();
    }

    public Integer getProvinceIdF() {
        return provinceIdF;
    }

    public void setProvinceIdF(Integer provinceIdF) {
        this.provinceIdF = provinceIdF;
    }

    public Integer getCityIdF() {
        return cityIdF;
    }

    public void setCityIdF(Integer cityIdF) {
        this.cityIdF = cityIdF;
    }

    public Integer getDistrictIdF() {
        return districtIdF;
    }

    public void setDistrictIdF(Integer districtIdF) {
        this.districtIdF = districtIdF;
    }

    public Integer getCommunityIdF() {
        return communityIdF;
    }

    public void setCommunityIdF(Integer communityIdF) {
        this.communityIdF = communityIdF;
    }

    public Integer getPavilionIdF() {
        return pavilionIdF;
    }

    public void setPavilionIdF(Integer pavilionIdF) {
        this.pavilionIdF = pavilionIdF;
    }

    public String getRemarkF() {
        return remarkF;
    }

    public void setRemarkF(String remarkF) {
        this.remarkF = remarkF == null ? null : remarkF.trim();
    }

    public String getReceiverF() {
        return receiverF;
    }

    public void setReceiverF(String receiverF) {
        this.receiverF = receiverF == null ? null : receiverF.trim();
    }

    public String getMobileF() {
        return mobileF;
    }

    public void setMobileF(String mobileF) {
        this.mobileF = mobileF == null ? null : mobileF.trim();
    }
}