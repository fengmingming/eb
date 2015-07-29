package net.sls.dto.pc.product;

import java.io.Serializable;
import java.util.Date;

public class PavilionInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	
	private String pavilionCode;

    private String pavilionSn;

    private Integer province;

    private Integer city;

    private Integer district;

    private Integer community;

    private String coordinate;

    private String mobile;

    private String email;

    private String name;

    private Integer sex;

    private String cartId;

    private String detailHome;

    private Date createTime;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPavilionCode() {
		return pavilionCode;
	}

	public void setPavilionCode(String pavilionCode) {
		this.pavilionCode = pavilionCode;
	}

	public String getPavilionSn() {
		return pavilionSn;
	}

	public void setPavilionSn(String pavilionSn) {
		this.pavilionSn = pavilionSn;
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

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getDetailHome() {
		return detailHome;
	}

	public void setDetailHome(String detailHome) {
		this.detailHome = detailHome;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	private Date modifyTime;
	
}
