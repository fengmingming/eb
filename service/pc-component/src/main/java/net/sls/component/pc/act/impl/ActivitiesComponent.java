package net.sls.component.pc.act.impl;

import net.sls.component.pc.act.IActivitiesComponent;
import net.sls.dao.pc.activity.PCActivitiesMapper;
import net.sls.dto.pc.act.Activities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class ActivitiesComponent implements IActivitiesComponent{
	
	@Autowired
	private PCActivitiesMapper  pActivitiesMapper;

	@Override
	public Activities selectActivities(Long actId) {
		return pActivitiesMapper.selectActivities(actId) ;
	}
}
