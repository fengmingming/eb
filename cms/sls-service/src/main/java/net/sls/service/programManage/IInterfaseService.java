package net.sls.service.programManage;

import java.util.List;

import net.sls.dto.programManage.Interfase;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IInterfaseService {
	/**
	 * 查询接口
	 * @param interfase  remark  page  rows
	 * @return  接口列表
	 */
	ResBo<Pager<List<Interfase>>> selectInterfacesList(ReqBo reqBo);
	/**
	 * 新增接口
	 * @param Interfase
	 * @return 新增后的接口信息
	 */
	ResBo<Interfase> insertInterfaces(ReqBo reqBo);
	/**
	 * 修改接口
	 * @param reqBo
	 * @return
	 */
	ResBo<?> updateInterfaces(ReqBo reqBo);

}
