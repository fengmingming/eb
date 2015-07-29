package net.sls.dto.ext.order;

public class ExportDto {

	private String sku;
	private String goodsName;
	private int number;
	private String pavilionCode;
	private String pavilionName;
	private String orderNum;
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getPavilionCode() {
		return pavilionCode;
	}
	public void setPavilionCode(String pavilionCode) {
		this.pavilionCode = pavilionCode;
	}
	public String getPavilionName() {
		return pavilionName;
	}
	public void setPavilionName(String pavilionName) {
		this.pavilionName = pavilionName;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
}
