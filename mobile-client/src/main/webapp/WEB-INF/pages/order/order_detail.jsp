<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<title>手拉手触屏版</title>
	<%@include file="../common/common.jsp" %>
	<link rel='stylesheet' href='${staUrl }/css/order/order_detail.css' type='text/css' />
	<script type="text/javascript">
		var deleteOrder = function(html){
			if(window.confirm("确认取消订单？")){
				$.get(html,function(data){
					if(data.status == "302"){
						location.href = data.location;
						return ;
					}
					if(data.success){
						window.location.reload(true);
					}else{
						alert(data.errMsg);
					}
				},"json");
			}
		}
		$(function() {
			
		});
		
		function pay(id, num, price, payType){
			var orderId = id;
			var orderNum = num;
			var payPrice = price;
			var payType = payType;
			var wechatInfo = navigator.userAgent.match(/MicroMessenger\/([\d\.]+)/i) ;
			if(payType == 4){
				if( wechatInfo && wechatInfo[1] >= '5.0') {
					var appid = "";
					$.ajax({
						type: "GET",
						url: _rootUrl+"/wx/getAppid.htm",
						async: false,
						cache: false,
						dataType: "json",
						success: function(data){
							if (data.status == "302") {
								window.location.href = data.location;
								return ;
						    }
							if(data == null){
								alert("请重新点击");
								return ;
							}else{
								appid=data.appid;
								var STATE = '{"orderId":"'+orderId+'","total_fee":'+payPrice+',"order_num":"'+orderNum+'"}';
								window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri=http://www.365020.com/mobile/wx/getOpenid.htm?showwxpaytitle=1&response_type=code&scope=snsapi_base&state="+STATE+"#wechat_redirect";
							}
						}
					
					});				
				}else{
					alert("只支持微信支付！");
				}
			}else if(payType == 1){
				//if(wechatInfo){
					//alert("只支持支付宝支付！");
				//}else{
					//window.location.href = _dynUrl+"/pcenter/pay.htm?orderId="+orderId+"&orderNum="+orderNum+"&money="+payPrice;
					alert("手机支付宝暂没开通！");
				//}		
			}							
		}
		
		function delivery(orderId){
			window.showLoading();
			$.post("${dynUrl}/pcenter/orders/delivery.htm?orderId="+orderId,function(d){
				if(data.status == "302"){
					location.href = data.location;
					return ;
				}
				window.hideLoading();
				console.log(d);
				if(d.success){
					window.location.reload(true);
				}else{
					alert(d.errMsg);
				}
			},"json");
		}
	</script>
</head>

<body>
	<div class="viewport">
		<div id="header">
			<div class="header_txt">订单详情</div>
			<div class="h_back" onclick="history.go(-1);"></div>
			<%@include file="../common/mz_nav.jsp" %>
		</div>
		
		<ul class="od_mes">
			<li>订单号：${re.orderNum }</li>
			<c:choose>
				<c:when test="${re.isPaid == 1 && re.state == 1 }">
					<li>订单状态：<span style="color: #ff9900;">未付款</span></li>
				</c:when>
				<c:otherwise>
					<li>订单状态：<span style="color: #ff9900;">
					<c:choose>
						<c:when test="${re.state == 2}">已取消</c:when>
						<c:when test="${re.status == 4}">已发货</c:when>
						<c:when test="${re.status == 5}">已完成</c:when>	
						<c:when test="${re.status == 1}">未确认</c:when>					
						<c:when test="${re.status == 6}">退货中</c:when>
						<c:when test="${re.status == 7}">退货完成</c:when>	
						<c:otherwise>
						 	已付款
						</c:otherwise>											
					</c:choose>					
					</span></li>
				</c:otherwise>
			</c:choose>
			<li>订单日期：<fmt:formatDate value="${re.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></li>
		</ul>
		<div class="od_opt">
			<c:choose>
				<c:when test="${re.isPaid == 1&&re.state == 1}">
					<a style="background: #cc3333;" href="javascript:;" onclick="pay('${re.id}','${re.orderNum}','${re.payPrice}','${re.payCode }')">付款</a>
				</c:when>
				<c:when test="${re.state == 1&&(re.status == 4||re.status == 3) }">
					<a style="background: #cc3333" onclick="delivery(${re.id})">确认收货</a>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${re.state == 1 && (re.status == 1||re.status == 2) }">
					<a style="background: #ff9900;"  href="javascript:void(0);" onclick="deleteOrder('${dynUrl }/pcenter/orders/delete/${re.id}.htm')">取消订单</a>
				</c:when>
			</c:choose>
		</div>
		
		<ul class="od_shrxx">
			<li class="ods_tit">收货人信息</li>
			<li>收货人：${re.receiver }</li>
			<li>联系电话：${re.mobile }</li>
			<li class="clear">
				<span style="float: left;">收货地址：</span>
				<p style="overflow: hidden;">${re.remark }</p>
			</li>
		</ul>
		
		<ul class="od_zfps">
			<li class="ods_tit">支付和配送方式</li>
			<li>支付方式：${re.payName }</li>
			<c:if test="${re.deliveryType == 1}">
				<li>配送方式：自提</li>
			</c:if>
			<c:if test="${re.deliveryType == 2}">
				<li>配送方式：送货上门</li>
			</c:if>
		</ul>
		
		<ul class="co_orders">
			<c:forEach items="${re.orderDetailList }" var="detail">
				<li class="clear co_od_li">
					<a class="od_a_img" href="${rootUrl }/goods/goodsDetail.htm?id=${detail.goodsId }">
						<img class="od_img" src="${imgUrl }/200X200${detail.photoUrl}" />
					</a>
					<p class="od_txt">${detail.goodsName }</p>
					<p><span style="color: #bcbcbc;">价格：</span>￥${detail.price }</p>
					<p><span style="color: #bcbcbc;">数量：</span>${detail.number }</p>
				</li>
			</c:forEach>
		</ul>
		<div class="vew_all">展开全部商品</div>
		<div class="od_total">共计：<span style="color: #e3383c;">￥${re.orderPrice }元</span></div>
	</div>
</body>
</html>