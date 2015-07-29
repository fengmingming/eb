package net.sls.component.pc.product.impl;

import java.util.List;

import net.sls.component.pc.product.IBroadcastComponent;
import net.sls.dao.pc.product.PCBroadcastMapper;
import net.sls.dto.pc.product.Broadcast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BroadcastComponent implements IBroadcastComponent{
	
	@Autowired
	private PCBroadcastMapper broadcastMapper;

	@Override
	public List<Broadcast> getBroadcastList(String areaCode,int channelId) {
		// TODO Auto-generated method stub
		return broadcastMapper.getBroadcastList(areaCode,channelId);
	}
	

}
