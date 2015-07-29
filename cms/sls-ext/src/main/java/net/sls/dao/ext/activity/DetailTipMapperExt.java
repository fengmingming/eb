package net.sls.dao.ext.activity;

import java.util.List;
import java.util.Map;

import net.sls.dto.activity.DetailTip;

import org.apache.ibatis.annotations.Param;

public interface DetailTipMapperExt {

	public int insert(DetailTip tip);
	
	public List<Map<String,Object>> selectDetailTipPager(@Param("isAct")Integer isAct,@Param("areaCode")String areaCode,@Param("type")Integer type,@Param("start")int start,@Param("number")int number);
	
	public long countDetailTipPager(@Param("isAct")Integer isAct,@Param("areaCode")String areaCode,@Param("type")Integer type);
}
