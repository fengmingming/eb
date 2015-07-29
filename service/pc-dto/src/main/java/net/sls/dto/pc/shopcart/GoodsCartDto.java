package net.sls.dto.pc.shopcart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GoodsCartDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ProductDto> productDtoList ;
	private BigDecimal account;
	private int number;
	public List<ProductDto> getProductDtoList() {
		if (productDtoList != null) {
			return productDtoList;
		}else{
			return productDtoList = new ArrayList<ProductDto>();
		}
		
	}
	public void setProductDtoList(List<ProductDto> productDtoList) {
		this.productDtoList = productDtoList;
	}
	public BigDecimal getAccount() {
		if (account != null) {
			return account;
		}else{
			return account = new BigDecimal(0);
		}
	}
	public void setAccount(BigDecimal account) {
		this.account = account;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
}
