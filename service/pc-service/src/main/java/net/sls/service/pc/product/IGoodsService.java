package net.sls.service.pc.product;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import net.sls.dto.pc.product.CommodityDto;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IGoodsService {

	
 	/**
	 * 查询商品信息
	 * @param reqBo 包括品类oneId、twoId、threeId、areaId、hasGoods、minPrice、maxPrice、orderPrice、orderNew、orderSale、page、rows
	 * @return
	 */
	@Cacheable("default")
	public ResBo<Pager<List<CommodityDto>>> selectProductPageList(ReqBo reqBo);
	
	/**
	 * 查询商品详情根据goodsId
	 * 查到的内容包括：四张小图、一张大图、商品描述、库存
	 */
	public ResBo<CommodityDto> selectGoodsDetail(ReqBo reqBo);
	
	
	/**
	 * 查询商品信息
	 * @param reqBo 包括content、areaIds、hasGoods、minPrice、maxPrice、orderPrice、orderNew、orderSale、page、rows
	 * @return
	 */
	public ResBo<Pager<List<CommodityDto>>> sreachGoods(ReqBo reqBo);
	/**
	 * 查询市场活动商品信息
	 * @param reqBo 包括dynpageId、page、rows
	 * @return
	 */
	public ResBo<Pager<List<CommodityDto>>> selectDynpageGoodsPageList(
			ReqBo reqBo);
	/**
	 * 查询市场活动商品信息
	 * @param reqBo 包括page、rows type
	 * @param reqBo
	 * @return
	 */
	public ResBo<Pager<List<CommodityDto>>> selectDynpageGoodsListByType(
			ReqBo reqBo);
}
