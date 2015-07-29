<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>人气商品</title>
	<%@include file="../common/common.jsp" %>
	<link rel='stylesheet' href='${staUrl }/css/goods/top_n_list.css' type='text/css' />
</head>

<body>
	<div class="viewport">
		<div id="header">
			<div class="header_txt">人气商品</div>
			<div class="h_back" onclick="history.go(-1);"></div>
			<%@include file="../common/mz_nav.jsp" %>
		</div>
		<ul class="clear goods_list">
			<c:if test="${!empty topGoods }">
				<c:forEach items="${topGoods }" var="goods">
					<li>
						<a href="${rootUrl }/goods/goodsDetail.htm?id=${goods.goodsId}">
							<img src="${imgUrl }/200X200${goods.photoUrl}" class="gl_img" />
							<p class="gl_txt"><c:if test="${!empty goods.actType && goods.actType == 25}">【限时抢购】</c:if><c:if test="${!empty goods.actType && goods.actType == 30}">【闪购】</c:if>${goods.goodsName }</p>
							<p class="gl_xj"><c:if test="${!empty goods.actPrice }">￥${goods.actPrice }</c:if><c:if test="${empty goods.actPrice}">￥${goods.price }</c:if><span class="gl_yj">${goods.marketPrice }</span></p>
						</a>
					</li>
				</c:forEach>
			</c:if>
		</ul>
	</div>
</body>
</html>