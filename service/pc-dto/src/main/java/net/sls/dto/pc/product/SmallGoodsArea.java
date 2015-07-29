package net.sls.dto.pc.product;

import java.io.Serializable;

public class SmallGoodsArea implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;

    private Long goodsId;

    private Boolean isLimit;

    private Integer number;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Boolean getIsLimit() {
		return isLimit;
	}

	public void setIsLimit(Boolean isLimit) {
		this.isLimit = isLimit;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}
