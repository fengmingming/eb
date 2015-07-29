<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<div id="ft_div" style="display: none; cursor: pointer; width: 43px; height: 43px; background: url(${rootUrl}/images/inner.png) no-repeat 0px -287px; position: fixed; bottom: 10px; right: 10px;"></div>
<script type="text/javascript">
	$(function() {
		var _ft_div = $("#ft_div");
		$(window).scroll(function(){
			if ($(window).scrollTop()>100){
				_ft_div.fadeIn();
			}else{
				_ft_div.fadeOut();
			}
		});
		_ft_div.hover(function() {
			_ft_div.css("background-position","0px -330px");
		}, function() {
			_ft_div.css("background-position","0px -287px");
		});
		_ft_div.click(function(){
			$('body,html').animate({scrollTop:0},500);
			return false;
		});
	});
</script>
