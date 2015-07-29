$(function(){
	if($("input[name='payment']:checked").parent().text() == "账户余额支付"){
		$("#payPwd").show();
	}else{
		$("#payPwd").hide();
	}
	//获取焦点
	$("#cardNum, #money, #payPassword").focus(function() {
		var _t = $(this);
		_t.removeClass("focus_eor_ipt");
		_t.addClass("focus_ipt");
		_t.siblings("label").remove();
	}).blur(function() {
		$(this).removeClass("focus_ipt");
	});
});
//消息模板1
function hintMsg1(obj, str) {
	obj.siblings("label").remove();
	obj.addClass("focus_eor_ipt");
	obj.after($("<label class='r_msg'>" + str + "</label>"));
}
//消息模板2
function hintMsg2(obj, str) {
	obj.siblings("label").remove();
	obj.addClass("focus_eor_ipt");
	obj.after($("<label class='r_msg_w'>" + str + "</label>"));
}
function checkUserInfo(){
	var cardNum = $("#cardNum");
	if($.trim(cardNum.val()) == ''){
		hintMsg1(cardNum,"请刷卡");
		//alert("请刷卡");
	}else{
		$.ajax({
			type: "post",
			url: _rootUrl+"/card/getUserByCardNum.htm",
			async: true,
			cache: false,
			data: {cardNum: cardNum.val().toLowerCase()},
			dataType: "json",
			success: function(data){
				if(data.result == undefined){
					hintMsg1(cardNum,"该卡未绑定用户");
					//alert("该卡未绑定用户");
				}else{
					var first = data.result.mobile.substr(0,3);
					var middle = "*****";
					var last = data.result.mobile.substr(8);
					$("#name").val(data.result.userName);
					$("#mobile").val(first+middle+last);
					$("#amount").val("￥"+data.result.amount);
				}
			}								
		});
	}
	
}
//单选框事件
function selRadio(obj){
	if($(obj).parent().text() == "账户余额支付"){
		$("#payPwd").show();
	}else{
		$("#payPwd").hide();
	}
}
var reg_money = /^\+?[1-9][0-9]*$/;
var reg2_money = /^([1-9][0-9]*)\.[0-9]{1,2}$/;
//充值
function recharge(){
	var money = $("#money");
	var cardNum = $("#cardNum");
	var payPassword = $("#payPassword");
	var payType = $("input[name='payment']:checked").parent().text();
	if(!reg_money.test(money.val())){
		if(!reg2_money.test(money.val())){
			hintMsg1(money, "请输入正确的金额");
		}		
	}else{
		$.ajax({
			type: "get",
			url: _rootUrl+"/card/checkSwingCard.htm",
			async: false,
			cache: false,
			data:{cardNum: cardNum.val().toLowerCase()},
			dataType: "text",
			success: function(data){
				var result = eval(data);
				if(result == "false"){
					hintMsg1(cardNum, "卡号和用户不匹配");
					//alert("卡号和用户不匹配！");
				}else{
					if(payType == "账户余额支付"){
						if($.trim(payPassword.val()) == ''){
							hintMsg1(payPassword, "支付密码不能为空");
						}else{
							$.ajax({
								type: "post",
								url: _rootUrl+"/card/recharge.htm",
								async: false,
								cache: false,
								data:{money: money.val(),payType: "balance", payPwd: hex_md5(payPassword.val())},
								dataType: "json",
								success: function(data){
									if(data.state){
										alert("充值"+data.money+"元成功");
										cardNum.focus();
									}else{
										if(data.money != undefined){
											alert(data.money);
										}else{
											alert("充值失败");
										}
										
									}
								}
							});
						}
						
					}else{
						window.location.href=_rootUrl+"/card/recharge.htm?money="+money.val();												
					}
				}
			}
		});
		
	}
}