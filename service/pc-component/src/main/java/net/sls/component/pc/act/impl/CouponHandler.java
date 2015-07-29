package net.sls.component.pc.act.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sls.component.pc.act.ICouponComponent;
import net.sls.component.pc.act.IHandler;
import net.sls.component.pc.product.ICategoryComponent;
import net.sls.component.pc.product.IGoodsComponent;
import net.sls.dto.pc.act.Buyer;
import net.sls.dto.pc.act.UserCoupon;
import net.sls.dto.pc.product.Category;
import net.sls.dto.pc.shopcart.ProductDto;
import net.sls.dto.pc.shopcart.SSettleGood;
import util.framework.FindServiceUtil;
import util.model.ActEnum;

/**
 * 优惠券处理，包括优惠券的使用和赠送
 * @author huzeyu 2015-05-21
 *
 */
public class CouponHandler implements IHandler {
	private ICouponComponent cc = FindServiceUtil.findService(ICouponComponent.class);
	private IGoodsComponent gc = FindServiceUtil.findService(IGoodsComponent.class);
	private ICategoryComponent cac = FindServiceUtil.findService(ICategoryComponent.class);
	
	@Override
	public List<ProductDto> handler(Buyer buyer, List<Long> ids, List<SSettleGood> ssgs) {
		//得到当前用户未使用的优惠券
		List<UserCoupon> canUsecoupons = cc.selectUnUseCouponsByUserId(buyer.getUserId());
		if(canUsecoupons != null && canUsecoupons.size() > 0){
			Map<Long, SSettleGood> ssgMap = new HashMap<Long, SSettleGood>();
			for(int index = 0; index < ssgs.size(); index++){
				SSettleGood ssg = ssgs.get(index);
				ssgMap.put(ssg.getId(), ssg);
			}
			for(UserCoupon coupon : canUsecoupons){
				if(coupon.getLimitCat() != null){
					List<Long> categoryIds = new ArrayList<Long>();
					categoryIds.add(coupon.getLimitCat());
					for(int index = 0; index < categoryIds.size(); index++){
						Long categoryId = categoryIds.get(index);
						List<Category> children = cac.selectCategoryList(categoryId);
						if(children != null && children.size() > 0){
							for(Category child : children){
								categoryIds.add(child.getId());
							}
						}
					}
					List<Long> goodsIds = gc.selectCategoryGoods4Coupon(ids, categoryIds);
					for(Long goodsId : goodsIds){
						ssgMap.get(goodsId).getCanUseCouponList().add(coupon);
					}
				}else{
					for(int index = 0; index < ssgs.size(); index++){
						ssgs.get(index).getCanUseCouponList().add(coupon);
					}
				}
			}
		}
		
		return new ArrayList<ProductDto>();
	}

	@Override
	public int getActType() {
		return ActEnum.Coupon.getActType();
	}

	@Override
	public String getActName() {
		return ActEnum.Coupon.getName();
	}
}
