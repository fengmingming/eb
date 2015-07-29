<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的订单详情</title>
<%@include file="../common/common.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/common/nav.css' type='text/css' />
<link rel='stylesheet' href='${staUrl }/css/common/menu.css' type='text/css' />
<link rel='stylesheet' href='${staUrl }/css/order/my_order_detail.css' type='text/css' />

<script type="text/javascript" src="${staUrl }/js/order/my_order_detail.js"></script>
</head>
<body>
	<%@include file="../common/nav.jsp" %>

	<%@include file="../common/category_nav.jsp" %>

	<div id="u_info">
		<ul class="clear" id="url_here">
			<li>首页<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li>订单管理<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<c:if test="${memberType == 1}">
				<li class="uh_cur">我的订单</li>
			</c:if>
			<c:if test="${memberType == 2}">
				<li class="uh_cur">订单管理</li>
			</c:if>
		</ul>
		<div id="u_info_con" class="clear">
			<%@include file="../common/menu.jsp" %>
			<c:if test="${!empty orderDetail }">
			<div id="order_detail">
				<ul id="ord_tit" class="clear">
					<li class="odt_l">订单号：<span id="odr_id">${orderDetail.orderNum}</span></li>
					<li class="odt_l">
                        <span style="color: #525252;">订单状态：</span>
                        <span id="odr_state">
                            <c:choose>
                                <c:when test="${orderDetail.state == 2 }">已取消</c:when>
                                <c:when test="${orderDetail.isPaid == 1}">未付款</c:when>
                                <c:when test="${orderDetail.status == 4 }">已发货</c:when>
                                <c:when test="${orderDetail.status == 5 }">已完成</c:when>
                                <c:when test="${orderDetail.status == 6 }">退换货中</c:when>
                                <c:when test="${orderDetail.status == 7 }">退换货完成</c:when>
                                <c:otherwise>已付款<c:if test="${orderDetail.shopType == 2 }">-代</c:if></c:otherwise>
                            </c:choose>
                        </span>
                    </li>
					<li class="odt_r"><c:choose><c:when test="${orderDetail.state == 2 }"></c:when>
												<c:when test="${orderDetail.status == 5 }"></c:when>
												<c:when test="${orderDetail.isPaid == 1 }"><a class="qx_order qo_txt_btn" href="javascript: orderPay('${orderDetail.orderNum }','${orderDetail.payPrice }','${orderDetail.payCode }');">付款</a></c:when>
									  </c:choose></li>
					<li class="odt_r"><c:choose><c:when test="${orderDetail.state == 2 }"></c:when>
												<c:when test="${orderDetail.status == 1 || orderDetail.status == 2}"><a class="qx_order qo_txt_btn" href="javascript:void(0);" onclick="cancleOrder('${orderDetail.id}',this)">取消订单</a></c:when>
												<c:when test="${orderDetail.isPaid == 1 }"></c:when>
												<c:when test="${orderDetail.status == 4 }"><a class="qx_order qo_txt_btn" href="javascript:;" onclick="confirmReceipt('${orderDetail.id }',this)">确认收货</a></c:when>
									  </c:choose></li>
					<!--
					<li class="odt_r"><c:choose><c:when test="${menu_id == 'ds_order' }"><a id="to_myorders" href="${rootUrl }/order/dsOrderList.htm">返回代收订单</a></c:when>
									  	<c:when test="${menu_id == 'dg_order' }"><a id="to_myorders" href="${rootUrl }/order/dgOrderList.htm">返回代购订单</a></c:when>
									  	<c:otherwise><a id="to_myorders" href="${rootUrl }/order/getOrderList.htm">返回我的订单</a></c:otherwise>
									  </c:choose></li> -->
				</ul>

				<div id="od_info_tit">订单信息</div>

				<div class="consignee_info">收货人信息</div>
				<div id="address_info">
					<ul class="clear">
						<li class="da_tit">收货人：</li>
						<li>${orderDetail.receiver }</li>
					</ul>
					<c:if test="${orderDetail.deliveryType == 2 }">
						<ul class="clear">
							<li class="da_tit">地址：</li>
							<li class="da_detail">${orderDetail.remark }</li>
						</ul>
					</c:if>
					<ul class="clear">
						<li class="da_tit">联系电话：</li>
						<li><c:if test="${!empty orderDetail.mobile }">
								${orderDetail.mobile }&nbsp;&nbsp;&nbsp;</c:if>
							<c:if test="${!empty orderDetail.phone }">${orderDetail.phone }</c:if>
						</li>
					</ul>
					<ul class="clear da_eskp">
						<c:if test="${orderDetail.deliveryType == 1 }">
							<li class="da_tit">自提点：</li>
							<li>${pavilionInfo.name }&nbsp;&nbsp;&nbsp;${pavilionInfo.mobile }</li>
						</c:if>
					</ul>
				</div>

				<div class="consignee_info">支付和配送方式</div>
				<div id="pay_info">
					<ul class="clear">
						<li class="da_tit">支付方式：</li>
						<li>${orderDetail.payName }</li>
					</ul>
					<ul class="clear">
						<li class="da_tit">配送方式：</li>
						<li><c:choose><c:when test="${orderDetail.deliveryType == '1'}">自提点自提</c:when><c:otherwise>快递送货</c:otherwise></c:choose></li>
					</ul>
				</div>

				<div class="consignee_info">商品清单</div>
				<ul id="detail_list_th" class="clear">
					<li class="dlt_num">商品编号</li>
					<li class="dlt_img">商品图片</li>
					<li class="dlt_name">商品名称</li>
					<li class="dlt_price">商品价格</li>
					<li class="dlt_sl">商品数量</li>
					<li class="dlt_state">状态</li>
					<li class="dlt_ope">操作</li>
				</ul>

				<ul id="detail_list_tr">
					<c:forEach var="item" items="${orderDetail.orderDetailList }">
						<li>
							<ul class="clear" id="detail_list_td">
								<li class="dlt_num">${item.sku }</li>
								<li class="dltr_img"><a href="${rootUrl }/goods/getGoodsInfo.htm?id=${item.goodsId}" ><img src="${imgUrl }/58X58${item.photoUrl }" /></a></li>
								<li class="dltr_name"><div title='${item.goodsName }' class="dltr_name_div">${item.goodsName }</div></li>
								<li class="dltr_price">￥${item.price }</li>
								<li class="dlt_sl">${item.number }</li>
								<li class="dlt_state">有货</li>
								<li class="dlt_ope dlt_ope_tb">
									<div class="dlt_ope_td">
										<c:choose><c:when test="${orderDetail.state == 2}"></c:when>
																  <c:when test="${orderDetail.isPaid == 1 }"></c:when>
																  <c:when test="${orderDetail.status == 4 }"><c:if test="${item.isDelivery == 0 }"><a href="javascript:;" onclick="confirmOrderDetail('${item.id }',this);">确认收货</a></c:if>
																  	
																  </c:when>
																  <c:otherwise></c:otherwise>
																</c:choose>
										<c:choose><c:when test="${orderDetail.state == 1 && orderDetail.status >=4}">
																  <!-- shopType == 2 DG  （亭子服务）取消 -->
																  	<c:if test="${ memberType == 2 && orderDetail.shopType == 2  }">
																  		<a class="" href="${rootUrl }/order/getReturnOrderInfo.htm?orderDetailId=${item.id} " >退/换货</a>
																  	</c:if>
																  	<c:if test="${ memberType == 1  }">
																  		<a class="" href="${rootUrl }/order/getReturnOrderInfo.htm?orderDetailId=${item.id} " >退/换货</a>
																  	</c:if>
																  	</c:when>
																  	</c:choose>
									</div>
								</li>
							</ul>
						</li>
					</c:forEach>
				</ul>

				<ul id="detail_list_tl" class="clear">
					<li id="dltl_total">￥${orderDetail.amount }</li>
					<li>总商品金额：</li>
					<li id="dltl_jf">0</li>
					<li>可获积分：</li>
				</ul>

				<ul id="detail_list_tq" class="clear">
					<li id="dltl_yf">0</li>
					<li>运费：</li>
				</ul>

				<ul id="detail_list_tf" class="clear">
					<li id="dltf_total">￥${orderDetail.payPrice }</li>
					<li id="dltf_txt">应付总额：</li>
				</ul>
			</div>
			</c:if>
		</div>
	</div>

	<%@include file="../common/foot.jsp" %>

</body>
</html>
