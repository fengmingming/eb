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
			<!-- 修改支付密码：第三步：修改密码成功 -->
			<div id="at_result">
				<div id="at_result_txt">修改支付密码</div>
				<div id="at_follow_3"></div>
				<ul id="at_fw3_ul" class="clear">
					<li>验证身份</li>
					<li>修改支付密码</li>
					<li class="afu_cur">完成</li>
				</ul>
				<ul id="at_fw3_res" class="clear">
					<li id="af3r1"></li>
					<li id="af3r2">恭喜您修改密码成功！</li>
				</ul>
				<ul id="at_fw3_res" class="clear" style="display: none;">
					<li id="af3r3"></li>
					<li id="af3r4">XXXXX！</li>
				</ul>
			</div>			
			
		</div>
	</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
