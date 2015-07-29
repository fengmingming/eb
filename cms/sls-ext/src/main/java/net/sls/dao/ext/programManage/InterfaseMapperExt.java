package net.sls.dao.ext.programManage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.sls.dto.programManage.Interfase;

public interface InterfaseMapperExt {

	long countInterfacesByFilter(@Param("interfase") String interfase, @Param("remark") String remark);

	List<Interfase> selectInterfacesList(@Param("interfase") String interfase, @Param("remark") String remark,
			@Param("start") Integer start,@Param("number") Integer number);
	/**
	 * 通过主键查询接口信息
	 * @param id
	 * @return
	 */
	Interfase selectInterfacesInfo(@Param("id") Long id);
	/**
	 * 插入接口返回Id
	 * @param interfase
	 * @return
	 */
	int insertInterfaseReturnId(Interfase interfase);
	/**
	 * 查询接口
	 * @param url
	 * @param interfase
	 * @param start
	 * @param number
	 * @return
	 */
	List<Interfase> selectInterfaces(@Param("url") String url,@Param("interfase") String interfase,@Param("start") Integer start,
			@Param("number") Integer number);
	/**
	 * 
	 * @param url
	 * @param interfase
	 * @return
	 */
	long countInterfaceByFilter(@Param("url") String url,@Param("interfase") String interfase);
	/**
	 * 更新接口
	 * @param interfase
	 * @return
	 */
	int updateByPrimaryKey(Interfase interfase);

}
