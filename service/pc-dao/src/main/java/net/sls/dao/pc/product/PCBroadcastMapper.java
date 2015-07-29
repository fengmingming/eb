package net.sls.dao.pc.product;

import java.util.List;

import net.sls.dto.pc.product.Broadcast;

import org.apache.ibatis.annotations.Param;

public interface PCBroadcastMapper {

	List<Broadcast> getBroadcastList(@Param("areaCode") String areaCode, @Param("channelId") Integer channelId);

}
