package net.sls.service.pc.user;

import java.util.List;
import java.util.Map;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IAmountLogService {
	/**
	 * 进入我的钱包页
	 * @param reqBo
	 * @return
	 */
	public ResBo<Pager<List<Map<Object,Object>>>> getAmountLogByUserId(ReqBo reqBo);
	
	
	
}
