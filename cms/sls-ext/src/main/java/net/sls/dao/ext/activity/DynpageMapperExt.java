package net.sls.dao.ext.activity;

import java.util.List;
import java.util.Map;

import net.sls.dto.activity.Dynpage;
import net.sls.dto.activity.DynpageGoods;

import org.apache.ibatis.annotations.Param;

public interface DynpageMapperExt {

	public int insertDynpage(Dynpage dynpage);
	
	public int insertDynpageGoods(List<DynpageGoods> list);
	
	public List<Dynpage> selectDynpage(@Param("start")int start,@Param("number")int number);
	
	public long countDynpage();
	
	public List<Map<String,Object>> selectDynpageGoods(@Param("dynpageId")long dynpageId,@Param("start")int start,@Param("number")int number);
	
	public long countDynpageGoods(long dynpageId);
	
	public int updateDynpage(Dynpage dynpage);
	
	public int deleteDynpageGoods(long id);
	
	public int updateDynpageGoods(DynpageGoods dg);
}
