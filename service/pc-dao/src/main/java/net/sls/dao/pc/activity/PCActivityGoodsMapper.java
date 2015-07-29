package net.sls.dao.pc.activity;

import java.util.List;

import net.sls.dto.pc.act.ActivityGoods;

import org.apache.ibatis.annotations.Param;

public interface PCActivityGoodsMapper {

	List<ActivityGoods> selectActivityGoods(@Param("goodsIds") List<Long> goodsIds,
			@Param("actType") Integer actType, @Param("areaCodeList") List<String> areaCodeList);
	
}
