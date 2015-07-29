package net.sls.service.impl.pc.product;

import java.util.List;

import net.sls.component.pc.product.IBroadcastComponent;
import net.sls.dto.pc.product.Broadcast;
import net.sls.service.pc.product.IBroadcastService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.web.ReqBo;
import framework.web.ResBo;

@Service("broadcastService")
public class BroadcastService implements IBroadcastService{

	@Autowired
	private IBroadcastComponent broadcastComponent;

	@Override
	public ResBo<List<Broadcast>> getBroadcastList(ReqBo reqBo) {
		// TODO Auto-generated method stub
		ResBo<List<Broadcast>> resBo = new ResBo<List<Broadcast>>();
		List<Broadcast> list = broadcastComponent.getBroadcastList(reqBo.getParamStr("areaCode"),reqBo.getParamInt("channelId"));
		resBo.setResult(list);
		return resBo;
	}		
}
