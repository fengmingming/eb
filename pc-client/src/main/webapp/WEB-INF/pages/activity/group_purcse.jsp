<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
<c:if test="${actType == 25}">限时抢购商品列表</c:if>
<c:if test="${actType == 30}">闪购商品列表</c:if>
</title>
<%@include file="../common/common.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/activity/group_purcse.css' type='text/css' />

<!-- <script type="text/javascript" src="${staUrl }/js/goods/goods_list.js"></script> -->
<script type="text/javascript" src="${staUrl }/js/activity/group_purcse.js"></script>

</head>
<body>
	<%@include file="../common/activity_nav.jsp" %>
	
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
								<a href="${rootUrl }/goods/getGoodsInfo.htm?id=${item.goodsId }">${item.goodsName }</a>
							</div>
							<div class="glc_lt_gds_pri">
								<span class="glgp_np">￥${item.price }</span>
								<!-- <span class="glgp_op">168</span> -->
							</div>
						</div>
					</c:forEach>					
				</c:if>												
			</div>
		</div>
		
		<div id="glc_rt">
		<!--  
			<div id="glc_rt_top">
				<ul id="glc_rt_sorts" class="clear">
				
					<li id="sortZh_li" class="grs_djt">综合</li>
					<li id="sortNew_li" class="grs_djt">新品</li>
					<li id="sortSale_li" class="grs_djt">销量</li>
					<li id="sortPrice_li" class="grs_sjt">价格</li>
				</ul>
			</div>
		-->
			
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
										<a target="_blank" href="${rootUrl }/goods/getGoodsInfo.htm?id=${goods.id}"><img src="${imgUrl }/430X430${goods.photoUrl}" /></a>
									</div>
									<div class="glc_rt_gds_txt">
										【${goods.actDto[0].actTypeName}】<a href="${rootUrl }/goods/getGoodsInfo.htm?id=${goods.id}" title="${goods.goodsName }">${goods.goodsName }</a>
									</div>
									<ul class="clear grg_ul">
										<li class="grg_ul_li" style="font-weight: bold; color: red; font-size: 14px;">￥</li>
										<li style="font-weight: bold; color: red; font-size: 40px;">${goods.actDto[0].actPrice}</li>
										<li class="grg_ul_li" style="text-decoration: line-through; font-size: 14px; margin-left: 10px;">￥${goods.marketPrice}</li>
										<!-- 
										<li class="grg_ul_li" style="float: right; margin-right: 10px;"><span style="color: #ff7f00;font-size: 15px;font-weight: bold;">${goods.actDto[0].actBuyNumber}</span>件已售</li>
										 -->
									</ul>
									<div class="glc_rt_gds_bg"></div>
								</div>	
										
						</c:forEach>
					</c:if>
					<c:if test="${fn:length(goodslist) == 0 && actType == 25}">
						<jsp:include page="../common/null_list_hint.jsp">
							<jsp:param name="content" value="限时抢购商品" />
						</jsp:include>
					</c:if>
					<c:if test="${fn:length(goodslist) == 0 && actType == 30}">
						<jsp:include page="../common/null_list_hint.jsp">
							<jsp:param name="content" value="闪购商品" />
						</jsp:include>
					</c:if>
				</div>
			</div>
			<!-- 自定义分页组件 -->
			<c:if test="${actType == 30}">
				<form id="fm_goodsList" action="${rootUrl }/activity/getGrouponList.htm" method="post">
					<input type="hidden" id="page" name="currPage" value="${currPage }" />
					<input type="hidden" id="total_page" value="${total_page }" />
				</form>
			</c:if>
			
			<c:if test="${actType == 25}">
				<form id="fm_goodsList" action="${rootUrl }/activity/getFlashSaleList.htm" method="post">
					<input type="hidden" id="page" name="currPage" value="${currPage }" />
					<input type="hidden" id="total_page" value="${total_page }" />
				</form>
			</c:if>
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
