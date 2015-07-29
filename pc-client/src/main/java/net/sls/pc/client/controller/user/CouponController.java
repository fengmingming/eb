package net.sls.pc.client.controller.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sls.dto.pc.act.Buyer;
import net.sls.dto.pc.act.Coupon;
import net.sls.dto.pc.act.UserCoupon;
import net.sls.dto.pc.product.SettlementsDto;
import net.sls.dto.pc.user.User;
import net.sls.pc.client.util.Constants;
import net.sls.service.pc.act.ICouponService;
import net.sls.service.pc.product.IShopCartService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Controller
@RequestMapping("coupon")
public class CouponController {
	/**
	 * 未使用优惠劵
	 * @param request
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value="unUseCoupon.htm")
    @ResponseBody
    public ModelAndView getunUseCouponByUserId(HttpServletRequest request){
		ModelAndView view = new ModelAndView("coupon/my_couponunuse");
		ReqBo reqBo = new ReqBo(request);
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		reqBo.setParam("userId", user.getId());
	    int num = Constants.WALLETS_NUM_PAGE;
		reqBo.setParam("rows", num);//每页显示10条记录
		if (reqBo.getParam("page") == null || !reqBo.getParam("page").toString().matches("[1-9][0-9]*")) {
			reqBo.setParam("page", 1);
		}
		
	    ICouponService couponS = (ICouponService)FindServiceUtil.findRemoteService(Constants.SERVICE_URL_COUPON, ICouponService.class);
	    ResBo<Pager<List<Map<Object, Object>>>> resBo = couponS.selectUnUseCouponListsByUserId(reqBo);
	    Pager<List<Map<Object,Object>>> p = resBo.getResult();
		List<Map<Object,Object>> l = p.getEntry();
		long total_num = p.getTotal();
		long total_page = (total_num + num - 1) / num;
		view.addObject("unUseCouponList", l);
		view.addObject("page", reqBo.getParam("page"));
		view.addObject("total_page", total_page);
		view.addObject(Constants.MENU_ID, Constants.MENU_MY_COUPON);
	    return view;
	}
	
	/**
	 * 使用优惠劵
	 * @param request
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value="useCoupon.htm")
    @ResponseBody
    public ModelAndView getUseCouponByUserId(HttpServletRequest request){
		ModelAndView view = new ModelAndView("coupon/my_couponuse");
		ReqBo reqBo = new ReqBo(request);
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		reqBo.setParam("userId", user.getId());
	    int num = Constants.WALLETS_NUM_PAGE;
		reqBo.setParam("rows", num);//每页显示10条记录
		if (reqBo.getParam("page") == null || !reqBo.getParam("page").toString().matches("[1-9][0-9]*")) {
			reqBo.setParam("page", 1);
		}
		
	    ICouponService couponS = (ICouponService)FindServiceUtil.findRemoteService(Constants.SERVICE_URL_COUPON, ICouponService.class);
	    ResBo<Pager<List<Map<Object, Object>>>> resBo = couponS.selectUseCouponListsByUserId(reqBo);
	    Pager<List<Map<Object,Object>>> p = resBo.getResult();
		List<Map<Object,Object>> l = p.getEntry();
		long total_num = p.getTotal();
		long total_page = (total_num + num - 1) / num;
		view.addObject("useCouponList", l);
		view.addObject("page", reqBo.getParam("page"));
		view.addObject("total_page", total_page);
		view.addObject(Constants.MENU_ID, Constants.MENU_MY_COUPON);
	    return view;
	}
	/**
	 * 过期优惠劵
	 * @param request
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value="expireCoupon.htm")
    @ResponseBody
    public ModelAndView getExpireCouponByUserId(HttpServletRequest request){
		ModelAndView view = new ModelAndView("coupon/my_couponexpire");
		ReqBo reqBo = new ReqBo(request);
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		reqBo.setParam("userId", user.getId());
	    int num = Constants.WALLETS_NUM_PAGE;
		reqBo.setParam("rows", num);//每页显示10条记录
		if (reqBo.getParam("page") == null || !reqBo.getParam("page").toString().matches("[1-9][0-9]*")) {
			reqBo.setParam("page", 1);
		}
		
	    ICouponService couponS = (ICouponService)FindServiceUtil.findRemoteService(Constants.SERVICE_URL_COUPON, ICouponService.class);
	    ResBo<Pager<List<Map<Object, Object>>>> resBo = couponS.selectExpireCouponListsByUserId(reqBo);
	    Pager<List<Map<Object,Object>>> p = resBo.getResult();
		List<Map<Object,Object>> l = p.getEntry();
		long total_num = p.getTotal();
		long total_page = (total_num + num - 1) / num;
		view.addObject("expireCouponList", l);
		view.addObject("page", reqBo.getParam("page"));
		view.addObject("total_page", total_page);
		view.addObject(Constants.MENU_ID, Constants.MENU_MY_COUPON);
	    return view;
	}
	/**
	 * 普通用户领取优惠券
	 * @return 0 用户为亭子用户    1领取成功   2 优惠券码错误   3 领取失败  4优惠券已过期
	 */
	@RequestMapping(value="getCouponByCode.htm")
	@ResponseBody
	public Coupon getCouponByCode(HttpServletRequest request){
		ReqBo reqBo = new ReqBo(request);
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		Coupon coupon = null;
		if(user.getMemberType() == 2){
			return coupon;
		}else{
			reqBo.setParam("id", user.getId());
			ICouponService couponS = (ICouponService)FindServiceUtil.findRemoteService(Constants.SERVICE_URL_COUPON, ICouponService.class);
			coupon = couponS.selectCouponByCode(reqBo).getResult();
			if(coupon != null){
				int i = couponS.updateUserCouponByCode(reqBo).getResult();
				if(i != 1){
					coupon = null;
				}
			}
		}
		return coupon;
	}
	
	/**
	 * 普通用户在核对订单时领取优惠券，需要判断该优惠券在当前订单中是否可以使用
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getUserCouponByCode4Order.htm")
	@ResponseBody
	public ResBo<List<UserCoupon>> getUserCouponByCode4Order(HttpServletRequest request){
		ResBo<List<UserCoupon>> coupons = new ResBo<List<UserCoupon>>();
		coupons.setSuccess(false);
		coupons.setErrMsg("领取优惠券失败，优惠码错误或者已使用或者已过期！");
		ReqBo reqBo = new ReqBo(request);
		User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
		reqBo.setParam("id", user.getId());
		ICouponService ics = (ICouponService)FindServiceUtil.findRemoteService(Constants.SERVICE_URL_COUPON, ICouponService.class);
		ResBo<UserCoupon> couponRes = ics.selectUnUseUserCouponByCode(reqBo);
		if(couponRes.isSuccess() && couponRes.getResult() != null){
			int i = ics.updateUserCouponByCode(reqBo).getResult();
			if(i == 1){
				coupons.setErrMsg("领取优惠券成功，但优惠券不满足使用条件！");
				//判断优惠券是否满足使用条件
				Buyer buyer = new Buyer();
				buyer.setUserId(user.getId());
				buyer.setCreateUserId(user.getId());
				User orderOwner = (User)SessionUtil.get(Constants.SESSION_ORDER_OWNER);
				if(Constants.USER_TPYE_COMMON == user.getMemberType().intValue()){
					buyer.setIsCommonUser(Boolean.TRUE);
				}
				if(Constants.USER_TPYE_PAVILION == user.getMemberType().intValue() &&
						orderOwner != null){
					buyer.setUserId(orderOwner.getId());
					buyer.setIsCommonUser(Constants.USER_TPYE_COMMON == orderOwner.getMemberType().intValue());
				}
				buyer.setProvinceId(user.getProvince());
				buyer.setCityId(user.getCity());
				buyer.setDistrictId(user.getDistrict());
				buyer.setCommunityId(user.getCommunity());
				buyer.setPavilionId(user.getPavilionId());
				reqBo.setReqModel(buyer);			
				IShopCartService ss = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_SHOPCART, IShopCartService.class);
				ResBo<SettlementsDto> settleShopCart = ss.settleShopCart(reqBo);
				if(settleShopCart.isSuccess() && settleShopCart.getResult() != null){
					List<UserCoupon> couponList = settleShopCart.getResult().getCanUseCouponList();
					if(couponList != null && couponList.size() > 0){
						String code = request.getParameter("code");
						for(UserCoupon userCoupon : couponList){
							if(userCoupon.getCode().equals(code)){
								coupons.setSuccess(true);
								coupons.setErrMsg("");
								break;
							}
						}
						coupons.setResult(couponList);
					}
				}
			}
		}
		return coupons;
	}
}