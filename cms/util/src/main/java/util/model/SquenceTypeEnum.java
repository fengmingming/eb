package util.model;

public enum SquenceTypeEnum {
	
	GOODSSKU(1,"商品货号");
	
	private int type;
	private String name;
	
	private SquenceTypeEnum(int type,String name){
		this.setType(type);
		this.setName(name);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
