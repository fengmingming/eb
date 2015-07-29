package net.sls.component.programManage;

import java.util.List;

import net.sls.dto.programManage.Methods;
import framework.web.Pager;

public interface IMethodsComponent {
	/**
	 * 根据接口Id查询接口下所有方法  列表
	 * @param Iid
	 * @param start
	 * @param number
	 * @return
	 */
	Pager<List<Methods>> selectMethodsList(Integer Iid, Integer start,Integer number);
	/**
	 * 根据方法Id 查询方法信息
	 * @param methodId
	 * @return
	 */
	Methods selectMethodInfo(Integer methodId);
	/**
	 * 新增方法
	 * @param reqModel
	 * @return
	 */
	Methods insertMethods(Methods reqModel);
	/**
	 * 更新方法
	 * @param reqModel
	 * @return
	 */
	Methods updateMethods(Methods reqModel);
	
	/**
	 * 查询方法
	 * @param methodName
	 * @param methodEn
	 * @param Iid
	 * @param i
	 * @param maxValue
	 * @return
	 */
	Pager<List<Methods>> selectMethods(String methodName, String methodEn,
			Long Iid, Integer i, Integer maxValue);

}
