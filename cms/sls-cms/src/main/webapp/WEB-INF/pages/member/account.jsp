<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../commons/commons.jsp" %>
</head>
<body>
	<div class="easyui-tabs">
		<div title="账户操作记录">
			<div>
				<input class="easyui-datetimebox" id="startDate"/>至<input class="easyui-datetimebox" id="endDate"/> <button class="btn btn-primary" onclick="query()">查询</button><button class="btn btn-primary" onclick="excel()">导出excel</button>
			</div>
			<div id="log_datagrid"></div>
		</div>
		<div title="第三方工具充值记录">
			<div>
				<a href="javascript:void(0)" onclick="forceComplete()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">强制完成</a>
			</div>
			<div id="recharge_datagrid"></div>
		</div>
	</div>
	<script type="text/javascript">
		function query(){
			$("#log_datagrid").datagrid("reload");
		}
		function excel(){
			var startDate = $("#startDate").datetimebox("getValue"); 
		    var endDate =  $("#endDate").datetimebox("getValue"); 
			framework.openWindowTab(window.framework.dynUrl+"/member/excel.htm?userId=${userId}&startDate="+startDate+"&endDate="+endDate);
		}
		$(document).ready(function(){
			forceComplete = function(){
				var data = $("#recharge_datagrid").datagrid("getSelected");
				if(data&&data.status == 0){
					$.messager.confirm("","确认强制完成？",function(r){
						if(r){
							$.post(framework.dynUrl+"/member/updateaccountorder.htm",{id:data.id},function(d){
								if(d.success){
									framework.alert("操作成功");
									$("#recharge_datagrid").datagrid("reload");
								}else{
									framework.alert(d.errMsg);
								}
							},"json");
						}
					});
				}
			};
			$("#recharge_datagrid").datagrid({
				singleSelect:true,
				pagination:true,
				rownumbers:true,
				fitColumns:true,
				url:window.framework.dynUrl+"/member/recharge.htm?userId=${userId}",
				columns:[[{
					field:"voucherOrderNum",title:"充值流水号",width:200
				},{
					field:"thirdOrderNum",title:"第三方流水号",width:200
				},{
					field:"money",title:"金额",width:100
				},{
					field:"status",title:"状态",formatter:function(value){
						switch(value){
						case 0:return "未完成";
						case 1:return "完成";
						}
					},width:50
				},{
					field:"createTime",title:"生成时间",width:100
				},{
					field:"remark",title:"备注",width:500
				}]],
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
			$("#log_datagrid").datagrid({
				singleSelect:true,
				pagination:true,
				fitColumns:true,
				rownumbers:true,
				url:window.framework.dynUrl+"/member/log.htm?userId=${userId}",
				onBeforeLoad:function(param){
					param.startDate = $("#startDate").datetimebox("getValue"); 
				    param.endDate =  $("#endDate").datetimebox("getValue"); 
				},
				columns:[[{
					field:"money",title:"消费金额",width:100,formatter:function(value,rowData){
						if(rowData.type == 1){
							return "+"+value;
						}else if(rowData.type == 2){
							return "-"+value;
						}else{
							return value;
						}
					}
				},{
					field:"curAmount",title:"余额",width:100
				},{
					field:"operator",title:"操作人",width:200
				},{
					field:"type",title:"类型",width:50,formatter:function(value){
						switch(value){
						case 1:return "充入";
						case 2:return "支出";
						}
					}
				},{
					field:"createTime",title:"创建时间",width:100
				},{
					field:"remark",title:"备注",width:500
				}]],
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