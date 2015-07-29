package net.sls.component.pc.act;

import java.util.List;

import net.sls.dto.pc.act.ActivityGoods;

public interface IActivityGoodsComponent {

	List<ActivityGoods> selectActivityGoods(List<Long> goodsIds,
			List<String> areaCodeList);
	
	/**
	 * 得到用户相关的商品活动信息
	 * @param userId
	 * @param isCommonUser 是否普通用户
	 * @param actType
	 * @param goodsIds
	 * @param areaCodeList
	 * @return
	 */
	List<ActivityGoods> selectUserActivityGoods(Long userId, Boolean isCommonUser, Integer actType, List<Long> goodsIds, List<String> areaCodeList);

}
