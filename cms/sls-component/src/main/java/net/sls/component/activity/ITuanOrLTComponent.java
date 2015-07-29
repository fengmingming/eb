package net.sls.component.activity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import framework.web.Pager;
import net.sls.dto.activity.Activities;
import net.sls.dto.activity.ActivityGoods;

public interface ITuanOrLTComponent {

	public void insertTuanOrLT(Activities act,List<ActivityGoods> list);
	
	public void updateTuanOrLT(Activities act);
	
	public void updateTuanOrLTDetail(ActivityGoods ag);
	
	public Pager<List<Activities>> selectTuanOrLT(String areaCode,String actName,Integer ing,Integer state,Integer verify,Date startTime,Date endTime,int start,int number);
	
	public Pager<List<Map<String,Object>>> selectTuanOrLTDetail(long actId,int start,int number);
}
