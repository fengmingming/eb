<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
	<title>手拉手触屏版</title>
	<%@include file="../common/common.jsp" %>
	<link rel='stylesheet' href='${staUrl }/css/user/find_password.css' type='text/css' />
	<script src='${staUrl }/js/common/md5.js' type='text/javascript' ></script>
	<script src='${staUrl }/js/user/find_password.js' type='text/javascript'></script>
</head>

<body>
	<div class="viewport">
		<div id="header">
			<div class="header_txt">找回密码</div>
			<div class="h_back" onclick="history.go(-1);"></div>
		</div>
		<div class="common_wrapper">
			<form action="" method="post">
				<div class="item">
					<input type="text" placeholder="请输入手机号码" value="" class="txt_input txt_username" id="mobile">
				</div>
				<div class="item">
					<input type="password" placeholder="请输入新密码(6位以上字符)" class="txt_input txt_password" id="pwd" >
		        </div>
		        <div class="item">
					<input type="password" placeholder="请再次输入密码" class="txt_input txt_password" id="newPwd" >
		        </div>
				<div class="item">
					<input type="text" placeholder="请输入短信验证码" class="txt_input txt_vcode" id="captcha" maxlength="6">
					<a href="javascript:void(0);" id="get_dxvcode">获取验证码</a>
		        </div>
		        <div class="item">
					<input type="text" placeholder="验证码" class="txt_input txt_vcode" id="code" maxlength="4">
					<a href="javascript:;" id="get_vcode"><img src="${rootUrl }/img/validate.htm" id="at_fw1_yzm" onclick="changeCaptcha('at_fw1_yzm')"/></a>
		        </div>
			</form>
			<a href="javascript:;" class="reg_btn">完成</a>
		</div>
	</div>
</body>
</html>