<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@include file="../commons/commons.jsp"%>
<title>check</title>
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
			<div class="form-group">
				<label>审核状态：</label> 
				<select id="chkStatus" class="easyui-combobox">
					<option value=""></option>
					<option value="1">未审核</option>
					<!-- <option value="2">审核中</option> -->
					<option value="3">已审核</option>
				</select>
			</div>
			<div class="form-group">
				<label>卡号范围:</label>
				<input class="easyui-numberbox" id="idStart" name="idStart" data-options="required:true,precision:0"/>
				<label>--</label>
				<input class="easyui-numberbox" id="idEnd" name="idEnd" data-options="required:true,precision:0"/>
			</div>			
			
			<button id="ff">查询</button>
		</div>

		<div id="tb" style="height:auto" title="卡列表" class="easyui-panel">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="chkChecked()">审核选中</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="chkBatch()">审核所属批次</a>
		</div>
		<div id="cardList" style="padding: 10px 60px 20px 60px">asdfasdf</div>
	</div>
	

	<script>
		$(document).ready(
			function() {
				var gd=$('#cardList').datagrid({						
						idField : 'id',
						pageSize : framework.pageNum,
						pageList : [ 20, 30, 40, 50 ],
						method : 'post',
						border : true,
						url : '${dynUrl}/ppc/qry.htm',									
						pagination:true, 
				        singleSelect:true,
				        fitColumns:true,
				        rownumbers:true,
				        toolsbar:"#tb",
				        checkOnSelect:false,
				        selectOnCheck:false,
						
						columns : [ [
							{
								field:"check",checkbox:true
							},{
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
									case 1:return "未审核";
									case 2:return "审核中";
									case 3:return "已审核";									
									}
								}
							},{
								field : '',
								title : '操作',
								width : 100,
								align : 'center',
								formatter : function(value,rowData,rowIndex) {
									if(rowData.checkStatus != 3) // 3 means checked already.
									{										
										return '<button onclick="chkInLine(\''+rowData.id+'\')">审核</button>'
									}
									return '';			
								}
							} ] ],
							
						pagination : true,

						onBeforeLoad : function(param) {
										param.batch = $.trim($("#batch").val());										
										param.parValue = $.trim($.trim($("#parValue").combobox("getValue")));
										param.validityStart = $.trim($("#validityStart").datetimebox("getValue"));
										param.validityEnd =  $.trim($("#validityEnd").datetimebox("getValue"));
										param.checkStatus = $.trim($.trim($("#chkStatus").combobox("getValue")));
										param.idStart = $.trim($("#idStart").val());	
										param.idEnd = $.trim($("#idEnd").val());
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
															rows : data.result.entry,
														};
														return result;
													}
												}
											});
							
							
				$("#ff").click(function(){					
					$('#cardList').datagrid("reload");
				});					
				
				chkBatchInLine = function(batch){					
					$.messager.confirm('','确定审核此批次 [ '+batch+' ] 的卡？',function(r){
						if(r){
							framework.startMask();
							$.post(framework.dynUrl+"/ppc/chkBatch.htm",{batch:batch},function(d){
								if(d.success){
									framework.closeMask();
									$('#cardList').datagrid("reload");
								}else{
									framework.alert(d.errMsg);
								}
							},'json');
						}else{
							framework.closeMask();
						}					
					});
				}
				
				chkInLine = function(id){					
					$.messager.confirm('','确定审核此卡？',function(r){
						if(r){
							var ids = [];
							ids[0]=id;
							framework.startMask();
							$.post(framework.dynUrl+"/ppc/chkByIds.htm",{ids:ids.join(",")},function(d){
								if(d.success){
									framework.closeMask();
									$('#cardList').datagrid("reload");
								}else{
									framework.alert(d.errMsg);
								}
							},'json');
						}else{
							framework.closeMask();
						}					
					});
				}
				
				chkBatch = function(){		
					var checkDatas = gd.datagrid("getChecked");
					var seLen = checkDatas.length;
					if( seLen > 0){
						debugger;
						var batch = [];
						$.each(checkDatas,function(index,data){
							if(data.checkStatus != 3)
								batch.push(data.batch);
						});
						var bth=batch[0]; // get the first batch
						var msg = "确定审核批次[ "+bth+" ]的卡？";
						if(seLen != batch.length){
							msg = "您所选择的卡列表中含有已审核的记录，确定继续？"
						}
						$.messager.confirm('',msg,function(r){
							if(r){								
								framework.startMask();
								$.post(framework.dynUrl+"/ppc/chkBatch.htm",{batch:bth},function(d){
									if(d.success){
										framework.closeMask();
										$('#cardList').datagrid("reload");
									}else{
										framework.alert(d.errMsg);
									}
								},'json');
							}else{
								framework.closeMask();
							}					
						});	
					}			
				}
				// check the card by ids
				chkChecked = function(){
					var checkDatas = gd.datagrid("getChecked");
					if(checkDatas.length > 0){
						$.messager.confirm('','确定审核所选择的卡',function(r){
							if(r){
								framework.startMask();	
								var ids = [];
								$.each(checkDatas,function(index,data){
									ids.push(data.id);
								});
								$.post(framework.dynUrl+"/ppc/chkByIds.htm",{ids:ids.join(",")},function(d){
									if(d.success){
										gd.datagrid("reload");
									}else{
										framework.alert(d.errMsg);
									}
									
								},"json");
								framework.closeMask();
							}							
						});
					}
				}//end of chkChecked.				
	});
		
		
	$('.easyui-datebox').datebox({ editable:false });	
	</script>

</body>
</html>