var loadMore = true;
var loadNum = 12;
$(function(){
	getGoodsList(1);
	
	$(document).scroll(function(){
		if($(".viewport").is(":hidden")){
			return;
		}
		if(loadMore){
			var distance = $(document).height() - $(window).height() - $(document).scrollTop();
			if(distance == 0){
				getGoodsList(parseInt($("#page").val())+1);
			}
		}
	});		
});

function getGoodsList(page){
	window.showLoading();
	$.ajax({
		type: "get",
		url: _rootUrl + "/activity/getGFGoodsList.htm",
		data: {currPage: page, actType:$("#actType").val()},
		async: false,
		cache: false,
		dataType: "json",
		success: function(data){
			if(data.success){
				window.hideLoading();
				var html = "";
				var resule = data.result;
				if(resule.total > 0){
					if(page == 1){
						$(".goods_list").html("");
					}
					$.each(resule.entry, function(index, obj){
						html += "<li>";
						html += "<a href='javascript:;' onclick='showGoodsDetail("+obj.id+");'>";
						html += "<img src='"+_imgUrl+"/430X430"+obj.photoUrl+"' class='gl_img' />";
						html += "<p class='gl_txt'>【"+obj.actDto[0].actTypeName + "】"+obj.goodsName+"</p>";
						html += "<p class='gl_xj'>￥"+obj.actDto[0].actPrice+"<span class='gl_yj'>￥"+obj.marketPrice+"</span></p>";
						html += "</a>";
						html += "</li>";
					});
					$(".goods_list").append(html);
					$("#page").val(page);
				}
				var _totalNum = $("#totalNum");
				_totalNum.val(parseInt(_totalNum.val()) + loadNum);
				if(_totalNum.val() >= resule.total){
					loadMore = false;
				}
			}
		}
	});
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