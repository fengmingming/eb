package net.sls.component.product;

import java.util.List;

import net.sls.dto.product.Area;

public interface IAreaComponent {

	public List<Area> selectAreaByPid(Integer pid);
	
	public void insertArea(Area area);
	
	public void updateArea(Area area);
	
	public Area selectAreaById(Integer id);
	
	public String selectAreaCodeById(Integer id);
	
	public Long selectCode(String code);
	
	public Integer selectKey (String code);
	
	public Area selectAreaByCode(String code);
	
	public String selectAreaNameById(Integer id);

	List<Area> selectAreasByCodes(String[] paramStr);

	public Area selectAreaCodeByPid(Integer pid);
	
}
