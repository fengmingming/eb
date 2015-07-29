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
			<!-- 修改支付密码：第一步：验证身份 -->
				<div id="at_identity">
					<div id="at_identity_txt">修改支付密码</div>
					<div id="at_follow_1"></div>
					<ul id="at_fw1_ul" class="clear">
						<li class="afu_cur">验证身份</li>
						<li>修改支付密码</li>
						<li>完成</li>
					</ul>
					<table id="at_fw1_tb">
						<tr>
							<td class="at_fw1_td">已验证手机：</td>
							<td><span>${mobile }</span></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="at_fw1_td">请填写手机校验码：</td>
							<td class="a_i_td"><input class="at_fw1_inp" type="text" id="captcha" name="captcha" maxlength="6"/></td>
							<td><a href="javascript:;" id="at_fw1_fsm">发送校验码</a></td>
						</tr>
						<tr>
							<td class="at_fw1_td">验证码：</td>
							<td class="a_i_td"><input class="at_fw1_inp" type="text" id="picCode" name="picCode" maxlength="4"/></td>
							<td>
								<img src="${rootUrl }/img/validate.htm" id="at_fw1_yzm" onclick="changeCaptcha('at_fw1_yzm');"/>
								<span id="czk_ex">看不清，<a href="javascript:;" onclick="changeCaptcha('at_fw1_yzm');">换一张</a></span>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><a id="at_fw1_sub" href="javascript:checkCaptcha();">提交</a></td>
							<td>&nbsp;</td>
						</tr>
					</table>
				</div>											
		</div>
	</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
