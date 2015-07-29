<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<style type="text/css">
#msg_cfm_hint_div { width: 420px; position: fixed; left: 50%; top: 50%; margin-top: -100px; margin-left: -210px; border: solid 1px #c4c4c4; z-index: 2000; background: #fff; display: none;}
	#mchd_tit { font-size: 12px; height: 31px; line-height: 31px; text-indent: 10px; color: #666; background: #f5f5f5;}
		#mchd_tit_a { background: url("${staUrl}/images/index.png") no-repeat 0 -72px; float: right; height: 23px; margin-right: 3px; margin-top: 3px; width: 23px;}
	#mchd_con { min-height: 168px;}
	#mchd_con_img { background-image: url("${staUrl}/images/inner.png"); display: block; float: left; height: 32px; text-align: left; width: 55px; margin-left: 70px; margin-top: 30px;}
		.gantan { background-position: 0px -519px;}
		.duigou { background-position: 0px -487px;}
	#mchd_con_p { width: 230px; line-height: 24px; float: left; color: #666; margin-top: 36px; font-size: 14px; min-height: 48px;}
	
	#mchd_ul_op { width: 260px; margin: auto; padding: 10px 0px 20px 0px;}
	#mchd_ent_li, #mchd_can_li { float: left;}
		#mchd_ent_li a, #mchd_can_li a { display: block; width: 120px; height: 36px; line-height: 36px; font-size: 18px; color: #fff; text-align: center;}
		#mchd_ent_li a { background: #ff9a00;}
		#mchd_can_li a { background: #ccc; margin-left: 20px;}
</style>
<div id="msg_cfm_hint_div">
	<div id="mchd_tit">
		<span id="mchd_tit_sp"></span>
		<a id="mchd_tit_a" href="javascript:;"></a>
	</div>
	<div id="mchd_con">
		<ul class="clear">
			<li id="mchd_con_img" class="gantan"></li>
			<li id="mchd_con_p"></li>
		</ul>
		<ul class="clear" id="mchd_ul_op">
			<li id="mchd_ent_li"><a href="javascript:;">确&nbsp;&nbsp;&nbsp;认</a></li>
			<li id="mchd_can_li"><a href="javascript:;">取&nbsp;&nbsp;&nbsp;消</a></li>
		</ul>
	</div>
</div>
<script type="text/javascript">
	$(window).load(function() {
		setFilter();
		
		//showMsgCfmHint("提示", "请仔细核对客户信息，再完成确认！", "gantan", function() { console.log("执行确认操作！");});
		
		$("#mchd_tit_a, #mchd_can_li").click(function() {
			hideMsgCfmHint();
		});
	});
	
	var _msg_cfm_filter_div = $("#msg_filter_div"),
		_msg_cfm_hint_div = $("#msg_cfm_hint_div");
	function showMsgCfmHint(s_tit, s_html, s_img, callback) {
		$("#mchd_con_p").html(s_html);
		$("#mchd_tit_sp").html(s_tit);
		if (s_img) {
			$("#mchd_con_img").attr("class", "").addClass(s_img);
		}
		
		setFilter();
		_msg_cfm_filter_div.show();
		_msg_cfm_hint_div.show();
		
		if (callback) {
			$("#mchd_ent_li").unbind("click").click(function() {
				callback();
				hideMsgCfmHint();
			});
		}
	}
	
	function hideMsgCfmHint() {
		_msg_cfm_filter_div.hide();
		_msg_cfm_hint_div.hide();
	}
</script>
