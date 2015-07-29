<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel='stylesheet' href='${staUrl }/css/common/note_menu.css' type='text/css' />
<script type="text/javascript">
$(function(){
	var menuId = "${menuId }";
	$("#"+menuId).addClass("u_menu_cur");
});
</script>
<div id="u_menu">
	<ul>
		<li><a id="we_about" href="${rootUrl }/note/show.htm?menuId=we_about">关于我们</a></li>
		<li><a id="we_contact" href="${rootUrl }/note/show.htm?menuId=we_contact">联系我们</a></li>
	</ul>
</div>
    