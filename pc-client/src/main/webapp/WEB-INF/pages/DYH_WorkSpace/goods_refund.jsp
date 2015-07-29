<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>退换货</title>
<%@include file="../common/common.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/DYH_WorkSpace/goods_refund.css' type='text/css' />
<script type="text/javascript">
	$(function() {
		function bindGidv_dClick() {
			$(".gidv_d").unbind("click").click(function() {
				$(this).parent().remove();
			});
		}
		bindGidv_dClick();
	});
</script>
</head>
<body>
	<%@include file="../common/nav.jsp" %>
	
	<%@include file="../common/category_nav.jsp" %>
	
	<div id="u_info">
		<ul class="clear" id="url_here">
			<li>首页<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li>我的手拉手<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li class="uh_cur">退换货</li>
		</ul>
		<div id="u_info_con" class="clear">
			<%@include file="../common/menu.jsp" %>
			<div id="gr_div">
				<div class="gr_d_tit1">
					<span>订单号：</span>
					<span id="gdt_num">ZG15070211432083036459</span>
					<span id="gdt_state">处理中</span>
				</div>
				<div class="gr_d_tit2">
					<span class="gdt_img">商品图片</span>
					<span class="gdt_nam">商品名称</span>
				</div>
				<div class="gr_d_tit3">
					<img id="gt3_img" src="" />
					<span id="gt3_name">山东大鸭梨350g</span>
				</div>
				<div class="gr_d_tit4">
					<ul class="clear gt4_ul1">
						<li>服务类型：</li>
						<li style="padding-left: 20px;">
							<input type="radio" name="servetype" id="retreat" />
							<label for="retreat">退货</label>
						</li>
						<li style="padding-left: 40px;">
							<input type="radio" name="servetype" id="exchange" />
							<label for="exchange">换货</label>	
						</li>
					</ul>
					<ul class="clear gt4_ul2">
						<li>问题描述：</li>
						<li>
							<textarea></textarea>
						</li>
					</ul>
					<ul class="clear gt4_ul3">
						<li>上传图片：</li>
						<li id="g43_imgs">
							<a href="javascript:;" id="s_file">选取文件...</a><input id="s_inp_file" type="file" /><br />
							<span style="color: #666; font-size: 12px; display: block; padding: 5px 0px 0px 6px;">(最多上传3张)</span>
							<div id="g43_imgs_div">
								<div class="gidv">
									<img src="" />
									<a href="javascript:;" class="gidv_d"></a>
								</div>
								<div class="gidv">
									<img src="" />
									<a href="javascript:;" class="gidv_d"></a>
								</div>
								<div class="gidv">
									<img src="" />
									<a href="javascript:;" class="gidv_d"></a>
								</div>
							</div>
						</li>
					</ul>
					<ul class="clear gt4_ul4">
						<li>退款方式：</li>
						<li style="padding-left: 20px;">
							<input type="radio" name="tkfs" id="tzye" />
							<label for="tzye">退至余额(推荐)</label>	
						</li>
						<li style="padding-left: 30px;">
							<input type="radio" name="tkfs" id="tzfkzh" />
							<label for="tzfkzh">退至付款账户</label>	
						</li>
					</ul>
					<div style="border-bottom: 1px solid #dcdcdc; margin-top: 42px;"></div>
					<ul class="gt4_ul5">
						<li>
							<table><%@include file="../common/booth_area_d.jsp" %></table>
						</li>
						<li><input type="text" placeholder="详细地址" id="g45_detail_d" /></li>
					</ul>
					<ul class="clear gt4_ul6">
						<li style="padding: 6px 0px; margin-right: 22px;">客户姓名：</li>
						<li><input type="text" /></li>
					</ul>
					<ul class="clear gt4_ul7">
						<li style="padding: 6px 0px; margin-right: 22px;">手机号码：</li>
						<li><input type="text" /></li>
					</ul>
					<div class="shdfwt_cx">
						<input type="checkbox" id="shdfwt" /><label for="shdfwt">送货到服务亭</label>
					</div>
					<div style="border-bottom: 1px solid #dcdcdc; margin-top: 24px;"></div>
					<ul class="gt4_ul5">
						<li>
							<table><%@include file="../common/booth_area.jsp" %></table>
						</li>
						<li><input type="text" placeholder="详细地址" id="g45_detail" /></li>
					</ul>
					<ul class="clear gt4_ul6">
						<li style="padding: 6px 0px; margin-right: 22px;">客户姓名：</li>
						<li><input type="text" /></li>
					</ul>
					<ul class="clear gt4_ul7">
						<li style="padding: 6px 0px; margin-right: 22px;">手机号码：</li>
						<li><input type="text" /></li>
					</ul>
					<div class="dfwtzt_cx">
						<input type="checkbox" id="dfwtzt" /><label for="dfwtzt">到服务亭自提</label>
					</div>
					<div class="gt4_d_op">
						<a class="gt4p_a" href="javascript:;">保存申请</a>
						<a class="gt4p_a" href="javascript:;">取消申请</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
