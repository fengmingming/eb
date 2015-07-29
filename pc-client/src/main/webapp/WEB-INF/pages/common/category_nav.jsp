<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 
<script type="text/javascript">
!window.jQuery && document.write('<script src="js/common/jquery-1.8.0.min.js"><\/script>'); 
</script>
 -->
<script type="text/javascript" src="${staUrl }/js/common/jQueryRotate.js"></script>
<script type="text/javascript">
	$(function() {
		var _cn_cgy_ul = $("#cn_cgy_ul"),
			_cn_tit = $("#cn_tit"),
			_cn_tit_sj = $("#cn_tit_sj"),
			_sd = 200;
		_cn_tit.hover(function() {
			_cn_tit.stop(false).animate({"height":"484"}, _sd);
			_cn_cgy_ul.stop(false).animate({"top":"50"}, _sd);
			_cn_tit_sj.rotate({ animateTo:180,duration:200});
		}, function(e) {
			if ((e.clientY < 160.5) && (e.clientY > 108.5) && (e.clientX > _cn_tit.offset().left + 210)) {
				if (_cn_tit.width() == 210) {
					_cn_tit.stop(false).animate({"height":"50", "width":"210"}, _sd);
					_cn_cgy_ul.stop(false).animate({"top":"-384"}, _sd);
					_cn_tit_sj.rotate({ animateTo:0,duration:200});
				}
				return false;
			} else {
				$(".cn_cgy_div").promise().done(function() {
					_cn_tit.stop(false).animate({"height":"50", "width":"210"}, _sd);
					_cn_cgy_ul.stop(false).animate({"top":"-384"}, _sd);
					_cn_tit_sj.rotate({ animateTo:0,duration:200});
				});
			}
		});
		
		$("#cn_cgy_ul li").hover(function(e) {
			e.stopPropagation();
			
			var _t = $(this),
				_d = _t.find(".cn_cgy_div");
			_t.css({"background":"#fff"});
			_t.find(".cn_cgy_txt a").css("color", "#9ec052");
			_t.find(".cn_cgy_img").css("background-position", "-28px -" + _t.attr("py") + "px");
			_d.css("z-index", "-1").stop(false, true).animate({"left":"210px"}, _sd);
			
			_cn_tit.stop(false).animate({"height":"484", "width":(_d.width() + 211)}, _sd);
			_cn_cgy_ul.stop(false).animate({"top":"50"}, _sd);
			_cn_tit_sj.rotate({ animateTo:180,duration:200});
		}, function(e) {
			
			var _t = $(this),
				_d = _t.find(".cn_cgy_div");
			_t.css("background", "#9ec052");
			_t.find(".cn_cgy_txt a").css("color", "#fff");
			_t.find(".cn_cgy_img").css("background-position", "0px -" + _t.attr("py") + "px");
			_d.css("z-index", "-2").stop(false, true).animate({"left":"-241px"}, _sd, function() {
				if (e.clientX < _cn_tit.offset().left || e.clientX > (_cn_tit.offset().left + 360) || (e.clientY + $(window).scrollTop()) > 590 || (e.clientY + $(window).scrollTop()) < 160.5) {
					_cn_tit.stop(false).animate({"height":"50", "width":"210"}, _sd);
					_cn_cgy_ul.stop(false).animate({"top":"-384"}, _sd);
					_cn_tit_sj.rotate({ animateTo:0,duration:200});
				}
			});
		});
		
		
		$.getJSON(_rootUrl + "/json/category.json", function(result) {
			var _categorys = result.category;
		
			var _cn_cgy_div = $(".cn_cgy_div"),
				_cn_cgy_txt = $(".cn_cgy_txt");
			for (var i = 0; i < _categorys.length; i++) {
				_cn_cgy_txt.eq(i).html("<a href='" + _rootUrl + "/goods/getGoodsList.htm?first=" + _categorys[i].fid + "'>" + _categorys[i].fne + "</a>");
				var _len = _categorys[i].saray.length;
				if (_len <= 6) {
					_cn_cgy_div.eq(i).css({"width":150});
				} else if (_len <= 12) {
					_cn_cgy_div.eq(i).css({"width":300});
				} else if (_len <= 18) {
					_cn_cgy_div.eq(i).css({"width":450});
				}
				var _ver_div = null;
				for (var j = 0; j < _categorys[i].saray.length; j++) {
					if (j % 6 == 0) {
						_ver_div = $("<div style='height: 434px; width: 149px; border-right: solid 1px #ddd; display: inline-block;'></div>");
						_cn_cgy_div.eq(i).append(_ver_div.append($("<div style='height: 32px; width: 149px;'></div>")));
					}
					_ver_div.append($("<div class='cn_cgy_div_tits'><a target='_blank' href='" + _rootUrl + "/goods/getGoodsList.htm?second=" + _categorys[i].saray[j].sid + "'>" + _categorys[i].saray[j].sne + "</a></div>"));
					if (j == _categorys[i].saray.length - 1) {
						_ver_div.css({"border":"0px"});
					}
				}
			}
			
			//修改title值
			var _first = $("#first"),
				_second = $("#second");
			if (_first[0] && _second[0]) {
				if (_first.val() != "" && !isNaN(_first.val())) {
					$("title").html(getCategoryName(_first.val()) + "&nbsp;-&nbsp;商品列表&nbsp;-&nbsp;手拉手");
				} else if (_second.val() != "" && !isNaN(_second.val())) {
					$("title").html(getCategoryName(_second.val()) + "&nbsp;-&nbsp;商品列表&nbsp;-&nbsp;手拉手");
				}
			}
			
			function getCategoryName(fsid) {
				var str = "";
				var flag = true;
				for (var i = 0; i < _categorys.length; i++) {
					if (fsid == _categorys[i].fid) {
						str = _categorys[i].fne;
						flag = false;
						break;
					}
				}
				
				if (flag) {
					for (var i = 0; i < _categorys.length; i++) {
						for (var j = 0; j < _categorys[i].saray.length; j++) {
							if (fsid == _categorys[i].saray[j].sid) {
								str = _categorys[i].saray[j].sne;
								flag = false;
								break;
							} 
						}
						if (!flag) {
							break;
						}
					}
				}
				return str;
			}
		});
		
		adaptShow();//窗口缩小或最大化的时候，位置会有所改变
		$(window).resize(function() {
			adaptShow();
		});
		function adaptShow() {
			if ($(window).width() <= 1200) {
				$("#cn_tit").removeClass("adapt_show");
			} else {
				$("#cn_tit").addClass("adapt_show");
			}
		}
	});
</script>
<style type="text/css">
	#cn_tit { width: 210px; height: 50px; position: absolute; top: 110px; z-index: 10; overflow: hidden; cursor: default;}
		.adapt_show { left: 50%; margin-left: -600px;}
		#cn_tit_sj { display: inline-block; width: 16px; height: 8px; background: url(../images/inner.png) no-repeat 0px -186px; position: absolute; left: 165px; top: 22px; z-index: 3;}
		#shade { width: 210px; height: 50px; position: absolute; background: #f2f2f2; top: 0px; left: 0px; z-index: 2; line-height: 50px; text-align: center; color: #666; text-shadow: 0px 0px 1px #ccc;}
		
		#cn_cgy_ul { position: relative; top: -384px; left: 0px; width: 210px; z-index: 1;}
			#cn_cgy_ul li { height: 62px; width: 210px; cursor: default; background: #9ec052; text-align: center;}
			.cn_cgy_img { position: relative; background: url(../../images/category_s.png) no-repeat; width: 28px; height: 27px; left: -48px; top: 8px; display: inline-block;}
				#cn_cgy_sc .cn_cgy_img { background-position: 0px 0px;}
				#cn_cgy_rl .cn_cgy_img { background-position: 0px -29px;}
				#cn_cgy_sg .cn_cgy_img { background-position: 0px -57px;}
				#cn_cgy_ly .cn_cgy_img { background-position: 0px -86px;}
				#cn_cgy_js .cn_cgy_img { background-position: 0px -114px;}
				#cn_cgy_hx .cn_cgy_img { background-position: 0px -142px;}
				#cn_cgy_ls .cn_cgy_img { background-position: 0px -171px;}
				.cn_cgy_txt { height: 62px; line-height: 62px; font-size: 16px; margin-left: -30px; color: #fff;}
					.cn_cgy_txt a { color: #fff;}
					.cn_cgy_txt a:hover { text-decoration: underline;}
				.cn_cgy_div { /*width: 299px;*/ height: 432px; border: solid 1px #ddd; border-left: 0px; position: absolute; top: 0px; /*left: -90px;*/ left: -240px; background: #fff; z-index: -2; /*opacity: 0; filter: alpha(opacity=0);*/}
					.cn_cgy_div_tit { font-size: 20px; color: #666; height: 86px; line-height: 86px; padding-left: 48px; border-bottom: solid 1px #ddd;}
						.cn_cgy_div_tit a { color: #666;}
						.cn_cgy_div_tit a:hover { color: #ff7f00; text-decoration: underline;}
					.cn_cgy_div_tits { width: 149px; padding: 20px 0px; float: left; /*text-indent: 50px;*/ text-align: center;}
						.cn_cgy_div_tits a { color: #666;}
						.cn_cgy_div_tits a:hover { color: #ff7f00; text-decoration: underline;}
</style>
<div id="cn_tit" class="adapt_show">
	<div id="shade">全部商品分类</div>
	<span id="cn_tit_sj"></span>
	<ul id="cn_cgy_ul">
		<li id="cn_cgy_sc" py="0">
			<span class="cn_cgy_img"></span>
			<span class="cn_cgy_txt">生鲜蔬菜</span>
			<div class="cn_cgy_div"></div>
		</li>
		<li id="cn_cgy_rl" py="29">
			<span class="cn_cgy_img"></span>
			<span class="cn_cgy_txt">精品肉类</span>
			<div class="cn_cgy_div"></div>
		</li>
		<li id="cn_cgy_sg" py="57">
			<span class="cn_cgy_img"></span>
			<span class="cn_cgy_txt">精选水果</span>
			<div class="cn_cgy_div"></div>
		</li>
		<li id="cn_cgy_ly" py="86">
			<span class="cn_cgy_img"></span>
			<span class="cn_cgy_txt">精品粮油</span>
			<div class="cn_cgy_div"></div>
		</li>
		<li id="cn_cgy_js" py="114">
			<span class="cn_cgy_img"></span>
			<span class="cn_cgy_txt">酒水饮料</span>
			<div class="cn_cgy_div"></div>
		</li>
		<li id="cn_cgy_hx" py="142">
			<span class="cn_cgy_img"></span>
			<span class="cn_cgy_txt">生猛海鲜</span>
			<div class="cn_cgy_div"></div>
		</li>
		<li id="cn_cgy_ls" py="171">
			<span class="cn_cgy_img"></span>
			<span class="cn_cgy_txt">休闲零食</span>
			<div class="cn_cgy_div"></div>
		</li>
	</ul>
</div>
