package net.sls.dto.pc.shopcart;

import java.io.Serializable;

public class CartGood implements Serializable{

	private static final long serialVersionUID = 1L;

	private long id;
	
	private int number = 1;
	
	public CartGood(){
		super();
	}
	
	public CartGood(long id){
		this.id = id;
	}
	
	public CartGood(long id,int number){
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
