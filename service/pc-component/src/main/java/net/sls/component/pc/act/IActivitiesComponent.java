package net.sls.component.pc.act;

import net.sls.dto.pc.act.Activities;

public interface IActivitiesComponent {
	/**
	 * 根据Id查询
	 * @param actId
	 * @return Activities
	 */
	Activities selectActivities(Long actId);

}
