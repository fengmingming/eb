<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<script type="text/javascript">
$(function() {
	/*
	$(".h_opt").hover(function(event) {
		$(".ho_menu").show();
		event.stopPropagation();
	}, function(event) {
		$(".ho_menu").hide();
	});*/
	
	var _h_opt_div = $("#h_opt_div").width($(window).width()).height($(document).height());
	
	var _ho_menu = $(".ho_menu");
	$(".h_opt").click(function(event) {
		event.stopPropagation();
		if (_ho_menu.is(":visible")) {
			_ho_menu.hide();
			_h_opt_div.hide();
		} else {
			_ho_menu.show();
			_h_opt_div.show();
		}
	});
	
	_h_opt_div.click(function() {
		if (_ho_menu.is(":visible")) {
			_ho_menu.hide();
			_h_opt_div.hide();
		}
	});
	
	$("#index").click(function() {
		top.location.href = _rootUrl + "/index.htm";
	});
	$("#search").click(function() {
		top.location.href = _rootUrl + "/goods/getGoodsListBySearch.htm?flag=true";
	});
	$("#carts").click(function() {
		top.location.href = _rootUrl + "/carts/myCarts.htm";
	});
	$("#category").click(function() {
		top.location.href = _rootUrl + "/category.htm";
	});
	$("#my").click(function() {
		if(!_is_user_login()){
			top.location.href = _rootUrl + "/user/login.htm?flag=true";
		} else {
			top.location.href = _rootUrl + "/pcenter/index.htm";
		}
	});
});
</script>
<style type="text/css">
	.h_opt { position: absolute; width: 8px; height: 30px; top: 8px; right: 10px; background: url(/mobile/images/sls_mz.png) 0px -118px; background-size: 520px; z-index: 10; -webkit-tap-highlight-color:rgba(0,0,0,0);}
		.ho_menu { position: absolute; top: 26px; right: -10px; background: #fff; width: 100px; display: none; border: solid 1px #ccc; border-bottom: 0px;}
			.ho_menu li { border-bottom: solid 1px #ccc; height: 38px; line-height: 38px;}
				.ho_menu li a { display: block; font-size: 12px; color: #666; text-indent: 35px; position: relative;}
					.mn_opt { background: url(/mobile/images/sls_mz.png) no-repeat; background-size: 600px; width: 20px; height: 20px; display: inline-block; position: absolute; top: 0px; left: 0px;}
					.mn_sy { background-position: -270px 0px; top: 9px; left: 8px;}
					.mn_ss { background-position: -270px -21px; top: 9px; left: 8px;}
					.mn_gw { background-position: -270px -41px; top: 10px; left: 7px;}
					.mn_fl { background-position: -270px -61px; top: 9px; left: 8px;}
					.mn_sl { background-position: -270px -82px; top: 10px; left: 9px;}
					
	#h_opt_div { position: absolute; top: 0px; left: 0px; /*opacity: 0.5; filter: alpha(opacity=50); background: #000;*/ display: none;}
</style>

<div class="h_opt">
	<ul class="ho_menu">
		<li id="index"><a href="javascript:;"><i class="mn_opt mn_sy"></i>首页</a></li>
		<li id="search"><a href="javascript:;"><i class="mn_opt mn_ss"></i>搜索</a></li>
		<li id="carts"><a href="javascript:;"><i class="mn_opt mn_gw"></i>购物车</a></li>
		<li id="category"><a href="javascript:;"><i class="mn_opt mn_fl"></i>分类</a></li>
		<li id="my"><a href="javascript:;"><i class="mn_opt mn_sl"></i>手拉手</a></li>
	</ul>
</div>
<div id="h_opt_div"></div>
