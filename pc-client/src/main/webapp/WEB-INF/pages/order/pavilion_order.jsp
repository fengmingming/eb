<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单管理</title>
<%@include file="../common/common.jsp" %>

<link rel='stylesheet' href='${staUrl }/css/order/purchasing_order.css' type='text/css' />
<link rel='stylesheet' href='${staUrl }/css/order/collecting_order.css' type='text/css' />
<script type="text/javascript" src="${staUrl }/js/order/pavilion_order.js"></script>
</head>
<body>
	<%@include file="../common/nav.jsp" %>
	
	<%@include file="../common/category_nav.jsp" %>
	
	<div id="u_info">
		<ul class="clear" id="url_here">
			<li>首页<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li>订单管理<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li class="uh_cur">订单管理</li>
		</ul>
		<div id="u_info_con" class="clear">
			<%@include file="../common/menu.jsp" %>
			<div id="purchasing">
				<div id="purchasing_tit" class="clear">
					<div id="pt_txt">订单管理</div>
					<ul id="pt_search">
						<li><input type="text" value="${query.mobile }" placeholder="手机号或订单号" id="mobile" maxlength="30"/></li>
						<li><a href="javascript:void(0);" onclick="query_order_mobile();">查询</a></li>
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
					<li>
						<span id="orderType">${query.orderType }</span>
						<select id="order_type">
							<option value="0">全部订单</option>
							<option value="1">代收订单</option>
							<option value="2">代购订单</option>
						</select>
					</li>
				</ul>
				
				<ul id="purchasing_th" class="clear">
					<li class="pth_spdd">商品订单</li>
					<li class="pth_name">姓名</li>
					<li class="pth_mobile">手机号</li>
					<li class="pth_price">订单金额</li>
					<li class="pth_get">自提/送货</li>
					<li class="pth_deatil">下单时间</li>
					<li class="pth_state">订单状态</li>
					<li class="pth_operate">操作</li>
				</ul>
				
				<ul id="purchasing_tb">
				<c:if test="${!empty pavilionOrders }">
					<c:forEach var="item" items="${pavilionOrders }">
					<li>
						<ul class="purchasing_tr clear">
							<li class="ptr_orderId">单号：${item.orderNum }</li>
							
							<li class="ptr_img ptr_h">
								<c:forEach var="srcPath" items="${item.orderDetailList }" begin="0" end="1" >
									<a href="${rootUrl }/goods/getGoodsInfo.htm?id=${srcPath.goodsId}" ><img src="${imgUrl }/58X58${srcPath.photoUrl }" /></a>
								</c:forEach>
							</li>
							<li class="pth_name ptr_h">${item.receiver }</li>
							<li class="pth_mobile ptr_h">${item.mobile }</li>
							<li class="pth_price ptr_h">￥${item.orderPrice }</li>
							<li class="pth_get ptr_h"><c:choose><c:when test="${item.deliveryType == '1' }">自提</c:when><c:otherwise>送货上门</c:otherwise></c:choose></li>
							<li class="pth_deatil ptr_h"><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></li>
							<li class="pth_state ptr_h ptr_state1"><c:choose><c:when test="${item.state == 2 }">已取消</c:when>
																		 <c:when test="${item.isPaid == 1 }">未付款</c:when>				
																	     <c:when test="${item.status == 4 }">已发货</c:when>
																	     <c:when test="${item.status == 5 }">已完成</c:when>
																	     <c:when test="${item.status == 6 }">退货中</c:when>
																	     <c:when test="${item.status == 7 }">退货完成</c:when>
																	     <c:otherwise>已付款</c:otherwise>
																	</c:choose>
							</li>
							<li class="pth_operate ptr_h ptr_p"><div class="ptr_c"><c:choose><c:when test="${item.state == 2 }"><a class="ptr_a_see" href="${rootUrl }/order/pavilionOrderInfo.htm?id=${item.id }">查看</a></c:when>
																		 <c:when test="${item.isPaid == 1 }">
																	     	<a class="ptr_a_see" href="javascript: orderPay('${item.orderNum }','${item.payPrice }','${item.payCode }');">付款</a>
																	     	<a class="ptr_a_see" href="${rootUrl }/order/pavilionOrderInfo.htm?id=${item.id }">查看</a>
																	     </c:when>
																	     <c:when test="${item.status == 4 }">
																	     	<a class="ptr_a_see" href="${rootUrl }/order/pavilionOrderInfo.htm?id=${item.id }">查看</a>
																	     	<a class="ptr_a_state1 ptr_a_khqs" href="javascript:;" orderId="${item.id }" >客户签收</a>
																	     </c:when>
																	     <c:when test="${item.status == 5 }">
																	     	<a class="ptr_a_see" href="${rootUrl }/order/pavilionOrderInfo.htm?id=${item.id }">查看</a>
																	     	<a class="ptr_a_state2" href="javascript:;">已签收</a>
																	     </c:when>
																	     <c:otherwise><a class="ptr_a_see" href="${rootUrl }/order/pavilionOrderInfo.htm?id=${item.id }">查看</a></c:otherwise>
																			</c:choose>
																			
								</div>
							</li>
						</ul>
					</li>
					</c:forEach>	
				</c:if>
				<c:if test="${empty pavilionOrders }">				
					<jsp:include page="../common/null_list_hint.jsp">
						<jsp:param name="content" value="订单" />
					</jsp:include>
				</c:if>					
				</ul>
				<!-- 自定义分页组件 -->
				<form id="pavilion_order" action="${rootUrl }/order/queryPavilionOrders.htm" method="get">
					<input type="hidden" name="mobile" value="${query.mobile }" />
					<input type="hidden" name="status" value="${query.status }" />
					<input type="hidden" name="orderType" value="${query.orderType }" />
					<input type="hidden" name="timeType" value="${query.time }" />
					<input type="hidden" id="page" name="page" value="${page }" />
					<input type="hidden" id="total_page" value="${total_page }" />
				</form>
				<jsp:include page="../component/page_nav.jsp">
					<jsp:param name="fm_id" value="pavilion_order" />
					<jsp:param name="is_visible" value="true" />
				</jsp:include>
				<!-- 自定义分页组件 -->
			</div>
		</div>
	</div>
	
	<%@include file="../common/foot.jsp" %>	
</body>
</html>
