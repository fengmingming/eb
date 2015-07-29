$(function() {
	
});
//取消订单
function cancleOrder(pm,obj){
	var orderId = pm;
	showMsgCfmHint("提示","确认取消订单吗？","gantan",function(){
		$.post(_rootUrl + "/order/cancleOrder.htm",{orderId : orderId},function(data){
			var result = eval(data);
			if(result == 1){
				//$(obj).remove();
				window.location.reload();
			}else if(result == 0){
				showMsgHint("取消失败！","gantan");
			}else{
				showMsgHint(result,"gantan");
			}
		},"text");
	});
}
//订单单个商品确认收货
function confirmOrderDetail(pm,obj){
	var detailId = pm;
	showMsgCfmHint("提示","确认单个商品收货吗？","gantan",function(){
		$.post(_rootUrl + "/order/confirmOrderDetail.htm",{detailId:detailId},function(data){
			var result = eval(data);
			if(result == 1){
				$(obj).remove();
			}else{
				showMsgHint("操作失败！","gantan");
			}
			
		},"text");		
	});
}
//订单确认收货
function confirmReceipt(pm,obj){
	var id = pm;
	showMsgCfmHint("提示","确认收货吗？","gantan",function(){
		$.post(_rootUrl + "/order/confirmReceipt.htm",{orderId:id},function(data){
			if(data.status == 5){
				//$(obj).remove();
				window.location.reload();
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