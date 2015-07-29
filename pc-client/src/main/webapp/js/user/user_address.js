$(function() {
	//新增按钮点击
	$("#tg_a_ul_a").click(function() {
		//判断是否可以添加新地址
		$.ajax({
			   type: "get",
			   async: false,
			   cache: false,
			   url: _rootUrl + "/address/canAddAddress.htm",
			   success: function(result){
				   if(result != "success"){
					   showMsgHint("地址信息已满，无法添加新地址！","gantan");
				   }else{
					   $("#uaddiv_sp").html("新增");
					   showUserAddresForm();
				   }
			   }
		});
	});
	
	//关闭弹出层
	$("#uaddiv_tit_a").click(function() {
		$("#_uad_filter").remove();
		$("#uaddiv").hide();
		clearState();
	});
	
	//清状态
	function clearState() {
		$(".r_ipt").val("");
		$("#province").val("");
		$("#city").html("<option value=''>请选择市</option>");
		$("#district").html("<option value=''>请选择区</option>");
		$("#community").html("<option value=''>请选择商圈</option>");
		$("#pavilionId").html("<option value=''>请选择自提点</option>");
		$("#pavilionCode").val("");
		$(":text[name='receiver']").val("");
		$(":text[name='addressDetail']").val("");
		$(":text[name='mobile']").val("");
		$(":hidden[name='addressId']").val("");
		//清除消息提示
		$(".r_msg, .p_msg").remove();
		$(".r_ipt").removeClass("focus_eor_ipt");
	}
	
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
});

//弹出地址编辑表单
function showUserAddresForm() {
	var _f = $("<div id='_uad_filter' style='width: " + $(window).width() + "px; height: " + $(document).height() + "px; position: absolute; top: 0px; left: 0px; opacity: 0.5; filter: alpha(opacity=50); background: #000; z-index: 1000;'></div>");
	$("body").append(_f);
	$("#uaddiv").show();
}

//得到地址信息
function getAddress(id){
	$.ajax({
		   type: "get",
		   async: false,
		   cache: false,
		   url: _rootUrl + "/address/getUserAddress.htm",
		   data: "addressId="+id,
		   success: function(data){
			   var address = eval("("+data+")").result;
			   $(":text[name='receiver']").val(address.receiver);
			   $(":text[name='addressDetail']").val(address.addressDetail);
			   $(":text[name='mobile']").val(address.mobile);
			   $(":text[name='phone']").val(address.phone);
			   $(":hidden[name='addressId']").val(id);
			   restoreValue(address.province, address.city, address.district, 
					   address.community, address.pavilionId, address.pavilionCode);
			   $("#uaddiv_sp").html("编辑");
			   showUserAddresForm();
		   }
	});
}

//修改地址信息
function modifyAddress(){
	var flag = true;
	//判断所有的输入框是否填写了
	$("input[class*='required']").each(function(){
		var _input = $(this);
		if($.trim(_input.val()) == ""){
			_input.addClass("focus_eor_ipt");
			_input.siblings("label").remove();
			if ($(this).attr("name") == "addressDetail") {
				_input.after($("<label class='r_msg r_msg_w'>不能为空</label>"));
			} else {
				_input.after($("<label class='r_msg'>不能为空</label>"));
			}
			flag = false;
		}
	});
	//判断选择框是否填写了
	if($("#province").val() == "" || $("#city").val() == "" || $("#district").val() == ""||$("#community").val()==""||$("#pavilionId").val()==""){
		flag = false;
		$("#pavilionId").siblings("label").remove();
		$("#pavilionId").after($("<label class='p_msg'>不能为空</label>"));
	}
	//手机号码和电话号码其一必填
	var _mobile = $(":text[name='mobile']");
	var _phone = $(":text[name='phone']");
	if(_mobile.val() == "" && _phone.val() == ""){
		flag = false;
		_mobile.after($("<label class='r_msg'>手机号码和电话号码其一必填</label>"));
		_phone.after($("<label class='r_msg'>手机号码和电话号码其一必填</label>"));
	}
	if(!flag){
		return flag;
	}
	//判断手机号码是否正确
	if(_mobile.val() != ""){
		var reg = /^1[3|5|7|8|][0-9]{9}$/;
		if(!reg.test(_mobile.val())){
			_mobile.addClass("focus_eor_ipt");
			_mobile.siblings("label").remove();
			_mobile.after($("<label class='r_msg'>手机号码不正确</label>"));
			flag = false;
		}
	}
	//判断电话号码是否正确
	if(_phone.val() != ""){
		var reg = /^[0-9]+$/;
		if(!reg.test(_phone.val())){
			_phone.addClass("focus_eor_ipt");
			_phone.siblings("label").remove();
			_phone.after($("<label class='r_msg'>电话号码不正确</label>"));
			flag = false;
		}
	}
	if(!flag){
		return flag;
	}
	var addressId = $(":hidden[name='addressId']").val();
	var receiver = $(":text[name='receiver']").val();
	var mobile = $(":text[name='mobile']").val();
	var addressDetail = $(":text[name='addressDetail']").val();
	$.ajax({
		   type: "post",
		   async: false,
		   contentType:"application/x-www-form-urlencoded;charset=UTF-8",
		   url: _rootUrl + "/address/saveOrUpdateAddress.htm",
		   data: {
			    id:addressId,
		   		receiver:receiver,
		   		mobile:_mobile.val(),
		   		phone:_phone.val(),
		   		addressDetail:addressDetail,
		   		province:$("select[name='province']").val(),
		   		city:$("select[name='city']").val(),
		   		district:$("select[name='district']").val(),
		   		community:$("select[name='community']").val(),
		   		pavilionId:$("select[name='pavilionId']").val(),
		   		pavilionCode:$(":hidden[name='pavilionCode']").val()
		   },
		   success: function(data){
			   var address = eval("("+data+")");
			   if(address.success){
				   var result = address.result;
				   var html = "<ul class='clear'>" +
				   				 "<li class='ai_li'>收货人：</li><li>"+receiver+"</li></ul>" +
				   				 "<ul class='clear'>" +
				   				 "<li class='ai_li'>地址：</li><li class='ad_li'>"+addressDetail+"</li></ul>" +
				   				 "<ul class='clear'>" +
				   				 "<li class='ai_li'>手机号码：</li><li>"+_mobile.val()+"</li></ul>" +
				   				 "<ul class='clear'>" +
				   				 "<li class='ai_li'>电话号码：</li><li>"+_phone.val()+"</li></ul>" +
				   				 "<ul class='clear' id='ai_ul_fwt'>" +
				   				 "<li class='ai_li'>自提点：</li><li>"+result.pavilionName+"&nbsp;&nbsp;&nbsp;"+result.pavilionPhone+"</li></ul>" +
				   				 "<ul class='clear' id='ai_ul_ope'>" +
				   				 "<li><a class='ai_li_a upd_address' href='javascript:;' onclick='getAddress("+result.id+")'>编辑</a></li>" +
				   				 "<li><a class='ai_li_a' href='javascript:;' onclick='deleteAddress("+result.id+")'>删除</a></li></ul>";
				   if(addressId != ""){ //修改
					  $("#address"+addressId).html(html);
				   }else{ //新增
					   $("#address_infos").append("<div class='address_info' id='address"+result.id+"'>" + html + "</div>");
					   $("#addressNum").html(parseInt($("#addressNum").html())+1);
				   }
				   $("#uaddiv_tit_a").click();
			   }else{
				   showMsgHint("新增或者修改地址信息出现错误！","gantan");
			   }
		   }
	});
}

//删除地址信息
function deleteAddress(id){
	showMsgCfmHint("提示", "确定要删除?", "gantan", function(){
		$.ajax({
			   type: "post",
			   async: false,
			   data: "id="+id,
			   url: _rootUrl + "/address/deleteAddress.htm",
			   success: function(data){
				   if(data == "success"){
					   $("#address"+id).remove();
					   $("#addressNum").html(parseInt($("#addressNum").html()-1));
				   }else{
					   showMsgHint("删除地址错误！","gantan");
				   }
			   }
		});
	});
}
