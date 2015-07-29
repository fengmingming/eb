$(function() {
	if(document.referrer != "" && document.referrer != "http://www.365020.com/"){
		return;
	}
    $("._nav li").eq(1).remove();
    var a = $("<div style='position: absolute; top: 0px; left: 0px; background: #000; z-index: 8888; opacity: 0.5; filter: alpha(opacity=50);'></div>");
    var b = $(window).width();
    var e = $(document).height();
    a.css({
        "width": b,
        "height": e
    });
    $("body").append(a);
    var c = $("<div style='width: 388px; height: 240px; background: #fff; position: fixed; top: 50%; left: 50%; margin-top: -120px; margin-left: -194px; z-index: 9999; border-radius: 5px; overflow: hidden; font-family: Microsoft Yahei;'></div>");
    $("body").append(c);
    var d = $("<div style='height: 59px; border-bottom: solid 1px #dadada; background: #f2f2f2; text-align: center; line-height: 59px; color: #757575; font-size: 22px;'>请选择城市</div>");
    c.append(d);
    var gb = $("<div style='position: absolute; top: 20px; right: 15px; font-size: 12px; cursor: pointer;'>关闭</div>");
    c.append(gb);
    var f = $("<ul style='width: 274px; margin: auto; margin-top: 26px;'>" + "<li id='_go_bj' style='float: left; color: #b4d17d; font-size: 22px; width: 120px; height: 120px; border: solid 2px #b4d17d; border-radius: 5px; line-height: 185px; text-align: center; cursor: pointer; background: url(../../images/_hb.png) no-repeat center 12px;'><label for='_cys1' style='cursor: pointer;'>北京</label></li>" + "<li id='_go_ly' style='float: left; color: #b4d17d; font-size: 22px; width: 120px; height: 120px; border: solid 2px #b4d17d; border-radius: 5px; line-height: 185px; text-align: center; cursor: pointer; background: url(../../images/_sk.png) no-repeat center 12px; margin-left: 26px;'><label for='_cys2' style='cursor: pointer;'>洛阳</label></li>" + "<div style='clear: both;'></div>" + "</ul>");
    c.append(f);
    $("#_go_bj").hover(function() {
        $(this).css({
            "background": "#b4d17d url(../../images/_hb_0.png) no-repeat center 12px",
            "color": "#fff"
        });
    }, function() {
        $(this).css({
            "background": "#fff url(../../images/_hb.png) no-repeat center 12px",
            "color": "#b4d17d"
        });
    });
    $("#_go_ly").hover(function() {
        $(this).css({
            "background": "#b4d17d url(../../images/_sk_0.png) no-repeat center 12px",
            "color": "#fff"
        });
    }, function() {
        $(this).css({
            "background": "#fff url(../../images/_sk.png) no-repeat center 12px",
            "color": "#b4d17d"
        });
    });
    gb.click(function() {
    	a.hide();
    	c.hide();
    });
    $("#_go_bj").click(function() {
    	gb.click();
    });
    $("#_go_ly").click(function() {
        window.location.href = "http://www.365020.com.cn";
    });
});