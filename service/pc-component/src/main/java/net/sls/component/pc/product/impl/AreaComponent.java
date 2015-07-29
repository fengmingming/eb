package net.sls.component.pc.product.impl;

import java.util.List;

import net.sls.component.pc.product.IAreaComponent;
import net.sls.dao.pc.product.PCAreaMapper;
import net.sls.dto.pc.product.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.model.AreaEnum;
import util.model.ComboxDto;

@Component
public class AreaComponent implements IAreaComponent{
	
	@Autowired
	private PCAreaMapper areaMapper;
	@Override
	public List<ComboxDto> selectAreaList(Integer pid) {
		return areaMapper.selectAreaList(pid);
	}
	@Override
	public Area selectByPrimaryKey(Integer id) {
		
		return areaMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public String selectAreaNameById(Integer id) {
		if(id == null){
			return null;
		}
		String name = AreaEnum.getName(id);
		if(name == null){
			name = areaMapper.selectByPrimaryKey(id).getName();
			AreaEnum.setName(id, name);
		}
		return name;
	}
	
	@Override
	public util.model.Area selectAreaById(Integer id) {
		if(id == null){
			return null;
		}
		util.model.Area area=AreaEnum.getArea(id);
		if(area == null){
			area = areaMapper.selectAreaById(id);
			AreaEnum.setArea(id, area);
		}
		return area;
	}

}
