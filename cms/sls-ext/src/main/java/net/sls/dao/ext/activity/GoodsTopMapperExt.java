package net.sls.dao.ext.activity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import net.sls.dto.activity.GoodsTop;

public interface GoodsTopMapperExt {

	int insertGoodsTop(List<GoodsTop> gtList);

	int updateGoodsTop(GoodsTop bc);

	long countGoodsTopByFilter(@Param("areaCode") String areaCode);

	List<Map<Object,Object>> selectGoodsTopListByFilter(@Param("areaCode") String areaCode, @Param("start") Integer start,@Param("number") Integer number);

	int updateGoodsTopIsDel(List<Long> ids);

}
