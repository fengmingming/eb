<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <input type="button" value="test" id="test" /> -->
<script type="text/javascript">
!window.jQuery && document.write('<script src="js/common/jquery-1.8.0.min.js"><\/script>'); 
</script> 
<script type="text/javascript">
$(function() {
	var _f = $("<div id='_filter' style='width: " + $(window).width() + "px; height: " + $(document).height() + "px; position: absolute; top: 0px; left: 0px; opacity: 0.5; filter: alpha(opacity=50); background: #000; z-index: 1000; display: none;'></div>"),
		_b = $("body");
	_b.append(_f);
	
	$("#log_name, #log_pwd").focus(function() {
		$(this).removeClass("focus_eor_ipt");
		$(this).addClass("focus_ipt");
		$(this).siblings("label").remove();
	}).blur(function() {
		$(this).removeClass("focus_ipt");
	});
	
	var _log_name = $("#log_name"),
		_log_pwd = $("#log_pwd");
	
	$("#denglu").click(function() {
		var flag = true;
		if ($.trim(_log_name.val()) == "") {
			_log_name.addClass("focus_eor_ipt");
			if (_log_name.siblings("label")) {
				_log_name.siblings("label").remove();
			}
			_log_name.after($("<label class='log_msg'>不能为空</label>"));
			flag = false;
		}
		if ($.trim(_log_pwd.val()) == "") {
			_log_pwd.addClass("focus_eor_ipt");
			if (_log_pwd.siblings("label")) {
				_log_pwd.siblings("label").remove();
			}
			_log_pwd.after($("<label class='log_msg'>不能为空</label>"));
			flag = false;
		}
		if (!flag) {
			return false;
		}
		$(this).val("登录中......");
		
		var pswMD5 = hex_md5(_log_pwd.val());
		 $.post(_rootUrl + "/user/login.htm",{mobile:$("#log_name").val(), password:pswMD5},
			function (data, textStatus) {
			 	if (data.success) {
			 		$("#logdiv_tit_a").click();
			 		var user = eval(data.result);
			 		$("#dologin").parent("li").remove();
			 		$("#doregist").parent("li").remove();
			 		var htmlStr = "<span id='tp_username' style='cursor:hand;cursor:pointer;' onclick='toUserInfo();'>" + user.userName + "</span>" + "&nbsp;&nbsp;&nbsp;&nbsp;";
			 		htmlStr += "欢迎光临手拉手！&nbsp;";
			 		htmlStr += "<span id='tp_logout' style='cursor:hand;cursor:pointer;' onclick='logout();'>[退出]</span>";
			 		$("#welcome").html(htmlStr);
			 		if(data.result.memberType != 1){
			 			$("#myorder").remove();
			 		}
			 		//更改用户的城市
			 		if(typeof changeCity != "undefined"){
			 			changeCity(user.city);
			 		}
			 		//登录后的回调函数，如果登录后需要执行其他操作，需要自己实现afterLogin方法（如果有多个，可以通过设置afterLoginFlag区分）
			 		if(typeof afterLogin != "undefined"){
			 			afterLogin();
			 		}
			 		
			 		$("#tp_logout, #tp_username").hover(function() {
			 			$(this).addClass("tp_logout");
			 		}, function() {
			 			$(this).removeClass("tp_logout");
			 		});
			 	}else{
			 		_log_pwd.addClass("focus_eor_ipt");
					if (_log_pwd.siblings("label")) {
						_log_pwd.siblings("label").remove();
					}
					_log_pwd.after($("<label class='log_msg'>" + data.errMsg + "</label>"));
					$("#denglu").val("登  录");
			 	}
			},"json");
	});
	$("#logdiv_tit_a").click(function() {
		$("#_filter, #logdiv").hide();
		$("#denglu").val("登  录");
	});
	$("#test, #dologin").click(function() {
		_f .width($(document).width()).height($(document).height());
		$("#log_name").val("${lastUser }");
		$("#log_pwd").val("");
		$("#log_name, #log_pwd").removeClass("focus_eor_ipt");
		$(".log_msg").remove();
		$("#_filter, #logdiv").show();
	});
	$(window).resize(function() {
		if ($(window).width() <= 1200) {
			_f .width(1200).height($(document).height());
		} else {
			_f .width($(document).width()).height($(document).height());
		}
		
	});
	//回车提交
	$("#log_pwd").keyup(function(e) {
		if(e.keyCode == 13) {
			$("#denglu").click();
		} else if (e.keyCode == 27) {
			$("#logdiv_tit_a").click();
		}
	});
});
</script>
<style type="text/css">
	#logdiv { width: 410px; height: 334px; position: fixed; top: 50%; left: 50%; margin-top: -167px; margin-left: -205px; background: #fff; border: solid 1px #c4c4c4; z-index: 2000; display: none;}
		#logdiv_tit { height: 31px; font-size: 12px; line-height: 31px; font-weight: bold; text-indent: 10px; background: #f5f5f5;}
			#logdiv_tit_a { float: right; width: 23px; height: 23px; background: url(../images/index.png) no-repeat 0px -72px; margin-top: 3px; margin-right: 3px;}
		#logul { font-size: 12px; width: 270px; margin: auto; margin-top: 35px;}
			.logul_li { color: #c0c0c0; margin-bottom: 5px;}
			
			#logdiv .focus_ipt { border: solid 1px #7abd54;}
			#logdiv .focus_eor_ipt { border: solid 1px red;}
			.ll_p { position: relative;}
				.log_msg { position: absolute; top: 30px; left: 0px; border: solid 1px #ffbdbf; background: #ffebec; padding: 0px 3px; color: red; width: 262px;}
			
			#log_name { width: 233px; padding: 6px 30px 6px 5px; border: solid 1px #e1e1e1; background: url(../images/index.png) no-repeat 241px -96px; margin-bottom: 8px;}
			#log_pwd { width: 233px; padding: 6px 30px 6px 5px; border: solid 1px #e1e1e1; background: url(../images/index.png) no-repeat 242px -125px;}
				#logul_ul { padding: 12px 0px;}
				#logul_ul li { float: left; height: 30px; line-height: 30px;}
			#denglu { width: 270px; height: 35px; border: 0px; border-top: solid 1px #f7c47b; border-bottom: solid 1px #f7c47b; background: #f09007; color: #fff; font-size: 16px; cursor: pointer;}
</style>
<div id="logdiv">
	<div id="logdiv_tit">
		您尚未登录
		<a id="logdiv_tit_a" href='javascript:;'></a>
	</div>
	<ul id="logul">
		<li class="logul_li">用户名/已验证手机</li>
		<li class="ll_p"><input id="log_name" type="text" value="${lastUser }" maxlength="20"/></li>
		<li class="logul_li">密码</li>
		<li class="ll_p"><input id="log_pwd" type="password" maxlength="16"/></li>
		<li>
			<ul id="logul_ul" class="clear">
				<!-- <li style="height: auto; line-height: normal; margin: 9px 19px 0px 10px;"><input type="checkbox" /></li>
				<li style="padding-right: 10px;">自动登陆</li> -->
				<li style="padding-right: 35px; color: #005aa4;"><a href="${rootUrl }/account/resetPwd1.htm" style="color: #005aa4;">忘记密码？</a></li>
				<li><a href="${rootUrl }/user/toRegist.htm" style="color: #005aa4;">点击注册</a></li>
			</ul>
		</li>
		<li><input type="button" id="denglu" value="登&nbsp;&nbsp;录" /></li>
	</ul>
</div>
