package net.sls.service.product;

import java.util.List;
import java.util.Map;

import net.sls.dto.product.Goods;
import util.model.ComboxDto;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IGoodsService {

	public ResBo<?> insertGoods(ReqBo reqBo);
	
	public ResBo<?> updateAll(ReqBo reqBo);
	
	public ResBo<List<ComboxDto>> selectGoodsListByProvider(ReqBo reqBo);
	
	/**
	 * 查询产品列表根据品类id
	 * 
	 * */
	public ResBo<Pager<List<Goods>>> selectGoodsByCategoryId(ReqBo reqBo);
	
	public ResBo<Pager<List<Map<String,Object>>>> selectGoodsByFilter(ReqBo reqBo);
	
	public ResBo<?> updateGoodsById(ReqBo reqBo);
	
	public ResBo<Map<String,Object>> selectUpdateGoodsInfo(ReqBo reqBo);
	
	public ResBo<?> batchGoodsIsSale(ReqBo reqBo);
	
	public ResBo<?> flushBuyNum();
	
	public ResBo<?> batchUseCoupon(ReqBo reqBo);
}
