<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>${goods.goodsName }&nbsp;-&nbsp;商品详情&nbsp;-&nbsp;手拉手</title>
	<%@include file="../common/common.jsp" %>
	<link rel='stylesheet' href='${staUrl }/css/goods/goods_detail.css' type='text/css' />
	<script src="${staUrl }/js/common/TouchSlide.1.1.js"></script>
	<script type="text/javascript">
	function back(){
		if(typeof window.parent != "undefined" && 
				typeof window.parent.backToGoodsList != "undefined"){
			window.parent.backToGoodsList();
		}else{
			history.back(-1);
		}
	}
	</script>
</head>

<body>
	<div class="viewport">
		<div id="header">
			<div class="header_txt">商品详情</div>
			<div class="h_back" onclick="back();"></div>
		</div>
		
		<div class="big_img_div">
			<div id="slideBox" class="slideBox">
				<div class="bd">
					<ul>
						<c:forEach var="url" items="${urls }">
						<c:if test="${url != null && url != '' }">
							<li>
								<img src="${imgUrl }/430X430${url }" />
							</li>
						</c:if>
						</c:forEach>
					</ul>
				</div>
				<div class="hd">
					<ul></ul>
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
			</div>
		</div>
		<div class="goods_txt">
			<c:if test="${!empty commodityActDto.actTypeName}">【${commodityActDto.actTypeName }】</c:if>${goods.goodsName}
		</div>
		<div class="goods_jg">价格：
			<c:if test="${!empty commodityActDto.actPrice }">
				<span>￥${commodityActDto.actPrice}</span>
			</c:if>
			<c:if test="${empty commodityActDto.actPrice }">
				<span>￥${goods.price}</span>
			</c:if>
		</div>
		<div class="goods_imgs">
			<a href="${rootUrl }/goods/goodsImgTxt.htm?id=${goods.id}">图文详情</a>
			<i></i>
		</div>
	</div>
	<%@include file="goods_foot_cart.jsp" %>
</body>
</html>