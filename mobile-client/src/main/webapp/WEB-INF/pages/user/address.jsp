<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>手拉手触屏版</title>
	<%@include file="../common/common.jsp" %>
	<link rel='stylesheet' href='${staUrl }/css/order/check_order.css' type='text/css' />
	<script type="text/javascript" src="${imgUrl }/area.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".goods_check").click(function(){
				$(".goods_check").each(function(){
					$(this).removeClass("gc_y").addClass("gc_n");
				})
				var obj = $(this);
				var id = obj.attr("id");
				$.get("${dynUrl}/address/setDefaultAddress.htm",{id:id},function(d){
					
					if(d.status == "302"){
						location.href= d.location;
						return ;
					}
					if(!d.success){
						alert(d.errMsg);
					}else{
						obj.removeClass("gc_n").addClass("gc_y");
					}
				},"json");
			});
			$(".co_add2").click(function(){
				$.get("${dynUrl}/address/canAddAddress.htm",function(d){
					if(d == 'success'){
						window.location.href = "${dynUrl }/pcenter/address/addOrup.htm";
					}else{
						alert("最多三条收货地址");
					}
				},"html");
			});
			ua_li_del = function(id){
				if(window.confirm("是否确认要删除？")){
					window.showLoading();
					$.get("${dynUrl}/address/deleteAddress.htm",{id:id},function(d){
						window.hideLoading();
						if(d.success){
							window.location.reload();
						}else{
							alert("删除失败");
						}
					},"json");
				}
			};
		});
	</script>
</head>

<body>
	<div class="viewport">
		<div id="header">
			<div class="header_txt">收货地址</div>
			<div class="h_back" onclick="window.location.href='${dynUrl}/pcenter/index.htm'"></div>
			<div class="h_opt"></div>
		</div>
		<!-- 收货人地址列表 -->
		<div id="address_list">
			<ul class="clear shrxx_txt">
				<li style="float: left; padding-left: 10px;">收货人信息</li>
			</ul>
			<c:if test="${!empty addresslist}">
				<c:forEach var="add" items="${addresslist }">
				<ul class="clear ua_ul">
					<li class="ua_li">
						<c:if test="${!add.isdefault }">
							<a href="javascript:void(0);" class="goods_check gc_n" id="${add.id }"></a>
						</c:if>
						<c:if test="${add.isdefault }">
							<a href="javascript:void(0);" class="goods_check gc_y" id="${add.id }"></a>
						</c:if>
					</li>
					<li class="ua_li">
						<ul class="ua_li_ul">
							<li class="ua_li_ul_ads">${add.addressDetail }</li>
							<li class="ua_li_ul_rec">${add.receiver }&nbsp;${add.mobile}</li>					
						</ul>
					</li>
					<li class="ua_li" style="float: right;"><a class="ua_li_edit" href="${dynUrl }/pcenter/address/addOrup.htm?addressId=${add.id}">编辑</a></li>
					<li class="ua_li" style="float: right;"><a class="ua_li_del" href="javascript:ua_li_del(${add.id })">删除</a></li>
				</ul>
				</c:forEach>
			</c:if>
			
			<ul class="clear co_shdz">
				<li>新增收货地址：</li>
				<li style="float: right;"><a class="co_add2" href="javascript:void(0);"></a></li>
			</ul>
		</div>
	</div>
</body>
</html>