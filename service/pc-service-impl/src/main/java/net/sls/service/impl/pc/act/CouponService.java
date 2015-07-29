package net.sls.service.impl.pc.act;

import java.util.List;
import java.util.Map;

import net.sls.component.pc.act.ICouponComponent;
import net.sls.dto.pc.act.Coupon;
import net.sls.dto.pc.act.UserCoupon;
import net.sls.service.pc.act.ICouponService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Service("couponService")
public class CouponService implements ICouponService{
	@Autowired
	private ICouponComponent couponCom;
	@Override
	public ResBo<Pager<List<Map<Object,Object>>>> selectUnUseCouponListsByUserId(ReqBo reqBo) {
		return new ResBo<Pager<List<Map<Object,Object>>>>(couponCom.selectUnUseCouponListsByUserId(reqBo.getParamLong("userId"),reqBo.getParamInt("page"),reqBo.getParamInt("rows")));
	}
	
	@Override
	public void saveCoupon(ReqBo reqBo){
		couponCom.saveCoupon(reqBo.getParamLong("userId"));
	}
	@Override
	public ResBo<Pager<List<Map<Object, Object>>>> selectUseCouponListsByUserId(
			ReqBo reqBo) {
		return new ResBo<Pager<List<Map<Object,Object>>>>(couponCom.selectUseCouponListsByUserId(reqBo.getParamLong("userId"),reqBo.getParamInt("page"),reqBo.getParamInt("rows")));
	}
	@Override
	public ResBo<Pager<List<Map<Object, Object>>>> selectExpireCouponListsByUserId(
			ReqBo reqBo) {
		return new ResBo<Pager<List<Map<Object,Object>>>>(couponCom.selectExpireCouponListsByUserId(reqBo.getParamLong("userId"),reqBo.getParamInt("page"),reqBo.getParamInt("rows")));
	}

	@Override
	public ResBo<Integer> updateUserCouponByCode(ReqBo reqBo) {
		return new ResBo<Integer>(couponCom.updateUserCouponByCode(reqBo.getParamLong("id"), reqBo.getParamStr("code")));
	}

	@Override
	public ResBo<Coupon> selectCouponByCode(ReqBo reqBo) {
		return new ResBo<Coupon>(couponCom.selectCouponByCode(reqBo.getParamStr("code")));
	}
	
	@Override
	public ResBo<UserCoupon> selectUnUseUserCouponByCode(ReqBo reqBo){
		return new ResBo<UserCoupon>(couponCom.selectUnUseUserCouponByCode(reqBo.getParamStr("code")));
	}
}
