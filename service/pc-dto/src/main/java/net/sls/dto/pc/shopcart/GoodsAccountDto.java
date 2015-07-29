package net.sls.dto.pc.shopcart;

import java.math.BigDecimal;
import java.util.List;

public class GoodsAccountDto {
	private List<GoodsDto> goodsDtoList;
	private BigDecimal amount;
	public List<GoodsDto> getGoodsDtoList() {
		return goodsDtoList;
	}
	public void setGoodsDtoList(List<GoodsDto> goodsDtoList) {
		this.goodsDtoList = goodsDtoList;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}
