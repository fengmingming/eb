<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<%@include file="../commons/commons.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${staUrl }/resources/kindeditor/kindeditor-all.js"></script>
<script type="text/javascript" src="${staUrl }/resources/kindeditor/lang/zh_CN.js"></script>
</head>
<body>
	<div style="padding:10px">
		<div class="form-inline" role="form">
		  <div class="form-group">
		    <label>商品类型：</label>
		    <select id="sg_isSale" class="easyui-combobox" style="width: 120px">
		    	<option selected="selected" value="1">上架</option>
		    	<option value="2">下架</option>
		    	<option value="127">不可见</option>
		    </select>
		  </div>
		  <div class="form-group">
		    <label>商品id：</label>
		    <input type="text" class="form-control" style="width: 120px;height: 25px;"  id="sg_goodsId" placeholder="商品id">
		  </div>
		  <div class="form-group">
		      <label>商品货号：</label>
		      <input class="form-control" type="text" style="width: 120px;height: 25px;"  id="sg_sku" placeholder="商品货号">
		  </div>
		  
		   <div class="form-group">
		      <label>商品名称：</label>
		      <input class="form-control" type="text" style="width: 120px;height: 25px;"  id="sg_name" placeholder="商品名称">
		  </div>
		  
		   <div class="form-group">
		      <label>供货商：</label>
		      <select class="easyui-combobox" id="sg_provider" style="width:120px" data-options="valueField:'v',textField:'k',url:'${dynUrl }/provider/getProvidersList.htm',mode:'remote',method:'post'" style="width: 120px">
		      </select>
		  </div>
		 
		  <div class="form-group">
		      <label>价格区间：</label>
			  <input class="easyui-numberbox" id="sg_price" style="width:50px" data-options="min:0,precision:2"/>-<input class="easyui-numberbox" id="sg_price2" style="width:50px" data-options="min:0,precision:2"/>		     
		  </div>
		  <button type="submit" class="btn btn-default" id="selectGoodsSubmit" onclick="javascript:return false">查询</button>
		</div>
	</div>
	
	<div>
		<button type="button" class="btn btn-default" aria-label="Left Align" id="showOrhideBtn">
		  ▽
		</button>
		<div id="showOrhideDiv" style="display:none">
			<button class="btn btn-default btn-block" id="flushGoodsBuyNumBtn" style="padding:10px 20px 10px 20px">刷新商品购买数量值</button>
		</div>
	</div>
	
	
	<div id="tb" style="height:auto" title="商品列表" class="easyui-panel">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="batchUp()">批量上架</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="batchDown()">批量下架</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="batchUseCoupon()">可以使用优惠劵</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="batchNoUseCoupon()">不能使用优惠券</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="batchDelete()">批量删除</a>
	</div>
	<div id="selectGoodsPagation">
	</div> 
	<div id="window" class="easyui-window" data-options="closed:true,modal:true,maximized:true,minimizable:false,maximizable:false" title="修改商品基本信息">
		<form style="padding-top: 20px" id="form">
			<div class="container">
				<div class="easyui-panel" title="商品品类选择" style="overflow: hidden;padding-left:20px">
					<div class="form-inline row">
						<div class="form-group col-md-3">
							<label>一级分类：</label> <select class="easyui-combobox"
								id="oneId" name="oneId"
								data-options="required:true,valueField:'v',textField:'k',url:'${dynUrl}/category/combobox.htm?pid=0',
								loadFilter:function(data){
									return data.result;
								},
								onSelect:function(record){
									$('#twoId').combobox('clear');
									$('#twoId').combobox('reload','${dynUrl }/category/combobox.htm?pid='+record.v);
								}"
								style="width: 120px">
							</select><span class="sls-required-icon">*</span>
						</div>
						<div class="form-group col-md-3">
							<label>二级分类：</label> <select class="easyui-combobox"
								id="twoId" name="twoId"
								data-options="valueField:'v',textField:'k',
								loadFilter:function(data){
									return data.result;
								},
								onSelect:function(record){
									$('#threeId').combobox('clear');
									$('#threeId').combobox('reload','${dynUrl }/category/combobox.htm?pid='+record.v);
								}"
								style="width: 120px">
							</select>
						</div>
						<div class="form-group col-md-3">
							<label>三级分类：</label> <select class="easyui-combobox" name="threeId"
								id="threeId" style="width: 120px"
								data-options="valueField:'v',textField:'k',loadFilter:function(data){
									return data.result;
								}">
							</select>
						</div>
					</div>
				</div>
				<div class="easyui-panel" title="商品基本信息" style="overflow: hidden;padding-left:20px">
					<div class="form-inline row">
						<div class="form-group col-dm-2">
							<label style="width:150px">货号:<span id="sku"></span></label> 
						</div>
						<div class="form-group col-dm-2">
							<label>商品名称:</label> <input class="easyui-textbox" type="text"
								name="goodsName" id="goodsName"
								data-options="required:true,validType:'length[1,50]'"
								style="width: 120px" /><span class="sls-required-icon">*</span>
						</div>
						<div class="form-group col-dm-2">
							<label>市场价格:</label> <input class="easyui-numberbox" type="text"
								name="marketPrice" id="marketPrice" data-options="required:true,min:0,precision:2"
								style="width: 120px" /><span class="sls-required-icon">*</span>
						</div>
						<div class="form-group col-dm-2">
							<label>商品价格:</label> <input class="easyui-numberbox" type="text"
								name="price" id="price" data-options="required:true,min:0,precision:2"
								style="width: 120px" /><span class="sls-required-icon">*</span>
						</div>
						<div class="form-group col-dm-2">
							<label>条形码:</label> <input class="easyui-textbox" type="text"
								name="barCode" id="barCode" style="width: 120px" />
						</div>
						<div class="form-group col-dm-2">
							<label>品牌:</label> <select class="easyui-combobox"
							 name="brandId" id="brandId" style="width: 120px" data-options="valueField:'v',textField:'k',url:'${dynUrl }/brand/combobox.htm',loadFilter:function(data){
									return data.result;
								}">
							</select>
						</div>
					</div>
	
					<div class="form-inline row" style="padding-top: 20px">
						<div class="form-group col-dm-2">
							<label>规格:</label> <input
								class="easyui-textbox" type="text" name="standard" id="standard"
								data-options="validType:'length[0,20]'"
								style="width: 120px" />
						</div>
						
						<div class="form-group col-dm-2">
							<label>原产地:</label> <input class="easyui-textbox" type="text" id="place"
								name="place" style="width: 120px" />
						</div>
	
						<div class="form-group col-dm-2">
							<label>是否虚拟:</label> <select class="easyui-combobox" name="isReal" id="isReal"
								style="width: 120px">
								<option value="0" selected="selected">非虚拟</option>
								<option value="1">虚拟</option>
							</select><span class="sls-required-icon">*</span>
						</div>
	
						<div class="form-group col-dm-2">
							<label>范围限制:</label> <select class="easyui-combobox" id="limittype"
								name="limittype" style="width: 120px" data-options="required:true">
								<option value="0" selected="selected">不限制</option>
								<option value="1">省</option>
								<option value="2">市</option>
								<option value="3">区</option>
								<option value="4">商圈</option>
								<option value="5">亭子</option>
							</select><span class="sls-required-icon">*</span>
						</div>
	
						<div class="form-group col-dm-2">
							<label>供货商:</label> <select class="easyui-combobox" id="providerId"
								name="providerId" style="width: 120px" data-options="valueField:'v',textField:'k',method:'post',mode:'remote',url:'${dynUrl }/provider/getProvidersList.htm'">
							</select>
						</div>
						
						<div class="form-group col-dm-2">
							<label>库存量:</label><input class="easyui-numberbox" type="text" id="virtualNumber"
								name="virtualNumber" data-options="required:true,min:0,precision:0"
								style="width: 120px" /><span class="sls-required-icon">*</span>
						</div>
						
					</div>
					<div class="form-inline row" style="padding-top:20px">
						<div class="col-dm-12">
							<label>备注:</label> <input class="easyui-textbox" type="text" id="remark"
								name="remark" style="width: 520px" />
						</div>
					</div>
				</div>
	
				<div class="easyui-panel" title="商品详情" style="overflow: hidden;padding-left:20px">
					<h4 style="color: red;">温馨提示：上传图片时，大小不要超过500kb，图片最好是正方形，长宽大于600px</h4>
					<div>
						<button class="btn btn-primary btn-block imguploadAll" onclick="return false;">批量上传</button>
						<div  style="float:left">
							<img alt="上传主图片" src="${staUrl }/image/default.jpg" class="img-thumbnail" style="width:200px;height:200px;background-color:red"/>
							<input name="photoUrl" id="photoUrl" class="form-control picture" type="text" readonly="readonly"/>
							<button class="imgupload btn-block btn btn-primary" onclick="return false;">上传主图片</button>
						</div>
						<div  style="float:left">
							<img alt="上传附图1" src="${staUrl }/image/default.jpg" class="img-thumbnail" style="width:200px;height:200px;background-color:red"/>
							<input name="photoUrl1" id="photoUrl1" class="form-control picture" type="text" readonly="readonly"/>
							<button class="btn btn-primary imgupload btn-block" onclick="return false;">上传图片1</button>
						</div>
						<div  style="float:left">
							<img alt="上传附图2" src="${staUrl }/image/default.jpg" class="img-thumbnail" style="width:200px;height:200px;background-color:red"/>
							<input name="photoUrl2" id="photoUrl2" class="form-control picture" type="text" readonly="readonly"/>
							<button class="btn btn-primary imgupload btn-block" onclick="return false;">上传图片2</button>
						</div>
						<div  style="float:left">
							<img alt="上传附图3" src="${staUrl }/image/default.jpg" class="img-thumbnail" style="width:200px;height:200px;background-color:red"/>
							<input name="photoUrl3" id="photoUrl3" class="form-control picture" type="text" readonly="readonly"/>
							<button class="btn btn-primary imgupload btn-block" onclick="return false;">上传图片3</button>
						</div>
						<div  style="float:left">
							<img alt="上传附图4" src="${staUrl }/image/default.jpg" class="img-thumbnail" style="width:200px;height:200px;background-color:red"/>
							<input name="photoUrl4" id="photoUrl4" class="form-control picture" type="text" readonly="readonly"/>
							<button class="btn btn-primary imgupload btn-block" onclick="return false;">上传图片4</button>
						</div>
					</div>
				</div>
			</div>
		</form>
		<div class="container">
			<div class="easyui-panel" title="商品限购">
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
					<div class="form-group">
						<label>是否限购：</label> 
						<select class="easyui-combobox" id="isLimit" style="width: 120px">
							<option value="true">是</option>
							<option value="false">否</option>
						</select>
					</div>
					<div class="form-group">
						<label>限购数量：</label><input class="easyui-numberbox" style="width:120px" id="number" value="0" data-options="min:0,precision:0"/>
					</div>
				</div>
				<div>
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="add()">新增</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="update()">修改限购地址</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="del()">删除</a>
				</div>
				<div class="easyui-datagrid" id="limit_datagrid">
				</div>
			</div>
			<div class="easyui-panel" title="商品详情">
				<textarea id="descript" style="width: 100%; height: 700px; visibility: hidden;">
				</textarea>
			</div>
			<div class="easyui-panel" style="overflow: hidden">
				<div class="row">
					<div class="col-md-4">
					</div>
					<div class="col-md-4">
						<button class="btn btn-primary" id="update" onclick="javascript:return false;">修改</button>
						<button class="btn btn-primary" id="preview" onclick="javascript:return false;">预览</button>
						<button class="btn btn-primary" id="closeWindow" onclick="javascript:return false;">关闭</button>
					</div>
					<div class="col-md-4">
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		document.domain = "365020.com";
		$(document).ready(function(){
			var cur_goodsId;
			var cur_row;
			var limit_datagrid;
			var form = $("#form");
			var datagrid = $('#selectGoodsPagation').datagrid({ 
		        pageSize:framework.pageNum,
		        pageList:[20,30,40,50],
		        method: 'post',
		        border: true, 
		        url:'${dynUrl}/goods/filter.htm', 
		        pagination:true, 
		        singleSelect:true,
		        fitColumns:true,
		        rownumbers:true,
		        toolsbar:"#tb",
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
					field:"isUseCoupon",title:"优惠券",width:20,formatter:function(v){
						switch(parseInt(v)){
						case 1:return "是";
						case 0:return "否";
						}
					}
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
		        	param.goodsId = $.trim($("#sg_goodsId").val());
		        	param.sku=$.trim($("#sg_sku").val());
		        	param.name=$.trim($("#sg_name").val());
		        	param.provider=$("#sg_provider").combobox("getValue");
		        	param.price=$.trim($("#sg_price").numberbox("getValue"));
		        	param.price2=$.trim($("#sg_price2").numberbox("getValue"));
		        	param.isSale=$.trim($("#sg_isSale").combobox("getValue"));
		        },
		        onLoadSuccess: function () {
		            
		        },
		        onLoadError: function () {
		            
		        },
		        onClickCell: function (rowIndex, field, value) {
		        	
		        },
		        onDblClickRow: function (rowIndex, rowData) {
					if(rowData){
						cur_goodsId = rowData.id;
						framework.startMask();
						form.form("clear");
						$("#photoUrl").parent("div").parent("div").find("img").attr("src","${staUrl }/image/default.jpg");
						$.post(framework.dynUrl+"/goods/selectupdate.htm",{goodsId:cur_goodsId},function(d){
							if(d.success){
								var data = d.result;
								$("#oneId").combobox("setValue",data.oneId);
								if(data.oneId){
									$.post(framework.dynUrl+"/category/combobox.htm?pid="+data.oneId,{},function(d){
										$("#twoId").combobox("loadData",d);
										if(data.twoId){
											$("#twoId").combobox("setValue",data.twoId);
										}
										
									},"json");
								}
								if(data.twoId){
									$.post(framework.dynUrl+"/category/combobox.htm?pid="+data.twoId,{},function(d){
										$("#threeId").combobox("loadData",d);
										if(data.threeId){
											$("#threeId").combobox("setValue",data.threeId);
										}
									},"json");
								}
								$("#sku").text(data.sku);
								$("#goodsName").textbox("setValue",data.goodsName);
								$("#marketPrice").numberbox("setValue",data.marketPrice);
								$("#price").numberbox("setValue",data.price);
								$("#barCode").textbox("setValue",data.barCode);
								$("#brandId").combobox("setValue",data.brandId);
								$("#standard").textbox("setValue",data.standard);
								$("#place").textbox("setValue",data.place);
								$("#isReal").combobox("setValue",data.isReal);
								$("#limittype").combobox("setValue",data.limittype);
								$("#providerId").combobox("loadData",[{k:data.providerName,v:data.providerId}]);
								$("#providerId").combobox("setValue",data.providerId);
								$("#virtualNumber").numberbox("setValue",data.virtualNumber);
								$("#remark").textbox("setValue",data.remark);
								$("#photoUrl").val(data.photoUrl);
								$("#photoUrl1").val(data.photoUrl1);
								$("#photoUrl2").val(data.photoUrl2);
								$("#photoUrl3").val(data.photoUrl3);
								$("#photoUrl4").val(data.photoUrl4);
								if(data.photoUrl){
									$("#photoUrl").parent("div").find("img").attr("src",framework.imgUrl+data.photoUrl);
								}
								if(data.photoUrl1){
									$("#photoUrl1").parent("div").find("img").attr("src",framework.imgUrl+data.photoUrl1);
								}
								if(data.photoUrl2){
									$("#photoUrl2").parent("div").find("img").attr("src",framework.imgUrl+data.photoUrl2);
								}
								if(data.photoUrl3){
									$("#photoUrl3").parent("div").find("img").attr("src",framework.imgUrl+data.photoUrl3);
								}
								if(data.photoUrl4){
									$("#photoUrl4").parent("div").find("img").attr("src",framework.imgUrl+data.photoUrl4);
								}
								editor.html(data.description);
					        	$("#window").window("open");
					        	var cur_editor_row;
					        	limit_datagrid = $("#limit_datagrid").datagrid({
									fitColumns:true,
									singleSelect:true,
									url:framework.dynUrl+"/goodsarea/list.htm?goodsId="+cur_goodsId,
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
										if(cur_editor_row >= 0){
											limit_datagrid.datagrid("endEdit",cur_editor_row);
										}
										cur_row = rowIndex;
									},
									onDblClickCell:function(rowIndex,field,value){
										if(field == 'number'){
											if(cur_editor_row >= 0){
												limit_datagird.datagrid("endEdit",cur_editor_row);
											}
											limit_datagrid.datagrid("beginEdit",rowIndex);
										}
									},
									onBeforeEdit:function(rowIndex,rowData){
										cur_editor_row = rowIndex;
									},
									onAfterEdit:function(rowIndex, rowData, changes){
										if(!changes.number){
											changes.number = 0;
										}
										cur_editor_row = undefined;
									},
									columns : [ [ {
										field : "provinceId",
										title : "省",
										width:150,
										formatter:function(value){
											return framework.area_map[value];
										}
									}, {
										field : "cityId",
										title : "市",
										width:150,
										formatter:function(value){
											return framework.area_map[value];
										}
									}, {
										field : "districtId",
										title : "区",
										width:150,
										formatter:function(value){
											return framework.area_map[value];
										}
									}, {
										field : "communityId",
										title : "商圈",
										width:150,
										formatter:function(value){
											return framework.area_map[value];
										}
									}, {
										field : "pavilionId",
										title : "亭子",
										width:150,
										formatter:function(value){
											return framework.area_map[value];
										}
									}, {
										field : "isLimit",
										title : "限购",
										formatter : function(value) {
											if(value == 'false') {
												return "否";
											} else {
												return "是";
											}
										}
									}, {
										field : "number",
										title : "限购数量",
										width:150,
										editor:{type:'numberbox',options:{precision:0,min:0,required:true}}
									} ] ]
								});
							}else{
								framework.alert(d.errMsg);
							}
				        	framework.closeMask();
						},"json");
					}
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
			
			batchUp = function(){
				var checkDatas = datagrid.datagrid("getChecked");
				if(checkDatas.length > 0){
					var ids = [];
					$.each(checkDatas,function(index,data){
						ids.push(data.id);
					});
					framework.startMask();
					$.post(framework.dynUrl+"/goods/batchissale.htm",{ids:ids.join(","),isSale:1},function(d){
						if(d.success){
							datagrid.datagrid("reload");
						}else{
							framework.alert(d.errMsg);
						}
						framework.closeMask();
					},"json");
				}
			}
			
			batchDown = function(){
				var checkDatas = datagrid.datagrid("getChecked");
				if(checkDatas.length > 0){
					var ids = [];
					$.each(checkDatas,function(index,data){
						ids.push(data.id);
					});
					framework.startMask();
					$.post(framework.dynUrl+"/goods/batchissale.htm",{ids:ids.join(","),isSale:2},function(d){
						if(d.success){
							datagrid.datagrid("reload");
						}else{
							framework.alert(d.errMsg);
						}
						framework.closeMask();
					},"json");
				}
			}
			
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
			
			batchDelete = function(){
				var checkDatas = datagrid.datagrid("getChecked");
				if(checkDatas.length > 0){
					var ids = [];
					$.each(checkDatas,function(index,data){
						ids.push(data.id);
					});
					framework.startMask();
					$.post(framework.dynUrl+"/goods/batchissale.htm",{ids:ids.join(","),isSale:127},function(d){
						if(d.success){
							datagrid.datagrid("reload");
						}else{
							framework.alert(d.errMsg);
						}
						framework.closeMask();
					},"json");
				}
			}
			
			$("#selectGoodsSubmit").click(function(){
				$('#selectGoodsPagation').datagrid("reload");
			});
			
			$("#update").click(function(event){
				if(!priceCompare()){
					framework.alert("商品价格应该小于市场价");
					event.preventDefault();
					return;
				}
				if(cur_goodsId&&form.form("validate")){
					var paramArray = form.serializeArray();
					var param = {};
					$.each(paramArray,function(i,d){
						param[d.name] = d.value;
					});
					param.description = editor.html();
					param.ga = JSON.stringify(limit_datagrid.datagrid("getData").rows);
					param.goodsId = cur_goodsId;
					framework.startMask();
					$.post(framework.dynUrl+"/goods/updateall.htm",param,function(data){
						if(data.success){
							limit_datagrid.datagrid("load",{goodsId:cur_goodsId});
							framework.alert("修改成功");
						}else{
							framework.alert(data.errMsg);
						}
						framework.closeMask();
					},"json");
				}
				event.preventDefault();
			});
			
			$("#closeWindow").click(function(event){
				$("#window").window("close");
				event.preventDefault();
			});
			
			$("#preview").click(function(event){
				if(cur_goodsId){
					framework.openWindowTab(framework.detailUrl+cur_goodsId);
				}
				event.preventDefault();
			});
			add = function(){
				var i = parseInt($("#limittype").combobox("getValue"));
				if(i > 0){
					var obj = {};
					obj.isLimit = $("#isLimit").combobox("getValue");
					obj.number = $("#number").numberbox("getValue");
					var provinceId = $("#provinceId").combobox("getValue");
					var cityId = $("#cityId").combobox("getValue");
					var districtId = $("#districtId").combobox("getValue");
					var communityId = $("#communityId").combobox("getValue")
					var pavilionId = $("#pavilionId").combobox("getValue");
					var datas = limit_datagrid.datagrid("getData").rows;
					var isExist = false;
					$.each(datas,function(index,d){
						var j = 0;
						switch(i){
						case 5:if(pavilionId == d.pavilionId){j++};
						case 4:if(communityId == d.communityId){j++};
						case 3:if(districtId == d.districtId){j++};
						case 2:if(cityId == d.cityId){j++};
						case 1:if(provinceId == d.provinceId){j++};
						}
						if(i == j){
							isExist = true;
						}
					});
					if(isExist){
						framework.alert("存在相似记录");
						return;
					}
					switch(i){
						case 5:if(pavilionId == undefined||pavilionId == ''){
							framework.alert("亭子必选");
							return;
						}obj.pavilionId = pavilionId;
						case 4:if(communityId == undefined||communityId == ''){
							framework.alert("商圈必选");
							return;
						}obj.communityId = communityId;
						case 3:if(districtId == undefined||districtId == ''){
							framework.alert("小区必选");
							return;
						}obj.districtId = districtId;
						case 2:if(cityId == undefined||cityId == ''){
							framework.alert("市必选");
							return;
						}obj.cityId = cityId;
						case 1:if(provinceId == undefined||provinceId == ''){
							framework.alert("省必选");
							return;
						}obj.provinceId = provinceId;
					}
					limit_datagrid.datagrid("insertRow",{row:obj});
				}
			}
			update = function(){
				var i = parseInt($("#limittype").combobox("getValue"));
				if(i > 0&&cur_row >= 0){
					var obj = {};
					obj.isLimit = $("#isLimit").combobox("getValue");
					obj.number = $("#number").numberbox("getValue");
					var provinceId = $("#provinceId").combobox("getValue");
					var cityId = $("#cityId").combobox("getValue");
					var districtId = $("#districtId").combobox("getValue");
					var communityId = $("#communityId").combobox("getValue")
					var pavilionId = $("#pavilionId").combobox("getValue");
					var datas = limit_datagrid.datagrid("getData").rows;
					var isExist = false;
					$.each(datas,function(index,d){
						var j = 0;
						switch(i){
						case 5:if(pavilionId == d.pavilionId){j++};
						case 4:if(communityId == d.communityId){j++};
						case 3:if(districtId == d.districtId){j++};
						case 2:if(cityId == d.cityId){j++};
						case 1:if(provinceId == d.provinceId){j++};
						}
						if(i == j){
							isExist = true;
						}
					});
					if(isExist){
						framework.alert("存在相似记录");
						return;
					}
					switch(i){
						case 5:if(pavilionId == undefined||pavilionId == ''){
							framework.alert("亭子必选");
							return;
						}obj.pavilionId = pavilionId;
						case 4:if(communityId == undefined||communityId == ''){
							framework.alert("商圈必选");
							return;
						}obj.communityId = communityId;
						case 3:if(districtId == undefined||districtId == ''){
							framework.alert("小区必选");
							return;
						}obj.districtId = districtId;
						case 2:if(cityId == undefined||cityId == ''){
							framework.alert("市必选");
							return;
						}obj.cityId = cityId;
						case 1:if(provinceId == undefined||provinceId == ''){
							framework.alert("省必选");
							return;
						}obj.provinceId = provinceId;
					}
					limit_datagrid.datagrid("updateRow",{index:cur_row,row:obj});
				}
			}
			del = function(){
				if(cur_row!=undefined){
					if(cur_goodsId){
						var data = limit_datagrid.datagrid("getSelected");
						if(data.id){
							framework.startMask();
							$.post(framework.dynUrl+"/goodsarea/delete.htm",{id:data.id},function(d){
								if(d.success){
									limit_datagrid.datagrid("deleteRow",cur_row);
									cur_row = undefined;
								}else{
									framework.alert(d.errMsg);
								}
								framework.closeMask();
							},"json");
						}else{
							limit_datagrid.datagrid("deleteRow",cur_row);
							cur_row = undefined;
						}
					}else{
						limit_datagrid.datagrid("deleteRow",cur_row);
						cur_row = undefined;
					}
				}
			}
			var editor;
			var num=0;
			KindEditor.ready(function(K) {
				editor = K.create('#descript', {
					allowFileManager : true,
					autoHeightMode : true,
					afterCreate : function() {
						this.loadPlugin('autoheight');
					},
					items:[
					        'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
					        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
					        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
					        'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
					        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
					        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
					        'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
					        'anchor', 'link', 'unlink'
					],
					uploadJson:framework.detailUploadUrl+"goodsdescript&token="+framework.token,
					afterUpload : function(url,data,name) {
			            
			    	},
			    	 //监听编辑器内容发生改变的事件
			        afterChange : function() {
			            //判断是否输入了内容
			            var count = editor.html().length; //获取编辑器输入内容总数
			            //判断输入个数
			            if(count > 2000)
			            {
			            	num++;
			            	if(num%2==1){
				            	//下面的方法，让这个afterChange方法又触发了一次	
				            		framework.alert("输入字符不能超过2000个，目前输了"+count+"个字符，点击编辑器左上角，可看到输入的具体内容");
				            	num++;
			            	}
			            }
			        }
				});
			});
			
			$(".imgupload").each(function(){
				var obj = $(this);
				obj.ajaxUploadPrompt({
					url : window.framework.uploadUrl+"product&token="+framework.token+"&small=small",
					beforeSend : function () {
						window.framework.startMask();
					},
					onprogress : function (e) {
					},
					error : function (e) {
						framework.alert(e);
					},
					success : function (data) {
						try{
							data = $.parseJSON(data);
							if(data.success){
								obj.parent("div").find("img").attr("src",window.framework.imgUrl+data.result[0]);
								obj.parent("div").find("input").val(data.result[0]);
							}else{
								window.framework.alert(data.errMsg);
							}
						}catch(e){
							framework.alert(data);
						}
						framework.closeMask();
					}
				});
			});
			
			$(".imguploadAll").each(function(){
				var obj = $(this);
				obj.ajaxUploadPrompt({
					url : window.framework.uploadUrl+"product&token="+framework.token+"&small=small",
					beforeSend : function () {
						window.framework.startMask();
					},
					multiple:'multiple',
					onprogress : function (e) {
					},
					error : function (e) {
						framework.alert(e);
					},
					success : function (data) {
						try{
							data = $.parseJSON(data);
							if(data.success){
								var paths = data.result;
								var divs = obj.parent("div").find("div");
								var index = 0;
								divs.each(function(){
									var div = $(this);
									if(paths.length > index){
										var uri = paths[index];
										var url = window.framework.imgUrl+uri;
										div.find("img").attr("src",url);
										div.find("input").val(uri);
									}
									index = index + 1;
								});
							}else{
								window.framework.alert(data.errMsg);
							}
						}catch(e){
							framework.alert(data);
						}
						framework.closeMask();
					}
				});
			});
			
			function priceCompare(){
				var marketPrice = parseInt($("#marketPrice").numberbox("getValue"))||0;
				var price = parseInt($("#price").numberbox("getValue"))||0;
				if(marketPrice < price){
					return false;
				}
				return true;
			}
			
			$("#showOrhideBtn").click(function(){
				var btn = $(this);
				if(btn.text() == '▽'){
					btn.text("△");
					$("#showOrhideDiv").show();
				}else{
					btn.text("▽");
					$("#showOrhideDiv").hide();
				}
			});
			$("#flushGoodsBuyNumBtn").click(function(){
				framework.startMask();
				$.get("${dynUrl}/goods/flushbuynum.htm",function(){
					framework.closeMask();
				},"json");
			});
		});
	</script>
</body>
</html>