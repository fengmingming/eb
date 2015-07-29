package net.sls.component.product.impl;

import java.util.List;

import net.sls.component.product.IGoodsAreaComponent;
import net.sls.dao.ext.product.GoodsAreaMapperExt;
import net.sls.dao.product.GoodsAreaMapper;
import net.sls.dto.product.GoodsArea;
import net.sls.dto.product.GoodsAreaExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.web.Pager;

@Component
public class GoodsAreaComponent implements IGoodsAreaComponent {

	@Autowired
	public GoodsAreaMapper goodsAreaMapper;

	@Autowired
	public GoodsAreaMapperExt goodsAreaMapperExt;


	public Pager<List<GoodsArea>> selectAreaPager(long goodsId){
		GoodsAreaExample e = new GoodsAreaExample();
		e.createCriteria().andGoodsIdEqualTo(goodsId).andIsDelEqualTo(1);
		List<GoodsArea> list = goodsAreaMapper.selectByExample(e);
		return new Pager<List<GoodsArea>>(list,(long) list.size());
	}

	@Override
	public void deleteGoodsAreaById(long id) {
		GoodsArea ga = new GoodsArea();
		ga.setIsDel(127);
		ga.setId(id);
		goodsAreaMapper.updateByPrimaryKeySelective(ga);
	}
}
