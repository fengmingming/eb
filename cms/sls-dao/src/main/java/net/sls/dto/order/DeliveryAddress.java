package net.sls.dto.order;

import java.util.Date;

public class DeliveryAddress {
    private Long id;

    private Long orderId;

    private Integer province;

    private Integer city;

    private Integer district;

    private Integer community;

    private String provinceName;

    private String cityName;

    private String districtName;

    private String communityName;

    private String mobile;

    private String phone;

    private String receiver;

    private String postcode;

    private Integer pavilionId;

    private String pavilionCode;

    private String pavilionName;

    private String remark;

    private Date startdate;

    private Date enddate;

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

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName == null ? null : communityName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
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

    public String getPavilionName() {
        return pavilionName;
    }

    public void setPavilionName(String pavilionName) {
        this.pavilionName = pavilionName == null ? null : pavilionName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
}