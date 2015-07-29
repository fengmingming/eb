package net.sls.service.programManage;

import java.util.List;

import net.sls.dto.programManage.Methods;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IMethodsService {
	/**
	 * 查询方法列表
	 * @param reqBo
	 * @return
	 */
	ResBo<Pager<List<Methods>>> selectMethodsList(ReqBo reqBo);
	/**
	 * 查询方法信息
	 * @param reqBo
	 * @return
	 */
	Methods selectMethodInfo(ReqBo reqBo);
	/**
	 * 新增方法
	 * @param reqBo
	 * @return
	 */
	ResBo<Methods> insertMethods(ReqBo reqBo);
	/**
	 * 更新方法
	 * @param reqBo
	 * @return
	 */
	ResBo<?> updateMethods(ReqBo reqBo);

}
