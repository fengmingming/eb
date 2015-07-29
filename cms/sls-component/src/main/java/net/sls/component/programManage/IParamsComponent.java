package net.sls.component.programManage;

import java.util.List;
import java.util.Map;

import net.sls.dto.programManage.Params;
import framework.web.Pager;

public interface IParamsComponent {
	/**
	 * 方法参数查询
	 * @param mid
	 * @return
	 */
	List<Map<Object,Object>> selectParamListBymethodId(Integer methodId);
	/**
	 * 方法参数查询
	 * @param methodId
	 * @param paramInt2
	 * @param paramInt3
	 * @return
	 */
	Pager<List<Params>> selectParamsPagerBymethodId(
			Integer methodId, Integer start, Integer number);
	/**
	 * 根据条件查询参数
	 * @param paramsName
	 * @param reqparams
	 * @param i
	 * @param maxValue
	 * @return
	 */
	Pager<List<Params>> selectParams(Long methodId,String paramsName, String reqparams,
			Integer start,Integer number);
	/**
	 * 新增参数
	 * @param reqModel
	 * @return
	 */
	Params insertParams(Params reqModel);
	/**
	 * 更新参数
	 * @param reqModel
	 * @return
	 */
	Params updateParams(Params reqModel);

}
