<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
	<title>手拉手触屏版</title>
	<%@include file="../common/common.jsp" %>
	<link rel='stylesheet' href='${staUrl }/css/order/order_payment_result.css' type='text/css' />
</head>

<body>
	<div class="viewport">
		<div id="header">
			<div class="header_txt">手拉手</div>
			<div class="h_back" onclick="history.go(-1);"></div>
			<%@include file="../common/mz_nav.jsp" %>
		</div>
		
		<p class="page_title">余额付款</p>
		<div class="duigou_logo"></div>
		<p class="pr_gmcg">购买成功！</p>
		<p class="pr_opt">
			<a href="${rootUrl }/pcenter/orders/detail/${orderId }.htm">查看订单</a>
			<a href="${rootUrl }/index.htm">继续逛逛</a>
		</p>
		<div class="sls_logo"></div>
		<p class="pr_ft">把服务送到家门口</p>
	</div>
</body>
</html>