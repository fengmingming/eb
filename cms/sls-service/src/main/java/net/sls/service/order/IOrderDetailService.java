package net.sls.service.order;

import java.util.List;
import java.util.Map;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IOrderDetailService {
	/**
	 * 查询订单详情列表
	 * @param reqBo
	 * @return
	 */
	ResBo<Pager<List<Map<String, Object>>>> selectOrderDetailList(ReqBo reqBo);
	

}
