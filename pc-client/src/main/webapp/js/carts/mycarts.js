
$(function() {
	//滚动条位置
	$(window).scrollTop($.cookie("scroll"))
	
	//全选按钮
	var _all = $(":checkbox[name='all']");
	_all.attr("checked", true);
	$(":checkbox[name='goods']").each(function(){
		if(!$(this).attr("checked")){
			_all.removeAttr("checked");
			return;
		}
	});
	_all.click(function(){
		var status = "";
		if($(this).attr("checked")){
			$(":checkbox[name='goods']").attr("checked","checked");
			_all.attr("checked","checked");
			status = "Y";
		}else{
			$(":checkbox[name='goods']").removeAttr("checked");
			_all.removeAttr("checked");
			status = "N";
		}
		checkProducts();
	});
	
	//商品勾选
	$(":checkbox[name='goods']").click(function(){
		var status = "";
		if($(this).attr("checked")){
			status = "Y";
		}else{
			status = "N";
		}
		checkProducts($(this), status);
	});
	
	//展开收起
	$(".co_name_btn").toggle(function() {
		var _t = $(this);
		_t.html("收起").append($("<span class='co_name_lg co_name_lg_up'></span>"));
		_t.closest(".cart_obj").next().slideDown();
	}, function() {
		var _t = $(this);
		_t.html("展开").append($("<span class='co_name_lg'></span>"));
		_t.closest(".cart_obj").next().slideUp();
	});
	
	//信息补全
	var _need_msg = $("#need_msg");
	if (_need_msg[0]) {
		_need_msg.css({"margin-left":-_need_msg[0].offsetWidth/2, "left":"50%"});
		_need_msg.animate({"top":"0"}, 1000);
		
		$("#nd_msg_clo").click(function() {
			_need_msg.animate({"top":"-50", "opacity":"0", "filter":"alpha(opacity=0)"}, 500);
		});
	}
	// 刷卡结算时弹出window
	/*
    $("#pop").jqm({
        modal:true,
        overlay:40,
        onShow :function(h){
            h.w.fadeIn(500);
        },
        onHide : function(h){
            h.o.remove();
            h.w.fadeOut(500);
        }
    }).jqmAddClose("#close");
    */
	
	//回车提交
	$("#cardNum").keyup(function(e) {
		if(e.keyCode == 13) {
			$("#queryBtn").click();
		} else if (e.keyCode == 27) {
			$("#pop_tit_a").click();
		}
	});
	function showCardMsg(str) {
		$("#card_msg").html(str);
	}

	$("#queryBtn").click(function(){
		var val = $("#cardNum").val().toLowerCase();
        var chkUrl,cardPayFlag;
        ////////////// 增加手机号输入功能 //////////////
        var reg = /^1[3|5|7|8|][0-9]{9}$/;
        if(reg.test(val)){
            cardPayFlag = 0;
           chkUrl ="/order/getUsrByPhone.htm"
        }
        else if(val.length == 8){ // cardNum length
            cardPayFlag = 1;
            chkUrl = "/card/getUserByCardNum.htm"
        }
        else{
        	showCardMsg("输入的号码错误，请重输！");
            return;
        }
        ////////////// end 增加手机号输入功能 //////////////
		$.ajax({
			type:"post",
			dataType:"json",
			async:false,
			data:{"cardNum":val,"mobile":val},
			url:_rootUrl + chkUrl,
			success:function(data){
				if(!data.result){
					showCardMsg("未能取得此卡有效信息，请检查后重刷！");
                    return ;
                }
				else
				{
                    $("#ctf_card_js").hide();
					$("#ctf_card_js").hide().after($("<span style='color: red;'>正在转向订单信息填写页面，请稍候！&nbsp;&nbsp;</span>"));//点击结算后的等待消息
					//判断库存是否充足
					$.ajax({
						type: "post",
						async: false,
						url: _rootUrl + "/carts/isEnough.htm",
						success: function(msg){
							if(msg != "success"){
								$("#pop_tit_a").click();
								showMsgHint("当前存在库存不足或者限购的商品，商品状态将会发生改变！","gantan",toMyCarts);
							}else{
								//跳转到结算页面
								window.location.href = _rootUrl + "/carts/amtByCard.htm?cardPay="+cardPayFlag;
							}
						}
					});
				}
			}
		});
	});

});

$(window).load(function() {
	//结算按钮悬浮效果
	var _cart_tf = $("#cart_tf");
	if ($("#ctf_js")[0] && $("#ctf_js").offset().top >= $(window).height()) {
		_cart_tf.addClass("cart_tf_xf");
	}
	var _lis = $("#cart_con li.cart_con_li");
		_len = _lis.length;
	$(window).scroll(function() {
		if (_len > 0 && _cart_tf.offset().top >= (_lis.eq(_len - 1).offset().top + _lis.eq(_len - 1).height())) {
			_cart_tf.removeClass("cart_tf_xf");
		}
		if (_cart_tf.attr("class") == "clear" && $(window).scrollTop() + $(window).height() <= (_cart_tf.offset().top + _cart_tf.height())) {
			_cart_tf.addClass("cart_tf_xf");
		}
		//滚动时，记录滚动条的位置，刷新后，保持在相同的位置
		var cookietime = new Date();
		cookietime.setTime(cookietime.getTime() + (30 * 1000)); //coockie保存30秒 
	    $.cookie("scroll", $(window).scrollTop(), {expires: cookietime});
	});
});

function minusOneCallback(_input){
	changeNumber(_input);
}

function plusOneCallback(_input){
	changeNumber(_input);
}

function modifyNumberCallback(_input){
	changeNumber(_input);
}

function changeNumber(_input){
	var _row = _input.parents("ul");
	var id = _row.children("li").children(":checkbox").val();
	$.ajax({
		   type: "post",
		   url: _rootUrl + "/carts/changeNumber.htm",
		   async: false,
		   data: {
				   id:id,
				   count:_input.val()
		   		 },
		   success: function(msg){
			   if(msg != "success"){
				   showMsgHint("修改购物车商品数量出现错误！","gantan");
			   }else{
				   toMyCarts();
			   }
		   }
		});
}

function deleteProducts(obj){
	var ids ="";
	if(typeof obj != "undefined"){
		//单个删除
		var _deleteRow = $(obj).parents("ul");
		ids = _deleteRow.children("li").children(":checkbox").val();
	}else{
		//批量删除
		var _checked = $(":checked[name='goods']");
		if(_checked.length == 0){
			showMsgHint("请选择需要删除的商品！","gantan");
			return;
		}
		_checked.each(function(){
			ids = ids + $(this).val();
		});
	}
	showMsgCfmHint("提示", "确定要删除?", "gantan", function(){
		$.ajax({
		   type: "post",
		   url: _rootUrl + "/carts/deleteProducts.htm",
		   async: false,
		   data: {ids:ids},
		   success: function(msg){
			   if(msg != "success"){
				   showMsgHint("删除购物车商品出现错误！","gantan");
			   }else{
				   toMyCarts();
			   }
		   }
		});
	});
}

function checkProducts(obj, status){
	var ids ="";
	var checkeds = "";
	if(typeof obj != "undefined" && typeof status != "undefined"){
		//单个更改状态
		var _checkbox = $(obj).parents("ul").children("li").children(":checkbox[name='goods']");
		ids = _checkbox.val();
		checkeds = status;
	}else{
		//批量更改状态
		var _checkbox = $(":checkbox[name='goods']");
		_checkbox.each(function(){
			ids = ids + $(this).val();
			if($(this).attr("checked")){
				checkeds = checkeds + "Y" + "&";
			}else{
				checkeds = checkeds + "N" + "&";
			}
		});
	}
	if(ids != "" && checkeds != ""){
		$.ajax({
		   type: "post",
		   url: _rootUrl + "/carts/changeStatus.htm",
		   async: false,
		   data: {
			   		ids:ids,
			   		checkeds:checkeds
			   	 },
		   success: function(msg){
			   if(msg != "success"){
				   showMsgHint("勾选购物车商品出现错误！","gantan");
			   }else{
				   toMyCarts();
			   }
		   }
		});
	}
}

function amount(){
	if($(":checked[name='goods']").size() == 0){
		showMsgHint("请选择要结算的商品！","gantan");
		return;
	}
	if(!_is_user_login()){
		$("#_filter, #logdiv").show();
	}else{
		$("#ctf_js").hide().after($("<span style='color: red;'>正在转向订单信息填写页面，请稍候！&nbsp;&nbsp;</span>"));//点击结算后的等待消息
		//判断库存是否充足
		$.ajax({
			   type: "post",
			   async: false,
			   url: _rootUrl + "/carts/isEnough.htm",
			   success: function(msg){
				   if(msg != "success"){
					   showMsgHint("当前存在库存不足或者限购的商品，商品状态将会发生改变！","gantan");
					   toMyCarts();
				   }else{
					   // 清除之前的session中刷卡用户信息,在刷卡后没有进行付款，则此用户信息会一直留存于服务器中
					   // 为了防止内存泄露需要清理
					   // <%session.setAttribute("",null)%>
					   //跳转到结算页面
					   window.location.href = _rootUrl + "/carts/amount.htm";
				   }
			   }
		});
	}
}

// 刷卡结算功能
function cardAmount(){
	if($(":checked[name='goods']").size() == 0){
		showMsgHint("请选择要结算的商品！","gantan");
		return;
	}
	if(!_is_user_login())
	{
		$("##_filter").width($(window).width()).height($(document).height()).show();
		$("#logdiv").show();
	}
	else
	{
		//进入到刷卡及验证部分：
		$("#msg_filter_div").show();
		$("#pop").show();
		$("#cardNum").val("");
		$("#cardNum")[0].focus();
	}
}

function toMyCarts(){
	window.location.href = _rootUrl + "/carts/myCarts.htm";
}

//登录后刷新购物车页面
function afterLogin(){
	toMyCarts();
}

function login(){
	$("#_filter, #logdiv").show();
}
