package net.sls.service.pc.product;

import java.util.List;
import java.util.Map;

import framework.web.ReqBo;
import framework.web.ResBo;

public interface IPavilionInfoService {

	public ResBo<Map<Object,Object>> getPavilionInfoById(ReqBo reqBo);

	public ResBo<List<Map<Object, Object>>> getpavilionAreaById(ReqBo reqBo);
	
}
