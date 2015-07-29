<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../commons/commons.jsp" %>
</head>
<body>
	<div class="container-fluid" style="padding-top:20px">
		<div class="row">
			<div class="col-xs-3">充值卡总数：${re.count }</div>
			<div class="col-xs-3">已激活：${re.activated }</div>
			<div class="col-xs-3">已充值：${re.recharged }</div>
			<div class="col-xs-3">已失效：${re.invalid }</div>
		</div>
	</div>
	<hr/>
	<div class="form-inline">
		<div class="form-group">
			<label>起始时间：</label>
			<input type="text" class="easyui-datebox" id="startDate"/>
		</div>
		<div class="form-group">
			<label>结束时间：</label>
			<input type="text" class="easyui-datebox" id="endDate"/>
		</div>
		<div class="form-group">
			<button id="query" class="btn btn-default">查询</button>
		</div>
		<div class="form-group">
			<button id="excel" class="btn btn-default">批量导出收支明细</button>
		</div>
	</div>
	<div id="qry_pagation"></div>
	<div id="excel_window" title="导出明细时间范围选择" data-options="width:600,closed:true">
		<div class="form-inline">
			<div class="form-group">
				<label>导出开始时间</label>
				<input class="easyui-datebox" id="excel_startDate"/>
			</div>
			<div class="form-group">
				<label>导出结束时间</label>
				<input class="easyui-datebox" id="excel_endDate"/>
			</div>
			<div class="form-group">
				<button class="btn btn-default" id="excelBtn">导出</button>
			</div>
		</div>
	</div>
<script type="text/javascript">
	$(document).ready(function(){
		var excel_window = $("#excel_window").window();
		$("#query").click(function(){
			dg.datagrid("reload");
		});
		$("#excel").click(function(){
			excel_window.window("open");
		});
		$("#excelBtn").click(function(){
			excel_window.window("close");
			var data = dg.datagrid("getChecked");
			var ids = [];
			$.each(data,function(i,d){
				if(d.userId){
					ids.push(d.userId);
				}
			});
			if(ids.length > 0){
				var startDate = $("#excel_startDate").datetimebox("getValue"); 
			    var endDate =  $("#excel_endDate").datetimebox("getValue");
			    var idsStr = ids.join(",");
				framework.openWindowTab(window.framework.dynUrl+"/member/excels.htm?ids="+idsStr+"&startDate="+startDate+"&endDate="+endDate);
			}
		});
		purchasehistory = function(userId){
			framework.flushTabs(framework.dynUrl+"/member/initaccount.htm?userId="+userId,"账户操作记录");
		}
		var dg = $("#qry_pagation").datagrid({
			columns:[[{
				field:"check",checkbox:true
			},{
				field:"batch",title:"批次",width:100
			},{
				field:"parValue",title:"面值",width:100
			},{
				field:"reChangeDate",title:"充值时间",width:150
			},{
				field:"username",title:"用户名",width:150
			},{
				field:"mobile",title:"手机号",width:200
			},{
				field:"userId",title:"消费记录",formatter:function(value){
					if(value){
						return '<button onclick="purchasehistory('+value+')">消费记录</button>';
					}
				}
			}]],
			pageSize:framework.pageNum,
	        pageList:[20,30,40,50],
	        url:'${dynUrl}/ppc/rechargedInfo.htm',
	        method: 'post',
	        border: true,
	        singleSelect:true,
	        pagination:true, 
	        fitColumns:true,
	        rownumbers:true,
	        checkOnSelect:false,
	        selectOnCheck:false,
	        onBeforeLoad: function (param) {
	        	param.startDate = $("#startDate").datebox("getValue");
	        	param.endDate = $("#endDate").datebox("getValue");
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
	});
</script>
</body>
</html>