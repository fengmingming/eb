<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>余额支付</title>
<%@include file="../common/common.jsp" %>

<style type="text/css">
	#note_right { float: left; margin-left: 10px; width: 980px; background: #fff; height: 480px; overflow: hidden;}
		#nr_tit { font-size: 20px; color: #666; padding: 22px 0px 50px 22px;}
		/*.context { padding: 0px 35px; font-size: 15px; color: #666; line-height: 26px; text-indent: 30px;}*/
		#nr_img { width: 600px; height: 241px; background: url(${staUrl }/images/note/_yezf.png) no-repeat; margin: auto;}
</style>

</head>
<body>
	<%@include file="../common/nav.jsp" %>
	
	<%@include file="../common/category_nav.jsp" %>
	
	<div id="u_info">
		<ul class="clear" id="url_here">
			<li>首页<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li>余额支付</li>
		</ul>
		<div id="u_info_con" class="clear">
			<%@include file="note_menu.jsp" %>
			<div id="note_right">
				<div id="nr_tit">余额支付</div>
				<!-- <div class="context">您可以使用余额为您的订单付款，根据余额和订单金额的不同，您可以使用余额支付全部订单金额或者使用余额支付一部分，余下部分用其他方式付款。勾选使用余额支付后，会弹出余额支付安全验证的窗口（每次使用余额前都需要安全验证）。点击获取验证码，在等待短时间（正常情况下1分钟内）系统会给您绑定的手机号发送一条验证短信，其中包括校验码，请输入手机短信收到的校验码，并输入下方图片中的验证码。再点击确认，如果验证通过，余额就可以正常使用了。 </div> -->
				<div id="nr_img"></div>
			</div>
		</div>
	</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
