package net.sls.service.activity.impl;

import java.util.Date;
import java.util.List;

import net.sls.businessconstant.BusinessContant;
import net.sls.component.activity.IBroadcastComponent;
import net.sls.dto.activity.Broadcast;
import net.sls.service.activity.IBroadcastService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;
@Service
public class BroadcastService implements IBroadcastService{

	@Autowired
	private IBroadcastComponent ibc;
	@Override
	public ResBo<Broadcast> insertBroadcast(ReqBo reqBo) {
		Broadcast bc=reqBo.getReqModel(Broadcast.class);
		ibc.insertBroadcast(bc);
		return new ResBo<Broadcast>();
	}
	@Override
	public ResBo<Pager<List<Broadcast>>> selectBroadcastList(ReqBo reqBo) {
		return new ResBo<Pager<List<Broadcast>>>(ibc.selectBroadcastList(reqBo.getParamStr(BusinessContant.OPERAREAID),reqBo.getParamInt("page"),reqBo.getParamInt("rows")));
	}
	@Override
	public ResBo<Broadcast> updateBroadcast(ReqBo reqBo) {
		Broadcast bc=reqBo.getReqModel(Broadcast.class);
		bc.setModifytime(new Date());
		ibc.updateBroadcast(bc);
		return new ResBo<Broadcast>();
	}

}
