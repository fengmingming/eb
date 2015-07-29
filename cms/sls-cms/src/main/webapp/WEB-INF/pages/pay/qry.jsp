<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../commons/commons.jsp"%>
<title>query</title>
</head>
<body>
<div class="easyui-panel" title="查询卡" style="width: 100%">
		<div style="padding: 10px 60px 20px 60px" role="form"
			class="form-inline">
			<div class="form-group">
				<label>批次：</label>
				<input id="batch" class="easyui-textbox" type="text" name="batch" />
			</div>
			<div class="form-group">
				<label>面值：</label> 
				<select id="parValue" class="easyui-combobox">
					<option value=""></option>
					<option value="100">100</option>
					<option value="200">200</option>
					<option value="300">300</option>
					<option value="500">500</option>
					<option value="1000">1000</option>
				</select>
			</div>
			<div class="form-group">
				<label>开始时间：</label> <input id="validityStart"
					class="easyui-datebox" />
			</div>
			<div class="form-group">
				<label>结束时间：</label> <input id="validityEnd" class="easyui-datebox" />
			</div>		
			<button id="fff">查询</button>
		</div>

		<div id="cardBatchList" style="padding: 10px 60px 20px 60px"></div>
		<div id="cardList" style="padding: 10px 60px 20px 60px"></div>
	</div>

	<script>
		$(document).ready(
			function() {				
				var gd=$('#cardBatchList').datagrid({
						title : '卡批次列表',
						idField : 'id',
						pageSize : framework.pageNum,
						pageList : [ 20, 30, 40, 50 ],
						method : 'post',
						border : true,
						url : '${dynUrl}/ppc/qryBatch.htm',
						pagination : true,
						fitColumns : true,
						rownumbers : true,
						columns : [ [							             
							{
								field : "batch",
								title : "批次",
								width : 200
							},{
								field : "parValue",
								title : "面值",
								width : 80
							},{								
								field : "validityStart",
								title : "开始日期",
								width : 150
							},{
								field : "validityEnd",
								title : "结束日期",
								width : 150
							},{
								field : "remark",
								title : "备注",
								width : 300
							},{
								field : "checkStatus",
								title : "审核状态",
								width : 150,
								formatter:function(value){
									switch(parseInt(value)){
									case 1:return "未审核";
									case 2:return "审核中";
									case 3:return "已审核";									
									}
								}
							},{
								field : 'operate',
								title : '操作',
								width : 100,
								align : 'left',
								formatter : function(value,rowData,rowIndex) {
									var batch = rowData.batch;
									var a ='<button onclick="showDetail(\''+batch+'\')">明细</button>&nbsp;&nbsp;';
									
									var b="";
										var chkstatus = rowData.checkStatus;									
										if(parseInt(chkstatus)==3)
											b ='<button onclick="exportExcel(\''+batch+'\')">导出</button>';
									return a+b;
								} 
							
							}
							] ],
							
						pagination : true,

						onBeforeLoad : function(param) {
										param.batch = $.trim($("#batch").val());										
										param.parValue = $.trim($.trim($("#parValue").combobox("getValue")));
										param.validityStart = $.trim($("#validityStart").datetimebox("getValue"));
										param.validityEnd =  $.trim($("#validityEnd").datetimebox("getValue"));
										},
						
						onLoadSuccess : function() {},
						onLoadError : function() {},
						onClickCell : function(rowIndex, field, value) {},

						onDblClickRow : function(rowIndex, rowData) {},
						onClickRow : function(rowIndex,rowData) {},
						loadFilter : function(data) {
													if (!data.success) {
														framework.dialog(data);
														return [];
													} else {
														var result = {
															total : data.result.total,
															rows : data.result.entry
														};
														return result;
													}
												}
											});				
								
				
				showDetail = function(batch){					
					var gd=$('#cardList').datagrid({
						title : '卡列表',
						idField : 'id',
						pageSize : framework.pageNum,
						pageList : [ 20, 30, 40, 50 ],
						method : 'post',
						border : true,
						url : '${dynUrl}/ppc/qry.htm',
						pagination : true,
						fitColumns : true,
						rownumbers : true,
						columns : [ [
						      
										{
											field : 'id',
											title : '号',
											width : 80
										},{
											field : "batch",
											title : "批次",
											width : 200
										},{
											field : "parValue",
											title : "面值",
											width : 80
										},{
											field : "cardNum",
											title : "卡号",
											width : 150
										},{
											field : "validityStart",
											title : "开始日期",
											width : 150
										},{
											field : "validityEnd",
											title : "结束日期",
											width : 150
										},{
											field : "remark",
											title : "备注",
											width : 300
										},{
											field : "checkStatus",
											title : "审核状态",
											width : 150,
											formatter:function(value){
												switch(parseInt(value)){
												case 1:return "新生成";
												case 2:return "审核中";
												case 3:return "已审核";									
												}
											}
										},{
											field : "cardStatus",
											title : "卡状态",
											width : 150,
											formatter:function(value){
												switch(parseInt(value)){
												case 0:return "已冻结";
												case 1:return "已激活";
												case 2:return "已超期";
												case 3:return "已充值";									
												}
											}
										},{										
											field : '',
											title : '操作',
											width : 100,
											align : 'center',
											formatter : function(value,rowData,rowIndex) {
												if(rowData.checkStatus == 3) // 3 means checked already.
												{
													var id = rowData.id;
													var status = rowData.cardStatus;
													
													if(status == 0){
														var a = '<button onclick="activate('+id+')">激活</button>';
														return a;
													}														
													else if(status == 1) {
														var b= '<button onclick="freezen('+id+')">冻结</button>';
														return b;
													}							
												}
												return '';			
											}	
											
										} ] ],
							
						pagination : true,

						onBeforeLoad : function(param) {
										param.batch = batch;
										param.idStart=0;
										param.idEnd="";
										},
						
						onLoadSuccess : function() {},
						onLoadError : function() {},
						onClickCell : function(rowIndex, field, value) {},

						onDblClickRow : function(rowIndex, rowData) {},
						onClickRow : function(rowIndex,rowData) {},
						loadFilter : function(data) {
													if (!data.success) {
														framework.dialog(data);
														return [];
													} else {
														var result = {
															total : data.result.total,
															rows : data.result.entry
														};
														return result;
													}
												}
											});
				}	
				
				
				exportExcel = function(batch){					
					$.messager.confirm('','确定导出 ['+batch+'] 批次的卡？',function(r){
						if(r)
						framework.openWindowTab(window.framework.dynUrl+"/ppc/exportxls.htm?html=html&batch="+batch+"&numStart=&numEnd=");						
					});
				}				
				
				activate = function(id){					
					$.messager.confirm('','确定激活此卡？',function(r){
						if(r){
							framework.startMask();
							$.post(framework.dynUrl+"/ppc/setCardStatus.htm",{id:id,status:1},function(d){
								if(d.success){
									framework.closeMask();
									$('#cardList').datagrid("reload");
								}else{
									framework.alert(d.errMsg);
								}
							},'json');							
						}						
					});
				}
				
				
			freezen = function(id){					
					$.messager.confirm('','确定冻结此卡？',function(r){
						if(r){
							framework.startMask();
							$.post(framework.dynUrl+"/ppc/setCardStatus.htm",{id:id,status:0},function(d){
								if(d.success){
									framework.closeMask();
									$('#cardList').datagrid("reload");
								}else{
									framework.alert(d.errMsg);
								}
							},'json');
						}					
					});
				}			
	});
		
		$("#fff").click(function(){					
			$('#cardBatchList').datagrid("reload");
		});
		
		$('.easyui-datebox').datebox({ editable:false });	
	</script>
</body>
</html>