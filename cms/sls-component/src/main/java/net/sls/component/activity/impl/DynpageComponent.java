package net.sls.component.activity.impl;

import java.util.List;
import java.util.Map;

import net.sls.component.activity.IDynpageComponent;
import net.sls.dao.activity.DynpageGoodsMapper;
import net.sls.dao.ext.activity.DynpageMapperExt;
import net.sls.dto.activity.Dynpage;
import net.sls.dto.activity.DynpageGoods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.exception.BusinessException;
import framework.web.Pager;

@Component
public class DynpageComponent implements IDynpageComponent{
	
	@Autowired
	private DynpageMapperExt ext;
	
	@Autowired
	private DynpageGoodsMapper dgmapper;

	@Override
	public Long insertDynpage(Dynpage page, List<DynpageGoods> list) {
		int i = ext.insertDynpage(page);
		if(i != 1){
			throw new BusinessException(32L);
		}
		for(DynpageGoods dg : list){
			dg.setDynpageId(page.getId());
		}
		i = ext.insertDynpageGoods(list);
		if(i != list.size()){
			throw new BusinessException(32L);
		}
		return page.getId();
	}

	@Override
	public void updateDynpage(Dynpage dg) {
		int i = ext.updateDynpage(dg);
		if(i != 1){
			throw new BusinessException(3L);
		}
	}

	@Override
	public void deleteDynpageGoods(Long id) {
		int i = ext.deleteDynpageGoods(id);
		if(i != 1){
			throw new BusinessException(3L);
		}
	}

	@Override
	public Pager<List<Dynpage>> selectDynpage(int start, int number) {
		return new Pager<List<Dynpage>>(ext.selectDynpage(start, number),ext.countDynpage());
	}

	@Override
	public Pager<List<Map<String, Object>>> selectDynPageGoods(Long id,
			int start, int number) {
		return new Pager<List<Map<String,Object>>>(ext.selectDynpageGoods(id, start, number),ext.countDynpageGoods(id));
	}

	@Override
	public void updateDynpageGoods(DynpageGoods dg) {
		int i = ext.updateDynpageGoods(dg);
		if(i != 1){
			throw new BusinessException(2L);
		}
	}

}
