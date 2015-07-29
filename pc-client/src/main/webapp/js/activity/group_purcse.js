$(function(){
	$("#hd_search").empty().css("background","none");
	
	$(".glc_rt_gds").hover(function() {
		$(this).find(".glc_rt_gds_bg").show();
	}, function() {
		$(this).find(".glc_rt_gds_bg").hide();
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
});
