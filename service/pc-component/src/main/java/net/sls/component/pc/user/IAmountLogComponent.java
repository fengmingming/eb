package net.sls.component.pc.user;

import java.util.List;
import java.util.Map;

import framework.web.Pager;

public interface IAmountLogComponent {

	Pager<List<Map<Object, Object>>> selectAmountLogByUserId(Long userId,
			Integer type, Integer start, Integer number);


}
