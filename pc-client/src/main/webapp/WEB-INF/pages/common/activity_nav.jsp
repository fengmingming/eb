<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
	#ay_nav { background: #ff9900; height: 50px;}
		#ay_nav_con { width: 1200px; margin: auto;}
			#ay_nav_con li { float: left; width: 100px; line-height: 50px; height: 50px; text-align: center;}
			#ay_nav_con li a { color: #fff; display: block;}
			#ay_nav_con li a:hover { background: #ec6700;}
			
			.ancl_cur { background: #ec6700;}
</style>
<div id="ay_nav">
	<ul id="ay_nav_con" class="clear">
		<li><a href="${rootUrl }/main/index.htm">首页</a></li>
		<li <c:if test="${actType == 30 }"> class="ancl_cur" </c:if>><a href="${rootUrl }/activity/getGrouponList.htm">闪购</a></li>
		<li <c:if test="${actType == 25 }"> class="ancl_cur" </c:if>><a href="${rootUrl }/activity/getFlashSaleList.htm">限购</a></li>
	</ul>
</div>
