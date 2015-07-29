<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../commons/commons.jsp" %>
</head>
<body>
	<div class="form-inline">
		<div class="form-group">
			<label>省：</label> <select class="easyui-combobox" id="provinceId" data-options="valueField:'v',textField:'k',data:framework.areaFilter(1,3),
				onSelect:function(record){
					$('#cityId').combobox('clear');
					var r = framework.areaFilter(record.v,6);
					if(r){
						$('#cityId').combobox('loadData',r);
					}
				}"
				style="width: 120px">
			</select>
		</div>
		<div class="form-group">
			<label>市：</label> <select class="easyui-combobox" id="cityId" data-options="valueField:'v',textField:'k',
				onSelect:function(record){
					$('#districtId').combobox('clear');
					var r = framework.areaFilter(record.v,9);
					if(r){
						$('#districtId').combobox('loadData',r);
					}
				}"
				style="width: 120px">
			</select>
		</div>
		<div class="form-group">
			<label>区：</label> <select class="easyui-combobox" id="districtId" data-options="valueField:'v',textField:'k',
				onSelect:function(record){
					$('#communityId').combobox('clear');
					var r = framework.areaFilter(record.v,12);
					if(r){
						$('#communityId').combobox('loadData',r);
					}
				}"
				style="width: 120px">
			</select>
		</div>
		<div class="form-group">
			<label>商圈：</label> <select class="easyui-combobox" id="communityId" data-options="valueField:'v',textField:'k',
				onSelect:function(record){
					$('#pavilionId').combobox('clear');
					var r = framework.areaFilter(record.v,15);
					if(r){
						$('#pavilionId').combobox('loadData',r);
					}
				}"
				style="width: 120px">
			</select>
		</div>
		<div class="form-group">
			<label>亭子：</label> <select class="easyui-combobox" id="pavilionId" name="pavilionId" data-options="valueField:'v',textField:'k'"
				style="width: 120px">
			</select>
		</div>
	</div>
	<div class="easyui-tabs">
		<div title="维护">
			<button id="query" class="btn btn-default">查询</button>
			<div id="dg" title="亭子周边信息"></div>
		</div>
		<div title="新增">
			<button id="addPa" class="btn btn-default">新增</button>
			<button id="savePa" class="btn btn-default">保存</button>
			<button id="flushPa" class="btn btn-default">重置</button>
			<div id="add_dg" title="新增亭子周边信息"></div>
		</div>
	</div>
<script type="text/javascript">
$(document).ready(function(){
	var loadNum = 0;
	var _cur_row = undefined;
	var dg = $("#dg").datagrid({
		fitColumns:true,
		singleSelect:true,
		pageSize:framework.pageNum,
        pageList:[20,30,40,50],
        method: 'post',
        border: true, 
        pagination:true, 
        rownumbers:true,
		loadFilter:function(data){
			if(!data.success){
        		return {total:0,rows:[]};
        	}else{
        		var result = {
	        			total:data.result.total,
	        			rows:data.result.entry,
	        	};
	        	return result;
        	}
		},
		onClickRow:function(rowIndex,rowData){
			cur_row = rowIndex;
		},
		onBeforeLoad:function(param){
			_cur_row = undefined;
			param.communityId = $("#communityId").combobox("getValue")
			param.pavilionId = $("#pavilionId").combobox("getValue");
			if(loadNum > 0 && !(param.communityId||param.pavilionId)){
				framework.alert("查询最小单位为商圈");
				return false;
			}
			loadNum++;
		},
		columns:[[{
			field:"pavilionName",title:"亭子名称",width:200
		},{
			field:"paName",title:"周边标志名称",editor:"text",width:400
		},{
			field:"isUse",title:"状态",formatter:function(v){
				switch(parseInt(v)){
				case 1:return "启用";
				case 2:return "禁用";
				}
			},editor:{type:"combobox",options:{textField:"k",valueField:"v",data:[{k:"启用",v:1},{k:"禁用",v:2}]}},width:100
		},{
			field:"isDel",formatter:function(v){
				return "正常";
			},editor:{type:"combobox",options:{textField:"k",valueField:"v",data:[{k:"正常",v:1},{k:"删除",v:127}]}},width:100
		}]],
		onDblClickRow:function(rowIndex){
			if(_cur_row!=undefined){
				dg.datagrid("endEdit",_cur_row);
			}else{
				_cur_row = rowIndex;
				dg.datagrid("beginEdit",rowIndex);
			}
		},
		onClickRow:function(){
			if(_cur_row!=undefined){
				dg.datagrid("endEdit",_cur_row);
			}
		},
		onAfterEdit:function(rowIndex, rowData, changes){
			_cur_row = undefined;
			if(changes&&(changes.paName||changes.isUse||changes.isDel)){
				var param = {id:rowData.id};
				param = $.extend(param,changes);
				framework.startMask();
				$.post("${dynUrl}/pavilionInfo/upcircum.htm",param,function(d){
					framework.closeMask();
					if(changes.isDel == 127){
						dg.datagrid("deleteRow",rowIndex);	
					}
				},"json");
			}
		}
	});
	var _cur_row2 = undefined;
	var add_dg = $("#add_dg").datagrid({
		fitColumns:true,
		columns:[[{
			field:"pavilionName",title:"亭子名称",width:200
		},{
			field:"paName",title:"周边标志名称",editor:"text",width:400
		},{
			field:"isUse",title:"状态",formatter:function(v){
				switch(v){
				case 1:return "启用";
				case 2:return "禁用";
				}
			},editor:{type:"combobox",options:{textField:"k",valueField:"v",data:[{k:"启用",v:1},{k:"禁用",v:2}]}},width:100
		}]],
		onDblClickRow:function(rowIndex){
			if(_cur_row2!=undefined){
				add_dg.datagrid("endEdit",_cur_row2);
			}else{
				_cur_row2 = rowIndex;
				add_dg.datagrid("beginEdit",rowIndex);
			}
		},
		onRowContextMenu:function(e,rowIndex){
			if(_cur_row2!=undefined){
				add_dg.datagrid("endEdit",_cur_row2);
			}
			add_dg.datagrid("deleteRow",rowIndex);
			e.preventDefault();
		},
		onClickRow:function(){
			if(_cur_row2!=undefined){
				add_dg.datagrid("endEdit",_cur_row2);
			}
		},
		onAfterEdit:function(rowIndex, rowData, changes){
			_cur_row2 = undefined;
		}
	});
	$("#query").click(function(){
		dg.datagrid({url:framework.dynUrl+"/pavilionInfo/pavilionarea.htm"});
	});
	$("#addPa").click(function(){
		var pavilionId = $("#pavilionId").combobox("getValue");
		if(!pavilionId){
			framework.alert("请选择亭子");
			return;
		}
		var pavilionName = $("#pavilionId").combobox("getText");
		add_dg.datagrid("insertRow",{row:{pavilionName:pavilionName,pavilionId:pavilionId,isUse:1}});
	});
	$("#savePa").click(function(){
		if(_cur_row2 != undefined){
			add_dg.datagrid("endEdit",_cur_row2);
		}
		var data = add_dg.datagrid("getData");
		if(data.rows.length > 0){
			framework.startMask();
			$.post("${dynUrl}/pavilionInfo/addPa.htm",{data:JSON.stringify(data.rows)},function(d){
				framework.closeMask();
				if(d.success){
					framework.alert("保存成功");
					$("#flushPa").click();
				}else{
					framework.alert(d.errMsg);
				}
			},"json");
		}
	});
	$("#flushPa").click(function(){
		_cur_row2 = undefined;
		add_dg.datagrid("loadData",[]);
	});
});
</script>
</body>
</html>