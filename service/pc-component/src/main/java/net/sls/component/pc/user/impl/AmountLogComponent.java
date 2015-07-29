package net.sls.component.pc.user.impl;

import java.util.List;
import java.util.Map;

import net.sls.component.pc.user.IAmountLogComponent;
import net.sls.dao.pc.user.PCAmountLogMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.web.Pager;
@Component
public class AmountLogComponent implements IAmountLogComponent{
	
	@Autowired
	private PCAmountLogMapper amountLogMapper;
	@Override
	public Pager<List<Map<Object,Object>>> selectAmountLogByUserId(Long userId,
			Integer type, Integer start, Integer number) {
		long count = amountLogMapper.countAmountLogByUserId(userId,type);
		List<Map<Object,Object>> list = amountLogMapper.selectAmountLogByUserId(userId, type, (start-1)*number, number);
		Pager<List<Map<Object,Object>>> pager = new Pager<List<Map<Object,Object>>>(list,count);
		return pager;
	}
	

}
