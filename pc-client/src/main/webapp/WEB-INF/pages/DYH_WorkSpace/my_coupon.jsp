<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的优惠劵</title>
<%@include file="../common/common.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/DYH_WorkSpace/my_coupon.css' type='text/css' />
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
				<ul id="mc_div_ul" class="clear">
					<li><a class="cur_a" href="javascript:;">未使用</a></li>
					<li><a href="javascript:;">已使用</a></li>
					<li><a href="javascript:;">已过期</a></li>
				</ul>
				
				<!-- “未使用”记录开始 -->
				<ul class="clear" id="mc_wsy_th">
					<li class="mc_wsy_yhq">优惠券</li>
					<li class="mc_wsy_mz">面值</li>
					<li class="mc_wsy_xfje">所需消费金额</li>
					<li class="mc_wsy_syjz">使用限制</li>
					<li class="mc_wsy_yxq">有效期</li>
				</ul>
				<ul>
					<li>
						<ul class="clear mc_wsy_td">
							<li class="mc_wsy_yhq">满100元减10元优惠券</li>
							<li class="mc_wsy_mz">￥30.00</li>
							<li class="mc_wsy_xfje">￥100.00</li>
							<li class="mc_wsy_syjz">全部商品</li>
							<li class="mc_wsy_yxq">2015-05-10&nbsp;至&nbsp;2015-05-25</li>
						</ul>
					</li>
					<li>
						<ul class="clear mc_wsy_td">
							<li class="mc_wsy_yhq">满100元减10元优惠券</li>
							<li class="mc_wsy_mz">￥30.00</li>
							<li class="mc_wsy_xfje">￥100.00</li>
							<li class="mc_wsy_syjz">全部商品</li>
							<li class="mc_wsy_yxq">2015-05-10&nbsp;至&nbsp;2015-05-25</li>
						</ul>
					</li>
					<li>
						<ul class="clear mc_wsy_td">
							<li class="mc_wsy_yhq">满100元减10元优惠券</li>
							<li class="mc_wsy_mz">￥30.00</li>
							<li class="mc_wsy_xfje">￥100.00</li>
							<li class="mc_wsy_syjz">全部商品</li>
							<li class="mc_wsy_yxq">2015-05-10&nbsp;至&nbsp;2015-05-25</li>
						</ul>
					</li>
				</ul>
				<!-- “未使用”记录结束 -->
				
				<!-- “已使用”记录开始 -->
				<ul class="clear" id="mc_ysy_th">
					<li class="mc_ysy_yhq">优惠券</li>
					<li class="mc_ysy_mz">面值</li>
					<li class="mc_ysy_xfje">所需消费金额</li>
					<li class="mc_ysy_syjz">使用限制</li>
					<li class="mc_ysy_sysj">使用时间</li>
					<li class="mc_ysy_ddh">订单号</li>
				</ul>
				<ul>
					<li>
						<ul class="clear mc_ysy_td">
							<li class="mc_ysy_yhq">满100元减10元优惠券</li>
							<li class="mc_ysy_mz">￥30.00</li>
							<li class="mc_ysy_xfje">￥100.00</li>
							<li class="mc_ysy_syjz">全部商品</li>
							<li class="mc_ysy_sysj">2015-05-10</li>
							<li class="mc_ysy_ddh">ZG15051917221559329173</li>
						</ul>
					</li>
					<li>
						<ul class="clear mc_ysy_td">
							<li class="mc_ysy_yhq">满100元减10元优惠券</li>
							<li class="mc_ysy_mz">￥30.00</li>
							<li class="mc_ysy_xfje">￥100.00</li>
							<li class="mc_ysy_syjz">全部商品</li>
							<li class="mc_ysy_sysj">2015-05-10</li>
							<li class="mc_ysy_ddh">ZG15051917221559329173</li>
						</ul>
					</li>
					<li>
						<ul class="clear mc_ysy_td">
							<li class="mc_ysy_yhq">满100元减10元优惠券</li>
							<li class="mc_ysy_mz">￥30.00</li>
							<li class="mc_ysy_xfje">￥100.00</li>
							<li class="mc_ysy_syjz">全部商品</li>
							<li class="mc_ysy_sysj">2015-05-10</li>
							<li class="mc_ysy_ddh">ZG15051917221559329173</li>
						</ul>
					</li>
				</ul>
				<!-- “已使用”记录结束 -->
				
				<!-- “已过期”记录开始 -->
				<ul class="clear" id="mc_ygq_th">
					<li class="mc_ygq_yhq">优惠券</li>
					<li class="mc_ygq_mz">面值</li>
					<li class="mc_ygq_xfje">所需消费金额</li>
					<li class="mc_ygq_syjz">使用限制</li>
				</ul>
				<ul>
					<li>
						<ul class="clear mc_ygq_td">
							<li class="mc_ygq_yhq">满100元减10元优惠券</li>
							<li class="mc_ygq_mz">￥30.00</li>
							<li class="mc_ygq_xfje">￥100.00</li>
							<li class="mc_ygq_syjz">全部商品</li>
						</ul>
					</li>
					<li>
						<ul class="clear mc_ygq_td">
							<li class="mc_ygq_yhq">满100元减10元优惠券</li>
							<li class="mc_ygq_mz">￥30.00</li>
							<li class="mc_ygq_xfje">￥100.00</li>
							<li class="mc_ygq_syjz">全部商品</li>
						</ul>
					</li>
					<li>
						<ul class="clear mc_ygq_td">
							<li class="mc_ygq_yhq">满100元减10元优惠券</li>
							<li class="mc_ygq_mz">￥30.00</li>
							<li class="mc_ygq_xfje">￥100.00</li>
							<li class="mc_ygq_syjz">全部商品</li>
						</ul>
					</li>
				</ul>
				<!-- “已过期”记录结束 -->
				
			</div>
		</div>
	</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
