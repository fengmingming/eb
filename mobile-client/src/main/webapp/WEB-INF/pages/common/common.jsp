<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" CONTENT="-1">           
<meta http-equiv="Cache-Control" CONTENT="no-cache">           
<meta http-equiv="Pragma" CONTENT="no-cache">

<link rel='stylesheet' href='${staUrl }/css/common/style.css' type='text/css' />

<script type="text/javascript" src="${staUrl }/js/common/jquery-1.8.0.min.js"></script>

<script type="text/javascript">
var _rootUrl = "${rootUrl }";
var _staUrl = "${staUrl }";
var _dynUrl = "${dynUrl }";
var _imgUrl = "${imgUrl }";

//判断当前用户是否登录
function _is_user_login(){
	var flag = false;
	$.ajax({
		type: "get",
		url: _rootUrl + "/user/isUserLogin.htm",
		async: false,
		cache: false,
		success: function(msg){
		   if(msg == "true"){
			   flag = true;
		   }
	   }
	});
	return flag;
}
</script>

<%@include file="loading.jsp"  %>

