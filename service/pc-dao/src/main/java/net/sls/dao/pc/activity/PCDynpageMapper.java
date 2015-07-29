package net.sls.dao.pc.activity;

import net.sls.dto.pc.act.Dynpage;

import org.apache.ibatis.annotations.Param;

public interface PCDynpageMapper {

    Dynpage selectByPrimaryKey(@Param("id") Long id);

}