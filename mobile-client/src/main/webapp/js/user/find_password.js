$(function(){		
	$("#get_dxvcode").bind("click", clickFunction);			
	
	//找回密码验证
	$(".reg_btn").click(function(){
		var mark = false;
		var mobile = $("#mobile");
		var password = $("#pwd");
		var newPwd = $("#newPwd");
		var captcha = $("#captcha");
		var code = $("#code");
		if(!reg_mobile.test(mobile.val())){
			hintMsg1(mobile,"手机号不正确");
			mark = true;
			//alert("手机号不正确");
		}if(!reg_pwd.test(password.val())){
			hintMsg1(password,"密码必须6位以上的包含数字、字母、特殊符号至少两种的字符");
			mark = true;
			//alert("密码必须6位以上的包含数字、字母、特殊符号至少两种的字符");
		}if(password.val() != newPwd.val()){
			hintMsg1(newPwd,"两次密码输入不一致");
			mark = true;
			//alert("两次密码输入不一致")
		}if($.trim(captcha.val()) == ''){
			hintMsg1(captcha,"手机验证码不能为空");
			mark = true;
			//alert("验证码不能为空");
		}if($.trim(code.val()) == ''){
			hintMsg1(code,"图形验证码不能为空");
			mark = true;
			//alert("验证码不能为空");
		}
		if(mark){
			return ;
		}else{
			$.ajax({
				type: "post",
				url: _rootUrl+"/user/resetPwd.htm",
				data:{password:hex_md5(password.val()),captcha:captcha.val(),code:code.val(),mobile:mobile.val()},
				async: true,
				cache: false,
				dataType: "text",
				success: function(data){
					var result = eval(data);
					if(result == '5'){
						alert("修改成功");
						window.location.href=_rootUrl+"/index.htm";
					}else if(result == '2'){
						hintMsg1(captcha,"手机验证码不正确");
					}else if(result == '3'){
						hintMsg1(code,"图形验证码不正确");
					}else if(result == '1'){
						hintMsg1(mobile,"用户不存在");
					}else{
						alert("修改失败");
					}
				}
			});
		}
	});
	
	$("#mobile, #pwd, #newPwd, #captcha, #code").focus(function() {
		var _t = $(this);
		_t.removeClass("focus_eor_ipt");
		_t.addClass("focus_ipt");
		_t.siblings("label").remove();
	}).blur(function() {
		$(this).removeClass("focus_ipt");
	});
});
//验证手机号
var reg_mobile = /^1[3|4|5|6|7|8][0-9]\d{8}$/;
//验证密码(必须包含数字、字母、特殊符号至少两种的6位)
var reg_pwd = /^(((?=.*[0-9])(?=.*[a-zA-Z])|(?=.*[0-9])(?=.*[^\s0-9a-zA-Z])|(?=.*[a-zA-Z])(?=.*[^\s0-9a-zA-Z]))[^\s]{6,20})$/;
//获取图片验证码
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
//验证用户是否存在,计时器
var count = 60;
function clickFunction(){
	var mobile = $("#mobile");
	if(!reg_mobile.test(mobile.val())){
		hintMsg1(mobile,"手机号不正确");
	}else{
		$.ajax({
			type: "post",
			url: _rootUrl + "/user/ismobileExist.htm",
			cache: false,
			async: true,
			data: {mobile:mobile.val()},
			dataType: "text",
			success: function(data){
				if(data == "false"){
					$("#get_dxvcode").text(count+"重新发送");
					t = setInterval("intervalometer()",1000);
					$("#get_dxvcode").unbind("click");
					$.post(_rootUrl + "/user/sendMobileCode.htm",{mobile:mobile.val()});
				}else{
					alert("用户不存在");
				}
			}				
		});
	}
}
function intervalometer(){
	if(count > 0){
		count--;
		$("#get_dxvcode").text(count+"重新发送");
	}else{
		clearInterval(t);
		$("#get_dxvcode").text("重新发送");
		count = 60;
		$("#get_dxvcode").bind("click", clickFunction);		
	}	
}


