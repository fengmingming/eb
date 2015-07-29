<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>${actTypeName }</title>
	<%@include file="../common/common.jsp" %>
	<link rel='stylesheet' href='${staUrl }/css/goods/group_flash_list.css' type='text/css' />
	<script type="text/javascript" src="${staUrl }/js/common/jquery.cookie.js"></script>
	<script src="${staUrl }/js/activity/group_flash_list.js"></script>
</head>

<body>
	<div class="goods_detail">
		<iframe name="goods_detail_frame" src="" width="100%"  frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" style="max-width: 640px; min-width: 300px; height: 0px;"></iframe>
	</div>
	<div class="viewport">
		<div id="header">
			<div class="header_txt">${actTypeName }</div>
			<div class="h_back" onclick="javascript: window.location.href='${rootUrl}/index.htm';"></div>
			<%@include file="../common/mz_nav.jsp" %>
		</div>
		<ul class="clear goods_list">
			<jsp:include page="../common/null_list_hint.jsp">
				<jsp:param name="content" value="相关商品" />
			</jsp:include>
		</ul>
		<input type="hidden" id="actType" value="${actType }" />
		<input type="hidden" id="page" name="page" value="1" />
		<input type="hidden" id="totalNum" value="0" />
	</div>
</body>
</html>