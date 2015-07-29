package net.sls.component.activity.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sls.businessconstant.BusinessContant;
import net.sls.component.activity.ITuanOrLTComponent;
import net.sls.dao.activity.ActivitiesMapper;
import net.sls.dao.activity.ActivityGoodsMapper;
import net.sls.dao.activity.ActivityLogMapper;
import net.sls.dao.ext.activity.TuanOrLTMapperExt;
import net.sls.dto.activity.Activities;
import net.sls.dto.activity.ActivityGoods;
import net.sls.dto.activity.ActivityLog;
import net.sls.dto.user.CmsUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.framework.SessionUtil;
import framework.exception.BusinessException;
import framework.web.Pager;

@Component
public class TuanOrLTComponent implements ITuanOrLTComponent{
	
	@Autowired
	private TuanOrLTMapperExt ext;
	
	@Autowired
	private ActivitiesMapper mapper;
	
	@Autowired
	private ActivityGoodsMapper agmapper;
	
	@Autowired
	private ActivityLogMapper actLogMapper;

	@Override
	public void insertTuanOrLT(Activities act, List<ActivityGoods> list) {
		List<Long> gids = new ArrayList<Long>();
		for(ActivityGoods ag:list){
			gids.add(ag.getGoodsId());
		}
		List<String> gnames = ext.isExistGoodsActTuanOrLT(gids, act.getStartTime(), act.getEndTime());
		if(gnames.size() > 0){
			StringBuilder sb = new StringBuilder();
			for(String gname:gnames){
				sb.append("<p style='color:red'>");
				sb.append(gname);
				sb.append("</p>");
			}
			throw new BusinessException(28L, sb.toString());
		}
		int i = ext.insertTuanOrLT(act);
		if(i != 1){
			throw new BusinessException(1L);
		}
		for(ActivityGoods ag:list){
			ag.setActId(act.getId());
			ag.setActType(act.getActType());
			ag.setStartTime(act.getStartTime());
			ag.setEndTime(act.getEndTime());
		}
		i = ext.insertTuanOrLTDetails(list);
		if(i != list.size()){
			throw new BusinessException(1L);
		}
	}

	@Override
	public void updateTuanOrLTDetail(ActivityGoods ag) {
		int i = agmapper.updateByPrimaryKeySelective(ag);
		if(i != 1){
			throw new BusinessException(2l);
		}
		ActivityGoods nag = agmapper.selectByPrimaryKey(ag.getId());
		Activities act = mapper.selectByPrimaryKey(nag.getActId());
		if(act.getIsVerify()){
			ActivityLog log = new ActivityLog();
			log.setActId(act.getId());
			log.setActType(act.getActType());
			CmsUser cu = (CmsUser) SessionUtil.get(BusinessContant.CMSUSER);
			log.setOperatorId(cu.getId());
			log.setOperatorTime(new Date());
			log.setRemark("审核后修改活动商品信息goodsId="+nag.getGoodsId()+" actPrice="+nag.getActPrice()+" number="+nag.getNumber());
			i = actLogMapper.insert(log);
			if(i != 1){
				throw new BusinessException(2l);
			}
		}
	}

	@Override
	public Pager<List<Activities>> selectTuanOrLT(String areaCode,String actName,Integer ing,Integer state,Integer verify,Date startTime, Date endTime, int start, int number) {
		return new Pager<List<Activities>>(ext.selectTuanOrLT(actName,ing,areaCode,state, verify, startTime, endTime,(start-1)*number, number), ext.countTuanOrLT(actName,ing,areaCode,state, verify, startTime, endTime));
	}

	@Override
	public Pager<List<Map<String,Object>>> selectTuanOrLTDetail(long actId, int start, int number) {
		return new Pager<List<Map<String,Object>>>(ext.selectTuanOrLTDetail(actId, (start-1)*number, number), ext.countTuanOrLTDetail(actId));
	}

	@Override
	public void updateTuanOrLT(Activities act) {
		int i = ext.updateTuanOrLT(act.getId(), act.getState(), act.getIsVerify(), act.getIsDel());
		if(i != 1){
			if(act.getIsDel() != null){
				throw new BusinessException(29l);
			}
			if(act.getState() != null){
				throw new BusinessException(27l);
			}
			if(act.getIsVerify() != null){
				throw new BusinessException(30l);
			}
			throw new BusinessException(2l);
		}
	}

}
