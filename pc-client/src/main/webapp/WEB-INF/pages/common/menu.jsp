<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel='stylesheet' href='${staUrl }/css/common/menu.css' type='text/css' />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
$(function(){
	var menu_id = "${menu_id }";
	$("#"+menu_id).addClass("u_menu_cur");
});
</script>
<div id="u_menu">
	<ul>
		<li class="u_menu_tit"><a id="my_sls_page" href="${rootUrl }/user/toMysls.htm">我的手拉手</a></li>
		<li><a id="user_info" href="${rootUrl }/user/toUserInfo.htm">个人信息</a></li>
		<li><a id="account_safe" href="${rootUrl }/account/toAccountCenter.htm">账户安全</a></li>
		<c:if test="${user.memberType == '1' }">
			<li><a id="user_address" href="${rootUrl }/address/toAddress.htm">收货地址</a></li>
			<li><a id="my_favorite" href="${rootUrl }/favorite/toMyFavorite.htm">我的关注</a></li>
		</c:if> 		
		<li><a id="my_wallet" href="${rootUrl }/account/toMyWallet.htm">我的钱包</a></li>
		<li><a id="account_recharge" href="${rootUrl }/account/toRecharge.htm">资金充值</a></li>
		<!-- 暂时屏蔽该功能
		<li><a id="feedback" href="javascript:;">意见反馈</a></li> 
		-->
		<c:if test="${user.memberType == '1' }">
			<li><a id="my_coupon" href="${rootUrl }/coupon/unUseCoupon.htm">我的优惠券</a></li>
		</c:if> 		
		<li class="u_menu_tit">订单管理</li>
		<c:choose>
			<c:when test="${user.memberType == '1' }">		
				<li><a id="my_order" href="${rootUrl }/order/getOrderList.htm">我的订单</a></li>
			</c:when>
			<c:otherwise>
				<li><a id="p_order" href="${rootUrl }/order/getPavilionOrders.htm">订单管理</a></li>
				<!-- 
				<li><a id="ds_order" href="${rootUrl }/order/dsOrderList.htm">代收订单</a></li>
				<li><a id="dg_order" href="${rootUrl }/order/dgOrderList.htm">代购订单</a></li>
				 -->
			</c:otherwise>	
		</c:choose>
		<c:if test="${user.memberType == '2' }">
			<li class="u_menu_tit">社区一卡通</li>
			<li><a id="binding" href="${rootUrl }/card/toCard.htm">绑定</a></li>
			<li><a id="card_recharge" href="${rootUrl }/card/cardRecharge.htm">充值</a></li>
			<li><a id="report_loss" href="${rootUrl }/card/reportLoss.htm">换卡</a></li>
			<li class="u_menu_tit">用户管理</li>
			<li><a id="p_regist" href="${rootUrl }/user/toUserRegist.htm">用户注册</a></li>
			<li><a id="consumer" href="${rootUrl }/user/consumer.htm">用户信息</a></li>
		</c:if> 
	</ul>
</div>
    