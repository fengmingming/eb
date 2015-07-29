<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>物流配送</title>
<%@include file="../common/common.jsp" %>

<style type="text/css">
	#note_right { float: left; margin-left: 10px; width: 980px; background: #fff;}
		#nr_tit { font-size: 20px; color: #666; padding: 22px 0px 0px 22px;}
		#nr_content { padding: 22px 0px 30px 38px; color: #666; font-size: 15px;}
			#nc_first { padding: 0px 0px 40px 0px;}
				#nc_first p { font-weight: bold; padding-bottom: 12px;}
			#nc_two { padding: 0px 0px 40px 0px;}
				#nc_two p { font-weight: bold; padding-bottom: 12px;}
				#nc_two span { padding: 0px 0px 0px 32px;}
				#nc_three #nc_te_tit { font-weight: bold;}
				#nc_three .nc_tre_tit { font-weight: bold; padding: 12px 0px;}
				#nc_three .nc_tre_con { line-height: 28px;}
				#nc_three div { padding: 0px 50px 22px 30px;}
			#nc_four div { padding: 0px 50px 0px 32px;}
				#nc_four #nc_fr_tit { font-weight: bold; padding: 20px 0px 10px 0px;}
				#nc_four div p { line-height: 28px;}
</style>

</head>
<body>
	<%@include file="../common/nav.jsp" %>
	
	<%@include file="../common/category_nav.jsp" %>
	
	<div id="u_info">
		<ul class="clear" id="url_here">
			<li>首页<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li>物流配送</li>
		</ul>
		<div id="u_info_con" class="clear">
			<%@include file="note_menu.jsp" %>
			<div id="note_right">
				<div id="nr_tit">物流配送</div>
				<div id="nr_content">
					<div id="nc_first">
						<p>尊敬的会员：</p>
						<span>感谢您选择我们的服务，祝您购物愉快！</span>
					</div>
					<div id="nc_two">
						<p>一、服务时间：（不可抗力除外）</p>
						<span>1、全年365天&nbsp;&nbsp;&nbsp;&nbsp;2、每天09:00 - 19:00</span>
					</div>
					<div id="nc_three">
						<p id="nc_te_tit">二、收货方式：</p>
						<div>
							<p class="nc_tre_tit">自提点自提</p>
							<p style="padding-bottom: 6px;">自提流程及注意事项</p>
							<p class="nc_tre_con">1、自提时间：周一至周日，09:00 - 19:00（如遇国家法定节假日，则以网站或自提点公告为准，请您及时关注）；</p>
							<p class="nc_tre_con">2、商品到达自提点后，我们将为您保留1天（自然日），超过1天（自然日）则视为默认取消订单，视产品保存周期，另约自提时间的除外；</p>
							<p class="nc_tre_con">3、下单时，选择"自提点自提"，订单提交后，请关注订单状态；</p>
							<p class="nc_tre_con">4、请凭借订单号或手机号到相应自提点提货；</p>
							<p class="nc_tre_con">5、到达自助自提点后，工作人员会协助您完成订单产品的确认；</p>
							<p class="nc_tre_con">6、验证无误后，完成订单产品的交付。</p>
						</div>	
						<div>
							<p class="nc_tre_tit">快递送货</p>
							<p class="nc_tre_con">1、配送上门时间：每天上午10:00-12:00和每天下午16:00-19:00；</p>
							<p class="nc_tre_con">2、除上述时间之外，我们不能提供上门服务，请您下单时根据您的时间适当选择收货方式。</p>
						</div>					
					</div>
					<div id="nc_four">
						<p id="nc_fr_tit">三、温馨提示</p>
						<div>
							<p>1、验货内容：包括商品及附属产品的外观、数量、发票（如有）、三包凭证（如有）等;</p>
							<p>2、验货异常：验货后，若发现商品错发、商品少发、商品有表面质量等影响签收因素问题，可当场向送货员或自提点工作人员说明情况并拒签。</p>
							<p>3、当日下午15:00前的订单，次日19：00前可完成自提和配送上门。</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
