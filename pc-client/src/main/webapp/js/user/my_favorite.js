$(function() {
	//触发Enter事件
	$("#goodsName").keyup(function(e){
		if(e.keyCode == 13){
			$(this).parent().next().children().click();
		}
	});
	
});
//通过商品名称查询商品信息
function query_goods_goodsName(){
	var goodsName = $("#goodsName").val();
	if($.trim(goodsName) == ''){
		showMsgHint("请输入查询关键词！","gantan");
		return ;
	}
	$("input[name='goodsName']").val(goodsName);
	$("#page").val(1);
	$("#fm_my_favorite").submit();
}
//取消关注
function cancleFavorite(pm){
	var goodsId = pm;
	var page = $("#page").val();
	showMsgCfmHint("提示","确认取消关注吗？","gantan",function(){
		window.location.href=_rootUrl+"/favorite/cancleFavorite.htm?goodsId="+goodsId+"&page="+page;
	});
}
//加入购物车
function addCart(id){
	var _goods_id = id;
	var _goods_num = 1;
	window.showLoading("加入中。。。");
	$.post(_rootUrl + "/goods/addCart.htm",{"_goods_id":_goods_id, "_goods_num":_goods_num},
			function(data, textStatus) {
				if (data.success) {					
					//顶部气泡加数量
					updateTopBubble(_goods_num);
					window.hideLoading("加入成功！");
					//showMsgHint("加入成功！", "duigou");//加入失败
					//setTimeout("hideMsgHint()", 1000);
				} else {
					window.hideLoading();
					//showMsgHint(data.errMsg, "gantan");//加入失败
				}
			}
		,"json");
}
//更新头部购物车气泡数量
function updateTopBubble(num) {
	var _hd_mc_qp = $("#hd_mc_qp");
	if (_hd_mc_qp.html() != "") {
		var _cur_num = parseFloat(_hd_mc_qp.html());
		_hd_mc_qp.html(_cur_num + parseFloat(num)).show();
	} else {
		_hd_mc_qp.html(num).show();
	}
	
	maxBubble(_hd_mc_qp);
}
//超过100显示99+
function maxBubble(obj) {
	if (parseFloat(obj.html()) > 99) {
		obj.html("99<b style='position: relative; top: -1px;'>+</b>");
	}
}