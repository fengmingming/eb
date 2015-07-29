package util.model;

public class OrderConst {

	public static String orderStatusName(Integer status){
		if(status == null){
			return null;
		}
		String name = null;
		switch(status){
		case 1: name = "未确认";break;
		case 2: name = "已确认";break;
		case 3: name = "处理中";break;
		case 4: name = "已发货";break;
		case 5: name = "确认收货";break;
		case 6: name = "退货中";break;
		case 7: name = "退货完成";break;
		}
		return name;
	}
	
	public static String orderStateName(Integer state){
		if(state == null){
			return null;
		}
		String name = null;
		switch(state){
		case 1: name = "正常";break;
		case 2: name = "取消";break;
		case 127: name = "删除";break;
		}
		return name;
	}
	
	public static String isPaidName(Integer isPaid){
		if(isPaid == null){
			return null;
		}
		String name = null;
		switch(isPaid){
		case 1: name = "未付款";break;
		case 2: name = "已付款";break;
		}
		return name;
	}
	
	public static String deliveryTypeName(Integer deliveryType){
		if(deliveryType == null){
			return null;
		}
		String name = null;
		switch(deliveryType){
		case 1: name = "自提";break;
		case 2: name = "送货";break;
		}
		return name;
	}
	
	public static String typeName(Integer shopType){
		String typeName1 = "代购";
		String typeName2 = "自购";
		if(shopType == 1){
			return typeName2;
		}
		return typeName1;
	}
}
