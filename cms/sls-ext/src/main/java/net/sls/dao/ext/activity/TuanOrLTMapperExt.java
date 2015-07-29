package net.sls.dao.ext.activity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import net.sls.dto.activity.Activities;
import net.sls.dto.activity.ActivityGoods;

public interface TuanOrLTMapperExt {
   
	public int insertTuanOrLT(Activities act);
	
	public int insertTuanOrLTDetails(List<ActivityGoods> details);
	
	public int updateTuanOrLTDetails(List<ActivityGoods> details);
	
	public List<Activities> selectTuanOrLT(@Param("actName")String actName,@Param("ing")Integer ing,@Param("areaCode")String areaCode,@Param("state")Integer state,@Param("verify")Integer verify,@Param("startTime")Date startTime,@Param("endTime")Date endTime,@Param("start")int start,@Param("number")int number);
	
	public long countTuanOrLT(@Param("actName")String actName,@Param("ing")Integer ing,@Param("areaCode")String areaCode,@Param("state")Integer state,@Param("verify")Integer verify,@Param("startTime")Date startTime,@Param("endTime")Date endTime);
	
	public List<Map<String,Object>> selectTuanOrLTDetail(@Param("actId")long actId,@Param("start")int start,@Param("number")int number);
	
	public long countTuanOrLTDetail(long actId);
	
	public int updateTuanOrLT(@Param("actId")long actId,@Param("state")Integer state,@Param("isVerify")Boolean isVerify,@Param("isDel")Integer isDel);
	
	public List<String> isExistGoodsActTuanOrLT(@Param("list")List<Long> list,@Param("startTime")Date startTime,@Param("endTime")Date endTime);
}