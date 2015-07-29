package net.sls.mobile.client.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sls.dto.mobile.act.Buyer;
import net.sls.dto.mobile.order.ResOrder;
import net.sls.dto.mobile.user.User;
import net.sls.dto.mobile.user.UserAddress;
import net.sls.mobile.client.annotation.NoNeedUserLogin;
import net.sls.mobile.client.util.Constants;
import net.sls.service.mobile.order.IOrderService;
import net.sls.service.mobile.user.IUserAddressService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import util.model.PayEnum;

import com.wx.config.WxPayConfig;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * @author dyh 订单信息
 *
 */
@Controller
@RequestMapping("order")
public class OrderController {
	/**
	 * 提交订单
	 * @param request
	 * @param payType
	 * @param deliveryType
	 * @param payPassword
	 * @return
	 */
	@RequestMapping(value = "commitOrder.htm")
	@ResponseBody
	public ResBo<ResOrder> commitOrder(HttpServletRequest request, 
			@ModelAttribute("buyer")Buyer buyer){
		ReqBo reqBo = new ReqBo(request);
		//用户信息
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		buyer.setCreateUserId(user.getId());
		buyer.setUserId(user.getId()); //订单用户为当前的登录人
		buyer.setChannelId(2);
		//默认地址信息
		reqBo.setParam("userId", user.getId());
		reqBo.setParam("page", 1);
		reqBo.setParam("rows", 3);
		IUserAddressService us = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_USERADDRESS, IUserAddressService.class);
		ResBo<Pager<List<UserAddress>>> reqAddress = us.selectUserAddressListsByUserId(reqBo);
		if(reqAddress.isSuccess() && reqAddress.getResult() != null){
			for(UserAddress address : reqAddress.getResult().getEntry()){
				if(address.getIsdefault()){
					buyer.setAddressId(address.getId());
					buyer.setProvinceId(address.getProvince());
					buyer.setCityId(address.getCity());
					buyer.setDistrictId(address.getDistrict());
					buyer.setCommunityId(address.getCommunity());
					buyer.setPavilionId(address.getPavilionId());
					buyer.setMobile(address.getMobile());
					buyer.setPhone(address.getPhone());
					buyer.setReceiver(address.getReceiver());
					buyer.setRemark(address.getAddressDetail());
					buyer.setIsCommonUser(Boolean.TRUE);
					break;
				}
			}
		}
		
		if(buyer.getPayType().intValue() == PayEnum.Balance.getId()){
			buyer.setBalance(true);
		}
		reqBo.setReqModel(buyer);
		IOrderService os = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_ORDER, IOrderService.class);
		ResBo<ResOrder> rb = os.insertOrderCommit(reqBo);
		//微信支付的话，将appid放入cart字段传到前台
		if(request.getParameter("payType").equals("4")){
			rb.getResult().setCart(WxPayConfig.app_id);
		}
		return rb;
	}
	
	/**
	 * 跳转到支付结果页面
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "paymentResult.htm")
	public ModelAndView paymentResult(@RequestParam("orderId")String orderId, @RequestParam("orderNum")String orderNum){
		ModelAndView view = new ModelAndView("order/order_payment_result");
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		if(user.getMemberType() == Constants.USER_TPYE_COMMON){
			view.addObject("order_type", Constants.MENU_MY_ORDER);
		}else{
			view.addObject("order_type", Constants.MENU_DG_ORDER);
		}
		view.addObject("orderId", orderId);
		view.addObject("orderNum", orderNum);
		return view;
	}
	
	//订单中心页
	@NoNeedUserLogin
	@RequestMapping(value = "order_center.htm")
	public ModelAndView orderCenter(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("order/order_center");
		return view;
	}
	
	//订单详情页
	@NoNeedUserLogin
	@RequestMapping(value = "order_detail.htm")
	public ModelAndView orderDetail(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("order/order_detail");
		return view;
	}
}
