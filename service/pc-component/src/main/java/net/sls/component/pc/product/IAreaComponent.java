package net.sls.component.pc.product;

import java.util.List;

import net.sls.dto.pc.product.Area;
import util.model.ComboxDto;

public interface IAreaComponent {
	/**
	 * 区域查询
	 * @param id
	 * @param pid
	 * @return
	 */
	List<ComboxDto> selectAreaList( Integer pid);
	/**
	 * 查询area
	 * @param areaId
	 * @return
	 */
	Area selectByPrimaryKey(Integer pavilionId);
	
	/**@author wangshaohui
	 * @Description: TODO 根据areaid找到areaName
	 * @param id
	 * @return String
	 * @date 2015年1月12日 上午9:52:14
	 */
	String selectAreaNameById(Integer id);
	
	util.model.Area selectAreaById(Integer pid);

}
