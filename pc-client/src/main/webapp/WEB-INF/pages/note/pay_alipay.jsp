<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支付宝支付</title>
<%@include file="../common/common.jsp" %>

<style type="text/css">
	#note_right { float: left; margin-left: 10px; width: 980px; background: #fff; height: 480px; overflow: hidden;}
		#nr_tit { font-size: 20px; color: #666; padding: 22px 0px 50px 22px;}
			#note_right ul { margin: 70px 0px 0px 150px;}
				#note_right ul li { float: left; font-size: 14px; color: #666;}
					#note_right ul li a { color: #3f82b7;}
					#note_right ul li a:hover { text-decoration: underline;}
			#nr_img { width: 235px; height: 120px; background: url(${staUrl }/images/note/alipay.jpg) no-repeat;}
			#nr_txt { height: 120px; display: table; margin-left: 70px;}
				#nr_txt p { display: table-cell; vertical-align: middle; line-height: 24px;}	
</style>

</head>
<body>
	<%@include file="../common/nav.jsp" %>
	
	<%@include file="../common/category_nav.jsp" %>
	
	<div id="u_info">
		<ul class="clear" id="url_here">
			<li>首页<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li>支付宝支付</li>
		</ul>
		<div id="u_info_con" class="clear">
			<%@include file="note_menu.jsp" %>
			<div id="note_right">
				<div id="nr_tit">支付宝支付</div>
				<ul class="clear">
					<li id="nr_img"></li>
					<li id="nr_txt">
						<p>
							如果您已经拥有支付宝账户，可选择支付宝进行付款。<br />
							如果您还不知道如何使用支付宝。&nbsp;&nbsp;<a href="http://home.alipay.com/individual/tutorial.htm" target="_blank">点击支付宝帮助&gt;&gt;</a>
						</p>
					</li>
				</ul>
			</div>
		</div>
	</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
