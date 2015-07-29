package net.sls.component.pc.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sls.component.pc.product.IPavilionInfoComponent;
import net.sls.dao.pc.product.PCPavilionInfoMapper;
import net.sls.dto.pc.product.PavilionInfo;
@Component
public class PavilionInfoComponent implements IPavilionInfoComponent{
	@Autowired
	private PCPavilionInfoMapper pavilionInfoMapper;
	@Override
	public PavilionInfo selectPavilionInfoByCode(String pavilionCode) {
		return pavilionInfoMapper.selectPavilionInfoByCode(pavilionCode);
	}

}
