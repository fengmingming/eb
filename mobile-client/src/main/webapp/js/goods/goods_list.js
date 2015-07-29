$(function(){
	$("#search_input").focus();
	
	$("#search_input").keyup(function() {
		if ($(this).val() != "") {
			$(".hd_s_qx").show();
		} else {
			$(".hd_s_qx").hide();
		}
	});
	
	$("#qx_a").click(function(){
		$("#search_input").val("");
		$(".hd_s_qx").hide();
	});
	
	$(".h_back").click(function(){
		if(location.pathname=="/mobile/goods/getGoodsListBySearch.htm"){
			location.href=_rootUrl +"/index.htm";
		}else{
			//console.log(-(history.length-2));
			//history.go(-(history.length-2));
			location.href=_rootUrl +"/category.htm";
		}
	});
	
	var sortTp = $("#sortTp").val();
	if(sortTp == "sort_sale"){
		$("#sale").addClass("gl_cur_a");
		$("#sale").children(".gl_s_jt").addClass("gl_s_jt_cur");
	}else if(sortTp == "sort_price_1"){
		$("#price").addClass("gl_cur_a");
		$("#price").children(".gl_s_sxjt").addClass("gl_s_sxjt1");
	}else if(sortTp == "sort_price_0"){
		$("#price").addClass("gl_cur_a");
		$("#price").children(".gl_s_sxjt").addClass("gl_s_sxjt2");
	}else{
		$("#default").addClass("gl_cur_a");
	}
	$(document).scroll(function(){
		if($(".viewport").is(":hidden")){
			return;
		}
		var distance = $(document).height() - $(window).height() - $(document).scrollTop();
		if(distance == 0){
			var page = $("#page");	
			var total_page = $("#total_page").val();
			var currPage = parseInt(page.val()) + 1;
			page.val(currPage);
			var first = $("#first").val();
			var content = $("#content").val();
			var second = $("#second").val();
			var sortTp = $("#sortTp").val();
			if(parseInt(currPage) > parseInt(total_page)){
				return ;
			}else{
				if($.trim(content) != ''){
					window.showLoading();
					$.ajax({
						type: "post",
						url: _rootUrl + "/goods/goodsListBySearch.htm",
						data: {currPage: currPage,content: content, sortTp: sortTp},
						async: true,
						cache: false,
						dataType: "json",
						success: function(data){
							if(data != undefined){	
								window.hideLoading();
								var text = "";
								$.each(data,function(key,val){
									text += "<li><a href='javascript:;' onclick='showGoodsDetail("+val.id+");'><img src='"+_imgUrl+"/200X200"+val.photoUrl+"' class='gl_img' /><p class='gl_txt'>"+val.goodsName+"</p><p class='gl_xj'>￥"+val.price+"<span class='gl_yj'>"+val.marketPrice+"</span></p></a></li>";				
								});
								$(".clear.goods_list").append(text);								
							}
						}			
					});	
				}else{
					window.showLoading();
					$.ajax({
						type: "post",
						url: _rootUrl + "/goods/getGoodsList.htm",
						data: {currPage: currPage,first: first, second: second, sortTp: sortTp},
						async: true,
						cache: false,
						dataType: "json",
						success: function(data){
							if(data != undefined){										
								window.hideLoading();
								var result = data.entry;
								if(result == undefined){
									return ;
								}else{
									var text = "";
									$.each(result,function(key,val){	
										text += "<li><a href='javascript:;' onclick='showGoodsDetail("+val.id+");'><img src='"+_imgUrl+"/200X200"+val.photoUrl+"' class='gl_img' /><p class='gl_txt'>"+val.goodsName+"</p><p class='gl_xj'>￥"+val.price+"<span class='gl_yj'>"+val.marketPrice+"</span></p></a></li>";				
									});
									$(".clear.goods_list").append(text);	
								}
							}
						}			
					});		
				}
					
			}			
		}
	});	
	
	//搜索
	$(".hd_s_fdj").click(function(){
		//var content = $(".hd_s_mid").children().val();
		var content = $("#search_input").val();
		if($.trim(content) == ''){
			//alert("请输入查询关键词");
		}else{
			$("#content").val(content);
			$("#page").val(1);
			$("#fm_goodsList").attr("action",_rootUrl+"/goods/getGoodsListBySearch.htm");
			$("#fm_goodsList").submit();
		}
	});
});
//商品页面根据条件查询
function changeDefault(obj){
	//var price = $("#price");
	//price.removeClass("gl_cur_a");
	//price.find("i").removeClass("gl_s_sxjt1").removeClass("gl_s_sxjt2");
	//var sale = $("#sale");
	//sale.removeClass("gl_cur_a");
	//sale.find("i").removeClass("gl_s_jt_cur");
	//$(obj).addClass("gl_cur_a");
	$("#page").val(1);
	$("#sortTp").val("");
	if($.trim($("#content").val()) == ''){
		$("#fm_goodsList").submit();
	}else{
		$("#fm_goodsList").attr("action",_rootUrl+"/goods/getGoodsListBySearch.htm");
		$("#fm_goodsList").submit();
	}
	
}
function changePrice(obj){	
	/*
	var def = $("#default");
	def.removeClass("gl_cur_a");
	var sale = $("#sale");
	sale.removeClass("gl_cur_a");
	sale.find("i").removeClass("gl_s_jt_cur");
	$(obj).addClass("gl_cur_a");
	*/
	if($(obj).children().eq(0).attr("class") == "gl_s_sxjt"){
		//$(obj).children().eq(0).addClass("gl_s_sxjt1");
		$("#sortTp").val("sort_price_1");
	}else if($(obj).children().eq(0).attr("class") == "gl_s_sxjt gl_s_sxjt1"){
		//$(obj).children().eq(0).removeClass("gl_s_sxjt1");
		//$(obj).children().eq(0).addClass("gl_s_sxjt2");
		$("#sortTp").val("sort_price_0");
	}else{
		//$(obj).children().eq(0).removeClass("gl_s_sxjt2");
		//$(obj).children().eq(0).addClass("gl_s_sxjt1");
		$("#sortTp").val("sort_price_1");
	}
	$("#page").val(1);		
	if($.trim($("#content").val()) == ''){
		$("#fm_goodsList").submit();
	}else{
		$("#fm_goodsList").attr("action",_rootUrl+"/goods/getGoodsListBySearch.htm");
		$("#fm_goodsList").submit();
	}
	
}

function changeSale(obj){	
	/*
	var def = $("#default");
	def.removeClass("gl_cur_a");
	var price = $("#price");
	price.removeClass("gl_cur_a");
	price.find("i").removeClass("gl_s_sxjt1").removeClass("gl_s_sxjt2");
	$(obj).addClass("gl_cur_a");
	$(obj).children().eq(0).addClass("gl_s_jt_cur");
	*/
	$("#page").val(1);
	$("#sortTp").val("sort_sale");
	if($.trim($("#content").val()) == ''){
		$("#fm_goodsList").submit();
	}else{
		$("#fm_goodsList").attr("action",_rootUrl+"/goods/getGoodsListBySearch.htm");
		$("#fm_goodsList").submit();
	}
	
}

function showGoodsDetail(goodsId){
	var _goods_detail = $(".goods_detail");
	var _iframe = _goods_detail.find("iframe");
	_iframe.attr("src", _rootUrl+"/goods/goodsDetail.htm?id=" + goodsId);
	_iframe.load(function(){
		window.setTimeout(function() {_iframe.height(_iframe.contents().find("body").height());}, 100);
		$(this).width($(window).width());//兼容iphone手机
	});
	
	//记录滚动条的位置
	var cookietime = new Date();
	cookietime.setTime(cookietime.getTime() + (60 * 60 * 1000)); //coockie保存1小时 
    $.cookie("goods_list_scroll", $(window).scrollTop(), {expires: cookietime});
    
	_goods_detail.show();
	$(".viewport").hide();
}

function backToGoodsList(){
	var _goods_detail = $(".goods_detail");
	_goods_detail.hide();
	$(".viewport").show();
	
	//恢复滚动条位置
	$(window).scrollTop($.cookie("goods_list_scroll"));
	
	_goods_detail.find("iframe").attr("src", "");
}
