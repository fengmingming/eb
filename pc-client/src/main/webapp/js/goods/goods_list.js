$(function() {
	//每个商品的5px边框
	$(".glc_rt_gds").hover(function() {
		var _this = $(this);
		_this.css("border", "solid 1px #ababab");
		_this.find(".glc_rt_gds_bg").show();
	}, function() {
		var _this = $(this);
		_this.css("border", "solid 1px #fff");
		_this.find(".glc_rt_gds_bg").hide();
	});
	
	var _fm = $("#fm_goodsList"),
		_cp = _fm.find("#page"),
		_st = _fm.find("#sortTp");
	//综合点击
	var sortZh_li = $("#sortZh_li");
	sortZh_li.click(function() {
		delSortPriceState();
		var _this = $(this);
		_this.siblings().removeClass("grs_djt_cur");
		_this.addClass("grs_djt_cur");
		
		_cp.val(1);
		_st.val("sort_zh");
		_fm.submit();
	});
	//保存综合状态
	if (_st.val() != "" && _st.val() == "sort_zh") {
		sortZh_li.addClass("grs_djt_cur");
	}
	//新品点击
	var sortNew_li = $("#sortNew_li");
	sortNew_li.click(function() {
		delSortPriceState();
		var _this = $(this);
		_this.siblings().removeClass("grs_djt_cur");
		_this.addClass("grs_djt_cur");
		
		_cp.val(1);
		_st.val("sort_new");
		_fm.submit();
	});
	//保存新品状态
	if (_st.val() != "" && _st.val() == "sort_new") {
		sortNew_li.addClass("grs_djt_cur");
	}
	//销量点击
	var sortSale_li = $("#sortSale_li");
	sortSale_li.click(function() {
		delSortPriceState();
		var _this = $(this);
		_this.siblings().removeClass("grs_djt_cur");
		_this.addClass("grs_djt_cur");
		
		_cp.val(1);
		_st.val("sort_sale");
		_fm.submit();
	});
	//保存销量状态
	if (_st.val() != "" && _st.val() == "sort_sale") {
		sortSale_li.addClass("grs_djt_cur");
	}
	//价格点击
	var sortPrice_li = $("#sortPrice_li");
	sortPrice_li.click(function() {
		var _this = $(this);
		_this.siblings().removeClass("grs_djt_cur");
		
		_cp.val(1);
		
		if (_this.attr("class") == "grs_sjt") {//按价格降序
			_this.addClass("grs_sjt2");
			_st.val("sort_price_1");
		} else if (_this.attr("class") == "grs_sjt grs_sjt2") {//按价格升序
			_this.removeClass("grs_sjt2");
			_this.addClass("grs_sjt1");
			_st.val("sort_price_0");
		} else if (_this.attr("class") == "grs_sjt grs_sjt1") {//按价格降序
			_this.removeClass("grs_sjt1");
			_this.addClass("grs_sjt2");
			_st.val("sort_price_1");
		}
		_fm.submit();
	});
	//保存价格状态
	if (_st.val() != "") {
		if (_st.val() == "sort_price_1") {
			sortPrice_li.addClass("grs_sjt2");
		} else if (_st.val() == "sort_price_0") {
			sortPrice_li.addClass("grs_sjt1");
		}
	}
	//删除价格状态
	function delSortPriceState() {
		sortPrice_li.removeClass("grs_sjt1");
		sortPrice_li.removeClass("grs_sjt2");
	}
	
	
	//购物车背景色效果
	var car_btns = $(".glc_rt_gds .grgp_ul_car");
	car_btns.hover(function() {
		$(this).addClass("grgp_ul_car_h");
	}, function() {
		$(this).removeClass("grgp_ul_car_h");
	});
	//加入购物车特效//这里还需要改动
	car_btns.click(function() {
		var grg = $(this).closest(".glc_rt_gds"),
			_goods_id = grg.find(".grg_goods_id").val(),
			_province_id = grg.find(".grg_province_id").val(),
			_city_id = grg.find(".grg_city_id").val(),
			_district_id = grg.find(".grg_district_id").val(),
			_community_id = grg.find(".grg_community_id").val(),
			_goods_num = grg.find(".grgp_ul_num_li input").val();
		
		var car_btn = $(this),
			_fly_cart = $("#fly_cart");
		if (!_fly_cart.is(":animated")) {
			_fly_cart.show();
			_fly_cart.find("img").attr("src", car_btn.closest(".glc_rt_gds_opt").siblings(".glc_rt_gds_img").find("img").attr("src"));
			
			$.post(_rootUrl + "/goods/addCart.htm",{"_goods_id":_goods_id, "_goods_num":_goods_num},
				function(data, textStatus) {
					if (data.success) {
						
						var _l = car_btn.offset().left - 50,
							_t = car_btn.offset().top;
						_fly_cart.css({"top":_t + "px", "left":_l + "px"}).animate({"top":"-=43", "opacity":"1"}, 500, function() {
							var _t = $("#fc_div").offset().top,
								_l = $("#fc_div").offset().left;
							_fly_cart.animate({"top":_t + "px", "left":_l + "px"}, 300, function() {
								_fly_cart.fadeOut(300, function() {
									_fly_cart.css({"opacity":"0", "filter":"alpha(opacity=0)"});
								});
							});
						});
						
						//顶部气泡加数量
						updateTopBubble(_goods_num);
						//右侧气泡加数量
						updateRightBubble(_goods_num);
					} else {
						showMsgHint(data.errMsg, "gantan");//加入失败
					}
				}
			,"json");
		}
	});
	//加入收藏
	$(".grgp_op").click(function() {
		var _g_op = $(this);
		if(!_is_user_login()){
			$("#_filter, #logdiv").show();
		} else {
			var grg = $(this).closest(".glc_rt_gds"),
			_goods_id = grg.find(".grg_goods_id").val();
			$.post(_rootUrl + "/goods/addFavorite.htm",{"_goods_id":_goods_id},
				function(data, textStatus) {
					if (data.success) {
						_g_op.css("background-position", "0px 1px");
						showMsgHint("关注成功！<br /><a href='" + _rootUrl + "/favorite/toMyFavorite.htm'>查看我的关注&gt;&gt;</a>", "duigou");
					} else {
						showMsgHint(data.errMsg + "<br /><a href='" + _rootUrl + "/favorite/toMyFavorite.htm'>查看我的关注&gt;&gt;</a>", "gantan");
					}
				}
			,"json");
		}
	});
	
	//数量加减
	$(".minus_a").click(function() {
		var _this = $(this),
			_inp = _this.siblings("input");
		if (parseInt(_inp.val()) > 1) {
			_inp.val(parseInt(_inp.val()) - 1);
			_inp.attr("tmp", _inp.val());
		}
	});
	$(".plus_a").click(function() {
		var _this = $(this),
			_inp = _this.siblings("input");
		if (parseInt(_inp.val()) < 1000) {
			_inp.val(parseInt(_inp.val()) + 1);
			_inp.attr("tmp", _inp.val());
		}
	});
	//输入框改变数量
	$(".grgp_ul_inp").keyup(function() {
		var _this = $(this);
		var reg = /^[1-9][0-9]*$/;
		if (!reg.test(_this.val())) {
			_this.val(_this.attr("tmp"));
		} else if (parseInt(_this.val()) > 1000) {
			_this.attr("tmp", 1000);
			_this.val(1000);
		} else {
			_this.attr("tmp", _this.val());
		}
	});
	
//	$(".glc_rt_gds_img img").load(function() {
//		var _t = $(this);
//		if (_t.width() != _t.height()) {
//			_t.css({"position":"relative", "top":"50%", "margin-top":-_t.height()/2});
//		}
//	});
});
