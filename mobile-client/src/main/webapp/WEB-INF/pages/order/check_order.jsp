<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<title>手拉手触屏版</title>
	<%@include file="../common/common.jsp" %>
	<link rel='stylesheet' href='${staUrl }/css/order/check_order.css' type='text/css' />
	<script type="text/javascript" src="${staUrl }/js/order/check_order.js"></script>
	<script type="text/javascript" src="${staUrl }/js/common/md5.js"></script>
</head>

<body>
	<div class="viewport">
		<div id="header">
			<div class="header_txt">订单核对</div>
			<div class="h_back" onclick="history.go(-1);"></div>
		</div>
		<!-- 配送信息 -->
		<div class="co_psxx">配送信息</div>
		<ul class="clear co_shfs">
			<li>收货方式：</li>
			<li><a id="kdsh_a" class="goods_check gc_y" href="javascript:;"></a></li>
			<li>快递送货</li>
			<li><a id="ztdzt_a" class="goods_check gc_n" href="javascript:;"></a></li>
			<li>自提点自提</li>
		</ul>
		<!-- 收货人信息 -->
		<div class="co_psxx">收货人信息</div>
		<ul class="clear co_shdz xz" <c:if test="${!empty addresses}">style="display:none"</c:if>>
			<li>新增收货地址：</li>
			<li style="float: right;"><a class="co_add" href="javascript:;"></a></li>
		</ul>
		<ul class="clear co_shdz select" <c:if test="${empty addresses}">style="display:none"</c:if>>
			<li>选择收货地址：</li>
			<li style="float: right;"><a class="co_sel" href="javascript:;"></a></li>
		</ul>
		<ul class="clear co_shdz_d" id="def_address" <c:if test="${empty addresses}">style="display:none"</c:if>></ul>
		
		<c:if test="${!empty shopCart.canUseCouponList }">
		<div class="co_psxx">优惠券</div>
			<ul class="clear co_yhq">
				<li style="color: #fb0f1c;"><span id="co_yhq_num">${fn:length(shopCart.canUseCouponList)}</span>张可用</li>
				<li id="co_yhq_txt">已抵用0.00元</li>
				<li><a class="csd_jt" style="top: 10px;" href="javascript:;"></a></li>
			</ul>
		</c:if>
		
		 <!-- -订单信息 -->
		<div class="co_psxx">订单信息</div>
		<ul class="co_orders">
			<c:forEach var="product" items="${shopCart.productList }">
				<li class="clear co_od_li">
					<a class="od_a_img" href="${rootUrl }/goods/goodsDetail.htm?id=${product.settleGoods[0].id }">
						<img class="od_img" src="${imgUrl }/200X200${product.settleGoods[0].photoUrl}" />
					</a>
					<p class="od_txt">${product.settleGoods[0].goodsName}</p>
					<p><span style="color: #bcbcbc;">价格：</span>￥${product.settleGoods[0].price}</p>
					<p><span style="color: #bcbcbc;">数量：</span>${product.number}</p>
				</li>
			</c:forEach>
		</ul>
		<div class="vew_all">展开全部商品</div>
		<div class="od_total">
			<ul class="clear" id="yhq_price">
				<li class="od_t_txt">优惠券：</li>
				<li id="discountByCoupon" class="od_t_pri">-￥0.00</li>
			</ul>
			<ul class="clear">
				<li class="od_t_txt">合计金额：</li>
				<li id="orderTotal" class="od_t_pri">￥${shopCart.payPrice }</li>
			</ul>
		</div>
		<!-- 支付方式 -->
		<div class="co_psxx">支付方式</div>
		<ul class="co_zffs">
			<li>
				<a id="yezf_a" class="goods_check gc_y" href="javascript:;"></a>
				<span>余额支付</span>
			</li>
			<li style="display: none;">
				<a id="zfbzf_a" class="goods_check gc_n" href="javascript:;" ></a>
				<span>支付宝支付</span>
			</li>
			<li>
				<a id="wxzf_a" class="goods_check gc_n" href="javascript:;"></a>
				<span>微信支付</span>
			</li>
		</ul>
		<ul class="clear co_yezf">
			<li class="co_yezf_txt">支付密码</li>
			<li><input type="password" name="password" maxlength="16" /></li>
			<li style="float: right;"><a href="javascript:;" id="submit_od_ye">确定</a></li>
		</ul>
		<div class="co_zfbzf_div"><a class="co_zfbzf" href="javascript:;" id="submit_od_zfb">支付宝支付</a></div>
		<div class="co_wxzf_div"><a class="co_wxzf" href="javascript:;" id="submit_od_wx">微信支付</a></div>
	</div>
	
	<div class="filter_div"></div>
	
	<!-- 新增/修改地址信息弹出框 -->
	<div class="pop_div">
		<ul class="clear">
			<li class="co_pd_w">收货人</li>
			<li><input class="inp_shr required" type="text" name="receiver" maxlength="5"/></li>
		</ul>
		<ul class="clear">
			<li class="co_pd_w">手机号码</li>
			<li><input class="inp_sjhm required" type="text" name="mobile" maxlength="11"/></li>
		</ul>
		<%@include file="../common/booth_area.jsp" %>
		<ul class="clear">
			<li class="co_pd_w">详细地址</li>
			<li><input class="inp_xxdz required" type="text" name="addressDetail" maxlength="254"/></li>
		</ul>
		<input type="hidden" id="edit_address_id"/>
		<div style="text-align: center;">
			<a href="javascript:;" class="co_ent">确认</a>
			<a href="javascript:;" class="co_cancel">取消</a>
		</div>
	</div>
	
	<!-- 收货人地址列表 -->
	<div class="viewport2">
		<ul class="clear shrxx_txt">
			<li style="float: left; padding-left: 10px;">收货人信息</li>
			<li id="co_to_viewport1" style="float: right; padding-right: 10px; cursor: pointer;">返回</li>
		</ul>
		<c:if test="${!empty addresses}">
			<c:forEach var="add" items="${addresses }">
			<ul class="clear ua_ul">
				<li class="ua_li">
					<a href="javascript:;" class="goods_check gc_n" id="${add.id }"></a>
				</li>
				<li class="ua_li">
					<ul class="ua_li_ul">
						<li class="ua_li_ul_pavi">${add.pavilionName }&nbsp;&nbsp;${add.pavilionPhone }</li>
						<li class="ua_li_ul_ads">${add.addressDetail }</li>
						<li class="ua_li_ul_rec">${add.receiver }&nbsp;${address.mobile}</li>					
					</ul>
				</li>
				<li class="ua_li" style="float: right;"><a class="ua_li_edit" href="javascript:;">编辑</a></li>
				<li class="ua_li" style="float: right;"><a class="ua_li_del" href="javascript:;">删除</a></li>
			</ul>
			</c:forEach>
		</c:if>
		<ul class="clear co_shdz">
			<li>新增收货地址：</li>
			<li style="float: right;"><a class="co_add2" href="javascript:;"></a></li>
		</ul>
	</div>
	
	<!-- 使用优惠券列表 -->
	<div class="viewport3">
		<div id="header">
			<div class="header_txt">使用优惠券</div>
			<div class="h_back" id="vp_yhq_bk"></div>
		</div>
		<div class="yhq_divs">
			<c:if test="${!empty shopCart.canUseCouponList }">
			<c:forEach var="userCoupon" items="${shopCart.canUseCouponList }">
				<div class="yhq_div">
					<a id="${userCoupon.id }" class="goods_check gc_n" href="javascript:;"></a>
					<input type="hidden" value="${userCoupon.parValue }">
					<ul class="yhq_ul">
						<li><span class="yq_us1">优惠券</span><span class="yq_us2">${userCoupon.name }</span></li>
						<li>使用范围：${userCoupon.limitCatName }</li>
						<li>有效期至：<fmt:formatDate value="${userCoupon.validityEnd }" pattern="yyyy年MM月dd日"/></li>
					</ul>
					<div class="yhq_bt"></div>
				</div>
			</c:forEach>
			</c:if>
			<div class="yhq_div">
				<a id="" class="goods_check gc_y" href="javascript:;"></a>
				<div id="yq_d_nouse">不使用</div>
				<div class="yhq_bt"></div>
			</div>
		</div>
		<div id="yhq_ent">
			<a id="yq_en_a" href="javascript:;">确认</a>
		</div>
	</div>
	<%@include file="../common/to_top.jsp" %>
</body>
<script type="text/javascript">
var total = "${shopCart.payPrice }";
</script>
</html>