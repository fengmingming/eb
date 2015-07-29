package net.sls.component.activity;

import java.util.List;
import java.util.Map;

import net.sls.dto.activity.Dynpage;
import net.sls.dto.activity.DynpageGoods;
import framework.web.Pager;

public interface IDynpageComponent {

	public Long insertDynpage(Dynpage page,List<DynpageGoods> list);
	
	public void updateDynpage(Dynpage dg);
	
	public void deleteDynpageGoods(Long id);
	
	public Pager<List<Dynpage>> selectDynpage(int start,int number);
	
	public Pager<List<Map<String,Object>>> selectDynPageGoods(Long id,int start, int number);
	
	public void updateDynpageGoods(DynpageGoods dg);
}
