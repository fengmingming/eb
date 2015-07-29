package net.sls.component.product.impl;

import java.util.List;

import net.sls.component.product.IGoodsCategoryComponent;
import net.sls.dao.ext.product.GoodsCategoryMapperExt;
import net.sls.dao.product.GoodsCategoryMapper;
import net.sls.dto.product.GoodsCategory;
import net.sls.dto.product.GoodsCategoryExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.framework.BusinessExceptionUtil;
import framework.exception.BusinessException;
import framework.web.Pager;

/**
 * 供应商组件的实现类
 *
 */
@Component
public class GoodsCategoryComponent implements IGoodsCategoryComponent {

	@Autowired
	private GoodsCategoryMapper goodsCategoryMapper;
	
	@Autowired
	private GoodsCategoryMapperExt goodsCategoryMapperExt;
	
	@Override
	public void insertGoodsCategory(GoodsCategory goodsCategory) {
		int i = goodsCategoryMapper.insertSelective(goodsCategory);
		if(i != 1){
			throw new BusinessException(1L);
		}
		
	}

	@Override
	public void updateGoodsCategory(GoodsCategory goodsCategory) {
		int i = goodsCategoryMapper.updateByPrimaryKeySelective(goodsCategory);
		if(i != 1){
			throw new BusinessException(2L);
		}
		
	}

	@Override
	public GoodsCategory selectByPrimaryKey(long id) {
		GoodsCategoryExample c = new GoodsCategoryExample();
		c.createCriteria().andIdEqualTo(id);
		List<GoodsCategory> goodsCategorys = goodsCategoryMapper.selectByExample(c);
		if(goodsCategorys.size() > 1){
			throw BusinessExceptionUtil.createBusinessException(5L, id);
		}
		if(goodsCategorys.size() == 1){
			return goodsCategorys.get(0);
		}
		return null;
	}

	@Override
	public void deleteGoodsCategory(long id) {
		int i = goodsCategoryMapper.deleteByPrimaryKey(id);
		if(i != 1){
			throw new BusinessException(3L);
		}
	}

	@Override
	public void deleteGoodsCategorys(List<Long> ids) {
		GoodsCategoryExample gc = new GoodsCategoryExample();
		gc.createCriteria().andIdIn(ids);
		int i = goodsCategoryMapper.deleteByExample(gc);
		if(i == 0){
			throw new BusinessException(3L);
		}
		
	}

	@Override
	public Pager<List<GoodsCategory>> selectGoodsCategorys(Long oneId,Long twoId,Long threeId,Long goodsId,Integer start, Integer number){
	
		
		long count = goodsCategoryMapperExt.countGoodsCategorysByFilter(oneId, twoId, threeId, goodsId);
		List<GoodsCategory> list = goodsCategoryMapperExt.selectGoodsCategorysByFilter(oneId, twoId, threeId, goodsId, start, number);
		Pager<List<GoodsCategory>> pager = new Pager<List<GoodsCategory>>(list,count);
		return pager;
	}

	@Override
	public void updateByGoodsIdSelective(Long oneId, Long twoId, Long threeId,
			Long[] goodsId) {

		int i = goodsCategoryMapperExt.updateByGoodsIdSelective(oneId, twoId, threeId, goodsId);
		if(i < 1){
			throw new BusinessException(1L);
		}
		
	}
	
}
