<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的钱包</title>
<%@include file="../common/common.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/account/my_wallet.css' type='text/css' />

<script type="text/javascript">
	$(function() {
		var _fm = $("#fm_myWallet"),
			_tp = _fm.find("#type"),
			_cp = _fm.find("#page"),
			_tp_all = $("#tp_all"),
			_tp_exp = $("#tp_exp"),
			_tp_rec = $("#tp_rec");
		_tp_all.click(function() {//全部点击
			_tp.val("");
			_cp.val(1);
			_fm.submit();
		});
		_tp_exp.click(function() {//支出点击
			_tp.val(2);
			_cp.val(1);
			_fm.submit();
		});
		_tp_rec.click(function() {//支付点击
			_tp.val(1);
			_cp.val(1);
			_fm.submit();
		});
		//状态选中
		if (_tp.val() == "" || _tp.val() == null) {
			_tp_all.addClass("mwt_a_cur");
		} else if (_tp.val() == "1") {
			_tp_rec.addClass("mwt_a_cur");
		} else if (_tp.val() == "2") {
			_tp_exp.addClass("mwt_a_cur");
		}
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
			<li class="uh_cur">我的钱包</li>
		</ul>
		<div id="u_info_con" class="clear">
			<%@include file="../common/menu.jsp" %>
			<div id="my_wlt">
				<div id="my_wlt_txt">我的钱包</div>
				<ul id="mwt_ul" class="clear">
					<li>可用余额：<span id="mu_price">￥${amount }</span></li>
					<li>账户状态：<span id="mu_state"><c:if test="${accountType==1 }">有效</c:if><c:if test="${accountType==2 }">冻结</c:if></span></li>
					<li><a href="${rootUrl }/account/toRecharge.htm" id="mu_btn">资金充值</a></li>
				</ul>
				
				<ul class="clear" id="mwt_state">
					<li><a id="tp_all" href="javascript:;">全部</a></li>
					<li><a id="tp_exp" href="javascript:;">支出</a></li>
					<li><a id="tp_rec" href="javascript:;">充值</a></li>
				</ul>
				
				<div id="mwt_ul_tb">
					<ul id="mwt_ul_th" class="clear">
						<li class="mwt_time mwtt_txt">时间</li>
						<li class="mwt_je">金额</li>
						<li class="mwt_bz mwtb_txt">名称|备注</li>
					</ul>
					
					<ul>
						<c:if test="${fn:length(amountLogList) > 0}">
							<c:forEach var="item" items="${amountLogList }">
								<li>
									<ul id="mwt_ul_tr" class="clear">
										<li class="mwt_time mwt_trt"><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></li>
										<c:if test='${item.TYPE == 1 }'>
											<li class="mwt_je mwt_trj1">+${item.money }</li>
										</c:if>
										<c:if test='${item.TYPE == 2 }'>
											<li class="mwt_je mwt_trj2">-${item.money }</li>
										</c:if>
										<li class="mwt_bz mwt_trb">${item.remark }</li>
									</ul>
								</li>
							</c:forEach>
						</c:if>
						<c:if test="${fn:length(amountLogList) == 0}">
							<jsp:include page="../common/null_list_hint.jsp">
								<jsp:param name="content" value="钱包列表" />
							</jsp:include>
						</c:if>
						<!-- 
						<li>
							<ul id="mwt_ul_tr" class="clear">
								<li class="mwt_time mwt_trt">2014-1-33 00:00:00</li>
								<li class="mwt_je mwt_trj1">+200</li>
								<li class="mwt_bz mwt_trb">充值 - 业务交易号：D201501058697xx</li>
							</ul>
						</li>
						 -->
					</ul>
					
					<p id="mwt_p">提示：系统仅显示一年内的月明细，更早的余额明细不再显示。</p>
					
					<!-- 自定义分页组件 -->
					<form id="fm_myWallet" action="${rootUrl }/account/toMyWallet.htm" method="get">
						<input type="hidden" id="type" name="type" value="${type }" />
						<input type="hidden" id="page" name="page" value="${page }" />
						<input type="hidden" id="total_page" value="${total_page }" />
					</form>
					<jsp:include page="../component/page_nav.jsp">
						<jsp:param name="fm_id" value="fm_myWallet" />
						<jsp:param name="is_visible" value="true" />
					</jsp:include>
					<!-- 自定义分页组件 -->
				</div>
			</div>
		</div>
	</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
