package net.sls.service.impl.pc.act;

import net.sls.component.pc.act.IDynpageComponent;
import net.sls.dto.pc.act.Dynpage;
import net.sls.service.pc.act.IDynpageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.web.ReqBo;
import framework.web.ResBo;

@Service("dynpageService")
public class DynpageService implements IDynpageService{

	@Autowired
	private IDynpageComponent iDynpageComponent;
	
	@Override
	public ResBo<Dynpage> selectByPrimaryKey(ReqBo reqBo) {
		ResBo<Dynpage> resBo = new ResBo<Dynpage>();
		Dynpage dynpage = iDynpageComponent.selectByPrimaryKey(reqBo.getParamLong("dynpageId"));
		resBo.setResult(dynpage);
		return resBo;
	}

}
