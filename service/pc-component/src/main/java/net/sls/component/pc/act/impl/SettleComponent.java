package net.sls.component.pc.act.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sls.component.pc.act.IHandler;
import net.sls.component.pc.act.ISettleComponent;
import net.sls.dao.pc.product.PCGoodsAreaMapper;
import net.sls.dao.pc.product.PCGoodsMapper;
import net.sls.dto.pc.act.Buyer;
import net.sls.dto.pc.product.SmallGoodsArea;
import net.sls.dto.pc.shopcart.CCartGood;
import net.sls.dto.pc.shopcart.Cart;
import net.sls.dto.pc.shopcart.CartGood;
import net.sls.dto.pc.shopcart.SSettleGood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SettleComponent implements ISettleComponent {

	@Autowired
	private PCGoodsMapper gm;
	
	@Autowired
	private PCGoodsAreaMapper ga;

	@Override
	public List<IHandler> selectActHandler(List<Cart> carts, Buyer buyer,List<Long> ids) {
		List<IHandler> handlers = new ArrayList<IHandler>();
		//优惠券(普通用户可以使用和赠送优惠券)
		if(buyer != null && buyer.isLogin() && buyer.getIsCommonUser()){
			handlers.add(new CouponHandler());
		}
		//团购
		handlers.add(new GrouponHandler());
		//限时抢购
		handlers.add(new FlashSaleHandler());
		return handlers;
	}

	@Override
	public List<SSettleGood> selectSettleGood(List<Cart> carts, Buyer buyer,
			List<Long> ids) {
		List<SSettleGood> ssgs = new ArrayList<SSettleGood>();
		if (carts != null && carts.size() > 0) {
			List<Long> gls = new ArrayList<Long>();
			List<CartGood> cgs = new ArrayList<CartGood>();
			for (Cart cart : carts) {
				for (CartGood cg : cart.getCartGoods()) {
					gls.add(cg.getId());
					cgs.add(cg);
				}
			}
			if (ids != null) {
				ids.addAll(gls);
			}
			List<SSettleGood> ssgList = gm.selectSSettleGoods(gls);
			Map<Long, SSettleGood> ssgsMap = new HashMap<Long, SSettleGood>();
			for (SSettleGood ssg : ssgList) {
				ssgsMap.put(ssg.getId(), ssg);
			}
			if (buyer != null && buyer.isLogin()) {
				List<Long> provinces = new ArrayList<Long>();
				List<Long> citys = new ArrayList<Long>();
				List<Long> districts = new ArrayList<Long>();
				List<Long> communitys = new ArrayList<Long>();
				List<Long> pavilions = new ArrayList<Long>();
				for(SSettleGood ssg:ssgList){
					switch(ssg.getLimittype()){
					case 1:ssg.set_isJudgeAreaStcok(true);provinces.add(ssg.getId());break;
					case 2:ssg.set_isJudgeAreaStcok(true);citys.add(ssg.getId());break;
					case 3:ssg.set_isJudgeAreaStcok(true);districts.add(ssg.getId());break;
					case 4:ssg.set_isJudgeAreaStcok(true);communitys.add(ssg.getId());break;
					case 5:ssg.set_isJudgeAreaStcok(true);pavilions.add(ssg.getId());break;
					}
				}
				Map<String,Object> map = new HashMap<String, Object>();
				if(provinces.size() > 0 && buyer.getProvinceId() != null){
					map.put("provinceIds", provinces);
					map.put("provinceId", buyer.getProvinceId());
				}
				if(citys.size() > 0 && buyer.getCityId() != null){
					map.put("cityIds", citys);
					map.put("cityId", buyer.getCityId());
				}
				if(districts.size() > 0 && buyer.getDistrictId() != null){
					map.put("districtIds", districts);
					map.put("districtId", buyer.getDistrictId());
				}
				if(communitys.size() > 0 && buyer.getCommunityId() != null){
					map.put("communityIds", communitys);
					map.put("communityId", buyer.getCommunityId());
				}
				if(pavilions.size() > 0 && buyer.getPavilionId()!= null){
					map.put("pavilionIds", pavilions);
					map.put("pavilionId", buyer.getPavilionId());
				}
				if(map.size() > 0){
					List<SmallGoodsArea> sgaList = ga.selectSmallGoodsAreas(map);
					if(sgaList.size() > 0){
						Map<Long,SmallGoodsArea> smallMap = new HashMap<Long, SmallGoodsArea>();
						for(SmallGoodsArea sga:sgaList){
							smallMap.put(sga.getGoodsId(), sga);
						}
						SSettleGood ssg = null;
						SmallGoodsArea sga = null;
						for(Long l:ssgsMap.keySet()){
							ssg = ssgsMap.get(l);
							sga = smallMap.get(l);
							if(ssg!=null&&sga!=null){
								ssg.set_goodAreaId(sga.getId());
								ssg.setLimit(sga.getIsLimit());
								ssg.setLimitNumber(sga.getNumber());
							}
						}
					}
				}
			}
			
			SSettleGood ssg = null;
			CCartGood ccg = null;
			for (int i = 0, j = gls.size(); i < j; i++) {
				ssg = ssgsMap.get(gls.get(i));
				ccg = (CCartGood) cgs.get(i);
				if (ssg != null) {
					ssg.set_isSelect(ccg.is_isSelect());
					ssg.set_number(ccg.get_number());
					ssg.setEffPay(ssg.getPrice());
					ssgs.add(ssg);
				}
			}
		}
		return ssgs;
	}

}
