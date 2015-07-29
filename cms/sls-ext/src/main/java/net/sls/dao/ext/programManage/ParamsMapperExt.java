package net.sls.dao.ext.programManage;

import java.util.List;
import java.util.Map;

import net.sls.dto.programManage.Params;

import org.apache.ibatis.annotations.Param;

public interface ParamsMapperExt {
	/**
	 * 根据方法Id查询参数
	 * @param methodId
	 * @return
	 */
	List<Map<Object,Object>> selectParamListBymethodId(@Param("methodId") Integer methodId);
	/**
	 * 根据方法Id查询参数列表分页
	 * @param methodId
	 * @param start
	 * @param number
	 * @return
	 */
	List<Params> selectParamsPagerBymethodId(@Param("methodId") Integer methodId, @Param("start") Integer start,@Param("number") Integer number);
	/**
	 * 
	 * @param methodId
	 * @return
	 */
	long countParamsByFilter(@Param("methodId") Integer methodId);
	/**
	 * 根据条件查询参数
	 * @param paramsName
	 * @param reqparams
	 * @param i
	 * @param number
	 * @return
	 */
	List<Params> selectParams(@Param("methodId") Long methodId,@Param("paramsName") String paramsName,@Param("reqparams") String reqparams, @Param("start") Integer start,@Param("number") Integer number);
	/**
	 * 
	 * @param paramsName
	 * @param reqparams
	 * @return
	 */
	long countParamsByFilters(@Param("methodId") Long methodId,@Param("paramsName") String paramsName,@Param("reqparams") String reqparams);
	/**
	 * 新增参数
	 * @param params
	 * @return
	 */
	int insertParamsReturnId(Params params);
	/**
	 * 更新参数
	 * @param params
	 * @return
	 */
	int updateParamsByPrimaryKey(Params params);

}
