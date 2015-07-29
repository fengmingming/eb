<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<%@include file="../commons/commons.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="easyui-panel" title="新增字典" style="width: 100%;height:100%;">
		<div style="padding:50px 0 50px 30%">
			<form id="addDictForm" method="post" data-options="novalidate:true" action="${dynUrl }/dict/add.htm">
				<table>
					<tr>
						<td>编号:</td>
						<td><input class="easyui-textbox" type="text" name="code"
							 style="width: 120px;height: 25px;" data-options="required:true,validType:'length[1,11]'"></input></td>
					</tr>
					<tr>
						<td>名字:</td>
						<td><input class="easyui-textbox" type="text" name="name"
							 style="width: 120px;height: 25px;" data-options="required:true,validType:'length[1,50]'"></input></td>
					</tr>
					<tr>
						<td>类型:</td>
						<td><select class="easyui-combobox" id="addDict_type" name="type" style="width:120px" data-options="required:true,textField:'k',valueField:'v',url:'${dynUrl }/dict/combox.htm',loadFilter:function(data){
							if(data.success){
								return data.result;
							}
							return [];
						}">
						</select></td>
					</tr>
					<tr>
						<td>备注:</td>
						<td><input class="easyui-textbox" type="text" name="remark"
						  style="width: 120px;height: 25px;" ></input></td>
					</tr>
				</table>
			</form>
			<div style="padding-left:70px">
				<a href="javascript:void(0)" class="btn btn-default" id="addDict_submit">提交</a> 
				<a href="javascript:void(0)" class="btn btn-default" id="addDict_clear">重置</a>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		(function($){
			$("#addDict_submit").click(function(){
				 $('#addDictForm').form('submit',{
					 onSubmit:function(){
						return $(this).form('enableValidation').form('validate');
					 },
					 success:function(data){
						 window.framework.dialog(data);
					 }
				 });
			});
			$("#addDict_clear").click(function(){
				 $('#addDictForm').form('clear');
			});
		})(jQuery);
	</script>
</body>
</html>