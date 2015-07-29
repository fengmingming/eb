package net.sls.service.impl.pc.user;

import java.util.List;
import java.util.Map;

import net.sls.component.pc.user.IAmountLogComponent;
import net.sls.service.pc.user.IAmountLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;
@Service("amountLogService")
public class AmountLogService implements IAmountLogService{
	@Autowired
	private IAmountLogComponent amountLogComponent;

	@Override
	public ResBo<Pager<List<Map<Object,Object>>>> getAmountLogByUserId(ReqBo reqBo) {
		return new ResBo<Pager<List<Map<Object,Object>>>>(amountLogComponent.selectAmountLogByUserId(reqBo.getParamLong("userId"),reqBo.getParamInt("type"),reqBo.getParamInt("page"),reqBo.getParamInt("rows")));
	}

	
	
	
}
