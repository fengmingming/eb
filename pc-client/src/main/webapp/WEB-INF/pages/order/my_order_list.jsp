<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我的订单</title>
<%@include file="../common/common.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/order/purchasing_order.css' type='text/css' />
<link rel='stylesheet' href='${staUrl }/css/order/my_order_list.css' type='text/css' />

<script type="text/javascript" src="${staUrl }/js/order/my_order_list.js"></script>
</head>
<body>
	<%@include file="../common/nav.jsp" %>
	
	<%@include file="../common/category_nav.jsp" %>
	
	<div id="u_info">
		<ul class="clear" id="url_here">
			<li>首页<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li>订单管理<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li class="uh_cur">我的订单</li>
		</ul>
		<div id="u_info_con" class="clear">
			<%@include file="../common/menu.jsp" %>
			<div id="purchasing">
				<div id="purchasing_tit" class="clear">
					<div id="pt_txt">我的订单</div>
					<ul id="pt_search">
						<li><input type="text" id="order_num" placeholder="订单号" maxlength="30"/></li>
						<li><a href="javascript:void(0);" onclick="query_order_num();">查询</a></li>
					</ul>
				</div>
				<ul id="purchasing_state" class="clear">
					
					<li>
						<span id="time">${query.time }</span>
						<select id="order_time">
							<option value="1">最近3个月</option>
							<option value="2">今年内</option>
							<option value="3">2014年</option>
						</select>
					</li>
					<li>
						<span id="status">${query.status }</span>
						<select id="order_status">
							<option value="0">全部状态</option>
							<option value="2">已取消</option>
							<option value="1">未付款</option>
							<option value="3">等待收货</option>
							<option value="5">已完成</option>
						</select>
					</li>
				</ul>
				
				<ul id="mo_list_th" class="clear">
					<li class="mlt_spxx">商品信息</li>
					<li class="mlt_name">收货人</li>
					<li class="mlt_ddje">订单金额</li>
					<li class="mlt_xdsj">下单时间</li>
					<li class="mlt_ddzt">订单状态</li>
					<li class="mlt_operate">操作</li>
				</ul>
				
				<ul id="mo_list_tb">
				<c:if test="${!empty orderList }">
					<c:forEach var="item" items="${orderList }">
						<li>
							<ul class="mo_list_tr clear">
								<li class="mlt_orderId">单号：${item.orderNum }</li>					
								<li class="mlt_img mlt_h">						
									<c:forEach var="srcPath" items="${item.orderDetailList }" begin="0" end="1">									
										<a href="${rootUrl }/goods/getGoodsInfo.htm?id=${srcPath.goodsId}" ><img src="${imgUrl }/58X58${srcPath.photoUrl }" /></a>							 
									</c:forEach> 
								</li>
								<!--<c:set var="gNumber" value="${item.orderDetailList }"/>-->
								<li class="mlt_all mlt_h"><a class="mlt_all_a" href="orderDetail.htm?id=${item.id }">共<!-- ${fn:length(gNumber)} -->${item.count }样&nbsp;&gt;</a></li>
								<li class="mlt_name mlt_h">${item.receiver }</li>
								<li class="mlt_ddje mlt_h">￥${item.orderPrice }</li>
								<li class="mlt_xdsj mlt_h"><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></li>
								<li class="mlt_ddzt mlt_h mlt_state" id="m_m_${item.id }"><c:choose><c:when test="${item.state == 2 }">已取消</c:when>
																	     <c:when test="${item.isPaid == 1 }">未付款</c:when>				
																	     <c:when test="${item.status == 4 }">已发货</c:when>
																	     <c:when test="${item.status == 5 }">已完成</c:when>
																	     <c:when test="${item.status == 6 }">退货中</c:when>
																	     <c:when test="${item.status == 7 }">退货完成</c:when>
																	     <c:otherwise>已付款<c:if test="${item.shopType == 2 }">-代</c:if></c:otherwise>
																	 </c:choose></li>
								<li class="mlt_operate mlt_h mlt_o_p"><div class="mlt_o_c"><c:choose><c:when test="${item.state == 2 }"><a class="mlt_o_a" href="${rootUrl }/order/orderDetail.htm?id=${item.id }">查看</a></c:when>
																	     <c:when test="${item.isPaid == 1 }">
																	     	<a class="mlt_o_a" href="javascript: orderPay('${item.orderNum }','${item.payPrice }','${item.payCode }')">付款</a>
																	     	<a class="mlt_o_a" href="${rootUrl }/order/orderDetail.htm?id=${item.id }">查看</a>
																	     </c:when>
																	     <c:when test="${item.status == 4 }">
																	     	<a class="mlt_o_a" href="${rootUrl }/order/orderDetail.htm?id=${item.id }">查看</a>
																	     	<a class="mlt_o_a" href="javascript:;" onclick="confirmReceipt('${item.id }',this)">确认收货</a>
																	     	
																	     </c:when>
																	     <c:when test="${item.status == 5 }">
																	     	<a class="mlt_o_a" href="${rootUrl }/order/orderDetail.htm?id=${item.id }">查看</a>
																	     	
																	     </c:when>
																	     <c:otherwise><a class="mlt_o_a" href="${rootUrl }/order/orderDetail.htm?id=${item.id }">查看</a></c:otherwise>
																	 </c:choose></div></li>
							</ul>
						</li>
					</c:forEach>
				</c:if>
				<c:if test="${empty orderList }">				
					<jsp:include page="../common/null_list_hint.jsp">
						<jsp:param name="content" value="我的订单" />
					</jsp:include>
				</c:if>	
				</ul>
				<!-- 自定义分页组件 -->
				<form id="my_order_list" action="${rootUrl }/order/queryOrderList.htm" method="get">
					<input type="hidden" name="orderNum" value="" />
					<input type="hidden" name="status" value="${query.status }" />
					<input type="hidden" name="timeType" value="${query.time }" />
					<input type="hidden" id="page" name="page" value="${page }" />
					<input type="hidden" id="total_page" value="${total_page }" />
				</form>
				<jsp:include page="../component/page_nav.jsp">
					<jsp:param name="fm_id" value="my_order_list" />
					<jsp:param name="is_visible" value="true" />
				</jsp:include>
				<!-- 自定义分页组件 -->
			</div>
		</div>
	</div>
	
	<%@include file="../common/foot.jsp" %>
	
</body>
</html>
