<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设置登陆密码</title>
<%@include file="../common/common.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/account/reset_password.css' type='text/css' />
<script src='${staUrl }/js/account/account_center.js' type='text/javascript' /></script>
</head>
<body>
	<div id="r_pwd">
		<div id="r_pwd_con">
			<div id="rpc_zhmm">找回密码</div>
			<div id="at_follow_2"></div>
			<ul class="clear" id="at_fw1_ul">
				<li>验证身份</li>
				<li class="afu_cur">设置登录密码</li>
				<li>完成</li>
			</ul>
			<table id="at_fw2_tb">
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
					<td><a id="at_fw2_sub" href="javascript:;" onclick="resetPwd()">确认修改</a></td>
					<td>&nbsp;</td>
				</tr>
			</table>
		</div>
	</div>	
</body>
<%@include file="../common/foot.jsp" %>
</html>
