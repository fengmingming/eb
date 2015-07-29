$(function() {
	getDefaultAddress();
	
	//选中事件
	$(".co_shfs .goods_check").click(function() {
		var _this = $(this);
		$(".co_shfs .goods_check").removeClass("gc_y").addClass("gc_n");
		_this.removeClass("gc_n").addClass("gc_y");
	});
	
	//快递送货点击
	$("#kdsh_a").click(function() {
		//$(".ztd_ul").hide();
		//$("#pavilion_ul").hide();
		//$("#def_address .co_pavi").hide();
		$(".pop_div").css("margin-top", -($(".pop_div").outerHeight() / 2));
	});
	//自提点自提点击
	$("#ztdzt_a").click(function() {
		//$(".ztd_ul").show();
		//$("#pavilion_ul").show();
		//$("#def_address .co_pavi").show();
		$(".pop_div").css("margin-top", -($(".pop_div").outerHeight() / 2));
	});
	$("#kdsh_a").click();//默认选中快递送货
	
	//收货人弹出层取消点击
	$(".co_cancel").click(function(){
		$(".filter_div").hide();
		$(".pop_div").hide();
	});
	//收货地址弹出层确认点击
	$(".co_ent").click(function() {
		var addressId = $("#edit_address_id").val();
		if(!checkInput(addressId)){
			return;
		}
		var style = "";
//		if($("#kdsh_a").attr("class").indexOf("gc_y") != -1){
//			  style = "style='display:none'";
//		}
		//修改地址信息，并设置为默认地址
		var receiver = $(":text[name='receiver']").val();
		var mobile = $(":text[name='mobile']").val();
		var addressDetail = $(":text[name='addressDetail']").val();
		$.ajax({
			   type: "post",
			   async: false,
			   data: {
				   		id:addressId,
				   		receiver:receiver,
				   		mobile:mobile,
				   		addressDetail:addressDetail,
				   		province:$("select[name='province']").val(),
				   		city:$("select[name='city']").val(),
				   		district:$("select[name='district']").val(),
				   		community:$("select[name='community']").val(),
				   		pavilionId:$("select[name='pavilionId']").val(),
				   		pavilionCode:$(":hidden[name='pavilionCode']").val()
			   		 },
			   url: _rootUrl + "/address/saveOrUpdateAddress.htm",
			   success: function(result){
				   var address = eval("("+result+")");
				   if(address.success){
					   //修改地址列表
					   var html = "";
					   if(addressId != ""){
						   var _ul = $("a[class*='goods_check'][id='"+addressId+"']").parents("ul[class*='ua_ul']");
						   _ul.find(".ua_li_ul_ads").html(addressDetail);
						   _ul.find(".ua_li_ul_rec").html(receiver +"&nbsp;&nbsp;"+ mobile);
						   $(".viewport2").children().each(function(){
							   $(this).children().eq(0).find('a').removeClass('gc_y').addClass('gc_n');
						   });
						   $("#"+addressId).removeClass('gc_n').addClass('gc_y');						   
						   if(address.result.pavilionName != "" && address.result.pavilionPhone != ""){
							   _ul.find(".ua_li_ul_pavi").show().html(address.result.pavilionName +
					   			  "&nbsp;&nbsp;" + address.result.pavilionPhone);
						   }else{
							   _ul.find(".ua_li_ul_pavi").hide().html("");
						   }
					   }else{						   
						   html = "<ul class='clear ua_ul'>" +
						   			  "<li class='ua_li'><a href='javascript:;' class='goods_check gc_y' id='"+address.result.id+"'></a></li>"+
						   			  "<li class='ua_li'><ul class='ua_li_ul'>"+
						   			  "<li class='ua_li_ul_pavi' "+style+">"+address.result.pavilionName+
				   			  		  "&nbsp;&nbsp;"+address.result.pavilionPhone+"</li>";
						   html += "<li class='ua_li_ul_ads'>"+address.result.addressDetail+"</li>";
						   html += "<li class='ua_li_ul_rec'>"+address.result.receiver+"&nbsp;&nbsp;"+address.result.mobile+"</li>";
						   html +="</ul></li>";
						   html +="<li class='ua_li' style='float: right;'><a class='ua_li_edit' href='javascript:;'>编辑</a></li>";
						   html +="<li class='ua_li' style='float: right;'><a class='ua_li_del' href='javascript:;'>删除</a></li></ul>";
						   $(".viewport2 .co_shdz").before(html);
						   $(".viewport2").children().each(function(){
							   $(this).children().eq(0).find('a').removeClass('gc_y').addClass('gc_n');
						   });
						   bindEditAndDeleteAndCheckAction();
					   }
					   $(".viewport .co_shdz").hide();
					   html = "<li>"+address.result.receiver +"&nbsp;"+address.result.mobile+"</li>";
					   html += "<li class='co_ads'>" + address.result.addressDetail + "</li>";
					   html += "<li class='co_ads'><span class='co_pavi' "+style+">" + address.result.pavilionName + "&nbsp;&nbsp;" + address.result.pavilionPhone + "</span>&nbsp;</li></ul>";
					   html += "<li><a href='javascript:;' class='csd_jt'></a></li>";
					   $("#def_address").html(html).show();
				   }else{
					   alert("保存收货人信息出现错误！");
				   }
			   }
		});
		$(".filter_div").hide();
		$(".pop_div").hide();
	});
	
	//余额支付点击
	$("#yezf_a").click(function() {
		var _this = $(this);
		if (_this.attr("class").indexOf("gc_y") != -1) {
			clearZfState();
			_this.removeClass("gc_y").addClass("gc_n");
			$(".co_yezf").hide();
		} else if (_this.attr("class").indexOf("gc_n") != -1) {
			clearZfState();
			_this.removeClass("gc_n").addClass("gc_y");
			$(".co_yezf").show();
		}
		
		$("#zfbzf_a").addClass("gc_n");
		$(".co_zfbzf_div").hide();
		$("#wxzf_a").addClass("gc_n");
		$(".co_wxzf_div").hide();
	});
	
	//支付宝支付点击
	$("#zfbzf_a").click(function() {
		var _this = $(this);
		if (_this.attr("class").indexOf("gc_y") != -1) {
			clearZfState();
			_this.removeClass("gc_y").addClass("gc_n");
			$(".co_zfbzf_div").hide();
		} else if (_this.attr("class").indexOf("gc_n") != -1) {
			clearZfState();
			_this.removeClass("gc_n").addClass("gc_y");
			$(".co_zfbzf_div").show();
		}
		
		$("#yezf_a").addClass("gc_n");
		$(".co_yezf").hide();
		$("#wxzf_a").addClass("gc_n");
		$(".co_wxzf_div").hide();
	});
	
	//判断是否是移动设备打开，是否在微信中打开
	//if (isMobile() && isMicroMessenger()) {
		//隐藏支付宝支付
		//$("#zfbzf_a").parent("li").hide();
		//微信支付点击
		$("#wxzf_a").click(function() {
			var _this = $(this);
			if (_this.attr("class").indexOf("gc_y") != -1) {
				clearZfState();
				_this.removeClass("gc_y").addClass("gc_n");
				$(".co_wxzf_div").hide();
			} else if (_this.attr("class").indexOf("gc_n") != -1) {
				clearZfState();
				_this.removeClass("gc_n").addClass("gc_y");
				$(".co_wxzf_div").show();
			}
			$("#yezf_a").addClass("gc_n");
			$(".co_yezf").hide();
			$("#zfbzf_a").addClass("gc_n");
			$(".co_zfbzf_div").hide();
		});		
	//}else{
		//$("#wxzf_a").parent("li").hide();
	//}
	
	//清除支付点击状态
	function clearZfState() {
		$(".co_zffs .goods_check").removeClass("gc_y").removeClass("gc_n");
	}
	
	//收货人详细地址点击
	$(".co_shdz_d").click(function() {
		$(".viewport").slideUp();
		$(".viewport2").show();
	});
	//选择收货人点击
	$(".co_sel").click(function() {
		$(".viewport").slideUp();
		$(".viewport2").show();
	});
	//viewport2板块的返回点击
	$("#co_to_viewport1").click(function() {
		$(".viewport").slideDown();
		$(".viewport2").hide();
	});
	
	//新增收货地址“+”号点击
	$(".pop_div").css("margin-top", -($(".pop_div").outerHeight() / 2));
	$(".co_add, .co_add2").click(function() {
		clearform();
		var flag = canAddAddress();
		if(!flag){
			return flag;
		}
		$(".filter_div").height($(document).height()).show();
		$(".pop_div").show();
	});
	//绑定勾选地址、编辑地址、删除地址的点击事件
	bindEditAndDeleteAndCheckAction();
	
	//优惠券模块
	var _viewport3 = $(".viewport3");
	adaptSize();
	$(window).resize(function() {
		adaptSize();
	});
	function adaptSize() {
		if ($(window).width() > 640) {
			_viewport3.css({"margin-left":"-320px", "left":"50%"});
		} else {
			_viewport3.css({"margin-left":"0px", "left":"0px"});
		}
	}
	//优惠券点击
	$(".co_yhq").click(function() {
		$(".viewport").fadeOut();
		_viewport3.fadeIn();
	});
	//优惠券列表层“返回点击”
	$("#vp_yhq_bk").click(function() {
		$(".viewport").fadeIn();
		_viewport3.fadeOut();
	});
	//优惠券选中点击
	$(".yhq_divs .goods_check").click(function() {
		var _this = $(this);
		$(".yhq_divs .goods_check").removeClass("gc_y").addClass("gc_n");
		_this.removeClass("gc_n").addClass("gc_y");
	});
	//优惠券列表“确认按钮”点击
	$("#yhq_ent").click(function() {
		var _userCouponId = _viewport3.find("a[class*='goods_check gc_y']");
		var userCouponId = _userCouponId.attr("id");
		var _total = $("#orderTotal");
		var _discountByCoupon = $("#discountByCoupon");
		var _co_yhq_txt = $("#co_yhq_txt");
		if(userCouponId != ""){
			var parValue = _userCouponId.siblings(":hidden").val();
			var payTotal = (parseFloat(total)-parseFloat(parValue)).toFixed(2);
			var discountByCoupon = parseFloat(parValue).toFixed(2);
			if(payTotal < 0){
				payTotal = "0.00";
				discountByCoupon = total;
			}
			_total.html("￥" + payTotal);
			_discountByCoupon.html("-￥" + discountByCoupon);
			$("#yhq_price").show();//显示优惠价格
			_co_yhq_txt.html("已抵用" + discountByCoupon + "元");
		}else{
			_total.html("￥" + total);
			_discountByCoupon.html("-￥0.00");
			$("#yhq_price").hide();//显示优惠价格
			_co_yhq_txt.html("已抵用0.00元");
		}
		$(".viewport").fadeIn();
		_viewport3.fadeOut();
	});
	
	//提交订单
	$("a[id*='submit_od_']").click(function(){
		var flag = true;
		//判断是否有默认地址
		$.ajax({
			   type: "get",
			   async: false,
			   cache: false,
			   url: _rootUrl + "/address/defaultAddress.htm",
			   dataType: "json",
			   success: function(result){
				   if (result.status == "302") {
					   location.href = result.location;
					   return ;
			        }
				   var address = result;
				   //var address = eval("("+result+")");
				   if(!address.success){
					   flag = false;
					   alert("请选择收货人信息！");
				   }else{
					   address = address.result;
					   if($("#kdsh_a").attr("class").indexOf("gc_y") != -1 && (address.province == null||address.city == null||address.district == null||address.mobile == null||address.receiver == null) ||
							   $("#ztdzt_a").attr("class").indexOf("gc_y") != -1 && (address.province == null||address.city == null||address.district == null||address.community==null||address.pavilionId==null||address.mobile == null||address.receiver == null)){
						   flag = false;
						   alert("收货人信息不全，请选择自提点或者填写收货地址！");
					   }
					   
				   }
				   
			   }
		});
		if(!flag){
			return;
		}
		
		var payType = 2;
		var payBntId = "#submit_od_ye";
		//支付宝支付
		if($("#zfbzf_a").attr("class").indexOf("gc_y") != -1){
			payType = 1;
			payBntId = "#submit_od_zfb";
		}
		//微信支付
		if($("#wxzf_a").attr("class").indexOf("gc_y") != -1){
			payType = 4;
			payBntId = "#submit_od_wx";
		}
		var deliveryType = 2;
		if($("#ztdzt_a").attr("class").indexOf("gc_y") != -1){
			deliveryType = 1;
		}
		
		//余额支付，判断是否输入支付密码
		var _password = $(":password[name='password']");
		if(payType == 2){
			if($.trim(_password.val()) == ""){
				alert("支付密码不能为空！");
			   return;
			}
		}
		$(payBntId).hide().after($("<span style='color: red; float: right; margin: 20px;'>正在提交，请稍候！</span>"));//点击提交订单后的等待消息
		//ajax提交订单，跳转到订单成功页面
		var orderId = "";
		var orderNum = "";
		var payPrice = "";
		var appid = "";
		var pswMD5 = hex_md5(_password.val());
		var userCouponId = _viewport3.find("a[class*='goods_check gc_y']").attr("id");
		$.ajax({
			   type: "post",
			   async: false,
			   data:{
				   payType:payType,
				   deliveryType:deliveryType,
				   userCouponId:userCouponId,
				   payPassword:pswMD5
			   },
			   url: _rootUrl + "/order/commitOrder.htm",
			   success: function(data){
				   var order = eval("("+data+")");
				   if(!order.success){
					   if(order.errCode == 9999){
						   window.location.href = _rootUrl + "/main/error.htm";
					   }
					   alert(order.errMsg);
					   flag = false;
				   }else{
					   orderId = order.result.orderId;
					   orderNum = order.result.orderNum;
					   payPrice = order.result.payPrice;
					   appid = order.result.cart;
				   }
			   },
			   
		});
		if(!flag){
			$(payBntId).siblings("span").remove();
			$(payBntId).show();
			return;
		}
		if(payType == 2){
			//跳转到支付结果页面
			window.location.href = _rootUrl + "/order/paymentResult.htm?orderId="+orderId+"&orderNum="+orderNum;
		}
		if(payType == 1){
			//跳转到支付宝支付页面
			window.location.href = _rootUrl + "/pcenter/pay.htm?orderId="+orderId+"&orderNum="+orderNum+"&money="+payPrice;
		}
		if(payType == 4){
			//获取code，跳转到微信支付页面			
			if($.trim(payPrice) == '' || $.trim(orderNum) == '' || $.trim(orderId) == ''){
				window.location.href = _rootUrl + "/main/error.htm";
			}else{
				var STATE = '{"orderId":"'+orderId+'","total_fee":'+payPrice+',"order_num":"'+orderNum+'"}';
				window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri=http://www.365020.com/mobile/wx/getOpenid.htm?showwxpaytitle=1&response_type=code&scope=snsapi_base&state="+STATE+"#wechat_redirect";
			}			
			//window.location.href = "http://www.baidu.com";
		}	
		window.event.returnValue=false;
	});
});

function getDefaultAddress(){
	$.ajax({
		   type: "get",
		   cache: false,
		   url: _rootUrl + "/address/defaultAddress.htm",
		   success: function(data){
			   var address = eval("("+data+")");
			   if(address.success){
				   var html = "";
				   if(address.result != null){
					   $(".viewport .co_shdz").hide();
					   html += "<li>"+address.result.receiver +"&nbsp;"+address.result.mobile+"</li>";
					   html += "<li class='co_ads'>" + address.result.addressDetail + "</li>";
					   html += "<li class='co_ads'><span class='co_pavi'>" + address.result.pavilionName + "&nbsp;&nbsp;" + address.result.pavilionPhone + "</span>&nbsp;</li></ul>";
					   html += "<li><a href='javascript:;' class='csd_jt'></a></li>";
					   $("#def_address").html(html).show();
				   }
			   }
		   }
	});
}

function checkInput(addressId){
	var flag = true;
	//判断是否可以添加新地址
	if(addressId == null || addressId == ""){
		var flag = canAddAddress();
		if(!flag){
			return flag;
		}
	}
	//判断所有的输入框是否填写了
	$("input[class*='required']").each(function(){
		var _input = $(this);
		if($.trim(_input.val()) == ""){
			flag = false;
		}
	});
	if(!flag){
		alert("收货人、手机号码、详细地址不能为空");
		return flag;
	}
	//判断选择框是否填写了
//	if($("#ztdzt_a").attr("class").indexOf("gc_y") != -1){
//		if($("#province").val() == "" || $("#city").val() == "" || $("#district").val() == "" ||
//				$("#community").val() == "" ||$("#pavilionId").val() == ""){
//			flag = false;
//			alert("请选择自提点");
//		}
//	}else{
//		if($("#province").val() == "" || $("#city").val() == "" || $("#district").val() == ""){
//			flag = false;
//			alert("请选择省市区");
//		}
//	}
	if($("#province").val() == "" || $("#city").val() == "" || $("#district").val() == "" ||
		$("#community").val() == "" ||$("#pavilionId").val() == ""){
		flag = false;
		alert("请选择自提点");
	}
	if(!flag){
		return flag;
	}
	//判断手机号码是否正确
	var _mobile = $(":text[name='mobile']");
	var reg = /^1[3|5|7|8|][0-9]{9}$/;
	if(!reg.test(_mobile.val())){
		alert("手机号码不正确");
		return false;
	}
	return true;
}

function canAddAddress(){
	var flag = true;
	$.ajax({
		   type: "get",
		   async: false,
		   url: _rootUrl + "/address/canAddAddress.htm",
		   success: function(result){
			   if(result != "success"){
				   flag =  false;
				   alert("地址信息已满，无法添加新地址！");
			   }
		   }
	});
	return flag;
}

function bindEditAndDeleteAndCheckAction(){
	$(".viewport2 .goods_check").unbind("click");
	$(".ua_li_edit").unbind("click");
	$(".ua_li_del").unbind("click");
	//勾选地址点击
	$(".viewport2 .goods_check").click(function() {
		var _this = $(this);
		if (_this.attr("class").indexOf("gc_y") != -1) {
			$(".viewport2 .goods_check").removeClass("gc_y").addClass("gc_n");
			_this.removeClass("gc_y").addClass("gc_n");
		} else {
			$(".viewport2 .goods_check").removeClass("gc_y").addClass("gc_n");
			_this.removeClass("gc_n").addClass("gc_y");
			//选择某个地址，设为默认值
			$.ajax({
			   type: "get",
			   async: false,
			   data: {
				   		id:_this.attr("id"),
			   		 },
			   url: _rootUrl + "/address/setDefaultAddress.htm",
			   dataType: "json",
			   success: function(result){
				   //var address = eval("("+result+")");
				   if(result.status == "302"){
					   location.href=result.location;
					   return;
				   }
				   var address = result;
				   if(address.success){
					   var style = "";
//					   if($("#kdsh_a").attr("class").indexOf("gc_y") != -1){
//						   style = "style='display:none'";
//					   }
					   //修改默认地址信息
					   $(".viewport .co_shdz").hide();
					   var html = "<li>"+address.result.receiver +"&nbsp;"+address.result.mobile+"</li>";
					   html += "<li class='co_ads'>" + address.result.addressDetail + "</li>";
					   html += "<li class='co_ads'><span class='co_pavi' "+style+">" + address.result.pavilionName + "&nbsp;&nbsp;" + address.result.pavilionPhone + "</span>&nbsp;</li></ul>";
					   html += "<li><a href='javascript:;' class='csd_jt'></a></li>";
					   $("#def_address").html(html).show();
				   }else{
					   alert("选择收货人信息出现错误！");
				   }
			   }
			});
			$(".viewport").slideDown(function() {
				$(".viewport2").hide();
		    });
		}
	});
	
	//编辑地址点击
	$(".ua_li_edit").click(function(){
		clearform();
		var _this = $(this);
		var addressId = _this.parents("ul[class*='ua_ul']").find("a[class*='goods_check']").attr("id");
		//判断session是否过期,退出该方法
		var flag = false;
		$("#edit_address_id").val(addressId);
		$.ajax({
		   type: "get",
		   async: false,
		   url: _rootUrl + "/address/getUserAddress.htm",
		   data: "addressId="+addressId,
		   success: function(data){
			   //var address = eval("("+data+")").result;
			   if(data.status == "302"){
				   location.href=data.location;
				   flag = true;
				   return ;
			   }
			   var address = data.result;
			   $(":text[name='receiver']").val(address.receiver);
			   $(":text[name='addressDetail']").val(address.addressDetail);
			   $(":text[name='mobile']").val(address.mobile);
			   restoreValue(address.province, address.city, address.district, 
					   address.community, address.pavilionId, address.pavilionCode);
		   },
		   dataType: "json"
		});
		if(flag){
			return ;
		}
		$(".filter_div").height($(document).height()).show();
		$(".pop_div").show();
	});
	
	//删除地址点击
	$(".ua_li_del").click(function(){
		var _this = $(this);
		var _ul = _this.parents("ul[class*='ua_ul']");
		var addressId = _ul.find("a[class*='goods_check']").attr("id");
		var flag = confirm("确认删除？");
		if(!flag){
			return;
		}
		$.ajax({
			   type: "get",
			   async: false,
			   data: "id="+addressId,
			   url: _rootUrl + "/address/deleteAddress.htm",
			   success: function(data){
				   if(data.status == "302"){
					   location.href=data.location;
					   return ;
				   }
				   //var data = eval("("+data+")");
				   //console.log(data);
				   if(data.success){
					   _ul.remove();
					   if(data.result > 0){
						   $("#def_address").empty();
						   $(".select").css("display","block");
					   }
					   if(data.result == -1){
						 //  $(".select").css("display","block");
					   }
					   if(data.result == 0){
							 $(".xz").css("display","block");
							 $(".select").css("display","none");
							 $(".co_shdz_d").css("display","none");
					   }
				   }else{
					   alert("删除地址错误！");
				   }
			   },
			   dataType: "json"
		});
	});
}

//清除支付点击状态
function clearZfState() {
	$(".co_zffs .goods_check").removeClass("gc_y").removeClass("gc_n");;
}

function clearform() {
	//清除表单信息
	$("input").val("");
	$("#province").val("");
	$("#city").html("<option value=''>请选择市</option>");
	$("#district").html("<option value=''>请选择区</option>");
	$("#community").html("<option value=''>请选择商圈</option>");
	$("#pavilionId").html("<option value=''>请选择自提点</option>");
	$("#pavilionCode").val("");
	$("#pavilionId_div").html("请选择自提点");
}

function isMobile(){
	var agentcheck = navigator.userAgent.toLowerCase();
	var chesys = true;
	var flag = false;
	var keywords = ["mobile", "android", "symbianos", "iphone", "ipad", "windows phone", "mqqbrowser", "nokia", 
	                "samsung", "midp-2", "untrusted/1.0", "windows ce", "blackberry","ucweb", "brew", "j2me", 
	                "yulong", "coolpad", "tianyu", "ty-", "k-touch", "haier", "dopod", "lenovo", "huaqin", "aigo-", 
	                "ctc/1.0", "ctc/2.0", "cmcc", "daxian", "mot-", "sonyericsson", "gionee", "htc", "zte", 
	                "huawei", "webos", "gobrowser", "iemobile", "wap2.0", "wapi"];
	//排除 windows、苹果等桌面系统、iPod 
	var rekeywords = ["Windows 98", "Windows ME","Windows 2000","Windows XP","Windows NT","Ubuntu","ipod","macintosh"];
	if (agentcheck!=null){
		for (var i = 0; i < rekeywords.length; i++) {
			if (agentcheck.indexOf(rekeywords[i].toLowerCase()) > -1){ 
				chesys = false;
			}
		}
	}
	if (chesys){ 
		for (var i = 0; i < keywords.length; i++) {
			if ( agentcheck.indexOf(keywords[i].toLowerCase()) > -1) {
				flag = true;
				break;
			}
		}
	}
	if(flag){
		return true;
	}
	return false;
}

function isMicroMessenger(){
	var wechatInfo = navigator.userAgent.match(/MicroMessenger\/([\d\.]+)/i) ;
	if( wechatInfo && wechatInfo[1] >= "5.0") {
	//if(wechatInfo) {
		return true;
	}
	return false;
}