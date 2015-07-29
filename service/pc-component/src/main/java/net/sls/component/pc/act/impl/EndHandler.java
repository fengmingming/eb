package net.sls.component.pc.act.impl;

import java.util.ArrayList;
import java.util.List;

import net.sls.component.pc.act.IHandler;
import net.sls.dto.pc.act.Buyer;
import net.sls.dto.pc.shopcart.ProductDto;
import net.sls.dto.pc.shopcart.SSettleGood;

public class EndHandler implements IHandler{
	
	@Override
	public List<ProductDto> handler(Buyer buyer, List<Long>ids, List<SSettleGood> sgs) {
		List<ProductDto> pds = new ArrayList<ProductDto>();
		ProductDto pd = null;
		int i = 0;
		for(SSettleGood ssg:sgs){
			pd = new ProductDto();
			pd.getSettleGoods().add(ssg);
			pd.setChecked(ssg.is_isSelect());
			pd.setNumber(ssg.get_number());
			i = pds.indexOf(pd);
			if(i == -1){
				pds.add(pd);
			}else{
				pd = pds.get(i);
				pd.initBuild().setNumber(pd.getNumber()+ssg.get_number());
			}
		}
		for(ProductDto p:pds){
			p.setPayPrice(p.getAmount());
			p.setPoints(p.getAmount());
		}
		return pds;
	}

	@Override
	public int getActType() {
		return Integer.MAX_VALUE;
	}

	@Override
	public String getActName() {
		return null;
	}

}
