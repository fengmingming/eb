<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>核对订单信息</title>
<%@include file="../common/common.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/order/check_order.css' type='text/css' />
</head>
<body>
	<div id="order">
		<div id="ck_order_tit" class="clear">
			<span id="cot_txt">核对订单信息</span>
			<ul id="cart_flow" class="clear">
				<li id="c_f_img"></li>
				<li class="c_f_li">1.我的购物车</li>
				<li class="c_f_li c_f_li_cur">2.填写核对订单信息</li>
				<li class="c_f_li">3.成功提交订单</li>
			</ul>
		</div>
		<ul id="order_info">
			<li id="order_info_tit">订单信息</li>
			<li class="bt_bd">
				<div id="consignee"><span>收货人信息</span></div>
                <div id="addrTo" class="clear"></div>
			</li>
			
			<li id="pay">
				<div id="pay_fs">支付方式：</div>
				<ul>
					<li class="df_li">
						<input id="yezf" name="zffs" value="2"  type="radio" checked="checked"/>
						<label for="yezf">余额支付</label>
						<c:if test="${!empty balanceMsg }"><span style="color:red; margin: 0px 12px 0px 30px;">您的${balanceMsg }</span><a href="${rootUrl }/account/toRecharge.htm">请点击充值&gt;&gt;</a></c:if>
					</li>
					<li class="df_li">
						<input id="zfbzf" name="zffs" value="1"  type="radio" />
						<label for="zfbzf">支付宝支付</label>
					</li>
				</ul>
			</li>
		</ul>
		<!-- 可用优惠券 -->
		<div id="cfo_yhq">
			<a href="javascript:;" class="c_yq_a"><b class="c_yq_log c_yq_log_a"></b>使用优惠券：</a>
			<ul id="cfo_uyq_ul">
				<ul id="cfo_uyq_div" class="clear">
					<li><input id="use_coupon_code"type="button" value="使用优惠券"/></li>
					<li><input type="text" id="coupon_code"/></li>
					<li style="font-size: 14px; color: #666; padding-right: 20px;">请输入优惠码</li>
				</ul>
				<li class="cq_kyyhq"><span>可用优惠券</span></li>
				<c:if test="${!empty shopCart.canUseCouponList }">
				<c:forEach var="userCoupon" items="${shopCart.canUseCouponList }">
					<li>
						<ul class="cq_ul_f">
							<li><input name="coupon" type="radio" class="cq_f_inp" value="${userCoupon.id }"/>
								<input type="hidden" value="${userCoupon.parValue }"></li>
							<li style="width: 235px;">${userCoupon.name }</li>
							<li style="width: 195px;">${userCoupon.limitCatName }</li>
							<li style="width: 175px;"><fmt:formatDate value="${userCoupon.validityEnd }" pattern="yyyy年MM月dd日"/></li>
						</ul>
						<div style="clear: both;"></div>
					</li>
				</c:forEach>
				</c:if>
				<li>
					<ul class="cq_ul_f" style="margin-top: -1px;">
						<li><input name="coupon" type="radio" class="cq_f_inp" value="" checked /></li>
						<li style="width: 605px;">不使用</li>
					</ul>
					<div style="clear: both;"></div>
				</li>
			</ul>
		</div>
		
		<ul id="confirm_order_tit" class="clear">
			<li id="cft_txt">确认订单信息</li>
			<li id="cft_a"><a href="${rootUrl }/carts/myCarts.htm">返回修改购物车</a></li>
		</ul>
		
		<ul id="cf_order_tit" class="clear">
			<li id="cot_tol">小计（元）</li>
			<li id="cot_num">数量</li>
			<li id="cot_price">单价（元）</li>
			<li id="cot_mprice">市场价（元）</li>
		</ul>
		
		<ul id="cf_order_con">
			<c:forEach var="product" items="${shopCart.productList }">
			<li>
				<ul class="cfoc_od_info clear">
					<li class="coi_img">
						<a href="${rootUrl }/goods/getGoodsInfo.htm?id=${product.settleGoods[0].id }" target="_blank">
							<img src="${imgUrl }/200X200${product.settleGoods[0].photoUrl}" width="100" height="100" /></a></li>
					<li class="coi_name"><a href="${rootUrl }/goods/getGoodsInfo.htm?id=${product.settleGoods[0].id }" title='${product.settleGoods[0].goodsName}' class="hover_a" target="_blank">
							${product.settleGoods[0].goodsName}</a></li>
					<li class="coi_mprice">${product.settleGoods[0].marketPrice}</li>
					<li class="coi_price">${product.settleGoods[0].price}</li>
					<li class="coi_num">${product.number}</li>
					<li class="coi_tol">${product.amount}</li>
				</ul>
			</li>
			</c:forEach>
		</ul>
		
		<ul id="pay_password" class="clear">
			<li>支付密码：</li>
			<li id="p_pd_li"><input type="password" name="password" maxlength="16"/></li>
			<li><a href="${rootUrl }/account/modifyPayPwd1.htm" target="_blank">忘记支付密码？</a></li>
			<li style="float: right; margin-right: 32px;" id="discountByCoupon">-￥0.00</li>
			<li style="float: right;">优惠券：</li>
		</ul>
		
		<!-- 提交订单出错的提示信息 -->
		<ul id="cf_order_hint"><li></li></ul>
		
		<ul id="cf_order_ft">
			<li><a id="submit_od" href="javascript:;" onclick="commitOrder()">提交订单</a></li>
			<li id="cfof_total">￥${shopCart.payPrice }</li>
			<li id="cfof_total_tit">合计（不含运费）</li>
			<li id="cfof_slt">已选商品<span>${shopCart.number }</span>件</li>
		</ul>
	</div>
</body>
<script type="text/javascript" src="${imgUrl }/area.js"></script>
<script type="text/javascript">
var total = "${shopCart.payPrice }";
var deliveryType = 1; //送货类型：1自提点自提，2快递送货
var curAddrId = 0; //目前选中的地址id
var editAddrId = 0; //目前修改的地址id
$(function(){
	var zt_receiver = "${user.userName}";
	var zt_mobile = "${user.mobile}"
	var zt_province = "${user.province}";
	var zt_city = "${user.city}";
	var zt_district = "${user.district}";
	var zt_community = "${user.community}";
	var zt_pavilionId = "${user.pavilionId}";
	var zt_pavilionCode = "${user.pavilionCode}";
	if(zt_province != null && zt_province != "" && zt_city != null && zt_city != "" &&
			zt_district != null && zt_district != "" && zt_community != null && zt_community != "" &&
			zt_pavilionId != null && zt_pavilionId != ""){
	    $("#addrTo").html("<div id='ztAddr' class='add_block adbk_cur2' onclick='setDefaultAddr(this)'><span class='at_zt_icon'>自提地址</span><p class='ad_p1'>" + 
        				  "<span class='at_ssq'><label id='add_province' value='"+zt_province+"'>"+ framework.area_map[zt_province]+ "</label>" +
        				  "<label id='add_city' value='"+zt_city+"'>"+ framework.area_map[zt_city]+ "</label></span>" +
        				  "<span>&nbsp;&nbsp;&nbsp;&nbsp;(<span id='add_receiver'>${user.userName}</span>&nbsp;收)</span></p>" +
        				  "<p class='ad_p2'><label id='add_district' value='"+zt_district+"'>"+ framework.area_map[zt_district]+ "</label>" +
        				  "<label id='add_community' value='"+zt_community+"'>"+ framework.area_map[zt_community]+ "</label>" +
        				  "<label id='add_pavilionId' value='"+zt_pavilionId+"'>"+ framework.area_map[zt_pavilionId]+ "</label>" +
        				  "<label id='add_pavilionCode' value='"+zt_pavilionCode+"' style='display:none;'></label>" +
        				  "<label id='add_detail' style='display:none'></label>&nbsp;&nbsp;" +
        				  "<span class='at_pon' id='add_mobile'>"+ zt_mobile +"</span><span id='add_phone' style='display:none'></span></p>" +
        				  "<a class='at_upd' onclick='showUpdtAddr(ztAddr, event);' href='javascript:;'>修改</a>" +
        				  "<div class='at_duigou'></div></div>");
	}else{
		 $("#addrTo").html("<div id='ztAddr' class='add_block adbk_cur2'><span class='at_zt_icon'>自提地址</span>" +
				 		   "<div class='new_adds' onclick='showAddtAddr(ztAddr, event);'></div>" +
				 		   "<div class='at_duigou'></div></div>");
	}

    var sbx = []
    var cpt;
    var html = "";
    <c:forEach var="addr" items="${addresses}">
	    var add_prov = "${addr.province}";
	    var add_city = "${addr.city}";
	    var add_dist = "${addr.district}";
	    var add_comm = "${addr.community}";
	    var add_pavi = "${addr.pavilionId}";
	    var add_paviC = "${addr.pavilionCode}";

	    html += "<div id='${addr.id}' class='add_block adbk_cur2' onclick='setDefaultAddr(this)'><p class='ad_p1'>" + 
				  "<span class='at_ssq'><label id='add_province' value='"+add_prov+"'>"+ framework.area_map[add_prov]+ "</label>" +
				  "<label id='add_city' value='"+add_city+"'>"+ framework.area_map[add_city]+ "</label></span>" +
				  "<span>&nbsp;&nbsp;&nbsp;&nbsp;(<span id='add_receiver'>${addr.receiver}</span>&nbsp;收)</span></p>" +
				  "<p class='ad_p2'><label id='add_district' value='"+add_dist+"'>"+ framework.area_map[add_dist]+ "</label>" +
				  "<label id='add_community' value='"+ add_comm +"' style='display:none;'></label>" +
				  "<label id='add_pavilionId' value='"+ add_pavi +"' style='display:none;'></label>" +//自提点的周边地址要改吗？后台的map文件，还要给我返回一个zbid，需要修改此map文件
				  "<label id='add_pavilionCode' value='"+ add_paviC +"' style='display:none;'></label>" +
				  "<label id='add_detail'>${addr.addressDetail}</label>&nbsp;&nbsp;";
		<c:if test="${!empty addr.mobile}">
		html +=	"<span class='at_pon' id='add_mobile'>${addr.mobile}</span><span id='add_phone' style='display:none'>${addr.phone}</span></p>";
		</c:if>
		<c:if test="${empty addr.mobile}">
		html +=	"<span class='at_pon' id='add_mobile' style='display:none'>${addr.mobile}</span><span id='add_phone'>${addr.phone}</span></p>";
		</c:if>
		html +=   "<a class='at_upd' onclick='showUpdtAddr(${addr.id}, event);' href='javascript:;'>修改</a>" +
				  "<a class='at_del' onclick='deleteAddr(${addr.id}, event);' href='javascript:;'>删除</a>" +
				  "<div class='at_duigou'></div></div>";
    </c:forEach>
    html += "<div class='add_new_block adbk_cur2'><div class='new_adds' onclick='showAddtAddr(-1, event);'></div></div>";
    $("#addrTo").append(html);
    
    //选中默认地址
    if("${dftId}" != null && "${dftId}" != ""){
    	var _dft_div = $("#addrTo div[id='${dftId}']");
    	_dft_div.removeClass("adbk_cur2").addClass("adbk_cur1");
    	_dft_div.find(".at_duigou").addClass("at_show");
    	deliveryType = 2;
    	curAddrId = "${dftId}";
    }else{
    	//var _zt_div = $("#addrTo div[id='ztAddr']");
    	//_zt_div.removeClass("adbk_cur2").addClass("adbk_cur1");
    	//_zt_div.find(".at_duigou").addClass("at_show");
    }
});
</script>
<script type="text/javascript" src="${staUrl }/js/order/check_order.js"></script>
<%@include file="../common/foot.jsp" %>
<div id="upd_address_pop">
	<div id="uap_tit">
		<span id="uap_tit_str"></span>送货地址
		<a id="uap_tit_a" href="javascript:closeAddressPop();"></a>
	</div>
	<table>
		<%@include file="../common/booth_area.jsp" %>
		<tr class="tr_xx_adds">
			<td class="td_fs">
				<span class="r_star">*</span>
				<span>详细地址：</span>
			</td>
			<td class="a_i_td"><textarea id="xx_address" placeholder="建议您如实填写详细收货地址，例如街道名称，门牌号码，楼层和房间号等信息"></textarea></td>
		</tr>
		<tr>
			<td class="td_fs">
				<span class="r_star">*</span>
				<span>收货人：</span>
			</td>
			<td class="a_i_td"><input id="rcvr" type="text" class="uap_inp" maxlength="5"/></td>
		</tr>
		<tr>
			<td class="td_fs">手机号码：</td>
			<td class="a_i_td"><input id="mobi" type="text" class="uap_inp" maxlength="11"/></td>
		</tr>
		<tr>
			<td class="td_fs">电话号码：</td>
			<td class="a_i_td"><input id="phone" type="text" class="uap_inp" maxlength="11"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<input type="checkbox" id="set_def_addr" />
				<label for="set_def_addr">设置为默认收货地址</label>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<a href="javascript:;" id="sav_add" onclick="updtAddr();">保存收货地址</a>
			</td>
		</tr>
	</table>
</div>
</html>
