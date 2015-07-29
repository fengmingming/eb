$(function() {
	$.getJSON(_rootUrl + "/json/category.json", function(result) {
		var _categorys = result.category;
	
		var _cgy_div = $(".cgy_div"),
			_cgy_txt = $(".cgy_txt");
		for (var i = 0; i < _categorys.length; i++) {
			_cgy_txt.eq(i).html("<a href='" + _rootUrl + "/goods/getGoodsList.htm?first=" + _categorys[i].fid + "'>" + _categorys[i].fne + "</a>");
			var _len = _categorys[i].saray.length;
			if (_len <= 6) {
				_cgy_div.eq(i).css({"width":150});
			} else if (_len <= 12) {
				_cgy_div.eq(i).css({"width":300});
			} else if (_len <= 18) {
				_cgy_div.eq(i).css({"width":450});
			}
			var _ver_div = null;
			for (var j = 0; j < _categorys[i].saray.length; j++) {
				if (j % 6 == 0) {
					_ver_div = $("<div style='height: 434px; width: 149px; border-right: solid 1px #ddd; display: inline-block;'></div>");
					_cgy_div.eq(i).append(_ver_div.append($("<div style='height: 32px; width: 149px;'></div>")));
				}//一道，二道，或者三道杠
				_ver_div.append($("<div class='cgy_div_tits'><a target='_blank' href='" + _rootUrl + "/goods/getGoodsList.htm?second=" + _categorys[i].saray[j].sid + "'>" + _categorys[i].saray[j].sne + "</a></div>"));
				if (j == _categorys[i].saray.length - 1) {
					_ver_div.css({"border":"0px"});
				}
			}
		}
	});
	
	var _sp = 300;
	
	$("#cgy_ul li").hover(function() {
		
		var _t = $(this),
			_d = _t.find(".cgy_div");
		_d.css("z-index", "-1").stop(false, true).animate({"left":"210px"}, _sp);
		_t.css({"background":"#fff"});
		_t.find(".cgy_txt a").css("color", "#75AE0F");
		_t.find(".cgy_img").css("background-position", "-28px -" + _t.attr("py") + "px");
		
	}, function() {
		var _t = $(this),
			_d = _t.find(".cgy_div");
		
		_d.css("z-index", "-2").stop(false, true).animate({"left":"-240px"}, _sp);
		
		_t.css("background", "#75AE0F");
		_t.find(".cgy_txt a").css("color", "#fff");
		_t.find(".cgy_img").css("background-position", "0px -" + _t.attr("py") + "px");
	});
	
	
	
	var bn_ig = $(".bn_ig");
	bn_ig.hide();
	bn_ig.eq(0).show();
	
	var index = 0;
	var iSize = bn_ig.length;
	var _timer = null;
	
	var ul = $("<ul class='bn_pg clear'></ul>");
	for(var i = 0; i < iSize; i++) {
		var li = $("<li class='bn_pg_li'>" + (i + 1) + "</li>");
		ul.append(li);
	}
	$("#category_con").append(ul);
	var lis = ul.find("li");
	lis.eq(0).addClass("cur");
	lis.mouseover(function(){
		index = $(this).index();
		change(index);
	});
	
	function autoshow() {
		index++;
		if(index<=iSize-1){
		   change(index);
		}else{
			index=0;
			change(index);
		}
	}
	function change(index){
		lis.removeClass("cur");
		lis.eq(index).addClass("cur");
		bn_ig.stop(false, true);
		bn_ig.eq(index).siblings(".bn_ig").fadeOut(1000);
		bn_ig.eq(index).fadeIn(1000);
	}
	_timer = setInterval(autoshow,3000);
	function clearT() {
		$("#banners, .bn_pg_li").mouseover(function() {
			clearInterval(_timer);
		});
	}
	function setT() {
		$("#banners, .bn_pg_li").mouseout(function() {
			_timer = setInterval(autoshow,3000);
		});
	}
	clearT();
	setT();
});
