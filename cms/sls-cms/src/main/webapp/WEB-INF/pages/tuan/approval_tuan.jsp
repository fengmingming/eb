<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../commons/commons.jsp" %>
</head>
<body>
	<form class="form-inline" role="form" id="query_form">
		<div class="form-group">
			<label>状态：</label>
			<select name="ing" class="easyui-combobox" style="width: 120px">
				<option value=""></option>
				<option value="0">已过时</option>
				<option value="1">进行中</option>
				<option value="2">未开始</option>
			</select>
		</div>
		<div class="form-group">
			<label>活动名称：</label>
			<input class="easyui-textbox" name="actName" style="width: 120px"/>
		</div>
		<div class="form-group">
			<label>上架状态：</label>
			<select name="state" class="easyui-combobox" style="width: 120px">
				<option value=""></option>
				<option value="1">上架</option>
				<option value="0">下架</option>
			</select>
		</div>
		<div class="form-group">
			<label>审核状态：</label>
			<select name="isVerify" class="easyui-combobox" style="width: 120px">
				<option value=""></option>
				<option value="1">审核</option>
				<option value="0" selected="selected">未审核</option>
			</select>
		</div>
		<div class="form-group">
			<label>开始时间：</label>
			<input class="easyui-datetimebox" name="startTime"/>
		</div>
		<div class="form-group">
			<label>结束时间：</label>
			<input class="easyui-datetimebox" name="endTime"/>
		</div>
		<button class="btn btn-primary" id="query">查询</button>
	</form>
	<div id="act_datagrid">
	</div>
	<div id="act_detail_dg"></div>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#query").click(function(e){
				act_datagrid.datagrid("load");
				e.preventDefault();
				e.stopPropagation();
			});
			actDetail = function(actId){
				act_detail_dg.datagrid("load",{actId:actId});
			}
			verify = function(actId){
				framework.startMask();
				$.post(framework.dynUrl+"/tuan/update.htm",{id:actId,isVerify:true},function(d){
					if(!d.success){
						framework.alert(d.errMsg);
					}
					act_datagrid.datagrid("reload");
					framework.closeMask();
				},"json");
			}
			updateDetail = function(index){
				act_detail_dg.datagrid("beginEdit",index);
			}
			saveDetail = function(index){
				act_detail_dg.datagrid("endEdit",index);
			}
			var act_datagrid = $("#act_datagrid").datagrid({
				url:framework.dynUrl+"/tuan/sel.htm",
				method: 'post',
		        border: true, 
		        title:"活动列表",
		        singleSelect:true,
		        fitColumns:true,
		        rownumbers:true,
		        checkOnSelect:false,
		        selectOnCheck:false,
		        pageSize:framework.pageNum,
		        pageList:[20,30,40,50],
		        pagination:true,
		        onBeforeLoad:function(param){
		        	$.extend(param,$("#query_form").serializeObj());
		        },
		        columns:[[{
		        	field:"actName",title:"活动名称",width:100
		        },{
		        	field:"actType",title:"活动类型",formatter:function(value){
		        		return framework.actEnum[value];
		        	},width:50
		        },{
		        	field:"rf",title:"活动状态",formatter:function(value,rowData,rowIndex){
		        		var curTime = new Date().formatDate("yyyy-MM-dd HH:mm:ss");
		        		if(curTime > rowData.endTime){
		        			return "已过时";
		        		}else if(curTime >= rowData.startTime&&curTime <= rowData.endTime){
		        			return "进行中";
		        		}else if(curTime < rowData.startTime){
		        			return "未开始";
		        		}
		        	}
		        },{
		        	field:"startTime",title:"开始时间",width:50
		        },{
		        	field:"endTime",title:"结束时间",width:50
		        },{
		        	field:"remark",title:"备注",width:100
		        },{
		        	field:"state",title:"上架状态",formatter:function(value){
		        		if(value == 0){
		        			return "下架";
		        		}
		        		return "上架";
		        	},width:30
		        },{
		        	field:"isVerify",title:"审核状态",formatter:function(value){
		        		if(value == 0){
		        			return "未审核";
		        		}
		        		return "审核";
		        	},width:30
		        },{
		        	field:"id",title:"操作",formatter:function(value,rowData,rowIndex){
		        		var re = '<button onclick="actDetail('+value+')">查看商品</button>';
		        		if(!rowData.isVerify){
		        			re = re + '<button onclick="verify('+value+')">审核</button>';
		        		}
		        		return re;
		        	},width:50
		        }]],
		        loadFilter:function(data){
		        	if(!data.success){
		        		framework.alert(data.errMsg);
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
			var act_detail_dg = $("#act_detail_dg").datagrid({
				url:framework.dynUrl+"/tuan/detail.htm",
				title:"活动商品列表",
				method: 'post',
		        border: true, 
		        singleSelect:true,
		        fitColumns:true,
		        rownumbers:true,
		        checkOnSelect:false,
		        selectOnCheck:false,
		        pageSize:framework.pageNum,
		        pageList:[20,30,40,50],
		        pagination:true,
		        columns:[[{
		        	field:"sku",title:"商品编号",width:100
		        },{
		        	field:"goodsName",title:"商品名称",width:100
		        },{
		        	field:"price",title:"手拉手价格",width:50
		        },{
		        	field:"actPrice",title:"促销价",editor:{type:"numberbox",options:{min:0,precision:2}},width:50
		        },{
		        	field:"virtualNumber",title:"库存",width:50
		        },{
		        	field:"number",title:"(单个用户)限购数量",editor:{type:"numberbox",options:{min:0}},width:50
		        },{
		        	field:"id",title:"操作",formatter:function(id,rowData,rowIndex){
		        		return '<button onclick="updateDetail('+rowIndex+')">修改</button><button onclick="saveDetail('+rowIndex+')">保存</button>';
		        	},width:100
		        }]],
		        loadFilter:function(data){
		        	if(!data.success){
		        		framework.alert(data.errMsg);
		        		return [];
		        	}else{
		        		if(!data.result){
		        			return {total:0,rows:[]};
		        		}
		        		var result = {
			        			total:data.result.total,
			        			rows:data.result.entry,
			        	};
			        	return result;
		        	}
		        },
		        onAfterEdit:function(rowIndex, rowData, changes){
		        	if(changes.number||changes.actPrice){
		        		var param = {};
		        		param.id = rowData.id;
		        		if(changes.actPrice){
		        			param.actPrice = changes.actPrice;
		        		}
		        		if(changes.number){
		        			param.number = changes.number;
		        		}
		        		framework.startMask();
		        		$.post(framework.dynUrl+"/tuan/updateDetail.htm",param,function(d){
		        			if(!d.success){
		        				framework.alert(d.errMsg);
		        				act_detail_dg.datagrid("reload");
		        			}
							framework.closeMask();
		        		},"json");
		        	}
		        }
			});
		});
	</script>
</body>
</html>