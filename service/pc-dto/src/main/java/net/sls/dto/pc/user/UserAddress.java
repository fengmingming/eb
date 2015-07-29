package net.sls.dto.pc.user;

import java.io.Serializable;
import java.util.Date;

public class UserAddress implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long userId;

    private Integer pavilionId;

    private String pavilionCode;

    private Integer province;

    private Integer city;

    private Integer district;

    private Integer community;

    private String addressDetail;

    private Integer isdel;

    private Date createTime;

    private String postcode;

    private String mobile;

    private String receiver;

    private String phone;

    private Boolean isdefault;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getPavilionId() {
        return pavilionId;
    }

    public void setPavilionId(Integer pavilionId) {
        this.pavilionId = pavilionId;
    }

    public String getPavilionCode() {
        return pavilionCode;
    }

    public void setPavilionCode(String pavilionCode) {
        this.pavilionCode = pavilionCode == null ? null : pavilionCode.trim();
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public Integer getCommunity() {
        return community;
    }

    public void setCommunity(Integer community) {
        this.community = community;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail == null ? null : addressDetail.trim();
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Boolean getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Boolean isdefault) {
        this.isdefault = isdefault;
    }
}