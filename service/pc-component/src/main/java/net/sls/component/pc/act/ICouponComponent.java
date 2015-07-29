package net.sls.component.pc.act;

import java.util.List;
import java.util.Map;

import net.sls.dto.pc.act.Coupon;
import net.sls.dto.pc.act.UserCoupon;
import framework.web.Pager;

public interface ICouponComponent {

	Pager<List<Map<Object, Object>>> selectUnUseCouponListsByUserId(
			Long userId, Integer start, Integer number);	
	
	void saveCoupon(Long userId);
	Pager<List<Map<Object, Object>>> selectUseCouponListsByUserId(
			Long userId, Integer start, Integer number);

	Pager<List<Map<Object, Object>>> selectExpireCouponListsByUserId(
			Long userId, Integer start, Integer number);

	List<UserCoupon> selectUnUseCouponsByUserId(Long userId);
	
	boolean selectCanUseCoupon(Long userId, Long userCouponId);

	Integer updateUserCouponByCode(Long id, String code);
	
	Coupon selectCouponByCode(String code);

	UserCoupon selectUnUseUserCouponByCode(String code);
}
