$(function(){
	/*
	$("input[name='password']").keyup(function(e){
		if(e.keyCode == 13){
			$("#login").click();
		}
	});
	*/
	$("input[name='username'], input[name='password']").focus(function() {
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

function loginValidate(){
	var mark = false;
	var flag = $("#flag").val();
	var username = $("input[name='username']");
	var password = $("input[name='password']");	
	if($.trim(username.val()) == ''){
		mark = true;
		hintMsg1(username,"用户名不能为空");
	}
	if($.trim(password.val()) == ''){
		mark = true;
		hintMsg1(password,"密码不能为空");
	}
	if(mark){
		//alert("用户名和密码不能为空");
		//hintMsg1(username,"用户名和密码不能为空");
		return ;
	}else{
		$.ajax({
			type: "post",
			url: _rootUrl+"/user/loginValidate.htm",
			async: true,
			cache: false,
			data: {mobile:username.val(),password:hex_md5(password.val())},
			dataType: "json",
			success: function(data){
				if(data.success){
					if($.trim(flag) == 'true'){
						window.location.href = _rootUrl + "/user/my_sls.htm";
					}else{
						if(document.referrer.indexOf("logout.htm") > 0 || document.referrer.indexOf("login.htm") > 0){
							window.location.href = _rootUrl + "/index.htm";
						}else{
							window.location.href = document.referrer;
						}
					}															
				}else{
					alert("用户名和密码错误");
				}				
			}
		});		
	}
}