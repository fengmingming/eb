<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收货地址</title>
<%@include file="../common/common.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/user/user_address.css' type='text/css' />
<script type="text/javascript" src="${staUrl }/js/user/user_address.js"></script>

</head>
<body>
	<%@include file="../common/nav.jsp" %>
	
	<%@include file="../common/category_nav.jsp" %>
	
	<div id="u_info">
		<ul class="clear" id="url_here">
			<li>首页<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li>我的手拉手<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li class="uh_cur">收货地址</li>
		</ul>
		<div id="u_info_con" class="clear">
			<%@include file="../common/menu.jsp" %>
			<div id="tg_address">
				<ul id="tg_a_ul" class="clear">
					<li><a id="tg_a_ul_a" href="javascript:;">新增收货地址</a></li>
					<li id="tg_a_ul_txt">您创建了<span id="addressNum">${fn:length(addresses.result) }</span>个收货地址，最多可创建<span id="tg_a_ul_span">3</span>个</li>
				</ul>
				
				<div id="address_infos">
					<c:if test="${addresses.success }">
						<c:forEach var="address" items="${addresses.result }">
							<div class="address_info" id="address${address.id }">
								<ul class="clear">
									<li class="ai_li">收货人：</li>
									<li>${address.receiver }</li>
								</ul>
								<ul class="clear">
									<li class="ai_li">地址：</li>
									<li class="ad_li">${address.addressDetail }</li>
								</ul>
								<ul class="clear">
									<li class="ai_li">手机号码：</li>
									<li>${address.mobile }</li>
								</ul>
								<ul class="clear">
									<li class="ai_li">电话号码：</li>
									<li>${address.phone }</li>
								</ul>
								<ul class="clear" id="ai_ul_fwt">
									<li class="ai_li">自提点：</li>
									<li>${address.pavilionName }&nbsp;&nbsp;&nbsp;${address.pavilionPhone }</li>
								</ul>
								<ul class="clear" id="ai_ul_ope">
									<li><a class="ai_li_a upd_address" href="javascript:;" onclick="getAddress(${address.id})">编辑</a></li>
									<li><a class="ai_li_a" href="javascript:;" onclick="deleteAddress(${address.id})">删除</a></li>
								</ul>
							</div>
						</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	
	<div id="uaddiv">
		<div id="uaddiv_tit">
			<span id="uaddiv_sp"></span>收货地址
			<a id="uaddiv_tit_a" href='javascript:;'></a>
		</div>
		<div id="uad_address_div">
			<table>
				<tr>
					<td class="td_fs"><span class="r_star">*</span>收货人：</td>
					<td class="uad_td"><input class="r_ipt required" type="text" name="receiver" maxlength="5"/></td>
				</tr>
				<tr>
					<td class="td_fs">手机号码：</td>
					<td class="uad_td"><input class="r_ipt" type="text" name="mobile" maxlength="11"/></td>
				</tr>
				<tr>
					<td class="td_fs">电话号码：</td>
					<td class="uad_td"><input class="r_ipt" type="text" name="phone" maxlength="11"/></td>
				</tr>
				<%@include file="../common/booth_area.jsp" %>
				<tr>
					<td class="td_fs"><span class="r_star">*</span>详细地址：</td>
					<td class="uad_td"><input class="r_ipt xx_ipt required" type="text" name="addressDetail" maxlength="254"/></td>
				</tr>
				<tr>
					<td><input type="hidden" name="addressId"></td>
					<td>
						<a id="save_adds" href="javascript:;" onclick="modifyAddress()">保存收货地址</a>
						<span style="color:red;font-size:12px;">*商圈和自提点必填，手机号码和电话号码其一必填</span></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
				</tr>
			</table>
		</div>
	</div>
	<%@include file="../common/foot.jsp" %>
</body>
<script type="text/javascript">

</script>
</html>
					