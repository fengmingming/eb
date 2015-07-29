var deTp = 1 ;      // 默认为自提
var curAddrId = "dftAddr";  // 默认为亭子地址

$(function() {
    // For addr update validate
    $.validator.addMethod("mobile", function(value, element) {
            var length = value.length;
            var mobile = /^1[3,5,7,8]{1}\d{9}$/
            return this.optional(element) || (length == 11 && mobile.test(value));
        },
        "手机号码格式错误");

    $.validator.addMethod("phone", function(value, element) {
            var len = value.length;
            var pReg = /^\d{3,4}\d{7,8}$/      //带分机的 /^\d{3,4}-\d{7,8}(-\d{3,4})?$/
            return this.optional(element) || (len == 11 || len==12 && pReg.test(value));
        },
        "格式: 区号-电话");

    $("#frUpdtAddr").validate({
        rules: {
            mobi: {mobile: true},
            phone: {phone:true, minlength: 11}
        }
    });
    // End of addr validate


	$(":radio[name='zffs']").click(function() {
		if($("#yezf")[0].checked){
			$("#pay_password").show();
		}else{
			$("#pay_password").hide();
		}
	});
	$(".r_ipt").focus(function() {
		$(this).removeClass("focus_eor_ipt");
		$(this).siblings("span#hint").remove();
	});
	$("#pay_password input").focus(function() {
		$(this).removeClass("focus_eor_ipt");
		$("#cf_order_hint").hide();
		$("#cf_order_hint li").html("");
	});

    // 初始化亭子地址及默认的收货人及电话
    $("#dftDist").text(dftCity);
    $("#dftShop").text(shop);
    $("#dftRcvrName").text(buyerName);
    $("#dftRcvrMobi").text(buyerMobi);

    // Append 其它地址到默认地址后面
    // 详见jsp页面

    //地址选择事件：修改当前地址为用户的默认地址
    $("#addrTo").children('div.add_block').click(function(){
    	$(".add_block").removeClass("adbk_cur1").addClass("adbk_cur2");
    	$(".at_duigou").removeClass("at_show");
    	var _this = $(this);
        var addrId = this.id;
        if(addrId>0 && curAddrId != addrId){ // 多次点击只执行一次
            deTp = 2;
            $.ajax({
                type: "get",
                async: false,
                data: {
                    addrId:addrId
                },
                url: _rootUrl + "/address/setDftAddrByAgent.htm",
                success: function(result){
                    curAddrId = addrId;
                    var address = eval("("+result+")");
                    if(address.success){
                    	_this.removeClass("adbk_cur2").addClass("adbk_cur1");
                    	_this.find(".at_duigou").addClass("at_show");
                    }else{
                        showMsgHint("保存收货人信息出现错误！","gantan");
                    }
                }
            });
        }
        else{
            deTp = 1 ; // 自提货
            _this.removeClass("adbk_cur2").addClass("adbk_cur1");
            _this.find(".at_duigou").addClass("at_show");
        }
    });
});

function showErr(msg){
    $("#cf_order_hint li").html(msg);
    $("#cf_order_hint").show();
}

function commitOrder(){
    var commitSucceed = false;
    var mobile, receiver;
    var orderId, orderNum, payPrice;

	//余额支付，判断是否输入支付密码
	var _password = $(":password[name='password']");
	if(($("#yezf").length > 0 && $("#yezf")[0].checked) || 
			($("#cardPay").length > 0 && $("#cardPay")[0].checked)){
		if($.trim(_password.val()) == ""){
			_password.addClass("focus_eor_ipt");
            showErr("支付密码不能为空！")
		   return;
		}
	}

    if(deTp == 1){ //如果自提需传递收货地址和电话
        mobile = $("#dftRcvrMobi").text();
        receiver = $("#dftRcvrName").text();
    }
	
	$("#submit_od").hide().after($("<span style='color: red;'>正在提交订单，请稍后！&nbsp;&nbsp;</span>"));
	var pswMD5 = hex_md5(_password.val());
	var payType= $(":radio[name='zffs']:checked").val();
    if(isCardPay) //转true fasle 为int 类型的数据，否则参数类型不匹配不会进入controller
        isCardPay =1;
    else
        isCardPay = 0;
	$.ajax({
		   type: "post",
		   async: false,
		   data:{
		   	   payType:payType,
			   isCardPay:isCardPay,
               deliveryType:deTp,
			   payPassword:pswMD5,
               mobile:mobile,
               receiver:receiver
		   },
		   url: _rootUrl + "/order/commitOrder.htm",
		   success: function(data){
			   var order = eval("("+data+")");
			   if(!order.success){
				   $("#hint").remove();
				   if(order.errCode == 9999){
					   window.location.href = _rootUrl + "/main/error.htm";
				   }
                   showErr(order.errMsg)
				   $("#submit_od").siblings("span").remove();
				   $("#submit_od").show();
			   }else{
				   orderId = order.result.orderId;
				   orderNum = order.result.orderNum;
				   payPrice = order.result.payPrice;
                   commitSucceed = true;
			   }
		   },
           error: function(jqXHR,textStatus,errorThrow){
                    console.log(textStatus,errorThrow);
           }
	});

    if(commitSucceed){
        var cardPayChk = $("#cardPay").attr("checked")=="checked"
        var blcPayChk  = $("#yezf").attr("checked")=="checked"

        if(cardPayChk || blcPayChk){
            //跳转到支付结果页面
            window.location.href = _rootUrl + "/order/paymentResult.htm?orderId="+orderId+"&orderNum="+orderNum;
            window.event.returnValue=false;
        }else{
            //跳转到支付页面
            window.location.href = _rootUrl + "/order/toPayment.htm?orderNum="+orderNum+"&payPrice="+payPrice;
            window.event.returnValue=false;
        }
    }
}

function closeAddressPop() {
        // 清理旧数据
        $("#city").val("");
        $("#district").val("");
        $("#xx_address").val("");
        $("#mobi").val("");
        $("#phone").val("");
        $("#rcvr").val("");
        $("#msg_filter_div").hide();
        $("#upd_address_pop").hide();
}
