<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
	<title>手拉手触屏版</title>
	<%@include file="../common/common.jsp" %>
	<link rel='stylesheet' href='${staUrl }/css/user/login.css' type='text/css' />
	<script src='${staUrl }/js/user/login.js' type='text/javascript' ></script>	
	<script src='${staUrl }/js/common/md5.js' type='text/javascript' ></script>
</head>

<body>
	<div class="viewport">
		<div id="header">
			<div class="header_txt">登录</div>
			<div class="h_back" onclick="history.go(-1);"></div>
		</div>
		<div class="common_wrapper">
			<form action="" method="post">
				<div class="item">
					<input type="text" placeholder="请输入手机号/用户名" value="${lastUser }" name="username" class="txt_input txt_username">
				</div>
				<div class="item">
					<input type="password" placeholder="请输入密码(6位以上字符)" name="password" class="txt_input txt_password">
		        </div>
				<div class="item">
					<a href="javascript:loginValidate();" class="btn_login"><span id="login">登录</span></a>
					<input type="hidden" value="${flag }" id="flag"/>
				</div>
			</form>
			<!-- 
			<ul class="clear c_w_jz">
				<li></li>
				<li class="c_w_jz_txt">记住用户名和密码</li>
			</ul>
			 -->
			<ul class="clear m_z_s">
				<li class="mfzc"><a href="${rootUrl }/user/regist.htm">免费注册</a></li>
				<li class="zhmm"><a href="${rootUrl }/user/findPassword.htm">找回密码</a></li>
			</ul>
			<!-- 
			<div class="dsfdl">第三方登录：</div>
			<ul class="clear dsf_ul">
				<li class="l_qq"></li>
				<li class="l_wx"></li>
			</ul>
			 -->
		</div>
	</div>
</body>
</html>