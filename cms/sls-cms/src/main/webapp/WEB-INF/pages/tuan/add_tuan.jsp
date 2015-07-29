<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../commons/commons.jsp" %>
</head>
<body>
	<div class="easyui-panel">
	<br/>
		<form class="form-inline" role="form" style="padding-left:30px" id="act_form">
			<div class="form-group">
				<label>活动类型：</label>
				<select class="easyui-combobox" data-options="required:true" id="act_type" name="actType" style="width: 120px">
					<option value="30">团购</option>
					<option value="25">限时抢购</option>
				</select>
			</div>
			<div class="form-group">
				<label>活动名称：</label>
				<input class="easyui-textbox" data-options="required:true" id="act_name" name="actName" style="width: 120px;height: 25px;"/>
			</div>
			<div class="form-group">
				<label>活动开始时间：</label>
				<input class="easyui-datetimebox" data-options="required:true" id="act_starttime" name="startTime" />
			</div>
			<div class="form-group">
				<label>活动结束时间：</label>
				<input class="easyui-datetimebox" data-options="required:true" id="act_endtime" name="endTime" />
			</div>
		</form>
		<div style="padding-left:30px">
			备注：<textarea cols="155" rows="2" id="act_remark"></textarea>
			<button class="btn btn-primary" id="submit">保存并提交审核</button>&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-primary" id="reset">重置</button>
		</div>
	</div>
	<div class="easyui-panel" title="活动商品列表">
		<div id="act_link">
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="captureData()">添加表格商品</a>
		</div>
		<div class="easyui-datagrid" id="act_datagrid">
		</div>
	</div>
	<div class="form-inline" role="form">
	  <div class="form-group">
	    <label>商品id：</label>
	    <input type="text" style="width: 120px;height: 25px;"  class="form-control" id="g_goodsId" placeholder="商品id">
	  </div>
	  <div class="form-group">
	      <label>商品货号：</label>
	      <input class="form-control" style="width: 120px;height: 25px;"  type="text" id="g_sku" placeholder="商品货号">
	  </div>
	   <div class="form-group">
	      <label>商品名称：</label>
	      <input class="form-control" style="width: 120px;height: 25px;"  type="text" id="g_name" placeholder="商品名称">
	  </div>
	   <div class="form-group">
	      <label>供货商：</label>
	      <select class="easyui-combobox" id="g_provider"  style="width: 120px;height: 25px;"  data-options="valueField:'v',textField:'k',url:'${dynUrl }/provider/getProvidersList.htm',mode:'remote',method:'post'">
	      </select>
	  </div>
	  <div class="form-group">
	      <label>价格区间：</label>
		  <input class="easyui-numberbox" id="g_price" style="width:50px" data-options="min:0,precision:2"/>-<input class="easyui-numberbox" id="g_price2" style="width:50px" data-options="min:0,precision:2"/>		     
	  </div>
	  <button type="submit" class="btn btn-default" onclick="goodsSubmit()">查询</button>
	</div>
	<div id="goods_datagrid"></div>
	<script type="text/javascript">
		$(document).ready(function(){
			var cur_update_count = 0;
			$("#submit").click(function(){
				if(cur_update_count > 0){
					framework.alert("存在活动商品修改没有保存，请先保存");
					return;
				}
				var form = $("#act_form");
				var data = act_datagrid.datagrid("getData").rows;
				if(form.form("validate")&&data.length > 0){
					var param = form.serializeObj();
					if(param.startTime >= param.endTime){
						framework.alert("开始时间大于结束时间");
						return;
					}
					param.remark = $("#act_remark").val();
					var obj = []
					$.each(data,function(i,d){
						var map = {};
						map.goodsId = d.goodsId;
						map.actPrice = d.actPrice;
						map.number = d.number;
						obj.push(map);
					});
					param.data = JSON.stringify(obj);
					framework.startMask();
					$.post(framework.dynUrl+"/tuan/add.htm",param,function(d){
						if(d.success){
							framework.alert("创建成功");
							$("#reset").click();
						}else{
							framework.alert(d.errMsg);
						}
						framework.closeMask();
					},"json");
				}
			});
			$("#reset").click(function(){
				$("#act_form").form("clear");
				$("#act_remark").val("");
				act_datagrid.datagrid("clearSelections");
				act_datagrid.datagrid("loadData",{rows:[],total:0});
			});
			goodsSubmit = function(){
				goods_datagrid.datagrid("load");
			}
			captureData = function(){
				var data = goods_datagrid.datagrid("getChecked");
				console.log(data);
				if(data.length > 0){
					var input_d = [];
					var cur_data = act_datagrid.datagrid("getData").rows;
					$.each(data,function(i,d){
						var isE = false;
						$.each(cur_data,function(i,cur_d){
							if(d.id == cur_d.goodsId){
								isE = true;
							}
						});
						if(!isE){
							input_d.push(d);
						}
					});
					$.each(input_d,function(i,d){
						var map = {};
						map.goodsId = d.id;
						map.goodsName = d.goodsName;
						map.sku = d.sku;
						map.virtualNumber = d.virtualNumber;
						map.price = d.price;
						map.actPrice = d.price;
						map.number = 0;
						map.id = d.id;
						act_datagrid.datagrid("appendRow",map);
					});
				}
			}
			var act_datagrid = $("#act_datagrid").datagrid({
		        method: 'post',
		        border: true, 
		        fitColumns:true,
		        rownumbers:true,
		        idField:"id",
		        toolbar:"#act_link",
		        columns:[[{
		        	field:"goodsId",title:"商品id",width:100
		        },{
		        	field:"sku",title:"商品货号",width:100
		        },{
		        	field:"goodsName",title:"商品名称",width:100
		        },{
		        	field:"virtualNumber",title:"库存",width:100
		        },{
		        	field:"price",title:"价格",width:100
		        },{
		        	field:"actPrice",title:"活动价",editor:{type:"numberbox",options:{min:0,required:true,precision:2}},width:100
		        },{
		        	field:"number",title:"(单个用户)限购数量",editor:{type:"numberbox",options:{min:0,required:true}},width:100
		        },{
		        	field:"id",title:"操作",formatter:function(value,rowData,rowIndex){
		        		return '<button onclick="act_s_editor('+value+')">编辑</button><button onclick="act_s_save('+value+')">保存</button><button onclick="act_s_del('+value+')">删除</button>';
		        	},width:100
		        }]],
		        onBeforeEdit:function(rowIndex, rowData){
		        	cur_update_count ++;
		        },
		        onAfterEdit:function(rowIndex, rowData, changes){
		        	cur_update_count --;
		        }
			});
			act_s_editor = function(id){
				var index = act_datagrid.datagrid("getRowIndex",id);
				act_datagrid.datagrid("beginEdit",index);
			}
			act_s_save = function(id){
				var index = act_datagrid.datagrid("getRowIndex",id);
				act_datagrid.datagrid("endEdit",index);
			}
			act_s_del = function(id){
				var index = act_datagrid.datagrid("getRowIndex",id);
				act_datagrid.datagrid("deleteRow",index);
			}
			var goods_datagrid = $('#goods_datagrid').datagrid({ 
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