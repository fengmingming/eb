package com.wx.config;


public class WxPayConfig {
	
	//公众号Appid 
	public static String app_id = "wxb3c9f7f8c7f62a1d";
	//公众好对应商户id
	public static String mchid = "1225179702";
	//商户支付密钥Key
	public static String key = "afsTrin88mGGpP2qO71HsDR4DZdIWX2R";
	//公众号Appsecret 
	public static String app_secret = "06dfca499795a8a9c530244727d9212b";
	//应用授权作用域scope，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
	public static String scope = "snsapi_base";
	//重定向后会带上state参数，开发者可以填写任意参数值 [会传递回回调地址]
	public static String state = "STATE";
	//支付成功后回调通知地址
	public static String notify_url = "http://www.365020.com/mobile/wx/execute.htm";	
	//支付商品描述
	public static String body = "手拉手购物商品";
	//获取openid的url
	public static String openid_url = "https://api.weixin.qq.com/sns/oauth2/access_token";
	//获取prepayid的url [统一支付接口]
	public static String prepayid_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
}
