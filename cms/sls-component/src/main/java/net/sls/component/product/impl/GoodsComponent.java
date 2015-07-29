package net.sls.component.product.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sls.businessconstant.BusinessContant;
import net.sls.component.product.ICategoryComponent;
import net.sls.component.product.IGoodsComponent;
import net.sls.component.squence.ISquenceComponent;
import net.sls.dao.ext.product.AreaMapperExt;
import net.sls.dao.ext.product.GoodsAreaMapperExt;
import net.sls.dao.ext.product.GoodsMapperExt;
import net.sls.dao.product.GoodsCategoryMapper;
import net.sls.dao.product.GoodsDetailMapper;
import net.sls.dao.product.GoodsMapper;
import net.sls.dao.product.GoodsStockMapper;
import net.sls.dto.ext.product.GoodsAddBeanExt;
import net.sls.dto.product.Goods;
import net.sls.dto.product.GoodsArea;
import net.sls.dto.product.GoodsCategory;
import net.sls.dto.product.GoodsCategoryExample;
import net.sls.dto.product.GoodsDetail;
import net.sls.dto.product.GoodsDetailExample;
import net.sls.dto.product.GoodsStock;
import net.sls.dto.product.GoodsStockExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.framework.SessionUtil;
import util.framework.StrUtil;
import util.model.ComboxDto;
import util.model.SquenceTypeEnum;
import framework.exception.BusinessException;
import framework.web.Pager;

@Component
public class GoodsComponent implements IGoodsComponent {
	
	@Autowired
	private AreaComponent areaComponent;
	
	@Autowired
	private ISquenceComponent squenceComponent;
	
	@Autowired
	private ICategoryComponent categoryComponent;

	@Autowired
	private AreaMapperExt areaMapperExt;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private GoodsAreaMapperExt goodsAreaMapperExt;
	
	@Autowired
	private GoodsDetailMapper goodsDetailMapper;
	
	@Autowired
	private GoodsStockMapper goodsStockMapper;

	@Autowired
	private GoodsMapperExt goodsMapperExt;
	
	@Autowired
	private GoodsCategoryMapper goodsCategoryMapper;

	/*
	 * 
	 * @param GoodsAddBeanExt
	 * @date 2014年12月9日 下午2:55:50
	 */
	@Override
	public void insertGoods(GoodsAddBeanExt ext) {
		String areaCode = StrUtil.toString(SessionUtil.get(BusinessContant.OPERAREAID));
		Integer areaId = areaMapperExt.selectKey(areaCode);
		StringBuilder sku = new StringBuilder();
		if(ext.getGoodsCategory().getThreeId() != null){
			sku.append(categoryComponent.selectCategoryCodeById(ext.getGoodsCategory().getThreeId()));
		}else if(ext.getGoodsCategory().getTwoId() != null){
			sku.append(categoryComponent.selectCategoryCodeById(ext.getGoodsCategory().getTwoId()));
			sku.append("000");
		}else{
			sku.append(categoryComponent.selectCategoryCodeById(ext.getGoodsCategory().getOneId()));
			sku.append("000000");
		}
		sku.append(squenceComponent.updateSquence(SquenceTypeEnum.GOODSSKU.getType()));
		Date date = new Date();
		Goods goods = ext.getGoods();
		goods.setCreatetime(date);
		goods.setAreaCode(areaCode);
		goods.setAreaId(areaId);
		goods.setSku(sku.toString());
		int i = goodsMapperExt.insertGoodsExt(goods);
		if (i != 1) {
			throw new BusinessException(1L);
		}
		GoodsDetail gd = ext.getGoodsDetail();
		gd.setGoodsId(ext.getGoods().getId());
		i = goodsDetailMapper.insert(gd);
		if (i != 1) {
			throw new BusinessException(1L);
		}
		if(ext.getGas().size() > 0){
			for(GoodsArea area:ext.getGas()){
				area.setCreateTime(date);
				if(area.getPavilionId() != null){
					area.setLimitCode(areaComponent.selectAreaCodeById(area.getPavilionId()));
				}else if(area.getCommunityId() != null){
					area.setLimitCode(areaComponent.selectAreaCodeById(area.getCommunityId()));
				}else if(area.getDistrictId() != null){
					area.setLimitCode(areaComponent.selectAreaCodeById(area.getDistrictId()));
				}else if(area.getCityId() != null){
					area.setLimitCode(areaComponent.selectAreaCodeById(area.getCityId()));
				}else if(area.getProvinceId() != null){
					area.setLimitCode(areaComponent.selectAreaCodeById(area.getProvinceId()));
				}
				area.setGoodsId(goods.getId());
			}
			i = goodsAreaMapperExt.insert(ext.getGas());
			if(i!=ext.getGas().size()){
				throw new BusinessException(1L);
			}
		}
		GoodsStock gs = ext.getGoodsStock();
		gs.setCreateTime(date);
		gs.setGoodsId(goods.getId());
		i = goodsStockMapper.insert(gs);
		if (i != 1) {
			throw new BusinessException(1L);
		}
		GoodsCategory gc = ext.getGoodsCategory();
		gc.setGoodsId(goods.getId());
		i = goodsCategoryMapper.insert(gc);
		if (i != 1) {
			throw new BusinessException(1L);
		}
	}
	
	@Override
	public void updateAll(GoodsAddBeanExt ext){
		Date date = new Date();
		Goods goods = ext.getGoods();
		goods.setModifytime(date);
		goods.setAreaCode(null);
		goods.setAreaId(null);
		Long goodsId = goods.getId();
		int i = goodsMapper.updateByPrimaryKeySelective(goods);
		if (i > 1) {
			throw new BusinessException(2L);
		}
		GoodsDetail gd = ext.getGoodsDetail();
		gd.setGoodsId(goodsId);
		GoodsDetailExample gde = new GoodsDetailExample();
		gde.createCriteria().andGoodsIdEqualTo(goodsId);
		i = goodsDetailMapper.updateByExampleSelective(gd, gde);
		if (i > 1) {
			throw new BusinessException(2L);
		}
		if(ext.getGas().size() > 0){
			for(GoodsArea area:ext.getGas()){
				if(area.getPavilionId() != null){
					area.setLimitCode(areaComponent.selectAreaCodeById(area.getPavilionId()));
				}else if(area.getCommunityId() != null){
					area.setLimitCode(areaComponent.selectAreaCodeById(area.getCommunityId()));
				}else if(area.getDistrictId() != null){
					area.setLimitCode(areaComponent.selectAreaCodeById(area.getDistrictId()));
				}else if(area.getCityId() != null){
					area.setLimitCode(areaComponent.selectAreaCodeById(area.getCityId()));
				}else if(area.getProvinceId() != null){
					area.setLimitCode(areaComponent.selectAreaCodeById(area.getProvinceId()));
				}
				area.setGoodsId(goodsId);
			}
			List<GoodsArea> updateGa = new ArrayList<GoodsArea>();
			List<GoodsArea> addGa = new ArrayList<GoodsArea>();
			for(GoodsArea ga:ext.getGas()){
				if(ga.getId() == null){
					addGa.add(ga);
				}else{
					updateGa.add(ga);
				}
			}
			if(addGa.size() > 0){
				i = goodsAreaMapperExt.insert(addGa);
				if(i!=addGa.size()){
					throw new BusinessException(1L);
				}
			}
			if(updateGa.size() > 0){
				goodsAreaMapperExt.update(updateGa);
			}
		}
		GoodsStock gs = ext.getGoodsStock();
		gs.setModifyTime(date);
		gs.setGoodsId(goodsId);
		GoodsStockExample gse = new GoodsStockExample();
		gse.createCriteria().andGoodsIdEqualTo(goodsId);
		i = goodsStockMapper.updateByExampleSelective(gs, gse);
		if (i > 1) {
			throw new BusinessException(2L);
		}
		GoodsCategory gc = ext.getGoodsCategory();
		GoodsCategoryExample gce = new GoodsCategoryExample();
		gce.createCriteria().andGoodsIdEqualTo(goodsId);
		gc.setGoodsId(goodsId);
		i = goodsCategoryMapper.updateByExampleSelective(gc, gce);
		if (i > 1) {
			throw new BusinessException(2L);
		}
	}

	/*
	 * @author wangguojun
	 * @description: 按id查询商品
	 * @param goodId
	 * @return
	 * @date 2014年12月9日 下午2:56:28
	 */
	@Override
	public Goods selectByPrimaryKey(Long goodId) {
		return goodsMapper.selectByPrimaryKey(goodId);
	}

	@Override
	public List<ComboxDto> selectGoodsListByProvider(Long providerId) {
		return goodsMapperExt.selectGoodsListByProvider(providerId);
	}

	@Override
	public Pager<List<Goods>> selectGoodsByCategoryId(String areaCode,Long providerId,Long oneId, Long twoId, Long threeId,
			Integer start, Integer number) {
		
		long count = goodsMapperExt.countGoodsListByCategoryId(areaCode,providerId,oneId, twoId, threeId);
		List<Goods> list = goodsMapperExt.selectGoodsListByCategoryId(areaCode,providerId,oneId, twoId, threeId, start, number);
		Pager<List<Goods>> pager = new Pager<List<Goods>>(list,count);
		return pager;
	}

	@Override
	public Pager<List<Map<String, Object>>> selectGoodsByFilter(String areaCode,Long goodsId,
			String sku, String name, BigDecimal price, BigDecimal price2, Integer provider,Integer isSale, int start, int number) {
		return new Pager<List<Map<String,Object>>>(goodsMapperExt.selectGoodsByFilter(areaCode,goodsId, sku, name, price, price2, provider, isSale, (start-1)*number,number),goodsMapperExt.countGoodsByFilter(areaCode, goodsId, sku, name, price, price2, provider,isSale));
	}

	@Override
	public void updateGoodsById(Goods g) {
		int i =goodsMapper.updateByPrimaryKeySelective(g);
		if(i != 1){
			throw new BusinessException(2L);
		}
	}
	
	@Override
	public Map<String, Object> selectUpdateGoodsInfo(long goodsId) {
		return goodsMapperExt.selectUpdateGoodsInfo(goodsId);
	}

	@Override
	public void updateBatchGoodsIsSale(List<Long> ids, int isSale) {
		goodsMapperExt.updateBatchGoodsIsSale(ids, isSale);
	}

	@Override
	public void updateFlushBuyNum() {
		goodsMapperExt.flushBuyNum();
	}

	@Override
	public void updateBatchUseCoupon(List<Long> ids, boolean isUse) {
		if(isUse){
			goodsMapperExt.batchUseCoupon(ids);
		}else{
			goodsMapperExt.batchNoUseCoupon(ids);
		}
	}
	
	

}
