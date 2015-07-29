<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../commons/commons.jsp" %>
</head>
<body>
	<form id="form" role="form" class="form-inline" style="padding:20px 0px 0px 50px">
		<div class="form-group">
			<label>商品id：</label>
			<input class="form-control" id="goodsId" style="width: 120px;height:25px;"/>
		</div>
		<div class="form-group">
			<label>商品货号：</label>
			<input class="form-control" id="sku" style="width: 120px;height:25px;"/>
		</div>
		<div class="form-group">
			<label>商品名称：</label>
			<input class="form-control" id="goodsName" style="width: 120px;height:25px;"/>
		</div>
		<div class="form-group">
			<label>供货商：</label>
			<select class="easyui-combobox" id="providerId" style="width:120px" data-options="valueField:'v',textField:'k',url:'${dynUrl }/provider/getProvidersList.htm',mode:'remote',method:'post'" style="width: 120px">
		    </select>
		</div>
		<div class="form-group">
			<label>库存区间：</label>
			<input class="easyui-numberbox" data-options="min:0" id="number" style="width:50px"/>-<input class="easyui-numberbox" data-options="min:0" id="number2" style="width:50px"/>
		</div>
		<div class="form-group">
			<button type="button" class="btn btn-primary" id="submit">查询</button>
			<button type="button" class="btn btn-primary" id="reset">重置</button>
		</div>
	</form>
	<div id="tb" style="height:auto" title="商品库存管理" class="easyui-panel"  style="padding-top:50px">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="save()">保存</a>
	</div>
	<div id="datagrid">
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			var curIndex;
			save = function(){
				if(curIndex != undefined){
					datagrid.datagrid("endEdit",curIndex);
				}
			};
			var datagrid = $("#datagrid").datagrid({
				columns:[[{
					field:"goodsName",title:"商品名称",width:300
				},{
					field:"virtualNumber",title:"虚拟库存量",editor:{type:"numberbox",options:{min:0}},width:200
				},{
					field:"providerName",title:"供货商",width:500
				}]],
				pageSize:framework.pageNum,
		        pageList:[20,30,40,50],
		        url:'${dynUrl}/goodsStock/selectpager.htm',
		        method: 'post',
		        border: true, 
		        pagination:true, 
		        singleSelect:true,
		        fitColumns:true,
		        rownumbers:true,
		        onBeforeLoad: function (param) {
		        	param.goodsId = $.trim($("#goodsId").val());
		        	param.sku=$.trim($("#sku").val());
		        	param.goodsName=$.trim($("#goodsName").val());
		        	param.providerId=$("#providerId").combobox("getValue");
		        	param.number=$.trim($("#number").val());
		        	param.number2=$.trim($("#number2").val());
		        },
		        onLoadSuccess: function () {
		            
		        },
		        onLoadError: function () {
		            
		        },
		        onBeforeEdit:function(rowIndex, rowData){
		        	curIndex = rowIndex;
		        },
		        onDblClickCell: function (rowIndex, field, value) {
		        	if(field == "virtualNumber"){
		        		if(curIndex == undefined){
			        		datagrid.datagrid("beginEdit",rowIndex);
			        	}else{
			        		datagrid.datagrid("endEdit",curIndex);
			        		datagrid.datagrid("beginEdit",rowIndex);
			        	}
		        	}
		        },
		        onAfterEdit:function(rowIndex, rowData, changes){
		        	curIndex = undefined;
		        	if(changes.virtualNumber){
		        		framework.startMask();
		        		$.post(window.framework.dynUrl+"/goodsStock/update.htm",{virtualNumber:changes.virtualNumber,id:rowData.id},function(data){
		        			if(data.success){
		        				
		        			}else{
		        				framework.alert(data.errMsg);
		        			}
		        			framework.closeMask();
		        		},"json");
		        	}
		        },
		        onDblClickRow: function (rowIndex, rowData) {
		        	
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
			$("#submit").click(function(){
				datagrid.datagrid("reload");
			});
			$("#reset").click(function(){
				$("#form").form("clear");
			});
		});
	</script>
</body>
</html>