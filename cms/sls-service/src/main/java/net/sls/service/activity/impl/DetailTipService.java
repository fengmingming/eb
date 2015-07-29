package net.sls.service.activity.impl;

import java.util.List;
import java.util.Map;

import net.sls.component.activity.IDetailTipComponent;
import net.sls.dto.activity.DetailTip;
import net.sls.service.activity.IDetailTipService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Service
public class DetailTipService implements IDetailTipService{
	
	@Autowired
	private IDetailTipComponent com;

	@SuppressWarnings("unchecked")
	@Override
	public ResBo<?> insertDetailTip(ReqBo reqBo) {
		com.insertDetailTip(reqBo.getParamT("tip", DetailTip.class), reqBo.getParamT("ids", List.class));
		return new ResBo<Object>();
	}

	@Override
	public ResBo<Pager<List<Map<String, Object>>>> selectDetailTipList(
			ReqBo reqBo) {
		return new ResBo<Pager<List<Map<String,Object>>>>(com.selectDetailTip(reqBo.getParamStr("areaCode"), reqBo.getParamInt("isAct"), reqBo.getParamInt("type"), reqBo.getParamInt("page"), reqBo.getParamInt("rows")));
	}

	@Override
	public ResBo<?> deleteDetailTip(ReqBo reqBo) {
		com.updateDetailTip(reqBo.getParamInt("tipId"));
		return new ResBo<Object>();
	}

	@Override
	public ResBo<Pager<List<Map<String, Object>>>> selectDetailTipRel(
			ReqBo reqBo) {
		return new ResBo<Pager<List<Map<String,Object>>>>(com.selectDetailTipRel(reqBo.getParamInt("type"), reqBo.getParamLong("tipId"), reqBo.getParamInt("page"), reqBo.getParamInt("rows")));
	}

}
