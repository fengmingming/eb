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
	#note_right { float: left; margin-left: 10px; width: 980px; background: #fff;}
		#nr_tit { font-size: 20px; color: #666; padding: 22px 0px 22px 22px;}
		#nr_content { color: #666; padding: 0px 0px 22px 0px;}
			#nc_one { padding: 0px 0px 25px 20px; width: 940px; font-size: 15px;}
				#no_tab tr th{ border-top:1px solid #000;}
				#no_tab tr td{ line-height: 30px; border-bottom:1px solid #ccc; padding: 10px;}
			#nc_two { padding: 0px 0px 25px 20px; width: 940px; font-size: 15px;}
				#nt_tab tr td{ border-bottom:1px solid #ccc; padding: 10px;}
				.nc_two_line_td { line-height: 30px;}
			.ct_td { vertical-align:top; text-align: center;}	
			.t_td { text-align: center;}	
			#nc_three { padding: 0px 42px;}
				#nc_three div{ padding: 0px 0px 25px 0px;}
				#nc_three p { line-height: 28px; font-size: 15px;}
				#nc_three .nc_tre_tit { font-weight: bold; line-height: 32px; font-size: 16px;}
</style>

</head>
<body>
	<%@include file="../common/nav.jsp" %>	
	<%@include file="../common/category_nav.jsp" %>	
	<div id="u_info">
		<ul class="clear" id="url_here">
			<li>首页<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li>售后服务</li>
		</ul>
		<div id="u_info_con" class="clear">
			<%@include file="note_menu.jsp" %>
			<div id="note_right">
				<div id="nr_tit">售后服务</div>	
				<div id="nr_content">
					<div id="nc_one">
						<table id="no_tab" cellpadding="0" cellspacing="0">
							<caption style="padding: 10px 0px; font-weight: bold; font-size: 16px;">售后特色服务</caption> 
							<tr>
								<td style="width: 200px; text-align: center; font-size: 14px; border: 0px; height: 35px; background: #f5f5f5; border-top: solid 1px #ccc; padding: 0px;">服务名称</td>
								<td style="width: 740px; text-align: center; font-size: 14px; border: 0px; height: 35px; background: #f5f5f5; border-top: solid 1px #ccc; padding: 0px;">具体|描述</td>
							</tr>
							<tr>
								<td class="ct_td">7天无理由退货</td>
								<td>客户购买手拉手商品7日内（含7日，自客户收到商品之日起计算），在保证商品完好的前提下，可无理由退货。（部分商品除外，详情请见各商品细则）</td>
							</tr>
							<tr>
								<td class="ct_td">售后上门取件</td>
								<td>客户购买手拉手商品30日内（含30日，自客户收到商品之日起计算）因质量问题提交退换申请且审核通过，手拉手提供免费上门取件服务。法定节假日、停电、天气等不可抗力情况除外。</td>
							</tr>
							<tr>
								<td class="ct_td">一小时解决售后</td>
								<td>客户购买手拉手商品如出现故障（以国家三包法等有关法律、法规为准），客户有两种处理方式：<br />
									<p style="padding-top: 12px;">1、电话通知服务亭人员上门处理售后，服务亭人员接到客户售后电话后，30分钟内上门，一小时内处理完成售后问题；处理完的标志为已经为客户提交了换新订单、补发订单、补偿申请或者退款申请（通过网站退款要依赖于第三方退款平台服务速度）。</p>
									<p style="padding-top: 12px;">2、客户带商品到小区内的手拉手服务亭，服务亭人员接到客户的售后问题后，一般30分钟处理完（最长不超过一小时）售后问题；处理完的标志为已经为客户提交了退款申请（通过网站退款要依赖于第三方退款平台服务速度）。注：如客户不同意以上解决方案，协商时间另计。</p>
								</td>
							</tr>
							<tr>
								<td class="ct_td">5分钟退款</td>
								<td>手拉手售后退款有两种方式（最快5分钟完成）：<br />
									1、 现金退款：手拉手服务亭人员确认可以退货后，可以当场退现金。<br />
									2、 账户退款：客户可以选择网上退货，经工作人员审核确认后，将相应款项退回付款账户；这个周期需要5天时间。
								</td>
							</tr>			
						</table>
					</div>
					<div id="nc_two">
						<table id="nt_tab" cellpadding="0" cellspacing="0">
							<caption style="padding-bottom: 10px; font-weight: bold; font-size: 16px;">售后服务总则(手拉手承诺符合以下情况，自客户收到商品之日起7日内可以退货。具体退货标准如下：)</caption>
							<tr style="height: 35px; text-align: center; background: #f5f5f5; font-size: 14px;">
								<td style="width: 174px;">退换类别</td>
								<td style="width: 168px;">具体描述</td>
								<td style="width: 168px;">是否支持7天（含）内退货</td>
								<td style="width: 168px;">是否收取返回运费</td>
								<td >备注</td>
							</tr>
							<tr>
								<td class="ct_td nc_two_line_td">国家法律所规定的功能性故障或商品质量问题</td>
								<td class="nc_two_line_td">经由生产厂家指定或特约售后服务中心检测确认，并出具检测报告或经手拉手售后确认属于商品质量问题。</td>
								<td class="t_td nc_two_line_td">是</td>
								<td class="t_td nc_two_line_td">否</td>
								<td class="nc_two_line_td">当地无检测条件的请联系手拉手售后处理。</td>
							</tr>
							<tr>
								<td class="ct_td nc_two_line_td">到货物流损、缺件或商品描述与网站不符等手拉手原因</td>
								<td class="nc_two_line_td">物流损指在运输过程中造成的损坏、漏液、破碎、性能故障，经售后人员核查情况属实。缺件指商品原装配件缺失。</td>
								<td class="t_td nc_two_line_td">是</td>
								<td class="t_td nc_two_line_td">否</td>
								<td class="nc_two_line_td">手拉手审核期间可能需要服务亭人员证明或要求您提供实物照片等，以便售后人员快速做出判断并及时处理。</td>
							</tr>
							<tr>
								<td class="ct_td nc_two_line_td">其他原因</td>
								<td class="nc_two_line_td">除以上两种原因之外，如个人原因导致的退换货，在商品完好的前提下。</td>
								<td class="t_td nc_two_line_td">是</td>
								<td class="t_td nc_two_line_td">是</td>
								<td class="nc_two_line_td">由您承担商品返回手拉手的运费。</td>
							</tr>
						</table>
					</div>
					<div id="nc_three">
						<div>
							<p class="nc_tre_tit">1.判断实际收货日期规则：</p>
							<p>1) 手拉手配送或者自提的订单：以客户实际签收日期为准； </p>
							<p>2) 非手拉手配送的订单，按照第三方物流平台显示的实际到货日期为准。如果第三方合作伙伴不能有效返回签收日期，则手拉手客服根据距离等因素和客户人工确认实际到货日期。</p>
						</div>
						<div>
							<p class="nc_tre_tit">2. 在商品无任何问题情况下，手拉手承诺：自您实际收到商品之日起7日内，在商品返回运费由您承担的情况下,可享受无理由退货。手拉手所售均为全新品，为保护消费者利益，以下商品不适用于7天无理由退货：</p>											
							<p>1) 个人定作类商品；</p>
							<p>2) 鲜活易腐类商品；</p>
							<p>3) 在线下载或者您拆封的音像制品，计算机软件等数字化商品；</p>
							<p>4) 交付的报纸期刊类商品；</p>
							<p>5) 其他根据商品性质不适宜退货，经您在购买时确认不宜退货的商品。</p>
						</div>
						<div>
							<p class="nc_tre_tit">3.特别说明，以下情况不予办理退换货：</p>
							<p>1)任何非手拉手出售的商品（序列号不符）；</p>
							<p>2)过保商品（超过三包保修期的商品）；</p>
							<p>3)未经授权的维修、误用、碰撞、疏忽、滥用、进液、事故、改动、不正确的安装所造成的商品质量问题，或撕毁、涂改标贴、机器序号、防伪标记；</p>
							<p>4)无法提供商品的发票、保修卡等三包凭证或者三包凭证信息与商品不符及被涂改的；</p>
							<p>5)其他依法不应办理退换货的。</p>
							<p>温馨提示：在商品退货时，需扣除购买该商品时通过评价或晒单所获得的积分及相应优惠，如账户积分已使用，则从商品退款金额中相应扣除</p>
						</div>
						<div>
							<p class="nc_tre_tit">4. 7日×12小时工作制手拉手售后审核人员服务工作时间是：每周一至周日，9：00至19：00。</p>
						</div>
						<div>						
							<p class="nc_tre_tit">5. 热线咨询服务在产品保修期内，如果您有售后问题需要咨询，欢迎您拨打我们的24小时客服热线：400-0365-020。如果您所在地区不支持400电话，请您打010—84770922。</p>
						</div>
					</div>
				</div>			
			</div>		
		</div>	
	</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
