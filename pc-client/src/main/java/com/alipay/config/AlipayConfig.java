package com.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 */

public class AlipayConfig {
		

	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088811956721163";
	// 商户的私钥
	public static String key = "ohoakmgwyiw4s2wu8iyrv9x38lg6goi3";

	// 调试用，创建TXT日志文件夹路径
	public static String log_path =  "log/";
	
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "MD5";

}
