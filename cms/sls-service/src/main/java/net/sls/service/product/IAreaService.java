package net.sls.service.product;

import java.util.List;

import net.sls.dto.product.Area;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * 
 *省市区级联管理
 * 
 */
public interface IAreaService {

	/**
	 * 根据父id找子列表
	 * @param pid:integer 父id
	 * 
	 * */
	public ResBo<List<Area>> selectAreaListByPid(ReqBo reqBo);
	
	/**
	 * 新增area
	 * @param area:Area 
	 *
	 */
	public ResBo<?> insertArea(ReqBo reqBo);
	/**
	 * 修改area
	 * @param area:Area 
	 *
	 */
	public ResBo<?> updateArea(ReqBo reqBo);
	
	public ResBo<Area> selectAreaById(ReqBo reqBo);
	
	public ResBo<Object> countSelectCode(ReqBo reqBo);
	
	public ResBo<String> selectAreaByCode(ReqBo reqBo);

	/**
	 * 由指定的id集合返回对应的Area对象集合
	 * @param rb
	 * @return
	 */
	public ResBo<List<Area>> selectAreasByCodes(ReqBo rb);

	public ResBo<Area> selectAreaCodeByPid(ReqBo reqBo);
	
}
