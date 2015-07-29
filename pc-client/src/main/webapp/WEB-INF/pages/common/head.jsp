<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<ul id="head" class="clear">
	<li id="hd_slslog"><a href="${rootUrl }/main/index.htm" target="_self"></a></li>
	<li id="hd_city">
		<ul id="hc_ul" class="clear">
			<li id="hc_cur"></li>
			<!-- 
			<li id="hc_cut">
				<a id="hc_cut_a" href="javascript:;">[更换]</a>
				<div id="hc_cys_div">
					<div id="hcd_line"></div>
					
				</div>
			</li>
			 -->
		</ul>
	</li>
	<li id="hd_search">
		<input id="hd_search_txt" type="text" value="${content }" maxlength="50" />
		<a href="javascript:;" id="hd_search_a"></a>
	</li>
	<li id="hd_mysls">
		<a href="javascript:;" class="clear">
			<span id="hd_ms_i"></span>
			<span class="hd_ms_a">我的手拉手</span>
		</a>
	</li>
	<li id="hd_mycar">
		<a href="${rootUrl }/carts/myCarts.htm" class="clear">
			<span id="hd_mc_i"></span>
			<span id="hd_mc_txt" class="hd_ms_a">我的购物车</span>
			<span id="hd_mc_qp"></span>
		</a>
		
	</li>
</ul>
<script type="text/javascript">
//判断登陆后执行哪种操作的flag
var afterLoginFlag = "";
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
	
	var	_hc_cut = $("#hc_cut");
	var	_hc_cut_a = $("#hc_cut_a");
	var	_hc_cys_div = $("#hc_cys_div");
	_hc_cut_a.click(function() {
		_hc_cut.css({"border-left":"solid 1px #ddd", "border-right":"solid 1px #ddd", "border-top":"solid 1px #ddd"});
		
		_hc_cys_div.show();
	});
	_hc_cut.hover(function() {}, function() {
		_hc_cut.css({"border-left":"solid 1px #fff", "border-right":"solid 1px #fff", "border-top":"solid 1px #fff"});
		
		_hc_cys_div.hide();
	});
	
	$("#hd_search_txt").val();
	changeCity("${sessionScope.cityId}");
	
	//我的手拉手点击
	$("#hd_mysls a").click(function() {
		if(!_is_user_login()){
			afterLoginFlag = "toMysls";
			$("#_filter, #logdiv").show();
		} else {
			window.location.href = _rootUrl + "/user/toMysls.htm";
		}
	});
	//搜索点击
	$("#hd_search_a").click(function() {
		var content = $("#hd_search_txt").val();
		if($.trim(content) != ""){
			window.location.href = _rootUrl + "/goods/getGoodsListBySearch.htm?content=" + encodeURIComponent(content);
		}
	});
	$("#hd_search_txt").keyup(function(e) {
		if (e.keyCode == 13) {
			$("#hd_search_a").click();
		}
	});
	
	//获取购物车的商品数量（购物车气泡）
	$.post(_rootUrl + "/carts/getShopCartCount.htm",
		function (data, textStatus) {
			if (data.success) {
				if (data.result != 0) {
					var _hd_mc_qp = $("#hd_mc_qp"),
						_fc_d_a = $("#fc_d_a");
					_hd_mc_qp.html(data.result).show();//顶部气泡数量变化
					if (parseFloat(_hd_mc_qp.html()) > 99) {
						_hd_mc_qp.html("99<b style='position: relative; top: -1px;'>+</b>");
					}
					if (_fc_d_a[0]) {
						_fc_d_a.html(data.result).show();//右部气泡数量变化
						if (parseFloat(_fc_d_a.html()) > 99) {
							_fc_d_a.html("99<b style='position: relative; top: -1px;'>+</b>");
						}
					}
				}
			} else {
				showMsgHint(data.errMsg,"gantan");
			}
		}
	,"json");
});

function changeCity(cityId){
	var _hc_cur = $("#hc_cur");
	var	_hc_cys_div = $("#hc_cys_div");
	_hc_cys_div.find("a").remove();
	for (var i = 0; i < citys.length; i++) {
		if (citys[i].cid == cityId) {
			_hc_cur.html(citys[i].cne);
		}
		if (citys[i].cid == 150) {
			_hc_cys_div.append($("<a href='http://www.365020.com.cn'>" + citys[i].cne + "</a>"));
		} else {
			_hc_cys_div.append($("<a href='" + _rootUrl + "/main/index.htm?cityId=" + citys[i].cid + "&provinceId=" + citys[i].pid + "&cityCode=" + citys[i].ccode + "'>" + citys[i].cne + "</a>"));
		}
	}
}

function afterLogin(){
	//进入我的手拉手页
	if(afterLoginFlag == "toMysls"){
		window.location.href = _rootUrl + "/user/toMysls.htm";
	}
}
</script>
