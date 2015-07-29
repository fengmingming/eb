package net.sls.service.pc.act;

import net.sls.dto.pc.act.Dynpage;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IDynpageService {

	ResBo<Dynpage> selectByPrimaryKey(ReqBo reqBo);

}
