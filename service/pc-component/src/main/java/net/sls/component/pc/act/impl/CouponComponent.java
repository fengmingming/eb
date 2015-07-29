package net.sls.component.pc.act.impl;

import java.util.List;
import java.util.Map;

import net.sls.component.pc.act.ICouponComponent;
import net.sls.dao.pc.activity.PCCouponMapper;
import net.sls.dao.pc.activity.PCUserCouponMapper;
import net.sls.dto.pc.act.Coupon;
import net.sls.dto.pc.act.UserCoupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.exception.BusinessException;
import framework.web.Pager;

@Component
public class CouponComponent implements ICouponComponent{
	@Autowired
	private PCUserCouponMapper uCouponMapper;
	@Autowired
	private PCCouponMapper couponMapper;
	
	@Override
	public Pager<List<Map<Object, Object>>> selectUnUseCouponListsByUserId(
			Long userId, Integer start, Integer number) {
		long count = uCouponMapper.countUnUseCouponListsByUserId(userId);
		List<Map<Object, Object>> list = uCouponMapper.selectUnUseCouponListsByUserId(userId, (start-1)*number, number);
		Pager<List<Map<Object, Object>>> pager = new Pager<List<Map<Object, Object>>>(list,count);
		return pager;
	}
	
	@Override
	public void saveCoupon(Long userId) {
		Map<Object, Object> map = couponMapper.selectRegisterCoupon();
		if(map != null && map.size() > 1){
			if(map.get("total").toString().equals("-1")){
				uCouponMapper.insertUserCoupon(Long.parseLong(map.get("id").toString()), userId);
			}else if(Integer.parseInt(map.get("total").toString()) > 0){
				int i = couponMapper.updateCouponById(Long.parseLong(map.get("id").toString()));
				if(i != 1){
					throw new BusinessException(9L);
				}else{
					int j = uCouponMapper.insertUserCoupon(Long.parseLong(map.get("id").toString()), userId);
					if(j != 1){
						throw new BusinessException(50L);
					}
				}
			}
		}
	}	
	
	@Override
	public Pager<List<Map<Object, Object>>> selectUseCouponListsByUserId(
			Long userId, Integer start, Integer number) {
		long count = uCouponMapper.countUseCouponListsByUserId(userId);
		List<Map<Object, Object>> list = uCouponMapper.selectUseCouponListsByUserId(userId, (start-1)*number, number);
		Pager<List<Map<Object, Object>>> pager = new Pager<List<Map<Object, Object>>>(list,count);
		return pager;
	}
	
	@Override
	public Pager<List<Map<Object, Object>>> selectExpireCouponListsByUserId(
			Long userId, Integer start, Integer number) {
		long count = uCouponMapper.countExpireCouponListsByUserId(userId);
		List<Map<Object, Object>> list = uCouponMapper.selectExpireCouponListsByUserId(userId, (start-1)*number, number);
		Pager<List<Map<Object, Object>>> pager = new Pager<List<Map<Object, Object>>>(list,count);
		return pager;
	}
	
	@Override
	public List<UserCoupon> selectUnUseCouponsByUserId(Long userId) {
		return uCouponMapper.selectUnUseCoupons(null, userId);
	}
	
	@Override
	public boolean selectCanUseCoupon(Long userCouponId, Long userId){
		List<UserCoupon> userCoupons = uCouponMapper.selectUnUseCoupons(userCouponId, userId);
		if(userCoupons != null && userCoupons.size() > 0){
			return true;
		}
		return false;
	}
	
	@Override
	public Integer updateUserCouponByCode(Long id, String code) {
		Integer i = uCouponMapper.updateUserCouponByCode(id, code);
		if(i != 1){
			throw new BusinessException(52L);
		}
		return i;
	}

	@Override
	public Coupon selectCouponByCode(String code) {
		return uCouponMapper.selectCouponByCode(code);
	}
	
	@Override
	public UserCoupon selectUnUseUserCouponByCode(String code) {
		return uCouponMapper.selectUnUseCouponByCode(code);
	}
}
