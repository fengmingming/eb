package net.sls.dao.ext.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import net.sls.dto.product.PavilionArea;
import net.sls.dto.product.PavilionInfo;

public interface PavilionInfoMapperExt {

	int updatePavilionInfo(PavilionInfo record);
	Long countSelectPavilionCode(String pavilionCode);
	Long countSelectPavilionSn(String pavilionSn);
	int insertPavilionInfo(PavilionInfo record);
	public List<Map<String,Object>> selectPavilionInfo(@Param("code") String code,@Param("pavilionCode") String pavilionCode,
			@Param("pavilionSn") String pavilionSn,@Param("mobile") String mobile,
			@Param("name") String name,@Param("start") Integer start,
			@Param("number") Integer number);
	public long countSelectPavilionInfo(@Param("code") String code,@Param("pavilionCode") String pavilionCode,
			@Param("pavilionSn") String pavilionSn,@Param("mobile") String mobile,
			@Param("name") String name);
	
	public long countPavilionArea(@Param("community") Integer community,@Param("pavilion")Integer pavilion);
	
	public List<Map<String,Object>> selectPavilionArea(@Param("community") Integer community,@Param("pavilion")Integer pavilion,@Param("start") Integer start,@Param("number") Integer number);
	
	public int insertPavilionArea(List<PavilionArea> list);
}