package net.sls.dto.ext.product;

import java.util.ArrayList;
import java.util.List;

import net.sls.dto.product.Goods;
import net.sls.dto.product.GoodsArea;
import net.sls.dto.product.GoodsCategory;
import net.sls.dto.product.GoodsDetail;
import net.sls.dto.product.GoodsStock;

public class GoodsAddBeanExt {
	
	private Goods goods;
	private GoodsCategory goodsCategory;
	private GoodsDetail goodsDetail;
	private GoodsStock goodsStock;
	private List<GoodsArea> gas = new ArrayList<GoodsArea>();
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public GoodsCategory getGoodsCategory() {
		return goodsCategory;
	}
	public void setGoodsCategory(GoodsCategory goodsCategory) {
		this.goodsCategory = goodsCategory;
	}
	public GoodsDetail getGoodsDetail() {
		return goodsDetail;
	}
	public void setGoodsDetail(GoodsDetail goodsDetail) {
		this.goodsDetail = goodsDetail;
	}
	public GoodsStock getGoodsStock() {
		return goodsStock;
	}
	public void setGoodsStock(GoodsStock goodsStock) {
		this.goodsStock = goodsStock;
	}
	public List<GoodsArea> getGas() {
		return gas;
	}

}
