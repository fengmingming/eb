package net.sls.service.impl.pc.order;

import net.sls.dto.pc.product.SettlementsDto;
import net.sls.service.pc.order.IPay;

public class AliPay implements IPay {

	private SettlementsDto settle = null;
	
	@Override
	public void send() {
		// TODO 异步调用时使用
		
	}

	@Override
	public Object get() {
		// TODO 从第三方或余额那得到相应的数据
		// 1.组装url 
		// URL url = https://domain/biz/pay.do?a=xxx&b=xxx&c=xxx
		// httpClient hc = 
		// req.getResult();
		// Object obj = xx;
		// return obj;
		// RespBO 
		return null;
	}

	public SettlementsDto getSettle() {
		return settle;
	}

	public void setSettle(SettlementsDto settle) {
		this.settle = settle;
	}
	
	
	
}
