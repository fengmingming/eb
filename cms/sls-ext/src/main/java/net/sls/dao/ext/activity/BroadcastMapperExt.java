package net.sls.dao.ext.activity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.sls.dto.activity.Broadcast;

public interface BroadcastMapperExt {

	int insertBroadcast(Broadcast bc);

	long countBroadcastByFilter(@Param("areaCode") String areaCode);

	List<Broadcast> selectBroadcastListByFilter(@Param("areaCode") String areaCode, @Param("start") Integer start,@Param("number") Integer number);

	int updateBroadcast(Broadcast bc);

}
