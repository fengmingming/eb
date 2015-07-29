<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<div>
	<hr/>
	<div class="form-inline">
		<div class="form-group">
			<label>类型：</label>
			<select class="easyui-combobox" id="goodsFragment_type">
				<option value="1">商品id</option>
				<option value="2">商品sku</option>
				<option value="3">商品名称</option>
			</select>
		</div>
		<div class="form-group">
			<label>&nbsp;</label>
			<input class="easyui-textbox" id="goodsFragment_value"/>
		</div>
		<div class="form-group">
			<label>供货商：</label>
			<select class="easyui-combobox" id="goodsFragment_provider" style="width:120px" data-options="valueField:'v',textField:'k',url:'${dynUrl }/provider/getProvidersList.htm',mode:'remote',method:'post'">
	        </select>
		</div>
		<button class="btn btn-default" id="goodsFragment_queryBtn">查询</button>
	</div>
	<div id="goodsFragment_dg"></div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#goodsFragment_queryBtn").click(function(){
			window.goods_dg.datagrid("load");
		});
		window.goods_dg = $('#goodsFragment_dg').datagrid({ 
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
	        	var type = $("#goodsFragment_type").combobox("getValue");
	        	var value = $.trim($("#goodsFragment_value").textbox("getValue"));
	        	switch(parseInt(type)){
	        	case 1:param.goodsId = value;break;
	        	case 2:param.sku = value;break;
	        	case 3:param.name = value;break;
	        	}
	        	param.provider = $("#goodsFragment_provider").combobox("getValue");
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
</div>