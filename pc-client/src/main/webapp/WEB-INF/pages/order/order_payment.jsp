<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>余额支付</title>
<%@include file="../common/common.jsp" %>

<link rel='stylesheet' href='${staUrl }/css/order/order_payment.css' type='text/css' />
</head>
<body>
	<div id="order">
		<div id="ck_order_tit" class="clear">
			<span id="cot_txt">核对订单信息</span>
			<ul id="cart_flow" class="clear">
				<li id="c_f_img"></li>
				<li class="c_f_li">1.我的购物车</li>
				<li class="c_f_li">2.填写核对订单信息</li>
				<li class="c_f_li c_f_li_cur">3.成功提交订单</li>
			</ul>
		</div>
		
		<ul id="od_description" class="clear">
			<li id="odd_con">订单提交成功，请您尽快付款！<span id="oddn_num">订单号：8089800518</span></li>
			<li id="odd_price_txt">应付金额：</li>
			<li id="odd_price">￥29.00</li>
		</ul>
		
		<ul id="srzfmm">
			<li id="sm_txt">输入支付密码</li>
			<li>
				<table>
					<tr>
						<td class="sm_td">支付密码：</td>
						<td><input class="sm_inp" type="text" /></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td class="sm_td">验证码：</td>
						<td><input class="sm_inp" type="text" /></td>
						<td><img id="sm_img" src="" /></td>
					</tr>
					<tr>
						<td class="sm_td">&nbsp;</td>
						<td colspan="2"><a id="qdzf" href="javascript:;">确定支付</a><a id="wjzfmm" href="javascript:;">忘记支付密码</a></td>
					</tr>
				</table>
			</li>
		</ul>
	</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
					