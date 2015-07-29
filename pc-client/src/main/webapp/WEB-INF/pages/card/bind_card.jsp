<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:if test="${flag == null }">绑定社区一卡通</c:if><c:if test="${flag != null }">社区一卡通换卡</c:if></title>
<%@include file="../common/common.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/card/bind_card.css' type='text/css' />
<script src='${staUrl }/js/card/bind_card.js' type='text/javascript' ></script>
</head>
<body>
	<%@include file="../common/nav.jsp" %>
	
	<%@include file="../common/category_nav.jsp" %>
	
	<div id="u_info">
		<ul class="clear" id="url_here">
			<li>首页<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<!-- <li>我的手拉手<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li> -->
			<li>社区一卡通<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li class="uh_cur"><c:if test="${flag == null }">绑卡</c:if><c:if test="${flag != null }">换卡</c:if></li>
		</ul>
		<div id="u_info_con" class="clear">
			<%@include file="../common/menu.jsp" %>						
				<div id="at_identity">
					<div id="at_identity_txt"><c:if test="${flag == null }">绑定社区一卡通</c:if><c:if test="${flag != null }">社区一卡通换卡</c:if></div>
					<table id="at_bc_tb">
						<tr>
							<td class="at_bc_td">请输入手机号：</td>
							<td class="a_i_td"><input class="at_bc_inp" id="mobile" name="mobile" type="text" /></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="at_bc_td">请填写手机校验码：</td>
							<td class="a_i_td"><input class="at_bc_inp" type="text" id="captcha" name="mobileCode" maxlength="6"/></td>
							<td><a href="javascript:;" id="at_bc_fsm">发送校验码</a></td>
						</tr>
						<tr>
							<td class="at_bc_td"><c:if test="${flag == null }">请输入一卡通账号：</c:if><c:if test="${flag != null }">请输入新卡卡号：</c:if></td>
							<td class="a_i_td"><input id="ykt_num" class="at_bc_inp bind_card" type="password" /></td>
							<td>&nbsp;</td>
						</tr>
						<tr class="bc_tr_h">
							<td>&nbsp;</td>
							<td><a id="at_bd_sub"  href="javascript:;"><c:if test="${flag == null }">绑定</c:if><c:if test="${flag != null }">换卡</c:if></a></td>
							<td>&nbsp;</td>
						</tr>
					</table>
				</div>											
		</div>
	</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
