package net.sls.service.activity;

import java.util.List;
import java.util.Map;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IDetailTipService {

	public ResBo<?> insertDetailTip(ReqBo reqBo);

	public ResBo<Pager<List<Map<String, Object>>>> selectDetailTipList(ReqBo reqBo);
	
	public ResBo<Pager<List<Map<String, Object>>>> selectDetailTipRel(ReqBo reqBo);
	
	public ResBo<?> deleteDetailTip(ReqBo rqBo);
}
