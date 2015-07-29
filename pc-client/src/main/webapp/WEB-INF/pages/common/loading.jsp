<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<style type="text/css">
	.loading_filter_div { position: absolute; top: 0px; left: 0px; width: 100%; background: #000; opacity: 0.5; filter: alpha(opacity=50); z-index: 50; display: none;}
	.loading_pop_div { width: 100px; background: #fff; border-radius: 3px; position: fixed; top: 50%; left: 50%; margin-top: -50px; margin-left: -55px; z-index: 100; display: none; padding: 10px 5px;}
		.loading_img_icon {  display: block; margin: auto;}
		.loading_txt { text-align: center; font-size: 14px;}
</style>

<div class="loading_filter_div"></div>
<div class="loading_pop_div">
	<img class="loading_img_icon" src="${staUrl }/images/loading.gif" />
	<div class="loading_txt"></div>
</div>

<script type="text/javascript">
$(function() {
	var _loading_filter_div = $(".loading_filter_div");
	_loading_filter_div.height($(document).height());
	var _loading_pop_div = $(".loading_pop_div");
	
	window.showLoading = function(str) {
		_loading_filter_div.height($(document).height()).show();
		_loading_pop_div.show();
		if (str) {
			$(".loading_txt").html(str);
		}
	};
	
	window.hideLoading = function(str) {
		if (str) {
			$(".loading_txt").html(str);
		}
		setTimeout(function() {
			_loading_filter_div.hide();
			_loading_pop_div.hide();
		}, 500);
	};
});
</script>
