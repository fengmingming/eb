<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<style type="text/css">
#msg_filter_div { opacity: 0.5; filter: alpha(opacity=50); background: #000; position: absolute; top: 0px; left: 0px; z-index: 1000; display: none;}
#msg_hint_div { width: 400px; position: fixed; left: 50%; top: 50%; margin-top: -100px; margin-left: -200px; border: solid 1px #c4c4c4; z-index: 2000; background: #fff; display: none;}
	#mhd_tit { font-size: 12px; height: 31px; line-height: 31px; text-indent: 10px; color: #666; background: #f5f5f5;}
		#mhd_tit_a { background: url("${staUrl}/images/index.png") no-repeat 0 -72px; float: right; height: 23px; margin-right: 3px; margin-top: 3px; width: 23px;}
	#mhd_con { min-height: 168px;}	
	#mhd_con_img { background-image: url("${staUrl}/images/inner.png"); display: block; float: left; height: 32px; text-align: left; width: 55px; margin-left: 80px; margin-top: 30px;}
		.gantan { background-position: 0px -519px;}
		.duigou { background-position: 0px -487px;}
	#mhd_con_p { width: 170px; line-height: 24px; float: left; color: #666; margin-top: 36px; font-size: 14px;}
		#mhd_con_p a { font-size: 12px; color: #3f658c;}
		#mhd_con_p a:hover { text-decoration: underline;}
	#mhd_ent { display: block; width: 120px; height: 36px; line-height: 36px; font-size: 18px; color: #fff; text-align: center; background: #ff9a00; margin: 10px auto 25px auto;}
</style>
<div id="msg_filter_div"></div>
<div id="msg_hint_div">
	<div id="mhd_tit">
		提示
		<a id="mhd_tit_a" href="javascript:;"></a>
	</div>
	<div id="mhd_con">
		<ul class="clear">
			<li id="mhd_con_img" class="gantan"></li>
			<li id="mhd_con_p"></li>
		</ul>
		<a id="mhd_ent" href="javascript:;">确&nbsp;&nbsp;&nbsp;认</a>
	</div>
</div>
<script type="text/javascript">
	$(window).load(function() {
		//init
		setFilter();
		
		$(window).resize(function() {
			setFilter();
		});
		
		//showMsgHint("已关注过该商品！<br /><a href='javascript:;'>查看我的关注&gt;&gt;</a>");//test
		
		$("#mhd_tit_a").click(function() {
			hideMsgHint();
		});
		
	});
	var _msg_filter_div = $("#msg_filter_div"),
		_msg_hint_div = $("#msg_hint_div");
	function setFilter() {
		_msg_filter_div.width($(document).width());
		_msg_filter_div.height($(document).height());
	}
	
	function showMsgHint(s_html, s_img, callback) {
		$("#mhd_con_p").html(s_html);
		if (s_img) {
			$("#mhd_con_img").attr("class", "").addClass(s_img);
		}
		
		setFilter();
		_msg_filter_div.show();
		_msg_hint_div.show();
		
		//绑定“确认按钮”的回调函数，默认是关掉弹出框
		if (callback) {
			$("#mhd_ent").click(function() {
				callback();
				hideMsgHint();
			});
		} else {
			$("#mhd_ent").click(function() {
				hideMsgHint();
			});
		}
	}
	function hideMsgHint() {
		_msg_filter_div.hide();
		_msg_hint_div.hide();
	}
</script>
