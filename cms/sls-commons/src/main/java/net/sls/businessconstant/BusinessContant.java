package net.sls.businessconstant;

public class BusinessContant {

	/**
	 * 
	 * 用户常量
	 * 
	 * */
	public static final String CMSUSER = "const_cms_user";
	
	public static final String ROLE = "const_cms_role";
	
	public static final String OPERAREAID = "oper_area_id";
	
	public static final String OPERAREAALL = "oper_area_all";
	/**
	 * 
	 * 订单状态
	 * 状态 1 未确认 2 确认 3 处理中 4 已发货 5 确认收货 6 退货中 7 退货完成
	 * */
	public static final int ORDERSTATUS_1 =1 ; 
	public static final int ORDERSTATUS_2 =2 ; 
	public static final int ORDERSTATUS_3 =3 ; 
	public static final int ORDERSTATUS_4 =4 ; 
	public static final int ORDERSTATUS_5 =5 ; 
	public static final int ORDERSTATUS_6 =6 ; 
	public static final int ORDERSTATUS_7 =7 ; 
	/**
	 * 
	 * 订单支付状态
	 * 状态 1未付款 2 已付款
	 * */
	public static final int ORDERISPAID_1 =1 ; 
	public static final int ORDERISPAID_2 =2 ; 
	
	/**
	 * 订单操作类型
	 * */
	public static final int ORDEROPTYPE_CMS = 1;
	public static final int ORDEROPTYPE_PC = 2;
	public static final int ORDEROPTYPE_AUTO = 3;
	
	/**
	 * 充值状态
	 * 
	 * */
	public static int RECHARGE_STATUS_SUC = 1;
	public static int RECHARGE_STATUS_ERR = 0;
	
	/**
	 * 用户余额操作类型
	 * 
	 * */
	public static int ACCOUNTLOGTYPE_INT = 1;
	public static int ACCOUNTLOGTYPE1_OUT = 2;
	
}
