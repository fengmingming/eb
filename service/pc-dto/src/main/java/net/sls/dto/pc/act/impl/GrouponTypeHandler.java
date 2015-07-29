package net.sls.dto.pc.act.impl;

import net.sls.dto.pc.act.AbstractHandler;
import util.model.ActEnum;

/**
 * 团购活动
 * @author huzeyu 2015-03-18
 *
 */
public class GrouponTypeHandler extends AbstractHandler {
	private static final long serialVersionUID = 1L;

	public GrouponTypeHandler(Long actId){
		this.setActId(actId);
		this.setActName(ActEnum.Groupon.getName());
		this.setActType(ActEnum.Groupon.getActType());
	}
}
