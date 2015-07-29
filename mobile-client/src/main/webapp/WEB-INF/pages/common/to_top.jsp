<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<style type="text/css">
	#to_top { width: 35px; height: 35px; display: none; cursor: pointer; position: fixed; bottom: 80px; right: 5px;  border: solid 1px #ccc; background: #f5f5f5 url(/mobile/images/to_top.png) no-repeat center 9px;}
</style>

<div id="to_top"></div>

<script type="text/javascript">
$(function() {
	var _to_top = $("#to_top");
	$(window).scroll(function(){
		if ($(window).scrollTop()>100){
			_to_top.fadeIn();
		}else{
			_to_top.fadeOut();
		}
	});
	_to_top.click(function(){
		$('body,html').animate({scrollTop:0},500);
		return false;
	});
});
</script>
