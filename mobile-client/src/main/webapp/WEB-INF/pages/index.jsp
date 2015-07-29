<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>手拉手触屏版</title>
<%@include file="common/common.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/index.css' type='text/css' />
<script src="${staUrl }/js/common/TouchSlide.1.1.js"></script>
<script type="text/javascript" src="${staUrl }/js/common/jquery.cookie.js"></script>
<script src="${staUrl }/js/index.js"></script>
<script type="text/javascript">
var citys = "";
$(function() {
	//根据json文件得到所有城市信息
	$.ajax({
		url: _rootUrl + "/json/city.json",
		dataType: "json",
		async: false,
		success: function(result){
			citys = result.citys;
		}
	});
	
	$("#hd_search_txt").val();
	changeCity("${sessionScope.cityId}");
	
	//我的手拉手点击
	$(".my").click(function() {
		if(!_is_user_login()){
			window.location.href = _rootUrl + "/user/login.htm?flag=true";
		} else {
			window.location.href = _rootUrl + "/pcenter/index.htm";
		}
	});
	
	//搜索点击
	/* $("#hd_search_txt").keyup(function(e) {
		 if (e.keyCode == 13) {
			var content = $("#hd_search_txt").val();
			if($.trim(content) != ""){
				window.location.href = _rootUrl + "/goods/getGoodsListBySearch.htm?content=" + encodeURIComponent(content);
			}
		}else{
			return ;
		} 
	});  */ 
	
	$("#sls_search ul").click(function() {
		window.location.href = _rootUrl + "/goods/getGoodsListBySearch.htm?flag=true";
	});
});

function changeCity(cityId){
	var hd_cy = $(".hd_cy");
	var hd_cy_city=$(".hd_cy_city");
	for (var i = 0; i < citys.length; i++) {
		if (citys[i].cid == cityId) {
			hd_cy_city.html(citys[i].cne);
		
			hd_cy.append($("<a class='hd_cy_a' href='" + _rootUrl + "/go_city.htm'><i class='hd_cy_i'></i></a>"));
			hd_cy.append(hd_cy_city);
		}
	}
}
</script>
</head>
<body>
	<div id="head_div">
		<header id="sls_header">
			<ul class="clear">
				<li class="sls_logo"></li>
				<c:if test="${!empty user}">
					<li class="hd_lgn"><a class="hd_lgn_a" href="${rootUrl }/pcenter/index.htm">${user.userName }</a><a class="hd_lgn_a">|</a><a class="hd_lgn_a" href="${rootUrl }/user/logout.htm">退出</a></li>
				</c:if>
				<c:if test="${empty user}">
					<li class="hd_lgn"><a class="hd_lgn_a" href="${rootUrl }/user/login.htm">登录</a></li>
				</c:if>				
				<li class="hd_cy"><a class="hd_cy_a" href="javascript:;"><i class="hd_cy_i"></i><span class="hd_cy_city "></span></a></li>
			</ul>
		</header>
	</div>
	<div class="viewport">
		<div id="slideBox" class="slideBox">
			<div class="bd">		
				<ul>
					<c:if test="${!empty broadcast }">
						<c:forEach var="item" items="${broadcast }"> 
							<li>
								<a class="pic" href="${item.url }"><img src="${imgUrl }${item.imgUrl }" /></a>
							</li>
						</c:forEach>						
					</c:if>
					<c:if test="${empty broadcast }">
						<li>
							<a class="pic" href="javascript:;"><img src="${staUrl }/images/01.jpg" /></a>
						</li>
						<li>
							<a class="pic" href="javascript:;"><img src="${staUrl }/images/02.jpg"/></a>
						</li>
						<li>
							<a class="pic" href="javascript:;"><img src="${staUrl }/images/03.jpg"/></a>
						</li>

					</c:if>						  					  												
				</ul>
			</div>
			<div class="hd">
				<ul></ul>
			</div>
		</div>
		<script type="text/javascript">
			TouchSlide({ 
				slideCell:"#slideBox",
				titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
				mainCell:".bd ul", 
				effect:"leftLoop", 
				autoPage:true,//自动分页
				autoPlay:true //自动播放
			});
		</script>
		<div id="sls_search">
			<%-- 
			<ul class="clear">
				<li class="ss_lef"></li>
				<li class="ss_mid"><input type="text" id="hd_search_txt" value="${content }" placeholder="搜本站商品"/></li>
				<li class="ss_rei"></li>
				<li class="ss_fdj"></li>
			</ul>
			--%>
			<ul class="clear">
				<li class="ss_fdj"></li>
				<li class="ss_inp"><input type="text" id="hd_search_txt" value="${content }" placeholder="搜本站商品"/></li>
			</ul>
		</div>
		<nav class="app_nav">
			<a class="app_link" href="${rootUrl }/category.htm">
				<i class="nav_img ni_fl"></i>
				<span>分类</span>
			</a>
			<a class="app_link" href="${rootUrl }/goods/topNList.htm">
				<i class="nav_img ni_rqsp"></i>
				<span>人气商品</span>
			</a>
			<a class="app_link" href="${rootUrl }/carts/myCarts.htm">
				<i class="nav_img ni_gwc"></i>
				<span>购物车</span>
			</a>
			<a class="app_link my" href="javascript:;">
				<i class="nav_img ni_wd"></i>
				<span>我的</span>
			</a>
		</nav>
		
		<ul class="goods_nav clear">
			<li class="g_n_border">
				<a href="${rootUrl }/activity/getFlashSaleList.htm">
					<span>限时抢购</span>
					<img src="${staUrl }/images/test_cm.png" />
				</a>
			</li>
			<li class="g_n_border">
				<a href="${rootUrl }/activity/getGrouponList.htm">
					<span>闪购促销</span>
					<img src="${staUrl }/images/test_gly.png" />
				</a>
			</li>
			<li class="g_n_border">
				<a href="${rootUrl }/goods/goodsList.htm?first=46">
					<span>进口食品</span>
					<img src="${staUrl }/images/test_clz.png" />
				</a>
			</li>
		</ul>
		
		<input type="hidden" id="dftcVal" value="8" />
		<ul id="dftc" class="clear floor_tit">
			<li>|地方特产</li>
			<li><a href="${rootUrl }/goods/getOEM.htm?type=8">更多...</a></li>
		</ul>
		<ul  class="clear floor_cn dftc">
			<!-- 
			<li>
				<a href=""><img src="${staUrl }/images/test_clz.png" /></a>
				<div class="flr_c_d"><a href="">康顺达有机油菜400g味道好吃，纯天然</a></div>
				<span class="flr_c_s">￥198.00</span>
			</li>
			 -->
		</ul>
		
		<input type="hidden" id="jksgVal" value="4" />
		<ul id="jksg" class="clear floor_tit">
			<li>|进口水果</li>
			<li><a href="${rootUrl }/goods/getOEM.htm?type=4">更多...</a></li>
		</ul>
		<ul class="clear floor_cn jksg">
			
		</ul>
		
		<input type="hidden" id="yjscVal" value="3" />
		<ul id="yjsc" class="clear floor_tit">
			<li>|有机蔬菜</li>
			<li><a href="${rootUrl }/goods/getOEM.htm?type=3">更多...</a></li>
		</ul>
		<ul class="clear floor_cn yjsc">
			
		</ul>
		
		<input type="hidden" id="tjspVal" value="2" />
		<ul id="tjsp" class="clear floor_tit">
			<li>|特价商品</li>
			<li><a href="${rootUrl }/goods/getOEM.htm?type=2">更多...</a></li>
		</ul>
		<ul class="clear floor_cn tjsp">
			
		</ul>
		
		<input type="hidden" id="ydlspVal" value="5" />
		<ul id="ydlsp" class="clear floor_tit">
			<li>|意大利食品</li>
			<li><a href="${rootUrl }/goods/getOEM.htm?type=5">更多...</a></li>
		</ul>
		<ul class="clear floor_cn ydlsp">
			
		</ul>
		
		<input type="hidden" id="hgspVal" value="6" />
		<ul id="hgsp" class="clear floor_tit">
			<li>|韩国食品</li>
			<li><a href="${rootUrl }/goods/getOEM.htm?type=6">更多...</a></li>
		</ul>
		<ul class="clear floor_cn hgsp">
			
		</ul>
		
		<input type="hidden" id="hxspVal" value="7" />
		<ul id="hxsp" class="clear floor_tit">
			<li>|海鲜食品</li>
			<li><a href="${rootUrl }/goods/getOEM.htm?type=7">更多...</a></li>
		</ul>
		<ul class="clear floor_cn hxsp">
			
		</ul>
	</div>
</body>
</html>