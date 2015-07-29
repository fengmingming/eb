$(function(){
	$("#r_cod_a").bind("click", sendMobileCode);
	
	$(".required").focus(function() {
		$(this).removeClass("focus_eor_ipt");
		$(this).addClass("focus_ipt");
		$(this).siblings("label").remove();
	}).blur(function() {
		$(this).removeClass("focus_ipt");
	});
	
	
	$("#reg_btn").css({"background":"#ff9900"});
	
	 var placeholderfriend = {
			    focus: function(s) {
			      s = $(s).hide().prev().show().focus();
			      var idValue = s.attr("id");
			      if (idValue) {
			        s.attr("id", idValue.replace("placeholderfriend", ""));
			      }
			      var clsValue = s.attr("class");
				  if (clsValue) {
			        s.attr("class", clsValue.replace("placeholderfriend", ""));
			      }
			    }
			  };

			  //判断是否支持placeholder
			  function isPlaceholer() {
			    var input = document.createElement('input');
			    return "placeholder" in input;
			  }
			  //不支持的代码
			  if (!isPlaceholer()) {
			    $(function() {

			      var form = $(this);

			      //遍历所有文本框，添加placeholder模拟事件
			      var elements = form.find("input[type='text'][placeholder]");
			      elements.each(function() {
			        var s = $(this);
			        var pValue = s.attr("placeholder");
					var sValue = s.val();
			        if (pValue) {
			          if (sValue == '') {
			            s.val(pValue);
			          }
			        }
			      });

			      elements.focus(function() {
			        var s = $(this);
			        var pValue = s.attr("placeholder");
					var sValue = s.val();
			        if (sValue && pValue) {
			          if (sValue == pValue) {
			            s.val('');
			          }
			        }
			      });

			      elements.blur(function() {
			        var s = $(this);
			        var pValue = s.attr("placeholder");
					var sValue = s.val();
			        if (!sValue) {
			          s.val(pValue);
			        }
			      });

			      //遍历所有密码框，添加placeholder模拟事件
			      var elementsPass = form.find("input[type='password'][placeholder]");
			      elementsPass.each(function(i) {
			        var s = $(this);
			        var pValue = s.attr("placeholder");
					var sValue = s.val();
			        if (pValue) {
			          if (sValue == '') {
			            //DOM不支持type的修改，需要复制密码框属性，生成新的DOM
			            var html = this.outerHTML || "";
			            html = html.replace(/\s*type=(['"])?password\1/gi, " type=text placeholderfriend")
			              .replace(/\s*(?:value|on[a-z]+|name)(=(['"])?\S*\1)?/gi, " ")
			              .replace(/\s*placeholderfriend/, " placeholderfriend value='" + pValue
			              + "' " + "onfocus='placeholderfriendfocus(this);' ");
			            var idValue = s.attr("id");
			            if (idValue) {
			              s.attr("id", idValue + "placeholderfriend");
			            }
			            var clsValue = s.attr("class");
						if (clsValue) {
			              s.attr("class", clsValue + "placeholderfriend");
			            }
			            s.hide();
			            s.after(html);
			          }
			        }
			      });

			      elementsPass.blur(function() {
			        var s = $(this);
			        var sValue = s.val();
			        if (sValue == '') {
			          var idValue = s.attr("id");
			          if (idValue) {
			            s.attr("id", idValue + "placeholderfriend");
			          }
			          var clsValue = s.attr("class");
					  if (clsValue) {
			            s.attr("class", clsValue + "placeholderfriend");
			          }
			          s.hide().next().show();
			        }
			      });

			    });
			  }
			  window.placeholderfriendfocus = placeholderfriend.focus;
	
	
	
});

function afterLogin(){
	toIndex();
}

function toIndex(){
	window.location.href = _rootUrl + "/main/index.htm";
}

//判断手机号码是否正确，是否重复
function checkMobile(flag){
	var _mobile = $(":text[name='mobile']");
	var reg = /^1[3|5|7|8|][0-9]{9}$/;
	if(!reg.test(_mobile.val())){
		flag = false;
		_mobile.addClass("focus_eor_ipt");
		if (_mobile.siblings("label")) {
			_mobile.siblings("label").remove();
		}
		_mobile.after($("<label class='r_msg'>手机号码不正确</label>"));
	}
	if(!flag){
		return flag;
	}
	$.ajax({
		   type: "get",
		   async: false,
		   url: _rootUrl + "/user/ismobileExist.htm",
		   data: "mobile="+_mobile.val(),
		   success: function(msg){
			   if(msg != "true"){
					_mobile.addClass("focus_eor_ipt");
					if (_mobile.siblings("label")) {
						_mobile.siblings("label").remove();
					}
					_mobile.after($("<label class='r_msg'>手机号码重复</label>"));
					flag = false;
					return;
			   }
		   }
	});
	return flag;
}

function sendMobileCode(){
	var flag = true;
	if(!checkMobile(flag)){
		return flag;
	}
	//发送按钮灰显，90秒后恢复
	var second = 90;
	var _send_button = $("#r_cod_a");
	_send_button.css("background-color", "#ccc");
	_send_button.css("color", "#666");
	
	var timer = window.setInterval(function(){
		_send_button.unbind("click");
		second = parseInt(second) - 1;
		_send_button.html(second+"秒后重新获取");
		if(parseInt(second) < 1){
			window.clearInterval(timer);//去掉定时器 
			_send_button.css("background-color", "#ff9900");
			_send_button.css("color", "#fff");
			_send_button.html("获取短信验证码");
			_send_button.bind("click", sendMobileCode);
		}
	}, 1000); //秒倒数
	$.ajax({
		   type: "post",
		   url: _rootUrl + "/user/sendMobileCode.htm",
		   data: "mobile="+$(":text[name='mobile']").val(),
		   success: function(){}
	});
}

function regist(){
	var flag = true;
	//判断所有的输入框是否填写了
	$("form input[class*='required']").each(function(){
		var _input = $(this);
		if($.trim(_input.val()) == ""){
			_input.addClass("focus_eor_ipt");
			if (_input.siblings("label")) {
				_input.siblings("label").remove();
			}
			if (_input.attr("name") == "code" || _input.attr("name") == "mobileCode") {
				_input.after($("<label class='r_msg_w'>不能为空</label>"));
			} else {
				_input.after($("<label class='r_msg'>不能为空</label>"));
			}
			
			flag = false;
		}
	});
	if(!flag){
		return;
	}
	
	//判断两次输入的密码是否相同
	var _password = $(":password[name='password']");
	var _passwordRepeat = $(":password[name='passwordRepeat']");
	if(_password.val() != _passwordRepeat.val()){
		flag = false;
		_passwordRepeat.addClass("focus_eor_ipt");
		if (_passwordRepeat.siblings("label")) {
			_passwordRepeat.siblings("label").remove();
		}
		_passwordRepeat.after($("<label class='r_msg'>两次输入不相同</label>"));
	}
	//判断用户名是否正确，是否重复
	var _userName = $(":text[name='userName']");
	//  
	var reg = /^[\u4E00-\u9FA5\uF900-\uFA2D5A-Za-z0-9-_]{6,20}$/;
	if(!reg.test(_userName.val())){
		_userName.addClass("focus_eor_ipt");
		if (_userName.siblings("label")) {
			_userName.siblings("label").remove();
		}
		_userName.after($("<label class='r_msg'>用户名格式不正确</label>"));
		flag = false;
	}
	if(!flag){
		return flag;
	}
	$.ajax({
		   type: "get",
		   async: false,
		   url: _rootUrl + "/user/isUserNameExist.htm",
		   data: "userName="+_userName.val(),
		   success: function(msg){
			   if(msg != "true"){
				    _userName.addClass("focus_eor_ipt");
					if (_userName.siblings("label")) {
						_userName.siblings("label").remove();
					}
					_userName.after($("<label class='r_msg'>用户名重复</label>"));
					flag = false;
					return;
			   }
		   }
	});
	//验证手机号码是否正确
	flag = checkMobile(flag);
	
	//验证验证码是否正确
	var _code = $(":text[name='code']");
	$.ajax({
		   type: "get",
		   async: false,
		   url: _rootUrl + "/user/isCodeCorrect.htm",
		   data: "code="+_code.val(),
		   success: function(msg){
			   if(msg != "true"){
				    _code.addClass("focus_eor_ipt");
					if (_code.siblings("label")) {
						_code.siblings("label").remove();
					}
					_code.after($("<label class='r_msg_w'>验证码不正确</label>"));
					flag = false;
					return;
			   }
		   }
	});
	
	//判断输入的密码格式是否满足要求
	//var reg = /^[0-9a-zA-Z]{6,16}$/;
	var reg = /^(((?=.*[0-9])(?=.*[a-zA-Z])|(?=.*[0-9])(?=.*[^\s0-9a-zA-Z])|(?=.*[a-zA-Z])(?=.*[^\s0-9a-zA-Z]))[^\s]{6,20})$/;
	if(!reg.test(_password.val())){
		flag = false;
		_password.addClass("focus_eor_ipt");
		if (_password.siblings("label")) {
			_password.siblings("label").remove();
		}
		_password.after($("<label class='r_msg'>密码必须是包含数字、字母、特殊字符至少两种的6-20位字符</label>"));
	}
	
	if(!flag){
		return flag;
	}
	
	//提交表单
	if(flag){
		var pswMD5 = hex_md5(_password.val());
		_password.val(pswMD5);
		_passwordRepeat.val(pswMD5);
		$.ajax({
			   type: "post",
			   async: false,
			   url: _rootUrl + "/user/registAjax.htm",
			   data: {
				   userName:_userName.val(),
				   password : pswMD5,
				   mobile:$(":text[name='mobile']").val()
			   },
			   success: function(msg){
				   if(msg == "true"){
					   showMsgHint("注册成功！","duigou");
					   _password.val("");
					   _passwordRepeat.val("");
					   _code.val("");
					   _userName.val("");
					   $("#r_mb_num").val("");
					   
				   }else{
					   showMsgHint("注册失败！","gantan");
				   }
			   }
		});
	}
}

function changeCodeImage() {
	$("#vd_img").attr("src", _rootUrl + "/img/validate.htm?"+Math.random());
}
