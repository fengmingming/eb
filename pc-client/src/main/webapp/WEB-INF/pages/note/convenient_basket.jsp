<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>便民菜篮</title>
<%@include file="../common/common.jsp" %>

<style type="text/css">
	#note_right { float: left; margin-left: 10px; width: 980px; background: #fff; height: 480px; overflow: hidden;}
		#nr_tit { font-size: 20px; color: #666; padding: 22px 0px 50px 22px;}
		#nr_img { width: 712px; height: 374px; background: url(${staUrl }/images/note/_bmcl.png) no-repeat; margin-left: 100px; margin-top: 1px;}
</style>

</head>
<body>
	<%@include file="../common/nav.jsp" %>
	
	<%@include file="../common/category_nav.jsp" %>
	
	<div id="u_info">
		<ul class="clear" id="url_here">
			<li>首页<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li>便民菜篮</li>
		</ul>
		<div id="u_info_con" class="clear">
			<%@include file="note_menu.jsp" %>
			<div id="note_right">
				<div id="nr_tit">便民菜篮</div>
				<div id="nr_img"></div>
			</div>
		</div>
	</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
