<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../commons/commons.jsp" %>
</head>
<body id="export_jsp">
	<div title="订单批量导出" class="easyui-panel">
		<form role="form" class="form-inline" onsubmit="return false;">
			<div class="form-group">
				<label>选择时间范围：</label>
				<input class="easyui-datetimebox" id="startdate" name="startdate" style="width:150px">&nbsp;&nbsp;至&nbsp;&nbsp;<input id="enddate" name="enddate" class="easyui-datetimebox" style="width:150px">
			</div>
			<button type="button" class="btn btn-primary" onclick="insertExportOrder()">新建订单导出</button>
		</form>
	</div>
	<div title="导出记录列表" class="easyui-panel">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="deleteExportOrderRecord()">删除记录</a>
	</div>
	<div id="export_datagird"></div>
	
	<div id="tb">
		<div class="form-inline" role="form">
			<div class="form-group">
				<label>状态：</label>
				<select class="easyui-combobox" id="detailsort" style="width: 120px">
					<option value="1" selected="selected">选中</option>
					<option value="0">未选中</option>
				</select>
			</div>
			<div class="form-group">
				<label>订单号：</label>
				<input id="orderNum" class="easyui-textbox" style="width: 120px;height: 25px;"/>
			</div>
			<div class="form-group">
				<label>商品id：</label>
				<input id="goodsId" class="easyui-textbox" style="width: 120px;height: 25px;"/>
			</div>
			<div class="form-group">
				<label>货号：</label>
				<input id="sku" class="easyui-textbox" style="width: 120px;height: 25px;"/>
			</div>
			<div class="form-group">
				<label>商品名称：</label>
				<input id="goodsName" class="easyui-textbox" style="width: 120px;height: 25px;"/>
			</div>
			<div class="form-group">
				<label>供货商：</label>
				<select class="easyui-combobox" id="providerId" name="providerId" style="width: 120px" data-options="valueField:'v',textField:'k',method:'post',mode:'remote',url:'${dynUrl }/provider/getProvidersList.htm'">
				</select>
			</div>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-filter',plain:true" onclick="detailfilter()">过滤</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-filter',plain:true" onclick="clearfilter()">清空</a>
		</div>
		<div>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="sendOut()">发货</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-print',plain:true" onclick="exportExcelCG()">导出（采购）订单</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-print',plain:true" onclick="exportExcelFH()">导出（发货）订单</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="deleteExportOrderDetail()">删除单个订单</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="changeSelect()">批量选中(未选中)操作</a>
		</div>
	</div>
	
	<div id="exportdetail_datagrid" class="easyui-datagrid"></div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			var cur_exportNum = undefined;
			var cur_exportId = undefined;
			var cur_isSelect = undefined;
			detailfilter = function(){
				exportDetailGrid.datagrid("load");
			}
			changeSelect = function(){
				var checkeds = exportDetailGrid.datagrid("getChecked");
				var param = [];
				if(cur_isSelect == 0){
					$.each(checkeds,function(i,d){
						param.push(d.id);
					});
				}
				if(cur_isSelect == 1){
					var datas = exportDetailGrid.datagrid("getData").rows;
					var dataids = [];
					$.each(datas,function(i,d){
						dataids.push(d.id);
					})
					var checkedIds = [];
					$.each(checkeds,function(i,d){
						checkedIds.push(d.id);
					});
					$.each(dataids,function(i,d){
						var isAdd = true;
						$.each(checkedIds,function(i2,d2){
							if(d == d2){
								isAdd = false;
							}
						});
						if(isAdd){
							param.push(d);
						}
					});
				}
				if(param.length > 0){
					framework.startMask();
					$.post(window.framework.dynUrl+"/exportorder/select.htm",{curSelect:cur_isSelect,list:param.join()},function(d){
						if(d.success){
							exportDetailGrid.datagrid("reload");
						}else{
							framework.alert(d.errMsg);
						}
						framework.closeMask();
					},"json");
				}
			}
			orderDetailView = function(id){
				framework.flushTabs(window.framework.dynUrl+'/order/initorderdetail.htm?html=html&orderId='+id,'查看订单详情');
				return false;
			}
			sendOut = function(){
				if(cur_exportId){
					framework.startMask();
					$.messager.confirm("","确认发货吗？",function(r){
						if(r){
							$.post(window.framework.dynUrl+"/exportorder/sendout.htm",{exportId:cur_exportId},function(data){
								if(data.success){
									framework.alert("发货成功");
								}else{
									framework.alert(data.errMsg);
								}
								framework.closeMask();
							},"json");
						}else{
							framework.closeMask();
						}
					});
				}
			}
			clearfilter = function(){
				$("#sku").textbox("clear");
	        	$("#orderNum").textbox("clear");
	        	$("#goodsId").textbox("clear");
	        	$("#goodsName").textbox("clear");
	        	$("#providerId").combobox("clear");
			}
			insertExportOrder = function(){
				var startdate = $("#startdate").datetimebox("getValue");
				var enddate = $("#enddate").datetimebox("getValue");
				if(!startdate||!enddate){
					framework.alert("时间范围不能为空");
					return;
				}
				if(startdate>=enddate){
					framework.alert("结束时间不能小于开始时间");
					return;
				}
				framework.startMask();
				$.post(window.framework.dynUrl+"/exportorder/add.htm",{startdate:startdate,enddate:enddate},function(data){
					if(data.success){
						$("#export_datagird").datagrid("reload");
					}else{
						framework.alert(data.errMsg);
					}
					framework.closeMask();
				},"json");
			}
			exportExcelCG = function(){
				var data = $("#export_datagird").datagrid("getSelected");
				if(data){
					framework.openWindowTab(window.framework.dynUrl+"/exportorder/excel.htm?html=html&exportId="+data.id+"&exportNum="+data.exportNum+"&type=1");
				}else{
					framework.alert("请选择导出记录项");
				}
			}
			exportExcelFH = function(){
				var data = $("#export_datagird").datagrid("getSelected");
				if(data){
					framework.openWindowTab(window.framework.dynUrl+"/exportorder/excel.htm?html=html&exportId="+data.id+"&exportNum="+data.exportNum+"&type=2");
				}else{
					framework.alert("请选择导出记录项");
				}
			}
			deleteExportOrderRecord = function(){
				var data = $("#export_datagird").datagrid("getSelected");
				if(data){
					$.messager.confirm("删除功能","确认删除导出记录吗？",function(r){
						if(r){
							framework.startMask();
							$.post(window.framework.dynUrl+"/exportorder/deleterecord.htm",{exportId:data.id},function(data){
								if(data.success){
									$("#export_datagird").datagrid("reload");
								}else{
									framework.alert(data.errMsg);
								}
								framework.closeMask();
							},"json");
						}
					});
				}else{
					framework.alert("请选择删除项");
				}
			}
			deleteExportOrderDetail = function(){
				if(cur_exportNum){
					var datas = exportDetailGrid.datagrid("getChecked");
					if(datas.length == 1){
						var data = datas[0];
						$.messager.confirm("删除功能","确认删除订单"+data.orderNum+"记录",function(r){
							if(r){
								framework.startMask();
								$.post(window.framework.dynUrl+"/exportorder/deletedetail.htm",{id:data.id,exportNum:cur_exportNum},function(data){
									if(data.success){
										exportDetailGrid.datagrid("reload");
									}else{
										framework.alert(data.errMsg);
									}
									framework.closeMask();
								},"json");
							}
						});
					}else{
						framework.alert("只能删除单个订单");
					}
				}
			}
			$("#export_datagird").datagrid({
				pageNum:window.framework.pageNum,
				singleSelect:true,
				border:true,
				pagination:true, 
		        fitColumns:true,
		        method:"post",
		        url:window.framework.dynUrl+"/exportorder/records.htm",
		        columns:[[{
		        	field:"exportNum",title:"导出码",width:100
		        },{
		        	field:"number",title:"导出次数",width:50
		        },{
		        	field:"state",title:"状态",width:100,formatter:function(value){
		        		switch(value){
		        		case 0:return "未处理";
		        		case 1:return "处理中";
		        		case 2:return "已完成";
		        		}
		        	}
		        },{
		        	field:"startdate",title:"订单开始时间",width:100
		        },{
		        	field:"enddate",title:"订单结束时间",width:100
		        },{
		        	field:"createTime",title:"创建时间",width:100
		        },{
		        	field:"lastExportTime",title:"最后一次导出订单时间",width:100
		        },{
		        	field:"userName",title:"创建人",width:150
		        }]],
		        loadFilter:function(data){
		        	if(!data.success){
		        		framework.dialog(data);
		        		return {};
		        	}else{
		        		var result = {
			        			total:data.result.total,
			        			rows:data.result.entry,
			        	};
			        	return result;
		        	}
		        },
		        onDblClickRow:function(rowIndex,rowData){
		        	cur_exportNum = rowData.exportNum;
		        	cur_exportId = rowData.id;
		        	exportDetailGrid.datagrid("reload",{exportId:rowData.id})
		        }
			});
			exportDetailGrid_curData = undefined;
			exportDetailGrid = $("#exportdetail_datagrid").datagrid({
        		pageNum:window.framework.pageNum,
        		pageList:[10,20,50,100],
				border:true,
				toolbar:'#tb',
				url:window.framework.dynUrl+"/exportorder/detail.htm",
				pagination:true, 
		        fitColumns:true,
		        checkOnSelect:false,
		        selectOnCheck:false,
		        method:"post",
		        title:'出库明细列表',
		        columns:[[{
		        	field:"isSelect",checkbox:true
		        },{
		        	field:"orderNum",title:"订单号",width:100
		        },{
		        	field:"isPaid",title:"是否支付",formatter:function(value){
		        		if(value == 1){
		        			return "未支付";
		        		}
		        		if(value == 2){
		        			return "支付";
		        		}
		        	},width:50
		        },{
		        	field:"state",title:"订单状态",width:50,formatter:function(value){
		        		switch(value){
		        		case 1:return "正常";
		        		case 2:return "异常";
		        		case 127:return "删除";
		        		}
		        	}
		        },{
		        	field:"status",title:"订单处理状态",formatter:function(value){
		        		switch(value){
		        		case 1:return "未确认";
		        		case 2:return "确认";
		        		case 3:return "处理中";
		        		case 4:return "已发货";
		        		case 5:return "确认收货";
		        		case 6:return "退货中";
		        		case 7:return "退货完成";
		        		}
		        	},width:50
		        },{
		        	field:"createTime",title:"下单时间",width:100
		        },{
		        	field:"remark",title:"订单备注",width:100
		        },{
		        	field:"orderId",title:"",formatter:function(value){
		        		return '<button onclick="orderDetailView('+value+');return false;">查看订单详情</button>';
		        	}
		        }]],
		        onBeforeLoad:function(param){
		        	param.sku = $.trim($("#sku").textbox("getValue"));
		        	param.orderNum = $.trim($("#orderNum").textbox("getValue"));
		        	param.goodsId = $.trim($("#goodsId").textbox("getValue"));
		        	param.goodsName = $.trim($("#goodsName").textbox("getValue"));
		        	param.isSelect = $("#detailsort").combobox("getValue");
		        	param.providerId = $("#providerId").combobox("getValue");
		        	cur_isSelect = param.isSelect;
		        },
		        onClickRow:function(rowIndex,rowData){
		        	 $(this).datagrid('unselectRow', rowIndex);
		        },
		        loadFilter:function(data){
		        	if(!data.success){
		        		framework.dialog(data);
		        		return {};
		        	}else{
		        		var result = {
			        			total:data.result?data.result.total:0,
			        			rows:data.result?data.result.entry:[],
			        	};
			        	return result;
		        	}
		        },
		        onLoadSuccess:function(data){
		        	if(data){
		        		exportDetailGrid_curData = data;
		        		$.each(data.rows,function(i,d){
		        			if(d.isSelect == 1){
		        				exportDetailGrid.datagrid("checkRow",i);
		        			}
		        		});
		        	}
		        }
        	});
		});
	</script>
</body>
</html>