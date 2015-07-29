package net.sls.component.order;

import net.sls.dto.order.DeliveryAddress;

public interface IDeliveryAddressComponent {
	/**
	 * 根据订单Id查询收货地址
	 * @param id
	 * @return
	 */
	public DeliveryAddress selectAddressByOrderId(Long id);

}
