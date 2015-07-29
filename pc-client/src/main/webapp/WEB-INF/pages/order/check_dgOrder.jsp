<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>核对订单信息</title>
<%@include file="../common/common.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/order/check_order.css' type='text/css' />
<link rel="stylesheet" href="${staUrl}/cdn/lib/jqModal.css"/>
<script type="text/javascript" src="${staUrl}/cdn/lib/jquery.validate.js"></script>
<script type="text/javascript" src="${staUrl }/cdn/lib/messages_cn.js"></script>
<script type="text/javascript" src="${staUrl }/js/order/check_dgOrder.js"></script>
<script type="text/javascript" src="${staUrl }/js/user/user_address.js"></script>
<script type="text/javascript" src="${imgUrl }/area.js"></script>
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
		<span id="hint" style="text-algin:center;">${settlements.errMsg }</span>
		<ul id="order_info">
			<li id="order_info_tit">订单信息</li>
			<li class="bt_bd">
				<div id="consignee"><span>选择收货地址</span></div>
                <div id="addrTo" class="clear">
                    <div id="dftAddr" class="add_block adbk_cur1">
                    	<span class="at_zt_icon">自提地址</span>
                        <p class="ad_p1"><span id="dftDist" class="at_ssq"></span><span>&nbsp;&nbsp;&nbsp;&nbsp;(<span id="dftRcvrName"></span>&nbsp;收)</span></p>
                        <p class="ad_p2"><span id="dftShop"></span>&nbsp;&nbsp;<span class="at_pon" id="dftRcvrMobi"></span></p>
                        <a class="at_upd" onclick="showUpdtAddr(0, event,this);" href="javascript:;">修改</a>
                        <div class="at_duigou at_show"></div>
                    </div>
                </div>

			</li>
			<li id="pay">
				<div id="pay_fs">支付方式：</div>
				<ul>
                    <c:if test="${! isCardPay}">
					<li class="df_li">
						<input id="yezf" name="zffs" value="3"  type="radio" checked="checked"/>
						<label for="yezf">余额支付</label>
						<c:if test="${!empty balanceMsg }"><span style="color:red; margin: 0px 12px 0px 30px;">您的${balanceMsg }</span><a href="${rootUrl }/account/toRecharge.htm">请点击充值&gt;&gt;</a></c:if>
					</li>
					<li class="df_li">
						<input id="zfbzf" name="zffs" value="1"  type="radio" />
						<label for="zfbzf">支付宝支付</label>
					</li>
					</c:if>
					<c:if test="${isCardPay }">
					<li class="df_li">
						<input id="cardPay" name="zffs" value="3"  type="radio" checked="checked" />
						<label for="cardPay">社区卡支付</label>
					</li>
					</c:if>
				</ul>
			</li>

		</ul>
		
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
					<li class="coi_name">
						<a href="${rootUrl }/goods/getGoodsInfo.htm?id=${product.settleGoods[0].id }" title='${product.settleGoods[0].goodsName}' class="hover_a" target="_blank">
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
			<li><a href="${rootUrl }/account/toAccountCenter.htm" target="_blank">忘记支付密码？</a></li>
		</ul>

		<!-- 提交订单出错的提示信息 -->
		<ul id="cf_order_hint"><li></li></ul>
		
		<ul id="cf_order_ft">
			<li><a id="submit_od" href="javascript:;" onclick="commitOrder()">提交订单</a></li>
			<li id="cfof_total">${shopCart.payPrice }</li>
			<li id="cfof_total_tit">合计（不含运费）</li>
			<li id="cfof_slt">已选商品<span>${shopCart.number }</span>件</li>
		</ul>
	</div>
</body>
<%-- 初始化是否卡付的标志--%>
<script type="text/javascript">
    var isCardPay,buyerName,buyerMobi,dftCity,shop,receiverName,receiverPhone;
    var addrs = [];

    (function(){

        isCardPay = ${isCardPay}
        buyerName ="${buyerName}"
        buyerMobi ="${buyerMobi}"
        dftCity = [
            framework.area_map[${user.province}],
            framework.area_map[${user.city}]
        ].join("");

        shop = [
            framework.area_map[${user.district}],
            framework.area_map[${user.community}],
            framework.area_map[${user.pavilionId}]
        ].join("");

        var sbx = [];
        var cnt = 0;
        var provId,cityId,distId,prov,city,dist;
        <c:forEach var="addr" items="${addresses}">

            provId = ${addr.province};
            cityId = ${addr.city};
            distId = ${addr.district};
            prov = framework.area_map[provId];
            city = framework.area_map[cityId];
            dist = framework.area_map[distId];
            var model = new Object();
            model.id = ${addr.id};
            model.provId = provId;
            model.cityId = cityId;
            model.distId = distId;
            model.commId =  "${addr.community}";
            model.paviId=  "${addr.pavilionId}";
            model.receiver = "${addr.receiver}";
            model.dtlAddr = "${addr.addressDetail}";
            model.mobi = "${addr.mobile}";
            model.phone = "${addr.phone}"
            addrs.push(model);
            sbx.push('<div id="')
            sbx.push("${addr.id}")
            sbx.push('" class="add_block adbk_cur2"><p class="ad_p1"><span class="at_ssq">')
            sbx.push(prov)
            sbx.push('</span><span class="at_ssq">')
            sbx.push(city)
            sbx.push('</span><span>&nbsp;&nbsp;&nbsp;&nbsp;(<span>')
            sbx.push("${addr.receiver}")
            sbx.push('</span>&nbsp;收)</span></p><p class="ad_p2"><span>')
            sbx.push(dist)
            sbx.push("${addr.addressDetail}")
            sbx.push('</span>&nbsp;&nbsp;<span class="at_pon">')
            sbx.push("${addr.mobile}")
            sbx.push('</span></p>')
            sbx.push('<a onclick="showUpdtAddr(1, event,this);" class="at_upd" href="javascript:;">修改</a>')
            sbx.push('<div class="at_duigou"></div>')
            sbx.push('</div>')

            cnt ++;
        </c:forEach>
		
        if(cnt<3){
            sbx.push('<div onclick="showAddAddr(0);" class="add_new_block adbk_cur2"><div class="new_adds"></div></div>');
        }
        $("#addrTo").append(sbx.join(""));

    })();
</script>

<%@include file="../common/foot.jsp" %>
<div id="upd_address_pop">
	<div id="uap_tit">
		<span id="up_top_tit"></span>送货地址
		<a id="uap_tit_a" href="javascript:closeAddressPop();"></a>
	</div>
   <form id="frUpdtAddr" action="" method="get">
	<table>
		<%@include file="../common/booth_area.jsp" %>
		<tr class="tr_xx_adds">
			<td class="td_fs">
				<span class="r_star">*</span>
				<span>详细地址：</span>
			</td>
			<td><textarea id="xx_address" placeholder="建议您如实填写详细收货地址，例如街道名称，门牌号码，楼层和房间号等信息"></textarea></td>
		</tr>
		<tr>
			<td class="td_fs">
				<span class="r_star">*</span>
				<span>收货人：</span>
			</td>
			<td><input id="rcvr" type="text" class="uap_inp" maxlength="5"/></td>
		</tr>
		<tr>
			<td class="td_fs">手机号码：</td>
			<td><input id="mobi" name="mobi" type="text" class="uap_inp" maxlength="11"/></td>
		</tr>
		<tr>
			<td class="td_fs">电话号码：</td>
			<td><input id="phone" name="phone" type="text" class="uap_inp" maxlength="11"/></td>
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
   </form>
</div>
</html>
<script type="text/javascript">

    function showAddAddr() {
        $("#msg_filter_div").show();//蒙版
        $("#upd_address_pop").show();
        $("#up_top_tit").html("添加");
        curAddrId = "newAddr" // set add new
    }

    function showUpdtAddr(flag, e,obj) {
        window.event? window.event.cancelBubble = true : e.stopPropagation();
        var id = $(obj).closest("div")[0].id;
        if(id == "dftAddr"){ // default shop id
            curAddrId = id;
            //只设置电话收货人，其它的不可以
            $("#rcvr").val($("#dftRcvrName").html())
            $("#mobi").val($("#dftRcvrMobi").html())
            $("#phone").val($("#dftRcvrMobi").html())

        }
        else if(id){
            id = parseInt(id);
            if(id>0){
                curAddrId = id;
                //从数据缓存中查找当前的数据并赋值到弹出层
                for(var i = 0 ;i<addrs.length;i++){
                    model = addrs[i];
                    if(id === model.id){
                        restoreValue(model.provId,model.cityId,model.distId,model.commId,model.paviId);
                        $("#xx_address").val(model.dtlAddr);
                        $("#rcvr").val(model.receiver);
                        $("#mobi").val(model.mobi);
                        $("#phone").val(model.phone);
                        break;
                    }
                }
            }
        }
        else{
            console.log("param id is wrong!");
        }

        $("#msg_filter_div").show();//蒙版
        $("#upd_address_pop").show();
        $("#up_top_tit").html("修改");
    }

    // 更新地址 分为三类: dftAddr  newAddr 及拥有数字id的div
    function updtAddr() {
        if($("#mobi").val() == "" && $("#phone").val() == ""){
            showErr("电话和手机不能同时为空！");
            return;
        }
        var paramOk = $("#frUpdtAddr").valid();
        if(paramOk){
            var addrId,provId,cityId,distId,comId,shopId,addrDtl;
            var mobi,phone,isDft, rcvr;
            if(curAddrId == "dftAddr"){
                // set the page value only
                $("#dftRcvrName").html($("#rcvr").val())
                $("#dftRcvrMobi").html($("#mobi").val())
                $("#msg_filter_div").hide();//蒙版
                $("#upd_address_pop").hide();
                return;
            }
            else{
                if(curAddrId == "newAddr") // Add new addr.
                    addrId = "";
                else
                    addrId = curAddrId;

                provId = $("#province").val();
                cityId = $("#city").val();
                distId = $("#district").val();
                comId = $("#community").val();
                shopId = $("#pavilionId").val();
                addrDtl = $("#xx_address").val();
                mobi = $("#mobi").val();
                phone = $("#phone").val();
                rcvr = $("#rcvr").val();
                isDft = $("#set_def_addr").prop("checked");
                if(comId =="" || shopId==""){
                    showErr("请选择商圈和亭子");
                    return ;
                }
                $.ajax({
                    type: "post",
                    async: false,
                    data: {
                        id:addrId,
                        province:provId,
                        city:cityId,
                        district:distId,
                        community:comId,
                        pavilionId:shopId,
                        addressDetail:addrDtl,
                        mobile:mobi,
                        phone:phone,
                        isdefault:isDft,
                        receiver:rcvr
                    },
                    url: _rootUrl + "/address/updateDftAddrByAgent.htm",
                    success: function(result){
                        var address = eval("("+result+")")
                        if(address.success){
                            // refresh page
                            location.reload(true)
                        }else{
                            showMsgHint("保存收货人信息出现错误！","gantan");
                        }
                    }
                })
            }
        }
    }
</script>
