package net.sls.component.programManage;

import java.util.List;

import net.sls.dto.programManage.Interfase;
import framework.web.Pager;

public interface IInterfaseComponent {
	/**
	 * 根据条件查询接口
	 * @param interfase
	 * @param remark
	 * @param start
	 * @param number
	 * @return
	 */
	Pager<List<Interfase>> selectInterfacesList(String interfase,
			String remark, Integer start,Integer number);
	/**
	 * 根据接口Id 查询接口信息
	 * @param iid
	 * @return
	 */
	Interfase selectInterfacesInfo(Long iid);
	/**
	 * 查询接口信息
	 * @param url
	 * @param interfase
	 * @param i
	 * @param maxValue
	 * @return
	 */
	Pager<List<Interfase>> selectInterfaces(String url,String interfase, Integer i,
			Integer maxValue);
	/**
	 * 新增接口
	 * @param reqModel
	 * @return
	 */
	Interfase insertInterfaces(Interfase interfase);
	/**
	 * 更新 接口
	 * @param reqModel
	 */
	Interfase updateInterfaces(Interfase reqModel);

}
