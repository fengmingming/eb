<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的优惠劵</title>
<%@include file="../common/common.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/coupon/my_coupon.css' type='text/css' />
</head>
<body>
	<%@include file="../common/nav.jsp" %>
	
	<%@include file="../common/category_nav.jsp" %>
	
	<div id="u_info">
		<ul class="clear" id="url_here">
			<li>首页<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li>我的手拉手<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li class="uh_cur">我的优惠券</li>
		</ul>
		<div id="u_info_con" class="clear">
			<%@include file="../common/menu.jsp" %>
			<div id="mc_div">
				<%@include file="get_coupon.jsp" %>
				<ul id="mc_div_ul" class="clear">
					<li><a  href="${rootUrl }/coupon/unUseCoupon.htm" id="mc_div_unuse">未使用</a></li>
					<li><a href="${rootUrl }/coupon/useCoupon.htm" id="mc_div_use">已使用</a></li>
					<li><a class="cur_a" href="javascript:;" id="mc_div_expire">已过期</a></li>
				</ul>
				<div id="mc_ygq_th_div" >
					<ul class="clear" id="mc_ygq_th">
						<li class="mc_ygq_yhq">优惠券</li>
						<li class="mc_ygq_mz">面值</li>
						<li class="mc_ygq_xfje">所需消费金额</li>
						<li class="mc_ygq_syjz">使用限制</li>
					</ul>
					<ul>
						<c:if test="${!empty expireCouponList }">
						<c:forEach var="item" items="${expireCouponList }">
							<li>
								<ul class="clear mc_ygq_td">
									<li class="mc_ygq_yhq">${item.name }</li>
									<li class="mc_ygq_mz">￥${item.parValue }</li>
									<li class="mc_ygq_xfje">￥${item.minimum }</li>
									<li class="mc_ygq_syjz">${item.limitCat }</li>
								</ul>
							</li>
						</c:forEach>
						</c:if>	
						<c:if test="${empty expireCouponList }">
						<div id="no_item_list">
								<div id="nil_cn" class="clear">
									<span id="nil_cn_img"></span>
									<p>
										<span>抱歉，暂时没有已过期的“</span>
										<span id="nil_cn_txt">优惠劵</span>
										<span>”</span>
									</p>
								</div>
							</div>
						</c:if>
					</ul>
				</div>
				<!-- 自定义分页组件 -->
				<form id="fm_expirecoupon" action="${rootUrl }/coupon/expireCoupon.htm" method="get">
					<input type="hidden" id="page" name="page" value="${page }" />
					<input type="hidden" id="total_page" value="${total_page }" />
				</form>
				<jsp:include page="../component/page_nav.jsp">
					<jsp:param name="fm_id" value="fm_expirecoupon" />
					<jsp:param name="is_visible" value="true" />
				</jsp:include>
				<!-- 自定义分页组件 -->
			</div>
		</div>
	</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
