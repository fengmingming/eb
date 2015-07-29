<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我的购物车</title>
<link rel='stylesheet' href='${staUrl }/cdn/lib/jqModal.css' type='text/css' />
<%@include file="../common/common.jsp" %>
<script type="text/javascript" src="${staUrl }/cdn/lib/jqModal.js"></script>
<link rel='stylesheet' href='${staUrl }/css/carts/mycarts.css' type='text/css' />
<script type="text/javascript" src="${staUrl }/js/common/jquery.cookie.js"></script>
<script type="text/javascript" src="${staUrl }/js/common/modify_number.js"></script>
<script type="text/javascript" src="${staUrl }/js/carts/mycarts.js"></script>
<script type="text/javascript">
	$(function() {
		$("#pop_tit_a").click(function() {
			$("#pop").hide();
			$("#msg_filter_div").hide();
			$("#card_msg").html("");
		});
	});
</script>
</head>
<body>

	<div id="cart">
		<div id="cart_tit" class="clear">
			<span id="cart_tit_txt">我的购物车</span>
			<ul id="cart_flow" class="clear">
				<li id="c_f_img"></li>
				<li class="c_f_li c_f_li_cur">1.我的购物车</li>
				<li class="c_f_li">2.填写核对订单信息</li>
				<li class="c_f_li">3.成功提交订单</li>
			</ul>
		</div>
		<ul id="cart_th" class="clear">
			<li id="ct_inp"><input type="checkbox" name="all"/></li>
			<li id="ct_all">全选</li>
			<li id="ct_name">商品信息</li>
			<li id="ct_sj">市场价（元）</li>
			<li id="ct_dj">单价（元）</li>
			<li id="ct_sl">数量</li>
			<li id="ct_je">金额（元）</li>
			<li id="ct_op">操作</li>
		</ul>
		<ul id="cart_con">
			<c:if test="${!empty shopCart}">
			<c:forEach var="product" items="${shopCart.productList}">
				<li class="cart_con_li">
					<ul class="cart_obj clear">
						<li class="co_inp">
							<c:if test="${product.checked}">
								<input type="checkbox" name="goods" value="${product.productSku}" checked="checked"/>
							</c:if>
							<c:if test="${!product.checked}">
								<input type="checkbox" name="goods" value="${product.productSku}"/>
							</c:if>
						</li>
						<!-- 需要改成多个商品的样式，目前只显示一个 -->
						<li class="co_img"><a href="${rootUrl }/goods/getGoodsInfo.htm?id=${product.settleGoods[0].id }" target="_blank">
							<img src="${imgUrl }/200X200${product.settleGoods[0].photoUrl}" width="100" height="100" /></a></li>
						<li class="co_name"><a title="${product.settleGoods[0].goodsName}" href="${rootUrl }/goods/getGoodsInfo.htm?id=${product.settleGoods[0].id }" class="hover_a" target="_blank">
							${product.settleGoods[0].goodsName}</a></li>
						<li class="co_sj">${product.settleGoods[0].marketPrice}</li>
						<li class="co_dj" id="price_one">${product.settleGoods[0].price}</li>
						<li class="co_sl">
							<!-- 数量选择组件 -->
							<jsp:include page="../component/modify_number.jsp" flush="true">
								<jsp:param name="count" value="${product.number}" />
							</jsp:include>
							<span class="co_sl_remark" title="${product.remark}">${product.remark}</span>
						</li>
						<li class="co_je" id="price">${product.amount}</li>
						<li class="co_del"><a href="javascript:;" onclick="deleteProducts(this)" class="hover_a">删除</a></li>
					</ul>
					<%-- 先不做了：目前缺少字段，打包商品的字段和名称
					<!-- 打包商品里的商品列表 -->
					<c:if test="${fn:length(product.settleGoods) > 1}">
					<c:forEach var="goods" items="${product.settleGoods}">
						<ul class="cart_obj_pack">
							<li><img src="${imgUrl }${goods.photoUrl}" width="100" height="100"/><a href="javascript:;">${product.goodsName}</a></li>
						</ul>
					</c:forEach>
					</c:if>
					--%>
				</li>
			</c:forEach>
			</c:if>
			<c:if test="${empty shopCart}">
				<div id="mc_msg">
				<!-- 用户未登录 -->
				<c:if test="${empty sessionScope.user }">
					<span>购物车内暂时没有商品，登录后将显示您之前加入的商品</span>
					<a href="javascript:;" onclick="login()">登录</a>
				</c:if>
				<!-- 用户已登录 -->
				<c:if test="${!empty sessionScope.user }">
					购物车空空的哦，去看看心仪的商品吧~
				</c:if>
					<a href="${rootUrl }/main/index.htm">去购物</a>
				</div>
			</c:if>
		</ul>
		<ul id="cart_tf" class="clear">
			<li id="ctf_inp"><input type="checkbox" name="all"/></li>
			<li id="ctf_all">全选</li>
			<li><a href="javascript:;" onclick="deleteProducts()" class="hover_a">删除</a></li>
			<c:if test="${!empty sessionScope.user}">
				<c:if test="${sessionScope.user.memberType eq 1}">
					<li class="ctf_r"><a id="ctf_js" href="javascript:amount();">结算</a></li>
				</c:if>
				<c:if test="${sessionScope.user.memberType eq 2}">
					<li class="ctf_r"><a id="ctf_card_js" href="javascript:cardAmount();">刷卡结算</a></li>
				</c:if>
			</c:if>
			<c:if test="${empty sessionScope.user}">
				<li class="ctf_r"><a id="ctf_js" href="javascript:amount();">结算</a></li>
			</c:if>
			<li class="ctf_r" id="total">
				<c:if test="${empty shopCart}">￥0.00</c:if>
				<c:if test="${!empty shopCart}">￥${shopCart.payPrice}</c:if>
			</li>
			<li class="ctf_r" style="padding-left: 75px;">总计（不含运费）</li>
			<li class="ctf_r">
				<c:if test="${empty shopCart}">已选商品<span id="count">0</span>件</c:if>
				<c:if test="${!empty shopCart}">已选商品<span id="count">${shopCart.number}</span>件</c:if>
			</li>
		</ul>
	</div>

</body>
<%@include file="../common/foot.jsp" %>

<!-- 
<div id="pop" class="jqmWindow" style="width:300px">
	<label for="cardNum">请刷卡:</label>
	<input id="cardNum" type="text" name="cardNum"/>
	<input id="queryBtn" type="button" value="完成" />
</div>
 -->
<div id="pop">
	<div id="pop_tit">
		客户刷卡或手机号
		<a id="pop_tit_a" href="javascript:;"></a>
	</div>
	<ul class="clear">
		<li><img src="/images/card.png" /></li>
		<li>
			<div>
		        <input id="cardNum" type="password" name="cardNum"/><br />
				<input id="queryBtn" type="button" value="确&nbsp;认" />
			</div>
		</li>
	</ul>
	<p id="card_msg"></p>
	<p class="card_sm" style="font-weight: bold;">使用说明</p>
	<p class="card_sm">1.F9 刷卡支付：提交订单时输用户自己支付密码。扣除用户余额中的金额。</p>
	<p class="card_sm">2.输入手机号：提交订单时输入亭子用户支付密码。扣除亭子用户余额中的金额。</p>
</div>
</html>