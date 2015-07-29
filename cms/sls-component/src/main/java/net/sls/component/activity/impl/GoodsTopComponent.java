package net.sls.component.activity.impl;

import java.util.List;
import java.util.Map;

import net.sls.component.activity.IGoodsTopComponent;
import net.sls.dao.ext.activity.GoodsTopMapperExt;
import net.sls.dto.activity.GoodsTop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.exception.BusinessException;
import framework.web.Pager;

@Component
public class GoodsTopComponent implements IGoodsTopComponent{
	@Autowired
	private GoodsTopMapperExt gTopMapperExt;
	@Override
	public void insertGoodsTop(List<GoodsTop> gtList) {
		int i = gTopMapperExt.insertGoodsTop(gtList);
		if(i == 0){
			throw new BusinessException(1L);
		}
	}
	@Override
	public GoodsTop updateGoodsTop(GoodsTop bc) {
		int i = gTopMapperExt.updateGoodsTop(bc);
		if(i != 1){
			throw new BusinessException(2L);
		}
		return bc;
	}
	@Override
	public Pager<List<Map<Object,Object>>> selectGoodsTopList(String areaCode,
			Integer start, Integer number) {
		long count = gTopMapperExt.countGoodsTopByFilter(areaCode);
		List<Map<Object,Object>> list = gTopMapperExt.selectGoodsTopListByFilter(areaCode, 
				(start - 1) * number, number);
		Pager<List<Map<Object,Object>>> pager = new Pager<List<Map<Object,Object>>>(list, count);
		return pager;
	}
	@Override
	public void updateGoodsTopIsDel(List<Long> ids) {
		int i = gTopMapperExt.updateGoodsTopIsDel(ids);
		if(i == 0){
			throw new BusinessException(2L);
		}
	}

}
