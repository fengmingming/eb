<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息页</title>
<%@include file="../common/common.jsp" %>
<link rel='stylesheet' href='${staUrl }/css/user/user_info.css' type='text/css' />
<script type="text/javascript">
$(function(){
	restoreValue("${user.province}","${user.city}","${user.district}","${user.community}","${user.pavilionId}","${user.pavilionCode}");
	
	if($("#sexCode").val()!="" && $("#sexCode").val()==1){
		$("#man").attr("checked", true);
	}else if ($("#sexCode").val()!="" && $("#sexCode").val()==2) {
		$("#woman").attr("checked", true);
	}else {
		$("#secrecy").attr("checked", true);
	}
	
	//得到亭子的电话
	$("#pavilionId").change(function(){
		var pavilionId = $("#pavilionId").val();
		if(pavilionId == ""){
			return;
		}
		$.ajax({
			   type: "get",
			   url: _rootUrl + "/address/getPavilionInfoById.htm",
			   data: "id="+pavilionId,
			   success: function(data){
				   var pavilionInfo = JSON.parse(data);
				   if(pavilionInfo.success){
				   		$("#td_mobile").html(pavilionInfo.result.mobile);
				   }
			   }
		});
	});
	
	//亭子用户不能修改亭子信息
	if("${sessionScope.user.memberType}" == 2){
		$("#province").attr("disabled", "disabled");
		$("#city").attr("disabled", "disabled");
		$("#district").attr("disabled", "disabled");
		$("#community").attr("disabled", "disabled");
		$("#pavilionId").attr("disabled", "disabled");
	}
	
	//提交按钮
	$("#sub_btn").click(function(){
		if($("#province").val() != "" && $("#city").val() != "" && $("#district").val() != "" &&
				$("#community").val() != "" && $("#pavilionId").val() != ""){
			$.ajax({
				   type: "post",
				   url: _rootUrl + "/user/updateUser.htm",
				   data: {
					   		id:"${user.id}",
							province:$("#province").val(),
							city:$("#city").val(),
							district:$("#district").val(),
							community:$("#community").val(),
							pavilionId:$("#pavilionId").val(),
							pavilionCode:$("#pavilionCode").val(),
							sex:$(":radio[name='sex']:checked").val()
						},
				   success: function(msg){
					   if(msg == "success"){
						   showMsgHint("保存个人信息成功！","duigou");
					   }else{
						   showMsgHint("保存个人信息出现错误！","gantan");
					   }
				  }
			});
		}else{
			showMsgHint("请选择自提点","gantan");
		}
	});
});
</script>
</head>
<body>
	<%@include file="../common/nav.jsp" %>
	
	<%@include file="../common/category_nav.jsp" %>
	
	<div id="u_info">
		<ul class="clear" id="url_here">
			<li>首页<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li>我的手拉手<span style="font-family: '宋体'; margin: 0px 6px;">&gt;</span></li>
			<li class="uh_cur">个人信息</li>
		</ul>
		<div id="u_info_con" class="clear">
			<%@include file="../common/menu.jsp" %>
			<div id="u_info_r">
				<div id="u_i_tit">基本信息<span>|</span></div>
				<table>
					<tr>
						<td class="td_fs">手机：</td>
						<td>${sessionScope.user.mobile}</td>
					</tr>
					<c:if test="${sessionScope.user.memberType == 2 }">
						<tr>
							<td class="td_fs">性别：</td>
							<td>
								<input class="ro_inp" id="man" type="radio" name="sex" value="1"/>
								<label for="man">男</label>
								<input class="ro_inp" id="woman" type="radio" name="sex" value="2"/>
								<label for="woman">女</label>
								<input class="ro_inp" id="secrecy" type="radio" name="sex" value="9"/>
								<label for="secrecy">保密</label>
								<input type="hidden" value="${sessionScope.user.sex}" id="sexCode" name="pavilionCode"/>
							</td>
						</tr>					
						<jsp:include page="../common/booth_area.jsp">
							<jsp:param name="flag" value="false" />
						</jsp:include>
					</c:if>
					<c:if test="${sessionScope.user.memberType == 1}">
						<%@include file="../common/user_info_addr.jsp" %>
					</c:if>										
					<tr>
						<td class="td_fs">亭子电话：</td>
						<td id="td_mobile">${pavilionPhone }</td>
					</tr>
					<c:if test="${sessionScope.user.memberType == 2 }">
					<tr>
						<td>&nbsp;</td>
						<td><a id="sub_btn" href="javascript:;">提交</a></td>
					</tr>
					</c:if>
				</table>
				<div id="u_i_icon">
					<ul>
						<li>用户：<span id="uii_ph">${sessionScope.user.userName}</span>手拉手注册会员</li>
						<li id="uii_mk">把服务送到家门口</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
