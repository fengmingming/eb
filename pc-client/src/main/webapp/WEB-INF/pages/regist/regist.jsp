<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<%@include file="../common/common_no_head.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/regist/regist.css' type='text/css' />
<script type="text/javascript" src="${staUrl }/js/regist/regist.js"></script>
</head>
<body>
	
	<div id="regist_head">
		<ul id="rh_ul" class="clear">
			<li id="sls_log" onclick="toIndex();"></li>
			<li id="sls_txt"></li>
		</ul>
	</div>
	<ul id="regist" class="clear">
		<li id="r_cm"></li>
		<li id="r_con">
			<form id="registForm" action="${rootUrl }/user/regist.htm" method="post">
				<table>
					<tr>
						<td class="td_fs"><span class="r_star">*</span>用户名</td>
						<td class="td_required"><input class="r_ipt required r_spwd" id="r_name" type="text" name="userName" maxlength="20" placeholder="6-20位汉字、字母、数字、-、_组合"/></td>
					</tr>
					<tr>
						<td class="td_fs"><span class="r_star">*</span>请设置密码</td>
						<td class="td_required"><input class="r_ipt r_pwd required r_spwd" id="r_password" type="password" name="password" maxlength="16" placeholder="6-20位包含数字、字母、特殊字符至少两种"/></td>
					</tr>
					<tr>
						<td class="td_fs"><span class="r_star">*</span>请确认密码</td>
						<td class="td_required"><input class="r_ipt r_pwd required" type="password" name="passwordRepeat" maxlength="16"/></td>
					</tr>
					<tr>
						<td class="td_fs"><span class="r_star">*</span>手机号</td>
						<td class="td_required"><input class="r_ipt required" id="r_mb_num" type="text" name="mobile" maxlength="11"/></td>
					</tr>
					<tr>
						<td class="td_fs"><span class="r_star">*</span>验证手机</td>
						<td class="td_required"><input type="text" id="r_cod" class="required" name="mobileCode" maxlength="6"/><a id="r_cod_a" href="javascript:;">获取短信验证码</a></td>
					</tr>
					
					<%-- <%@include file="../common/booth_area.jsp" %>--%>
					<tr>
						<td class="td_fs"><span class="r_star">*</span>验证码</td>
						<td class="td_required">
							<input id="r_vcod" type="text" name="code" class="required" maxlength="6"/>
							<img id="vd_img" src="${rootUrl }/img/validate.htm" />
							<span id="vd_a_txt">看不清，</span>
							<a id="vd_a_upd" href="javascript:;" onclick="changeCodeImage();">换一张</a>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>
							<p><input type="checkbox" name="isRead" id="inp_read" />我已阅读并同意<a href="${rootUrl }/note/registAgreement.htm" class="r_log_a">《手拉手用户注册协议》</a></p>
							<p id="r_last_p">已经是手拉手会员？<a href="javascript:;" id="dologin" class="r_log_a">立即登录</a></p>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><a id="reg_btn" href="javascript:;" onclick="return regist();">立即注册</a></td>
					</tr>
				</table>
			</form>
		</li>
	</ul>
	<%@include file="../common/foot.jsp" %>
</body>
</html>