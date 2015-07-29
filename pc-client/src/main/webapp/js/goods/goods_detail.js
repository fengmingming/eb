//鼠标经过预览图片函数
function preview(img){
	$("#preview .jqzoom img").attr("src",$(img).attr("src").replace("58X58", "430X430"));
	$("#preview .jqzoom img").attr("jqimg",$(img).attr("bimg"));
	$("#jqPreload img").attr("src",$(img).attr("bimg"));
}
$(function() {
	//图片放大镜效果
	$(".jqzoom").jqueryzoom({xzoom:430,yzoom:498});
	
	var tempLength = 0; //临时变量,当前移动的长度
	var viewNum = 4; //设置每次显示图片的个数量
	var moveNum = 1; //每次移动的数量
	var moveTime = 300; //移动速度,毫秒
	var scrollDiv = $(".spec-scroll .items ul"); //进行移动动画的容器
	var scrollItems = $(".spec-scroll .items ul li"); //移动容器里的集合
	var moveLength = scrollItems.eq(0).innerWidth() * moveNum; //计算每次移动的长度
	var countLength = (scrollItems.length - viewNum) * scrollItems.eq(0).innerWidth(); //计算总长度,总个数*单个长度

	//下一张
	$(".spec-scroll .next").bind("click",function(){
		if(tempLength < countLength){
			if((countLength - tempLength) > moveLength){
				scrollDiv.animate({left:"-=" + moveLength + "px"}, moveTime);
				tempLength += moveLength;
			}else{
				scrollDiv.animate({left:"-=" + (countLength - tempLength) + "px"}, moveTime);
				tempLength += (countLength - tempLength);
			}
		}
	});
	//上一张
	$(".spec-scroll .prev").bind("click",function(){
		if(tempLength > 0){
			if(tempLength > moveLength){
				scrollDiv.animate({left: "+=" + moveLength + "px"}, moveTime);
				tempLength -= moveLength;
			}else{
				scrollDiv.animate({left: "+=" + tempLength + "px"}, moveTime);
				tempLength = 0;
			}
		}
	});
	
	//立即购买点击
	$("#gdo_buy").click(function() {
		$(this).unbind("click"); //解除click绑定，防止用户多次点击
		buyNow();
		$(this).bind("click", buyNow); //绑定click
	});
	
	//数量加减
	$(".minus_a").click(function() {
		var _this = $(this),
			_inp = _this.siblings("input");
		if (parseInt(_inp.val()) > 1) {
			_inp.val(parseInt(_inp.val()) - 1);
			_inp.attr("tmp", _inp.val());
		}
	});
	$(".plus_a").click(function() {
		var _this = $(this),
			_inp = _this.siblings("input");
		if (parseInt(_inp.val()) < 1000) {
			_inp.val(parseInt(_inp.val()) + 1);
			_inp.attr("tmp", _inp.val());
		}
	});
	//输入框改变数量
	$("#gda_inp").keyup(function() {
		var _this = $(this);
		var reg = /^[1-9][0-9]*$/;
		if (!reg.test(_this.val())) {
			_this.val(_this.attr("tmp"));
		} else if (parseFloat(_this.val()) > 1000) {
			_this.attr("tmp", 1000);
			_this.val(1000);
		} else {
			_this.attr("tmp", _this.val());
		}
	});
	
	//加入购物车点击
	var _add_car_flag = true;//防止库存不足时，重复点击
	$("#gdo_car").click(function() {
		var car_btn = $(this),
			_fly_cart = $("#fly_cart");
		if (!_fly_cart.is(":animated")) {
			_fly_cart.show();
			_fly_cart.find("img").attr("src", $(".items img").eq(0).attr("src"));
			
			if (_add_car_flag) {
				_add_car_flag = false;
			
				var _goods_num = $("#gda_inp").val();
				$.post(_rootUrl + "/goods/addCart.htm",{"_goods_id":$("#gd_des_id").val(), "_goods_num":_goods_num},
					function(data, textStatus) {
						if (data.success) {
							
							var _l = car_btn.offset().left - 50,
								_t = car_btn.offset().top;
							_fly_cart.css({"position":"absolute", "top":_t + "px", "left":_l + "px"}).animate({"top":"-=43", "opacity":"1"}, 500, function() {
								var _t = $("#fc_div").offset().top,
									_l = $("#fc_div").offset().left;
								_fly_cart.animate({"top":_t + "px", "left":_l + "px"}, 300, function() {
									_fly_cart.fadeOut(300, function() {
										_fly_cart.css({"opacity":"0", "filter":"alpha(opacity=0)"});
									});
								});
							});
							_add_car_flag = true;
							
							//顶部气泡加数量
							updateTopBubble(_goods_num);
							//右侧气泡加数量
							updateRightBubble(_goods_num);
						} else {
							showMsgHint(data.errMsg, "gantan"); //加入失败
							_add_car_flag = true;
						}
					}
				,"json");
			}
		}
	});
	
	//加入收藏
	$("#gdo_col").click(addToFavorite);
	
	//如果图片过大，控制最大宽度
	$("#glc_rt img").each(function() {
		$(this).load(function() {
			if ($(this).width() > 980) {
				$(this).width(980);
			}
		});
	});
	
	//剩余时间特效
	var intDiff = parseInt($("#total_seconds").val());//倒计时总秒数量
	var _timer = null;
	function timer(intDiff){
		_timer = window.setInterval(go_timer, 1000);
	}
	function go_timer() {
		var day=0,
		hour=0,
		minute=0,
		second=0;//时间默认值		
		if(intDiff > 0){
			day = Math.floor(intDiff / (60 * 60 * 24));
			hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
			minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
			second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
		} else {
			clearInterval(_timer);
		}
		if (minute <= 9) minute = '0' + minute;
		if (second <= 9) second = '0' + second;
		$('#day_show').html(day);
		$('#hour_show').html(hour);
		$('#minute_show').html(minute);
		$('#second_show').html(second);
		intDiff--;
	}
	go_timer();
	timer(intDiff);
	
	//商品详情页内容过长，滚动时，底部出现加入购物车按钮
	$(window).scroll(function(){
		if ($(window).scrollTop()>720){
			$("#gdo_car").addClass("god_car_bto");
		}else{
			$("#gdo_car").removeClass("god_car_bto");
		}
	});
});

function buyNow(){
	$.ajax({
		type: "post",
	    async: false,
	    url: _rootUrl + "/goods/buyNow.htm",
	    data: {
	    	_goods_id:$("#gd_des_id").val(),
	    	_goods_num:$("#gda_inp").val()
	    },
	    success: function(data){
	    	var result = eval("("+data+")");
	    	if(result.success){
	    		window.location.href = _rootUrl + "/carts/myCarts.htm";
	    	}else{
	    		showMsgHint(result.errMsg, "gantan");
	    	}
	   }
	});
}

//判断登陆后执行哪种操作的flag
var afterLoginFlag = "";
var _fav_flag = true;//防止连续点击
var _gdo_col = $("#gdo_col");
function addToFavorite() {
	if(!_is_user_login()){
		afterLoginFlag = "addToFav";
		$("#_filter, #logdiv").show();
	} else {
		if (_fav_flag) {
			_fav_flag = false;
			$.post(_rootUrl + "/goods/addFavorite.htm",{"_goods_id":$("#gd_des_id").val()},
				function(data, textStatus) {
					if (data.success) {
						_gdo_col.css({"background":"#f7f7f7", "color":"#ff9326"});
						_gdo_col.find("#gdo_col_ig").css({"background-position":"0px 1px"});
						_gdo_col.find("span").html("已关注");
						showMsgHint("关注成功！<br /><a href='" + _rootUrl + "/favorite/toMyFavorite.htm'>查看我的关注&gt;&gt;</a>", "duigou");
						_fav_flag = true;
					} else {
						showMsgHint(data.errMsg + "<br /><a href='" + _rootUrl + "/favorite/toMyFavorite.htm'>查看我的关注&gt;&gt;</a>", "duigou");
						_fav_flag = true;
					}
				}
			,"json");
		}
	}
}

function afterLogin(){
	if(afterLoginFlag == "addToFav"){
		addToFavorite();
	}
}
