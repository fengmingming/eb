package net.sls.dto.pc.product;

public class OrderStockDto {
	
	private long id;
	private int number;
	
	public OrderStockDto(long id,int number){
		this.id = id;
		this.number = number;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

}
