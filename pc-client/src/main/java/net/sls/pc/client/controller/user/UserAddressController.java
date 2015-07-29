package net.sls.pc.client.controller.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sls.dto.pc.user.User;
import net.sls.dto.pc.user.UserAddress;
import net.sls.pc.client.annotation.NoNeedUserLogin;
import net.sls.pc.client.controller.vo.UserAddressVo;
import net.sls.pc.client.util.Constants;
import net.sls.service.pc.product.IPavilionInfoService;
import net.sls.service.pc.user.IUserAddressService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * 用户地址
 * @author sls-28
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
					UserAddressVo addressVo = convertUserAddressToVo(reqBo, address);
					addressRes.setResult(addressVo);
					addressRes.setSuccess(true);
					break;
				}
			}
		}
		return addressRes;
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
		if(address.getId() != null &&address.getId().intValue() < 0){
			address.setId(null);
		}
        ReqBo reqBo = new ReqBo(request);
        reqBo.setReqModel(address);
        return saveOrUpdateUsrAddr(reqBo);
	}


    /**
     * 亭子用户设置buyer的默认地址
     * @param request
     * @param response
     * @param address
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "updateDftAddrByAgent.htm")
    @ResponseBody
    public ResBo<UserAddressVo> saveOrUpdateUsrAddr(HttpServletRequest request, HttpServletResponse response,
                                                        @ModelAttribute() UserAddress address) throws IOException{
        address.setUserId(((User)SessionUtil.get(Constants.SESSION_ORDER_OWNER)).getId());
        ReqBo reqBo = new ReqBo(request);
        reqBo.setReqModel(address);
        return saveOrUpdateUsrAddr(reqBo);
    }

    private ResBo<UserAddressVo> saveOrUpdateUsrAddr(ReqBo rb){
        UserAddress ua = rb.getReqModel(UserAddress.class);
        ua.setCreateTime(new Date());
        ua.setIsdel(new Integer(1));
        IUserAddressService is = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USERADDRESS, IUserAddressService.class);
        ResBo<UserAddress> reqAddress = null;
        ResBo<UserAddressVo> reqAddressVo = new ResBo<UserAddressVo>();
        reqAddressVo.setSuccess(false);
        if(ua.getId() != null){
            reqAddress = is.updateUserAddress(rb);
        }else{
            reqAddress = is.insertUserAddress(rb);
        }
        if(reqAddress.isSuccess() && reqAddress.getResult() != null){
            UserAddressVo addressVo = convertUserAddressToVo(rb, reqAddress.getResult());
            reqAddressVo.setResult(addressVo);
            reqAddressVo.setSuccess(true);
        }
        return reqAddressVo;
    }

	
	/**
	 * 设置为默认地址
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "setDefaultAddress.htm")
	@ResponseBody
	public ResBo<UserAddressVo> setDefaultAddress(HttpServletRequest request, Long id){
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("userId", (Long)SessionUtil.get(Constants.SESSION_USER_ID));
		reqBo.setParam("id", id);
        return setAddrDefult(reqBo);
	}

    /**
     * 亭子用户设置用户的默认地址
     * @param req
     * @param id
     * @return
     */
    @RequestMapping(value = "setDftAddrByAgent.htm")
    @ResponseBody
    public ResBo<UserAddressVo> setAddrDefaultByAgent(HttpServletRequest req,Long addrId){
        ReqBo rb = new ReqBo(req);
        rb.setParam("userId",((User)SessionUtil.get(Constants.SESSION_ORDER_OWNER)).getId());
        rb.setParam("id",addrId);
        return setAddrDefult(rb);
    }


    private  ResBo<UserAddressVo> setAddrDefult(ReqBo rb){
        IUserAddressService as = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USERADDRESS, IUserAddressService.class);
        ResBo<UserAddress> reqAddress = as.updateDefaultUserAddress(rb);
        ResBo<UserAddressVo> reqAddressVo = new ResBo<UserAddressVo>();
        reqAddressVo.setSuccess(false);
        if(reqAddress.isSuccess() && reqAddress.getResult() != null){
            UserAddressVo addressVo = convertUserAddressToVo(rb, reqAddress.getResult());
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
	public void deleteAddress(Long id, HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("id", id);
		IUserAddressService as = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USERADDRESS, IUserAddressService.class);
		if(as.deleteUserAddressById(reqBo).isSuccess()){
			response.getWriter().print("success");
		}else{
			response.getWriter().print("fail");
		}
		response.setContentType("text/html;charset=UTF-8");
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
	 * 进入用户地址列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "toAddress.htm")
	@ResponseBody
	public ModelAndView toAddress(HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		ModelAndView view = new ModelAndView("user/user_address");
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("userId", (Long)SessionUtil.get(Constants.SESSION_USER_ID));
		reqBo.setParam("page", 1);
		reqBo.setParam("rows", 3);
		IUserAddressService as = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USERADDRESS, IUserAddressService.class);
		ResBo<Pager<List<UserAddress>>> reqAddress = as.selectUserAddressListsByUserId(reqBo);
		ResBo<List<UserAddressVo>> addressRes = new ResBo<List<UserAddressVo>>();
		List<UserAddressVo> addresses = new ArrayList<UserAddressVo>();
		addressRes.setSuccess(false);
		if(reqAddress.isSuccess() && reqAddress.getResult() != null){
			for(UserAddress address : reqAddress.getResult().getEntry()){
				addresses.add(convertUserAddressToVo(reqBo, address));
			}
			addressRes.setResult(addresses);
			addressRes.setSuccess(true);
		}
		view.addObject("addresses", addressRes);
		view.addObject(Constants.MENU_ID, Constants.MENU_USER_ADDRESS);
		return view;
	}
	
	/**
	 * @author wangguojun
	 * @description: 根据亭子id查询亭子信息
	 * @param request
	 * @param response
	 * @return ResBo<Map<Object,Object>>
	 * @date 2015年1月19日 上午10:03:16
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "getPavilionInfoById.htm")
	@ResponseBody
	public ResBo<Map<Object,Object>> getPavilionInfoById(HttpServletRequest request,
			HttpServletResponse response) {
		ReqBo reqBo = new ReqBo(request);
		IPavilionInfoService ps = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_PAVILION,IPavilionInfoService.class);
		return ps.getPavilionInfoById(reqBo);
	}
	
	/**
	 * 将地址信息转化为页面显示类
	 * @param reqBo
	 * @param address
	 * @return
	 */
	private UserAddressVo convertUserAddressToVo(ReqBo reqBo, UserAddress address) {
		UserAddressVo addressVo = new UserAddressVo();
		addressVo.setId(address.getId());
		addressVo.setReceiver(address.getReceiver());
		addressVo.setMobile(address.getMobile());
		addressVo.setPhone(address.getPhone());
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
