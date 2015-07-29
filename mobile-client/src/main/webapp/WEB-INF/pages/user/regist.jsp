<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
	<title>手拉手触屏版</title>
	<%@include file="../common/common.jsp" %>
	<link rel='stylesheet' href='${staUrl }/css/user/regist.css' type='text/css' />
	<script type="text/javascript" src="${staUrl }/js/user/regist.js"></script>
	<script src='${staUrl }/js/common/md5.js' type='text/javascript' ></script>
</head>

<body>
	<div class="viewport">
		<div id="header">
			<div class="header_txt">免费注册</div>
			<div class="h_back" onclick="history.go(-1);"></div>
		</div>
		<div class="common_wrapper">
			<form id="registForm" action="${rootUrl }/user/register.htm" method="post">
				<div class="item">
					<input type="text" placeholder="请输入手机号码" id="r_mb_num" name="mobile" maxlength="11" value="" class="txt_input txt_username required">
				</div>
				<div class="item">
					<input type="password" placeholder="请输入密码(6位以上字符)" id="r_password" name="password" maxlength="16"  class="txt_input txt_password required">
		        </div>
		        <div class="item">
					<input type="password" placeholder="请再次输入密码" name="passwordRepeat" maxlength="16" class="txt_input txt_password required">
		        </div>
				<div class="item">
					<input type="text" placeholder="请输入短信验证码" name="mobileCode" id="r_cod" class="txt_input txt_vcode required">
					<a href="javascript:;" id="get_dxvcode">获取验证码</a>
		        </div>
		        <div class="item">
					<input type="text" placeholder="验证码" name="code" id="r_vcod" class="txt_input txt_vcode required">
					<a href="javascript:;" onclick="changeCodeImage();" id="get_vcode"><img id="vd_img" src="${rootUrl }/img/validate.htm" /></a>
		        </div>
			</form>
			<span class="zcxy">注册即视为同意&nbsp;<span>手拉手注册协议</span></span>
			<a href="javascript:;" class="reg_btn" onclick="return regist();">完成</a>
		</div>
	</div>
</body>
</html>