package net.sls.dao.pc.product;

import java.util.List;

import net.sls.dto.pc.product.Area;

import org.apache.ibatis.annotations.Param;

import util.model.ComboxDto;

public interface PCAreaMapper {

    List<ComboxDto> selectAreaList(@Param("pid") Integer pid);

	Area selectByPrimaryKey(@Param("id") Integer id);

	util.model.Area selectAreaById(@Param("id") Integer id);


}