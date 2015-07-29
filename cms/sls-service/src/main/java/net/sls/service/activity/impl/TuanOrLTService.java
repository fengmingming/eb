package net.sls.service.activity.impl;

import java.util.List;
import java.util.Map;

import net.sls.businessconstant.BusinessContant;
import net.sls.component.activity.ITuanOrLTComponent;
import net.sls.dto.activity.Activities;
import net.sls.dto.activity.ActivityGoods;
import net.sls.service.activity.ITuanOrLTService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Service
public class TuanOrLTService implements ITuanOrLTService{
	
	@Autowired
	private ITuanOrLTComponent component;

	@SuppressWarnings("unchecked")
	@Override
	public ResBo<?> addTuanOrLT(ReqBo reqBo) {
		component.insertTuanOrLT(reqBo.getParamT("act", Activities.class), (List<ActivityGoods>) reqBo.getParamT("list", List.class));
		return new ResBo<Object>();
	}

	@Override
	public ResBo<?> updateTuanOrLTDetail(ReqBo reqBo) {
		component.updateTuanOrLTDetail(reqBo.getReqModel(ActivityGoods.class));
		return new ResBo<Object>();
	}

	@Override
	public ResBo<Pager<List<Activities>>> selTuanOrLT(ReqBo reqBo) {
		return new ResBo<Pager<List<Activities>>>(component.selectTuanOrLT(reqBo.getParamStr(BusinessContant.OPERAREAID),reqBo.getParamStr("actName"),reqBo.getParamInt("ing"),reqBo.getParamInt("state"), reqBo.getParamInt("isVerify"), reqBo.getParamDate("startTime"), reqBo.getParamDate("endTime"), reqBo.getParamInt("page"), reqBo.getParamInt("rows")));
	}

	@Override
	public ResBo<Pager<List<Map<String,Object>>>> selTuanOrLTDetail(ReqBo reqBo) {
		return new ResBo<Pager<List<Map<String,Object>>>>(component.selectTuanOrLTDetail(reqBo.getParamLong("actId"), reqBo.getParamInt("page"), reqBo.getParamInt("rows")));
	}

	@Override
	public ResBo<?> updateTuanOrLT(ReqBo reqBo) {
		component.updateTuanOrLT(reqBo.getReqModel(Activities.class));
		return new ResBo<Object>();
	}

}
