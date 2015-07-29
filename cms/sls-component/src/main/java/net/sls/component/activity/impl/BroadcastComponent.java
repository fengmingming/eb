package net.sls.component.activity.impl;

import java.util.List;

import net.sls.component.activity.IBroadcastComponent;
import net.sls.dao.ext.activity.BroadcastMapperExt;
import net.sls.dto.activity.Broadcast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.exception.BusinessException;
import framework.web.Pager;

@Component
public class BroadcastComponent implements IBroadcastComponent{
	
	@Autowired
	private BroadcastMapperExt bcMapperExt;
	@Override
	public void insertBroadcast(Broadcast bc) {
		int i = bcMapperExt.insertBroadcast(bc);
		if(i != 1){
			throw new BusinessException(1L);
		}
	}
	@Override
	public Pager<List<Broadcast>> selectBroadcastList(String areaCode,
			Integer start, Integer number) {
		long count = bcMapperExt.countBroadcastByFilter(areaCode);
		List<Broadcast> list = bcMapperExt.selectBroadcastListByFilter(areaCode, 
				(start - 1) * number, number);
		Pager<List<Broadcast>> pager = new Pager<List<Broadcast>>(list, count);
		return pager;
	}
	
	@Override
	public Broadcast updateBroadcast(Broadcast bc) {
		int i = bcMapperExt.updateBroadcast(bc);
		if(i != 1){
			throw new BusinessException(2L);
		}
		return bc;
	}

}
