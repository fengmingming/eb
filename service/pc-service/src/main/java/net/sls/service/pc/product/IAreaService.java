package net.sls.service.pc.product;

import java.util.List;

import util.model.Area;
import util.model.ComboxDto;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IAreaService {
	public ResBo<List<ComboxDto>> getAreaList(ReqBo reqBo);
	
	public ResBo<List<Area>> getAreasList(ReqBo reqBo);
	
}
