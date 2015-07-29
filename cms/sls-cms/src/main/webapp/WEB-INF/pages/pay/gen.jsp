<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--  
<link rel="stylesheet" type="text/css" href="${staUrl}/resources/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${staUrl}/resources/easyui/themes/icon.css">

<script type="text/javascript" src="${staUrl}/resources/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${staUrl}/resources/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${staUrl}/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
-->
<%@include file="../commons/commons.jsp"%>
<title>Insert title here</title>
</head>
<body>
	<div class="easyui-panel" title="生成卡" style="width:100%">
		<div style="padding:10px 60px 20px 60px">
			<form id="ff" action="gen.htm" method="post">
				<table>
					<tr>
						<td>批次:</td>
						<td><input id="batch" class="easyui-textbox" type="text" name="batch" data-options="required:true"/></td>
						<td>面值:</td>
						<td>
							<select class="easyui-combobox" name="parValue" data-options="required:true">
								<option value="100">100</option>
								<option value="200">200</option>
								<option value="200">300</option>
								<option value="500">500</option>
								<option value="1000">1000</option>
							</select>
						</td>
						<td>开始时间:</td>
						<td><input id="startDt" name="validityStart" class="easyui-datebox" data-options="required:true" /></td>
						<td>结束时间:</td>
						<td><input id="endDt" name="validityEnd" class="easyui-datebox" data-options="required:true" /></td>
						<td>数量:</td>
						<td><input class="easyui-numberbox" name="count" id="count" data-options="required:true,precision:0"/></td>
					</tr>
					<tr>
						<td class="margin:200px; height:300px">&nbsp&nbsp</td>
					</tr>
					
					<tr>
						<td>备注:</td>
						<td  colspan="9"><input class="easyui-textbox" name="remark" data-options="multiline:true" style="height:90px; width:100%;"/></td>
					</tr>
				</table>
			</form>		
			
			 <div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">清空</a>
			</div>
			
		</div>
	</div>	
	<script>
	function nextBatch() {
		$.post(framework.dynUrl+"/ppc/getNextBatchNum.htm",{},function(d){
			if(d.success){					
				$('#batch').textbox('setValue',d.result)
			}else{
				framework.alert(d.errMsg)
			}
		},'json');				
	}
	
	
	$(document).ready(
			nextBatch()
		 );
	
	function submitForm(){	
		if($("#ff").form("validate")){
			var sta = $('#startDt').datetimebox('getValue'); 			
			var end = $('#endDt').datetimebox('getValue');
			if(sta>end){
				framework.alert("起始日期大于结束日期");
				return;
			}			
			var count = $("#count").val();
			if(count>2000){
				framework.alert("一次生成数量不能超过2000张");
				return;
			}
			
			var batch = $("#batch").val();
			$.messager.confirm('','确定生成批次[ '+batch+' ]  的卡？',function(r){
				if(r){
					framework.startMask();
					$('#ff').form('submit',
						{
							success:function(data){		
									framework.alert("成功生成"+count+"张预付费卡");
									framework.closeMask();
									nextBatch();										
								}			
							});
				}
			});
		}
		
	}
	function clearForm(){
		var batch = $("#batch").val();
		$('#ff').form('clear');
		$("#batch").next().children('input').val(batch);
		
	}	
	$('#batch').textbox({ editable:false });
	$('.easyui-datebox').datebox({ editable:false });	
	</script>
</body>
</html>