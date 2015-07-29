package net.sls.dao.pc.activity;

import org.apache.ibatis.annotations.Param;

import net.sls.dto.pc.act.Activities;

public interface PCActivitiesMapper {

	Activities selectActivities(@Param("id") Long actId);

}
