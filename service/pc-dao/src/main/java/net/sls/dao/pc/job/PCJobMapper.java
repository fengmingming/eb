package net.sls.dao.pc.job;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PCJobMapper {

	public List<Map<String,Object>> selectGoodsInfo(@Param("start") long start,@Param("rows") long rows);
	
	public long countGoods();
}
