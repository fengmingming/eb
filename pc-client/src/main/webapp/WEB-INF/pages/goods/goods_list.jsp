<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>商品列表</title>
<%@include file="../common/common.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/common/nav.css' type='text/css' />

<link rel='stylesheet' href='${staUrl }/css/goods/goods_list.css' type='text/css' />

<script type="text/javascript" src="${staUrl }/js/goods/goods_list.js"></script>
</head>
<body>
	<%@include file="../common/nav.jsp" %>
	
	<%@include file="../common/category_nav.jsp" %>
	
	<div id="goods_list_con" class="clear">
		<div id="glc_lt">
			<div id="glc_lt_tit">人气商品</div>
			<div id="glc_lt_gdss">
				<c:if test="${topGoods != null }">
					<c:forEach var="item" items="${topGoods }" begin="0" end="4">
						<div class="glc_lt_gds">
							<div class="glc_lt_gds_img">
								<a href="${rootUrl }/goods/getGoodsInfo.htm?id=${item.goodsId }"><img src="${imgUrl }/200X200${item.photoUrl }" /></a>
							</div>
							<div class="glc_lt_gds_des">
								<a href="${rootUrl }/goods/getGoodsInfo.htm?id=${item.goodsId }"><c:if test="${!empty item.actType && item.actType == 25}">【限时抢购】</c:if><c:if test="${!empty item.actType && item.actType == 30}">【闪购】</c:if>${item.goodsName }</a>
							</div>
							<div class="glc_lt_gds_pri">
								<span class="glgp_np"><c:if test="${!empty item.actPrice }">￥${item.actPrice }</c:if><c:if test="${empty item.actPrice}">￥${item.price }</c:if></span>
								<!-- <span class="glgp_op">168</span> -->
							</div>
						</div>
					</c:forEach>					
				</c:if>												
			</div>
		</div>
		<div id="glc_rt">
			<div id="glc_rt_top">
				<ul id="glc_rt_sorts" class="clear">
					<li id="sortZh_li" class="grs_djt">综合</li>
					<li id="sortNew_li" class="grs_djt">新品</li>
					<li id="sortSale_li" class="grs_djt">销量</li>
					<li id="sortPrice_li" class="grs_sjt">价格</li>
				</ul>
				<!-- 
					<ul id="glc_rt_pri" class="clear">
						<li><input type="text" class="grp_txt" /></li>
						<li>-</li>
						<li><input type="text" class="grp_txt" /></li>
						<li id="grp_yh_li"><input id="grp_yh" type="checkbox" /></li>
						<li><label for="grp_yh">仅显示有货</label></li>
					</ul>
				 -->
			</div>
			<div id="glc_rt_pcon">
				<div id="glc_rt_con" class="clear">
					<c:if test="${fn:length(goodslist) > 0}">
						<c:forEach items="${goodslist}" var="goods">
							<div class="glc_rt_gds">
								<input class="grg_goods_id" type="hidden" value="${goods.id}" />
								<input class="grg_province_id" type="hidden" value="0" />
								<input class="grg_city_id" type="hidden" value="1" />
								<input class="grg_district_id" type="hidden" value="2" />
								<input class="grg_community_id" type="hidden" value="3" />
								<div class="glc_rt_gds_img">
									<a target="_blank" href="${rootUrl }/goods/getGoodsInfo.htm?id=${goods.id}"><img src="${imgUrl }/200X200${goods.photoUrl}" /></a>
								</div>
								<div class="glc_rt_gds_pri clear">
									<span class="grgp_np grgp_npri">
									<c:if test="${empty goods.actDto}">
										￥${goods.price}					
									</c:if>
									<c:if test="${!empty goods.actDto}">
										￥${goods.actDto[0].actPrice}	
									</c:if>
									</span>
									<a href="javascript:;" class="grgp_op"></a>
								</div>
								<div class="glc_rt_gds_des">
									<c:if test="${empty goods.actDto}">
										<a href="${rootUrl }/goods/getGoodsInfo.htm?id=${goods.id}" title="${goods.goodsName }">${goods.goodsName }</a>					
									</c:if>
									<c:if test="${!empty goods.actDto}">
										<a href="${rootUrl }/goods/getGoodsInfo.htm?id=${goods.id}" title="${goods.goodsName }">【${goods.actDto[0].actTypeName}】${goods.goodsName }</a>
									</c:if>
									<div>规格：${goods.standard }</div>
									<div>原产地：${goods.place }</div>
								</div>
								<div class="glc_rt_gds_opt">
									<ul class="grgp_ul clear">
										<c:if test="${goods.isSale == 1 }">
											<li class="grgp_ul_num_li">
												<a class="grgp_ul_num grgp_ul_a minus_a" href="javascript:;">-</a>
												<input class="grgp_ul_num grgp_ul_inp" type="text" value="1" tmp="1" maxlength="4"/>
												<a class="grgp_ul_num grgp_ul_a plus_a" href="javascript:;">+</a>
											</li>
											<li class="grgp_ul_car_li">
												<a class="grgp_ul_car" href="javascript:;"><i></i>加入购物车</a>
											</li>
										</c:if>
										<c:if test="${goods.isSale == 2 }">
											<li class="grgp_ul_num_li">
												<a class="grgp_ul_num grgp_ul_a" href="javascript:;" style="color: #aeaeae;"></a>
												<input class="grgp_ul_num grgp_ul_inp" type="text" value="1" readonly="readonly" style="color: #aeaeae;" maxlength="4"/>
												<a class="grgp_ul_num grgp_ul_a" href="javascript:;" style="color: #aeaeae;"></a>
											</li>
											<li class="grgp_ul_car_li">
												<a class="grgp_ul_car_de" href="javascript:;"><i></i>商品已下架</a>
											</li>
										</c:if>
									</ul>
								</div>
								<div class="glc_rt_gds_bg"></div>
							</div>
						</c:forEach>
					</c:if>
					<c:if test="${fn:length(goodslist) == 0}">
						<jsp:include page="../common/null_list_hint.jsp">
							<jsp:param name="content" value="相关商品" />
						</jsp:include>
					</c:if>
				</div>
			</div>
			<!-- 自定义分页组件 -->
			<form id="fm_goodsList" action="${rootUrl }/goods/getGoodsList.htm" method="get">
				<input type="hidden" id="page" name="currPage" value="${currPage }" />
				<input type="hidden" id="total_page" value="${total_page }" />
				<input type="hidden" id="first" name="first" value="${first }" />
				<input type="hidden" id="second" name="second" value="${second }" />
				<input type="hidden" id="sortTp" name="sortTp" value=${sortTp }>
			</form>
			<jsp:include page="../component/page_nav.jsp">
				<jsp:param name="fm_id" value="fm_goodsList" />
				<jsp:param name="is_visible" value="true" />
			</jsp:include>
			<!-- 自定义分页组件 -->
		</div>
	</div>
	
	<%@include file="../common/foot.jsp" %>
	
	<%@include file="../component/float_mycarts.jsp" %>
	<%@include file="../component/float_top.jsp" %>
	
	<div id="fly_cart" style="position: absolute; top: 0px; left: 0px; width: 43px; height: 43px; opacity: 0; filter: alpha(opacity=0); display: none;"><img src="" style="width: 43px; height: 43px;" /></div>
	
	<%@include file="../component/cart_bubble.jsp" %>
</body>
</html>