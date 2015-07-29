package net.sls.service.impl.pc.user;

import java.util.List;
import java.util.Map;

import net.sls.component.pc.user.ITopNComponent;
import net.sls.service.pc.user.ITopNService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.web.ReqBo;
import framework.web.ResBo;

@Service("topNService")
public class TopNService implements ITopNService {

	@Autowired
	private ITopNComponent topNComponent;
	
	@Override
	public ResBo<List<Map<Object,Object>>> selectTopNGoodsByCityId(ReqBo reqBo) {
		String areaCode = reqBo.getParamStr("areaCode");
		ResBo<List<Map<Object,Object>>> resBo = new ResBo<List<Map<Object,Object>>>();
		resBo.setResult(topNComponent.selectTopNGoodsByCityId(areaCode));
		return resBo;		
	}

}
