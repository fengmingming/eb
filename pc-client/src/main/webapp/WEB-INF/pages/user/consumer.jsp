<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息页</title>
<%@include file="../common/common.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/user/consumer.css' type='text/css' />
<script type="text/javascript" src="${staUrl}/js/user/consumer.js" ></script>
<script type="text/javascript">
$(function(){
	
});
</script>
</head>
<body>
	<%@include file="../common/nav.jsp" %>	
	<%@include file="../common/category_nav.jsp" %>	
	<div id="u_info">
		<ul class="clear" id="url_here">
			<li>首页<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li>用户管理<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li class="uh_cur">用户查询</li>
		</ul>
		<div id="u_info_con" class="clear">
			<%@include file="../common/menu.jsp" %>
			<div id="csr">
				<div class="clear" id="csr_tit">
					<div id="csr_txt">用户查询</div>
					<ul id="csr_search">
						<li><input type="text" maxlength="30" id="nameMobile" placeholder="手机号或用户名" value=""></li>
						<li><a onclick="getConsumer();" href="javascript:void(0);">查询</a></li>
					</ul>
				</div>
				<ul class="clear" id="csr_th">
					<li class="csr_yhm">用户名</li>
					<li class="csr_sjh">手机号</li>
					<li class="csr_jt">金额(<a href="javascript: showMoney();" class="csr_jt_a">显示金额</a>)</li>
				</ul>
				<ul>					
					<c:if test="${!empty consumer }">
						<c:forEach var="user" items="${consumer }" >
							<li>
								<ul class="clear csr_tr">
									<li class="csr_yhm">${user.userName }</li>
									<li class="csr_sjh">${user.mobile }</li>
									<li class="csr_je">${user.amount }</li>
								</ul>
							</li>
						</c:forEach>
					</c:if>
					<c:if test="${empty consumer }">
						<jsp:include page="../common/null_list_hint.jsp">
							<jsp:param name="content" value="用户信息" />
						</jsp:include>
					</c:if>
					
				</ul>
			</div>
			<!-- 自定义分页组件 -->
			<form id="fm_consumer" action="${rootUrl }/user/consumer.htm" method="get">
				<input type="hidden" id="page" name="page" value="${page }" />
				<input type="hidden" id="total_page" value="${total_page }" />
			</form>
			<jsp:include page="../component/page_nav.jsp">
				<jsp:param name="fm_id" value="fm_consumer" />
				<jsp:param name="is_visible" value="true" />
			</jsp:include>
			<!-- 自定义分页组件 -->
		</div>
	</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
