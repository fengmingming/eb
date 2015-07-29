<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel='stylesheet' href='${staUrl }/css/goods/goods_foot_cart.css' type='text/css' />
<script src="${staUrl }/js/goods/goods_foot_cart.js"></script>
<div id="foot">
	<ul class="clear">
		<li class="w_16">
		<!--
			<a class="ft_sc" href="javascript:;">
				<i></i>
				<span>收藏</span>
			</a>
		  -->
		</li>
		<li class="w_34" style="text-align: center;">
		<ul class="clear ft_num">
			<c:if test="${goods.isSale == 1 }">
				<li><a href="javascript:;" id="minus_a">-</a></li>
				<li><input type="text" value="1" id="gda_inp" tmp="1" maxlength="3"/></li>
				<li><a href="javascript:;" id="plus_a">+</a></li>
			</c:if>
			<c:if test="${goods.isSale == 2 }">
				<li><a href="javascript:;" style="color: #aeaeae;">-</a></li>
				<li><input type="text" value="1" id="gda_inp" tmp="1" readonly="readonly" style="color: #aeaeae;" maxlength="3"/></li>
				<li><a href="javascript:;" style="color: #aeaeae;">+</a></li>
			</c:if>
			<li class="w_34" style="text-align: center;">
		</ul>
		</li>
		<li class="w_34">
			<c:if test="${goods.isSale == 1 }">
				<a class="ft_add_car" href="javascript:;" id="add_carts">加入购物车</a>
			</c:if>
			<c:if test="${goods.isSale == 2 }">
				<a class="ft_de_car" href="javascript:;">商品已下架</a>
			</c:if>
		</li>
		<li class="w_16">
			<a href="javascript:;" class="ft_car">
				<span class="ft_car_bg_img">
					<span>0</span>
				</span>
				<span class="ft_car_txt">购物车</span>
			</a>
		</li>
	</ul>
	<input type="hidden" value="${goods.id}" id="goods_id" />
</div>