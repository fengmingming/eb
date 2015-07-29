package net.sls.pc.client.controller.vo;

/**
 * 用户地址的页面显示类
 * @author huzeyu 2015-01-12
 *
 */
public class UserAddressVo {
		private Long id;
	    private Integer province;
	    private Integer city;
	    private Integer district;
	    private Integer community;
	    private String addressDetail;
	    private String mobile;
	    private String phone;
	    private String receiver;
	    private Integer pavilionId;
	    private String pavilionCode;
	    private String pavilionName;
	    private String pavilionPhone;
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
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
			this.addressDetail = addressDetail;
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
			this.pavilionCode = pavilionCode;
		}
		public String getPavilionName() {
			return pavilionName;
		}
		public void setPavilionName(String pavilionName) {
			this.pavilionName = pavilionName;
		}
		public String getPavilionPhone() {
			return pavilionPhone;
		}
		public void setPavilionPhone(String pavilionPhone) {
			this.pavilionPhone = pavilionPhone;
		}
}
