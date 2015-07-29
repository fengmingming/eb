<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单支付</title>
<%@include file="../common/common.jsp" %>

<link rel='stylesheet' href='${staUrl }/css/order/order_payment_result.css' type='text/css' />

</head>
<body>
	<div id="order">
		<div id="ck_order_tit">
			<span id="cot_txt">订单支付</span>
		</div>
	</div>
	
	<div id="pay_result">
		<div id="pr_div">
			<div id="pr_div_con">订单：${orderNum }&nbsp;支付成功！</div>
			<div id="pr_div_ope">
				<a id="pdo_index" href="${rootUrl }/main/index.htm">返回首页&gt;&gt;</a>
				<c:if test="${order_type == 'my_order' }">
					<a id="pdo_detail" href="${rootUrl }/order/orderDetail.htm?id=${orderId }">查看订单详情&gt;&gt;</a>
				</c:if>
				<c:if test="${order_type == 'dg_order'}">
					<a id="pdo_detail" href="${rootUrl }/order/pavilionOrderInfo.htm?id=${orderId }">查看订单详情&gt;&gt;</a>
				</c:if>
			</div>
		</div>
	</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
					