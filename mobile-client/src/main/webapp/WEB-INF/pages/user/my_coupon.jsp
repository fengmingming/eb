<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>手拉手触屏版</title>
	<%@include file="../common/common.jsp" %>
	<link rel='stylesheet' href='${staUrl }/css/user/my_coupon.css' type='text/css' />
	<script src='${staUrl }/js/user/my_coupon.js' type='text/javascript' ></script>
	<script type="text/javascript">
		type="${type}";
	</script>
</head>

<body>
	<div class="viewport">
		<div id="header">
			<div class="header_txt">我的优惠券</div>
			<div class="h_back" onclick="javascript: location.href='${rootUrl}/pcenter/index.htm'"></div>
		</div>
		<div class="item">
			<input type="text" id="yhq_inp" placeholder="请输入兑换码">
			<a id="yhq_btn" href="javascript:;">领取优惠券</a>
		</div>
		<ul class="clear oc_opt">
			<li style="border-right: solid 1px #ddd;"><a href="javascript: getCoupon('1');" <c:if test="${type == 1 }">class="oc_cur_a"</c:if>>未使用</a></li>
			<li style="border-right: solid 1px #ddd;"><a href="javascript: getCoupon('2');" <c:if test="${type == 2 }">class="oc_cur_a"</c:if>>已使用</a></li>
			<li><a href="javascript: getCoupon('3');" <c:if test="${type == 3 }">class="oc_cur_a"</c:if>>已过期</a></li>
		</ul>
		
		
		<c:if test="${empty coupon }">
			<jsp:include page="../common/null_list_hint.jsp">
				<jsp:param name="content" value="优惠券" />
			</jsp:include>
		</c:if>
		<c:if test="${!empty coupon }">
			<c:choose>
				<c:when test="${type == 1 }">
					<c:forEach var="item" items="${coupon }">
						<div class="yhq_dv">
							<div class="yhq_tp_1"></div>
							<ul class="clear yq_u">
								<li class="yq_u_l yq_color_1">优惠券</li>
								<li class="yq_u_r yq_color_0">${item.limitCat }</li>
							</ul>
							<div class="yq_pe yq_color_1">￥${item.parValue }</div>
							
							<ul class="yq_cn">
								<li class="yc_line"></li>
								<li class="yc_txt yq_color_0">${item.name }</li>
								<li class="yc_dta yq_color_0">${item.validityStart }&nbsp;—&nbsp;${item.validityEnd }</li>
							</ul>						
							<div class="yq_kb"></div>
							<div class="yq_dh_ln"></div>
							<div class="yq_dh_ln"></div>
							<div class="yq_dh_ln"></div>
						</div>	
					</c:forEach>					
				</c:when>
				<c:otherwise>
					<c:forEach var="item" items="${coupon }">
						<div class="yhq_dv">
							<div class="yhq_tp_1"></div>
							<ul class="clear yq_u">
								<li class="yq_u_l yq_color_2">优惠券</li>
								<li class="yq_u_r yq_color_2">${item.limitCat }</li>
							</ul>
							<div class="yq_pe yq_color_2">￥${item.parValue }</div>
							
							<ul class="yq_cn">
								<li class="yc_line"></li>
								<li class="yc_txt yq_color_2">${item.name }</li>
								<li class="yc_dta yq_color_2">${item.validityStart }&nbsp;—&nbsp;${item.validityEnd }</li>
							</ul>
							
							<div class="yq_kb"></div>
							<div class="yq_dh_ln"></div>
							<div class="yq_dh_ln"></div>
							<div class="yq_dh_ln"></div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>					
			</c:if>			
	</div>
</body>
</html>