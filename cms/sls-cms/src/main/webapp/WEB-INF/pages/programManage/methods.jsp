<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../commons/commons.jsp" %>
</head>
<body>
<input type="hidden" id="Iid_input" value='<%=request.getParameter("id")%>' >
<!-- <form class="form-inline" role="form" id="selectMethodForm" method="post" data-options="novalidate:true" action="${dynUrl}/programMange/searchInterface.htm">
	  <div class="form-group">
	    <label>接口：</label>
	    <input type="text" class="form-control" id="Interface_Input" placeholder="输入接口名">
	  </div>
	  <div class="form-group">
	      <label>描述：</label>
	      <input class="form-control" type="text" id="Remark_Input" placeholder="输入描述">
	  </div>
	  <button type="submit" class="btn btn-default" id="selectInterfaseSubmit" onclick="javascript:return false">查询</button>
	</form> -->
		<div id="selectMethodPagation">
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			var lastIndex;
			var datagrid = $("#selectMethodPagation");
			$('#selectMethodPagation').datagrid({ 
		        title:'接口方法测试列表', 
		        pageSize:framework.pageNum,
		        method: 'post',
		        border: true, 
		        url:'${dynUrl}/programManage/methodList.htm', 
		        pagination:true, 
		        fitColumns:true,
		        rownumbers:true,
		        columns:[[{
		        	field:"iid",title:"接口Id",width:$(this).width() * 0.2
		        },{
		        	field:"methodEn",title:"方法",editor:{type:'text'},width:$(this).width() * 0.2
		        },{
		        	field:"methodName",title:"方法名称",editor:{type:'text'},width:$(this).width() * 0.2
		        },{
		        	field:"model",title:"model",editor:{type:'text'},width:$(this).width() * 0.2
		        },{
		        	field:"remark",title:"描述",editor:{type:'text'},width:$(this).width() * 0.2
		        },{
		        	field:"id",title:"操作",formatter:function(value,rec){
		        		var str;
		        		if (rec.model==1) {
		        			str="<a href='javascript:void(0);' onclick=\"javascript:window.framework.addTabs('${dynUrl}/programManage/methodInfo.htm?id="+value+"','测试参数')\" >"+'测试方法'+'</a>';
						} else {
		        			str="<a href='javascript:void(0);' onclick=\"javascript:window.framework.addTabs('${dynUrl}/programManage/paramsInfo.htm?id="+value+"&mdl="+rec.model+"','测试Model')\" >"+'测试Model方法'+'</a>';
						}
		        		var str0="&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' onclick=\"javascript:window.framework.addTabs('${dynUrl}/programManage/addParams.htm?id="+value+"','增加参数')\" >"+'增加参数'+'</a>';
		        		return str+str0;
		        	},width:$(this).width() * 0.2
		        }]],
		        toolbar:[{
					iconCls: 'icon-add',
					text:"新增方法",
					handler: function(){
						if(lastIndex == undefined){
							datagrid.datagrid("insertRow",{index:0,row:{Iid:$('#Iid_input').val(),id:-1}});
							datagrid.datagrid("beginEdit",0);
						}else{
							datagrid.datagrid("endEdit",lastIndex);
							datagrid.datagrid("insertRow",{index:0,row:{Iid:$('#Iid_input').val(),id:-1}});
							datagrid.datagrid("beginEdit",0);
						}						
					}
				},{
					iconCls: 'icon-add',
					text:"保存方法",
					handler: function(){
						if(lastIndex != undefined){
							datagrid.datagrid("endEdit",lastIndex);	
						}else{
							framework.alert("没有改变的项目");
						}
					}
				}],
		        onBeforeLoad: function (param) {
		        	param.Iid = $.trim($("#Iid_input").val());
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
						if(changes.methodName){
							$.post(window.framework.dynUrl+"/programManage/addMethods.htm",{Iid:$('#Iid_input').val(),methodName:changes.methodName,methodEn:changes.methodEn,remark:changes.remark,model:changes.model},function(data){
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
							$.post(window.framework.dynUrl+"/programManage/updateMethods.htm",{id:rowData.id,Iid:$('#Iid_input').val()||rowData.Iid,methodEn:changes.methodEn||rowData.methodEn,methodName:changes.methodName||rowData.methodName,remark:changes.remark||rowData.remark,model:changes.model||rowData.model},function(data){
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