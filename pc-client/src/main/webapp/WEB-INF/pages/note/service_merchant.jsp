<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商家服务</title>
<%@include file="../common/common.jsp" %>

<style type="text/css">
	#note_right { float: left; margin-left: 10px; width: 980px; background: #fff;}
		#nr_tit { font-size: 20px; color: #666; padding: 22px 0px 0px 22px;}
		#nr_content { padding: 22px 42px 22px 42px; color: #666; font-size: 15px;}
			#nc_first { padding: 0px 0px 22px 0px;}
			#nc_two { padding: 0px 0px 22px 0px;}
			#nc_two span { padding: 0px 0px 0px 32px;}
			#nc_three div { padding: 0px 0px 22px 0px;}
			#nc_four div { padding: 0px 0px 0px 32px;}
			#nc_three p { line-height: 26px;}
			#nr_content .title { font-weight: bold; font-size: 16px; line-height: 34px;}
</style>

</head>
<body>
	<%@include file="../common/nav.jsp" %>
	
	<%@include file="../common/category_nav.jsp" %>
	
	<div id="u_info">
		<ul class="clear" id="url_here">
			<li>首页<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li>商家服务</li>
		</ul>
		<div id="u_info_con" class="clear">
			<%@include file="note_menu.jsp" %>
			<div id="note_right">
				<div id="nr_tit">商家服务</div>
				<div id="nr_content">
					<div id="nc_three">
						<div>
							<p class="title" >一、社区菜篮</p>
							<p>手拉手直接与当地绿色蔬菜基地配送供应商进行合作，每日一次配送（下午14:00）为居民提供每天凌晨新鲜采集的无公害健康蔬菜，以满足用户日益增长的“绿色生活”需求。</p>
							<p class="title">二、便民缴费</p>
							<p>手拉手联合多家银行并与当地公用事业收费中心合作，建立了集合“缴费、生活、金融”于一体的综合便民服务平台，最大程度上解决了用户生活缴费不便利、支付网点渠道不丰富、缴费银行卡不通用、缴费时间不灵活、排队等候等问题。</p>
							<p class="title">三、便民家政</p>
							<p>整合优质家政资源，提供标准化作业体系，提高家政人员服务质量水平，建立售后评估制度，为家政服务提供保障。</p>
							<p class="title">四、社区公益</p>
							<p>手拉手倡导“爱公益，爱生活”的公益生活理念，为社区提供免费工具箱、医疗箱，健康小屋免费体检，免费发布政府及物业信息等公益服务。随着业务的不断深入，将与团中央青创基金、希望工程、红十字会等公益机构合作开展更多公益活动。 </p>
							<p class="title">五、商家合作</p>
							<p>供应商合作：</p>
							<p>电话：010-84770722-829</p>
							<p>邮箱：ghb@slscn.com</p>
							<p>闪购合作：</p>
							<p>电话：010-84770722-829</p>
							<p>邮箱：ghb@slscn.com</p>
							<p>本地服务合作：</p>
							<p>电话：010-84770722-829</p>
							<p>邮箱：ghb@slscn.com</p>
						</div>	
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
