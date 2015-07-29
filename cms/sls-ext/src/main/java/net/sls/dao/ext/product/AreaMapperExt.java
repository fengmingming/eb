package net.sls.dao.ext.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.sls.dto.product.Area;

public interface AreaMapperExt {
   
	Long countSelectCode (String code);
	Integer selectKey (String code);
	
	List<Area> selectAreaByPid(Integer pid);
	
	util.model.Area selectAreaById(Integer id);
	
	List<Area> selectAreasByCodes(String[] codes);
	
	Area selectAreaCodeByPid(@Param("pid") Integer pid);
	
	List<Area> selectAllArea();
	
}