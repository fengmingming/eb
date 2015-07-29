package net.sls.dao.ext.user;

import java.util.List;

import net.sls.dto.user.Function;

import org.apache.ibatis.annotations.Param;


public interface FunctionMapperExt {

	
	/**
	 * @author fengmingming
	 * @description: 按父类节点id查询功能
	 * @param fatherId
	 * @return List<Function>
	 * @date 2014年12月9日 下午2:24:34
	 */
	List<Function> selectFunctionByPId(Integer fatherId);
	
	/**
	 * @author wangguojun
	 * @description: 可选择条件的查询功能并且分页
	 * @param fatherId
	 * @param url
	 * @param name
	 * @param start
	 * @param number
	 * @return List<Function>
	 * @date 2014年12月9日 下午2:25:08
	 */
	public List<Function> selectFunctionByFilter(@Param("fatherId") Integer fatherId,@Param("url") String url,@Param("name") String name);
	
	/**
	 * @author wangguojun
	 * @description: 查询总条数
	 * @param Function
	 * @return Integer
	 * @date 2014年12月9日 下午2:25:30
	 */
	public Integer countFunctionByFilter(Function function);
	
	int insertSelective(Function record);
	
	int insertBatchRoleFun(int id);
	
	Function selectFunctionByUrl(String url);
	
}
