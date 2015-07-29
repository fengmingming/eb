<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>手拉手触屏版</title>
	<%@include file="../common/common.jsp" %>
	<link rel='stylesheet' href='${staUrl }/css/goods/goods_list.css' type='text/css' />
	<script type="text/javascript" src="${staUrl }/js/common/jquery.cookie.js"></script>
	<script type="text/javascript" src="${staUrl }/js/activity/goodsDynpage_list.js"></script>
</head>

<body>
	<div class="goods_detail">
		<iframe name="goods_detail_frame" src="" width="100%"  frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" style="max-width: 640px; min-width: 300px; height: 0px;"></iframe>
	</div>
	<div class="viewport">
		<div id="header">
			<div class="h_back" ></div>
			<div class="header_txt">${title }</div>
			<%@include file="../common/mz_nav.jsp" %>
			<!-- 
			<ul class="clear hd_search">
				<li class="hd_s_lef"></li>
				<li class="hd_s_mid"><input id="search_input" type="text"/></li>
				<li class="hd_s_rig"></li>
				<li class="hd_s_fdj"></li>
				<li class="hd_s_qx"><a id="qx_a" href="javascript:;"></a></li>
			</ul>
			 
			<ul class="clear hd_search">
				<li class="hd_s_fdj"></li>
				<li class="hd_s_inp">
				<input id="search_input" type="text" <c:if test="${flag == true }">placeholder="请输入搜索关键词"</c:if>/></li>
				<li class="hd_s_qx"><a id="qx_a" href="javascript:;"></a></li>
			</ul>
			-->
		</div>
		<!-- 
		<ul class="clear sort_url">
			<li class="su_border"><a href="javascript:;" onclick="changeDefault(this);" id="default">默认</a></li>
			<li class="su_border"><a href="javascript:;" onclick=" changeSale(this);" id="sale"><i class="gl_s_jt"></i>销量</a></li>
			<li><a href="javascript:;" onclick="changePrice(this)" id="price"><i class="gl_s_sxjt"></i>价格</a></li>
		</ul>
		 -->
		<ul class="clear goods_list">
			<c:if test="${!empty goodslist }">
				<c:forEach items="${goodslist }" var="goods">
					<li>
						<a href="javascript:;" onclick="showGoodsDetail(${goods.id});">
							<img src="${imgUrl }/200X200${goods.photoUrl}" class="gl_img" />
							<c:if test="${empty goods.actDto}">
								<p class="gl_txt">${goods.goodsName }</p>
								<p class="gl_xj">￥${goods.price }<span class="gl_yj">${goods.marketPrice }</span></p>				
							</c:if>
							<c:if test="${!empty goods.actDto}">
								<p class="gl_txt">【${goods.actDto[0].actTypeName}】${goods.goodsName }</p>
								<p class="gl_xj">￥${goods.actDto[0].actPrice}<span class="gl_yj">${goods.marketPrice }</span></p>	
							</c:if>							
						</a>
					</li>
				</c:forEach>
			</c:if>
		</ul>
		<c:if test="${flag != true }">
			<c:if test="${empty goodslist }">
				<jsp:include page="../common/null_list_hint.jsp">
					<jsp:param name="content" value="相关商品" />
				</jsp:include>
			</c:if>
		</c:if>
		<form id="fm_goodsList" action="${rootUrl }/activity/getDynpageGoodsList.htm" method="get">
			<input type="hidden" id="page" name="currPage" value="${currPage }" />
			 <input type="hidden" id="content" name="content" value="${content }" />  
			<input type="hidden" id="total_page" value="${total_page }" />
			<input type="hidden" id="dynpageId" name="dynpageId" value="${dynpageId }" />
		</form>
	</div>
</body>
</html>