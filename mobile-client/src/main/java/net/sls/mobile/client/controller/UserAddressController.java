package net.sls.mobile.client.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sls.dto.mobile.user.UserAddress;
import net.sls.mobile.client.controller.vo.UserAddressVo;
import net.sls.mobile.client.util.Constants;
import net.sls.service.mobile.product.IPavilionInfoService;
import net.sls.service.mobile.user.IUserAddressService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * 用户地址
 * @author huzeyu 2015-04-15
 *
 */
@Controller
@RequestMapping("address")
public class UserAddressController {
	/**
	 * 根据地址id得到地址信息
	 * @param request
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "getUserAddress.htm")
	@ResponseBody
	public ResBo<UserAddress> getUserAddress(HttpServletRequest request, Long addressId) throws IOException{
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("id", addressId);
		IUserAddressService is = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USERADDRESS, IUserAddressService.class);
		return is.getUserAddressById(reqBo);
	}
	
	/**
	 * 得到默认的地址信息
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "defaultAddress.htm")
	@ResponseBody
	public ResBo<UserAddressVo> getDefaultAddress(HttpServletRequest request, HttpServletResponse response) throws IOException{
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("userId", (Long)SessionUtil.get(Constants.SESSION_USER_ID));
		reqBo.setParam("page", 1);
		reqBo.setParam("rows", 3);
		IUserAddressService as = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USERADDRESS, IUserAddressService.class);
		ResBo<Pager<List<UserAddress>>> reqAddress = as.selectUserAddressListsByUserId(reqBo);
		ResBo<UserAddressVo> addressRes = new ResBo<UserAddressVo>();
		addressRes.setSuccess(false);
		if(reqAddress.getResult() != null){
			for(UserAddress address : reqAddress.getResult().getEntry()){
				if(address.getIsdefault()){
					UserAddressVo addressVo = UserAddressVo.convertUserAddressToVo(address);
					addressRes.setResult(addressVo);
					addressRes.setSuccess(true);
					break;
				}
			}
		}
		return addressRes;
	}
	
	/**
	 * 设置为默认地址
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "setDefaultAddress.htm")
	@ResponseBody
	public ResBo<UserAddressVo> setDefaultAddress(HttpServletRequest request,@RequestParam("id") Long id){
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("userId", (Long)SessionUtil.get(Constants.SESSION_USER_ID));
		reqBo.setParam("id", id);
		IUserAddressService as = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USERADDRESS, IUserAddressService.class);
		ResBo<UserAddress> reqAddress = as.updateDefaultUserAddress(reqBo);
		ResBo<UserAddressVo> reqAddressVo = new ResBo<UserAddressVo>();
		reqAddressVo.setSuccess(false);
		if(reqAddress.isSuccess() && reqAddress.getResult() != null){
			UserAddressVo addressVo = UserAddressVo.convertUserAddressToVo(reqAddress.getResult());
			reqAddressVo.setResult(addressVo);
			reqAddressVo.setSuccess(true);
		}
		return reqAddressVo;
	}
	
	/**
	 * 更新或者新建地址信息
	 * @param request
	 * @param response
	 * @param address
	 * @throws IOException
	 */
	@RequestMapping(value = "saveOrUpdateAddress.htm")
	@ResponseBody
	public ResBo<UserAddressVo> saveOrUpdateUserAddress(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute() UserAddress address) throws IOException{
		address.setUserId((Long)SessionUtil.get(Constants.SESSION_USER_ID));
		address.setIsdefault(true);
		address.setCreateTime(new Date());
		address.setPhone(""); //数据库设计问题，目前phone不能为空
		address.setIsdel(new Integer(1));
		address.setReceiver(address.getReceiver());
		address.setAddressDetail(address.getAddressDetail());
		ReqBo reqBo = new ReqBo(request);
		reqBo.setReqModel(address);
		IUserAddressService is = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USERADDRESS, IUserAddressService.class);
		ResBo<UserAddress> reqAddress = null;
		ResBo<UserAddressVo> reqAddressVo = new ResBo<UserAddressVo>();
		reqAddressVo.setSuccess(false);
		if (null == address.getCommunity()) {
			reqAddressVo.setErrMsg("商圈不能为空");
			return reqAddressVo;
		}
		if (null == address.getPavilionId()) {
			reqAddressVo.setErrMsg("亭子不能为空");
			return reqAddressVo;
		}
		if(address.getId() != null){
			reqAddress = is.updateUserAddress(reqBo);
		}else{
			reqAddress = is.insertUserAddress(reqBo);
		}
		if(reqAddress.isSuccess() && reqAddress.getResult() != null){
			UserAddressVo addressVo = UserAddressVo.convertUserAddressToVo(reqAddress.getResult());
			reqAddressVo.setResult(addressVo);
			reqAddressVo.setSuccess(true);
		}
		return reqAddressVo;
	}
	
	/**
	 * 删除地址
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "deleteAddress.htm")
	@ResponseBody
	public ResBo<Long> deleteAddress(Long id, HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("id", id);
		reqBo.setParam("page", 1);
		reqBo.setParam("rows", 3);
		reqBo.setParam("userId", (Long)SessionUtil.get(Constants.SESSION_USER_ID));
		ResBo<Long> resBo = new ResBo<Long>();
		IUserAddressService as = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USERADDRESS, IUserAddressService.class);
		if(as.deleteUserAddressById(reqBo).isSuccess()){
			ResBo<Pager<List<UserAddress>>> reqAddress = as.selectUserAddressListsByUserId(reqBo);
			if(reqAddress.getResult() != null){
				for(UserAddress address : reqAddress.getResult().getEntry()){
					if(address.getIsdefault()){
						resBo.setResult(-1l);
						resBo.setSuccess(true);
						return resBo;
					}
				}
				resBo.setResult(reqAddress.getResult().getTotal());
				resBo.setSuccess(true);
			}
		}else{
			resBo.setSuccess(false);
		}
		return resBo;
	}
	
	/**
	 * 是否可以添加地址（目前只支持添加3个地址）
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "canAddAddress.htm")
	@ResponseBody
	public void canAddAddress(HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("userId", (Long)SessionUtil.get(Constants.SESSION_USER_ID));
		reqBo.setParam("page", 1);
		reqBo.setParam("rows", 3);
		IUserAddressService as = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USERADDRESS, IUserAddressService.class);
		ResBo<Pager<List<UserAddress>>> reqAddress = as.selectUserAddressListsByUserId(reqBo);
		if(reqAddress.getResult().getTotal() >= 3){
			response.getWriter().print("fail");
		}else{
			response.getWriter().print("success");
		}
		response.setContentType("text/html;charset=UTF-8");
	}
	
	/**
	 * @description: 根据亭子id查询亭子周边
	 * @param request
	 * @param response
	 * @return ResBo<Map<Object,Object>>
	 * @date 2015年1月19日 上午10:03:16
	 */
	@RequestMapping(value = "getpavilionAreaById.htm")
	@ResponseBody
	public ResBo<List<Map<Object,Object>>> getpavilionAreaById(HttpServletRequest request,
			HttpServletResponse response) {
		ReqBo reqBo = new ReqBo(request);
		IPavilionInfoService ps = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_PAVILION,IPavilionInfoService.class);
		return ps.getpavilionAreaById(reqBo);
	}
}
