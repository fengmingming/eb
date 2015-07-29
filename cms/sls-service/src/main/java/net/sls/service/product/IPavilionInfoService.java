package net.sls.service.product;

import java.util.List;
import java.util.Map;

import net.sls.dto.product.PavilionInfo;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IPavilionInfoService {

	public ResBo<Object> updatePavilionInfo(ReqBo reqBo);
	public ResBo<Object> insertOrUpdatePavilionInfo(ReqBo reqBo);
	public ResBo<Pager<List<Map<String,Object>>>> selectPavilionInfo(ReqBo reqBo);
	
	/**@author wangshaohui
	 * @Description: TODO 根据code找到亭子信息
	 * @param reqBo
	 * @return ResBo<PavilionInfo>
	 * @date 2015年1月27日 下午4:59:14
	 */
	public ResBo<PavilionInfo> selectPavilionByCode(ReqBo reqBo);
	
	public ResBo<Pager<List<Map<String,Object>>>> selectPavilionArea(ReqBo reqBo);
	
	public ResBo<?> upPavilionArea(ReqBo rqBo);
	
	public ResBo<?> addPavilionArea(ReqBo reqBo);
	
}
