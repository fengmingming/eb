<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../commons/commons.jsp" %>

</head>
<body>

   <div class="form-inline" role="form" style="padding:1em 0em 0em 1em">
	  <div class="form-group">
	      <label>订单号：</label>
	      <input class="easyui-textbox" type="text" id="orderNum"  style="width: 120px;height: 25px;" >
	  </div>
	  
	  <div class="form-group">
	    <label>会员姓名：</label>
	    <input type="text" style="width: 120px;height: 25px;"  class="easyui-textbox" id="member">
	  </div>
	  
	  <div class="form-group">
	      <label>电话：</label>
	      <input class="easyui-textbox" style="width: 120px;height: 25px;"  type="text" id="mobile">
	  </div>
	  
	  <div class="form-group">
	    <label>支付状态：</label>
	    <select class="easyui-combobox" id="isPaid" style="width:120px">
	    	<option value="">请选择</option>
		    <option value="1">未付款</option>
		    <option value="2">已付款</option>
		</select>
	  </div>
	  
	  <div class="form-group">
	  	<label>订单处理状态：</label>
	    <select class="easyui-combobox" id="status" style="width:120px">
	    	<option value="">请选择</option>
		    <option value="1">未确认</option>
		    <option value="2">已确认</option>
		    <option value="3">处理中</option>
		    <option value="4">已发货</option>
		    <option value="5">确认收货</option>
		    <option value="6">退货中</option>
		    <option value="7">退货完成</option>
		</select>
	  </div>
	  
	   <div class="form-group">
	  	<label>订单状态：</label>
	    <select class="easyui-combobox" id="state" style="width:120px">
	    	<option value="">请选择</option>
		    <option value="1">正常</option>
		    <option value="2">取消</option>
		    <option value="127">异常</option>
		</select>
	  </div>
	  <div class="form-group">
	  	<label>是否是亭子订单：</label>
	  	<select class="easyui-combobox" style="width:120px" id="isPaviOrder">
	  		<option value="">请选择</option>
	  		<option value="1">亭子订单</option>
	    	<option value="2">非亭子订单</option>
	  	</select>
	  </div>
	</div>
	
	<div class="form-inline" role="form" style="padding:1em 0em 0em 1em">
		 <div class="form-group">
		  	<label>订单渠道：</label>
		  	<select class="easyui-combobox" style="width:120px" id="isMobile">
		  		<option value="">请选择</option>
		  		<option value="1">pc</option>
		    	<option value="2">mobile</option>
		  	</select>
		  </div>
		  <div class="form-group">
		  	<label>下单类型：</label>
		  	<select class="easyui-combobox" style="width:120px" id="type">
		  		<option value="">请选择</option>
		  		<option value="1">代收</option>
		    	<option value="2">代购</option>
		  	</select>
		  </div>
	
		  <div class="form-group">
		  	<label>亭子：</label>
		  	<select class="easyui-combobox" data-options="textField:'k',valueField:'v',mode:'remote',url:'${dynUrl }/order/pavilion.htm',loadFilter:function(data){return data.result}" style="width:120px" id="pavilionId">
		  	</select>
		  </div>
		  <div class="form-group">
		  	<label>时间范围：</label>
		  	<input class="easyui-datetimebox" id="startdate" name="startdate" style="width:150px">&nbsp;&nbsp;至&nbsp;&nbsp;<input id="enddate" name="enddate" class="easyui-datetimebox" style="width:150px">
		  </div>
		  <button type="submit" class="btn btn-default" id="selectOrderSubmit" onclick="javascript:return false">查询</button>
	</div>
	
	<div class="easyui-panel" title="订单列表">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateOrder()">修改订单状态</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="payOrder()">支付</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="exportExcel()">导出excel</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="exportExcelDetail()">导出(包含明细)excel</a>
	</div>
	
	<div class="easyui-panel" id="shortcut">
		<a href="javascript:void(0)" class="easyui-linkbutton btn" data-options="iconCls:'icon-ok',plain:true" onclick="orderAll(this)">全部订单</a>
		<a href="javascript:void(0)" class="easyui-linkbutton btn" data-options="iconCls:'icon-ok',plain:true" onclick="orderNoPaid(this)">未付款</a>
		<a href="javascript:void(0)" class="easyui-linkbutton btn" data-options="iconCls:'icon-ok',plain:true" onclick="orderPaidOrOk(this)">已付款-已确认</a>
		<a href="javascript:void(0)" class="easyui-linkbutton btn" data-options="iconCls:'icon-ok',plain:true" onclick="orderWaitSend(this)">待发货</a>
		<a href="javascript:void(0)" class="easyui-linkbutton btn" data-options="iconCls:'icon-ok',plain:true" onclick="orderOut(this)">已出库</a>
		<a href="javascript:void(0)" class="easyui-linkbutton btn" data-options="iconCls:'icon-ok',plain:true" onclick="orderReceive(this)">已收货</a>
		<a href="javascript:void(0)" class="easyui-linkbutton btn" data-options="iconCls:'icon-ok',plain:true" onclick="order2(this)">取消订单</a>
		<a href="javascript:void(0)" class="easyui-linkbutton btn" data-options="iconCls:'icon-ok',plain:true" onclick="order127(this)">异常订单</a>
		<a href="javascript:void(0)" class="easyui-linkbutton btn" data-options="iconCls:'icon-ok',plain:true" onclick="orderDG(this)">代收</a>
		<a href="javascript:void(0)" class="easyui-linkbutton btn" data-options="iconCls:'icon-ok',plain:true" onclick="orderZG(this)">代购</a>
	</div>
	
	<div id="selectOrderPagation"></div>
	
	<div id="orderWindow" class="easyui-window" data-options="closed:true,modal:true,width:500,height:300" title="修改订单状态">
		<form role="form" method="post" id="orderForm" action="${dynUrl }/order/updatestatus.htm">
			<input type="hidden" name="id"/>
			<div class="form-group">
				<label>订单号：</label>
				<input type="text" readonly="readonly" style="width: 250px;height: 25px;"  name="orderNum" class="form-control"/>
			</div>
			
			<div class="form-group">
				<label>订单状态：</label>
				<select name="state" class="form-control">
					<option value="1">正常</option>
					<option value="127">异常</option>
				</select>
			</div>
			
			<div class="form-group">
				<label>订单处理状态：</label>
				<select name="status" class="form-control">
					<option value="1">未确认</option>
					<option value="2">确认</option>
					<option value="3">处理中</option>
					<option value="4">已发货</option>
					<option value="5">确认收货</option>
					<option value="6">退货中</option>
					<option value="7">退货完成</option>
				</select>
			</div>
		</form>
		<div>
			<button type="button" class="btn btn-primary btn-lg btn-block" id="updateorder_submit">提交</button>
		</div>
	</div>
	
	<script type="text/javascript">
		function clearParam(obj){
			$("#member").textbox("clear");
		   	$("#mobile").textbox("clear");
		   	$("#orderNum").textbox("clear");
		   	$("#isPaid").combobox("clear");
		   	$("#state").combobox("clear");
		   	$("#status").combobox("clear");
		   	$("#pavilionId").combobox("clear");
		   	$("#type").combobox("clear");
		   	$("#shortcut a").removeClass("btn-primary");
		   	$(obj).addClass("btn-primary");
		}
		function orderAll(obj){
			clearParam(obj);
			$("#selectOrderSubmit").click();
		}
		function orderNoPaid(obj){
			clearParam(obj);
			$("#state").combobox("setValue",1);
			$("#isPaid").combobox("setValue",1);
			$("#selectOrderSubmit").click();
		}
		function orderPaidOrOk(obj){
			clearParam(obj);
			$("#state").combobox("setValue",1);
			$("#isPaid").combobox("setValue",2);
			$("#status").combobox("setValue",2);
			$("#selectOrderSubmit").click();
		}
		function orderOut(obj){
			clearParam(obj);
			$("#state").combobox("setValue",1);
			$("#status").combobox("setValue",4);
			$("#selectOrderSubmit").click();
		}
		function orderReceive(obj){
			clearParam(obj);
			$("#state").combobox("setValue",1);
			$("#status").combobox("setValue",5);
			$("#selectOrderSubmit").click();
		}
		function order2(obj){
			clearParam(obj);
			$("#state").combobox("setValue",2);
			$("#selectOrderSubmit").click();
		}
		function order127(obj){
			clearParam(obj);
			$("#state").combobox("setValue",127);
			$("#selectOrderSubmit").click();
		}
		function orderDG(obj){
			clearParam(obj);
			$("#state").combobox("setValue",1);
			$("#type").combobox("setValue",1);
			$("#selectOrderSubmit").click();
		}
		function orderZG(obj){
			clearParam(obj);
			$("#type").combobox("setValue",2);
			$("#selectOrderSubmit").click();
		}
		function orderWaitSend(obj){
			clearParam(obj);
			$("#state").combobox("setValue",1);
			$("#status").combobox("setValue",3);
			$("#selectOrderSubmit").click();
		}
		$(document).ready(function(){
			$("#updateorder_submit").click(function(){
				$("#orderForm").form("submit",{
					onSubmit:function(){},
					success:function(data){
						try{data = $.parseJSON(data);}catch(e){}
						if(data.success){
							$("#orderWindow").window("close");
							$('#selectOrderPagation').datagrid('reload');
						}else{
							framework.alert(data.errMsg);
						}
					}
				});
			});
			function compareDate(){
				var startDate = $.trim($("#startdate").datetimebox("getValue"));
	        	var endDate = $.trim($("#enddate").datetimebox("getValue"));
	        	if(!startDate||!endDate){
	        		framework.alert("订单导出必须选择时间，时间间隔不能大于31天，超过31天联系技术人员");
	        		return false;
	        	}
	        	var time = parseInt(new Date(endDate.substring(0,10).replace(/-/g,"/")).getTime() - new Date(startDate.substring(0,10).replace(/-/g,"/")).getTime());
	        	if(time > 60*60*24*31*1000 || time < 0){
	        		framework.alert("订单导出，时间间隔不能大于31天");
	        		return false;
	        	}
	        	return true;
			}
			exportExcel = function(){
				if(compareDate()){
					var param = {};
					param.userName = $.trim($("#member").textbox("getValue"));
		        	param.mobile = $.trim($("#mobile").textbox("getValue"));
		        	param.orderNum = $.trim($("#orderNum").textbox("getValue"));
		        	param.isPaid = $.trim($("#isPaid").combobox("getValue"));
		        	param.state = $.trim($("#state").combobox("getValue"));
		        	param.status = $.trim($("#status").combobox("getValue"));
		        	param.pavilionId = $.trim($("#pavilionId").combobox("getValue"));
		        	param.startDate = $("#startdate").datetimebox("getValue");
		        	param.endDate = $("#enddate").datetimebox("getValue");
		        	param.type = $.trim($("#type").combobox("getValue"));
		        	param.isPaviOrder = $.trim($("#isPaviOrder").combobox("getValue"));
		        	param.isMobile = $.trim($("#isMobile").combobox("getValue"));
					framework.openWindowTab(window.framework.dynUrl+"/order/excel.htm?"+framework.encodeUri(param));
				}
			};
			exportExcelDetail = function(){
				if(compareDate()){
					var param = {};
					param.userName = $.trim($("#member").textbox("getValue"));
		        	param.mobile = $.trim($("#mobile").textbox("getValue"));
		        	param.orderNum = $.trim($("#orderNum").textbox("getValue"));
		        	param.isPaid = $.trim($("#isPaid").combobox("getValue"));
		        	param.state = $.trim($("#state").combobox("getValue"));
		        	param.status = $.trim($("#status").combobox("getValue"));
		        	param.pavilionId = $.trim($("#pavilionId").combobox("getValue"));
		        	param.startDate = $("#startdate").datetimebox("getValue");
		        	param.endDate = $("#enddate").datetimebox("getValue");
		        	param.type = $.trim($("#type").combobox("getValue"));
		        	param.isPaviOrder = $.trim($("#isPaviOrder").combobox("getValue"));
		        	param.isMobile = $.trim($("#isMobile").combobox("getValue"));
		        	param.hasDetail = 1;
					framework.openWindowTab(window.framework.dynUrl+"/order/excel.htm?"+framework.encodeUri(param));
				}
			}
			updateOrder = function(){
				var data = $('#selectOrderPagation').datagrid("getSelected");
				if(data){
					$("#orderForm").form("load",data);
					$("#orderWindow").window("open");
				}else{
					framework.alert("请选择订单项");
				}
			};
			payOrder = function(){
				var data = $('#selectOrderPagation').datagrid("getSelected");
				if(data){
					$.messager.confirm("订单支付","是否支付订单："+data.orderNum,function(r){
						if(r){
							if(data.isPaid != 2){
								$.post(window.framework.dynUrl+"/order/payop.htm",{orderId:data.id},function(data){
									if(data.success){
										$('#selectOrderPagation').datagrid('reload');
										framework.alert("操作成功");
									}else{
										framework.alert(data.errMsg);
									}
								},"json");
							}else{
								framework.alert("订单已经支付");								
							}
						}
					});
				}else{
					framework.alert("请选择订单项");
				}
			};
			$('#selectOrderPagation').datagrid({ 
		        pageSize:framework.pageNum,
		        method: 'post',
		        border: true, 
		        url:'${dynUrl}/order/searchOrders.htm', 
		        pagination:true, 
		        fitColumns:true,
		        singleSelect:true,
		        columns:[[{
		        	field:"orderNum",title:"订单编号",width:100
		        },{
		        	field:"userName",title:"会员名称",width:100
		        },{
		        	field:"memberType",title:"会员类型",formatter:function(value){
		        		if(value == 1){
		        			return "普通用户";
		        		}
		        		if(value == 2){
		        			return "服务亭人员";
		        		}
		        	},width:50
		        },{
		        	field:"mobile",title:"手机号",width:100
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
		        	field:"payName",title:"支付方式",width:100
		        },{
		        	field:"payTime",title:"付款时间",width:100
		        },{
		        	field:"amount",title:"商品总额",width:50
		        },{
		        	field:"paidPrice",title:"已付金额",width:50
		        },{
		        	field:"payPrice",title:"应付金额",width:50
		        },{
		        	field:"state",title:"订单状态",width:50,formatter:function(value){
		        		switch(value){
		        		case 1:return "正常";
		        		case 2:return "取消";
		        		case 127:return "异常";
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
		        	field:"channelId",title:"渠道",formatter:function(v){
		        		switch(v){
		        		case 1:return "pc";
		        		case 2:return "mobile";
		        		}
		        	}
		        },{
		        	field:"pavilionCode",title:"亭子编码",width:150
		        },{
		        	field:"pavilionName",title:"亭子",width:150
		        },{
		        	field:"createTime",title:"下单时间",width:100
		        }]],
		        onBeforeLoad: function (param) {
		        	param.userName = $.trim($("#member").textbox("getValue"));
		        	param.mobile = $.trim($("#mobile").textbox("getValue"));
		        	param.orderNum = $.trim($("#orderNum").textbox("getValue"));
		        	param.isPaid = $.trim($("#isPaid").combobox("getValue"));
		        	param.state = $.trim($("#state").combobox("getValue"));
		        	param.status = $.trim($("#status").combobox("getValue"));
		        	param.pavilionId = $.trim($("#pavilionId").combobox("getValue"));
		        	param.startDate = $("#startdate").datetimebox("getValue");
		        	param.endDate = $("#enddate").datetimebox("getValue");
		        	param.type = $.trim($("#type").combobox("getValue"));
		        	param.isPaviOrder = $.trim($("#isPaviOrder").combobox("getValue"));
		        	param.isMobile = $.trim($("#isMobile").combobox("getValue"));
		        },
		        onLoadSuccess: function (data) {
		        	
		        },
		        onLoadError: function () {
		            
		        },
		        onClickCell: function (rowIndex, field, value) {
		      
		        },
		        onDblClickRow:function(rowIndex,rowData){
		        	framework.flushTabs(window.framework.dynUrl+'/order/initorderdetail.htm?html=html&orderId='+rowData.id,'查看订单详情');
		        },
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
		        }
		    });
			
			$("#selectOrderSubmit").click(function(){
				$('#selectOrderPagation').datagrid('reload');
			});
			
		});
		
	</script>
</body>
</html>