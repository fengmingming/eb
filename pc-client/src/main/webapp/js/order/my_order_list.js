
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
	//根据订单状态和时间进行查询
	$("#order_status").change(function(){
		var status = $(this).val();
		var timeType = $("#order_time").val();	
		if(status != 0){
			$("input[name='timeType']").val(timeType);
			$("input[name='status']").val(status);
			$("#page").val(1);
			$("#my_order_list").submit();			
		}else{
			$("input[name='timeType']").val(timeType);
			$("input[name='status']").val("");
			$("#page").val(1);
			$("#my_order_list").submit();
		}
	});	
	$("#order_time").change(function(){
		var timeType = $(this).val();
		var status = $("#order_status").val();	
		if(status != 0){
			$("input[name='timeType']").val(timeType);
			$("input[name='status']").val(status);
			$("#page").val(1);
			$("#my_order_list").submit();
		}else{
			$("input[name='timeType']").val(timeType);
			$("#page").val(1);
			$("#my_order_list").submit();
		}
	});
	//触发Enter事件
	$("#order_num").keyup(function(e){
		if(e.keyCode == 13){
			$(this).parent().next().children().click();
		}
	});
});
//根据订单编号进行查询【我的订单】	
function query_order_num(){
	var orderNum = $("#order_num").val();
	if($.trim(orderNum) == ""){
		showMsgHint("请输入查询关键词！","gantan");
		return ;
	}	
	$("input[name='orderNum']").val(orderNum);
	$("#page").val(1);
	$("#my_order_list").submit();
}
//确认收货
function confirmReceipt(pm,obj){
	var id = pm;
	showMsgCfmHint("提示","确认收货吗？","gantan",function(){
		$.post(_rootUrl + "/order/confirmReceipt.htm",{orderId:id},function(data){
			if(data.status == 5){
				$("#m_m_"+pm).html(data.content);
				$(obj).remove();
			}else{
				showMsgHint("操作失败！","gantan");
			}
		},"json");
	});
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
