package net.sls.dto.pc.shopcart;

import java.math.BigDecimal;

/**
 * 购物车计算页面一条商品记录
 * @author Administrator
 *
 */
public class GoodsDto {
	
	private ProductDto productDto;
	private int number;
	private BigDecimal goodsAmount;
	
	public ProductDto getProductDto() {
		return productDto;
	}
	public void setProductDto(ProductDto productDto) {
		this.productDto = productDto;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public BigDecimal getGoodsAmount() {
		return goodsAmount;
	}
	public void setGoodsAmount(BigDecimal goodsAmount) {
		this.goodsAmount = goodsAmount;
	}
	/**
	 * number*productDto 总额
	 * @param number
	 * @param productDto
	 * @return
	 */
	public GoodsDto goodsAmount(int number,ProductDto productDto){
		GoodsDto goodsDto=new GoodsDto();
//		goodsDto.setNumber(number);
//		goodsDto.setProductDto(productDto);
//		goodsDto.setGoodsAmount(number*productDto.getPrice());
		return goodsDto;
	}
	
	
}
