$(function(){
	$.getJSON(_rootUrl + "/json/category.json", function(result) {
		var _categorys = result.category;
		var _viewport_div = $(".viewport");
		
		for (var i = 0; i < _categorys.length; i++) {
			var _cay_ul = $("<ul class='clear cay_ul'></ul>");
			var _cay_ul_lis_ul=$("<ul class='clear cay_ul_lis'></ul>");
			var _cay_txt_li=$("<li class='cay_txt' ct_fid='" + _categorys[i].fid + "'>"+_categorys[i].fne+"</li><li class='cay_jiantou jt_x'></li><li></li>");
			var _cay_img_li=$("<li class='cay_"+_categorys[i].fid+"_img'></li>");
			_cay_ul.append(_cay_img_li).append(_cay_txt_li);
			
			//var _jcm_li=$("<li><a href='javascript:;'>经常买</a></li>");
			//var _zxx_li=$("<li><a href='javascript:;'>最新鲜</a></li>");
			//_cay_ul_lis_ul.append(_jcm_li).append(_zxx_li);
			for (var j = 0; j < _categorys[i].saray.length; j++) {
				var li=$("<li><a href='"+ _rootUrl + "/goods/goodsList.htm?second=" + _categorys[i].saray[j].sid +"'>"+_categorys[i].saray[j].sne+"</a></li>");
				_cay_ul_lis_ul.append(li);
			}
			var cay_ul_ds=$("<li class='cay_ul_ds'></li>");
			_cay_ul_lis_ul.append(cay_ul_ds)
			_viewport_div.append(_cay_ul).append(_cay_ul_lis_ul);
		}
		
		$(".cay_txt").click(function(e) {
			e.stopPropagation();
			var fid = $(this).attr("ct_fid");
			location.href = _rootUrl + "/goods/goodsList.htm?first=" + fid;
		});
		
		$(".cay_ul").toggle(function() {
			var _this = $(this);
			_this.next().slideDown();
			_this.find(".cay_jiantou").removeClass("jt_x").addClass("jt_s");
		}, function() {
			var _this = $(this);
			_this.next().slideUp();
			_this.find(".cay_jiantou").removeClass("jt_s").addClass("jt_x");
		});
		
	});
	
	$(".h_back").click(function(){
		location.href=_rootUrl +"/index.htm";
	});

})