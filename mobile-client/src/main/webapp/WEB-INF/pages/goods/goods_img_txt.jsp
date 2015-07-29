<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>${goods.goodsName }&nbsp;-&nbsp;商品详情&nbsp;-&nbsp;手拉手</title>
	<%@include file="../common/common.jsp" %>
	<link rel='stylesheet' href='${staUrl }/css/goods/goods_img_txt.css' type='text/css' />
	<script type="text/javascript">
		$(function() {
			$(".cut_tab a").click(function() {
				$(".cut_tab a").removeClass("cur");
				$(this).addClass("cur");
			});
		});
	</script>
</head>

<body>
	<div class="viewport">
		<div id="header">
			<div class="header_txt">图文详情</div>
			<div class="h_back" onclick="history.go(-1);"></div>
		</div>
		
		<ul class="clear cut_tab">
			<li style="border-right: solid 1px #ddd;">
				<a href="javascript:;" class="cur">图文详情</a>
			</li>
			<!-- <li><a href="javascript:;">商品规格</a></li> -->
		</ul>
		
		<ul class="ul_imgs">
			<li>${goods.description }</li>
		</ul>
		
		<%@include file="goods_foot_cart.jsp" %>
	</div>
</body>
</html>