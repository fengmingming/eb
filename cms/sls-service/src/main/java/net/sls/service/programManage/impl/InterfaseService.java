package net.sls.service.programManage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;
import net.sls.component.programManage.IInterfaseComponent;
import net.sls.dto.programManage.Interfase;
import net.sls.service.programManage.IInterfaseService;

@Service
public class InterfaseService implements IInterfaseService{

	@Autowired
	private IInterfaseComponent interfaseComponent;
	@Override
	public ResBo<Pager<List<Interfase>>> selectInterfacesList(ReqBo reqBo) {
		return new ResBo<Pager<List<Interfase>>>(interfaseComponent.selectInterfacesList(reqBo.getParamStr("interfase"),reqBo.getParamStr("remark"),reqBo.getParamInt("page"),reqBo.getParamInt("rows")));
	}
	@Override
	public ResBo<Interfase> insertInterfaces(ReqBo reqBo) {
		Pager<List<Interfase>> pager = interfaseComponent.selectInterfaces(reqBo.getReqModel(Interfase.class).getUrl(),reqBo.getReqModel(Interfase.class).getInterfase(), 1, Integer.MAX_VALUE);
		if(pager.getTotal() > 0){
			return new ResBo<Interfase>(14L);
		}
		Interfase interfase = interfaseComponent.insertInterfaces(reqBo.getReqModel(Interfase.class));
		return new ResBo<Interfase>(interfase);
	}
	@Override
	public ResBo<?> updateInterfaces(ReqBo reqBo) {
		interfaseComponent.updateInterfaces(reqBo.getReqModel(Interfase.class));
		return new ResBo<Object>();
	}

}
