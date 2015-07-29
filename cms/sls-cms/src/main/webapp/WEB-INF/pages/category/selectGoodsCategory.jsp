<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../commons/commons.jsp" %>
</head>
<body>
	<div style="padding:10px">
		<form class="form-inline" role="form" id="selectGoodsCategoryForm" method="post" data-options="novalidate:true" action="${dynUrl }/goods/getGoodsList.htm">
			<div class="form-inline row">
				<div class="form-group col-md-2">
					<label>一级品类：</label> <select class="easyui-combobox"
						id="selectGoodsCategoryForm_oneId" name="oneId"
						data-options="valueField:'v',textField:'k',url:'${dynUrl}/category/combobox.htm?pid=0',
						loadFilter:function(data){
							return data.result;
							
						},
						onSelect:function(record){
							$('#selectGoodsCategoryForm_twoId').combobox('reload','${dynUrl }/category/combobox.htm?pid='+record.v);
						}"
						style="width: 120px">
					</select>
				</div>
				<div class="form-group col-md-2">
					<label>二级品类：</label> <select class="easyui-combobox" name="twoId"
						id="selectGoodsCategoryForm_twoId" style="width: 120px"
						data-options="valueField:'v',textField:'k',loadFilter:function(data){
							return data.result;
						},
						onSelect:function(record){
							$('#selectGoodsCategoryForm_threeId').combobox('reload','${dynUrl }/category/combobox.htm?pid='+record.v);
						}"
						style="width: 120px">
					</select>
				</div>
				<div class="form-group col-md-2">
					<label>三级品类：</label> <select class="easyui-combobox" name="threeId"
						id="selectGoodsCategoryForm_threeId" style="width: 120px"
						data-options="valueField:'v',textField:'k',loadFilter:function(data){
							return data.result;
						}">
					</select>
				</div>
			    <button type="submit" class="btn btn-primary" id="selectGoodsCategorySubmit" onclick="javascript:return false">查询</button>
			</div>
		</form>
	</div>
	<div id="tb" style="height:auto" title="商品列表" class="easyui-panel">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="batchUseCoupon()">可以使用优惠劵</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="batchNoUseCoupon()">不能使用优惠券</a>
	</div>
	<div id="selectGoodsCategoryPagation">
	</div> 
	<script type="text/javascript">
		var oneId;
		var twoId;
		var threeId;
		function assignment() {
			oneId = $("#selectGoodsCategoryForm_oneId").combobox('getValue');
			twoId = $("#selectGoodsCategoryForm_twoId").combobox('getValue');
			threeId = $("#selectGoodsCategoryForm_threeId").combobox('getValue');
		}
		$(document).ready(function(){
			batchUseCoupon = function(){
				var checkDatas = datagrid.datagrid("getChecked");
				if(checkDatas.length > 0){
					var ids = [];
					$.each(checkDatas,function(index,data){
						ids.push(data.id);
					});
					framework.startMask();
					$.post(framework.dynUrl+"/goods/batchUseCoupon.htm",{ids:ids.join(",")},function(d){
						if(d.success){
							datagrid.datagrid("reload");
						}else{
							framework.alert(d.errMsg);
						}
						framework.closeMask();
					},"json");
				}
			}
			
			batchNoUseCoupon = function(){
				var checkDatas = datagrid.datagrid("getChecked");
				if(checkDatas.length > 0){
					var ids = [];
					$.each(checkDatas,function(index,data){
						ids.push(data.id);
					});
					framework.startMask();
					$.post(framework.dynUrl+"/goods/batchNoUseCoupon.htm",{ids:ids.join(",")},function(d){
						if(d.success){
							datagrid.datagrid("reload");
						}else{
							framework.alert(d.errMsg);
						}
						framework.closeMask();
					},"json");
				}
			}
			var datagrid = $('#selectGoodsCategoryPagation').datagrid({ 
		        pageSize:framework.pageNum,
		        pageList:[20,30,40,50],
		        method: 'post',
		        border: true, 
		        url:'${dynUrl}/goods/goodsList.htm', 
		        pagination:true,
		        fitColumns:true,
		        rownumbers:true,
		        columns:[[{
		        	field:"check",checkbox:true
		        },{
		        	field:"sku",title:"商品货号",width:30
		        },{
		        	field:"goodsName",title:"商品名称",width:30
		        },{
		        	field:"price",title:"商品价格",width:30
		        },{
		        	field:"marketPrice",title:"市场价格",width:30
		        },{
		        	field:"isUseCoupon",title:"优惠券",width:20,formatter:function(v){
						switch(parseInt(v)){
						case 1:return "是";
						case 0:return "否";
						}
					}
		        },{
		        	field:"place",title:"原产地",width:30
		        },{
		        	field:"areaId",title:"范围区域id",width:30,formatter:function(value){
		        		return window.framework.area_map[value];
		        	}
		        },{
		        	field:"remark",title:"备注",width:50
		        }
		        ]],
		        onBeforeLoad: function (param) {
		        	param.oneId = oneId;
		        	param.twoId = twoId;
		        	param.threeId = threeId;
		        },
		        onLoadSuccess: function () {
		            
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
			$("#selectGoodsCategorySubmit").click(function(){
				$("#selectGoodsCategoryForm").form("submit",{
					onSubmit:function(){
						assignment();
						$('#selectGoodsCategoryPagation').datagrid("reload");
						return false;
					}
				});
			});
// 			一级失去焦点时，如果内容为空，那么二级为空。后面的同理
// 			当选择框选择东西时，它先触发失去焦点的方法，再触发onSelected方法。
			$("#selectGoodsCategoryForm_oneId").next().children('input').blur(function(){
				$("#selectGoodsCategoryForm_twoId").next().children('input').val('');
				$("div[id^='_easyui_combobox_i2']").empty();
			});
			$("#selectGoodsCategoryForm_twoId").next().children('input').blur(function(){
				$("#selectGoodsCategoryForm_threeId").next().children('input').val('');
				$("div[id^='_easyui_combobox_i3']").empty();
			});
			
		});
		
	</script>
</body>
</html>