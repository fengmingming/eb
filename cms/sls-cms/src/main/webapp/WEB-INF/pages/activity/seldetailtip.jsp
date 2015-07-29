<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../commons/commons.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div role="form" class="form-inline">
		<div class="form-group">
			<label>范围：</label>
			<select id="type" class="easyui-combobox" style="width: 120px">
				<option value="">请选择</option>
				<option value="1">单品</option>
				<option value="2">品牌</option>
				<option value="3">供货商</option>
				<option value="4">品类</option>
			</select>
		</div>
		<div class="form-group">
			<label>活动情况：</label>
			<select id="isAct" class="easyui-combobox" style="width: 120px">
				<option value="">请选择</option>
				<option value="1">进行中</option>
				<option value="2">未进行</option>
			</select>
		</div>
		<button id="query">查询</button>
	</div>
	<div id="tip_dg"></div>
	<div id="detail_dg"></div>
<script type="text/javascript">
	$(document).ready(function(){
		var _tipId;
		var _type;
		var tip_dg = $("#tip_dg").datagrid({
			pageNum:window.framework.pageNum,
			singleSelect:true,
			border:true,
			pagination:true, 
	        fitColumns:true,
	        method:"post",
	        url:window.framework.dynUrl+"/detailtip/list.htm",
	        loadFilter:function(data){
	        	if(!data.success){
	        		framework.dialog(data);
	        		return {};
	        	}else{
	        		var result = {
		        			total:data.result.total,
		        			rows:data.result.entry||[],
		        	};
		        	return result;
	        	}
	        },
			columns:[[{
				field:"id",formatter:function(value,rowData){
					if(rowData.isDel == 1){
						return '<button onclick="deleteTip('+value+')">删除</button>'
					}
					return '';
				}
			},{
				field:"type",title:"范围",width:50,formatter:function(value){
					switch(parseInt(value)){
					case 1:return "单品";
					case 2:return "品牌";
					case 3:return "供货商";
					case 4:return "品类";
					}
				}
			},{
				field:"startdate",title:"开始时间",width:100
			},{
				field:"enddate",title:"结束时间",width:100
			},{
				field:"remark",title:"html代码",width:500,formatter:function(value){
					return framework.htmlEncode(value);
				}
			}]],
			onBeforeLoad:function(param){
				param.type = $.trim($("#type").combobox("getValue"));
				param.isAct = $.trim($("#isAct").combobox("getValue"));
			},
			onDblClickRow:function(rowIndex,rowData){
				_tipId = rowData.id;
				_type = rowData.type;
				detail_dg.datagrid("load");
			}
		});
		
		$("#query").click(function(){
			tip_dg.datagrid("load");
		});
		
		deleteTip = function(tipId){
			$.messager.confirm('','确定删除？',function(r){
				framework.startMask();
				$.post(framework.dynUrl+"/detailtip/del.htm",{tipId:tipId},function(d){
					if(d.success){
						framework.closeMask();
						tip_dg.datagrid("reload");
					}else{
						framework.alert(d.errMsg);
					}
				},'json');
			});
		}
		
		var detail_dg = $("#detail_dg").datagrid({
			pageNum:window.framework.pageNum,
			singleSelect:true,
			border:true,
			pagination:true, 
	        fitColumns:true,
	        method:"post",
	        url:window.framework.dynUrl+"/detailtip/rel.htm",
	        loadFilter:function(data){
	        	if(!data.success){
	        		framework.dialog(data);
	        		return {};
	        	}else{
	        		var result = {
		        			total:data.result.total,
		        			rows:data.result.entry||[],
		        	};
		        	return result;
	        	}
	        },
			columns:[[{
				field:"type",title:"范围",width:50,formatter:function(value){
					switch(parseInt(value)){
					case 1:return "单品";
					case 2:return "品牌";
					case 3:return "供货商";
					case 4:return "品类";
					}
				}
			},{
				field:"startdate",title:"开始时间",width:100
			},{
				field:"enddate",title:"结束时间",width:100
			},{
				field:"name",title:"名称",width:100
			}]],
			onBeforeLoad:function(param){
				param.tipId = _tipId;
				param.type = _type;
			}
		});
	});
</script>
</body>
</html>