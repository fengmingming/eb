package net.sls.component.pc.act.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sls.component.pc.act.IActivityGoodsComponent;
import net.sls.dao.pc.activity.PCActivityGoodsMapper;
import net.sls.dao.pc.order.PCOrderActGoodsInfoMapper;
import net.sls.dto.pc.act.ActivityGoods;
import net.sls.dto.pc.order.OrderActGoodsInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivityGoodsComponent implements IActivityGoodsComponent{

	@Autowired
	private PCActivityGoodsMapper activityGoodsMapper;
	
	@Autowired
	private PCOrderActGoodsInfoMapper oagMapper;

	@Override
	public List<ActivityGoods> selectActivityGoods(List<Long> goodsIds,List<String> areaCodeList) {
		return activityGoodsMapper.selectActivityGoods(goodsIds,null,areaCodeList);
	}
	
	@Override
	public List<ActivityGoods> selectUserActivityGoods(Long userId, Boolean isCommonUser, Integer actType, List<Long> goodsIds, List<String> areaCodeList){
		List<ActivityGoods> ags = activityGoodsMapper.selectActivityGoods(goodsIds, actType, areaCodeList);
		if(ags != null && ags.size() > 0){
			List<Long> actIds = new ArrayList<Long>();
			Map<Long, ActivityGoods> agMap = new HashMap<Long, ActivityGoods>();
			for(ActivityGoods ag : ags){
				if(ag.getNumber() > 0){
					ag.setCanBuyNumber(ag.getNumber());
				}
				//有用户，需要得到用户相关的限购数据，否则得到所有限购的数据
				if(userId != null && Boolean.TRUE.equals(isCommonUser)){
					if(ag.getNumber() > 0){
						actIds.add(ag.getActId());
						agMap.put(ag.getGoodsId(), ag);
					}
				}else{
					actIds.add(ag.getActId());
					agMap.put(ag.getGoodsId(), ag);
				}
			}
			if(actIds.size() > 0){
				List<OrderActGoodsInfo> actNumbers = oagMapper.selectNumberByUserId(userId, goodsIds, actIds);
				for(OrderActGoodsInfo actNumber : actNumbers){
					Long goodsId = actNumber.getGoodsId();
					if(agMap.containsKey(goodsId)){
						ActivityGoods ag = agMap.get(goodsId);
						if(ag.getNumber() > 0){
							if(userId != null && Boolean.TRUE.equals(isCommonUser)){
								int canBuyNum = ag.getNumber() - actNumber.getNumber();
								ag.setCanBuyNumber(canBuyNum > 0 ? canBuyNum : 0);
							}else{
								ag.setCanBuyNumber(ag.getNumber());
							}
						}else{
							ag.setCanBuyNumber(Integer.MAX_VALUE);
						}
						ag.setBuyNumber(actNumber.getNumber());
					}
				}
			}
		}
		return ags;
	}
}
