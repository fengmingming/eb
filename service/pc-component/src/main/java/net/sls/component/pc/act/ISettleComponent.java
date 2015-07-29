package net.sls.component.pc.act;

import java.util.List;

import net.sls.dto.pc.act.Buyer;
import net.sls.dto.pc.shopcart.Cart;
import net.sls.dto.pc.shopcart.SSettleGood;

public interface ISettleComponent {
	
	public List<IHandler> selectActHandler(List<Cart> carts,Buyer buyer,List<Long> ids);
	
	public List<SSettleGood> selectSettleGood(List<Cart> carts,Buyer buyer,List<Long> ids);
	
}
