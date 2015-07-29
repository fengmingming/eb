<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资金充值</title>
<%@include file="../common/common.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/user/account_recharge.css' type='text/css' />
<script type='text/javascript' src='${staUrl }/js/account/account_recharge.js'></script>
</head>
<body>
	<%@include file="../common/nav.jsp" %>
	<%@include file="../common/category_nav.jsp" %>
	<div id="u_info">
		<ul class="clear" id="url_here">
			<li>首页<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li>我的手拉手<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li class="uh_cur">资金充值</li>
		</ul>
		<div id="u_info_con" class="clear">
			<%@include file="../common/menu.jsp" %>
			<div id="zhyecz">
				<!-- <div id="zhyecz_txt">账户余额充值</div> -->
				<ul class="clear" id="tab_txt">
					<li id="zhyecz_li" class="tab_txt_cur">账户余额充值</li>
					<li id="czkcz_li">充值卡充值</li>
				</ul>
				<form id="recharge_form" action="${rootUrl }/account/recharge.htm" method="post" target="_blank" onsubmit="return checkInput()">
					<table id="zhyecz_tb">
						<tr>
							<td width="95"><span class="r_star">*</span>充值金额：</td>
							<td><input id="zt_inp" type="text" name="money" maxlength="8"/></td>
						</tr>
						<tr>
							<td>会员备注：</td>
							<td><textarea id="zt_tta" name="remark"></textarea></td>
						</tr>
						<tr>
							<td>支付方式：</td>
							<td>
								<input id="zfbzf" name="zfbzf" type="radio" checked="checked"/>
								<label for="zfbzf" id="zfbzf_ll">支付宝支付</label>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td id="zfbzf_opa">
								<a id="tjsq" href="javascript:;" onclick="submitForm()">提交申请</a>
								<a id="czbd" href="javascript:$('#recharge_form')[0].reset();">重置表单</a>
							</td>
						</tr>
					</table>
				</form>
				<form id="re_card_form" action="" method="post">
					<table id="czkcz_tb">
						<tr>
							<td class="right_td">充值卡密码：</td>
							<td>
								<input class="czk_pwd" type="text" id="t1" onkeyup="return T1_onkeyup()" maxlength="4" />								 
								-
								<input class="czk_pwd" type="text" id="t2" onkeyup="return T2_onkeyup()" maxlength="4" />
								-
								<input class="czk_pwd" type="text" id="t3" onkeyup="return T3_onkeyup()" maxlength="4" />
								-
								<input class="czk_pwd" type="text" id="t4" onkeyup="return T4_onkeyup()" maxlength="4" />
								<span style="font-size: 12px; margin-left: 15px;">16位卡密码</span>
							</td>
						</tr>
						<tr>
							<td class="right_td">验证码：</td>
							<td>
								<input id="czk_vcode" type="text" maxlength="4"/>
								<img src="${rootUrl }/img/validate.htm" id="czk_vimg" onclick="changeCaptcha('czk_vimg');"/>
								<span id="czk_ex">看不清，<a href="javascript:;" onclick="changeCaptcha('czk_vimg');">换一张</a></span>
							</td>
						</tr>
						<!-- 
						<tr id="cap_val" style="display: none;">
							<td>&nbsp;</td>
							<td><input type="text" value="验证码不能为空" /></td>
						</tr>
						 -->
						<tr>
							<td>&nbsp;</td>
							<td><a id="czk_sub" href="javascript:;">充值</a></td>
						</tr>
						<tr>
							<td class="right_td" style="vertical-align: top; padding-top: 16px;">温馨提示：</td>
							<td style="vertical-align: top; padding-top: 16px;">
								<ul>
									<li>1. 充值成功后，余额可能存在延迟现象，一般1到5分钟内到账；</li>
									<li>2: 您只能用充值卡进行充值，如有问题，请咨询 4000-365-020；</li>
									<li>3: 充值完成后，您可以进入我的钱包记录页面进行查看余额充值状态。</li>
								</ul>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>