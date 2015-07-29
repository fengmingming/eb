<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel='stylesheet' href='${staUrl }/css/common/note_menu.css' type='text/css' />
<script type="text/javascript">
$(function(){
	var menuId = "${menuId }";
	$("#"+menuId).addClass("u_menu_cur");
});
</script>
<div id="u_menu">
	<ul>
		<li class="u_menu_tit">便民服务</li>
		<li><a id="convenient_money" href="${rootUrl }/note/show.htm?menuId=convenient_money">便民缴费</a></li>
		<li><a id="convenient_basket" href="${rootUrl }/note/show.htm?menuId=convenient_basket">便民菜篮</a></li>
		<li><a id="convenient_hut" href="${rootUrl }/note/show.htm?menuId=convenient_hut">健康小屋</a></li>
		<li><a id="convenient_tool" href="${rootUrl }/note/show.htm?menuId=convenient_tool">便民工具箱</a></li>
		<li class="u_menu_tit">配送服务</li>
		<li><a id="delivery_flow" href="${rootUrl }/note/show.htm?menuId=delivery_flow">物流配送</a></li>
		<li><a id="delivery_web" href="${rootUrl }/note/show.htm?menuId=delivery_web">服务网络</a></li>
		<li class="u_menu_tit">支付方式</li>
		<li><a id="pay_blance" href="${rootUrl }/note/show.htm?menuId=pay_blance">余额支付</a></li>
		<li><a id="pay_alipay" href="${rootUrl }/note/show.htm?menuId=pay_alipay">支付宝支付</a></li>
		<li class="u_menu_tit">售后方式</li>
		<li><a id="service_after" href="${rootUrl }/note/show.htm?menuId=service_after">售后服务</a></li>
		<li><a id="service_merchant" href="${rootUrl }/note/show.htm?menuId=service_merchant">商家服务</a></li>
	</ul>
</div>
    