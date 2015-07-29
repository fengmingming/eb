package net.sls.service.pc.user;

import java.util.List;
import java.util.Map;

import framework.web.ReqBo;
import framework.web.ResBo;

public interface ITopNService {
	
	//@Cacheable("cache3600")
	public ResBo<List<Map<Object, Object>>>  selectTopNGoodsByCityId(ReqBo reqBo);
}
