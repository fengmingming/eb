<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<%@include file="../commons/commons.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div style="padding:10px">
		<form class="form-inline" role="form" id="selectDictForm" method="post" data-options="novalidate:true" action="${dynUrl }/dict/infos.htm">
		  <div class="form-group">
		    <label>字典编号：</label>
		    <input type="text" class="form-control" style="width: 120px;height: 25px;"  id="selectDictForm_code" placeholder="输入字典编号">
		  </div>
		  <div class="form-group">
		      <label>字典名字：</label>
		      <input class="form-control" type="text" style="width: 120px;height: 25px;"  id="selectDictForm_name" placeholder="输入字典名字">
		  </div>
		  <div class="form-group">
		    <label>字典类型：</label>
		    <select class="easyui-combobox" id="selectDictForm_type" style="width:120px" data-options="textField:'k',valueField:'v',url:'${dynUrl }/dict/combox.htm',loadFilter:function(data){
							if(data.success){
								return data.result;
							}
							return [];
						}">
						</select>
		  </div>
		  <button type="submit" class="btn btn-default" id="selectDictSubmit" onclick="javascript:return false">查询</button>
		</form>
	</div>
	<div id="selectDictPagation">
	</div> 
	<div id="updateDictDiv" style="width:300px;height:300px;" class="easyui-window" title="修改字典信息" data-options="modal:true,closed:true">
		<form id="updateDictForm" method="post" data-options="novalidate:true" action="${dynUrl }/dict/update.htm" style="padding-left:30px;padding-top:30px">
				<input type="hidden" name="id"/>
				<table>
					<tr>
						<td>字典编号:</td>
						<td><input class="easyui-textbox" type="text" style="width: 120px;height: 25px;"  name="code" readonly="readonly"></input></td>
					</tr>
					<tr>
						<td>字典名字:</td>
						<td><input class="easyui-textbox" type="text" name="name" style="width: 120px;height: 25px;" 
							data-options="required:true,validType:'length[2,50]'"></input></td>
					</tr>
					<tr>
						<td>备注:</td>
						<td><input class="easyui-textbox" name="remark"
						 data-options="validType:'length[2,50]'"></input></td>
					</tr>
				</table>
				<div style="padding-left:70px">
					<a href="javascript:void(0)" class="btn btn-default" id="updateDict_submit">提交</a> 
					<a href="javascript:void(0)" class="btn btn-default" id="updateDict_clear">重置</a>
				</div>
			</form>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$("#updateDict_submit").click(function(){
				$('#updateDictForm').form('submit',{
					 onSubmit:function(){
						return $(this).form('enableValidation').form('validate');
					 },
					 success:function(data){
						 $('#updateDictForm').form('clear');
						 $("#updateDictDiv").window("close");
						 $('#selectDictPagation').datagrid("reload");
						 window.framework.dialog(data);
					 }
				 });
			});
			
			$("#updateDict_clear").click(function(){
				 $('#updateDictForm').form('clear');
			});
			
			
			$('#selectDictPagation').datagrid({ 
		        title:'字典列表', 
		        pageSize:framework.pageNum,
		        pageList:[20,30,40,50],
		        method: 'post',
		        border: true, 
		        url:'${dynUrl}/dict/infos.htm', 
		        pagination:true, 
		        singleSelect:true,
		        fitColumns:true,
		        rownumbers:true,
		        columns:[[{
		        	field:"code",title:"编号",width:50
		        },{
		        	field:"name",title:"名字",width:50
		        },{
		        	field:"type",title:"类型",width:50
		        },{
		        	field:"remark",title:"备注",width:50
		        }]],
		        onBeforeLoad: function (param) {
		        	param.code = $.trim($("#selectDictForm_code").val());
		        	param.name = $.trim($("#selectDictForm_name").val());
		        	param.type = $.trim($("#selectDictForm_type").combobox("getValue"));
		        },
		        onLoadSuccess: function () {
		            
		        },
		        onLoadError: function () {
		            
		        },
		        onClickCell: function (rowIndex, field, value) {
		            
		        },
		        
		        onDblClickRow: function (rowIndex, rowData) {
		        	$("#updateDictForm").form("load",rowData);
		        	$("#updateDictDiv").window("open");
		        },
		        onClickRow:function(rowIndex,rowData){
		        	
		        },
		        loadFilter:function(data){
		        	if(!data.success){
		        		framework.dialog(data);
		        		return [];
		        	}else{
		        		var result = {
			        			total:data.result.total,
			        			rows:data.result.entry,
			        	};
			        	return result;
		        	}
		        }
		    });
			
			$("#selectDictSubmit").click(function(){
				$("#selectDictForm").form("submit",{
					onSubmit:function(){
						$('#selectDictPagation').datagrid("reload");
						return false;
					}
				});
			});
			
			
		});
		
	</script>
</body>
</html>