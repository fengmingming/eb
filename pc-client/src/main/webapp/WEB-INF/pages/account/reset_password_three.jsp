<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>完成</title>
<%@include file="../common/common.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/account/reset_password.css' type='text/css' />

</head>
<body>
	<div id="r_pwd">
		<div id="r_pwd_con">
			<div id="rpc_zhmm">找回密码</div>
			<div id="at_follow_3"></div>
			<ul class="clear" id="at_fw1_ul">
				<li>验证身份</li>
				<li>设置登录密码</li>
				<li class="afu_cur">完成</li>
			</ul>
			<ul id="at_fw3_res" class="clear">
				<li id="af3r1"></li>
				<li id="af3r2">恭喜设置密码成功！</li>
			</ul>
			<ul id="at_fw3_res" class="clear" style="display: none;">
				<li id="af3r3"></li>
				<li id="af3r4">XXXXX！</li>
			</ul>
		</div>
	</div>	
</body>
<%@include file="../common/foot.jsp" %>
</html>
