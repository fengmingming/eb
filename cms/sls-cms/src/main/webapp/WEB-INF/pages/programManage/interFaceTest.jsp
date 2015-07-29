<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../commons/commons.jsp" %>
</head>
<body>

<form class="form-inline" role="form" id="selectInterfaceForm" method="post" data-options="novalidate:true" action="${dynUrl}/programManage/searchInterface.htm">
	  <div class="form-group">
	    <label>接口：</label>
	    <input type="text" style="width: 120px;height: 25px;"  class="form-control" id="Interface_Input" placeholder="输入接口名">
	  </div>
	  <div class="form-group">
	      <label>描述：</label>
	      <input class="form-control" style="width: 120px;height: 25px;"  type="text" id="Remark_Input" placeholder="输入描述">
	  </div>
	  <button type="submit" class="btn btn-default" id="selectInterfaseSubmit" onclick="javascript:return false">查询</button>
	</form>
		<div id="selectInterfacePagation">
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			var lastIndex;
			var datagrid = $("#selectInterfacePagation");
			$('#selectInterfacePagation').datagrid({ 
		        title:'接口测试列表', 
		        pageSize:framework.pageNum,
		        method: 'post',
		        border: true, 
		        url:'${dynUrl}/programManage/searchInterface.htm', 
		        pagination:true, 
		        fitColumns:true,
		        rownumbers:true,
		        columns:[[{
		        	field:"interfase",title:"接口名",editor:{type:'text'},width:$(this).width() * 0.15
		        },{
		        	field:"remark",title:"描述",editor:{type:'text'},width:$(this).width() * 0.2
		        },{
		        	field:"url",title:"路径",editor:{type:'text'},width:$(this).width() * 0.2
		        },{
		        	field:"id",title:"操作",formatter:function(value){
		        		var str="<button onclick=\"javascript:window.framework.addTabs('${dynUrl}/programManage/methods.htm?id="+value+"','测试方法')\" type=\"button\" class=\"btn btn-primary btn-xs btn-block\">"+'测试'+'</button>';
		        		return str;
		        	},width:$(this).width() * 0.25
		        }]],
		        toolbar:[{
					iconCls: 'icon-add',
					text:"新增接口",
					handler: function(){
						if(lastIndex == undefined){
							datagrid.datagrid("insertRow",{index:0,row:{id:-1}});
							datagrid.datagrid("beginEdit",0);
						}else{
							datagrid.datagrid("endEdit",lastIndex);
							datagrid.datagrid("insertRow",{index:0,row:{id:-1}});
							datagrid.datagrid("beginEdit",0);
						}						
					}
				},{
					iconCls: 'icon-add',
					text:"保存接口",
					handler: function(){
						if(lastIndex != undefined){
							datagrid.datagrid("endEdit",lastIndex);	
						}else{
							framework.alert("没有改变的项目");
						}
					}
				}],
		        onBeforeLoad: function (param) {
		        	param.interfase = $.trim($("#Interface_Input").val());
		        	param.remark = $.trim($("#Remark_Input").val());
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
						if(changes.url&&changes.url.length > 0){
							$.post(window.framework.dynUrl+"/programManage/addInterfaces.htm",{url:changes.url,interfase:changes.interfase,remark:changes.remark},function(data){
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
							$.post(window.framework.dynUrl+"/programManage/updateInterfaces.htm",{id:rowData.id,url:changes.url||rowData.url,interfase:changes.interfase||rowData.interfase,remark:changes.remark||rowData.remark},function(data){
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
			
			$("#selectInterfaseSubmit").click(function(){
				$('#selectInterfacePagation').datagrid('reload');
			});
		});
		
	</script>
</body>
</html>