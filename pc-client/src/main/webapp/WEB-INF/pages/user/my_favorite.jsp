<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的关注</title>
<%@include file="../common/common.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/user/my_favorite.css' type='text/css' />
<script src ='${staUrl }/js/user/my_favorite.js' type='text/javascript' ></script>
</head>
<body>
	<%@include file="../common/nav.jsp" %>
	
	<%@include file="../common/category_nav.jsp" %>
	
	<div id="u_info">
		<ul class="clear" id="url_here">
			<li>首页<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li>我的手拉手<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li class="uh_cur">我的关注</li>
		</ul>
		<div id="u_info_con" class="clear">
			<%@include file="../common/menu.jsp" %>
			<div id="mf_div">
				<div id="mf_div_tit" class="clear">
					<div id="md_txt">我的关注</div>
					<ul id="md_search">
						<li><input type="text" placeholder="商品名称" id="goodsName" maxlength="20"></li>
						<li><a href="javascript:void(0);" onclick="query_goods_goodsName();">查询</a></li>
					</ul>
				</div>
				<ul id="mf_th" class="clear">
					<li class="mh_img">&nbsp;</li>
					<li class="mh_name mh_rig">商品名称</li>
					<li class="mh_price">单价（元）</li>
					<li class="mh_kc">库存</li>
					<li class="mh_sj">关注时间</li>
					<li class="mh_ope">操作</li>
				</ul>
				
				<ul id="mf_tb">
			
				<c:if test="${!empty favoriteList }">
					<c:forEach var="item" items="${favoriteList }">
						<li id="m_t_${item.id }">
							<ul class="mf_tr clear">
								<li class="mh_img mf_h"><a href="${rootUrl }/goods/getGoodsInfo.htm?id=${item.id}"><img src="${imgUrl }/58X58${item.photoUrl }" /></a></li>
								<li class="mh_name mf_h">
									<div><a href="${rootUrl }/goods/getGoodsInfo.htm?id=${item.id}">${item.goodsName }</a></div>
								</li>
								<li class="mh_price mf_h mhp_col">￥${item.price }</li>
								<li class="mh_kc mf_h"><c:choose ><c:when test="${item.isSale == 2 }">已下架</c:when><c:otherwise>${item.stock }</c:otherwise></c:choose></li>
								<li class="mh_sj mf_h"><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></li>
								<li class="mh_ope mf_h">
									<div class="mh_oe_div">
										<a href="javascript:;" onclick="addCart('${item.id}')">加入购物车</a>
										<a href="javascript:cancleFavorite('${item.id }')">取消关注</a>
									</div>
								</li>
							</ul>
						</li>
					</c:forEach>	
				</c:if>	
				<c:if test="${empty favoriteList }">				
					<jsp:include page="../common/null_list_hint.jsp">
						<jsp:param name="content" value="关注商品" />
					</jsp:include>
				</c:if>			
				</ul>
				<!-- 自定义分页组件 -->
				<form id="fm_my_favorite" action="${rootUrl }/favorite/toMyFavorite.htm" method="get">
					<input type="hidden" name="goodsName" value="${query.goodsName }" />
					<input type="hidden" id="page" name="page" value="${query.page }" />
					<input type="hidden" id="total_page" value="${total_page }" />
				</form>
				<jsp:include page="../component/page_nav.jsp">
					<jsp:param name="fm_id" value="fm_my_favorite" />
					<jsp:param name="is_visible" value="true" />
				</jsp:include>
				<!-- 自定义分页组件 -->
			</div>
		</div>
	</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
