<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>充值</title>
<%@include file="../common/common.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/card/card_recharge.css' type='text/css' />
<script src ='${staUrl }/js/card/card_recharge.js' type='text/javascript' ></script>
</head>
<body>
	<%@include file="../common/nav.jsp" %>
	
	<%@include file="../common/category_nav.jsp" %>
	
	<div id="u_info">
		<ul class="clear" id="url_here">
			<li>首页<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li>社区一卡通<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li class="uh_cur">充值</li>
		</ul>
		<div id="u_info_con" class="clear">
			<%@include file="../common/menu.jsp" %>
			<div id="cc_div">
				<div id="cc_div_tit" class="clear">
					<div id="cd_txt">社区一卡通充值</div>										
					<table >
						<tr>
							<td class="td_fs">请刷卡 :</td>
							<td class="a_i_td"><input type="password" value="" name="cardNum" id="cardNum" class="cr_inp" /></td>
							<td class="td_ts"><a class="cr_check" href="javascript:checkUserInfo();">查询</a></td>
						</tr>
						<tr>
							<td class="td_fs" >姓名 : </td>
							<td><input type="text" value="" id="name" class="no_border" readonly="readonly"/></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="td_fs" >手机号 : </td>
							<td><input type="text" value="" id="mobile" class="no_border" readonly="readonly"/></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="td_fs" >当前余额 : </td>
							<td><input type="text" value="" id="amount" class="no_border" readonly="readonly"/></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="td_fs">请输入充值金额 : </td>
							<td class="a_i_td"><input type="text" value="" id="money" class="cr_inp"/></td>
							<td>&nbsp;</td>
						</tr>
						<tr style="height: 30px;">
							<td>&nbsp;</td>
							<td><input class="cr_rdo" type="radio" name="payment" checked onclick="selRadio(this)"/>账户余额支付</td>
							<td>&nbsp;</td>
						</tr>
						<tr style="height: 30px;">
							<td>&nbsp;</td>
							<td class="a_i_td"><input class="cr_rdo" type="radio" name="payment" onclick="selRadio(this)"/>支付宝支付<span class="cr_zfb"></span></td>
							<td>&nbsp;</td>
						</tr>
						<tr id="payPwd">
							<td class="td_fs">请输入支付密码 :</td>
							<td class="a_i_td"><input type="password" value="" id="payPassword" class="cr_inp"/></td>
							<td class="td_ts"><a class="cr_fpwd" href="${rootUrl }/account/modifyPayPwd1.htm" target="_blank">忘记支付密码？</a></td>
						</tr>
						<tr style="height: 60px;">
							<td>&nbsp;</td>
							<td><a class="cr_rech" href="javascript:recharge();" >充值</a></td>
							<td>&nbsp;</td>
						</tr>
					</table>
				<!-- 
				<div>
					<div style="text-align:center; margin-top: 10px; margin-button: 10px; height: 36px">
						请刷卡: <input type="text" value="" name="cardNum" id="cardNum"/> <a href="javascript:checkUserInfo();">查询</a>
					</div>
					<div style="text-align:center; margin-top: 10px; margin-button: 10px; height: 59px; line-height: 23px;" >
						<div>姓名: <input type="text" value="" /> </div>
						<div>手机号: <input type="text" value="" /> </div>
						<div>当前余额: <input type="text" value="" /> </div>
					</div>
					<div style="text-align:center; margin-top: 10px; margin-button: 10px; height: 36px">
						<div>请输入充值金额: <input type="text" value="" /></div>
					</div>
					<div style="text-align:center; margin-top: 10px; margin-button: 10px; height: 36px">
						<div><input type="radio" name="payment" checked />账户余额支付</div>
						<div><input type="radio" name="payment" />支付宝支付</div>
					</div>
					<div style="text-align:center; margin-top: 10px; margin-button: 10px" id="payPwd">
						<div>请输入支付密码: <input type="text" value="" /><a href="javascript:;">忘记密码？</a></div>
					</div>
					<div style="text-align:center; margin-top: 10px; margin-button: 10px">
						<div><a href="javascript:;">充值</a></div>
					</div>
					 -->					 
				</div>														
			</div>
		</div>
	</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
