<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../commons/commons.jsp" %>
</head>
<body>
<div class="form-inline" style="padding:15px 0px 0px 15px">
	<div class="form-group">
		<label>订单号：</label>
		<input id="orderNum" class="easyui-textbox"/>
	</div>
	<div class="form-group">
		<label>类型：</label>
		<select class="easyui-combobox" id="type">
			<option value=""></option>
			<option value="1">退货</option>
			<option value="2">换货</option>
		</select>
	</div>
	<div class="form-group">
		<label>状态：</label>
		<select class="easyui-combobox" id="state">
			<option value=""></option>
			<option value="1">处理中</option>
			<option value="2">处理完成</option>
			<option value="127">取消申请</option>
		</select>
	</div>
	<div class="form-group">
		<label>时间：</label>
		<input id="startDate" class="easyui-datetimebox"/>至
		<input id="endDate" class="easyui-datetimebox"/>
	</div>
	<div class="form-group">
		<button id="query" class="btn btn-default btn-xs">查询</button>
		<button id="exportExcel" class="btn btn-default btn-xs">导出excel</button>
	</div>
</div>
<div id="refund_dg"></div>
<div id="detail_win" class="easyui-window" data-options="width:1000,height:600,title:'退换货详情',closed:true,modal:true">
	<form id="updateRefund" class="form" style="padding-left:15px">
		<input type="hidden" id="refundId" name="id"/>
		<div class="form-group">
			<label>退换货商品：</label>
			<img src="" id="goodsImg" class="img-rounded" style="width:200px;height:200px">
		</div>
		<div class="form-group">
			<label>退换货金额：</label>
			<input class="easyui-numberbox" id="money" name="refundPrice" data-options="required:true,min:0,precision:2"/>
		</div>
		<div class="form-group">
			<label>取货地址：<input type="checkbox" id="pickupWay"/>&nbsp;&nbsp;送货至服务亭</label>
			<div class="form">
				<div class="form-group" style="padding-left:60px">
					<select class="easyui-combobox" id="provinceIdT" name="provinceIdT" style="width:150px" data-options="required:true,valueField:'v',textField:'k',data:framework.areaFilter(1,3),
						onSelect:function(record){
							$('#cityIdT').combobox('clear');
							var r = framework.areaFilter(record.v,6);
							if(r){
								$('#cityIdT').combobox('loadData',r);
							}
						}"
						style="width: 120px">
					</select>
					<select style="width:150px" class="easyui-combobox" id="cityIdT" name="cityIdT" data-options="required:true,valueField:'v',textField:'k',
					onSelect:function(record){
						$('#districtIdT').combobox('clear');
						var r = framework.areaFilter(record.v,9);
						if(r){
							$('#districtIdT').combobox('loadData',r);
						}
					}"
					style="width: 120px">
					</select>
					<select class="easyui-combobox" style="width:150px" id="districtIdT" name="districtIdT" data-options="required:true,valueField:'v',textField:'k',
					onSelect:function(record){
						$('#communityIdT').combobox('clear');
						var r = framework.areaFilter(record.v,12);
						if(r){
							$('#communityIdT').combobox('loadData',r);
						}
					}"
					style="width: 120px">
					</select>
					<select style="width:150px" class="easyui-combobox" id="communityIdT" name="communityIdT" data-options="required:true,valueField:'v',textField:'k',
					onSelect:function(record){
						$('#pavilionIdT').combobox('clear');
						var r = framework.areaFilter(record.v,15);
						if(r){
							$('#pavilionIdT').combobox('loadData',r);
						}
					}"
					style="width: 120px">
					</select>
					<select class="easyui-combobox" style="width:200px" id="pavilionIdT" name="pavilionIdT" data-options="required:true,valueField:'v',textField:'k'"
					style="width: 120px">
					</select>
				</div>
				<div class="form-group">
					<label>&nbsp;&nbsp;&nbsp;收货人：</label>
					<input class="easyui-textbox" id="receiverT" name="receiverT" data-options="required:true"/>
					<label>手机号：</label>
					<input class="easyui-textbox" id="mobileT" name="mobileT" data-options="required:true"/>
					<label>详细地址：</label>
					<input class="easyui-textbox" id="remarkT" name="remarkT" data-options="required:true"/>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label>收货地址：<input type="checkbox" id="deliveryType"/>&nbsp;&nbsp;到亭自提</label>
			<div class="form">
				<div class="form-group" style="padding-left:60px">
					<select class="easyui-combobox" id="provinceIdF" name="provinceIdF" style="width:150px" data-options="required:true,valueField:'v',textField:'k',data:framework.areaFilter(1,3),
						onSelect:function(record){
							$('#cityIdF').combobox('clear');
							var r = framework.areaFilter(record.v,6);
							if(r){
								$('#cityIdF').combobox('loadData',r);
							}
						}"
						style="width: 120px">
					</select>
					<select style="width:150px" class="easyui-combobox" id="cityIdF" name="cityIdF" data-options="required:true,valueField:'v',textField:'k',
					onSelect:function(record){
						$('#districtIdF').combobox('clear');
						var r = framework.areaFilter(record.v,9);
						if(r){
							$('#districtIdF').combobox('loadData',r);
						}
					}"
					style="width: 120px">
					</select>
					<select class="easyui-combobox" style="width:150px" id="districtIdF" name="districtIdF" data-options="required:true,valueField:'v',textField:'k',
					onSelect:function(record){
						$('#communityIdF').combobox('clear');
						var r = framework.areaFilter(record.v,12);
						if(r){
							$('#communityIdF').combobox('loadData',r);
						}
					}"
					style="width: 120px">
					</select>
					<select style="width:150px" class="easyui-combobox" id="communityIdF" name="communityIdF" data-options="required:true,valueField:'v',textField:'k',
					onSelect:function(record){
						$('#pavilionIdF').combobox('clear');
						var r = framework.areaFilter(record.v,15);
						if(r){
							$('#pavilionIdF').combobox('loadData',r);
						}
					}"
					style="width: 120px">
					</select>
					<select class="easyui-combobox" style="width:200px" id="pavilionIdF" name="pavilionIdF" data-options="required:true,valueField:'v',textField:'k'"
					style="width: 120px">
					</select>
				</div>
				<div class="form-group">
					<label>&nbsp;&nbsp;&nbsp;收货人：</label>
					<input class="easyui-textbox" id="receiverF" name="receiverF" data-options="required:true"/>
					<label>手机号：</label>
					<input class="easyui-textbox" id="mobileF" name="mobileF" data-options="required:true"/>
					<label>详细地址：</label>
					<input class="easyui-textbox" id="remarkF" name="remarkF" data-options="required:true"/>
				</div>
			</div>
		</div>
		<div class="from-group text-center" id="btns">
			<button class="btn btn-default" id="saveRefund">保存修改</button>
			<button class="btn btn-default" id="completeRefund">处理完成</button>
			<button class="btn btn-default" id="removeRefund">取消申请</button>
		</div>
	</form>
</div>
<div id="img_win" class="easyui-window" data-options="width:800,height:500,title:'退换货客户留言',closed:true,modal:true">
	<div class="form">
		<div class="form-group">
			<label>留言：</label>
			<textarea rows="10" cols="120" id="user_remark"></textarea>
		</div>
		<div class="form-group">
			<label>晒图：</label>
			<img class="img-rounded" src="" id="photoUrl1" style="width:200px;height:200px">
			<img class="img-rounded" src="" id="photoUrl2" style="width:200px;height:200px">
			<img class="img-rounded" src="" id="photoUrl3" style="width:200px;height:200px">
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#query").click(function(){
		dg.datagrid("load");
	});
	$("#exportExcel").click(function(){
		var param = {};
		param.orderNum = $("#orderNum").textbox("getValue");
    	param.type = $("#type").combobox("getValue");
    	param.state = $("#state").combobox("getValue");
    	param.startDate = $("#startDate").datetimebox("getValue");
    	param.endDate = $("#endDate").datetimebox("getValue");
    	if(!param.startDate||!param.endDate){
    		framework.alert("导出excel，时间间隔必填，不能超过半年");
    		return;
    	}
    	var time = parseInt(new Date(param.endDate.substring(0,10).replace(/-/g,"/")).getTime() - new Date(param.startDate.substring(0,10).replace(/-/g,"/")).getTime());
    	if(time > 60*60*24*31*6*1000 || time < 0){
    		framework.alert("订单导出，时间间隔不能大于半年");
    		return false;
    	}
    	var href = framework.dynUrl+"/order/refundexcel.htm?";
    	for(var p in param){
    		href = href + p + "=" + param[p] + "&";
    	}
    	href = href.substring(0,href.length-1);
    	framework.openWindowTab(href);
	});
	var dwin = $("#detail_win");
	var iwin = $("#img_win");
	$("#saveRefund").click(function(e){
		e.stopPropagation();
		e.preventDefault();
		var form = $("#updateRefund");
		if(!form.form("validate")){
			return;
		}
		var param = form.serializeObj();
		if($("#pickupWay").attr("checked")){
			param.pickupWay = 1
		}else{
			param.pickupWay = 2
		}
		if($("#deliveryType").attr("checked")){
			param.deliveryType = 1
		}else{
			param.deliveryType = 2
		}
		framework.startMask();
		$.post(framework.dynUrl+"/order/updateRefund.htm",param,function(d){
			framework.closeMask();
			if(d.success){
				dg.datagrid("reload");
				dwin.window("close");
			}else{
				framework.alert(d.errMsg);
			}
		},"json");
	});
	$("#completeRefund").click(function(e){
		e.stopPropagation();
		e.preventDefault();
		var form = $("#updateRefund");
		if(!form.form("validate")){
			return;
		}
		$.messager.confirm("","是否将退款金额退返给用户账户",function(r){
			var param = form.serializeObj();
			if($("#pickupWay").attr("checked")){
				param.pickupWay = 1
			}else{
				param.pickupWay = 2
			}
			if($("#deliveryType").attr("checked")){
				param.deliveryType = 1
			}else{
				param.deliveryType = 2
			}
			framework.startMask();
			$.post(framework.dynUrl+"/order/updateRefund.htm",param,function(d){
				if(d.success){
					$.post(framework.dynUrl+"/order/completeRefund.htm",{id:param.id,type:r?1:0},function(d){
						framework.closeMask();
						if(d.success){
							dg.datagrid("reload");
							dwin.window("close");
						}else{
							framework.alert(d.errMsg);
						}
					},'json');
				}else{
					framework.closeMask();
					framework.alert(d.errMsg);
				}
			},"json");
		});
	});
	$("#removeRefund").click(function(e){
		var param = {id:$("#refundId").val()};
		param.state = 127;
		framework.startMask();
		$.post(framework.dynUrl+"/order/updateRefund.htm",param,function(d){
			framework.closeMask();
			if(d.success){
				dg.datagrid("reload");
				dwin.window("close");
			}else{
				framework.alert(d.errMsg);
			}
		},"json");
		e.stopPropagation();
		e.preventDefault();
	});
	lookupDetail=function(index){
		var d = dg.datagrid("getData").rows[index];
		$("#refundId").val(d.id);
		$("#goodsImg").attr("src",framework.imgUrl+"/200X200"+d.goodsUrl);
		$("#money").numberbox("setValue",d.refundPrice);
		if(d.pickupWay == 1){
			$("#pickupWay").attr("checked","checked");
		}else{
			$("#pickupWay").removeAttr("checked");
		}
		if(d.deliveryType == 1){
			$("#deliveryType").attr("checked","checked");
		}else{
			$("#deliveryType").removeAttr("checked");
		}
		$("#provinceIdT").combobox("setValue",d.provinceIdT);
		$("#cityIdT").combobox("loadData",framework.areaFilter(d.provinceIdT,6));
		$("#cityIdT").combobox("setValue",d.cityIdT);
		$("#districtIdT").combobox("loadData",framework.areaFilter(d.cityIdT,9));
		$("#districtIdT").combobox("setValue",d.districtIdT);
		if(framework.areaFilter(d.districtIdT,12).length > 0){
			$("#communityIdT").combobox("loadData",framework.areaFilter(d.districtIdT,12));
			$("#communityIdT").combobox("setValue",d.communityIdT);
			if(framework.areaFilter(d.communityIdT,15).length > 0){
				$("#pavilionIdT").combobox("loadData",framework.areaFilter(d.communityIdT,15));
				$("#pavilionIdT").combobox("setValue",d.pavilionIdT);
			}else{
				$("#pavilionIdT").combobox("loadData",[]);
			}
		}else{
			$("#communityIdT").combobox("loadData",[]);
			$("#pavilionIdT").combobox("loadData",[]);
		}
		$("#receiverT").textbox("setValue",d.receiverT);
		$("#mobileT").textbox("setValue",d.mobileT);
		$("#remarkT").textbox("setValue",d.remarkT);
		
		$("#provinceIdF").combobox("setValue",d.provinceIdF);
		$("#cityIdF").combobox("loadData",framework.areaFilter(d.provinceIdF,6));
		$("#cityIdF").combobox("setValue",d.cityIdF);
		$("#districtIdF").combobox("loadData",framework.areaFilter(d.cityIdF,9));
		$("#districtIdF").combobox("setValue",d.districtIdF);
		if(framework.areaFilter(d.districtIdF,12).length > 0){
			$("#communityIdF").combobox("loadData",framework.areaFilter(d.districtIdF,12));
			$("#communityIdF").combobox("setValue",d.communityIdF);
			if(framework.areaFilter(d.communityIdF,15).length > 0){
				$("#pavilionIdF").combobox("loadData",framework.areaFilter(d.communityIdF,15));
				$("#pavilionIdF").combobox("setValue",d.pavilionIdF);				
			}else{
				$("#pavilionIdF").combobox("loadData",[]);
			}
		}else{
			$("#communityIdF").combobox("loadData",[]);
			$("#pavilionIdF").combobox("loadData",[]);
		}
		$("#receiverF").textbox("setValue",d.receiverF);
		$("#mobileF").textbox("setValue",d.mobileF);
		$("#remarkF").textbox("setValue",d.remarkF);
		if(d.state != 1){
			$("#btns").hide();
		}else{
			$("#btns").show();
		}
		dwin.window("open");
	}
	lookupRemark=function(index){
		var d = dg.datagrid("getData").rows[index];
		$("#user_remark").val(d.remark);
		if(d.photoUrl1){
			$("#photoUrl1").attr("src",framework.imgUrl+d.photoUrl1);
		}else{
			$("#photoUrl1").attr("src","");
		}
		if(d.photoUrl2){
			$("#photoUrl2").attr("src",framework.imgUrl+d.photoUrl2);
		}else{
			$("#photoUrl2").attr("src","");
		}
		if(d.photoUrl3){
			$("#photoUrl3").attr("src",framework.imgUrl+d.photoUrl3);
		}else{
			$("#photoUrl3").attr("src","");
		}
		iwin.window("open");
	}
	var dg = $("#refund_dg").datagrid({
		pageSize:framework.pageNum,
        pageList:[20,30,40,50],
        method: 'post',
        border: true, 
        url:'${dynUrl}/order/refunds.htm', 
        pagination:true, 
        singleSelect:true,
        fitColumns:true,
        rownumbers:true,
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
        },
        onBeforeLoad: function (param) {
        	param.orderNum = $("#orderNum").textbox("getValue");
        	param.type = $("#type").combobox("getValue");
        	param.state = $("#state").combobox("getValue");
        	param.startDate = $("#startDate").datetimebox("getValue");
        	param.endDate = $("#endDate").datetimebox("getValue");
        },
        columns:[[{
        	field:"orderNum",title:"订单号",width:100
        },{
        	field:"origin",title:"来源",formatter:function(v){
        		switch(parseInt(v)){
        		case 1:return "客户";
        		case 2:return "亭子";
        		}
        	},width:30
        },{
        	field:"createTime",title:"创建时间",width:80
        },{
        	field:"state",title:"状态",formatter:function(v){
        		switch(parseInt(v)){
        		case 1:return "退换货中";
        		case 2:return "退换货完成";
        		case 127:return "取消申请";
        		}
        	},width:60
        },{
        	field:"type",title:"类型",formatter:function(v){
        		switch(parseInt(v)){
        		case 1:return "退货";
        		case 2:return "换货";
        		}
        	},width:30
        },{
        	field:"moneyWay",title:"退款方式",formatter:function(v){
        		switch(parseInt(v)){
        		case 1:return "余额";
        		case 2:return "付款账户";
        		}
        	},width:50
        },{
        	field:"remark",title:"客户留言",formatter:function(v,d,i){
        		return "<button onclick='lookupRemark("+i+")'>查看</button>";
        	},width:60
        },{
        	field:"account",title:"退款账户",width:100
        },{
        	field:"pickupWay",title:"取货方式",formatter:function(v){
        		switch(parseInt(v)){
        		case 1:return "送至服务亭";
        		case 2:return "上门自提";
        		}
        	},width:100
        },{
        	field:"deliveryType",title:"送货方式",formatter:function(v){
        		switch(parseInt(v)){
        		case 1:return "自提";
        		case 2:return "送货上门";
        		}
        	},width:100
        },{
        	field:"refundPrice",title:"退款金额",width:30
        },{
        	field:"remarkT",title:"取货地址",width:100
        },{
        	field:"remarkF",title:"收货地址",width:100
        },{
        	field:"btn",title:"操作",formatter:function(v,d,i){
        		if(d.state == 1){
        			return "<button onclick='lookupDetail("+i+")'>处理</button>";
        		}
        		return "<button onclick='lookupDetail("+i+")'>查看</button>";
        	},width:30
        }]]
	});
});
</script>
</body>
</html>