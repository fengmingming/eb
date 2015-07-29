<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="top">
	<div id="top_con">
		<ul id="top_con_lf">
			<li id="top_con_star"></li>
			<li><a href="javascript:;" onclick="addToFavorite();" title="手拉手-把服务送到家门口">收藏手拉手</a></li>
		</ul>
		<ul id="top_con_rt">
			<c:if test="${empty sessionScope.user}">
				<li><a id="welcome">欢迎光临手拉手</a></li>
				<li><a id="dologin" href="javascript:;" class="tp_hover_a">请登录</a></li>
				<li><a id="doregist" href="${rootUrl }/user/toRegist.htm" class="tp_hover_a">免费注册</a></li>
			</c:if>
			<c:if test="${!empty sessionScope.user}">
				<li><a id="welcome">
					<span style="cursor:hand;cursor:pointer;" onclick="toUserInfo();" id="tp_username">${sessionScope.user.userName}</span>&nbsp;&nbsp;&nbsp;&nbsp;欢迎光临手拉手！
					<span style="cursor:hand;cursor:pointer;" onclick="logout();" id="tp_logout">[退出]</span></a>
				</li>
			</c:if>
			<c:if test="${empty user || user.memberType == 1 }">
				<li><a id="myorder" href="javascript:;" onclick="toMyOrder()" class="tp_hover_a">我的订单</a></li>
			</c:if>
			<!--<li><a href="javascript:;">客户服务</a></li>  -->
		</ul>
	</div>
</div>
<script type="text/javascript">
function logout(){
	$.ajax({
		type: "get",
		url : _rootUrl + "/user/logout.htm",
		async: false,
		cache: false,
		dataType: "json",
		success: function(data){
			//var a = document.createElement("a");
			//a.setAttribute("href",_rootUrl + "/main/index.htm");
			//mar.href = _rootUrl + "/main/index.htm";
			//a.click();
			$("#welcome").attr("href",_rootUrl + "/main/index.htm");
			$("#welcome").click();
		}		
	});	
}

function toUserInfo(){
	window.location.href = _rootUrl + "/user/toUserInfo.htm";
}

function addToFavorite(){
	var title = "手拉手-把服务送到家门口";
    var url = _rootUrl + "/main/index.htm";
    try {
		window.external.addFavorite(url, title);
	} catch(e) {
		try {
			window.sidebar.addPanel(title, url, title);
		} catch(e) {
			showMsgHint("抱歉，您所使用的浏览器无法完成此操作。\n\n加入收藏失败，请使用Ctrl+D进行添加","gantan");
		}
	}
}

function toMyOrder(){
	if(!_is_user_login()){
		$("#_filter, #logdiv").show();
	}else{
		window.location.href = _rootUrl + "/order/getOrderList.htm";
	}
}

$(function() {
	$("#tp_logout, #tp_username").hover(function() {
		$(this).addClass("tp_logout");
	}, function() {
		$(this).removeClass("tp_logout");
	});
});
</script>
