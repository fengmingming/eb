package net.sls.service.pc.act;

import java.util.List;
import java.util.Map;

import net.sls.dto.pc.act.Coupon;
import net.sls.dto.pc.act.UserCoupon;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface ICouponService {
	/**
	 * 当前用户未使用优惠劵
	 * @param reqBo
	 * @return
	 */
	public ResBo<Pager<List<Map<Object,Object>>>> selectUnUseCouponListsByUserId(ReqBo reqBo);

	/**
	 * 注册用户领取优惠券
	 * @param reqBo
	 */
	void saveCoupon(ReqBo reqBo);

	/**
	 * 当前用户使用优惠劵
	 * @param reqBo
	 * @return
	 */
	public ResBo<Pager<List<Map<Object, Object>>>> selectUseCouponListsByUserId(
			ReqBo reqBo);
	/**
	 * 当前用户过期优惠劵
	 * @param reqBo
	 * @return
	 */
	public ResBo<Pager<List<Map<Object, Object>>>> selectExpireCouponListsByUserId(
			ReqBo reqBo);
	
	/**
	 * 普通用户获取优惠券
	 * @param reqBo
	 * @return
	 */
	public ResBo<Integer> updateUserCouponByCode(ReqBo reqBo);
	
	/**
	 * 根据code获得优惠券
	 * @param reqBo
	 * @return
	 */
	public ResBo<Coupon> selectCouponByCode(ReqBo reqBo);

	/**
	 * 根据code获得未使用的优惠券
	 * @param reqBo
	 * @return
	 */
	public ResBo<UserCoupon> selectUnUseUserCouponByCode(ReqBo reqBo);
}
