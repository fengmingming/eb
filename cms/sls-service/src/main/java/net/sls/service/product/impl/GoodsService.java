package net.sls.service.product.impl;


import java.util.List;
import java.util.Map;

import net.sls.businessconstant.BusinessContant;
import net.sls.component.product.IGoodsComponent;
import net.sls.dto.ext.product.GoodsAddBeanExt;
import net.sls.dto.product.Goods;
import net.sls.service.product.IGoodsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.framework.SessionUtil;
import util.framework.StrUtil;
import util.model.ComboxDto;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Service
public class GoodsService implements IGoodsService {

	@Autowired
	public IGoodsComponent goodsComponent;
	

	@Override
	public ResBo<?> insertGoods(ReqBo reqBo) {
		goodsComponent.insertGoods(reqBo.getReqModel(GoodsAddBeanExt.class));
		return new ResBo<Object>();
	}
	
	@Override
	public ResBo<?> updateAll(ReqBo reqBo){
		goodsComponent.updateAll(reqBo.getReqModel(GoodsAddBeanExt.class));
		return new ResBo<Object>();
	}

	@Override
	public ResBo<List<ComboxDto>> selectGoodsListByProvider(ReqBo reqBo) {
		return new ResBo<List<ComboxDto>>(goodsComponent.selectGoodsListByProvider(reqBo.getParamLong("providerId")));
	}

	@Override
	public ResBo<Pager<List<Goods>>> selectGoodsByCategoryId(ReqBo reqBo) {
		return new ResBo<Pager<List<Goods>>>
		(goodsComponent.selectGoodsByCategoryId(reqBo.getParamStr("areaCode"),reqBo.getParamLong("providerId"),reqBo.getParamLong("oneId"), reqBo.getParamLong("twoId"), reqBo.getParamLong("threeId"), reqBo.getParamInt("page")-1, reqBo.getParamInt("rows")));
	}

	@Override
	public ResBo<Pager<List<Map<String, Object>>>> selectGoodsByFilter(
			ReqBo reqBo) {
		return new ResBo<Pager<List<Map<String,Object>>>>(goodsComponent.selectGoodsByFilter(StrUtil.toString(SessionUtil.get(BusinessContant.OPERAREAID)),reqBo.getParamLong("goodsId"), reqBo.getParamStr("sku"), reqBo.getParamStr("name"), reqBo.getParamDecimal("price"), reqBo.getParamDecimal("price2"), reqBo.getParamInt("provider"),reqBo.getParamInt("isSale"), reqBo.getParamInt("page"),reqBo.getParamInt("rows")));
	}

	@Override
	public ResBo<?> updateGoodsById(ReqBo reqBo) {
		goodsComponent.updateGoodsById(reqBo.getReqModel(Goods.class));
		return new ResBo<Object>();
	}
	
	@Override
	public ResBo<Map<String, Object>> selectUpdateGoodsInfo(ReqBo reqBo) {
		return new ResBo<Map<String,Object>>(goodsComponent.selectUpdateGoodsInfo(reqBo.getParamLong("goodsId")));
	}

	@Override
	@SuppressWarnings("unchecked")
	public ResBo<?> batchGoodsIsSale(ReqBo reqBo) {
		List<Long> ids = reqBo.getParamT("ids", List.class);
		if(ids.size() > 0){
			goodsComponent.updateBatchGoodsIsSale(ids, reqBo.getParamInt("isSale"));
		}
		return new ResBo<Object>();
	}

	@Override
	public ResBo<?> flushBuyNum() {
		goodsComponent.updateFlushBuyNum();
		return new ResBo<Object>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResBo<?> batchUseCoupon(ReqBo reqBo) {
		goodsComponent.updateBatchUseCoupon(reqBo.getReqModel(List.class), reqBo.getParamBoolean("isUse"));
		return new ResBo<Object>();
	}
}
