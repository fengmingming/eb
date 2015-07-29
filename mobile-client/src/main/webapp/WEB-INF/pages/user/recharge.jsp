<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
	<title>手拉手触屏版</title>
	<%@include file="../common/common.jsp" %>
	<link rel='stylesheet' href='${staUrl }/css/user/recharge.css' type='text/css' />
	<script type="text/javascript">
		$(function() {
			//支付方式点击
			$(".goods_check").click(function() {
				var _this = $(this);
				if (_this.attr("class").indexOf("gc_y") != -1) {
					_this.removeClass("gc_y").addClass("gc_n");
				}else{
					_this.removeClass("gc_n").addClass("gc_y");
				}
			});
			
			//账户余额充值点击
			$("#r_yecz").click(function() {
				$(this).addClass("cur_a");
				$("#r_czkcz").removeClass("cur_a");
				$(".common_wrapper_ye").show();
				$(".common_wrapper_czk").hide();
			});
			//充值卡充值点击
			$("#r_czkcz").click(function() {
				$(this).addClass("cur_a");
				$("#r_yecz").removeClass("cur_a");
				$(".common_wrapper_ye").hide();
				$(".common_wrapper_czk").show();
			});
			
			recharge_card = function(){
				var obj = {};
				obj.vericode = $.trim($("#valInput").val());
				obj.cardNum = $.trim($("#passInput").val());
				if(!obj.vericode){
					alert("验证码不能为空");
					return;
				}
				if(!obj.cardNum){
					alert("充值卡密码不能为空");
					return;
				}
				$.post("${dynUrl}/pcenter/recharge/card.htm", obj,function(data){
					if(data.status == "302"){
						location.href=data.location;
						return ;  
					}
					if(data.success){
						alert("充值成功");
						$("#passInput").val("");
					}else{
						alert(data.errMsg);
					}
					$("#valInput").val("");
					$("#valImg").click();
										
				},"json");
			}
			$(document).ready(function(){
				$("#valImg").click(function(){
					$(this).attr("src","${dynUrl}/img/validate.htm?version="+Math.random());
				});
			});
		});
	</script>
</head>

<body>
	<div class="viewport">
		<div id="header">
			<div class="header_txt">充值</div>
			<div class="h_back" onclick="history.go(-1);"></div>
		</div>
		
		<ul class="clear r_opt">
			<li style="border-right: solid 1px #ddd;"><a id="r_yecz" class="cur_a" href="javascript:;">账户余额充值</a></li>
			<li><a id="r_czkcz" href="javascript:;">充值卡充值</a></li>
		</ul>
		
		<div class="common_wrapper_ye">
			<form method="post" action="">
				<input type="text" class="r_inp" placeholder="金额 (元) 请输入金额" />
				<div class="r_zffs">支付方式</div>
				<div class="r_zfbzf clear">
					<a class="goods_check gc_y" href="javascript:;"></a>
					<span class="">支付宝支付</span>
				</div>
				<a class="r_btn_ye" href="javascript:;">充值</a>
			</form>
		</div>
		
		<div class="common_wrapper_czk">
			<div>
				<input type="text" class="r_inp" placeholder="请输入16位充值卡密码"  id="passInput"/>
				<div class="r_vcode">
					<input type="text" class="r_inp" id="valInput" placeholder="验证码" />
					<a id="get_vcode" href="javascript:;"><img src="${dynUrl}/img/validate.htm" id="valImg"/></a>
				</div>
				<a class="r_btn_czk" onclick="recharge_card()">充值</a>
			</div>
			<p style="margin: 30px 0px 15px 0px;">温馨提示：</p>
			<p>1：充值成功后，余额可能会存在延迟现象，一般1到5分钟内到账；</p>
			<p>2：您只能用充值卡进行充值，如有问题，请咨询&nbsp;4000-365-020；</p>
			<p>3：充值完成后，您可以进入我的钱包记录页面进行查看余额充值状态。</p>
		</div>
	</div>
</body>
</html>