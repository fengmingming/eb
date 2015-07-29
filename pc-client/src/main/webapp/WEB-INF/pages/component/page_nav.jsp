<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<style type="text/css">
	#pn_div { padding-right: 20px; padding-bottom: 20px;}
		#pageGo { float: right; font-size: 12px; margin-left: 20px;}
			#pageGo li { float: left;}
			#pageGo .pgo_li { padding: 9px 0;}
			#pageGo #pageGo_inp { border: 1px solid #ddd; padding: 7px 0; text-align: center; width: 36px;}
			#pageGo #pageGo_a { background: none repeat scroll 0 0 #f5f5f5; border: 1px solid #ddd; margin-left: 10px; padding: 8px 13px; color: #666;}
		
		#pageNav { -moz-user-select: none; -webkit-user-select: none; -ms-user-select: none; float: right; height: 35px;}
			#pageNav a { color: #666; text-decoration: none; font-size: 12px; background: #f5f5f5; margin-right: 5px;}
			#pageNav a:hover { color: #2B4A78; text-decoration: underline;}
			#pageNav a:focus, #pageNav input:focus { outline-style: none; outline-width: medium;}
			/* custom css style: pageNum,cPageNum */
			#pageNav .pageNum { border: 1px solid #ddd; padding: 8px 13px; display: inline-block;}
			#pageNav .cPageNum { font-size: 12px; color: #ff9f3f; padding: 8px 13px; margin-right: 5px; display: inline-block;}
			#pageNav a:hover { text-decoration: none; background: #fff4d8;}

</style>
<script type="text/javascript" >
	$(function() {
		var _form = $("#" + $("#fm_id_inp").val()),
			_page = _form.find("#page"),
			_total_page = _form.find("#total_page");
		
		var pageNav = {
			nav : function(p, pn) {
				//只有一页,直接显示1
			    if (pn <= 1) {
			        this.p = 1;
			        this.pn = 1;
			        return this.pHtml2(1);
			    }
			    if (pn < p) {
			        p = pn;
			    };
			    var re = "";
			    //第一页
			    if (p <= 1) {
			        p = 1;
			    } else {
			        //非第一页
			        re += this.pHtml(p - 1, pn, "上一页");
			        //总是显示第一页页码
			        re += this.pHtml(1, pn, "1");
			    }
			    //校正页码
			    this.p = p;
			    this.pn = pn;

			    //开始页码
			    var start = 2;
			    var end = (pn < 9) ? pn: 9;
			    //是否显示前置省略号,即大于10的开始页码
			    if (p >= 7) {
			        re += "...";
			        start = p - 4;
			        var e = p + 4;
			        end = (pn < e) ? pn: e;
			    }
			    for (var i = start; i < p; i++) {
			        re += this.pHtml(i, pn);
			    };
			    re += this.pHtml2(p);
			    for (var i = p + 1; i <= end; i++) {
			        re += this.pHtml(i, pn);
			    };
			    if (end < pn) {
			        re += "...";
			        //显示最后一页页码,如不需要则去掉下面这一句
			        re += this.pHtml(pn, pn);
			    };
			    if (p < pn) {
			        re += this.pHtml(p + 1, pn, "下一页");
			    };
			    return re;
			},
			pHtml : function(pageNo, pn, showPageNo) {
				showPageNo = showPageNo || pageNo;
				var H = " <a href='javascript:;' class='pageNum'>" + showPageNo + "</a> ";
			    return H;
			},
			pHtml2 : function(pageNo) {
				var H = " <span class='cPageNum'>" + pageNo + "</span> ";
			    return H;
			},
			drawPage : function(p, pn) {
				document.getElementById("pageNav").innerHTML = this.nav(parseFloat(p), parseFloat(pn));
				$("#pageNav a").click(function() {
					var p = $(this);
					if (isNaN(p.html())) {
						var cp = p.siblings(".cPageNum");
						if (p.html() == "上一页") {
							_page.val(cp.prev().html());
						} else if (p.html() == "下一页") {
							_page.val(cp.next().html());
						}
					} else {
						_page.val(p.html());
					}
					_form.submit();
				});
			}
		};
		
		pageNav.drawPage(_page.val(), _total_page.val());
		
		if (!eval($("#is_ivsible_inp").val())) {
			$("#pageGo").hide();
		} else {
			$("#pageGo_a").click(function() {
				var inp = $("#pageGo_inp");
				if (inp.val() != "") {
					if (isNaN(inp.val())) {
						_page.val(1);
					} else if (inp.val() < 0) {
						_page.val(1);
					} else if (inp.val() == 0) {
						_page.val(1);
					} else if (inp.val().indexOf(".") != -1) {
						_page.val(1);
					} else if (parseFloat(inp.val()) > parseFloat(_total_page.val())) {
						_page.val(_total_page.val());
					} else {
						_page.val(inp.val());
					}
					_form.submit();
				}
			});
			
			$("#pageGo_inp").keyup(function(e) {
				if (e.keyCode == 13) {
					$("#pageGo_a").click();
				}
			});
		}
	});
</script>
<input type="hidden" value="<%=request.getParameter("fm_id") %>" id="fm_id_inp" />
<input type="hidden" value="<%=request.getParameter("is_visible") %>" id="is_ivsible_inp" />
<div id="pn_div" class="clear">
	<ul id="pageGo" class="clear">
		<li class="pgo_li">共${total_page}页&nbsp;&nbsp;</li>
		<li class="pgo_li">到第</li>
		<li>&nbsp;<input id="pageGo_inp" type="text" />&nbsp;</li>
		<li class="pgo_li">页</li>
		<li class="pgo_li"><a id="pageGo_a" href="javascript:;">确定</a></li>
	</ul>
	<div id="pageNav"></div>
</div>
