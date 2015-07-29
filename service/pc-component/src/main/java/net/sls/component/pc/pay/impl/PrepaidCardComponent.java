package net.sls.component.pc.pay.impl;

import net.sls.component.pc.pay.IPrepaidCardComponent;
import net.sls.dao.pc.pay.PCPrepaidCardMapper;
import net.sls.dto.pc.pay.PrepaidCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrepaidCardComponent implements IPrepaidCardComponent{

	@Autowired
	private PCPrepaidCardMapper pCardMapper;
	@Override
	public PrepaidCard selectPrepaidCard(String password) {
		return pCardMapper.selectPrepaidCard(password);
	}

}
