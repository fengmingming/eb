package net.sls.mobile.client.controller.vo;

import java.util.Map;

import net.sls.dto.pc.user.UserAddress;
import net.sls.mobile.client.util.Constants;
import net.sls.service.pc.product.IPavilionInfoService;
import util.framework.FindServiceUtil;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * 用户地址的页面显示类
 * @author huzeyu 2015-04-14
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
	
	/**
	 * 将地址信息转化为页面显示类
	 * @param request
	 * @param address
	 * @return
	 */
	public static UserAddressVo convertUserAddressToVo(UserAddress address) {
		UserAddressVo addressVo = new UserAddressVo();
		addressVo.setId(address.getId());
		addressVo.setReceiver(address.getReceiver());
		addressVo.setMobile(address.getMobile());
		addressVo.setAddressDetail(address.getAddressDetail());
		addressVo.setProvince(address.getProvince());
		addressVo.setCity(address.getCity());
		addressVo.setProvince(address.getProvince());
		addressVo.setDistrict(address.getDistrict());
		addressVo.setCommunity(address.getCommunity());
		addressVo.setPavilionId(address.getPavilionId());
		addressVo.setPavilionCode(address.getPavilionCode());
		//得到亭子的信息
		if(address.getPavilionId() != null && address.getPavilionCode() != null){
			ReqBo reqBo = new ReqBo();
			reqBo.setParam("id", address.getPavilionId());
			IPavilionInfoService ps = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_PAVILION, IPavilionInfoService.class);
			ResBo<Map<Object,Object>> pavilion = ps.getPavilionInfoById(reqBo);
			if(pavilion.isSuccess() && pavilion.getResult() != null){
				addressVo.setPavilionName((String)pavilion.getResult().get("name"));
				addressVo.setPavilionPhone((String)pavilion.getResult().get("mobile"));
			}
		}else{
			addressVo.setPavilionName("");
			addressVo.setPavilionPhone("");
		}
		return addressVo;
	}
}
