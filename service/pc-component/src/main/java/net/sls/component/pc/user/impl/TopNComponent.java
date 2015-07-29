package net.sls.component.pc.user.impl;
import java.util.List;
import java.util.Map;

import net.sls.component.pc.user.ITopNComponent;
import net.sls.dao.pc.user.PCGoodsTopNMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopNComponent implements ITopNComponent {
	
	@Autowired
	private PCGoodsTopNMapper topNMapper;

	@Override
	public List<Map<Object, Object>> selectTopNGoodsByCityId(String areaCode) {
		
		return topNMapper.selectTopNGoodsByCityId(areaCode);
	}

}
