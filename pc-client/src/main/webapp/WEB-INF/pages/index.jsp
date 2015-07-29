<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>手拉手-把服务送到家门口</title>
<%@include file="common/common.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/common/nav.css' type='text/css' />
<link rel='stylesheet' href='${staUrl }/css/index_1.css' type='text/css' />
<script type="text/javascript" src="${staUrl }/js/common/index_1.js"></script>
<!-- <script language="javascript" src="${staUrl }/js/common/_go_city.js"></script> -->
</head>
<body>
	<%@include file="common/nav.jsp" %>
	<div id="category">
		<div id="category_con" class="clear">
			<ul id="cgy_ul">
				<li id="cgy_sc" py="0">
					<span class="cgy_img"></span>
					<span class="cgy_txt">生鲜蔬菜</span>
					<div class="cgy_div"></div>
				</li>
				<li id="cgy_rl" py="29">
					<span class="cgy_img"></span>
					<span class="cgy_txt">新品肉类</span>
					<div class="cgy_div"></div>
				</li>
				<li id="cgy_sg" py="57">
					<span class="cgy_img"></span>
					<span class="cgy_txt">精选水果</span>
					<div class="cgy_div"></div>
				</li>
				<li id="cgy_ly" py="86">
					<span class="cgy_img"></span>
					<span class="cgy_txt">精品粮油</span>
					<div class="cgy_div"></div>
				</li>
				<li id="cgy_js" py="114">
					<span class="cgy_img"></span>
					<span class="cgy_txt">酒水饮料</span>
					<div class="cgy_div"></div>
				</li>
				<li id="cgy_hx" py="142">
					<span class="cgy_img"></span>
					<span class="cgy_txt">生猛海鲜</span>
					<div class="cgy_div"></div>
				</li>
				<li id="cgy_ls" py="171">
					<span class="cgy_img"></span>
					<span class="cgy_txt">休闲零食</span>
					<div class="cgy_div"></div>
				</li>
			</ul>
			<ul id="banners">
				<c:if test="${!empty broadcast }">
					<c:forEach var="item" items="${broadcast }">
						<li class="bn_ig"><a href="${item.url }" target="_blank"><img src="${imgUrl }${item.imgUrl }" /></a></li>
					</c:forEach>
				</c:if>
				<c:if test="${empty broadcast }">
					<li class="bn_ig"><img src="${staUrl }/images/t_a1.jpg" /></li>
					<li class="bn_ig"><img src="${staUrl }/images/t_a2.jpg" /></li>
				</c:if>				
			</ul>
		</div>
	</div>
	
	<c:if test="${fn:length(goodslist) != 0}">
	<%@include file="activity/group_purcse_activity.jsp" %><!-- 闪购活动版块 -->
	</c:if>
	
	<%@include file="activity/activity_column.jsp" %><!-- 商品展示版块 -->
	
	<%@include file="common/foot.jsp" %>
</body>
</html>