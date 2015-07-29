package util.model;

public class CategoryDto {
	
	private String firstCode;
    
	private String secondCode;
	
	private String thirdCode;
	
	private String code;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * categoryDto 实例类型
	 * */
	private CategoryEnum categoryEnum;
	
	public CategoryEnum getCategoryEnum() {
		return categoryEnum;
	}

	public void setCategoryEnum(CategoryEnum categoryEnum) {
		this.categoryEnum = categoryEnum;
	}

	public CategoryDto(String code){
		this.categoryEnum = CategoryEnum.getCategoryEnum(code.length());
		switch(this.categoryEnum){
		case CODE: this.code = code; break;
		case FIRST: this.firstCode = code.substring(0, CategoryEnum.FIRST.length());break;
		case SECOND: this.firstCode = code.substring(0, CategoryEnum.SECOND.length());break;
		case THIRD: this.firstCode = code.substring(0, CategoryEnum.THIRD.length());break;
		
		}
	}

	public String getFirstCode() {
		return firstCode;
	}

	public void setFirstCode(String firstCode) {
		this.firstCode = firstCode;
	}

	public String getSecondCode() {
		return secondCode;
	}

	public void setSecondCode(String secondCode) {
		this.secondCode = secondCode;
	}

	public String getThirdCode() {
		return thirdCode;
	}

	public void setThirdCode(String thirdCode) {
		this.thirdCode = thirdCode;
	}
    
}
