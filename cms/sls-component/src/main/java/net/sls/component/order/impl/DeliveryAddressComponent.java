package net.sls.component.order.impl;

import java.util.List;

import net.sls.component.order.IDeliveryAddressComponent;
import net.sls.dao.order.DeliveryAddressMapper;
import net.sls.dto.order.DeliveryAddress;
import net.sls.dto.order.DeliveryAddressExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryAddressComponent implements IDeliveryAddressComponent{
	
	@Autowired
	private DeliveryAddressMapper deliveryAddressMapper;
	@Override
	public DeliveryAddress selectAddressByOrderId(Long id) {
		DeliveryAddressExample e=new DeliveryAddressExample();
		e.createCriteria().andOrderIdEqualTo(id);
		List<DeliveryAddress> deliveryAddressList=deliveryAddressMapper.selectByExample(e);
		if (deliveryAddressList.size() > 0) {
			return deliveryAddressList.get(0);
		}else{
			return null;
		}
	}

}
