package util.model;

public enum AmountLogTypeEnum {

	in(1,"存入"),out(2,"支出");
	private int code;
	private String name;
	AmountLogTypeEnum(int code,String name){
		this.setCode(code);
		this.setName(name);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
}
