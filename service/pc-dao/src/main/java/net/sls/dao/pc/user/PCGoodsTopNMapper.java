package net.sls.dao.pc.user;

import java.util.List;
import java.util.Map;


import org.apache.ibatis.annotations.Param;

public interface PCGoodsTopNMapper {

	// 返回指定城市的TopN 商品
	List<Map<Object, Object>> selectTopNGoodsByCityId(@Param("areaCode") String areaCode);

}
