package net.sls.component.pc.product;

import java.util.List;

import net.sls.dto.pc.product.Broadcast;

public interface IBroadcastComponent {
	/**
	 * 根据区域查询轮播图
	 * @param areaCode
	 * @return
	 */
	List<Broadcast> getBroadcastList(String areaCode,int channelId);
}
