<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
	<title>手拉手触屏版</title>
	<%@include file="../common/common.jsp" %>
	<link rel='stylesheet' href='${staUrl }/css/go_city.css' type='text/css' />
	<script type="text/javascript">
		$(function() {
			$("#sc_bj_a").click(function() {
				$(this).find("img").attr("src", _staUrl+"/images/bj_lg.png");
				$("#sc_ly_a img").attr("src", _staUrl+"/images/ly0_lg.png");
				//跳转北京
				return;
			});
			
			$("#sc_ly_a").click(function() {
				$(this).find("img").attr("src", _staUrl+"/images/ly_lg.png");
				$("#sc_bj_a img").attr("src", _staUrl+"/images/bj0_lg.png");
				//跳转洛阳
				return;
			});
		});
	</script>
</head>

<body>
	<div class="viewport">
		<div class="header">
			<div class="header_txt">
				<i></i>
				<span>选择城市</span>
			</div>
		</div>
		<ul class="clear switch_city">
			<li class="sc_bj">
				<a id="sc_bj_a" href="${rootUrl}/index.htm?cityId=52&provinceId=2&cityCode=111100">
					<img src="${staUrl }/images/bj0_lg.png"/>
				</a>
			</li>
			<li class="sc_ly">
				<a id="sc_ly_a"  href="${rootUrl}/index.htm?cityId=150&provinceId=11&cityCode=120101">
					<img src="${staUrl }/images/ly0_lg.png"/>
				</a>
			</li>
		</ul>
		<div class="fw_txt_img"><img src="${staUrl }/images/fw_txt.png" /></div>
		
		<div class="foot_div foot_txt">手拉手(北京)社区服务有限公司</div>
		<div class="foot_div">电话：010-58482177(1号服务亭)</div>
		<div class="foot_div">010-58482155(2号服务亭)</div>
		<div class="foot_div">手拉手网站：www.365020.com</div>
	</div>
</body>
</html>