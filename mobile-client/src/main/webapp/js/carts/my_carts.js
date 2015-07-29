$(function() {
	//滚动条位置
	$(window).scrollTop($.cookie("scroll"));
	
	//单个商品的选中事件
	$(".goods_check").each(function(){
		var _this = $(this);
		if (_this.attr("class").indexOf("gc_n") != -1){
			$(".goods_check_all").removeClass("gc_y").addClass("gc_n");
		}
		_this.click(function() {
			var status = "";
			if (_this.attr("class").indexOf("gc_y") != -1) {
				_this.removeClass("gc_y").addClass("gc_n");
				status = "N";
			}else{
				_this.removeClass("gc_n").addClass("gc_y");
				status = "Y";
			}
			checkProducts(_this, status);
		});
	});
	
	//全选按钮的选中事件
	$(".goods_check_all").click(function() {
		var _this = $(this);
		if (_this.attr("class").indexOf("gc_y") != -1) {
			_this.removeClass("gc_y").addClass("gc_n");
			$(".goods_check").removeClass("gc_y").addClass("gc_n");
		} else if (_this.attr("class").indexOf("gc_n") != -1) {
			_this.removeClass("gc_n").addClass("gc_y");
			$(".goods_check").removeClass("gc_n").addClass("gc_y");
		}
		checkProducts();
	});
	
	//数量加减
	$(".minus_a").click(function(){
		var _this = $(this);
		var _inp = _this.parents(".ft_num").find("input");
		if (parseInt(_inp.val()) > 1) {
			_inp.val(parseInt(_inp.val()) - 1);
			_inp.attr("tmp", _inp.val());
			changeNumber(_inp);
		}else if(parseInt(_inp.val()) == 1){
			$("#filter_div").height($(document).height()).show();
			$("#pop_div").show();
			//点击‘-’号时的弹出层确认操作
			$("#pd_ent").click(function() {
				$("#filter_div").hide();
				$("#pop_div").hide();
				deleteProducts(_this.parents(".goods_li").find(".goods_check").attr("id"));
			});
		}
	});
	$(".plus_a").click(function() {
		var _this = $(this),
			_inp = _this.parents(".ft_num").find("input");
		if (parseInt(_inp.val()) < 200) {
			_inp.val(parseInt(_inp.val()) + 1);
			_inp.attr("tmp", _inp.val());
			changeNumber(_inp);
		}
	});
	//输入框改变数量
	$(".gda_inp").change(function() {
		var _this = $(this);
		var reg = /^[1-9][0-9]*$/;
		if (!reg.test(_this.val())) {
			_this.val(_this.attr("tmp"));
		} else if (parseInt(_this.val()) > 200) {
			_this.attr("tmp", 200);
			_this.val(200);
		} else {
			_this.attr("tmp", _this.val());
		}
		changeNumber(_this);
	});
	
	//删除商品弹出层
	$("#del_goods").click(function() {
		var flag = true;
		$(".goods_ul .goods_check").each(function() {
			if ($(this).attr("class").indexOf("gc_y") != -1) {
				flag = false;
				return false;
			}
		});
		if (flag) {
			alert("请选择要删除的商品！");
		} else {
			$("#filter_div").height($(document).height()).show();
			$("#pop_div").show();
			//点击删除商品时的弹出层确认操作
			$("#pd_ent").click(function() {
				$("#filter_div").hide();
				$("#pop_div").hide();
				deleteProducts();
			});
		}
	});
	
	//弹出层取消操作
	$("#pd_can").click(function() {
		$("#filter_div").hide();
		$("#pop_div").hide();
	});
});

$(window).load(function() {
	$(window).scroll(function() {
		//滚动时，记录滚动条的位置，刷新后，保持在相同的位置
		var cookietime = new Date();
		cookietime.setTime(cookietime.getTime() + (30 * 1000)); //coockie保存30秒 
	    $.cookie("scroll", $(window).scrollTop(), {expires: cookietime});
	});
});

function changeNumber(_input){
	var id = _input.parents("li[class*='goods_li']").find("a[class*='goods_check']").attr("id");
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
		ids = obj;
	}else{
		//批量删除
		var _checked = $(".goods_check[class*='gc_y']");
		_checked.each(function(){
			ids = ids + $(this).attr("id");
		});
	}
	$.ajax({
	   type: "post",
	   url: _rootUrl + "/carts/deleteProducts.htm",
	   async: false,
	   data: {ids:ids},
	   success: function(msg){
		   $("#pd_ent").unbind("click");
		   if(msg != "success"){
			   alert("删除购物车商品出现错误！");
		   }else{
			   toMyCarts();
		   }
	   }
	});
}

function checkProducts(obj, status){
	var ids ="";
	var checkeds = "";
	if(typeof obj != "undefined" && typeof status != "undefined"){
		//单个更改状态
		ids =$(obj).attr("id");
		checkeds = status;
	}else{
		//批量更改状态
		var _checkbox = $(".goods_check");
		_checkbox.each(function(){
			ids = ids + $(this).attr("id");
			if($(this).attr("class").indexOf("gc_y") != -1){
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
				   alert("勾选购物车商品出现错误！");
			   }else{
				   toMyCarts();
			   }
		   }
		});
	}
}

function amount(){
	if($(".goods_check[class*='gc_y']").size() == 0){
		alert("请选择要结算的商品！");
		return;
	}
	
	if(!_is_user_login()){
		window.location.href = _rootUrl + "/user/login.htm";
	}else{
		$(".mc_js a").hide().after($("<span style='color: red;'>正在跳转，请稍候！</span>"));//点击结算后的等待消息
		//判断库存是否充足
		$.ajax({
			   type: "post",
			   async: false,
			   url: _rootUrl + "/carts/isEnough.htm",
			   success: function(msg){
				   if(msg != "success"){
					   alert("当前存在库存不足的商品，商品状态将会发生改变！");
					   toMyCarts();
				   }else{
					   //跳转到结算页面
					   window.location.href = _rootUrl + "/carts/amount.htm";
				   }
			   }
		});
	}
}

function toMyCarts(){
	window.location.href = _rootUrl + "/carts/myCarts.htm";
}
