<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>账户安全</title>
<%@include file="../common/common.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/account/account_center.css' type='text/css' />
</head>
<body>
	<%@include file="../common/nav.jsp" %>
	
	<%@include file="../common/category_nav.jsp" %>
	
	<div id="u_info">
		<ul class="clear" id="url_here">
			<li>首页<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li>我的手拉手<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li class="uh_cur">账户安全</li>
		</ul>
		<div id="u_info_con" class="clear">
			<%@include file="../common/menu.jsp" %>
			<div id="at_center">
				<div id="at_center_txt">安全中心</div>
				<ul id="at_upd" class="clear">
					<li id="au_gou"></li>
					<li class="au_pwd">登录密码</li>
					<li class="au_des"><span id="ag_des">互联网账号存在被盗风险，建议您定期更改密码以保护账户安全</span></li>
					<li><a id="au_a" href="${rootUrl }/account/modifyLoginPwd1.htm">修改登录密码</a></li>
				</ul>
				<c:choose><c:when test="${payExist == 1 }">
				<ul id="at_zf" class="clear">
					<li id="au_gou"></li>
					<li class="au_pwd">支付密码</li>
					<li class="au_des"><span id="ag_des">启用支付密码后，在使用账户资产时，需要通过支付密码进行身份认证</span></li>
					<li><a id="au_a" href="${rootUrl }/account/modifyPayPwd1.htm">修改支付密码</a></li>
				</ul>
				</c:when>
				<c:otherwise>
				<ul id="at_zf" class="clear">
					<li id="az_gan"></li>
					<li class="au_pwd">支付密码</li>
					<li class="au_des"><span id="az_des">启用支付密码后，在使用账户资产时，需要通过支付密码进行身份认证</span></li>
					<li><a id="az_a" href="${rootUrl }/account/modifyPayPwd1.htm">立即开启</a></li>
				</ul>
				</c:otherwise>
				</c:choose>
			</div>						 
		</div>
	</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
