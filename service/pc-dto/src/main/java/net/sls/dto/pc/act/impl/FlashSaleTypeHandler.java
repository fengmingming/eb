package net.sls.dto.pc.act.impl;

import util.model.ActEnum;
import net.sls.dto.pc.act.AbstractHandler;

/**
 * 限时抢购活动
 * @author huzeyu 2015-03-18
 *
 */
public class FlashSaleTypeHandler extends AbstractHandler {
	private static final long serialVersionUID = 1L;

	public FlashSaleTypeHandler(Long actId){
		this.setActId(actId);
		this.setActName(ActEnum.FlashSale.getName());
		this.setActType(ActEnum.FlashSale.getActType());
	}
}
