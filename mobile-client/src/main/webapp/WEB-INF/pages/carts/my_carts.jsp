<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>我的购物车</title>
	<%@include file="../common/common.jsp" %>
	<link rel='stylesheet' href='${staUrl }/css/carts/my_carts.css' type='text/css' />
	<script type="text/javascript" src="${staUrl }/js/common/jquery.cookie.js"></script>
	<script src="${staUrl }/js/carts/my_carts.js"></script>
</head>
<body>
	<div class="viewport">
		<div id="header">
			<div class="header_txt">购物车</div>
			<div class="h_back" onclick="history.go(-1);"></div>
			<%@include file="../common/mz_nav.jsp" %>
		</div>
		<c:if test="${!empty shopCart}">
			<div class="hd_scsp"><a id="del_goods" href="javascript:;">删除商品</a></div>
			<ul class="goods_ul">
				<c:forEach var="product" items="${shopCart.productList}">
					<li class="clear goods_li">
						<c:if test="${product.checked}">
							<a class="goods_check gc_y" href="javascript:;" id="${product.productSku}"></a>
						</c:if>
						<c:if test="${!product.checked}">
							<a class="goods_check gc_n" href="javascript:;" id="${product.productSku}"></a>
						</c:if>
						<img src="${imgUrl }/200X200${product.settleGoods[0].photoUrl}" class="goods_img" />
						<ul class="goods_txt">
							<li class="gt_txt">${product.settleGoods[0].goodsName}</li>
							<!-- <li class="gt_txt">6粒/约1.2kg</li> -->
							<li class="gt_txt" style="color: #dd3e48; font-weight: bold;">￥${product.settleGoods[0].price}</li>
						</ul>
						<ul class="goods_num">
							<li class="gn_txt">数量：</li>
							<li>
								<ul class="clear ft_num">
									<li><a href="javascript:;" class="minus_a">-</a></li>
									<li><input type="text" value="${product.number}" class="gda_inp" tmp="${product.number}" maxlength="3"/></li>
									<li><a href="javascript:;" class="plus_a">+</a></li>
								</ul>
							</li>
						</ul>
						<span class="gn_remark">${product.remark}</span>
						<a href="${rootUrl }/goods/goodsDetail.htm?id=${product.settleGoods[0].id }" class="gl_jt"></a>
					</li>
				</c:forEach>
			</ul>
		</c:if>
		
		<c:if test="${empty shopCart}">
			<!-- 购物车无商品记录时，需要展示的样式 -->
			<div class="cart_logo"></div>
			<p class="pd_txt">您的购物车内还没有商品！</p>
			<ul class="clear no_goods_opt">
				<li style="margin-right: 10px;"><a href="${rootUrl }/pcenter/index.htm">个人中心</a></li>
				<li><a href="${rootUrl }/index.htm">立即逛逛</a></li>
			</ul>
			<div class="sls_logo"></div>
			<p class="pd_txt">把服务送到家门口</p>
		</c:if>
		
		<div id="filter_div"></div>
		<div id="pop_div">
			<div class="cart_logo"></div>
			<p class="pd_txt">是否确认删除商品？</p>
			<ul class="pd_btns clear">
				<li style="margin-right: 10px; background: #ff9900;"><a id="pd_ent" style="color: #fff;" href="javascript:;">确认</a></li>
				<li style="background: #f3f3f3;"><a id="pd_can" style="color: #666;" href="javascript:;">取消</a></li>
			</ul>
		</div>
		
		<c:if test="${!empty shopCart}">
		<div id="foot">
			<ul class="clear foot_ul">
				<li class="all_check">
					<a class="goods_check_all gc_y" href="javascript:;"></a>
				</li>
				<li class="ac_txt">全选</li>
				<li class="ac_des">
					<span>总计：<span style="font-weight: bold; color: #dd3e48; padding-left: 4px;">￥${shopCart.payPrice}</span></span><br />
					<span>(共<span>${shopCart.number}</span>件，不含运费)</span>
				</li>
				<li class="mc_js"><a href="javascript:;" onclick="amount();">结算</a></li>
			</ul>
		</div>
	</c:if>
	</div>
	
	
</body>
</html>