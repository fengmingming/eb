$(function() {
	$(":text[name='money'],textarea[name='remark']").focus(function() {
		$("span[id='hint']").remove();
		$(this).removeClass("focus_eor_ipt");
	});
	
	//tab切换
	var _recharge_form = $("#recharge_form").show(),
		_re_card_form = $("#re_card_form").hide();
	$("#zhyecz_li").click(function() {
		$(this).siblings().removeClass("tab_txt_cur");
		$(this).addClass("tab_txt_cur");
		_recharge_form.show();
		_re_card_form.hide();
	});
	$("#czkcz_li").click(function() {
		$(this).siblings().removeClass("tab_txt_cur");
		$(this).addClass("tab_txt_cur");
		_recharge_form.hide();
		_re_card_form.show();
	});
	
	$("#czk_sub").click(function(){
		var mark = true;
		var recharge_pwd = $(".czk_pwd");
		var captcha = $("#czk_vcode").val();
		var password = '';
		if($.trim(captcha) == ''){
			//$("#cap_val").show();
			var lb = $("<label class='lb_eor_msg' >验证码不能为空！</label>");
			$("#czk_ex").siblings(".lb_eor_msg").remove().end().after(lb);
			mark = false;
		}
		recharge_pwd.each(function(){
			if($.trim($(this).val()).length != 4){
				showMsgHint("请输入16位卡密码！","gantan");
				mark = false;
			}else{
				password += $(this).val();
			}
		});
		
		/*
		if($.trim(recharge_pwd) == ''){
			showMsgHint("充值卡密码不能为空！","gantan");
			mark = false;
		}*/	
		if(mark){
			$.post(_rootUrl + "/account/rechargeCard.htm",{password:password,captcha:captcha},function(data){				
				var result = eval(data);
				if(result == 3){
					//showMsgHint("验证码错误！","gantan");
					var lb = $("<label class='lb_eor_msg' >验证码错误！</label>");
					$("#czk_ex").siblings(".lb_eor_msg").remove().end().after(lb);
				}else if(result == 2){
					showMsgHint("充值卡密码不正确！","gantan");
				}else if(result == 1){
					showMsgHint("充值失败！","gantan");
				}else{
					$("#czk_ex").siblings(".lb_eor_msg").remove();
					recharge_pwd.val("");
					showMsgHint("充值"+result+"元成功！","duigou");
				}				
			});
		}else{
			return false;
		}
	});
	
});

function checkInput(){
	$("#hint").remove();
	var _money = $(":text[name='money']");
	if($.trim(_money.val()) == ""){
		_money.addClass("focus_eor_ipt");
		_money.after("<span id='hint'>&nbsp;&nbsp;&nbsp;充值金额不能为空！</span>");
		return false;
	}
	
	var reg1 = /^[1-9][0-9]*$/;
	var reg2 = /^(0|[1-9][0-9]*)\.[0-9]{1,2}$/;
	if(!(reg1.test(_money.val()) || reg2.test(_money.val()))){
		_money.addClass("focus_eor_ipt");
		_money.after("<span id='hint'>&nbsp;&nbsp;&nbsp;充值金额不正确！</span>");
		return false;
	}
	
	if(_money.val() == "0" || _money.val() == "0.0" || 
			_money.val() == "0.00"){
		_money.addClass("focus_eor_ipt");
		_money.after("<span id='hint'>&nbsp;&nbsp;&nbsp;充值金额不正确！</span>");
		return false;
	}
	
	var _remark = $("textarea[name='remark']");
	if(_remark.val().length > 255){
		_remark.addClass("focus_eor_ipt");
		_remark.after("<span id='hint'>&nbsp;&nbsp;&nbsp;备注不能超过255个字符！</span>");
		return false;
	}
}

function submitForm(){
	$("#recharge_form").submit();
}

function changeCaptcha(imgId){
	$("#"+imgId).attr("src",_rootUrl + "/img/validate.htm?"+Math.random());
}
//输入4字符个自动跳到下一个
function T1_onkeyup() {
    if($("#t1").val().length == 4){
    	$("#t2").focus();
    }
} 
function T2_onkeyup() {
    if($("#t2").val().length == 4){
	    $("#t3").focus();
    }
}
function T3_onkeyup() {
    if($("#t3").val().length == 4){
	    $("#t4").focus();
    }
}
function T4_onkeyup() {
	if($("#t4").val().length == 4){
	    $("#czk_vcode").focus();
	}
}
