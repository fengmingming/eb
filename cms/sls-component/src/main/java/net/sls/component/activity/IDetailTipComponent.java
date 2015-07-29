package net.sls.component.activity;

import java.util.List;
import java.util.Map;

import framework.web.Pager;
import net.sls.dto.activity.DetailTip;

public interface IDetailTipComponent {
	
	public void insertDetailTip(DetailTip tip,List<Long> ids);
	
	public void updateDetailTip(long tipId);
	
	public Pager<List<Map<String,Object>>> selectDetailTip(String areaCode,Integer isAct,Integer type,int start,int number);
	
	public Pager<List<Map<String,Object>>> selectDetailTipRel(int type,long tipId,int start,int number);

}
