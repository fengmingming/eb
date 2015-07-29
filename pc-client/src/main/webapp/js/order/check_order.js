$(window).load(function() {
	$(":radio[name='zffs']").click(function() {
		if($("#yezf").attr("checked")){
			$("#pay_password").show();
		}else{
			$("#pay_password").hide();
		}
	});
	
	$("#pay_password input").focus(function() {
		$(this).removeClass("focus_eor_ipt");
		$("#cf_order_hint").hide();
		$("#cf_order_hint li").html("");
	});
	
	$(".r_ipt").focus(function() {
		var _t = $(this);
		_t.removeClass("focus_eor_ipt");
		_t.addClass("focus_ipt");
		_t.siblings("label").remove();
	}).blur(function() {
		$(this).removeClass("focus_ipt");
	});
	$("#province, #city, #district, #community, #pavilionId").focus(function() {
		$(".p_msg").remove();
	});
	
	//获取焦点
	$("#xx_address, #rcvr, #mobi, #phone").focus(function() {
		var _t = $(this);
		_t.removeClass("focus_eor_ipt");
		_t.addClass("focus_ipt");
		_t.siblings("label").remove();
	}).blur(function() {
		$(this).removeClass("focus_ipt");
	});
	
	$("#province, #city, #district, #community, #pavilionId").click(function() {
		$(this).siblings("label").remove();
	});
	
	//使用优惠券点击
	$(".c_yq_a").toggle(function() {
		$("#cfo_uyq_ul").slideDown();
		$(".c_yq_log").removeClass("c_yq_log_a").addClass("c_yq_log_s");
	}, function() {
		$("#cfo_uyq_ul").slideUp();
		$(".c_yq_log").removeClass("c_yq_log_s").addClass("c_yq_log_a");
	});
	
	//选择优惠券
	bindSelectCouponAction();
	
	//使用优惠码
	$("#use_coupon_code").click(function(){
		var _coupon_code = $("#coupon_code");
		if($.trim(_coupon_code.val()) == ""){
			showMsgHint("优惠码不能为空！","gantan");
			return;
		}
		$.ajax({
            type: "post",
            async: false,
            data: {
            	code:_coupon_code.val()
            },
            url: _rootUrl + "/coupon/getUserCouponByCode4Order.htm",
            success: function(obj){
            	var data = eval("(" + obj + ")");
            	if(data.result != null){
            		var userCoupons = data.result;
            		var html = "";
            		$.each(userCoupons, function(index, userCoupon){
            			html += "<li><ul class='cq_ul_f'>" +
		     				   "<li><input name='coupon' type='radio' class='cq_f_inp' value='" + userCoupon.id +"'/>" +
		     				   "<input type='hidden' value='" + userCoupon.parValue +"'></li>" +
		     				   "<li style='width: 235px;'>" + userCoupon.name + "</li>" +
		     				   "<li style='width: 195px;'>" + userCoupon.limitCatName + "</li>" +
		     				   "<li style='width: 175px;'>" + toDateStr(userCoupon.validityEnd) + "</li>" +
		     				   "</ul><div style='clear: both;'></div></li>";
            		});
            		html += "<li><ul class='cq_ul_f' style='margin-top: -1px;'>" +
            				"<li><input name='coupon' type='radio' class='cq_f_inp' value='' checked /></li>" +
            				"<li style='width: 605px;'>不使用</li></ul><div style='clear: both;'></div></li>";
            		var _cq_kyyhq = $(".cq_kyyhq");
            		_cq_kyyhq.siblings("li").remove();
            		_cq_kyyhq.after(html);
            		bindSelectCouponAction();
            	}
            	if(!data.success){
            		showMsgHint(data.errMsg, "gantan");
            	}
            	//展开优惠券列表
            	$("#cfo_uyq_ul").slideDown();
        		$(".c_yq_log").removeClass("c_yq_log_a").addClass("c_yq_log_s");
            	$(":radio[name='coupon']:last").click();
            }
        });
	});
});

function toDateStr(datetime){
	var date = new Date(datetime.replace(/-/g,"/"));
	return date.getFullYear()+"年"+(date.getMonth()+1)+"月"+date.getDate()+"日";
}

function bindSelectCouponAction(){
	$(":radio[name='coupon']").unbind("click");
	var _total = $("#cfof_total");
	var _discountByCoupon = $("#discountByCoupon");
	$(":radio[name='coupon']").click(function(){
		var _this = $(this);
		if(_this.val() > 0){
			var parValue = _this.siblings(":hidden").val();
			var payTotal = (parseFloat(total)-parseFloat(parValue)).toFixed(2);
			var discountByCoupon = parseFloat(parValue).toFixed(2);
			if(payTotal < 0){
				payTotal = "0.00";
				discountByCoupon = total;
			}
			_total.html("￥" + payTotal);
			_discountByCoupon.html("-￥" + discountByCoupon);
		}else{
			_total.html("￥" + total);
			_discountByCoupon.html("-￥0.00");
		}
	});
}

function setDefaultAddr(obj){
	var _this = $(obj);
	var addrId = _this.attr("id");
	if(addrId == -1){
		return;
	}
	if(curAddrId == addrId){
		return;
	}
	$(".add_block").removeClass("adbk_cur1").addClass("adbk_cur2");
	$(".at_duigou").removeClass("at_show");
    if(addrId > 0){
        $.ajax({
            type: "post",
            async: false,
            data: {
                id:addrId
            },
            url: _rootUrl + "/address/setDefaultAddress.htm",
            success: function(result){
            	curAddrId = addrId;
                var address = eval("("+result+")");
                if(address.success){
                	_this.removeClass("adbk_cur2").addClass("adbk_cur1");
                    _this.find(".at_duigou").addClass("at_show");
                }else{
                    showMsgHint("保存收货人信息出现错误！","gantan");
                }
            }
        });
    }else{
    	curAddrId = 0;
    	_this.removeClass("adbk_cur2").addClass("adbk_cur1");
        _this.find(".at_duigou").addClass("at_show");
    }
}

function deleteAddr(id, e){
	window.event? window.event.cancelBubble = true : e.stopPropagation(); //阻止冒泡，阻止div的click事件
	if(id > 0){
		showMsgCfmHint("提示", "确定要删除?", "gantan", function(){
			$.ajax({
				   type: "get",
				   async: false,
				   data: "id="+id,
				   url: _rootUrl + "/address/deleteAddress.htm",
				   success: function(data){
					   if(data == "success"){
						   $("#addrTo").find("#" + id).remove();
					   }else{
						   showMsgHint("删除地址错误！","gantan");
					   }
				   }
			});
		});
	}
}

function showUpdtAddr(id, e) {
	$("#uap_tit_str").html("修改");
	window.event? window.event.cancelBubble = true : e.stopPropagation(); //阻止冒泡，阻止div的click事件
	if(editAddrId != id){
		clearform();
		var _edit_div = "";
		if(id > 0){
			_edit_div = $("#addrTo").find("#" + id);
			//$("#tr_pavilion_ads").hide();
			$(".tr_xx_adds").show();
		   editAddrId = id;
		}else{
			_edit_div = $("#addrTo").find("#ztAddr");
		    //$("#tr_pavilion_ads").show();
		    $(".tr_xx_adds").hide();
			editAddrId = 0;
		}
		$("#rcvr").val(_edit_div.find("#add_receiver").html());
		$("#mobi").val(_edit_div.find("#add_mobile").html());
		$("#phone").val(_edit_div.find("#add_phone").html());
	    $("#xx_address").val(_edit_div.find("#add_detail").html());
		restoreValue(_edit_div.find("#add_province").attr("value"), _edit_div.find("#add_city").attr("value"), 
			_edit_div.find("#add_district").attr("value"), _edit_div.find("#add_community").attr("value"),
			_edit_div.find("#add_pavilionId").attr("value"),_edit_div.find("#add_pavilionCode").attr("value"));
	}
	$("#msg_filter_div").show();//蒙版
	$("#upd_address_pop").show();
}

function showAddtAddr(id, e){
	$("#uap_tit_str").html("添加");
	window.event? window.event.cancelBubble = true : e.stopPropagation(); //阻止冒泡，阻止div的click事件
	var _edit_div = "";
	if(id == -1){
		//新增快递地址
		if(!canAddAddress()){
			return;
		}
		//$("#tr_pavilion_ads").hide();
		$(".tr_xx_adds").show();
		editAddrId = -1;
	}else{
		//新增收货地址
		//$("#tr_pavilion_ads").show();
	    $(".tr_xx_adds").hide();
		editAddrId = 0;
	}
	clearform();
	$("#msg_filter_div").show();//蒙版
	$("#upd_address_pop").show();
}

function updtAddr() {
	var flag = false;
	var _edit_div = "";
	var province = $("#province").val();
	var city = $("#city").val();
	var district = $("#district").val();
	var community = $("#community").val();
	var pavilionId = $("#pavilionId").val();
	var pavilionCode = $("#pavilionCode").val();
	var addressDetail = $("#xx_address").val();
	var mobile = $("#mobi").val();
	var phone = $("#phone").val();
	var receiver = $("#rcvr").val();
	var isdefault = $("#set_def_addr").prop("checked");
	if(!checkInput(editAddrId)){
		return;
	}
	//修改用户自提点自提的地址
	if(editAddrId == 0){
		_edit_div = $("#addrTo").find("#ztAddr");
		flag = true;
	}
	//新增和修改用户快递送货的地址
	if(editAddrId == -1 || editAddrId > 0){
		_edit_div = $("#addrTo").find("#" + editAddrId);
	    $.ajax({
	        type: "post",
	        async: false,
	        data: {
	            id: editAddrId,
	            province: province,
	            city: city,
	            district: district,
	            community: community,
	            pavilionId: pavilionId,
	            pavilionCode: pavilionCode,
	            addressDetail: addressDetail,
	            mobile: mobile,
	            phone: phone,
	            receiver: receiver,
	            isdefault: isdefault
	        },
	        url: _rootUrl + "/address/saveOrUpdateAddress.htm",
	        success: function(result){
	            var address = eval("("+result+")")
	            if(address.success){
	            	editAddrId = address.result.id;
	               flag = true;
	            }else{
	                showMsgHint("保存收货人信息出现错误！","gantan");
	            }
	        }
	    });
	}
	
	if(flag){
		//新增收货地址
		if(_edit_div.length == 0){
			var html = "<div id='"+ editAddrId +"' class='add_block adbk_cur2' onclick='setDefaultAddr(this)'><p class='ad_p1'>" + 
					   "<span class='at_ssq'><label id='add_province' value=''></label>" +
					   "<label id='add_city' value=''></label></span>" +
					   "<span>&nbsp;&nbsp;&nbsp;&nbsp;(<span id='add_receiver'></span>&nbsp;收)</span></p>" +
					   "<p class='ad_p2'><label id='add_district' value=''></label>" +
					   "<label id='add_community' value=''></label>" +
					   "<label id='add_pavilionId' value=''></label>" +
					   "<label id='add_pavilionCode' value='' style='display:none;'></label>" +
					   "<label id='add_detail'></label>&nbsp;&nbsp;" +
					   "<span class='at_pon' id='add_mobile'></span><span id='add_phone' style='display:none'></span></p>" +
					   "<a class='at_upd' onclick='showUpdtAddr("+ editAddrId +", event);' href='javascript:;'>修改</a>" +
					   "<a class='at_del' onclick='deleteAddr("+ editAddrId +", event);' href='javascript:;'>删除</a>" +
					   "<div class='at_duigou'></div></div>";
			$("#addrTo .add_new_block").before(html);
			_edit_div = $("#addrTo").find("#" + editAddrId);
		}
		//新增自提点地址
		if(editAddrId == 0 && $("#ztAddr .new_adds").length > 0){
			var html = "<div id='ztAddr' class='add_block adbk_cur2' onclick='setDefaultAddr(this)'><span class='at_zt_icon'>自提地址</span><p class='ad_p1'>" + 
					   "<span class='at_ssq'><label id='add_province' value=''></label>" +
					   "<label id='add_city' value=''></label></span>" +
					   "<span>&nbsp;&nbsp;&nbsp;&nbsp;(<span id='add_receiver'></span>&nbsp;收)</span></p>" +
					   "<p class='ad_p2'><label id='add_district' value=''></label>" +
					   "<label id='add_community' value=''></label>" +
					   "<label id='add_pavilionId' value=''></label>" +
					   "<label id='add_pavilionCode' value='' style='display:none;'></label>" +
					   "<label id='add_detail' style='display:none'></label>&nbsp;&nbsp;" +
					   "<span class='at_pon' id='add_mobile'></span><span id='add_phone' style='display:none'></span></p>" +
					   "<a class='at_upd' onclick='showUpdtAddr(ztAddr, event);' href='javascript:;'>修改</a>" +
					   "<div class='at_duigou'></div></div>";
			$("#addrTo #ztAddr").remove();
			$("#addrTo").prepend(html);
			_edit_div = $("#addrTo").find("#ztAddr");
		}
	    _edit_div.find("#add_province").attr("value", province).html(framework.area_map[province]);
	    _edit_div.find("#add_city").attr("value", city).html(framework.area_map[city]);
	    _edit_div.find("#add_district").attr("value", district).html(framework.area_map[district]);
	    _edit_div.find("#add_community").attr("value", community).html(framework.area_map[community]);
	    _edit_div.find("#add_pavilionId").attr("value", pavilionId).html(framework.area_map[pavilionId]);
	    _edit_div.find("#add_pavilionCode").attr("value", pavilionCode).html(framework.area_map[pavilionCode]);
//	    _edit_div.find("#add_community").attr("value", community).html("");//html的内容应该是下拉框选中的值（这里未做修改）
//		_edit_div.find("#add_pavilionId").attr("value", pavilionId).html("");//html的内容应该是下拉框选中的值（这里未做修改）
//		_edit_div.find("#add_pavilionCode").attr("value", pavilionCode).html(pavilionCode);//html的内容应被隐藏掉了（display:none;）
	    _edit_div.find("#add_detail").html(addressDetail);
	    _edit_div.find("#add_receiver").html(receiver);
	    _edit_div.find("#add_mobile").html(mobile);
	    _edit_div.find("#add_phone").html(phone);
	     if(mobile != ""){
	    	 _edit_div.find("#add_mobile").show();
	    	 _edit_div.find("#add_phone").hide();
	     }else{
	    	 _edit_div.find("#add_mobile").hide();
	    	 _edit_div.find("#add_phone").show();
	     }
	     if(isdefault){
	    	 $(".add_block").removeClass("adbk_cur1").addClass("adbk_cur2");
	     	 $(".at_duigou").removeClass("at_show");
	     	 _edit_div.removeClass("adbk_cur2").addClass("adbk_cur1");
	     	 _edit_div.find(".at_duigou").addClass("at_show");
	     }else{
	    	 _edit_div.removeClass("adbk_cur1").addClass("adbk_cur2");
	     	 _edit_div.find(".at_duigou").removeClass("at_show");
	     }
	}
	closeAddressPop();
}
function closeAddressPop() {
    $("#msg_filter_div").hide();
    $("#upd_address_pop").hide();
    
    //清除消息提示
    $("#bh_ads").find("label").remove();
    $("#xx_address, #rcvr, #mobi, #phone").siblings("label").remove();
    $("#xx_address, #rcvr, #mobi, #phone").removeClass("focus_eor_ipt");
    $("#uap_tit_str").html("");
}

function clearform() {
	//清除消息提示
	$(".r_msg, .p_msg").remove();
	$(".r_ipt").removeClass("focus_eor_ipt");
	//清除表单信息
    $("#rcvr").val("");
    $("#xx_address").val("");
    $("#phone").val("");
    $("#mobi").val("");
	$("#province").val("");
	$("#city").html("<option value=''>请选择市</option>");
	$("#district").html("<option value=''>请选择区</option>");
	$("#community").html("<option value=''>请选择商圈</option>");
	$("#pavilionId").html("<option value=''>请选择自提点</option>");
	$("#pavilionCode").val("");
	$("#pavilionId_div").html("请选择自提点");
	//选中设置为默认地址的勾选框
	$("#set_def_addr").attr("checked", "checked");
}

function canAddAddress(){
	var flag = true;
	$.ajax({
		   type: "get",
		   async: false,
		   cache: false,
		   url: _rootUrl + "/address/canAddAddress.htm",
		   success: function(result){
			   if(result != "success"){
				   flag =  false;
				   showMsgHint("地址信息已满，无法添加新地址！","gantan");
			   }
		   }
	});
	return flag;
}


function checkInput(editAddrId){
	var flag = true;
	
	if(editAddrId == 0){
		//自提点自提
		if($("#province").val() == "" || $("#city").val() == "" || $("#district").val() == "" ||
				$("#community").val() == "" || $("#pavilionId").val() == ""){
			flag = false;
			$("#bh_ads").append($("<label class='r_msg r_msg_w'>请选择收货地址</label>"));
		}
	}else{
		//用户快递送货地址
		if($("#province").val() == "" || $("#city").val() == "" || $("#district").val() == "" ||
				$("#community").val() == "" || $("#pavilionId").val() == ""){
			flag = false;
			$("#bh_ads").append($("<label class='r_msg r_msg_w'>请选择收货地址</label>"));
		}
		if($.trim($("#xx_address").val()) == ""){
			flag = false;
			$("#xx_address").siblings("label").remove();
			$("#xx_address").addClass("focus_eor_ipt");
			$("#xx_address").after($("<label class='r_msg_xx'>详细地址不能为空</label>"));
		}
		if($("#xx_address").val() > 254){
			flag = false;
			$("#xx_address").siblings("label").remove();
			$("#xx_address").addClass("focus_eor_ipt");
			$("#xx_address").after($("<label class='r_msg_xx'>详细地址不能超过254个字符</label>"));
		}
	}
	
	var _rcvr = $("#rcvr");
	if($.trim(_rcvr.val()) == ""){
		flag = false;
		hintMsg1(_rcvr, "收货人不能为空");
	}
	
	var _mobi = $("#mobi");
	var _phone = $("#phone");
	if(_mobi.val() == "" && _phone.val() == ""){
		flag = false;
		hintMsg1(_phone, "手机号码和电话号码其一必填");
	}
	
	if(!flag){
		return flag;
	}
	
	//判断手机号码是否正确
	if(_mobi.val() != ""){
		var reg = /^1[3|5|7|8|][0-9]{9}$/;
		if(!reg.test(_mobi.val())){
			hintMsg1(_mobi, "手机号码不正确");
			flag = false;
		}
	}
	
	//判断电话号码是否正确
	if(_phone.val() != ""){
		var reg = /^[0-9]+$/;
		if(!reg.test(_phone.val())){
			hintMsg1(_phone, "电话号码不正确");
			flag = false;
		}
	}
	
	if(!flag){
		return flag;
	}
	
	return true;
}

function commitOrder(){
	$("#cf_order_hint").hide();
	
	//收货地址不能为空
	var _addr = $("#addrTo .adbk_cur1");
	if(_addr.length == 0){
		$("#cf_order_hint li").html("请选择收货地址！");
		$("#cf_order_hint").show();
		return;
	}
	var deliveryType = 1; //送货类型：1自提点自提，2快递送货
	if(_addr.attr("id") > 0){
		deliveryType = 2;
	}
	
	//余额支付，判断是否输入支付密码
	var _password = $(":password[name='password']");
	if($("#yezf").attr("checked")){
		if($.trim(_password.val()) == ""){
			_password.addClass("focus_eor_ipt");
		   $("#cf_order_hint li").html("支付密码不能为空！");
		   $("#cf_order_hint").show();
		   return;
		}
	}
	
	//ajax提交订单，跳转到订单成功页面
	$("#submit_od").hide().after($("<span style='color: red;'>正在提交订单，请稍候！&nbsp;&nbsp;</span>"));//点击提交订单后的等待消息
	var flag = true;
	var orderId = "";
	var orderNum = "";
	var payPrice = "";
	var payType = $(":radio[name='zffs']:checked").val();
	var userCouponId = $(":radio[name='coupon']:checked").val();
	var pswMD5 = hex_md5(_password.val());
	$.ajax({
		   type: "post",
		   async: false,
		   data:{
			   provinceId:_addr.find("#add_province").attr("value"),
			   cityId:_addr.find("#add_city").attr("value"),
			   districtId:_addr.find("#add_district").attr("value"),
			   communityId:_addr.find("#add_community").attr("value"),
			   pavilionId:_addr.find("#add_pavilionId").attr("value"),
			   receiver:_addr.find("#add_receiver").html(),
			   mobile:_addr.find("#add_mobile").html(),
			   phone:_addr.find("#add_phone").html(),
			   remark:_addr.find("#add_detail").html(),
			   payType:payType,
			   deliveryType:deliveryType,
			   payPassword:pswMD5,
			   userCouponId:userCouponId
		   },
		   url: _rootUrl + "/order/commitOrder.htm",
		   success: function(data){
			   var order = eval("("+data+")");
			   if(!order.success){
				   $("#hint").remove();
				   if(order.errCode == 9999){
					   window.location.href = _rootUrl + "/main/error.htm";
				   }
				   $("#cf_order_hint li").html(order.errMsg);
				   $("#cf_order_hint").show();
				   $("#submit_od").siblings("span").remove();
				   $("#submit_od").show();
				   flag = false;
			   }else{
				   orderId = order.result.orderId;
				   orderNum = order.result.orderNum;
				   payPrice = order.result.payPrice;
			   }
		   }
	});
	if(!flag){
		return;
	}
	if($("#yezf").attr("checked")){
		//跳转到支付结果页面
		window.location.href = _rootUrl + "/order/paymentResult.htm?orderId="+orderId+"&orderNum="+orderNum;
		window.event.returnValue=false;
	}else{
		//跳转到支付页面
		if(payPrice > 0){
			window.location.href = _rootUrl + "/order/toPayment.htm?orderNum="+orderNum+"&payPrice="+payPrice;
			window.event.returnValue=false;
		}else{
			window.location.href = _rootUrl + "/order/paymentResult.htm?orderId="+orderId+"&orderNum="+orderNum;
			window.event.returnValue=false;
		}
	}
}

//消息模板1
function hintMsg1(obj, str) {
	obj.siblings("label").remove();
	obj.addClass("focus_eor_ipt");
	obj.after($("<label class='r_msg'>" + str + "</label>"));
}
