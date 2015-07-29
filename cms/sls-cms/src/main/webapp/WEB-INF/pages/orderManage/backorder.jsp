<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../commons/commons.jsp" %>
</head>
<body>
	<div class="form-inline" style="padding:10px 0px 0px 10px">
		<div class="form-group">
			<label>订单号：</label>
			<input type="text" id="orderNum" class="easyui-textbox" style="max-width:300px;width:300px"/>
			<button id="orderQuery" class="btn btn-default">快捷查询</button>
		</div>
		<div class="form-group">
			<label>用户名：</label><input type="text" id="userName" class="easyui-textbox" style="max-width:300px;width:300px"/>
			<label>手机号：</label><input type="text" id="mobile" class="easyui-textbox" style="max-width:300px;width:300px"/>
			<button id="userQuery" class="btn btn-default">查询</button>
		</div>
	</div>
	<form id="backorder_form">
		<div class="form-inline" style="padding:10px 0px 0px 10px">
			<div class="form-group">
				<label>查询结果-用户名：</label><input type="hidden" name="userId" id="userId"/>
				<input type="text" id="userName2" class="easyui-textbox" style="max-width:300px;width:300px" readonly="readonly"/>
				<label>手机号：</label><input type="text" id="mobile2" class="easyui-textbox" style="max-width:300px;width:300px" readonly="readonly"/>
			</div>
		</div>
		<div class="form-inline" style="padding:10px 0px 0px 10px">
			<div class="form-group">
				<label>收货地址：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;省</label>
				<select class="easyui-combobox" id="provinceId" name="provinceId" style="width:150px" data-options="required:true,valueField:'v',textField:'k',data:framework.areaFilter(1,3),
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
				<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市：</label> <select style="width:150px" class="easyui-combobox" name="cityId" id="cityId" data-options="required:true,valueField:'v',textField:'k',
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
				<label>&nbsp;&nbsp;&nbsp;&nbsp;区：</label> <select class="easyui-combobox" style="width:150px" name="districtId" id="districtId" data-options="required:true,valueField:'v',textField:'k',
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
				<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商圈：</label> <select style="width:150px" class="easyui-combobox" id="communityId" name="communityId" data-options="required:true,valueField:'v',textField:'k',
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
				<label>亭子：</label> <select class="easyui-combobox" style="width:200px" id="pavilionId" name="pavilionId" data-options="required:true,valueField:'v',textField:'k'"
					style="width: 120px">
				</select>
			</div>
		</div>
		<div class="form-inline" style="padding:10px 0px 0px 80px">
			<div class="form-group">
				<label>收货人：</label>
				<input class="easyui-textbox" name="receiver" id="receiver" data-options="required:true"/>
			</div>
			<div class="form-group">
				<label>手机号：</label>
				<input class="easyui-textbox" name="mobile" id="mobile3" data-options="required:true"/>
			</div>
			<div class="form-group">
				<label>邮编：</label>
				<input class="easyui-textbox" name="postcode" id="postcode"/>
			</div>
			<div class="form-group">
				<label>详细地址：</label>
				<input class="easyui-textbox" name="remark" id="remark" data-options="width:400,required:true"/>
			</div>
		</div>
		<div class="form-inline" style="padding:10px 0px 0px 70px">
			<div class="form-group">
				<label>提货方式：</label>
				<select class="easyui-combobox" name="deliveryType" style="width:150px" data-options="required:true">
					<option value="1">自提</option>
					<option value="2">送货上门</option>
				</select>
			</div>
			<div class="form-group">
				<label>付款方式：</label>
				<select class="easyui-combobox" name="orderWay" style="width:150px" data-options="required:true">
					<option value="1">支付宝</option>
					<option value="4">微信</option>
				</select>
			</div>
			<div class="form-group">
				<label>订单备注：</label>
				<input class="easyui-textbox" name="orderRemark" data-options="required:true,width:400"/>
			</div>
		</div>
	</form>
	<button class="btn btn-block btn-primary" id="submit_order">提交订单</button>
	<h4>商品列表</h4>
	<a href="javascript:void(0)" class="easyui-linkbutton btn-primary" data-options="iconCls:'icon-ok',plain:true" onclick="captureData()">抓取数据</a>
	<div id="orderDetail_dg"></div>
	<div style="padding-top:10px">
	<%@include file="../goods/goodsFragment.jsp" %>
	</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#orderQuery").click(function(){
			var orderNum = $("#orderNum").textbox("getValue");
			framework.startMask();
			$.get(framework.dynUrl+"/order/info.htm",{orderNum:orderNum},function(d){
				framework.closeMask();
				if(d.success){
					if(d.result){
						var re = d.result;
						$("#userId").val(re.userId);
						$("#userName2").textbox("setValue",re.userName);
						$("#mobile2").textbox("setValue",re.mobile);
						$("#provinceId").combobox("setValue",re.provinceId);
						$("#cityId").combobox("loadData",framework.areaFilter(re.provinceId,6));
						$("#cityId").combobox("setValue",re.cityId);
						$("#districtId").combobox("loadData",framework.areaFilter(re.cityId,9));
						$("#districtId").combobox("setValue",re.districtId);
						if(framework.areaFilter(re.districtId,9).length > 0&&re.communityId){
							$("#communityId").combobox("loadData",framework.areaFilter(re.districtId,9));
							$("#communityId").combobox("setValue",re.communityId);
							if(framework.areaFilter(re.communityId,12).length > 0 && re.pavilionId){
								$("#pavilionId").combobox("loadData",framework.areaFilter(re.communityId,12));
								$("#pavilionId").combobox("setValue",re.pavilionId);
							}
						}
						$("#receiver").textbox("setValue",re.receiver);
						$("#mobile3").textbox("setValue",re.mobile2);
						$("#postcode").textbox("setValue",re.postcode);
						$("#remark").textbox("setValue",re.remark);
					}else{
						framework.alert("没有订单记录");	
					}
				}else{
					framework.alert(d.errMsg);
				}
			},'json');
		});
		$("#userQuery").click(function(){
			framework.startMask();
			$.get(framework.dynUrl+"/member/userinfo.htm",{userName:$("#userName").textbox("getValue"),mobile:$("#mobile").textbox("getValue")},function(d){
				framework.closeMask();
				if(d.success){
					if(d.result){
						$("#userId").val(d.result.userId);
						$("#userName2").textbox("setValue",d.result.userName);
						$("#mobile2").textbox("setValue",d.result.mobile);
					}else{
						framework.alert("没有查到用户信息");	
					}
				}else{
					framework.alert(d.errMsg);
				}
			},'json');
		});
		$("#submit_order").click(function(){
			var form = $("#backorder_form");
			if(form.form("validate")){
				var param = form.serializeObj();
				param.details = dg.datagrid("getChecked");
				if(param.details.length == 0){
					framework.alert("还没有选择商品");
					return;
				}
				param.provinceName = $("#provinceId").combobox("getText");
				param.cityName = $("#cityId").combobox("getText");
				param.districtName = $("#districtId").combobox("getText");
				param.communityName = $("#communityId").combobox("getText");
				param.pavilionName = $("#pavilionId").combobox("getText");
				framework.startMask();
				$.ajax({
					type:"post",
					url:framework.dynUrl+"/order/commit.htm",
					contentType:"application/json; charset=utf-8",
					data:JSON.stringify(param),
					dataType:"json",
					success:function(d){
						framework.closeMask();
						if(d.success){
							framework.alert("订单号："+d.result);
						}else{
							framework.alert(d.errMsg);
						}
					},
					error:function(){
						framework.closeMask();
						framework.alert("服务器异常");
					}
				});
			}
		});
		captureData = function(){
			var inData = window.goods_dg.datagrid("getChecked");
			if(inData.length>0){
				var curData = dg.datagrid("getData").rows;
				if(curData.length>0){
					$.each(curData,function(i,d){
						$.each(inData,function(j,dd){
							if(dd&&dd.id == d.goodsId){
								inData[j] = null;
							}
						});
					});
				}
				$.each(inData,function(i,d){
					if(d){
						var map = {};
						map.goodsId = d.id;
						map.goodsName = d.goodsName;
						map.providerName = d.providerName;
						map.providerId = d.providerId;
						map.price = d.price;
						map.marketPrice = d.marketPrice;
						map.number = 1;
						dg.datagrid("insertRow",{row:map});
					}
				});
			}
		}
		update = function(index){
			dg.datagrid("beginEdit",index);
		}
		save = function(index){
			dg.datagrid("endEdit",index);
		}
		var dg = $("#orderDetail_dg").datagrid({
			border:true,
			fitColumns:true,
			columns:[[{
				field:"check",checkbox:true,width:10
			},{
				field:"goodsName",title:"商品名称",width:300
			},{
				field:"providerName",title:"供货商",width:100
			},{
				field:"marketPrice",title:"市场价",width:50
			},{
				field:"price",title:"购买价",width:50,editor:{type:"numberbox",options:{min:0.00,precision:2}}
			},{
				field:"number",title:"数量",width:50,editor:{type:"numberbox",options:{min:1,precision:0}}
			},{
				field:"btn",title:"操作",width:100,formatter:function(v,rowData,index){
					return "<button onclick='save("+index+")'>保存</button><button onclick='update("+index+")'>修改</button>";
				}
			}]]
		});
		
	});
</script>
</body>
</html>