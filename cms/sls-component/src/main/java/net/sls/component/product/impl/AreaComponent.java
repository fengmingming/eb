package net.sls.component.product.impl;

import java.util.List;

import net.sls.component.product.IAreaComponent;
import net.sls.dao.ext.product.AreaMapperExt;
import net.sls.dao.product.AreaMapper;
import net.sls.dto.product.Area;
import net.sls.dto.product.AreaExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.model.AreaEnum;
import framework.exception.BusinessException;

@Component
public class AreaComponent implements IAreaComponent{

	@Autowired
	public AreaMapper areaMapper; 
	
	@Autowired
	public AreaMapperExt areaMapperExt;
	
	
	@Override
	public List<Area> selectAreaByPid(Integer pid) {
		return areaMapperExt.selectAreaByPid(pid);
	}

	@Override
	public void insertArea(Area area) {
		AreaExample e = new AreaExample();
		e.createCriteria().andCodeEqualTo(area.getCode());
		if(areaMapper.selectByExample(e).size() != 0 ){
			throw new BusinessException("code is exist code="+area.getCode());
		}
		Area pArea = areaMapper.selectByPrimaryKey(area.getPid());
		if(pArea == null){
			throw new BusinessException("parent code is not exist pid="+area.getPid());
		}
		if(!area.getCode().startsWith(pArea.getCode())){
			throw new BusinessException(15L,area.getCode(),pArea.getCode());
		}
		if(pArea.getCode().length() == 1 && area.getCode().length() == 2){
			
		}else if(area.getCode().length() == pArea.getCode().length() + 3){
			
		}else{
			throw new BusinessException(16L);
		}
		int i = areaMapper.insertSelective(area);
		if(i != 1){
			throw new BusinessException(1L);
		}
	}

	@Override
	public void updateArea(Area area) {
		AreaExample e = new AreaExample();
		e.createCriteria().andCodeEqualTo(area.getCode());
		if(areaMapper.selectByExample(e).size() != 0 ){
			throw new BusinessException("code is exist code="+area.getCode());
		}
		Area pArea = areaMapper.selectByPrimaryKey(area.getPid());
		if(pArea == null){
			throw new BusinessException("parent code is not exist pid="+area.getPid());
		}
		if(!area.getCode().startsWith(pArea.getCode())){
			throw new BusinessException(15L,area.getCode(),pArea.getCode());
		}
		if(pArea.getCode().length() == 1 && area.getCode().length() == 2){
			
		}else if(area.getCode().length() == pArea.getCode().length() + 3){
			
		}else{
			throw new BusinessException(16L);
		}
		int i = areaMapper.updateByPrimaryKeySelective(area);
		if(i != 1){
			throw new BusinessException(2L);
		}
	}

	@Override
	public Area selectAreaById(Integer id) {
		return areaMapper.selectByPrimaryKey(id);
	}
	
	public String selectAreaCodeById(Integer id){
		if(id == null){
			return null;
		}
		util.model.Area area = AreaEnum.getArea(id);
		if(area == null){
			area = areaMapperExt.selectAreaById(id);
			AreaEnum.setArea(id, area);
		}
		return area.getCode();
	}

	@Override
	public Long selectCode(String code) {
		Long codeNo = areaMapperExt.countSelectCode(code);
		return codeNo;
	}
	
	@Override
	public Integer selectKey(String code) {
		if(code == null){
			return null;
		}
		Integer areaKey = AreaEnum.getKey(code);
		if(areaKey == null){
			areaKey = areaMapperExt.selectKey(code);
			AreaEnum.setKey(code, areaKey);
		}
		return areaKey;
	}
	
	@Override
	public Area selectAreaByCode(String code){
		AreaExample e = new AreaExample();
		e.createCriteria().andCodeEqualTo(code);
		List<Area> list = areaMapper.selectByExample(e);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Area> selectAreasByCodes(String[] paramStr) {
		return areaMapperExt.selectAreasByCodes(paramStr);		
	}

	@Override
	public Area selectAreaCodeByPid(Integer pid) {
		return areaMapperExt.selectAreaCodeByPid(pid);
	}

	@Override
	public String selectAreaNameById(Integer id) {
		if(id == null){
			return null;
		}
		util.model.Area area = AreaEnum.getArea(id);
		if(area == null){
			area = areaMapperExt.selectAreaById(id);
			AreaEnum.setArea(id, area);
		}
		return area.getName();
	}
	
}
