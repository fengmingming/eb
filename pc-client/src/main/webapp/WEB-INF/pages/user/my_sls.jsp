<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的手拉手</title>
<%@include file="../common/common.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/user/my_sls.css' type='text/css' />

<script type="text/javascript">
	$(function() {
		//收藏列表滑动效果
		var _mt_scroll_ul = $("#mt_scroll_ul"),
			_lis = _mt_scroll_ul.find("li"),
			_li_w = 190,
			speed = 600,
			tempLength = 0,
			countLength = (_lis.length - 5) * _li_w,
			_mp = $("#msd_prev"),
			_mn = $("#msd_next");
		_mp.click(function() {
			if(tempLength > 0){
				if(tempLength > _li_w){
					_mt_scroll_ul.animate({left: "+=" + _li_w + "px"}, speed);
					tempLength -= _li_w;
				}else{
					_mt_scroll_ul.animate({left: "+=" + _li_w + "px"}, speed);
					tempLength = 0;
				}
			}
		});
		_mn.click(function() {
			if(tempLength < countLength){
				if((countLength - tempLength) > _li_w){
					_mt_scroll_ul.animate({left:"-=" + _li_w + "px"}, speed);
					tempLength += _li_w;
				}else{
					_mt_scroll_ul.animate({left:"-=" + (countLength - tempLength) + "px"}, speed);
					tempLength += (countLength - tempLength);
				}
			}
		});
		var _mps = _mp.find("span"),
			_mns = _mn.find("span");
		_mp.hover(function() {
			_mps.css("background-position","-17px -458px");
		}, function() {
			_mps.css("background-position","0px -458px");
		});
		_mn.hover(function() {
			_mns.css("background-position","-34px -458px");
		}, function() {
			_mns.css("background-position","-51px -458px");
		});
	});
</script>
</head>
<body>
	<%@include file="../common/nav.jsp" %>
	
	<%@include file="../common/category_nav.jsp" %>
	
	<div id="u_info">
		<ul class="clear" id="url_here">
			<li>首页<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li class="uh_cur">我的手拉手</li>
		</ul>
		<div id="u_info_con" class="clear">
			<%@include file="../common/menu.jsp" %>
			<div id="my_sls">
				<div id="ms_top" class="clear">
					<div id="u_i_icon">
						<ul>
							<c:if test="${sessionScope.user.memberType == 1}">
								<li>用户：<span id="uii_ph">${userName }</span>手拉手注册会员</li>
								<li id="uii_mk">把服务送到家门口</li>
							</c:if>
							<c:if test="${sessionScope.user.memberType == 2}">
								<li>用户：<span id="uii_ph">${userName }</span>手拉手自提点管理员</li>
								<li id="uii_mk">把服务送到家门口
									<a class="export_btn" href="${rootUrl }/order/exportOrder.htm">导出今日订单</a>
								</li>
							</c:if>
						</ul>
					</div>
					<ul id="mp_ul">
						<li>
							<div class="ml_tit">账户余额（元）</div>
							<div class="ml_num">${amount }</div>
						</li>
						<c:if test="${user.memberType==2}">
							<li>
								<div class="ml_tit">今日消费余额（元）</div>
								<div class="ml_num">${todayTotal }</div>
							</li>
						</c:if>
						<li>
							<div class="ml_tit">可用积分</div>
							<div class="ml_num">${points }</div>
						</li>
					</ul>
				</div>
				<!-- 我的订单 -->
				<c:if test="${myOrders != null }">
					<div id="ms_bd">
						<ul id="md_ul" class="clear">
							<li id="ml_tit">我的订单</li>
							<li id="ml_all"><a href="${rootUrl }/order/getOrderList.htm">查看全部订单</a></li>
						</ul>
						<ul id="md_orders">
							<c:if test="${myOrders.success}">
							<c:forEach var="item" items="${myOrders.result.entry }">
								<li>
									<ul class="md_order clear">
										<li class="mo_img mo_h">
											<c:forEach var="goods" items="${item.orderDetailList }" begin="0" end="1">									
												<a href="${rootUrl }/goods/getGoodsInfo.htm?id=${goods.goodsId }" target="_blank">
													<img src="${imgUrl }/58X58${goods.photoUrl }" /></a>
											</c:forEach>
										</li>
										<li class="mo_all mo_h"><a class="mlt_all_a" href="${rootUrl}/order/orderDetail.htm?id=${item.id }">共${item.count }样&nbsp;&gt;</a></li>
										<li class="mo_name mo_h">${item.receiver }</li>
										<li class="mo_price mo_h">￥${item.orderPrice }</li>
										<li class="mo_time mo_h"><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></li>
										<li class="mo_state mo_h mo_s_cur">
											<c:choose><c:when test="${item.state == 2 }">已取消</c:when>
													  <c:when test="${item.isPaid == 1 }">未付款</c:when>				
												      <c:when test="${item.status == 4 }">已发货</c:when>
												      <c:when test="${item.status == 5 }">已完成</c:when>
												      <c:when test="${item.status == 6 }">退货中</c:when>
												      <c:when test="${item.status == 7 }">退货完成</c:when>
												      <c:otherwise>已付款<c:if test="${item.shopType == 2 }">-代</c:if></c:otherwise>
											</c:choose></li>
										<li class="mo_ope mo_h"><a href="${rootUrl}/order/orderDetail.htm?id=${item.id }">查看</a></li>
									</ul>
								</li>
							</c:forEach>
							</c:if>
						</ul>
					</div>
				</c:if>
				<!-- 代收订单
				<c:if test="${dsOrders != null }">
					<div id="ms_bd">
						<ul id="md_ul" class="clear">
							<li id="ml_tit">代收订单</li>
							<li id="ml_all"><a href="${rootUrl }/order/dsOrderList.htm">查看全部订单</a></li>
						</ul>
						<ul id="md_orders">
							<c:if test="${dsOrders.success}">
							<c:forEach var="item" items="${dsOrders.result.entry }">
								<li>
									<ul class="md_order clear">
										<li class="mo_img mo_h">
											<c:forEach var="goods" items="${item.orderDetailList }" begin="0" end="1">									
												<a href="${rootUrl }/goods/getGoodsInfo.htm?id=${goods.goodsId }" target="_blank">
													<img src="${imgUrl }${goods.photoUrl }" /></a>
											</c:forEach>
										</li>
										<li class="mo_name mo_h">${item.receiver }</li>
										<li class="mo_mobile mo_h">${item.mobile }</li>
										<li class="mo_price mo_h">￥${item.orderPrice }</li>
										<li class="mo_time mo_h"><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></li>
										<li class="mo_state mo_h mo_s_cur">
											<c:choose><c:when test="${item.state == 2 }">已取消</c:when>
												      <c:when test="${item.isPaid == 1 }">未付款</c:when>				
												      <c:when test="${item.status == 4 }">已发货</c:when>
												      <c:when test="${item.status == 5 }">已完成</c:when>
												      <c:when test="${item.status == 6 }">退货中</c:when>
												      <c:when test="${item.status == 7 }">退货完成</c:when>
												      <c:otherwise>已付款</c:otherwise>
											</c:choose></li>
										<li class="mo_ope mo_h"><a href="${rootUrl}/order/dsOrderDetail.htm?id=${item.id }">查看</a></li>
									</ul>
								</li>
							</c:forEach>
							</c:if>
						</ul>
					</div>
				</c:if> -->
				<!-- 亭子订单 -->
				<c:if test="${pavilionOrders != null }">
					<div id="ms_bd">
						<ul id="md_ul" class="clear">
							<li id="ml_tit">自提点订单</li>
							<li id="ml_all"><a href="${rootUrl }/order/getPavilionOrders.htm">查看全部订单</a></li>
						</ul>
						<ul id="md_orders">
							<c:if test="${pavilionOrders.success}">
							<c:forEach var="item" items="${pavilionOrders.result.entry }">
								<li>
									<ul class="md_order clear">
										<li class="mo_img mo_h">
											<c:forEach var="goods" items="${item.orderDetailList }" begin="0" end="1">									
												<a href="${rootUrl }/goods/getGoodsInfo.htm?id=${goods.goodsId }" target="_blank">
													<img src="${imgUrl }/58X58${goods.photoUrl }" /></a>
											</c:forEach>
										</li>
										<li class="mo_name mo_h">${item.receiver }</li>
										<li class="mo_mobile mo_h">${item.mobile }</li>
										<li class="mo_price mo_h">￥${item.orderPrice }</li>
										<li class="mo_time mo_h"><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></li>
										<li class="mo_state mo_h mo_s_cur">
											<c:choose><c:when test="${item.state == 2 }">已取消</c:when>
													  <c:when test="${item.isPaid == 1 }">未付款</c:when>				
												      <c:when test="${item.status == 4 }">已发货</c:when>
												      <c:when test="${item.status == 5 }">已完成</c:when>
												      <c:when test="${item.status == 6 }">退货中</c:when>
												      <c:when test="${item.status == 7 }">退货完成</c:when>
												      <c:otherwise>已付款</c:otherwise>
											</c:choose></li>
										<li class="mo_ope mo_h"><a href="${rootUrl}/order/pavilionOrderInfo.htm?id=${item.id }">查看</a></li>
									</ul>
								</li>
							</c:forEach>
							</c:if>
						</ul>
					</div>
				</c:if>
				<!-- 我关注的商品 -->
				<c:if test="${myFavorites != null }">
					<div id="ms_ft">
						<ul id="mt_tit">
							<li id="mt_t_tit">我关注的商品</li>
							<li id="mt_t_more"><a href="${rootUrl }/favorite/toMyFavorite.htm">更多</a></li>
						</ul>
						<div id="mt_scroll_div" class="clear">
							<c:if test="${myFavorites.success }">
								<a id="msd_prev" href="javascript:;"><span id="msd_p_s"></span></a>
								<div id="mt_scroll_con">
									<ul id="mt_scroll_ul" class="clear">
										<c:forEach var="item" items="${myFavorites.result.entry }">
											<li>
												<a href="${rootUrl }/goods/getGoodsInfo.htm?id=${item.id }"><img src="${imgUrl }/200X200${item.photoUrl }" /></a>
												<div class="msu_txt">${item.goodsName }</div>
												<div class="msu_price">￥${item.price }</div>
											</li>
										</c:forEach>
									</ul>
								</div>
								<a id="msd_next" href="javascript:;"><span id="msd_n_s"></span></a>
							</c:if>
						</div>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
