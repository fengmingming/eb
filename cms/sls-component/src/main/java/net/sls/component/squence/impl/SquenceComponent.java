package net.sls.component.squence.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.exception.BusinessException;
import net.sls.component.squence.ISquenceComponent;
import net.sls.dao.ext.squence.SquenceMapperExt;

@Component
public class SquenceComponent implements ISquenceComponent{

	@Autowired
	private SquenceMapperExt ext;
	
	@Override
	public long updateSquence(int type) {
		int i = ext.updateSquence(type);
		if(i != 1){
			throw new BusinessException(24L);
		}
		return ext.selectSquenceByType(type);
	}

}
