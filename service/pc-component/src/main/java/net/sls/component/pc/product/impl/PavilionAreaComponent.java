package net.sls.component.pc.product.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sls.component.pc.product.IPavilionAreaComponent;
import net.sls.dao.pc.product.PCPavilionAreaMapper;
@Component
public class PavilionAreaComponent implements IPavilionAreaComponent{
	@Autowired
	private PCPavilionAreaMapper pavilionAreaMapper;

	@Override
	public List<Map<Object, Object>> selectpavilionAreasById(Integer pavilionId) {
		List<Map<Object, Object>> list = pavilionAreaMapper.selectpavilionAreasById(pavilionId);
		 return list;
	}
}
