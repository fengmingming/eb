package net.sls.component.pc.act.impl;

import java.util.ArrayList;
import java.util.List;

import net.sls.component.pc.act.IActivityGoodsComponent;
import net.sls.component.pc.act.IHandler;
import net.sls.dto.pc.act.AbstractHandler;
import net.sls.dto.pc.act.ActivityGoods;
import net.sls.dto.pc.act.Buyer;
import net.sls.dto.pc.act.impl.GrouponTypeHandler;
import net.sls.dto.pc.shopcart.ProductDto;
import net.sls.dto.pc.shopcart.SSettleGood;
import util.framework.FindServiceUtil;
import util.model.ActEnum;

/**
 * 团购商品的处理
 * @author huzeyu 2015-03-17
 *
 */
public class GrouponHandler implements IHandler{
	private IActivityGoodsComponent ag = FindServiceUtil.findService(IActivityGoodsComponent.class);
	
	@Override
	public List<ProductDto> handler(Buyer buyer, List<Long> ids, List<SSettleGood> ssgs) {
		List<ProductDto> pds = new ArrayList<ProductDto>();
		Long userId = null;
		Boolean isCommonUser = null;
		if(buyer != null && buyer.isLogin()){
			userId = buyer.getUserId();
			isCommonUser = buyer.getIsCommonUser();
		}
		List<ActivityGoods> ags = ag.selectUserActivityGoods(userId, isCommonUser, getActType(), ids, null);
		if(ags != null && ags.size() > 0){
			for(ActivityGoods ag : ags){
				for(SSettleGood ssg : ssgs){
					if(ag.getGoodsId().longValue() == ssg.getId()){
						//限购数量
						if(ag.getNumber() > 0){
							ssg.setLimit(true);
							ssg.setActNumber(ag.getCanBuyNumber());
						}
						ssg.setGoodsName("【" + getActName() + "】" + ssg.getGoodsName());
						ssg.setPrice(ag.getActPrice());
						ProductDto pd = new ProductDto();
						pd.getSettleGoods().add(ssg);
						pd.setChecked(ssg.is_isSelect());
						pd.setNumber(ssg.get_number());
						List<AbstractHandler> actHandlers = new ArrayList<AbstractHandler>();
						actHandlers.add(new GrouponTypeHandler(ag.getActId()));
						pd.setActHandlers(actHandlers);
						pds.add(pd);
						//从列表中删除该商品（默认商品不参加其他活动）
						ssgs.remove(ssg);
						break;
					}
				}
			}
			for(ProductDto p:pds){
				p.setPayPrice(p.getAmount());
				p.setPoints(p.getAmount());
			}
		}
		return pds;
	}

	public int getActType() {
		return ActEnum.Groupon.getActType();
	}

	public String getActName() {
		return ActEnum.Groupon.getName();
	}
}
