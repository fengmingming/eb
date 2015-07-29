$(function() {
	//对查询订单状态和时间进行初始化
	if($("input[name='time']").val() != ''){
		$("#order_time option").each(function(){
			if($(this).val() == $("#time").text()){
				$(this).attr("selected",true);
			}
		});
	}
	if($("input[name='status']").val() != ''){
		$("#order_status option").each(function(){
			if($(this).val() == $("#status").text()){
				$(this).attr("selected",true);
			}
		});
	}
	if($("input[name='orderType']").val() != ''){
		$("#order_type option").each(function(){
			if($(this).val() == $("#orderType").text()){
				$(this).attr("selected",true);
			}
		});
	}
	//客户/亭子签收弹出层
	var _f = $("<div id='_filter' style='width: " + $(window).width() + "px; height: " + $(document).height() + "px; position: absolute; top: 0px; left: 0px; opacity: 0.5; filter: alpha(opacity=50); background: #000; z-index: 1000; display: none;'></div>"),
	_b = $("body");
	_b.append(_f);
	var _m = $("<div id='_msgdiv' style='width: 420px; height: 200px; position: fixed; top: 50%; left: 50%; margin-top: -100px; margin-left: -210px; background: #fff; border: solid 1px #c4c4c4; z-index: 2000; display: none;'></div>"),
		_mt = $("<div style='height: 31px; font-size: 12px; line-height: 31px; font-weight: bold; text-indent: 10px; background: #f5f5f5;'>客户签收<a id='msgdiv_tit_a' style='float: right; width: 23px; height: 23px; background: url(../images/index.png) no-repeat 0px -72px; margin-top: 3px; margin-right: 3px;' href='javascript:;'></a></div>"),
		_mes = $("<ul class='clear' style='margin: 0px 53px; margin-top: 34px;'><li style='float: left; width: 37px; height: 32px; line-height: 32px; margin-right: 20px; background: url(../images/inner.png) no-repeat 0px -519px;'></li><li style='float: left; height: 32px; line-height: 32px; color: #666;'>请仔细核对客户信息，再完成确认！</li></ul>");
		_ul = $("<ul style='margin: 0px 80px; margin-top: 34px;' class='clear'><li style='float: left;'><a id='qr' style='display: block; width: 120px; height: 36px; line-height: 36px; text-align: center; color: #fff; background: #ff9a00;' href='javascript:;'>确认</a></li><li style='float: left; margin-left: 20px;'><a id='qx' style='display: block; width: 120px; height: 36px; line-height: 36px; text-align: center; color: #fff; background: #ccc;' href='javascript:;'>取消</a></li></ul>");
	_b.append(_m.append(_mt).append(_mes).append(_ul));
	function showMsgdiv() { _f.show(); _m.show();}
	function hideMsgdiv() { _f.hide(); _m.hide();}
	var _temp = "";
	$(".ptr_a_khqs").click(function() { showMsgdiv(); _temp = $(this);});
	_mt.find("a").click(function() { hideMsgdiv();});
	_ul.find("#qr").click(function() {
		var id = _temp.attr("orderId");
		if (id != "") {			
			$.post(_rootUrl + "/order/confirmReceipt.htm",{orderId:id},function(data){
				if(data.status == 5){
					_temp.attr("class","ptr_a_state2");
					_temp.html("已签收");
					_temp.closest(".pth_operate").prev().html("已完成");
					_temp.unbind("click");
					hideMsgdiv();
				}else{
					hideMsgdiv();
					showMsgHint("操作失败！","gantan");
					
				}
			},"json");			
		}
		
	});
	_ul.find("#qx").click(function() {
		hideMsgdiv();
	});
	//根据订单状态和时间进行查询
	$("#order_status").change(function(){		
		var status = $(this).val();
		var mobile = $("#mobile").val();
		var timeType = $("#order_time").val();	
		var orderType = $("#order_type").val();
		$("input[name='orderType']").val(orderType);
		if(status != 0){
			if($.trim(mobile) != ''){
				$("input[name='timeType']").val(timeType);
				$("input[name='status']").val(status);
				$("input[name='mobile']").val(mobile);
				$("#page").val(1);
				$("#pavilion_order").submit();
			}else{
				$("input[name='timeType']").val(timeType);
				$("input[name='status']").val(status);
				$("#page").val(1);
				$("#pavilion_order").submit();
			}							
		}else{
			if($.trim(mobile) != ''){
				$("input[name='timeType']").val(timeType);
				$("input[name='mobile']").val(mobile);
				$("input[name='status']").val("");
				$("#page").val(1);
				$("#pavilion_order").submit();	
			}else{
				$("input[name='timeType']").val(timeType);
				$("input[name='status']").val("");
				$("#page").val(1);
				$("#pavilion_order").submit();
			}	
		}
	});	
	$("#order_time").change(function(){		
		var timeType = $(this).val();
		var mobile = $("#mobile").val();
		var status = $("#order_status").val();		
		var orderType = $("#order_type").val();
		$("input[name='orderType']").val(orderType);
		if(status != 0){
			if($.trim(mobile) != ''){
				$("input[name='timeType']").val(timeType);
				$("input[name='status']").val(status);
				$("input[name='mobile']").val(mobile);
				$("#page").val(1);
				$("#pavilion_order").submit();
			}else{
				$("input[name='timeType']").val(timeType);
				$("input[name='status']").val(status);
				$("#page").val(1);
				$("#pavilion_order").submit();
			}	
		}else{
			if($.trim(mobile) != ''){
				$("input[name='timeType']").val(timeType);
				$("input[name='mobile']").val(mobile);
				$("#page").val(1);
				$("#pavilion_order").submit();
			}else{
				$("input[name='timeType']").val(timeType);
				$("#page").val(1);
				$("#pavilion_order").submit();
			}
		}
	});
	$("#order_type").change(function(){		
		var timeType = $("#order_time").val();
		var mobile = $("#mobile").val();
		var status = $("#order_status").val();	
		var orderType = $("#order_type").val();
		$("input[name='orderType']").val(orderType);
		if(status != 0){
			if($.trim(mobile) != ''){
				$("input[name='timeType']").val(timeType);
				$("input[name='status']").val(status);
				$("input[name='mobile']").val(mobile);
				$("#page").val(1);
				$("#pavilion_order").submit();
			}else{
				$("input[name='timeType']").val(timeType);
				$("input[name='status']").val(status);
				$("#page").val(1);
				$("#pavilion_order").submit();
			}	
		}else{
			if($.trim(mobile) != ''){
				$("input[name='timeType']").val(timeType);
				$("input[name='mobile']").val(mobile);
				$("#page").val(1);
				$("#pavilion_order").submit();
			}else{
				$("input[name='timeType']").val(timeType);
				$("#page").val(1);
				$("#pavilion_order").submit();
			}
		}
	});	
	//触发Enter事件
	$("#mobile").keyup(function(e){
		if(e.keyCode == 13){
			$(this).parent().next().children().click();
		}
	});
});
//根据订单编号或手机号进行查询
function query_order_mobile(){
	var mobile = $("#mobile").val();
	var status = $("#order_status").val();	
	var timeType = $("#order_time").val();	
	var orderType = $("#order_type").val();
	$("input[name='orderType']").val(orderType);
	if($.trim(mobile) == ""){
		showMsgHint("请输入查询关键词！","gantan");
		return ;
	}
	if(mobile.length == 11){
		if(status == 0){
			$("input[name='timeType']").val(timeType);
			$("input[name='mobile']").val(mobile);
			$("#page").val(1);
			$("#pavilion_order").submit();
		}else{
			$("input[name='timeType']").val(timeType);
			$("input[name='status']").val(status);
			$("input[name='mobile']").val(mobile);
			$("#page").val(1);
			$("#pavilion_order").submit();
		}
	}else{
		$("input[name='mobile']").val(mobile);
		$("#page").val(1);
		$("#pavilion_order").submit();
	}		
}
//付款
function orderPay(orderNum, payPrice, payType){
	if(payType == 4){
		showMsgHint("不支持微信支付！","gantan");
	}else if(payType == 1){
		open(_rootUrl+"/order/toPayment.htm?orderNum="+orderNum+"&payPrice="+payPrice,"_blank");
	}	
}
//退/换货
function returnOrder(){
	showMsgHint("请联系客服！4000365020","gantan");
}