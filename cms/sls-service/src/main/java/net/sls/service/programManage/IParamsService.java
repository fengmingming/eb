package net.sls.service.programManage;

import java.util.List;
import java.util.Map;

import net.sls.dto.programManage.Params;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IParamsService {
	/**
	 * 查询方法参数及接口方法名等信息
	 * @param reqBo
	 * @return
	 */
	ResBo<List<Map<Object,Object>>> selectParamListBymethodId(ReqBo reqBo);
	/**
	 * 查询方法参数信息
	 * @param reqBo
	 * @return
	 */
	ResBo<Pager<List<Params>>> selectParamsPagerBymethodId(ReqBo reqBo);
	/**
	 * 更新参数
	 * @param reqBo
	 * @return
	 */
	ResBo<?> updateParams(ReqBo reqBo);
	/**
	 * 新增参数
	 * @param reqBo
	 * @return
	 */
	ResBo<Params> insertParams(ReqBo reqBo);

}
