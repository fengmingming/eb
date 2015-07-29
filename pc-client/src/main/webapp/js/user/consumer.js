$(function(){
	$("#nameMobile").keydown(function(e){
		if(e.keyCode == 13){
			getConsumer();
		}
	});
});
function getConsumer(){
	var nameMobile = $("#nameMobile").val();
	if($.trim(nameMobile) == ''){
		showMsgHint("请输入查询关键词！","gantan");
		return ;
	}else{
		location.href=_rootUrl+"/user/nameMobile.htm?nameMobile="+nameMobile;
	}
}
//用于是否显示余额
var flag = false;
function showMoney(){
	if(flag){
		flag = false;
		$(".csr_je").hide();
		$(".csr_jt_a").html("显示金额");
	}else{
		flag = true;
		$(".csr_je").show();
		$(".csr_jt_a").html("隐藏金额");
	}
}