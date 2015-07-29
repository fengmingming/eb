<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>手拉手触屏版</title>
	<%@include file="../common/common.jsp" %>
	<link rel='stylesheet' href='${staUrl }/css/user/my_wallet.css' type='text/css' />
</head>

<body>
	<div class="viewport">
		<div id="header">
			<div class="header_txt">我的钱包</div>
			<div class="h_back" onclick="history.go(-1);"></div>
			<a class="mw_szmx" href="${dynUrl }/pcenter/accountDetails.htm">收支明细</a>
		</div>
		
		<div class="wallet_logo"></div>
		<p class="mw_kyye">可用余额：</p>
		<p class="mw_jg">￥${user.amount }</p>
		<p class="mw_zhzt">账户状态：<span>
		<c:choose>
			<c:when test="${user.accountType == 1}">
				正常
			</c:when>
			<c:when test="${user.accountType == 2}">
				冻结
			</c:when>
		</c:choose>
		</span></p>
		<a class="mw_zjcz" href="${staUrl }/pcenter/recharge/index.htm">资金充值</a>
	</div>
</body>
</html>