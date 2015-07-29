package net.sls.dao.pc.activity;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PCCouponMapper {
	/**
	 * 注册查询可用优惠券
	 * @return
	 */
	public Map<Object, Object> selectRegisterCoupon();
	/**
	 * 如果优惠券限制数量则更改优惠券个数（注册）
	 * @param id
	 * @return
	 */
	public int updateCouponById(@Param("id") long id);
}
