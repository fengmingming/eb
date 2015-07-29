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
	<div id="selectParamsPagation">
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			var lastIndex;
			var datagrid = $("#selectParamsPagation");
			$('#selectParamsPagation').datagrid({ 
		        title:'接口方法测试列表', 
		        pageSize:framework.pageNum,
		        method: 'post',
		        border: true, 
		        url:'${dynUrl}/programManage/selectParamsList.htm', 
		        pagination:true, 
		        fitColumns:true,
		        rownumbers:true,
		        columns:[[{
		        	field:"methodId",title:"方法Id",width:$(this).width() * 0.2
		        },{
		        	field:"reqparams",title:"参数",editor:{type:'text'},width:$(this).width() * 0.2
		        },{
		        	field:"paramsName",title:"参数名称",editor:{type:'text'},width:$(this).width() * 0.2
		        }]],
		        toolbar:[{
					iconCls: 'icon-add',
					text:"新增参数",
					handler: function(){
						if(lastIndex == undefined){
							datagrid.datagrid("insertRow",{index:0,row:{methodId:$('#mid_input').val(),id:-1}});
							datagrid.datagrid("beginEdit",0);
						}else{
							datagrid.datagrid("endEdit",lastIndex);
							datagrid.datagrid("insertRow",{index:0,row:{methodId:$('#mid_input').val(),id:-1}});
							datagrid.datagrid("beginEdit",0);
						}						
					}
				},{
					iconCls: 'icon-add',
					text:"保存参数",
					handler: function(){
						if(lastIndex != undefined){
							datagrid.datagrid("endEdit",lastIndex);	
						}else{
							framework.alert("没有改变的项目");
						}
					}
				}],
		        onBeforeLoad: function (param) {
		        	param.methodId = $.trim($("#mid_input").val());
		        },
		        onLoadSuccess: function (data) {
		        	
		        },
		        onLoadError: function () {
		            
		        },
		        onClickCell: function (rowIndex, field, value) {
		            
		        },
		        onClickRow:function(rowIndex,rowData){
		        	
		        },
		        onDblClickRow : function(rowIndex,rowData){
					if(lastIndex != undefined){
						datagrid.datagrid("endEdit",lastIndex);
					}else{
						datagrid.datagrid("beginEdit",rowIndex);
					}
				},
				onBeforeEdit:function(rowIndex, rowData){
					lastIndex = rowIndex;
				},
				onAfterEdit:function(rowIndex, rowData, changes){
					if(lastIndex == undefined){
						return;
					}
					framework.startMask();
					lastIndex = undefined;
					if(rowData.id < 0){
						if(changes.reqparams&&changes.paramsName){
							$.post(window.framework.dynUrl+"/programManage/addParamInfo.htm",{methodId:$('#mid_input').val(),reqparams:changes.reqparams,paramsName:changes.paramsName},function(data){
								if(data.success){
									datagrid.datagrid("reload");
								}else{
									window.framework.alert(data.errMsg||"操作失败");
									datagrid.datagrid("deleteRow",rowIndex);
								}
								framework.closeMask();
							},"json");
						}else{
							window.framework.dialog(null,"非法url");
							datagrid.datagrid("deleteRow",rowIndex);
							framework.closeMask();
						}
					}else{
						if(!$.isEmptyObject(changes)){															
							$.post(window.framework.dynUrl+"/programManage/updateParams.htm",{id:rowData.id,methodId:$('#mid_input').val()||rowData.methodId,reqparams:changes.reqparams||rowData.reqparams,paramsName:changes.paramsName||rowData.paramsName},function(data){
								if(data.success){
									datagrid.datagrid("reload");
								}else{
									window.framework.alert("操作失败");
									datagrid.datagrid("reload");
								}
								framework.closeMask();
							},"json");
						}else{
							framework.closeMask();
						}
					}
				},
		        loadFilter:function(data){
		        	if(!data.success){
		        		framework.dialog(data);
		        		return {};
		        	}else{
		        		var result = {
			        			total:data.result.total,
			        			rows:data.result.entry,
			        	};
			        	return result;
		        	}
		        }
		    });
			
		});
		
	</script>
</body>
</html>