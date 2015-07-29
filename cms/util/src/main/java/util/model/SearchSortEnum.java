package util.model;

public enum SearchSortEnum {
	PRICE("price"),
	TIME("createtime"),
	SALES("number");
	
	private String field;
	SearchSortEnum(String field){
		this.field = field;
	}
	
	public String getField(){
		return this.field;
	}
}
