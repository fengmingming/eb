package net.sls.component.pc.product.impl;

import java.util.ArrayList;
import java.util.List;

import net.sls.commons.businessconstant.BusinessContant;
import net.sls.component.pc.product.IGoodsComponent;
import net.sls.dao.pc.product.PCGoodsAreaMapper;
import net.sls.dao.pc.product.PCGoodsMapper;
import net.sls.dto.pc.product.CommodityDto;
import net.sls.dto.pc.product.GoodsArea;
import net.sls.dto.pc.shopcart.GoodDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.web.Pager;

@Component
public class GoodsComponent implements IGoodsComponent {

	@Autowired
	public PCGoodsMapper goodsMapper;
	
	@Autowired
	public PCGoodsAreaMapper goodsAreaMapper;

	@Override
	public Pager<List<CommodityDto>> selectGoods(Long first,
			Long second, Long third, List<Integer> areaIds, Integer actType, Integer hasGoods,
			Double minPrice, Double maxPrice, Integer sortPrice,
			Integer sortNew, Integer sortSale, Integer start, Integer number) {
		long count = goodsMapper.countGoods(first, second, third, areaIds, actType,
				hasGoods, minPrice, maxPrice, sortPrice, sortNew, sortSale);
		List<CommodityDto> maps = goodsMapper.selectGoods(first, second,
				third, areaIds, actType, hasGoods, minPrice, maxPrice, sortPrice,
				sortNew, sortSale, start*number, number);
		Pager<List<CommodityDto>> pager = new Pager<List<CommodityDto>>(maps, count);
		return pager;
	}

	public CommodityDto selectGoods(long id,Integer provinceId,Integer cityId,Integer districtId,Integer communityId,Integer pavilionId) {
		CommodityDto goods = goodsMapper.selectGoodsDetail(id);
		if (goods != null) {
			//商品限购
			if(goods.getLimittype().intValue() != BusinessContant.GOODLIMITRANGE_NO){
				GoodsArea goodsArea = null;
				switch (goods.getLimittype()) {
					case 1:goodsArea=goodsAreaMapper.selectGoodsAreaByFilter(id,null,null,null,null,null);break;
					case 2:goodsArea=goodsAreaMapper.selectGoodsAreaByFilter(id,provinceId,null,null,null,null);break;
					case 3:goodsArea=goodsAreaMapper.selectGoodsAreaByFilter(id,provinceId,cityId,null,null,null);break;
					case 4:goodsArea=goodsAreaMapper.selectGoodsAreaByFilter(id,provinceId,cityId,districtId,communityId,null);break;
					case 5:goodsArea=goodsAreaMapper.selectGoodsAreaByFilter(id,provinceId,cityId,districtId,communityId,pavilionId);break;
					default:break;
				}
				if(goodsArea != null){
					goods.setNumber(Math.min(goodsArea.getNumber(), goods.getNumber()));
				}
			}
			//商品提示信息
			String detailTip = goodsMapper.selectGoodsDetailTip(id);
			if(detailTip != null && !detailTip.trim().equals("")){
				goods.setDetailTip(detailTip);
			}
			//月销量
			if(goods.getMonthNum() == null){
				goods.setMonthNum(0);
			}
		}
		return goods;
	}

	@Override
	public GoodDto selectGoodDto(Long goodId) {
		GoodDto goodDto = new GoodDto();
		goodDto = goodsMapper.selectGoodDto(goodId);
		return goodDto;
	}

	@Override
	public List<CommodityDto> selectGoodsCartDto(List<Long> goodIdList) {
		return goodsMapper.selectGoodsCartDto(goodIdList);
	}
	
	@Override
	public Pager<List<CommodityDto>> selectGoodsList(String contents[],List<Integer> areaIds,Integer hasGoods,Double minPrice,Double maxPrice,Integer sortPrice,
		      Integer sortNew,Integer sortSale,Integer start, Integer number) {

		long count = goodsMapper.countGoodsList(contents, areaIds, hasGoods, minPrice, maxPrice, sortPrice, sortNew, sortSale);
		List<CommodityDto> maps = goodsMapper.selectGoodsList(contents, areaIds, hasGoods, minPrice, maxPrice, sortPrice, sortNew, sortSale, start*number, number);
		Pager<List<CommodityDto>> pager = new Pager<List<CommodityDto>>(maps, count);
		return pager;
	}
	
	@Override
	public Pager<List<CommodityDto>> selectGoodsByIds(List<Long> goodsIds){
		long count = goodsIds.size();
		List<CommodityDto> maps = new ArrayList<CommodityDto>();
		if(count > 0){
			maps = goodsMapper.selectGoodsByIds(goodsIds);
		}
		Pager<List<CommodityDto>> pager = new Pager<List<CommodityDto>>(maps, count);
		return pager;
	}

	@Override
	public Pager<List<CommodityDto>> selectDynpageGoodsPageList(
			Integer dynpageId, Integer start, Integer number) {
		long count = goodsMapper.countDynpageGoods(dynpageId);
		List<CommodityDto> maps = goodsMapper.selectDynpageGoods(dynpageId, start*number, number);
		Pager<List<CommodityDto>> pager = new Pager<List<CommodityDto>>(maps, count);
		return pager;
	}
	
	@Override
	public List<Long> selectCategoryGoods4Coupon(List<Long> goodsIds, List<Long> categoryIds){
		return goodsMapper.selectCategoryGoods4Coupon(goodsIds, categoryIds);
	}

	@Override
	public Pager<List<CommodityDto>> selectDynpageGoodsListByType(Integer type,
			Integer start, Integer number) {
		long count = goodsMapper.countDynpageGoodsByType(type);
		List<CommodityDto> maps = goodsMapper.selectDynpageGoodsByType(type, start*number, number);
		Pager<List<CommodityDto>> pager = new Pager<List<CommodityDto>>(maps, count);
		return pager;
	}
}
