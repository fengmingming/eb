<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="shortcut icon" type="image/ico" href="${staUrl }/images/favicon.ico"> 
<link rel='stylesheet' href='${staUrl }/css/common/style.css' type='text/css' />
<link rel='stylesheet' href='${staUrl }/css/common/top.css' type='text/css' />
<link rel='stylesheet' href='${staUrl }/css/common/head.css' type='text/css' />
<link rel='stylesheet' href='${staUrl }/css/common/foot.css' type='text/css' />

<script type="text/javascript" src="${staUrl }/js/common/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${staUrl }/js/common/md5.js"></script>

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

<%@include file="top.jsp"  %>
<%@include file="login_popup.jsp" %>
