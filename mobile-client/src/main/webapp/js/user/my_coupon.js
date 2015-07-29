$(function(){
	//领取优惠券
	$("#yhq_btn").click(function(){
		var code = $("#yhq_inp").val();
		if($.trim(code) == ''){
			alert("请输入优惠券码！");
		}else{
			$.post(_rootUrl+"/pcenter/coupon/getCouponByCode.htm",{code:code},function(data){
				if(data){
					alert("领取成功！请刷新页面！");
				}else{
					alert("优惠码错误或优惠券已过期！");
				}
			},"json");
		}
	});
	//滚动条位置
	total_page = 0;
	page = 1;
	$(document).scroll(function(){
		var distance = $(document).height() - $(window).height() - $(document).scrollTop();				
		if(distance == 0){
			++page;	
			if(total_page == 0 || (parseInt(total_page) > 0 && parseInt(total_page) >= parseInt(page))){
				$.post(_rootUrl+"/pcenter/coupon/myCouponAjax.htm",{page:page,type:type},function(data){
					if(data){
						total_page = data.total;
						var result = data.entry;
						var html = "";
						$.each(result,function(idx,obj){
							html+="<div class='yhq_dv'><div class='yhq_tp_1'></div><ul class='clear yq_u'>";
							if(type != 1){
								html+="<li class='yq_u_l yq_color_2'>优惠券</li><li class='yq_u_r yq_color_2'>"+obj.limitCat+"</li></ul><div class='yq_pe yq_color_2'>￥"+obj.parValue+"</div><ul class='yq_cn'><li class='yc_line'></li><li class='yc_txt yq_color_2'>";
								html+=obj.name+"</li><li class='yc_dta yq_color_2'>"+obj.validityStart+"&nbsp;—&nbsp;"+obj.validityEnd+"</li>";
							}else{
								html+="<li class='yq_u_l yq_color_1'>优惠券</li><li class='yq_u_r yq_color_0'>"+obj.limitCat+"</li></ul><div class='yq_pe yq_color_1'>￥"+obj.parValue+"</div><ul class='yq_cn'><li class='yc_line'></li><li class='yc_txt yq_color_0'>";
								html+=obj.name+"</li><li class='yc_dta yq_color_0'>"+obj.validityStart+"&nbsp;—&nbsp;"+obj.validityEnd+"</li>";
							}							
							html+="</ul><div class='yq_kb'></div><div class='yq_dh_ln'></div><div class='yq_dh_ln'></div><div class='yq_dh_ln'></div></div>";							
						});
						$(".viewport").append(html);
					}else{
						total_page = 1;
						return ;
					}
				},"json");	
			}else{
				return ;
			}								
		}
	});
});
function getCoupon(type){
	var type = type;
	location.href=_rootUrl+"/pcenter/coupon/myCoupon.htm?type="+type;
}