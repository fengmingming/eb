$(function(){
	
	$(".h_back").click(function(){
		location.href=_rootUrl +"/index.htm";
	});
	for(var i=0; i<window.index_mobile_floor.length;i++){
		var floorobj = window.index_mobile_floor[i];
		if ($("#type").val() == floorobj.type) {
			$(".header_txt").html(floorobj.name);
		}
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
			var type = $("#type").val();

			if(parseInt(currPage) > parseInt(total_page)){
				return ;
			}else{
				window.showLoading();
				$.ajax({
					type: "post",
					url: _rootUrl + "/goods/getOEMListAjax.htm",
					data: {currPage: currPage,type: type},
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
	});	
	
});

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
