<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../commons/commons.jsp" %>
</head>
<body>
	<div class="form-inline">
		<div class="form-group">
			<label>类型：</label>
			<select class="easyui-combobox" id="type" data-options="required:true">
				<option value="1">动态页</option>
				<option value="2">特价商品专区</option>
				<option value="3">有机蔬菜专区</option>
				<option value="4">进口水果专区</option>
				<option value="5">意大利食品专区</option>
				<option value="6">韩国食品专区</option>
				<option value="7">海鲜专区</option>
				<option value="8">地方特产</option>
				<option value="9">进口食品</option>
			</select>
		</div>
		<div class="form-group">
			<label>动态页标题：</label>
			<input class="form-control" id="title"/>
		</div>
		<div class="form-group">
			<button id="add_btn">新增</button>
			<button id="flush_btn">重置</button>
		</div>
	</div>
	<div class="easyui-panel" title="动态页商品列表">
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="captureData()">添加商品数据</a>
		<div id="dynpage_dg"></div>
	</div>
	<div class="form-inline" role="form">
	  <div class="form-group">
	    <label>商品id：</label>
	    <input type="text" class="form-control" id="g_goodsId" placeholder="商品id" style="width: 120px;height: 25px;">
	  </div>
	  <div class="form-group">
	      <label>商品货号：</label>
	      <input class="form-control" type="text" id="g_sku" placeholder="商品货号" style="width: 120px;height: 25px;">
	  </div>
	   <div class="form-group">
	      <label>商品名称：</label>
	      <input class="form-control" type="text" id="g_name" placeholder="商品名称" style="width: 120px;height: 25px;">
	  </div>
	   <div class="form-group">
	      <label>供货商：</label>
	      <select class="easyui-combobox" id="g_provider" style="width:120px" data-options="valueField:'v',textField:'k',url:'${dynUrl }/provider/getProvidersList.htm',mode:'remote',method:'post'">
	      </select>
	  </div>
	  <div class="form-group">
	      <label>价格区间：</label>
		  <input class="easyui-numberbox" id="g_price" style="width:50px" data-options="min:0,precision:2"/>-<input class="easyui-numberbox" id="g_price2" style="width:50px" data-options="min:0,precision:2"/>		     
	  </div>
	  <button type="submit" class="btn btn-default" onclick="goodsSubmit()">查询</button>
	</div>
	<div id="goods_dg"></div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#add_btn").click(function(){
			var data = dynpage_dg.datagrid("getChecked");
			var title = $.trim($("#title").val());
			var type = $("#type").combobox("getValue");
			if(!type||!title||!data.length>0){
				framework.alert("类型，动态页标题和数据不能为空");
				return;
			}
			var ids = "";
			$.each(data,function(i,d){
				ids = ids + d.id + ",";
			});
			ids = ids.substring(0,ids.length - 1);
			framework.startMask();
			$.post("${dynUrl}/dynpage/add.htm",{type:type,title:title,ids:ids},function(d){
				framework.closeMask();
				if(d.success){
					framework.alert("创建成功：活动id为"+d.result);
					$("#flush_btn").click();
				}else{
					framework.alert(d.errMsg);
				}
			},"json");
		});
		$("#flush_btn").click(function(){
			$("#title").val("");
			dynpage_dg.datagrid("loadData",[]);
		});
		goodsSubmit = function(){
			goods_dg.datagrid("load");
		}
		captureData = function(){
			var cur_data = dynpage_dg.datagrid("getData").rows;
			var data = goods_dg.datagrid("getChecked");
			var in_data = []
			if(data.length>0){
				if(cur_data&&cur_data.length>0){
					for(var d in data){
						d = data[d];
						var b = true;
						for(var dd in cur_data){
							dd = cur_data[dd];
							if(d.id == dd.id){
								b = false;
							}
						}
						if(b){
							dynpage_dg.datagrid("insertRow",{row:d});
						}
					}
				}else{
					dynpage_dg.datagrid("loadData",data);
				}
			}
		}
		var dynpage_dg = $("#dynpage_dg").datagrid({
			border: true, 
	        fitColumns:true,
	        rownumbers:true,
	        columns:[[{
	        	field:"check",checkbox:true
	        },{
	        	field:"id",title:"商品id",width:20
	        },{
	        	field:"sku",title:"商品货号",width:20
	        },{
	        	field:"goodsName",title:"商品名称",width:20
	        },{
	        	field:"isSale",title:"状态",width:20,formatter:function(value){if(value==1){return '上架'}else if(value==2){return '下架'} else {return '删除'}}
	        },{
	        	field:"price",title:"价格",width:20
			},{
	        	field:"marketPrice",title:"市场价格",width:20
			},{
	        	field:"barCode",title:"条形码",width:20
			},{
	        	field:"operatorName",title:"操作人",width:20
			},{
	        	field:"providerName",title:"供货商",width:20
			},{
	        	field:"isReal",title:"是否虚拟",width:20,formatter:function(value){if(value==0){return '否'}else if(value==1){return '是'}}
			},{
				field:'virtualNumber',title:'库存',width:20
			},{
				field:'limittype',title:'限制级别',width:20,formatter:function(value){switch(value){case 0:return '不限制';case 1:return '省';case 2:return '市';case 3:return '区';case 4:return '商圈';case 5:return '亭子'}}
			},{
	        	field:"place",title:"场地",width:20
			},{
	        	field:"remark",title:"备注",width:20
			},{
				field:'brandName',title:'品牌名称',width:20
			},{
	        	field:"areaName",title:"范围",width:20
			},{
	        	field:"createtime",title:"创建时间",width:20
			},{
	        	field:"modifytime",title:"修改时间",width:20
			}]]
		});
		var goods_dg = $('#goods_dg').datagrid({ 
			title:"商品列表",
	        pageSize:framework.pageNum,
	        pageList:[20,30,40,50],
	        method: 'post',
	        border: true, 
	        url:'${dynUrl}/goods/filter.htm', 
	        pagination:true, 
	        singleSelect:true,
	        fitColumns:true,
	        rownumbers:true,
	        checkOnSelect:false,
	        selectOnCheck:false,
	        columns:[[{
	        	field:"check",checkbox:true
	        },{
	        	field:"id",title:"商品id",width:20
	        },{
	        	field:"sku",title:"商品货号",width:20
	        },{
	        	field:"goodsName",title:"商品名称",width:20,formatter:function(value,rowData,rowIndex){
	        		return '<a href="'+framework.detailUrl+rowData.id+'" target="_blank">'+value+'</a>';
	        	}
	        },{
	        	field:"isSale",title:"状态",width:20,formatter:function(value){if(value==1){return '上架'}else if(value==2){return '下架'} else {return '删除'}}
	        },{
	        	field:"price",title:"价格",width:20
			},{
	        	field:"marketPrice",title:"市场价格",width:20
			},{
	        	field:"barCode",title:"条形码",width:20
			},{
	        	field:"operatorName",title:"操作人",width:20
			},{
	        	field:"providerName",title:"供货商",width:20
			},{
	        	field:"isReal",title:"是否虚拟",width:20,formatter:function(value){if(value==0){return '否'}else if(value==1){return '是'}}
			},{
				field:'virtualNumber',title:'库存',width:20
			},{
				field:'limittype',title:'限制级别',width:20,formatter:function(value){switch(value){case 0:return '不限制';case 1:return '省';case 2:return '市';case 3:return '区';case 4:return '商圈';case 5:return '亭子'}}
			},{
	        	field:"place",title:"场地",width:20
			},{
	        	field:"remark",title:"备注",width:20
			},{
				field:'brandName',title:'品牌名称',width:20
			},{
	        	field:"areaName",title:"范围",width:20
			},{
	        	field:"createtime",title:"创建时间",width:20
			},{
	        	field:"modifytime",title:"修改时间",width:20
			}]],
	        onBeforeLoad: function (param) {
	        	param.goodsId = $.trim($("#g_goodsId").val());
	        	param.sku=$.trim($("#g_sku").val());
	        	param.name=$.trim($("#g_name").val());
	        	param.provider=$("#g_provider").combobox("getValue");
	        	param.price=$.trim($("#g_price").val());
	        	param.price2=$.trim($("#g_price2").val());
	        },
	        onLoadSuccess: function () {
	            
	        },
	        onLoadError: function () {
	            
	        },
	        onClickCell: function (rowIndex, field, value) {
	        	
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
	});
</script>
</body>
</html>