<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script type="text/javascript">
$(function() {
	
	var _g_i_scroll_ul = $("#g_i_scroll_ul"),
		_lis = _g_i_scroll_ul.find("li.g_i_scroll_ul_li"),
		_li_w = 248,
		speed = 600,
		tempLength = 0,
		countLength = (_lis.length - 3) * _li_w,
		_mp = $("#g_i_prev"),
		_mn = $("#g_i_next");
	_mp.click(function() {
		if(tempLength > 0){
			if(tempLength > _li_w){
				_g_i_scroll_ul.animate({left: "+=" + _li_w + "px"}, speed);
				tempLength -= _li_w;
			}else{
				_g_i_scroll_ul.animate({left: "+=" + _li_w + "px"}, speed);
				tempLength = 0;
			}
		}
	});
	_mn.click(function() {
		if(tempLength < countLength){
			if((countLength - tempLength) > _li_w){
				_g_i_scroll_ul.animate({left:"-=" + _li_w + "px"}, speed);
				tempLength += _li_w;
			}else{
				_g_i_scroll_ul.animate({left:"-=" + (countLength - tempLength) + "px"}, speed);
				tempLength += (countLength - tempLength);
			}
		}
	});
	var _mps = _mp.find("span"),
	_mns = _mn.find("span");
	_mp.hover(function() {
		_mps.css("background-position","-12px -564px");
	}, function() {
		_mps.css("background-position","0px -564px");
	});
	_mn.hover(function() {
		_mns.css("background-position","-24px -564px");
	}, function() {
		_mns.css("background-position","-36px -564px");
	});
	
	$("#g_p_d_mimg").hover(function() {
		$("#g_p_d_ftr").show();
	}, function() {
		$("#g_p_d_ftr").hide();
	});
});



</script>
<style type="text/css">
#g_p_div { width: 1200px; margin: auto;}
	#g_p_div_tit { font-size: 20px; color: #ff7f00; padding: 25px 0px 10px 0px; text-indent: 18px;}
	#g_p_d_ul { border: solid 1px #ccc;}
		.gpd_ul_li { float: left;}
		#g_p_d_mimg { width: 398px; height: 218px; border-right: solid 1px #ccc; position: relative;}
			#g_p_d_ftr { position: absolute; top: 0px; left: 0px; width: 398px; height: 218px; background: #fff; opacity: 0.2; filter: alpha(opacity=20); display: none;}
			#g_p_d_ftr a { display: block; width: 398px; height: 218px;}
			#g_p_d_mimg img { width: 398px; height: 218px; border: 0px;}
		#g_i_scroll_con { }
			#g_i_prev, #g_i_next, #g_i_scroll_div { float: left;}
			#g_i_prev { width: 28px; height: 218px;}
			#g_i_next { width: 28px; height: 218px;}
			#g_i_scroll_div { width: 743px; height: 218px; overflow: hidden; position: relative;}
				#g_i_scroll_ul { width: 9999px; position: absolute; top: 0px; left: 0px;}
				#g_i_scroll_ul li.g_i_scroll_ul_li { float: left; width: 247px; height: 218px; border-right: solid 1px #ccc;}
				#g_i_scroll_ul li.g_i_scroll_ul_li img { width: 228px; margin-top: -45px; display: block; border: 0px;}
					.glc_rt_gds_img a { width: 228px; height: 138px; display: block; overflow: hidden; margin: auto; margin-top: 10px;}
				#g_i_scroll_ul li.g_i_scroll_ul_li .gisui_txt { width: 228px; margin: auto; font-size: 12px; color: #666; line-height: 24px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap; word-wrap: normal;}
					.gisui_ul { width: 228px; margin: auto; margin-top: 4px;}
					.gisui_ul li { float: left; font-size: 12px; color: #a7a7a7;}
						.gisui_ul_li { position: relative; top: 12px;}
			#gip_s { background: url(../../images/inner.png) no-repeat 0px -564px; width: 12px; height: 22px; display: block; position: relative; left: 50%; top: 50%; margin-left: -6px; margin-top: -11px;}
			#gin_s { background: url(../../images/inner.png) no-repeat -36px -564px; width: 12px; height: 22px; display: block; position: relative; left: 50%; top: 50%; margin-left: -6px; margin-top: -11px;}
</style>

<div id="g_p_div">

	<div id="g_p_div_tit">闪购活动</div>
	<ul class="clear" id="g_p_d_ul">
		<li class="gpd_ul_li" id="g_p_d_mimg">
			<img src="${staUrl }/images/groupon.png" >
			<div id="g_p_d_ftr"><a target="_blank" href="${rootUrl }/activity/getGrouponList.htm"></a></div>
		</li>
		<li class="gpd_ul_li clear" id="g_i_scroll_con">
			<a id="g_i_prev" href="javascript:;"><span id="gip_s"></span></a>
			<div id="g_i_scroll_div">
			<ul class="clear" id="g_i_scroll_ul">
				<c:forEach var="goods" items="${goodslist }" begin="0" end="8">
					<li class="g_i_scroll_ul_li">
						<input class="grg_goods_id" type="hidden" value="${goods.id}" />
						<div class="glc_rt_gds_img">
							<a target="_blank" href="${rootUrl }/goods/getGoodsInfo.htm?id=${goods.id}"><img src="${imgUrl }/430X430${goods.photoUrl}" /></a>
						</div>
						<div class="gisui_txt">
						【闪购】<a href="${rootUrl }/goods/getGoodsInfo.htm?id=${goods.id}" title="${goods.goodsName }">${goods.goodsName }</a>
						</div>
						<ul class="clear gisui_ul">
							<li class="gisui_ul_li" style="font-weight: bold; color: red;">￥</li>
							<li style="font-weight: bold; color: red; font-size: 24px;">${goods.actDto[0].actPrice}</li>
							<li class="gisui_ul_li" style="text-decoration: line-through; margin-left: 10px;">￥${goods.marketPrice}</li>
							<!-- 
							<li class="gisui_ul_li" style="float: right;">${goods.actDto[0].actBuyNumber}件已售</li>
							 -->
						</ul>
					</li>
					</c:forEach>
				 </ul>
			</div>
			<a id="g_i_next" href="javascript:;"><span id="gin_s"></span></a>
		</li>
	</ul>

</div>
