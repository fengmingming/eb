$(function() {
		function bindGidv_dClick() {
			$(".gidv_d").unbind("click").click(function() {
				//$(this).parent().remove();
			});
		}
		bindGidv_dClick();
		
		$("#exchange").click(function(){
			$("input[name='tkfs']").attr("checked",false);
			$(".gt4_ul4").hide();
		});
		$("#retreat").click(function(){
			$(".gt4_ul4").show();
			if($("#payCode").val() != '1'){
				$("#tzye").attr("checked",true);
				$("#tzfkzh_li").hide();
			}else{
				$("#tzfkzh").attr("checked",true);
				$("#tzfkzh_li").show();
			}
		});
		if($("#exchange")[0].checked){
			$(".gt4_ul4").hide();
		}else{
			$(".gt4_ul4").show();
		}
		
//		$("#g45_detail").val($("#g45_detail_den").val());
		if($("#id").val()==""){
			restoreValue_d($("#daprovince").val(), $("#dacity").val(), $("#dadistrict").val(), $("#dacommunity").val(), $("#dapavilionId").val(), $("#dapavilionCode").val());
			
			restoreValue($("#daprovince").val(), $("#dacity").val(), $("#dadistrict").val(), $("#dacommunity").val(), $("#dapavilionId").val(), $("#dapavilionCode").val());
		}else{
			restoreValue_d($("#daprovinceT").val(), $("#dacityT").val(), $("#dadistrictT").val(), $("#dacommunityT").val(), $("#dapavilionIdT").val(), $("#dapavilionCodeT").val());
			
			restoreValue($("#daprovinceF").val(), $("#dacityF").val(), $("#dadistrictF").val(), $("#dacommunityF").val(), $("#dapavilionIdF").val(), $("#dapavilionCodeF").val());
		}
		$(".save").click(function(){
			if($("#retreat")[0].checked){
				if(!($("#tzye")[0].checked||$("#tzfkzh")[0].checked)){
					showMsgHint("请选择退款方式","gantan");
					return false;
				}
			}
			if($.trim($("#remark").val())==""){
				showMsgHint("问题描述不能为空","gantan");
				return false;
			}
			if($.trim($("#receiverT").val())==""){
				showMsgHint("取货人姓名不能为空","gantan");
				return false;
			}
			if($.trim($("#receiverF").val())==""){
				showMsgHint("收货人姓名不能为空","gantan");
				return false;
			}
			//判断手机号码是否正确
			var _mobileT=$("#mobileT");
			if($.trim(_mobileT.val())== ""){
				showMsgHint("取货人手机号码不能为空","gantan");
				return false;
			}
			if($.trim(_mobileT.val()) != ""){
				var reg = /^1[3|5|7|8|][0-9]{9}$/;
				if(!reg.test($.trim(_mobileT.val()))){
					showMsgHint("请正确填写取货人手机号码","gantan");
					return false;
				}
			}
			var _mobileF=$("#mobileF");
			if($.trim(_mobileF.val())== ""){
				showMsgHint("收货人手机号码不能为空","gantan");
				return false;		
			}
			if($.trim(_mobileF.val()) != ""){
				var reg = /^1[3|5|7|8|][0-9]{9}$/;
				if(!reg.test($.trim(_mobileF.val()))){
					showMsgHint("请正确填写收货人手机号码","gantan");
					return false;
				}
			}
			if($("#province_d").val()=="" || $("#city_d").val()=="" || $("#district_d").val()=="" || $("#community_d").val()=="" || $("#pavilionId_d").val()==""){
				showMsgHint("请填写取货地址","gantan");
				return false;
			}
			if(!($("#shdfwt")[0].checked)){
				if($("#g45_detail_d").val()==""){
					showMsgHint("请填写取货详细地址","gantan");
					return false;
				}
			}
			if($("#province").val()=="" || $("#city").val()=="" || $("#district").val()=="" || $("#community").val()=="" || $("#pavilionId").val()==""){
				showMsgHint("请填写收货地址","gantan");
				return false;
			}
			if(!($("#dfwtzt")[0].checked)){
				if($("#g45_detail").val()==""){
					showMsgHint("请填写收货详细地址","gantan");
					return false;
				}
			}
			if(!($("#retreat")[0].checked || $("#exchange")[0].checked)){
				showMsgHint("请选择“退货”或者“换货”","gantan");
				return false;
			}
			if($("#payCode").val() != '1'){
				$("#tzye").attr("checked",true);
			}else{
				$("#tzfkzh").attr("checked",true);
			}
			var deliveryTypeT;
			if($("#dfwtzt")[0].checked){
				deliveryTypeT = 1;
			}else{
				deliveryTypeT = 2;
			}
			var pickupWayT;
			if($("#shdfwt")[0].checked){
				pickupWayT = 1;
			}else{
				pickupWayT = 2;
			}
			$.ajax({
				   type: "post",
				   async: false,
				   contentType:"application/x-www-form-urlencoded;charset=UTF-8",
				   url: _rootUrl + "/order/saveReturnGoods.htm",
				   data:{
					   orderDetailId:$("#orderDetailId").val(),
					   type:$("input[name='servetype']:checked").val(),
					   moneyWay:$("input[name='tkfs']:checked").val(),
					   remark:$("#remark").val(),
					   photoUrl1:$("#photoUrl1_h").val(),
					   photoUrl2:$("#photoUrl2_h").val(),
					   photoUrl3:$("#photoUrl3_h").val(),
					   pickupWay:pickupWayT,
					   deliveryType:deliveryTypeT,
					   receiverT:$("#receiverT").val(),
					   mobileT:$("#mobileT").val(),
				   		remarkT:$("#g45_detail_d").val(),
				   		provinceIdT:$("#province_d").val(),
				   		cityIdT:$("#city_d").val(),
				   		districtIdT:$("#district_d").val(),
				   		communityIdT:$("#community_d").val(),
				   		pavilionIdT:$("#pavilionId_d").val(),
				   		provinceIdF:$("#province").val(),
				   		cityIdF:$("#city").val(),
				   		districtIdF:$("#district").val(),
				   		communityIdF:$("#community").val(),
				   		pavilionIdF:$("#pavilionId").val(),
				   		remarkF:$("#g45_detail").val(),
				   		receiverF:$("#receiverF").val(),
				   		mobileF:$("#mobileF").val()
				   		//pavilionCode:$(":hidden[name='pavilionCode']").val()
				   },
				   success: function(result){
					   var result = eval("("+result+")");
					   if(result.success){
							 showMsgHint("申请成功","duigou",function (){window.location.reload()});
							 //window.location.reload();  
					   }else{
						   showMsgHint("申请失败","gantan");
					   }
				   }
			});
			
		
	});
		$(".cancel").click(function(){
			$.ajax({
				   type: "post",
				   async: false,
				   contentType:"application/x-www-form-urlencoded;charset=UTF-8",
				   url: _rootUrl + "/order/cancleReturnGoods.htm",
				   data:{
					   orderDetailId:$("#orderDetailId").val(),
					   orderId:$("#orderId").val()
				   },
				   success: function(data){
					   var data = eval("("+data+")");
					   if(data.success){
							 showMsgHint("取消成功","duigou",function (){window.location.reload()});
							// window.location.reload();
					   }else{
						   showMsgHint("取消失败","gantan");
					   }
				   }
			});
	});
	
	$(".photoUrl1_h_del").click(function(){
		showMsgCfmHint("提示", "确定删除？", "gantan", function(){
			$.ajax({
				   type: "post",
				   async: false,
				   contentType:"application/x-www-form-urlencoded;charset=UTF-8",
				   url: _rootUrl + "/order/deleteImg.htm",
				   data:{
					   token:document.getElementById('sid').value,
					   uri:$("#photoUrl1_h").val()
				   },
				   success: function(data){
					   var data = eval("("+data+")");
					   if(data.success){
						   $("#photoUrl1").attr("src","").hide();
							$("#photoUrl1_h").val("");
					   }else{
						   showMsgHint("删除失败","gantan");
					   }
				   }
			});
		});
	});	
	$(".photoUrl2_h_del").click(function(){
		showMsgCfmHint("提示", "确定删除？", "gantan", function(){
			$.ajax({
				   type: "post",
				   async: false,
				   contentType:"application/x-www-form-urlencoded;charset=UTF-8",
				   url: _rootUrl + "/order/deleteImg.htm",
				   data:{
					   token:document.getElementById('sid').value,
					   uri:$("#photoUrl2_h").val()
				   },
				   success: function(data){
					   var data = eval("("+data+")");
					   if(data.success){
						   $("#photoUrl2").attr("src","").hide();
							$("#photoUrl2_h").val("");
					   }else{
						   showMsgHint("删除失败","gantan");
					   }
				   }
			});
		});
	});
	$(".photoUrl3_h_del").click(function(){
		showMsgCfmHint("提示", "确定删除？", "gantan", function(){
			$.ajax({
				   type: "post",
				   async: false,
				   contentType:"application/x-www-form-urlencoded;charset=UTF-8",
				   url: _rootUrl + "/order/deleteImg.htm",
				   data:{
					   token:document.getElementById('sid').value,
					   uri:$("#photoUrl3_h").val()
				   },
				   success: function(data){
					   var data = eval("("+data+")");
					   if(data.success){
						   $("#photoUrl3").attr("src","").hide();
							$("#photoUrl3_h").val("");
					   }else{
						   showMsgHint("删除失败","gantan");
					   }
				   }
			});
		});
	});
		
		
});		
