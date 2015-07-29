<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>手拉手触屏版</title>
	<%@include file="../common/common.jsp" %>
	<link rel='stylesheet' href='${staUrl }/css/order/order_center.css' type='text/css' />
	<script type="text/javascript">
		$(function() {
						
			var curPage = 1;
			var rows = 10;
			var isLoad = true;
			var isLoading = false;
			var type = ${type};
			var loadOrderData = function(){
				if(isLoad&&!isLoading){
					isLoading = true;
					var obj = {
							page:curPage++,
							rows:rows,
						};
					switch(parseInt(type)){
					case 1:obj.state = 1;obj.isPaid = 1;break;
					case 2:obj.state = 1;obj.isPaid = 2;obj.status = "2,3,4";break;
					//case 3:obj.status = "5,6,7";break;
					case 3:break;
					}
					window.showLoading();
					$.post("${dynUrl}/pcenter/orders/list.htm",obj,function(data){
						if(data.status == "302"){
							location.href=data.location;
							return ;
						}
						window.hideLoading();
						isLoading = false;
						if(data.success){
							var list = data.result.entry;
							if(list&&list.length>0){
								var html = "";
								for(var i in list){
									var l = list[i];
									html = html + '<ul class="oc_tit" onclick="javascript:window.location.href=\'${dynUrl}/pcenter/orders/detail/'+l.id+'.htm\'">';
									html = html + '<li>单号：'+l.orderNum+'</li>';
									html = html + '<li>总价：<span style="color: #d94a39;">￥'+l.orderPrice+'</span></li><li class="oc_jt"></li></ul><ul class="oc_mes">';
									var i = 0;
									for(var j in l.orderDetailList){
										if(i++ == 2){
											break;
										}
										var jData = l.orderDetailList[j];
										html = html + '<li class="clear"><a href='+_rootUrl+"/goods/goodsDetail.htm?id="+jData.goodsId+'><img src="${imgUrl}/200X200'+jData.photoUrl+'" class="oc_img" /></a><p>'+jData.goodsName+'</p></li>';
									}
									html = html + '</ul>';
									
								}
								$("#view_data").append(html);
							}else{
								isLoad = false;
							}
						}else{
							alert(data.errMsg);
						}
					},"json");
				}
			}
			$(document).ready(function(){
				if(!_is_user_login()){
					window.location.href='${rootUrl}/user/login.htm';
				}else{
					loadOrderData();
					$("#nextBtn").click(function(){
						loadOrderData();
					});
				}				
			});
		});
	</script>
</head>
<body>
	<div class="viewport">
		<div id="header">
			<div class="header_txt">订单中心</div>
			<div class="h_back" onclick="window.location.href='${dynUrl}/pcenter/index.htm'"></div>
			<%@include file="../common/mz_nav.jsp" %>
		</div>
		
		<ul class="clear oc_opt">
			<c:choose>
				<c:when test="${type == 1 }">
					<li style="border-right: solid 1px #ddd;"><a class="oc_cur_a" href="${dynUrl }/pcenter/orders.htm?type=1">待付款</a></li>
				</c:when>
				<c:otherwise>
					<li style="border-right: solid 1px #ddd;"><a href="${dynUrl }/pcenter/orders.htm?type=1">待付款</a></li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${type == 2 }">
					<li style="border-right: solid 1px #ddd;"><a class="oc_cur_a" href="${dynUrl }/pcenter/orders.htm?type=2">待收货</a></li>
				</c:when>
				<c:otherwise>
					<li style="border-right: solid 1px #ddd;"><a href="${dynUrl }/pcenter/orders.htm?type=2">待收货</a></li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${type == 3 }">
					<li><a class="oc_cur_a" href="${dynUrl }/pcenter/orders.htm?type=3">全部</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${dynUrl }/pcenter/orders.htm?type=3">全部</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
		<div id="view_data"></div>
		<div><a id="nextBtn" href="javascript:;" style="height: 44px; line-height: 44px; color: #fff; text-align: center; display: block; background: #ff9900; margin: 10px 5px; font-size: 14px; border-radius: 3px;">更多。。。</a></div>
	</div>
</body>
</html>