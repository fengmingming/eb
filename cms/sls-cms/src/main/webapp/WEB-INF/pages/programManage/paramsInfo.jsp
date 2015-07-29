<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../commons/commons.jsp" %>
</head>
<body>
<input type="hidden" id="mid_input" value='<%=request.getParameter("id")%>' >
<input type="hidden" id="model_input" value='<%=request.getParameter("mdl")%>' >
 <form class="form-inline" role="form" id="selectParamsForm" method="post" data-options="novalidate:true" action="${dynUrl}/programManage/programModelTest.htm ">
	  <table id="params_table">
	  </table>
	  <button type="submit" class="btn btn-default" id="selectParamsSubmit" onclick="javascript:return false">查询</button>
	</form> 
	<div id="selectMethodInfoPagation">
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$.ajax({
				url:'${dynUrl}/programManage/methodInfos.htm?methodId='+$('#mid_input').val(), 
				type : "post",
				dataType : "json",
				async : false,
				timeout : 999999,
				success : function(data) {
					if (data.success){
						
						var size = data.result.length;
						var paramstable=$("#params_table");
							var tr0 = $("<tr></tr>");
							var paramsInfoj = data.result[size-1];
							var interfase_input=$("<td>接口:</td>"+
									"<td><input class='easyui-textbox' type='text' name='interfase' value='"+paramsInfoj.interfase+"' /></td>");
							var MethodName_input=$("<td>方法:</td>"+
									"<td><input class='easyui-textbox' type='text' name='methodName' value='"+paramsInfoj.MethodName+"' /></td>");
							var url_input=$("<td>url:</td>"+
									"<td><input class='easyui-textbox' type='text' name='url' value='"+paramsInfoj.url+"' /></td>");
							var modl_input=$("<td>Model:</td>"+
									"<td><input class='easyui-textbox' type='text' name='model' value='"+$('#model_input').val()+"' /></td>");
							tr0.append(interfase_input).append(MethodName_input).append(url_input).append(modl_input);
							paramstable.append(tr0);
						for (var i = 0; i < size-1; i++) {
							var paramsInfo = data.result[i];
							//var tr = $("<tr>");
							var input=$("<tr><td>"+paramsInfo.paramsName+":</td></tr>"+
								"<tr><td><input  type='text' name='"+paramsInfo.reqparams+"' /></td></tr>");
							//tr.append(input);
							paramstable.append(input);
						}
					}
				},
				error : function(data) {
					//$.messager.alert('提示', data.errorReason.msg, 'error');
				}
			});
			
			$("#selectParamsSubmit").click(function(){
				 $('#selectParamsForm').form('submit',{
					 onSubmit:function(){
						return $(this).form('enableValidation').form('validate');
					 },
					 success:function(data){
						 alert(data.result);
						 alert(data);
					 }
				 });
			});
			
		});
		
	</script>
</body>
</html>