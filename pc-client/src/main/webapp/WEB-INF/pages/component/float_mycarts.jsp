<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<div id="fc_div" style="cursor: pointer; width: 43px; height: 43px; background: url(${staUrl}/images/inner.png) no-repeat 0px -201px; position: fixed; bottom: 63px; right: 10px;">
	<a href="${rootUrl}/carts/myCarts.htm" style="display: block; width: 43px; height: 43px;">
		<span style="width: 24px; height: 16px; display: block; background: url(${staUrl}/images/index.png) no-repeat -471px -261px; position: absolute; top: -8px; left: 30px; display: none; font-size: 11px; color: #fff; text-align: center;" id="fc_d_a"></span>
	</a>
</div>
<script type="text/javascript">
	$(function() {
		var _fc_div = $("#fc_div");
		_fc_div.hover(function() {
			_fc_div.css("background-position","0px -244px");
		}, function() {
			_fc_div.css("background-position","0px -201px");
		});
	});
</script>
