<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<style type="text/css">
	#zoom{z-index:99990;position:fixed;top:0;left:0;display:none;width:100%;height:100%;background:rgba(0,0,0,0.8);filter:"progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000)";-ms-filter:"progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000)"}
	#zoom .content{z-index:99991;position:absolute;top:50%;left:50%;width:200px;height:200px;background:#fff no-repeat 50% 50%;padding:0;margin:-100px 0 0 -100px;box-shadow:-20px 20px 20px rgba(0,0,0,0.3);border-radius:4px}
	#zoom .content.loading{background-image:url('../../images/zoom/loading.gif')}
	#zoom img{display:block;max-width:none;background:#ececec;box-shadow:0 1px 3px rgba(0,0,0,0.25);border-radius:4px}
	#zoom .close{z-index:99993;position:absolute;top:0;right:0;width:49px;height:49px;cursor:pointer;background:transparent url('../../images/zoom/icons/close.png') no-repeat 50% 50%;opacity:1;filter:alpha(opacity=100);border-radius:0 0 0 4px}
	#zoom .previous,#zoom .next{z-index:99992;position:absolute;top:50%;overflow:hidden;display:block;width:49px;height:49px;margin-top:-25px}
	#zoom .previous{left:0;background:url('../../images/zoom/icons/arrows.png') no-repeat 0 0;border-radius:0 4px 4px 0}
	#zoom .next{right:0;background:url('../../images/zoom/icons/arrows.png') no-repeat 100% 0;border-radius:4px 0 0 4px}
	#zoom .close:hover{background-color:#da4f49}
	#zoom .previous:hover,#zoom .next:hover{background-color:#08c}
</style>

<script type="text/javascript">
	$(function(){$("body").append('<div id="zoom"><a class="close"></a><a href="#previous" class="previous"></a><a href="#next" class="next"></a><div class="content loading"></div></div>');var j=$("#zoom").hide(),f=$("#zoom .content"),b=false,h=null,c=$(window).width(),a=$(window).height();function g(n){if(n){n.preventDefault()}var m=$(this),p=m.attr("src");if(!p){return}var o=$(new Image()).hide();$("#zoom .previous, #zoom .next").show();if(m.hasClass("zoom")){$("#zoom .previous, #zoom .next").hide()}if(!b){b=true;j.show();$("body").addClass("zoomed")}f.html(o).delay(200).addClass("loading");o.load(l).attr("src",p);h=m;function l(){var w=$(this),r=parseInt(c),x=parseInt(a),u=w.width(),q=w.height();if(u==f.width()&&u<=r&&q==f.height()&&q<=x){s(w);return}if(u>r||q>x){var v=x<q?x:q,t=r<u?r:u;if(v/q<=t/u){w.width(parseInt(u*v/q));w.height(v)}else{w.width(t);w.height(parseInt(q*t/u))}}f.animate({width:parseInt(w.width()),height:parseInt(w.height()),marginTop:-(parseInt(w.height()/2)),marginLeft:-(parseInt(w.width()/2))},200,function(){s(w)});function s(y){y.show();f.removeClass("loading")}}}function d(){var l=$(".gidvIg img:visible");var m=l.index(h);if(m==0){l.eq(l.length-1).trigger("click")}else{l.eq(--m).trigger("click")}}function k(){var l=$(".gidvIg img:visible");var m=l.index(h);if(m==(l.length-1)){l.eq(0).trigger("click")}else{l.eq(++m).trigger("click")}}function i(l){if(l){l.preventDefault()}b=false;h=null;j.hide();$("body").removeClass("zoomed");f.empty()}function e(){c=$(window).width();a=$(window).height()}window.bindNavigation=function(){j.unbind("click").on("click",function(l){l.preventDefault();if($(l.target).attr("id")=="zoom"){i()}});$("#zoom .close").unbind("click").on("click",i);$("#zoom .previous").unbind("click").on("click",d);$("#zoom .next").unbind("click").on("click",k);$(".gidvIg img").unbind("click").on("click",g)};bindNavigation();$(window).on("resize",e)});
</script>
