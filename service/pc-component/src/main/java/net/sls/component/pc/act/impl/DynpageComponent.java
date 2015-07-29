package net.sls.component.pc.act.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sls.component.pc.act.IDynpageComponent;
import net.sls.dao.pc.activity.PCDynpageMapper;
import net.sls.dto.pc.act.Dynpage;
@Component
public class DynpageComponent implements IDynpageComponent{
	@Autowired
	private PCDynpageMapper dynpageMapper;

	@Override
	public Dynpage selectByPrimaryKey(Long id) {
		return dynpageMapper.selectByPrimaryKey(id);
	}
	
}
