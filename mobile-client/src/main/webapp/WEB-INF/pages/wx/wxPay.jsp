<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
	<title>手拉手触屏版</title>
	<%@include file="../common/common.jsp" %>
	<style type="text/css">
		body { background: #f5f5f5;}
		.wcp_title { text-align: center; color: #666; padding: 30px 0px 5px 0px;}
		.wcp_price { text-align: center; color: #666; font-size: 32px; padding-bottom: 30px;}
		.wcp_skf { border-top: solid 1px #97948c; background: #fff; padding: 15px 20px 0px 20px;}
		.wcp_sp { border-bottom: solid 1px #97948c; background: #fff; padding: 8px 20px 15px 20px;}
		.wcp_zf { background: #33af3d; color: #fff; display: block; height: 44px; line-height: 44px; text-align: center; border-radius: 3px; margin: 35px 14px 0px 14px;}
	</style>
	<script type="text/javascript">
		$(function(){
			$("#getBrandWCPayRequest").click(function(){	
				var total_fee = $("#total_fee").val() * 100;
				var order_num = $("#order_num").val();
				var openid = $("#openid").val();
				var orderId = $("#orderId").val();
				$.ajax({
					type : "POST",
					url : _rootUrl+"/wx/getPrepayid.htm",
					asycn: true,
					cache: false,
					dataType: "json",
					data: {openid: openid, total_fee: total_fee, order_num: order_num},
					success: function(data){
						if (data.status == "302") {
							location.href = data.location;
					    }
						if(!data.state){
							alert(data.message);
						}else{
							var appId = data.appId;
							var timeStamp = data.timeStamp;
							var nonceStr =  data.nonceStr;
							var	packages =  data.packages;
							var signType =  data.signType;
							var paySign =  data.paySign;
							WeixinJSBridge.invoke('getBrandWCPayRequest',{
								 "appId": appId, "timeStamp": timeStamp, "nonceStr": nonceStr, "package": packages, "paySign": paySign, "signType": signType
							 }, function(res) {
									//alert(res.err_msg);
									//WeixinJSBridge.log(res.err_msg);
									if (res.err_msg == "get_brand_wcpay_request:ok") {
										//alert("支付成功!");
										window.location.href=_rootUrl+"/wx/wxPayResult.htm?orderId="+orderId;
									//} else if (res.err_msg == "get_brand_wcpay_request:cancel") {
										//alert("用户取消支付!");
									} else {
										alert("支付失败!");									
									}
								});
						}
					}												
				});	
			});
		});
		
	</script>
</head>

<body>
	<div class="viewport">
		<div id="header">
			<div class="header_txt">确认支付</div>
			<div class="h_back" onclick="history.go(-1);"></div>
			<%@include file="../common/mz_nav.jsp" %>
		</div>
		<div class="wcp_title">${body }付款</div>
		<div class="wcp_price">￥${total_fee }</div>
		<ul class="clear wcp_skf">
			<li style="float: left; color: #a9a9a9;">收款方</li>
			<li style="float: right; color: #666;">手拉手</li>
		</ul>
		<ul class="clear wcp_sp">
			<li style="float: left; color: #a9a9a9;">商品</li>
			<li style="float: right; color: #666;">${body }</li>
		</ul>
		<a href="javascript:;"  class="wcp_zf" id="getBrandWCPayRequest">支付</a>
		<input id="openid" value="${openid }" type="hidden"/>
		<input id="orderId" value="${orderId }" type="hidden"/>
        <input id="total_fee"  value="${total_fee }" type="hidden"/>
        <input id="order_num"  value="${order_num }" type="hidden"/>
	</div>
</body>
</html>