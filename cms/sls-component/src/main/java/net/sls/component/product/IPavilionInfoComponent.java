package net.sls.component.product;

import java.util.List;
import java.util.Map;

import net.sls.dto.product.PavilionArea;
import net.sls.dto.product.PavilionInfo;
import framework.web.Pager;

public interface IPavilionInfoComponent {

	public void updatePavilionInfo(PavilionInfo record);
	public Long selectPavilionCode(String pavilionCode);
	public Long selectPavilionSn(String pavilionSn);
	public void insertPavilionInfo(PavilionInfo record);
	public Pager<List<Map<String,Object>>> selectPavilionInfo(String code,String pavilionCode,
			String pavilionSn,String mobile,String name,
			Integer start, Integer number);
	
	/**@author wangshaohui
	 * @Description: TODO 根据编号找到亭子信息
	 * @param pavilionCode
	 * @return PavilionInfo
	 * @date 2015年1月27日 下午4:20:24
	 */
	public PavilionInfo selectPavilionByCode(String pavilionCode);
	
	public Pager<List<Map<String,Object>>> selectPavilionArea(Integer community,Integer pavilionId,Integer start,Integer number);
	
	public void updatePavilionArea(PavilionArea pa);
	
	public void insertPavilionArea(List<PavilionArea> list);
	
}
