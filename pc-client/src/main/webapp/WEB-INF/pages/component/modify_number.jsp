<%-- 
	数量选择组件 @author huzeyu 2014-12-31
	组件用法：
	1、引入js文件：
		<script type="text/javascript" src="/pc-client/js/modify_number.js"></script>
	2、在需要使用该组件的位置引入该组件，其中number是输入框的初始值。
		<jsp:include page="/component/modify_number.jsp" flush="true">
			<jsp:param name="count" value="${number}"" />
		</jsp:include>
	3、如果点击+和-后需要执行一些js，请在自己的js文件中实现回调函数minusOneCallback和plusOneCallback，参数为input组件的jQuery对象。
	4、如果填写输入框后需要执行一些js，请在自己的js文件中实现回调函数modifyNumberCallback，参数为input组件的jQuery对象。
	5、目前组件支持的大小是1到100，课根据实际情况修改数值范围，修改js文件中的min和max。
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<span class="co_sl_span">
	<a href="javascript:;" onclick="minusOne(this);" class="css_a">-</a>
	<input type="text" value="${param.count}" tmp="${param.count}" class="css_inp" onchange="isInteger(this)" maxlength="4"/>
	<a href="javascript:;" onclick="plusOne(this);" class="css_a">+</a>
</span>
