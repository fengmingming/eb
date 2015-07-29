<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
	<title>手拉手触屏版</title>
	<%@include file="common/common.jsp" %>
	<link rel='stylesheet' href='${staUrl }/css/contact_we.css' type='text/css' />
</head>

<body>
	<div class="viewport">
		<div id="header">
			<div class="header_txt">联系我们</div>
			<div class="h_back" onclick="history.go(-1);"></div>
			<%@include file="common/mz_nav.jsp" %>
		</div>
		
		<div class="phone_logo"></div>
		<div class="cw_phone">服务热线：4000-365-020</div>
		<div class="sls_logo"></div>
		<p class="pr_ft">把服务送到家门口</p>
	</div>
</body>
</html>