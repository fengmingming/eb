<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
	<title>手拉手触屏版</title>
	<%@include file="../common/common.jsp" %>
	<link rel='stylesheet' href='${staUrl }/css/user/pay_list.css' type='text/css' />
	<script type="text/javascript">
		(function($){
			var page = 1;
			var rows = 10;
			var isLoad = true;
			var isLoading = false;
			var loadWalletList = function(){
				if(!isLoading&&isLoad){
					isLoading = true;
					window.showLoading();
					$.post("${dynUrl}/pcenter/accountDetails/list.htm",{page:page++,rows:rows},function(data){
						if(data.status == "302"){
							location.href = data.location;
							 return ;
						}
						isLoading = false;
						window.hideLoading();
						if(data.success){
							data = data.result.entry;
							var html = "";
							$.each(data,function(i,d){
								html = html + '<li class="clear pl_obj"><span class="pl_obj_txt">' +d.remark+ '</span>';
								if(d.TYPE == 1){
									html = html + '<span class="pl_obj_num num_a">+'+d.money+'</span>';
								}else{
									html = html + '<span class="pl_obj_num num_a" style="color:red">-'+d.money+'</span>';
								}
								html = html + '<span class="pl_obj_time">'+d.createTime+'</span>';
							});
							$("#data_view").append(html);
						}else{
							alert(data.errMsg);
						}
					},"json");
				}
			}
			$(document).ready(function(){
				loadWalletList();
				$("#nextBtn").click(function(){
					loadWalletList();
				});
			});
		})(jQuery);
	</script>
</head>

<body>
	<div class="viewport">
		<div id="header">
			<div class="header_txt">收支明细</div>
			<div class="h_back" onclick="history.go(-1);"></div>
		</div>
		
		<ul id="data_view">
		</ul>
		<div><a id="nextBtn"  href="javascript:;" style="height: 44px; line-height: 44px; color: #fff; text-align: center; display: block; background: #ff9900; margin: 10px 5px; font-size: 14px; border-radius: 3px;">更多。。。</a></div>
	</div>
</body>
</html>