package net.sls.dao.ext.programManage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.sls.dto.programManage.Methods;

public interface MethodsMapperExt {
	
	/**
	 * 插入方法返回Id
	 * @param interfase
	 * @return
	 */
	int insertMethodsReturnId(Methods methods);
	/**
	 * 更新接口
	 * @param interfase
	 * @return
	 */
	int updateMethodsByPrimaryKey(Methods methods);
	
	long countMethodsByFilter(@Param("Iid") Integer Iid);
	/**
	 * 根据接口查询方法列表
	 * @param Iid
	 * @param start
	 * @param number
	 * @return
	 */
	List<Methods> selectMethodsList(@Param("Iid") Integer Iid, @Param("start") Integer start,@Param("number") Integer number);
	/**
	 * 根据id查询方法信息
	 * @param id
	 * @return
	 */
	Methods selectMethodInfo(@Param("id") Integer id);
	/**
	 * 查询方法
	 * @param methodName
	 * @param methodEn
	 * @param iid
	 * @param i
	 * @param maxValue
	 * @return
	 */
	List<Methods> selectMethods(@Param("methodName") String methodName,@Param("methodEn") String methodEn,@Param("Iid") Long Iid,
			@Param("start") Integer start,@Param("number") Integer number);
	/**
	 * 
	 * @param methodName
	 * @param methodEn
	 * @param iid
	 * @return
	 */
	long countMethodsByFilters(@Param("methodName") String methodName,@Param("methodEn") String methodEn,@Param("Iid") Long Iid);
	
}
