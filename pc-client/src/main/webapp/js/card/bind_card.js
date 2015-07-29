$(function(){
	$("#at_bc_fsm").bind("click", clickFunction);
	
	$("#captcha, #mobile, #ykt_num").focus(function() {
		var _t = $(this);
		_t.removeClass("focus_eor_ipt");
		_t.addClass("focus_ipt");
		_t.siblings("label").remove();
	}).blur(function() {
		$(this).removeClass("focus_ipt");
	});
	
	var _mobileCode = $(":text[name='mobileCode']");
	var _mobile_inp = $("#mobile");
	var _captcha_inp = $("#captcha");
	var _ykt_num_inp = $("#ykt_num");
	$("#at_bd_sub").click(function(){
		
		var flag = false;
		if ($.trim(_mobile_inp.val()) == '') {
			hintMsg1(_mobile_inp, "手机验证码不能为空");
			flag = true;
		}
		if ($.trim(_captcha_inp.val()) == '') {
			hintMsg1(_captcha_inp, "手机校验码不能为空");
			flag = true;
		}
		if ($.trim(_ykt_num_inp.val()) == '') {
			hintMsg1(_ykt_num_inp, "一卡通账号不能为空");
			flag = true;
		}
		
		$.ajax({
			   type: "post",
			   async: false,
			   url: _rootUrl + "/card/getUserByCardNum.htm",
			   data: { cardNum: $(".bind_card").val().toLowerCase() },
			   success: function(data){
				   var data = eval("("+data+")");
				   if(data.success && data.result != null){
					   showMsgHint("卡已被绑定","duigou");
					   flag = true;
				   }
			   }
		});
		if (flag) {
			return;
		}
		$.ajax({
			   type: "get",
			   async: false,
			   url: _rootUrl + "/user/isMobileCodeCorrect.htm",
			   data: {
				   mobileCode:$("#captcha").val() ,
				   mobile:$(":text[name='mobile']").val()
			   },
			   success: function(msg){
				   if(msg != "true"){
					    _mobileCode.addClass("focus_eor_ipt");
						if (_mobileCode.siblings("label")) {
							_mobileCode.siblings("label").remove();
						}
						hintMsg1(_mobileCode, "手机校验码不正确");
						return;
				   }else{
					   $.ajax({
						   type: "post",
						   async: false,
						   url: _rootUrl + "/card/bindCardNum.htm",
						   data: { mobile: $(":text[name='mobile']").val(), bindCardNum: $(".bind_card").val().toLowerCase() },
						   success: function(data){
							   var data = eval("("+data+")");
							   if(data.success){
								   if($("#at_bd_sub").text() == '绑定'){
									   showMsgHint("绑定成功","duigou");
								   }else{
									   showMsgHint("换卡成功","duigou");
								   }
								   
							   }
							  /* if(msg != "true"){
								   mobile.addClass("focus_eor_ipt");
									if (mobile.siblings("label")) {
										mobile.siblings("label").remove();
									}
									mobile.after($("<label class='r_msg'>手机号码重复</label>"));
									return;
							   }*/
						   }
					});
				   }
			   }
		});
		
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
					$("#at_bc_fsm").text(count+"重新发送");
					t = setInterval("intervalometer()",1000);
					$("#at_bc_fsm").unbind("click");
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
		$("#at_bc_fsm").text(count+"重新发送");
		t = setInterval("intervalometer()",1000);
		$("#at_bc_fsm").unbind("click");
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
		$("#at_bc_fsm").text(count+"重新发送");
	}else{
		clearInterval(t);
		$("#at_bc_fsm").text("重新发送");
		count = 60;
		$("#at_bc_fsm").bind("click", clickFunction);
	}		
}


//消息模板1
function hintMsg1(obj, str) {
	obj.siblings("label").remove();
	obj.addClass("focus_eor_ipt");
	obj.after($("<label class='r_msg'>" + str + "</label>"));
}