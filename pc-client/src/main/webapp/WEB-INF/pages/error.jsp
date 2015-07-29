<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>页面出错啦</title>
<%@include file="common/common.jsp" %>
<style type="text/css">
	#error { background: #f5f5f5; padding: 50px 0px;}
		#error_con { width: 980px; height: 240px; margin: auto; box-shadow: 0 4px 4px #cbc7c4; background: #fff url(${staUrl }/images/404.png) no-repeat 135px center;}
			#error_con ul { line-height: 40px; float: right; margin-top: 75px; margin-right: 290px;}
				#error_txt { font-size: 18px; color: #e4393b;}
				#error_ope { font-size: 14px;}
				#error_ope a { color: #ff7f00;}
</style>
</head>
<body>
	<%@include file="common/nav.jsp" %>

	<%@include file="common/category_nav.jsp" %>
	
	<div id="error">
		<div id="error_con">
			<ul>
				<li id="error_txt">糟糕，系统出错了！</li>
				<li id="error_ope">返回&nbsp;<a href="${staUrl }/main/index.htm">网站首页</a></li>
			</ul>
		</div>
	</div>
	
	<%@include file="common/foot.jsp" %>
</body>
</html>