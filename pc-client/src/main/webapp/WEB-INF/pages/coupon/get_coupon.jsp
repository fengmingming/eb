<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
	$(function() {
		$("#gt_c_a").click(function(){
			var code = $("#code").val();
			if($.trim(code) == ''){
				showMsgHint("请输入优惠券码！","gantan");
			}else{
				$.post(_rootUrl+"/coupon/getCouponByCode.htm",{code:code},function(data){
					if(data){
						showMsgHint("领取成功！请刷新页面！","duigou");
					}else{
						showMsgHint("优惠码错误或优惠券已过期！","gantan");
					}
				},"json");
			}
		});
	});
	
</script>
<style type="text/css">
	#gt_c { }
		#gt_c_tit { padding-top: 16px; padding-left: 20px; color: #666;}
		#gt_c ul { width: 480px; margin: auto;}
		#gt_c ul li { float: left; height: 33px; line-height: 32px;}
		#gt_c ul li input { border: 1px solid #e1e1e1; padding: 6px 5px; width: 255px;}
		#gt_c_a { display: block; width: 85px; height: 33px; font-size: 12px; color: #fff; background: #ff7f00; text-align: center; margin-left: 20px;}
</style>
<div id="gt_c">
	<div id="gt_c_tit">我的优惠券</div>
	<ul class="clear">
		<li style="font-size: 14px; color: #333; padding-right: 20px;">请输入优惠码</li>
		<li><input type="text" id="code"/></li>
		<li><a id="gt_c_a" href="javascript:;" >领取优惠券</a></li>
	</ul>
	<div style="height: 10px; background: #f5f5f5; margin-top: 15px;"></div>
</div>
