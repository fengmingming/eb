$(function(){
	$("#at_fw1_fsm").bind("click", clickFunction);
	
	$("#captcha, #picCode, .at_fw2_inp, #mobile").focus(function() {
		var _t = $(this);
		_t.removeClass("focus_eor_ipt");
		_t.addClass("focus_ipt");
		_t.siblings("label").remove();
	}).blur(function() {
		$(this).removeClass("focus_ipt");
	});
});
function clickFunction() {		
	if($("#mobile").length != 0){
		var mobile = $("#mobile");
		if(!reg_mobile.test(mobile.val())){
			hintMsg1(mobile, "手机号不正确");
			return ;
		}else{			
			$.post(_rootUrl + "/user/ismobileExist.htm",{mobile:mobile.val()}, function(data){
				if(data == "false"){
					$("#at_fw1_fsm").text(count+"重新发送");
					t = setInterval("intervalometer()",1000);
					$("#at_fw1_fsm").unbind("click");
					$.post(_rootUrl + "/user/sendMobileCode.htm",{mobile:mobile.val()});
				}else{
					hintMsg1(mobile, "手机号不存在");
				}								
			},"text");
			//mobile.removeClass("focus_eor_ipt");
			//mobile.addClass("focus_ipt");
			//mobile.siblings("label").remove();			
		}		
	}else{
		$("#at_fw1_fsm").text(count+"重新发送");
		t = setInterval("intervalometer()",1000);
		$("#at_fw1_fsm").unbind("click");
		$.post(_rootUrl + "/user/sendMobileCode4Login.htm");
	}	
}
//定时器
var count = 60;
//var reg = /^[0-9A-Za-z]{6,16}$/;
var reg = /^(((?=.*[0-9])(?=.*[a-zA-Z])|(?=.*[0-9])(?=.*[^\s0-9a-zA-Z])|(?=.*[a-zA-Z])(?=.*[^\s0-9a-zA-Z]))[^\s]{6,20})$/;
var reg_mobile = /^1[3|4|5|6|7|8][0-9]\d{8}$/;
function intervalometer(){
	if(count > 0){
		count--;
		$("#at_fw1_fsm").text(count+"重新发送");
	}else{
		clearInterval(t);
		$("#at_fw1_fsm").text("重新发送");
		count = 60;
		$("#at_fw1_fsm").bind("click", clickFunction);
	}		
}
//重新获取图形验证码
function changeCaptcha(imgId){
	$("#"+imgId).attr("src", _rootUrl + "/img/validate.htm?"+Math.random());
}
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
//修改密码第一步验证
function checkCaptcha(obj){
	var captcha = $("#captcha");
	var picCode = $("#picCode");
	if($.trim(captcha.val()) == '' && $.trim(picCode.val()) == ''){
		hintMsg1(captcha, "手机验证码不能为空");
		hintMsg1(picCode, "图形验证码不能为空");
	}else{
		if($.trim(captcha.val()) == ''){
			hintMsg1(captcha, "手机验证码不能为空");
		}else if($.trim(picCode.val()) == ''){
			hintMsg1(picCode, "图形验证码不能为空");
		}else{
			$.post(_rootUrl + "/account/checkCaptcha.htm",{captcha : captcha.val(), picCode : picCode.val()},function(data){
				var result = eval(data);
				if(result == "3"){
					hintMsg1(captcha, "手机验证码不正确");
					hintMsg1(picCode, "图形验证码不正确");
				}else if(result == "2"){
					hintMsg1(captcha, "手机验证码不正确");
				}else if(result == '1'){
					hintMsg1(picCode, "图形验证码不正确");
				}else{
					if(obj == undefined){
						window.location.href = _rootUrl + "/account/modifyPayPwd2.htm?token="+result;
					}else{
						window.location.href = _rootUrl + "/account/modifyLoginPwd2.htm?token="+result;
					}					
				}
			},"text");
		}				
	}
}
//修改密码第二步验证
function checkPwd(obj){
	var newPwd = $("input[name='newPwd']");
	var confirmPwd = $("input[name='confirmPwd']");
	var password= hex_md5(newPwd.val());
	if(obj == undefined){
		if($.trim(newPwd.val()) == '' && $.trim(confirmPwd.val()) == ''){
			hintMsg2(newPwd, "新密码不能为空");
			hintMsg2(confirmPwd, "确认密码不能为空");
		}else {
			if(!reg.test(newPwd.val())){
				if($.trim(confirmPwd.val()) == ''){
					hintMsg2(newPwd, "密码必须是包含数字、字母、特殊字符至少两种的6-20位字符");
					hintMsg2(confirmPwd, "确认密码不能为空");
				}else{
					hintMsg2(newPwd, "密码必须是包含数字、字母、特殊字符至少两种的6-20位字符");
				}				
			}else if(newPwd.val() != confirmPwd.val()){
				hintMsg2(confirmPwd, "新密码和确认密码不相同");
			}else{				
				$.post(_rootUrl + "/account/modifyPayPwd.htm",{password : password},function(data){
					var result = eval(data);
					if(result == 2){
						hintMsg2(newPwd, "支付密码不能和登录密码相同");
					}else if(result == 7){
						window.location.href = _rootUrl + "/account/modifyPayPwd1.htm";		
					}else if(result == 1){
						window.location.href = _rootUrl + "/account/modifyPayPwd3.htm";	
					}else{
						showMsgHint("修改失败！","gantan");
					}
				},"text");					
												
			}
		}					
	}else{
		var oldPwd = $("input[name='oldPwd']");
		var oldPassword = hex_md5(oldPwd.val());
		if($.trim(oldPwd.val()) == '' && $.trim(newPwd.val()) == '' && $.trim(confirmPwd.val()) == ''){
			hintMsg2(oldPwd, "原密码不能为空");
			hintMsg2(newPwd, "新密码不能为空");
			hintMsg2(confirmPwd, "确认密码不能为空");
		}else {
			if($.trim(oldPwd.val()) == '' && $.trim(newPwd.val()) == ''){
				hintMsg2(oldPwd, "原密码不能为空");
				hintMsg2(newPwd, "新密码不能为空");
			}else if($.trim(newPwd.val()) == '' && $.trim(confirmPwd.val()) == ''){
				hintMsg2(newPwd, "新密码不能为空");
				hintMsg2(confirmPwd, "确认密码不能为空");
			}else if($.trim(oldPwd.val()) == '' && $.trim(confirmPwd.val()) == ''){
				hintMsg2(confirmPwd, "确认密码不能为空");
				hintMsg2(oldPwd, "原密码不能为空");
			}else if($.trim(oldPwd.val()) == ''){
				hintMsg2(oldPwd, "原密码不能为空");
			}else if(!reg.test(newPwd.val())){
				if($.trim(confirmPwd.val()) == ''){
					hintMsg2(newPwd, "密码必须是包含数字、字母、特殊字符至少两种的6-20位字符");
					hintMsg2(confirmPwd, "确认密码不能为空");
				}else{
					hintMsg2(newPwd, "密码必须是包含数字、字母、特殊字符至少两种的6-20位字符");
				}				
			}else if($.trim(newPwd.val()) == $.trim(oldPwd.val())){
				hintMsg2(newPwd, "新密码不能和旧密码相同");
			}else if($.trim(newPwd.val()) != $.trim(confirmPwd.val())){
				hintMsg2(confirmPwd, "新密码和确认密码不相同");
			}else{
				$.post(_rootUrl + "/account/modifyLoginPwd.htm",{oldPassword : oldPassword, password : password},function(data){
					var result = eval(data);
					if(result == 2){
						hintMsg2(newPwd, "登陆密码不能和支付密码相同");
					}else if(result == 7){
						window.location.href = _rootUrl + "/account/modifyLoginPwd1.htm";
					}else if(result == 21){
						hintMsg2(oldPwd, "原密码不正确");
					}else if(result == 0){
						window.location.href = _rootUrl + "/account/modifyLoginPwd3.htm";
					}else{
						showMsgHint("修改失败！","gantan");
					}
				},"text");
			}
		}
	}	
}
//重设密码第一步验证
function checkMess(){
	var captcha = $("#captcha");
	var picCode = $("#picCode");
	var mobile = $("#mobile");
	var flag = true;
	if(!reg_mobile.test(mobile.val())){
		hintMsg1(mobile, "手机号不正确");
		flag = false;
	}
	if($.trim(picCode.val()) == ''){
		hintMsg1(picCode, "图形验证码不能为空");
		flag = false;
	}
	if($.trim(captcha.val()) == ''){
		hintMsg1(captcha, "手机验证码不能为空");
		flag = false;
	}
	if (flag) {
		$.post(_rootUrl + "/account/checkMess.htm",{captcha:captcha.val(),picCode:picCode.val(),mobile:mobile.val()},function(data){
			var result = eval(data);
			if(result == 1){
				hintMsg1(mobile, "不存在该用户");
			}else if(result == 2){
				hintMsg1(captcha, "手机验证码不正确");
			}else if(result == 3){
				hintMsg1(picCode, "图形验证码不正确");
			}else{
				window.location.href=_rootUrl + "/account/resetPwd2.htm?token="+result;
			}
		},"text");
	}
}
//重设密码第二步验证
function resetPwd(){
	var newPwd = $("input[name='newPwd']");
	var confirmPwd = $("input[name='confirmPwd']");
	var password = hex_md5(newPwd.val());
	if($.trim(newPwd.val()) == '' && $.trim(confirmPwd.val()) == ''){
		hintMsg2(newPwd, "新密码不能为空");
		hintMsg2(confirmPwd, "确认密码不能为空");
	}else {
		if(!reg.test(newPwd.val())){
			if($.trim(confirmPwd.val()) == ''){
				hintMsg2(newPwd, "密码必须是包含数字、字母、特殊字符至少两种的6-20位字符");
				hintMsg2(confirmPwd, "确认密码不能为空");
			}else{
				hintMsg2(newPwd, "密码必须是包含数字、字母、特殊字符至少两种的6-20位字符");
			}				
		}else if(newPwd.val() != confirmPwd.val()){
			hintMsg2(confirmPwd, "新密码和确认密码不相同");
		}else{
			$.post(_rootUrl + "/account/resetPwd.htm",{password : password},function(data){
				var result = eval(data);
				if(result == 1){
					window.location.href = _rootUrl + "/account/resetPwd3.htm";	
				}else if(result == 7){
					window.location.href = _rootUrl + "/account/resetPwd1.htm";
				}else if(result == 2){
					hintMsg2(newPwd, "新密码不可用！");
				}else{				
					showMsgHint("修改失败！","gantan");
				}
			},"text");																		
		}
	}						
}