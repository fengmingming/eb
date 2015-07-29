package net.sls.service.pc.product;

import java.util.List;

import net.sls.dto.pc.product.Broadcast;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IBroadcastService {
	
	ResBo<List<Broadcast>> getBroadcastList(ReqBo reqBo);
	
}
