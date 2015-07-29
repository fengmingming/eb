package util.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import framework.exception.BusinessException;

public enum CategoryEnum {
	FIRST(2), SECOND(3), THIRD(3), CODE(8); 
	private int length;
	private static final Map<Long,String> map = new ConcurrentHashMap<Long, String>();
	private static final Map<Long,String> mapName = new ConcurrentHashMap<Long, String>();
	
	private CategoryEnum(int length){
		this.length = length;
	}
	
	public int length(){
		return this.length;
	}
	
	public static CategoryEnum getCategoryEnum(int length){
		switch(length){
		case 2: return FIRST;
		case 3: return SECOND;
		case 5: return THIRD;
		case 8: return CODE;
		default: throw new BusinessException("CategoryEnum is not support value="+length);
		}
	}
	
	public static String getCategoryCode(Long id){
		return map.get(id);
	}
	
	public static void setCategoryCode(Long id,String name){
		map.put(id, name);
	}
	
	public static String getCategoryName(Long id){
		return mapName.get(id);
	}
	
	public static void setCategoryName(Long id,String name){
		mapName.put(id, name);
	}
	
}
