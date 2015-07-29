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
<script src='${staUrl }/js/account/account_center.js' type='text/javascript' ></script>
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
		
			<!-- 修改登录密码：第二步：修改登录密码 -->
			<div id="at_updpwd">
				<div id="at_updpwd_txt">修改密码</div>
				<div id="at_follow_2"></div>
				<ul id="at_fw1_ul" class="clear">
					<li>验证身份</li>
					<li class="afu_cur">修改登录密码</li>
					<li>完成</li>
				</ul>
				<table id="at_fw2_tb">
					<tr>
						<td class="at_fw2_td">原密码：</td>
						<td class="a_i_td"><input class="at_fw2_inp" type="password" name="oldPwd" maxlength="20"/></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td class="at_fw2_td">新密码：</td>
						<td class="a_i_td"><input class="at_fw2_inp" type="password" name="newPwd" maxlength="20"/></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td class="at_fw2_td">确认密码：</td>
						<td class="a_i_td"><input class="at_fw2_inp" type="password" name="confirmPwd" maxlength="20"/></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><a id="at_fw2_sub" href="javascript:checkPwd(this);">确认修改</a></td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</div>			
		</div>
	</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
