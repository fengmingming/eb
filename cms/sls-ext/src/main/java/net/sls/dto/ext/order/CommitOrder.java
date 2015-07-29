package net.sls.dto.ext.order;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.sls.dto.order.OrderDetail;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CommitOrder {

	public Long userId;
	public Integer provinceId;
	public Integer cityId;
	public Integer districtId;
	public Integer communityId;
	public Integer pavilionId;
	public String provinceName;
	public String cityName;
	public String districtName;
	public String communityName;
	public String pavilionName;
	public String mobile;
	public String postcode;
	public String remark;
	public String orderRemark;
	public List<OrderDetail> details;
	public Integer orderWay;
	private Integer deliveryType;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public String getPavilionName() {
		return pavilionName;
	}
	public void setPavilionName(String pavilionName) {
		this.pavilionName = pavilionName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrderRemark() {
		return orderRemark;
	}
	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}
	public List<OrderDetail> getDetails() {
		return details;
	}
	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}
	public Integer getOrderWay() {
		return orderWay;
	}
	public void setOrderWay(Integer orderWay) {
		this.orderWay = orderWay;
	}
	public Integer getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(Integer deliveryType) {
		this.deliveryType = deliveryType;
	}
	
}
