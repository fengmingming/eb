<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>退换货</title>
<%@include file="../common/common.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/order/goods_refund.css' type='text/css' />
<script type="text/javascript" src="${staUrl }/js/order/goods_refund.js"></script>
</head>
<body>
	<%@include file="../common/nav.jsp" %>
	
	<%@include file="../common/category_nav.jsp" %>
	
	<div id="u_info">
		<ul class="clear" id="url_here">
			<li>首页<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li>订单管理<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li class="uh_cur">退换货</li>
		</ul>
		<div id="u_info_con" class="clear">
			<%@include file="../common/menu.jsp" %>
			<div id="gr_div">
				<div class="gr_d_tit1">
					<span>订单号：</span>
					<span id="gdt_num">${orderInfo.orderNum }</span>
					<input type="hidden" id="orderDetailId" value="${orderInfo.orderDetailId}" >
					<input type="hidden" id="orderId" value="${orderInfo.orderId}" >
					<span id="gdt_state"><c:choose>
                                
                                <c:when test="${orderInfo.isPaid == 1}">未付款</c:when>
                                <c:when test="${orderInfo.status == 4 }">已发货</c:when>
                                <c:when test="${orderInfo.status == 5 }">已完成</c:when>
                                <c:when test="${orderInfo.status == 6 }">退换货中</c:when>
                                <c:when test="${orderInfo.status == 7 }">退换货完成</c:when>
                                <c:otherwise></c:otherwise>
                            </c:choose></span>
				</div>
				<div class="gr_d_tit2">
					<span class="gdt_img">商品图片</span>
					<span class="gdt_nam">商品名称</span>
				</div>
				<div class="gr_d_tit3">
					<img id="gt3_img" src="${imgUrl }/58X58${orderInfo.photoUrl }" />
					<span id="gt3_name">${orderInfo.goodsName }</span>
				</div>
				<div class="gr_d_tit4">
					<ul class="clear gt4_ul1">
						<li>服务类型：</li>
						<li style="padding-left: 20px;">
							<input type="radio" name="servetype" id="retreat" value="1"  <c:if test="${orderInfo.type=='1'}"> checked='checked'</c:if> />
							<label for="retreat">退货</label>
						</li>
						<li style="padding-left: 40px;">
							<input type="radio" name="servetype" id="exchange" value="2" <c:if test="${orderInfo.type=='2'}"> checked='checked'</c:if>/>
							<label for="exchange">换货</label>	
						</li>
					</ul>
					<ul class="clear gt4_ul2">
						<li>问题描述：</li>
						<li>
							<textarea id="remark">${orderInfo.remark}</textarea>
						</li>
					</ul>
					<ul class="clear gt4_ul3">
						<li>上传图片：</li>
						<li id="g43_imgs">
							<a href="javascript:;" id="s_file">选取文件...</a><!-- <input id="s_inp_file" type="file" /><br /> -->
							<span style="color: #666; font-size: 12px; display: block; padding: 5px 0px 0px 6px;">(最多上传3张)</span>
							<div id="g43_imgs_div">
								<div class="gidv">
									<div class="gidvIg">
										<img id="photoUrl1" src="${imgUrl }${orderInfo.photoUrl1 }" />
									</div>
									<input type="hidden" id="photoUrl1_h" value="${orderInfo.photoUrl1 }">
									<a href="javascript:;" class="gidv_d photoUrl1_h_del"></a>
								</div>
								<div class="gidv">
									<div class="gidvIg">
										<img id="photoUrl2" src="${imgUrl }${orderInfo.photoUrl2 }" />
									</div>
									<input type="hidden" id="photoUrl2_h" value="${orderInfo.photoUrl2 }">
									<a href="javascript:;" class="gidv_d photoUrl2_h_del"></a>
								</div>
								<div class="gidv">
									<div class="gidvIg">
										<img id="photoUrl3" src="${imgUrl }${orderInfo.photoUrl3 }" />
									</div>
									<input type="hidden" id="photoUrl3_h" value="${orderInfo.photoUrl3 }">
									<a href="javascript:;" class="gidv_d photoUrl3_h_del"></a>
								</div>
							</div>
						</li>
					</ul>
					<ul class="clear gt4_ul4">
						<li>退款方式：</li>
						<li style="padding-left: 20px;">
							<input type="radio" name="tkfs" id="tzye" value="1" <c:if test="${orderInfo.moneyWay=='1' }"> checked='checked'</c:if>/>
							<label for="tzye">退至余额(推荐)</label>	
						</li>
						<li style="padding-left: 30px;" id="tzfkzh_li">
							<input type="radio" name="tkfs" id="tzfkzh" value="2" <c:if test="${orderInfo.moneyWay=='2' }"> checked='checked'</c:if>/>
							<label for="tzfkzh">退至付款账户</label>	
						</li>
					</ul>
					<div style="border-bottom: 1px solid #dcdcdc; margin-top: 42px;"></div>
					<ul class="gt4_ul5">
						<li>
							<table>
								<jsp:include page="../common/booth_area_d.jsp">
									<jsp:param name="flag_d" value="false" />
								</jsp:include>
							</table>
							<!--
							<input type="hidden" id="daprovince"  value="2">
							<input type="hidden" id="dacity" value="52">
							<input type="hidden" id="dadistrict" value="503">
							<input type="hidden" id="dacommunity" value="3481">
							<input type="hidden" id="dapavilionId" value="3482">
							<input type="hidden" id="dapavilionCode" value="111100103001001">
							 -->
							 <input type="hidden" id="id" value="${orderInfo.id }">
							 <input type="hidden" id="payCode" value="${orderInfo.payCode }">
							<input type="hidden" id="daprovince"  value="${orderInfo.province}">
							<input type="hidden" id="dacity" value="${orderInfo.city}">
							<input type="hidden" id="dadistrict" value="${orderInfo.district}">
							<input type="hidden" id="dacommunity" value="${orderInfo.community}">
							<input type="hidden" id="dapavilionId" value="${orderInfo.pavilionId}">
							<input type="hidden" id="dapavilionCode" value="${orderInfo.pavilionCode}">
							
							<input type="hidden" id="daprovinceT"  value="${orderInfo.provinceIdT}">
							<input type="hidden" id="dacityT" value="${orderInfo.cityIdT}">
							<input type="hidden" id="dadistrictT" value="${orderInfo.districtIdT}">
							<input type="hidden" id="dacommunityT" value="${orderInfo.communityIdT}">
							<input type="hidden" id="dapavilionIdT" value="${orderInfo.pavilionIdT}">
							<input type="hidden" id="dapavilionCodeT" value="${orderInfo.pavilionCodeT}">
							
							<input type="hidden" id="daprovinceF"  value="${orderInfo.provinceIdF}">
							<input type="hidden" id="dacityF" value="${orderInfo.cityIdF}">
							<input type="hidden" id="dadistrictF" value="${orderInfo.districtIdF}">
							<input type="hidden" id="dacommunityF" value="${orderInfo.communityIdF}">
							<input type="hidden" id="dapavilionIdF" value="${orderInfo.pavilionIdF}">
							<input type="hidden" id="dapavilionCodeF" value="${orderInfo.pavilionCodeF}">
							 
						</li>
						<li>
							<c:if test="${empty orderInfo.id}">
							<input type="text" placeholder="详细地址" id="g45_detail_d" value="${orderInfo.address }"/>
							</c:if>
							<c:if test="${!empty orderInfo.id}">
							<input type="text" placeholder="详细地址" id="g45_detail_d" value="${orderInfo.remarkT }"/>
							</c:if>
						</li>
					</ul>
					<ul class="clear gt4_ul6">
						<li style="padding: 6px 0px; margin-right: 22px;">客户姓名：</li>
						<li>
						<c:choose>
                                <c:when test="${ empty orderInfo.id}">
								<input type="text" id="receiverT" value="${orderInfo.receiver} "/>
								</c:when>
								<c:otherwise><input type="text" id="receiverT" value="${orderInfo.receiverT} "/></c:otherwise>
								</c:choose>
						</li>
					</ul>
					<ul class="clear gt4_ul7">
						<li style="padding: 6px 0px; margin-right: 22px;">手机号码：</li>
						<li>
							<c:choose>
                                <c:when test="${empty orderInfo.id}">
								<input type="text" id="mobileT" value="${orderInfo.mobile} "/>
								</c:when>
								<c:otherwise><input type="text" id="mobileT" value="${orderInfo.mobileT} "/></c:otherwise>
								</c:choose>
						
						</li>
					</ul>
					<div class="shdfwt_cx">
						<input type="checkbox" id="shdfwt" name="shdfwt" value="1" <c:if test="${orderInfo.pickupWay=='1'}"> checked='checked'</c:if>/><label for="shdfwt">送货到服务亭</label>
					</div>
					<div style="border-bottom: 1px solid #dcdcdc; margin-top: 24px;"></div>
					<ul class="gt4_ul5">
						<li>
							<table>
								<jsp:include page="../common/booth_area.jsp">
									<jsp:param name="flag" value="false" />
								</jsp:include>
							</table>
						</li>
						<li>
							<c:if test="${empty orderInfo.id }">
							<input type="text" placeholder="详细地址" id="g45_detail" value="${orderInfo.address }"/>
							</c:if>
							<c:if test="${!empty orderInfo.id}">
							<input type="text" placeholder="详细地址" id="g45_detail" value="${orderInfo.remarkF }"/>
							</c:if>
						<!-- <input type="hidden" id="g45_detail_den" value="${orderInfo.remark }"/> -->
						</li>
						
					</ul>
					<ul class="clear gt4_ul6">
						<li style="padding: 6px 0px; margin-right: 22px;">客户姓名：</li>
						<li>
							<c:choose>
                                <c:when test="${empty orderInfo.id}">
								<input type="text" id="receiverF" value="${orderInfo.receiver} "/>
								</c:when>
								<c:otherwise><input type="text" id="receiverF" value="${orderInfo.receiverF} "/></c:otherwise>
							</c:choose>	
						</li>
					</ul>
					<ul class="clear gt4_ul7">
						<li style="padding: 6px 0px; margin-right: 22px;">手机号码：</li>
						<li>
						<c:choose>
                                <c:when test="${empty orderInfo.id}">
								<input type="text" id="mobileF" value="${orderInfo.mobile} "/>
								</c:when>
								<c:otherwise><input type="text" id="mobileF" value="${orderInfo.mobileF} "/></c:otherwise>
								</c:choose>	
						</li>
					</ul>
					<div class="dfwtzt_cx">
						<input type="checkbox" id="dfwtzt" value="1" <c:if test="${orderInfo.deliveryType=='1'}"> checked='checked'</c:if>/><label for="dfwtzt">到服务亭自提</label>
					</div>
					<div class="gt4_d_op">
					<c:choose>
						<c:when test="${empty orderInfo.state && orderInfo.status >=4}"><a class="gt4p_a save"  href="javascript:;">保存申请</a></c:when>
						<c:when test="${ orderInfo.state == 1}"><a class="gt4p_a save"  href="javascript:;">保存申请</a><a class="gt4p_a cancel" href="javascript:;">取消申请</a></c:when>
						<c:when test="${ orderInfo.state == 2}"></c:when>
						<c:when test="${ orderInfo.state == 127}"><a class="gt4p_a save"  href="javascript:;">保存申请</a></c:when>
					</c:choose>	
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 上传组件 -->
	<%@include file="../common/CFUpdate.jsp" %>
 
</body>
<%@include file="../common/foot.jsp" %>

<script type="text/javascript" src="${staUrl }/js/order/alert_img.js"></script>
<%@include file="../common/zoom.jsp" %>

</html>
