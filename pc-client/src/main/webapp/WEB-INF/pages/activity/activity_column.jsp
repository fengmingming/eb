<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="${staUrl }/js/activity/activity_column.js"></script>
<script type="text/javascript" src="${imgUrl }/index_floor.js"></script>
<script type="text/javascript">
$(function() {
	for(var i=1; i<window.index_floor.length;i++){
		$("#img_"+(i+1)).attr("src",window.index_floor[i]);
	}
});
</script>
<style type="text/css">
#a_c_div { width: 1200px; margin: auto; margin-top: 50px; padding-bottom: 50px;}
	.a_c_title { height: 50px; line-height: 50px; font-size: 20px; font-weight: bold; border-bottom: solid 3px #ff7f00; color: #ff7f00;}
		
	.a_c_ul .a_c_ul_li { float: left;}
		.a_u_l_l { width: 338px; height: 534px; border: solid 1px #ccc;}
			.a_u_l_l img { width: 338px; height: 534px; background: #999;}
		.a_u_l_r { width: 840px; padding: 0px 10px;}
			#a_c_u_l_l_ul { }
			.a_c_u_l_l_ul_li { width: 200px; padding: 5px 5px 0px 5px; height: 262px; float: left;}
				.a_c_u_l_l_ul_li img { width: 200px; height: 200px; background: #ccc;}
				.a_i_new { color: #ba3220; font-size: 20px;}
					.a_i_new span { font-size: 12px; padding-left: 5px;}
				.a_i_old { color: #666; font-size: 12px; text-decoration: line-through; margin-left: 10px;}
				.a_i_des { font-size: 12px; height: 20px; line-height: 20px; text-overflow: ellipsis; white-space: nowrap; word-wrap: normal; overflow: hidden;}
					.a_i_des a { color: #666;}
					.a_i_des a:hover { text-decoration: underline;}
</style>

<div id="a_c_div">
	<!-- 地方特产 -->
	<div class="a_c_title" id="dftc">&gt;&gt;&nbsp;&nbsp;地方特产
		<input type="hidden" id="dftcVal" value="8" />
	</div>
	<ul class="clear a_c_ul">
		<li class="a_c_ul_li a_u_l_l"><a href="${rootUrl }/goods/getOEM.htm?type=8"><img id="img_8" alt="" src="" /></a></li>
		<li class="a_c_ul_li a_u_l_r">
			<ul class="clear dftc" id="a_c_u_l_l_ul">
			
			</ul>
		</li>
	</ul>
	
	<div class="a_c_title" id="jksg">&gt;&gt;&nbsp;&nbsp;进口水果
		<input type="hidden" id="jksgVal" value="4" />
	</div>
	<ul class="clear a_c_ul">
		<li class="a_c_ul_li a_u_l_l"><a href="${rootUrl }/goods/getOEM.htm?type=4"><img id="img_4" alt="" src="" /></a></li>
		<li class="a_c_ul_li a_u_l_r">
			<ul class="clear jksg" id="a_c_u_l_l_ul">
			
			</ul>
		</li>
	</ul>
	<div class="a_c_title" id="yjsc">&gt;&gt;&nbsp;&nbsp;有机蔬菜
		<input type="hidden" id="yjscVal" value="3" />
	</div>
	<ul class="clear a_c_ul">
		<li class="a_c_ul_li a_u_l_l"><a href="${rootUrl }/goods/getOEM.htm?type=3"><img id="img_3" alt="" src="" /></a></li>
		<li class="a_c_ul_li a_u_l_r">
			<ul class="clear yjsc" id="a_c_u_l_l_ul">
			
			</ul>
		</li>
	</ul>
	
	<div class="a_c_title" id="tjsp">&gt;&gt;&nbsp;&nbsp;特价商品
		<input type="hidden" id="tjspVal" value="2" />
	</div>
	<ul class="clear a_c_ul">
		<li class="a_c_ul_li a_u_l_l"><a href="${rootUrl }/goods/getOEM.htm?type=2"><img id="img_2" alt="" src="" /></a></li>
		<li class="a_c_ul_li a_u_l_r">
			<ul class="clear tjsp" id="a_c_u_l_l_ul">
			
			</ul>
		</li>
	</ul>
	
	<div class="a_c_title" id="ydlsp">&gt;&gt;&nbsp;&nbsp;意大利食品
		<input type="hidden" id="ydlspVal" value="5" />
	</div>
	<ul class="clear a_c_ul">
		<li class="a_c_ul_li a_u_l_l"><a href="${rootUrl }/goods/getOEM.htm?type=5"><img id="img_5" alt="" src="" /></a></li>
		<li class="a_c_ul_li a_u_l_r">
			<ul class="clear ydlsp" id="a_c_u_l_l_ul">
			
			</ul>
		</li>
	</ul>
	
	<div class="a_c_title" id="hgsp">&gt;&gt;&nbsp;&nbsp;韩国食品
		<input type="hidden" id="hgspVal" value="6" />
	</div>
	<ul class="clear a_c_ul">
		<li class="a_c_ul_li a_u_l_l"><a href="${rootUrl }/goods/getOEM.htm?type=6"><img id="img_6" alt="" src="" /></a></li>
		<li class="a_c_ul_li a_u_l_r">
			<ul class="clear hgsp" id="a_c_u_l_l_ul">
			
			</ul>
		</li>
	</ul>
	
	<div class="a_c_title" id="hxsp">&gt;&gt;&nbsp;&nbsp;海鲜食品
		<input type="hidden" id="hxspVal" value="7" />
	</div>
	<ul class="clear a_c_ul">
		<li class="a_c_ul_li a_u_l_l"><a href="${rootUrl }/goods/getOEM.htm?type=7"><img id="img_7" alt="" src="" /></a></li>
		<li class="a_c_ul_li a_u_l_r">
			<ul class="clear hxsp" id="a_c_u_l_l_ul">
			
			</ul>
		</li>
	</ul>
	
	<div class="a_c_title" id="jksp">&gt;&gt;&nbsp;&nbsp;进口食品
		<input type="hidden" id="jkspVal" value="9" />
	</div>
	<ul class="clear a_c_ul">
		<li class="a_c_ul_li a_u_l_l"><a href="${rootUrl }/goods/getOEM.htm?type=9"><img id="img_9" alt="" src="" /></a></li>
		<li class="a_c_ul_li a_u_l_r">
			<ul class="clear jksp" id="a_c_u_l_l_ul">
			
			</ul>
		</li>
	</ul>
</div>
