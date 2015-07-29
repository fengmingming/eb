package net.sls.dto.pc.act;

import java.io.Serializable;
import java.util.Date;

import net.sls.dto.pc.user.UserAddress;
import framework.exception.BusinessException;

public class Buyer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer provinceId;
	
	private Integer cityId;

    private Integer districtId;

    private Integer communityId;

    private Integer pavilionId;
    
    private int channelId = 1;
    
    //提交订单参数
    private Long userId;


	private Long payUsrId;

	public Long getPayUsrId() {
		return payUsrId;
	}

	public void setPayUsrId(Long payUsrId) {
		this.payUsrId = payUsrId;
	}

	private String payPassword;
	
	private Long addressId;
    
    private Long createUserId;
    
    private Integer payType;
    
    private boolean isBalance;

	private Integer isCardPay;
	
	private Long userCouponId;
	
	public Integer getIsCardPay() {
		return isCardPay;
	}

	public void setIsCardPay(Integer isCardPay) {
		this.isCardPay = isCardPay;
	}

	private int deliveryType;
    
    private String mobile;
    
    private String phone;
    
    private String receiver;
    
    private String postCode;
    
    private String remark;
    
    private Date startDate;
    
    private Date endDate;
    
    private Boolean isCommonUser;

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public boolean isBalance() {
		return isBalance;
	}

	public void setBalance(boolean isBalance) {
		this.isBalance = isBalance;
	}

	public int getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(int deliveryType) {
		this.deliveryType = deliveryType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public Integer getCommunityId() {
		return communityId;
	}

	public void setCommunityId(Integer communityId) {
		this.communityId = communityId;
	}

	public Integer getPavilionId() {
		return pavilionId;
	}

	public void setPavilionId(Integer pavilionId) {
		this.pavilionId = pavilionId;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public boolean isLogin(){
		return this.userId == null?false:true;
	}
	
	public void commitOrderVerfiy(){
		if(this.userId == null){
			throw new RuntimeException("userId is not null");
		}
		if(this.createUserId == null){
			this.createUserId = this.userId;
		}
	}
	
	public void addressVerify(){
		//未选择送货类型，会使用deliveryType的默认值0
//		if(this.deliveryType == 0 ||
//				this.deliveryType==1 && (this.provinceId == null||this.cityId == null||this.districtId == null||this.communityId == null||this.pavilionId == null||this.mobile == null||this.receiver == null) ||
//				this.deliveryType==2 && (this.provinceId == null||this.cityId == null||this.districtId == null||this.mobile == null||this.receiver == null)){
		if(this.deliveryType == 0 || this.provinceId == null || this.cityId == null || this.districtId == null ||
				this.communityId == null || this.pavilionId == null || this.mobile == null || this.receiver == null){
			throw new BusinessException(48L);
		}
		if(this.payType == null){
			throw new BusinessException(49L);
		}
	}
	
	public void setUserAddress(UserAddress address){
		this.addressId = address.getId();
		this.provinceId = address.getProvince();
		this.cityId = address.getCity();
		this.districtId = address.getDistrict();
		this.communityId = address.getCommunity();
		this.pavilionId = address.getPavilionId();
		this.mobile = address.getMobile();
		this.phone = address.getPhone();
		this.postCode = address.getPostcode();
		this.receiver = address.getReceiver();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	
	public Boolean getIsCommonUser() {
		if(isCommonUser == null){
			return Boolean.FALSE;
		}
		return isCommonUser;
	}

	public void setIsCommonUser(Boolean isCommonUser) {
		this.isCommonUser = isCommonUser;
	}

	/**
	 * 判断用户信息是否齐全
	 * @return
	 */
	public boolean isNeedMoreInfo(){
		if(this.provinceId != null && this.cityId != null && this.districtId != null &&
				this.communityId != null && this.pavilionId != null){
			return false;
		}
		return true;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public Long getUserCouponId() {
		return userCouponId;
	}

	public void setUserCouponId(Long userCouponId) {
		this.userCouponId = userCouponId;
	}
}
