<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<title>${goods.goodsName }&nbsp;-&nbsp;商品详情&nbsp;-&nbsp;手拉手</title>
<%@include file="../common/common.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/common/nav.css' type='text/css' />

<link rel='stylesheet' href='${staUrl }/css/goods/goods_detail.css' type='text/css' />

<script type="text/javascript" src="${staUrl }/js/common/jquery.jqzoom.js"></script>
<script type="text/javascript" src="${staUrl }/js/goods/goods_detail.js"></script>
</head>
<body>
	<%@include file="../common/nav.jsp" %>
	
	<%@include file="../common/category_nav.jsp" %>
	
	<div id="goods_detail">
		<div id="url_here">
			<a class="uh_hover_a" href="${rootUrl }/main/index.htm">首页</a>
			&nbsp;&gt;&nbsp;<a class="uh_hover_a" href="${rootUrl }/goods/getGoodsList.htm?first=${firstId }">${firstName }</a>
			<c:if test="${!empty secondName}">
				&nbsp;&gt;&nbsp;<a class="uh_hover_a" href="${rootUrl }/goods/getGoodsList.htm?second=${secondId }">${secondName }</a>
			</c:if>
			&nbsp;&gt;&nbsp;${goods.goodsName }</div>
		<div id="goods" class="clear">
			<div id="gd_imgs">
				<div id="preview" class="spec-preview">
					<span class="jqzoom">
						<img style="width: 430px; height: 430px;" jqimg="${imgUrl }/800X800${PhotoUrl}" src="${imgUrl }/430X430${PhotoUrl }" />
					</span>
				</div>
				<div class="spec-scroll clear">
					<a class="prev">&lt;</a>
					<a class="next">&gt;</a>
					<div class="items">
						<ul>
							<c:forEach items="${urls}" var="url">
							<c:if test="${!empty url }">
								<li><img bimg="${imgUrl }/800X800${url }" src="${imgUrl }/58X58${url }" onmousemove="preview(this);"/></li>
							</c:if>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<div id="gd_des">
				<input type="hidden" value="${goods.id}" id="gd_des_id" />
				<div id="gd_des_name">
					<c:if test="${!empty commodityActDto.actTypeName}">【${commodityActDto.actTypeName }】</c:if>${goods.goodsName}
				</div>
				<c:if test="${empty commodityActDto.actPrice }">
					<ul id="gd_des_xl" class="clear">
						<li style="padding: 0px 22px 0px 12px">月销量</li>
						<li id="gdx_yxl">${goods.monthNum}</li>
						<li style="padding: 0px 45px 0px 80px">|</li>
						<li>积分</li>
						<li id="gdx_jf">0</li>
					</ul>
				</c:if>
				<ul id="gd_des_pri">
					<c:if test="${!empty commodityActDto.actPrice }">
						<li>
							<span id="gdp_c">￥${commodityActDto.actPrice}</span>
							<span id="gdp_s">￥${goods.marketPrice}</span>
						</li>
						<!--
						<li id="gd_des_yd">
							<ul class="clear">
								<li><i id="gdy_my"></i><a href="javascript:;">免约</a></li>
								<li><i id="gdy_gqt"></i><a href="javascript:;">过期退</a></li>
								<li><i id="gdy_sst"></i><a href="javascript:;">随时退</a></li>
							</ul>
						</li>
						-->
					</c:if>
					<c:if test="${empty commodityActDto.actPrice }">
						<li>促销价<span id="gdp_c">￥${goods.price}</span></li>
						<li>市场价<span id="gdp_s">￥${goods.marketPrice}</span></li>
					</c:if>
				</ul>
				<c:if test="${! empty commodityActDto.actPrice }">
				
					<ul id="gd_des_ser">
						<li>购买数量：<span style="color: #c40000; font-weight: bold; font-size: 18px;">${goods.monthNum }</span>件已售</li>
					</ul>
				  
					<ul id="gd_des_time" class="clear">
						<li>剩余时间：</li>
						<li id="sysj"><span id="day_show"></span>天<span id="hour_show"></span>小时<span id="minute_show"></span>分钟<span id="second_show"></span>秒</li>
					</ul>
					<input type="hidden" value="${ time}" id="total_seconds" />
				</c:if>
				<c:if test="${empty commodityActDto.actPrice }">
					<ul id="gd_des_ser">
						<li>服务：由手拉手负责发货，并提供售后服务。</li>
						<li>温馨提示：该商品不支持7天无理由退货。</li>
					</ul>
				</c:if>
				<ul id="gd_des_num" class="clear">
					<li id="gdn_num">数量</li>
					<li id="gd_des_aia">
						<c:if test="${goods.isSale == 1 }">
							<a href="javascript:;" class="gda_a minus_a">-</a>
							<input type="text" value="1" id="gda_inp" tmp="1" maxlength="4"/>
							<a href="javascript:;" class="gda_a plus_a">+</a>
						</c:if>
						<c:if test="${goods.isSale == 2 }">
							<a href="javascript:;" class="gda_a" style="color: #aeaeae;"></a>
							<input type="text" value="1" id="gda_inp" tmp="1" readonly="readonly" style="color: #aeaeae;" maxlength="4"/>
							<a href="javascript:;" class="gda_a" style="color: #aeaeae;"></a>
						</c:if>
					</li>
					<c:if test="${!empty commodityActDto.actPrice && commodityActDto.number > 0}">
						<li id="gdn_xgsl">限购数量(${commodityActDto.number})</li>
					</c:if>
				</ul>
				<ul id="gd_des_opt" class="clear">
					<c:if test="${goods.isSale == 1 }">
						<li><a href="javascript:;" id="gdo_buy">立刻购买</a></li>
					</c:if>
					<li>
						<c:if test="${goods.isSale == 1 }">
							<a href="javascript:;" id="gdo_car">
								<i id="gdo_car_ig"></i>
								<span>加入购物车</span>
							</a>
						</c:if>
						<c:if test="${goods.isSale == 2 }">
							<a href="javascript:;" id="gdo_car_de">
								<i id="gdo_car_ig_de"></i>
								<span>商品已下架</span>
							</a>
						</c:if>
					</li>
					
					<li>
						<a href="javascript:;" id="gdo_col">
							<i id="gdo_col_ig"></i>
							<span>加关注</span>
						</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	
	<div id="goods_adv" class="clear">
		<div id="glc_lt">
			<div id="glc_lt_tit">商品推荐</div>
			<div id="glc_lt_gdss">
				<c:if test="${topGoods != null }">
					<c:forEach var="item" items="${topGoods }" begin="0" end="5">
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
			<c:if test="${!empty goods.detailTip }">
				<div id="glc_tip">
					${goods.detailTip }
				</div>
			</c:if>
			<ul class="clear" id="glc_rt_tit">
				<li class="grt_txt grt_txt_cur">商品介绍</li>
				<li class="grt_sg"></li>
				<!-- 
				<li class="grt_txt">商品规格</li>
				<li class="grt_sg"></li>
				 -->
			</ul>
			${goods.description }
		</div>
	</div>
	
	<%@include file="../common/foot.jsp" %>

	<%@include file="../component/float_mycarts.jsp" %>
	<%@include file="../component/float_top.jsp" %>
	
	<div id="fly_cart" style="position: absolute; top: 0px; left: 0px; width: 43px; height: 43px; opacity: 0; filter: alpha(opacity=0); display: none;"><img src="" style="width: 43px; height: 43px;" /></div>
	
	<%@include file="../component/cart_bubble.jsp" %>
</body>
</html>