<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>意见反馈</title>
<%@include file="../common/common.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/user/feedback.css' type='text/css' />
</head>
<body>
	<%@include file="../common/nav.jsp" %>
	
	<%@include file="../common/category_nav.jsp" %>
	
	<div id="u_info">
		<ul class="clear" id="url_here">
			<li>首页<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li>我的手拉手<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li class="uh_cur">意见反馈</li>
		</ul>
		<div id="u_info_con" class="clear">
			<%@include file="../common/menu.jsp" %>
			<div id="fb_div">
				<div id="fb_div_tit">意见反馈</div>
				<form action="" method="post">
					<table id="fb_tb">
						<tr>
							<td class="fb_td">留言类型：</td>
							<td>
								<input class="lt_inp" type="radio" name="leave_type" id="lt_1" /><label class="lt_lb" for="lt_1">留言</label>
								<input class="lt_inp" type="radio" name="leave_type" id="lt_2" /><label class="lt_lb" for="lt_2">投诉</label>
								<input class="lt_inp" type="radio" name="leave_type" id="lt_3" /><label class="lt_lb" for="lt_3">询问</label>
								<input class="lt_inp" type="radio" name="leave_type" id="lt_4" /><label class="lt_lb" for="lt_4">售后</label>
								<input class="lt_inp" type="radio" name="leave_type" id="lt_5" /><label class="lt_lb" for="lt_5">求购</label>
							</td>
						</tr>
						<tr>
							<td class="fb_td">主题：</td>
							<td><input type="text" id="ft_zt" /></td>
						</tr>
						<tr>
							<td class="fb_td">留言内容：</td>
							<td><textarea id="ft_nr"></textarea></td>
						</tr>
						<tr>
							<td class="fb_td">上传文件：</td>
							<td><a id="fb_upfile" href="javascript:;">选取文件...</a></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td id="fb_sub_td"><a id="fb_sub" href="javascript:;">提交申请</a></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
