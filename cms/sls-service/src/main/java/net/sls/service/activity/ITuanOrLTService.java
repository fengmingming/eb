package net.sls.service.activity;

import java.util.List;
import java.util.Map;

import net.sls.dto.activity.Activities;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface ITuanOrLTService {

	/**
	 * 
	 * 增加团购和限购活动
	 * 
	 * */
	public ResBo<?> addTuanOrLT(ReqBo reqBo);
	
	public ResBo<?> updateTuanOrLT(ReqBo reqBo);
	
	/**
	 * 
	 * 修改团购和限购信息
	 * 
	 * */
	public ResBo<?> updateTuanOrLTDetail(ReqBo reqBo);
	
	public ResBo<Pager<List<Activities>>> selTuanOrLT(ReqBo reqBo);
	
	public ResBo<Pager<List<Map<String,Object>>>> selTuanOrLTDetail(ReqBo reqBo);
}
