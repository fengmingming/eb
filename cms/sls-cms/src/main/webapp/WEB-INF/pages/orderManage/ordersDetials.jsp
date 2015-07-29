<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../commons/commons.jsp" %>
</head>
<body>
<div>
	<div class="easyui-panel" title="订单详情">
		<div class="row" style="width:100%">
			<div class="col-md-1"></div>
			<div class="col-md-2">
				<label>订单号：</label>
				<span>${order.orderNum }</span>
			</div>
			<div class="col-md-2">
				<label>订单状态：</label>
				<c:choose>
					<c:when test="${order.status == 1 }">
						<span>未确认</span>
					</c:when>
					<c:when test="${order.status == 2 }">
						<span>确认</span>
					</c:when>
					<c:when test="${order.status == 3 }">
						<span>处理中</span>
					</c:when>
					<c:when test="${order.status == 4 }">
						<span>已发货</span>
					</c:when>
					<c:when test="${order.status == 5 }">
						<span>确认收货</span>
					</c:when>
					<c:when test="${order.status == 6 }">
						<span>退货中</span>
					</c:when>
					<c:when test="${order.status == 7 }">
						<span>退货完成</span>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${order.isPaid == 1 }">
						--<sapn>未付款</sapn>
					</c:when>
					<c:when test="${order.isPaid == 2 }">
						--<sapn>已付款</sapn>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${order.state == 1 }">
						--<span>正常</span>
					</c:when>
					<c:when test="${order.state == 2 }">
						--<span>异常</span>
					</c:when>
					<c:when test="${order.state == 127 }">
						--<span>删除</span>
					</c:when>
				</c:choose>
			</div>
			<div class="col-md-2">
				<label>付款时间：</label>
				<span><f:formatDate value="${order.payTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
			</div>
			<div class="col-md-2">
				<label>用户名：</label>
				<span>${user.userName }</span>
			</div>
			<div class="col-md-2">
				<label>手机号：</label>
				<span>${user.mobile }</span>
			</div>
		</div>
		<div class="row" style="width:100%">
			<div class="col-md-1"></div>
			<div class="col-md-2">
				<label>性别：</label>
				<c:choose>
					<c:when test="${user.sex == 1 }">
						<span>男</span>
					</c:when>
					<c:when test="${user.sex == 2 }">
						<span>女</span>
					</c:when>
					<c:when test="${user.sex == 9 }">
						<span>其他</span>
					</c:when>
				</c:choose>
			</div>
			<div class="col-md-2">
				<label>等级：</label>
				<span>${user.rank }</span>
			</div>
			<div class="col-md-2">
				<label>昵称：</label>
				<span>${user.alias }</span>
			</div>
		</div>
		
		<div class="row" style="width:100%">
			<div class="col-md-1">
				
			</div>
			<div class="col-md-2">
				<label>收货人：</label>
				<span>${orderAddress.receiver }</span>
			</div>
			<div class="col-md-2">
				<label>收货人手机号：</label>
				<span>${orderAddress.mobile }</span>
			</div>
			<div class="col-md-2">
				<label>收货人固定电话：</label>
				<span>${orderAddress.phone }</span>
			</div>
		</div>
		
		<div class="row" style="width:100%">
			<div class="col-md-1">
			</div>
			<div class="col-md-4" style="height:40px;">
				<label>收货地址：</label>
				<span>${orderAddress.provinceName }</span>-<span>${orderAddress.cityName }</span>-<span>${orderAddress.districtName }</span>-<span>${orderAddress.communityName }</span>-<span>${orderAddress.pavilionName }</span>&nbsp;&nbsp;备注：<span>${orderAddress.remark }</span>
			</div>
			<div class="col-md-4">
				<label>期望收货时间：</label>
				<span><f:formatDate value="${orderAddress.startdate }" pattern="yyyy-MM-dd HH:mm:ss"/></span><span>&nbsp;&nbsp;至&nbsp;&nbsp;</span><span><f:formatDate value="${orderAddress.enddate }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
			</div>
			<div class="col-md-3">
				<c:if test="${order.state == 1 and order.status == 2 and orderAddress.pavilionId==null }">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateOrderDetail()">修改订单收货地址信息</a>
				</c:if>
			</div>
		</div>

		<div class="row" style="width:100%">
			<div class="col-md-1"></div>
			<div class="col-md-11">
				<label>订单金额：</label>
				<span>订单总金额<span>=</span>已付金额<span>+</span>应付金额</span><span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<span>${order.amount }<span>=</span>${order.paidPrice }<span>+</span>${order.payPrice }</span>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<span style="color:red">${coupon.name }</span>
			</div>
		</div>
	</div>
</div>	

<div id="orderdetail_datagrid">
</div>

<div id="orderact_datagrid">
</div>

<div id="orderlog_datagrid">
</div>

<div id="updateOrderDetailDiv" class="easyui-window" title="修改定单详情" data-options="closed:true,modal:true,width:500,height:450">
		<form id="updateOrderDetailForm" method="post" data-options="novalidate:true" action="${dynUrl }/order/updateOrderAddress.htm" style="padding-left:30px;padding-top:30px">
			<input type="hidden" name="addressId" value="${orderAddress.id }"/>
			<input type="hidden" name="orderId" value="${order.id }"/>
			<div class="form-group">
				<label style="width:100px">收货人：</label>
				<input class="easyui-textbox" name="receiver" data-options="required:true" style="width:200px" value="${orderAddress.receiver }"/>
			</div>
			<div class="form-group">
				<label style="width:100px">收货人手机号：</label>
				<input class="easyui-textbox" name="mobile" data-options="required:true,validType:'phoneRex'" style="width:200px" value="${orderAddress.mobile }"/>
			</div>
			<div class="form-group">
				<label style="width:100px">省：</label>
				<select class="easyui-combobox" name="province" id="provinceId2" data-options="required:true,valueField:'v',textField:'k',data:framework.areaFilter(1,3),
								onSelect:function(record){
									$('#cityId2').combobox('clear');
									var r = framework.areaFilter(record.v,6);
									if(r){
										$('#cityId2').combobox('loadData',r);
									}
								}"
								style="width: 120px" >
							</select>
			</div>
			<div class="form-group">
				<label style="width:100px">市：</label>
				<select class="easyui-combobox" id="cityId2" name="city" data-options="required:true,valueField:'v',textField:'k',
								onSelect:function(record){
									$('#districtId2').combobox('clear')
									var r = framework.areaFilter(record.v,9);
									if(r){
										$('#districtId2').combobox('loadData',r);
									}
								}"
								style="width: 120px"></select>
			</div>
			<div class="form-group">
				<label style="width:100px">区：</label>
				<select class="easyui-combobox" id="districtId2" name="district" data-options="required:true,valueField:'v',textField:'k',
								onSelect:function(record){
									$('#communityId2').combobox('clear')
									var r = framework.areaFilter(record.v,12);
									if(r){
										$('#communityId2').combobox('loadData',r);
									}
								}"
								style="width: 120px"></select>
			</div>
			<div class="form-group">
				<label style="width:100px">商圈：</label>
				<select class="easyui-combobox" id="communityId2" name="community" data-options="valueField:'v',textField:'k',
								onSelect:function(record){
									$('#pavilionId2').combobox('clear')
									var r = framework.areaFilter(record.v,15);
									if(r){
										$('#pavilionId2').combobox('loadData',r);
									}
								}"
								style="width: 120px"></select>
			</div>
			<div class="form-group">
				<label style="width:100px">亭子：</label>
				<select class="easyui-combobox" id="pavilionId2" name="pavilionId" data-options="valueField:'v',textField:'k'" style="width: 120px"></select>
			</div>
			<div class="form-group">
				<label style="width:100px">备注：</label>
				<input class="easyui-textbox" name="remark" data-options="required:true" style="width:200px" value="${orderAddress.remark }"/>
			</div>
		</form>
		<button type="button" id="updateOrderDetail_submit" class="btn btn-primary btn-block">提交</button>
	</div>

<script type="text/javascript">
	$(document).ready(function(){
		$('#orderdetail_datagrid').datagrid({ 
	        title:'订单详情列表', 
	        pageSize:framework.pageNum,
	        pageList:[20],
	        method: 'post',
	        border: true, 
	        url:'${dynUrl}/order/orderdetail.htm?orderId=${orderId}', 
	        pagination:true, 
	        fitColumns:true,
	        rownumbers:true,
	        columns:[[{
	        	field:"goodsName",title:"商品名称",width:150,formatter:function(value,rowData,rowIndex){
	        		return '<a target="_blank" href="'+framework.detailUrl+rowData.goodsId+'">'+value+"</a>";
	        	}
	        },{
	        	field:"goodsPrice",title:"商品价格",width:50
	        },{
	        	field:"orderPrice",title:"购买价格",width:50
	        },{
	        	field:"marketPrice",title:"市场价格",width:50
	        },{
	        	field:"number",title:"购买数量",width:50
	        },{
	        	field:"providerName",title:"供货商名称",width:150
	        },{
	        	field:"isDelivery",title:"是否已经提货",formatter:function(value){
	        		if(value == 0){
	        			return "未提货";
	        		}
	        		if(value == 1){
	        			return "已提货";
	        		}
	        	},width:50
	        }]],
	        onBeforeLoad: function (param) {
	        },
	        onLoadSuccess: function (data) {
	        	
	        },
	        onLoadError: function () {
	            
	        },
	        onClickCell: function (rowIndex, field, value) {
	            
	        },
	        onClickRow:function(rowIndex,rowData){
	        	
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
		
		$('#orderlog_datagrid').datagrid({ 
	        title:'订单处理日志列表', 
	        pageSize:framework.pageNum,
	        method: 'post',
	        border: true, 
	        url:'${dynUrl}/order/orderlog.htm?orderId=${orderId}', 
	        pagination:true, 
	        fitColumns:true,
	        rownumbers:true,
	        columns:[[{
	        	field:"operType",title:"操作类型",width:50,formatter:function(value){
	        		switch(value){
	        		case 1:return "后台人员";
	        		case 2:return "亭子人员";
	        		case 3:return "系统自动";
	        		}
	        	}
	        },{
	        	field:"operName",title:"操作人姓名",width:100
	        },{
	        	field:"createTime",title:"操作时间",width:50
	        },{
	        	field:"remark",title:"操作备注",width:300
	        }]],
	        onBeforeLoad: function (param) {
	        	
	        },
	        onLoadSuccess: function (data) {
	        	
	        },
	        onLoadError: function () {
	            
	        },
	        onClickCell: function (rowIndex, field, value) {
	            
	        },
	        onClickRow:function(rowIndex,rowData){
	        	
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
		
		$("#orderact_datagrid").datagrid({
			title:'订单商品参与活动信息', 
	        method: 'post',
	        border: true, 
	        url:'${dynUrl}/order/actinfo.htm?orderId=${orderId}', 
	        fitColumns:true,
	        rownumbers:true,
	        columns:[[{
	        	field:"goodsName",title:"商品名称",width:100
	        },{
	        	field:"actType",title:"活动类型",width:100,formatter:function(v){
	        		switch(parseInt(v)){
	        		case 25:return "限时抢购";
	        		case 30:return "团购";
	        		}
	        	}
	        },{
	        	field:"actName",title:"活动名称",width:100
	        },{
	        	field:"startTime",title:"活动开始时间",width:100
	        },{
	        	field:"endTime",title:"活动结束时间",width:100
	        }]],
	        loadFilter:function(data){
	        	var result = {};
	        	if(data.success){
	        		result.rows = data.result;
		        	result.total = data.result.length;
	        	}else{
	        		framework.alert(data.errMsg);
	        	}
	        	return result;
	        }
		});
		
		
		$("#updateOrderDetail_submit").click(function(){
			$('#updateOrderDetailForm').form('submit',{
				 onSubmit:function(){
					if($(this).form('enableValidation').form('validate')){
						framework.startMask();
						return true;
					}
					return false;
				 },
				 success:function(data){
					 framework.closeMask();
					 data = jQuery.parseJSON(data);
					 $("#updateOrderDetailDiv").window("close");
					 if(data.success){
						 location.reload();
					 }else{
						 framework.alert(data.errMsg);
					 }
				 }
			 });
		});
		
	});
	function updateOrderDetail(){
		$("#provinceId2").combobox("loadData",framework.areaFilter(1,3));
		$("#cityId2").combobox("loadData",framework.areaFilter("${orderAddress.province }",6));
 		$("#districtId2").combobox("loadData",framework.areaFilter("${orderAddress.city }",9));
		$("#communityId2").combobox("loadData",framework.areaFilter("${orderAddress.district }",12));
		$("#pavilionId2").combobox("loadData",framework.areaFilter("${orderAddress.community }",15));
		
		$("#provinceId2").combobox("setValue","${orderAddress.province }");
		$("#cityId2").combobox("setValue","${orderAddress.city }");
 		$("#districtId2").combobox("setValue","${orderAddress.district }");
		$("#communityId2").combobox("setValue","${orderAddress.community }");
		$("#pavilionId2").combobox("setValue","${orderAddress.pavilionId }");
	 	$("#updateOrderDetailDiv").window("open");
	}
	
</script>
</body>
</html>