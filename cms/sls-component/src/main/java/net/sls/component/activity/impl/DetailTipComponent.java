package net.sls.component.activity.impl;

import java.util.List;
import java.util.Map;

import net.sls.component.activity.IDetailTipComponent;
import net.sls.dao.activity.DetailTipMapper;
import net.sls.dao.ext.activity.DetailTipMapperExt;
import net.sls.dao.ext.activity.DetailTipRelMapperExt;
import net.sls.dto.activity.DetailTip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.exception.BusinessException;
import framework.web.Pager;

@Component
public class DetailTipComponent implements IDetailTipComponent{
	
	@Autowired
	public DetailTipMapper tipMappper;
	
	@Autowired
	public DetailTipMapperExt tipExt;
	
	@Autowired
	public DetailTipRelMapperExt tipRelExt;

	@Override
	public void insertDetailTip(DetailTip tip, List<Long> ids) {
		int i = tipExt.insert(tip);
		if(i == 1){
			i = tipRelExt.insertBatch(ids, tip.getId());
			if(i != ids.size()){
				throw new BusinessException(1l);
			}
		}else{
			throw new BusinessException(1l);
		}
	}

	@Override
	public void updateDetailTip(long tipId) {
		DetailTip tip = new DetailTip();
		tip.setId(tipId);
		tip.setIsDel(127);
		tipMappper.updateByPrimaryKeySelective(tip);
	}

	@Override
	public Pager<List<Map<String, Object>>> selectDetailTip(String areaCode,Integer isAct,Integer type,
			int start, int number) {
		return new Pager<List<Map<String,Object>>>(tipExt.selectDetailTipPager(isAct, areaCode, type, (start-1)*number, number),tipExt.countDetailTipPager(isAct, areaCode, type));
	}

	@Override
	public Pager<List<Map<String, Object>>> selectDetailTipRel(int type,long tipId,
			int start, int number) {
		return new Pager<List<Map<String,Object>>>(tipRelExt.selectDetailTipRel(type, tipId, (start-1)*number, number),tipRelExt.countDetailTipRel(tipId));
	}

}
