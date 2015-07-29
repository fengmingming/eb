<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>关于我们</title>
<%@include file="../common/common.jsp" %>

<style type="text/css">
	#note_right { float: left; margin-left: 10px; width: 980px; height: 480px; overflow: hidden; background: #fff url(${staUrl }/images/note/_gywm.png) no-repeat 500px 237px;}
		#nr_tit { font-size: 20px; color: #666; padding: 22px 0px 50px 22px;}
		#nr_text { padding: 0px 40px; font-size: 15px; color: #666; text-indent: 30px; line-height: 26px;}
</style>

</head>
<body>
	<%@include file="../common/nav.jsp" %>
	
	<%@include file="../common/category_nav.jsp" %>
	
	<div id="u_info">
		<ul class="clear" id="url_here">
			<li>首页<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li>关于我们</li>
		</ul>
		<div id="u_info_con" class="clear">
			<%@include file="we_menu.jsp" %>
			<div id="note_right">
				<div id="nr_tit">关于我们</div>
				<div id="nr_text">手拉手&nbsp;(&nbsp;北京&nbsp;)&nbsp;社区服务有限公司&nbsp;(&nbsp;以下简称手拉手&nbsp;)&nbsp;于2014年4月投资成立，注册资金一千万元。2014年8月与团中央“中国青年创业就业基金会”共同成立“手拉手专项基金”，基金规模一亿元人民币，面向社会待业青年加入手拉手便民服务创业项目给予贷款及贷款贴息支持，以降低创业成本，促进创业就业；同时又以满足社区生活需要，提高社区幸福指数为目的，为社区居民提供便民缴费、便民家政、社区电商、社区菜篮、社区公益等服务，打造全国最具规模的一站式社区O2O服务平台。</div>
			</div>
		</div>
	</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
