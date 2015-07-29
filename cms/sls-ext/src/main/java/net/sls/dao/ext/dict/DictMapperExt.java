package net.sls.dao.ext.dict;

import java.util.List;

import net.sls.dto.product.Dict;

import org.apache.ibatis.annotations.Param;

import util.model.ComboxDto;

public interface DictMapperExt {

	public List<Dict> selectDictsByFilter(@Param("code") String code,@Param("name") String name,@Param("type") Integer type,@Param("start") Integer start,@Param("number") Integer number);
	
	public long countDictsByFilter(@Param("code") String code,@Param("name") String name,@Param("type") Integer type);
	
	public List<ComboxDto> selectDictCombox();
	
	public List<ComboxDto> selectDictComboxByType(@Param("type")Integer type,@Param("key")String key);
}
