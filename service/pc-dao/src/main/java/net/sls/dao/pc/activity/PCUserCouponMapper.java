package net.sls.dao.pc.activity;

import java.util.List;
import java.util.Map;

import net.sls.dto.pc.act.Coupon;
import net.sls.dto.pc.act.UserCoupon;

import org.apache.ibatis.annotations.Param;

public interface PCUserCouponMapper {

	long countUnUseCouponListsByUserId(@Param("usrId") Long usrId);

	List<Map<Object, Object>> selectUnUseCouponListsByUserId(@Param("usrId") Long usrId,
			@Param("start") Integer start,@Param("number") Integer number);

    int insertUserCoupon(@Param("couponId") Long couponId, @Param("usrId") Long usrId);

	long countUseCouponListsByUserId(@Param("usrId") Long usrId);

	List<Map<Object, Object>> selectUseCouponListsByUserId(@Param("usrId") Long usrId,
			@Param("start") Integer start,@Param("number") Integer number);

	long countExpireCouponListsByUserId(@Param("usrId") Long usrId);

	List<Map<Object, Object>> selectExpireCouponListsByUserId(@Param("usrId") Long usrId,
			@Param("start") Integer start,@Param("number") Integer number);

	List<UserCoupon> selectUnUseCoupons(@Param("id")Long id, @Param("usrId")Long userId);
	
	UserCoupon selectUnUseCouponByCode(@Param("code") String code);
	
	int updateUserCouponById(@Param("id")Long id, @Param("orderId")Long orderId, @Param("orderNum")String orderNum);
	
	int updateUserCouponByOrderId(@Param("orderId")Long orderId);

	int updateUserCouponByCode(@Param("id") Long id, @Param("code") String code);
	
	Coupon selectCouponByCode(@Param("code") String code);
}