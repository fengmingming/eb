$(function() {
	//数量加减
	$("#minus_a").click(function() {
		var _this = $(this),
			_inp = _this.parents("ul").find("input");
		if (parseInt(_inp.val()) > 1) {
			_inp.val(parseInt(_inp.val()) - 1);
			_inp.attr("tmp", _inp.val());
		}
	});
	$("#plus_a").click(function() {
		var _this = $(this),
			_inp = _this.parents("ul").find("input");
		if (parseInt(_inp.val()) < 200) {
			_inp.val(parseInt(_inp.val()) + 1);
			_inp.attr("tmp", _inp.val());
		}
	});
	//输入框改变数量
	$("#gda_inp").keyup(function() {
		var _this = $(this);
		var reg = /^[1-9][0-9]*$/;
		if (!reg.test(_this.val())) {
			_this.val(_this.attr("tmp"));
		} else if (parseFloat(_this.val()) > 200) {
			_this.attr("tmp", 200);
			_this.val(200);
		} else {
			_this.attr("tmp", _this.val());
		}
	});
	
	//加入购物车点击
	var _add_car_flag = true;//防止库存不足时，重复点击
	$("#add_carts").click(function() {
		var car_btn = $(this);
		if (_add_car_flag) {
			window.showLoading("加入中。。。");
			_add_car_flag = false;
			var goodsNum = $("#gda_inp").val();
			$.post(_rootUrl + "/carts/addCart.htm",{"goodsId":$("#goods_id").val(), "goodsNum":goodsNum},
				function(data, textStatus) {
					_add_car_flag = true;
					if (data.success) {
						window.hideLoading("加入成功！");
						//修改购物车中商品的总数量
						var _count_span = $(".ft_car_bg_img").find("span");
						_count_span.html(parseInt(_count_span.html()) + parseInt(goodsNum)).show();
						if (parseInt(_count_span.html()) > 99) {
							_count_span.html("99<b style='position: relative; top: -1px;'>+</b>");
						}
					}else{
						window.hideLoading();
						alert(data.errMsg); //加入失败
					}
				}
			,"json");
		}
	});
	
	//得到购物车中商品的总数量
	$.post(_rootUrl + "/carts/getShopCartCount.htm",
		function (data, textStatus) {
			if (data.success) {
				if (data.result != 0) {
					var _count_span = $(".ft_car_bg_img").find("span");
					_count_span.html(data.result).show();
					if (parseInt(_count_span.html()) > 99) {
						_count_span.html("99<b style='position: relative; top: -1px;'>+</b>");
					}
				}
			} else {
				alert(data.errMsg);
			}
		}
	,"json");
	
	//购物车跳转（当前页面有可能在iframe中）
	$(".ft_car").click(function(){
		top.location.href = _rootUrl + "/carts/myCarts.htm";
	});
});
