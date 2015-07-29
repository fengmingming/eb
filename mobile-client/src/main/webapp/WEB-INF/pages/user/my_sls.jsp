<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
	<title>手拉手触屏版</title>
	<%@include file="../common/common.jsp" %>
	<link rel='stylesheet' href='${staUrl }/css/user/my_sls.css' type='text/css' />
</head>

<body>
	<div class="viewport">
		<div id="header">
			<div class="header_txt">我的手拉手</div>
			<!-- <div class="h_back" onclick="history.go(-1);"></div> -->
			<%@include file="../common/mz_nav.jsp" %>
		</div>
		
		<ul class="clear user_mes">
			<li class="user_img"><img src="${staUrl }/images/user_logo.png" /></li>
			<li class="user_txt">
				<div>${user.userName }&nbsp;&nbsp;&nbsp;手拉手注册会员</div>
				<div>余额：&nbsp;&nbsp;&nbsp;${user.amount }</div>
			</li>
		</ul>
		<ul class="ms_opt">
			<li style="border-bottom: solid 1px #e6e6e6;">
				<a href="${dynUrl }/pcenter/orders.htm?type=1">
					我的订单
					<span class="ms_jt"></span>
				</a>
			</li>
			<li style="border-bottom: solid 1px #e6e6e6;">
				<a href="${dynUrl }/pcenter/address/index.htm">
					收货地址
					<span class="ms_jt"></span>
				</a>
			</li>
			<li style="border-bottom: solid 1px #e6e6e6;">
				<a href="${dynUrl }/pcenter/wallet.htm">
					我的钱包
					<span class="ms_jt"></span>
				</a>
			</li>
			<li style="border-bottom: solid 1px #e6e6e6;">
				<a href="${staUrl }/pcenter/recharge/index.htm">
					资金充值
					<span class="ms_jt"></span>
				</a>
			</li>
			<li>
				<a href="${staUrl }/pcenter/coupon/myCoupon.htm?type=1">
					我的优惠券
					<span class="ms_jt"></span>
				</a>
			</li>
		</ul>
		<a href="${staUrl }/contact_we.htm" class="ms_lxwm">
			联系我们
			<span class="ms_jt ms_op_jt"></span>
		</a>
	</div>
</body>
</html>