package net.sls.component.pc.act;

import java.util.List;

import net.sls.dto.pc.act.Buyer;
import net.sls.dto.pc.shopcart.ProductDto;
import net.sls.dto.pc.shopcart.SSettleGood;

public interface IHandler {

	public List<ProductDto> handler(Buyer buyer, List<Long> ids, List<SSettleGood> pds);
	
	public int getActType();
	
	public String getActName();
}
