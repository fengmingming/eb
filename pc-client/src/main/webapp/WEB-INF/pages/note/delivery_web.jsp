<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>服务网络</title>
<%@include file="../common/common.jsp" %>

<style type="text/css">
	#note_right { float: left; margin-left: 10px; width: 980px; background: #fff; height: 480px; overflow: hidden;}
		#nr_tit { font-size: 20px; color: #666; padding: 22px 0px 22px 22px;}
		#nr_tab { padding: 0px 22px;}
		#nr_tab table{ text-align:left; color: #666; width: 100%;}
		#nr_tab table tr th{ height: 36px; font-size: 12px; background: #f5f5f5;}
		#nr_tab table tr td{ height: 30px; border-bottom:1px solid #ccc; height: 40px;}
			.nr_tb_otd { padding-left: 22px;}
</style>

</head>
<body>
	<%@include file="../common/nav.jsp" %>
	
	<%@include file="../common/category_nav.jsp" %>
	
	<div id="u_info">
		<ul class="clear" id="url_here">
			<li>首页<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li>服务网络</li>
		</ul>
		<div id="u_info_con" class="clear">
			<%@include file="note_menu.jsp" %>
			<div id="note_right">
				<div id="nr_tit">服务网络</div>
				<div id="nr_tab">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<th style="width: 180px; padding-left: 22px;">服务中心所在小区</th>
							<th style="width: 600px; ">服务小区</th>
							<th>小区编号</th>
						</tr>
						<tr>
							<td class="nr_tb_otd">1、溪城家园1号院</td>
							<td>溪城家园1号院、嘉铭、珑原小区、清水园小区、佳运园一区、佳运园二区</td>
							<td>010-0001</td>
						</tr>
						<tr>
							<td class="nr_tb_otd">2、溪城家园2号院</td>
							<td>溪城家园2号院、佳运园小区三区、佳运园小区四区、嘉城花园、陈营小区</td>
							<td>010-0002</td>
						</tr>
						<tr>
							<td class="nr_tb_otd">3、江西路四院后东区</td>
							<td>江西路四院后东区</td>
							<td>010-0003</td>
						</tr>
						<tr>
							<td class="nr_tb_otd">4、南昌路中原人家</td>
							<td>南昌路中原人家</td>
							<td>010-0004</td>
						</tr>
						<tr>
							<td class="nr_tb_otd">5、联盟路华侨新村</td>
							<td>联盟路华侨新村</td>
							<td>010-0005</td>
						</tr>
						<tr>
							<td class="nr_tb_otd"></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td class="nr_tb_otd"></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td class="nr_tb_otd"></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td class="nr_tb_otd"></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td class="nr_tb_otd"></td>
							<td></td>
							<td></td>
						</tr>
					</table>				
				</div>
			</div>
		</div>
	</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
