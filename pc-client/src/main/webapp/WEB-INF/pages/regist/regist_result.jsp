<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<%@include file="../common/common_no_head.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/regist/regist.css' type='text/css' />
<script type="text/javascript">
//5秒后自动跳转到首页
function autoToIndex(){
	var _autoToIndex = $("#autoToIndex");
	var second = 5;
	var timer = window.setInterval(function(){
		second = parseInt(second) - 1;
		_autoToIndex.html(second+"秒后自动跳转到首页");
		if(parseInt(second) <= 1){
			window.clearInterval(timer);//去掉定时器 
			toIndex();
		}
	}, 1000); //秒倒数
}

//跳转到首页
function toIndex(){
	window.location.href = _rootUrl + "/main/index.htm";
}
</script>
</head>
<body>
	
	<div id="regist_head">
		<ul id="rh_ul" class="clear">
			<li id="sls_log" onclick="toIndex();"></li>
			<li id="sls_txt"></li>
		</ul>
	</div>
	
	<ul id="regist" class="clear">
		<li id="r_cm"></li>
		<li id="r_con_msg">
			<c:if test="${result == 'success'}">
				<div id="rcm_yes"></div>
				<p>恭喜，您已注册成功！<span id="autoToIndex">5秒后自动跳转到首页</span></p>
				<a href="${rootUrl }/main/index.htm">开始购买</a>
				<script type="text/javascript">autoToIndex();</script>
			</c:if>
			<c:if test="${result != 'success'}">
				<div id="rcm_no"></div>
				<p style="color: #ff4d4d;">很遗憾，您注册失败了。</p>
				<a href="${rootUrl }/user/toRegist.htm">重新注册</a>
			</c:if>
		</li>
	</ul>
	<%@include file="../common/foot.jsp" %>
</body>
</html>