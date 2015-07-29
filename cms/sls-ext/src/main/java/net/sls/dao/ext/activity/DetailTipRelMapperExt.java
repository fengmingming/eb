package net.sls.dao.ext.activity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DetailTipRelMapperExt {
	
	public int insertBatch(@Param("ids")List<Long> ids,@Param("tipId")long tipId);
	
	public List<Map<String,Object>> selectDetailTipRel(@Param("type")int type,@Param("tipId")long tipId,@Param("start")int start,@Param("number")int number);
	
	public long countDetailTipRel(long tipId);

}
