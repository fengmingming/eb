package net.sls.component.activity;

import java.util.List;

import framework.web.Pager;
import net.sls.dto.activity.Broadcast;


public interface IBroadcastComponent {

	void insertBroadcast(Broadcast bc);

	Pager<List<Broadcast>> selectBroadcastList(String areaCode,
			Integer start, Integer number);

	Broadcast updateBroadcast(Broadcast reqModel);

}
