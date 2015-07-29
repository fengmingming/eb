package util.model;

public enum OrderStatusEnum {
	status_1(1,"未确认"),
	status_2(2,"确认"),
	status_3(3,"处理中"),
	status_4(4,"已发货"),
	status_5(5,"确认收货"),
	status_6(6,"退货中"),
	status_7(7,"退货完成");
	
	private int status;
	private String name;
	
	OrderStatusEnum(int status,String name){
		this.status = status;
		this.name = name;
	}
	
	public int getStatus() {
		return status;
	}

	public String getName() {
		return name;
	}
}
