package net.sls.service.product.impl;

import java.util.List;

import net.sls.component.product.IGoodsCategoryComponent;
import net.sls.dto.product.GoodsCategory;
import net.sls.service.product.IGoodsCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Service
public class GoodsCategoryService implements IGoodsCategoryService {

	@Autowired
	private IGoodsCategoryComponent goodsCategoryComponent;
	
	@Override
	public ResBo<Object> insertGoodsCategory(ReqBo reqBo) {
		GoodsCategory goodsCategory = reqBo.getReqModel(GoodsCategory.class);
		goodsCategoryComponent.insertGoodsCategory(goodsCategory);
		return new ResBo<Object>();
	}

	@Override
	public void deleteGoodsCategory(ReqBo reqBo) {
		goodsCategoryComponent.deleteGoodsCategory(reqBo.getParamLong("goodsCategoryId"));
		
	}

	@Override
	public ResBo<GoodsCategory> updateGoodsCategory(ReqBo reqBo) {
		goodsCategoryComponent.updateGoodsCategory(reqBo.getReqModel(GoodsCategory.class));
		return new ResBo<GoodsCategory>();
	}

	@Override
	public ResBo<GoodsCategory> selectGoodsCategoryById(ReqBo reqBo) {
		GoodsCategory goodsCategory = goodsCategoryComponent.selectByPrimaryKey(reqBo.getParamLong("goodsCategoryId"));
		ResBo<GoodsCategory> resBo = new ResBo<GoodsCategory>(goodsCategory);
		return resBo;
	}

	@Override
	public ResBo<Pager<List<GoodsCategory>>> selectGoodsCategorys(ReqBo reqBo) {
		return new ResBo<Pager<List<GoodsCategory>>>(goodsCategoryComponent.selectGoodsCategorys(
				reqBo.getParamLong("oneId"), reqBo.getParamLong("twoId"),reqBo.getParamLong("threeId"), reqBo.getParamLong("goodsId"),
				reqBo.getParamInt("page")-1, reqBo.getParamInt("rows")));
	}

	@Override
	public ResBo<Object> updateByGoodsIdSelective(ReqBo reqBo) {
		
		Long oneId = reqBo.getParamLong("oneId");
		Long twoId = reqBo.getParamLong("twoId");
		Long threeId = reqBo.getParamLong("threeId");
		String goodsId = reqBo.getParamStr("goodsIds");
		String[] str = goodsId.split("/,");
		Long[] goodsIds = new Long[str.length];
		for(int i = 0; i < str.length; i++){
			goodsIds[i] = Long.parseLong(str[i]);
		}
		 goodsCategoryComponent.updateByGoodsIdSelective(oneId, twoId, threeId, goodsIds);
		return new ResBo<Object>();
	}
	
}
