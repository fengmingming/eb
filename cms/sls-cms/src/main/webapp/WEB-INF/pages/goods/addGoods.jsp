<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${staUrl }/resources/kindeditor/themes/default/default.css">
<%@include file="../commons/commons.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${staUrl }/resources/kindeditor/kindeditor-all.js"></script>
<script type="text/javascript" src="${staUrl }/resources/kindeditor/lang/zh_CN.js"></script>
</head>
<body>
	<form style="padding-top: 20px" id="addGoods_form">
		<div class="container">
			<div class="easyui-panel" title="商品范围选择" style="overflow: hidden;padding-left:20px">
				<div class="form-inline row">
					<div class="form-group">
						<label>可见区域：${areaName }</label>
					</div>
				</div>
			</div>
			
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
						</select>
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
							name="goodsName"
							data-options="required:true,validType:'length[1,50]'"
							style="width: 120px" /><span class="sls-required-icon">*</span>
					</div>
					<div class="form-group col-dm-2">
						<label>市场价格:</label> <input class="easyui-numberbox" type="text" id="marketPrice"
							name="marketPrice" data-options="required:true,min:0,precision:2"
							style="width: 120px" /><span class="sls-required-icon">*</span>
					</div>
					<div class="form-group col-dm-2">
						<label>商品价格:</label> <input class="easyui-numberbox" type="text" id="price"
							name="price" data-options="required:true,min:0,precision:2"
							style="width: 120px" /><span class="sls-required-icon">*</span>
					</div>
					<div class="form-group col-dm-2">
						<label>条形码:</label> <input class="easyui-textbox" type="text"
							name="barCode" style="width: 120px" />
					</div>
					<div class="form-group col-dm-2">
						<label>品牌:</label> <select class="easyui-combobox"
						 name="brandId" style="width: 120px" data-options="valueField:'v',textField:'k',url:'${dynUrl }/brand/combobox.htm',loadFilter:function(data){
								return data.result;
							}">
						</select>
					</div>
				</div>

				<div class="form-inline row" style="padding-top: 20px">
					<div class="form-group col-dm-2">
						<label>规格:</label> <input
							class="easyui-textbox" type="text" name="standard"
							data-options="validType:'length[0,20]'"
							style="width: 120px" />
					</div>
					
					<div class="form-group col-dm-2">
						<label>原产地:</label> <input class="easyui-textbox" type="text"
							name="place" style="width: 120px" />
					</div>

					<div class="form-group col-dm-2">
						<label>是否虚拟:</label> <select class="easyui-combobox" name="isReal"
							style="width: 120px" data-options="required:true">
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
						<label>供货商:</label> <select class="easyui-combobox"
							name="providerId" style="width: 120px" data-options="required:true,valueField:'v',textField:'k',method:'post',mode:'remote',url:'${dynUrl }/provider/getProvidersList.htm'">
						</select><span class="sls-required-icon">*</span>
					</div>
					
					<div class="form-group col-dm-2">
						<label>库存量:</label><input class="easyui-numberbox" type="text"
							name="virtualNumber" data-options="required:true,min:0,precision:0"
							style="width: 120px" /><span class="sls-required-icon">*</span>
					</div>
					
				</div>
				<div class="form-inline row" style="padding-top:20px">
					<div class="col-dm-12">
						<label>备注:</label> <input class="easyui-textbox" type="text"
							name="remark" style="width: 520px" />
					</div>
				</div>
			</div>

			<div class="easyui-panel" title="商品详情" style="overflow: hidden;padding-left:20px">
				<h4 style="color: red;">温馨提示：上传图片时，大小不要超过500kb，图片最好是正方形，长宽大于600px</h4>
				<div id="picture_div">
					<button class="btn btn-primary btn-block imguploadAll" onclick="return false;">批量上传</button>
					<div  style="float:left">
						<img alt="上传主图片" src="${staUrl }/image/default.jpg" class="img-thumbnail" style="width:200px;height:200px;background-color:red"/>
						<input name="photoUrl" class="form-control" type="text" readonly="readonly"/>
						<button class="imgupload btn-block btn btn-primary" onclick="return false;">上传主图片</button>
					</div>
					<div  style="float:left">
						<img alt="上传附图1" src="${staUrl }/image/default.jpg" class="img-thumbnail" style="width:200px;height:200px;background-color:red"/>
						<input name="photoUrl1" class="form-control" type="text" readonly="readonly"/>
						<button class="btn btn-primary imgupload btn-block" onclick="return false;">上传图片1</button>
					</div>
					<div  style="float:left">
						<img alt="上传附图2" src="${staUrl }/image/default.jpg" class="img-thumbnail" style="width:200px;height:200px;background-color:red"/>
						<input name="photoUrl2" class="form-control" type="text" readonly="readonly"/>
						<button class="btn btn-primary imgupload btn-block" onclick="return false;">上传图片2</button>
					</div>
					<div  style="float:left">
						<img alt="上传附图3" src="${staUrl }/image/default.jpg" class="img-thumbnail" style="width:200px;height:200px;background-color:red"/>
						<input name="photoUrl3" class="form-control" type="text" readonly="readonly"/>
						<button class="btn btn-primary imgupload btn-block" onclick="return false;">上传图片3</button>
					</div>
					<div  style="float:left">
						<img alt="上传附图4" src="${staUrl }/image/default.jpg" class="img-thumbnail" style="width:200px;height:200px;background-color:red"/>
						<input name="photoUrl4" class="form-control" type="text" readonly="readonly"/>
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
					<label>限购数量：</label><input class="easyui-numberbox" style="width:100px" id="number" value="0" data-options="min:0,precision:0"/>
				</div>
				<div class="form-group">
					<button class="btn btn-primary" id="add_button" onclick="return false;">新增</button>
					<button class="btn btn-primary" id="delete_button" onclick="return false;">删除</button>
				</div>
			</div>
			<div class="easyui-datagrid" id="limit_datagrid">
			</div>
		</div>
		<div class="easyui-panel" style="overflow: hidden">
			
		</div>
		<div class="easyui-panel" title="商品详情">
			<div class="row" style="position: fixed;width: 100%; ">
				<br><br><br><br><br><br>
				<div class="col-md-4">
				</div>
				<div class="col-md-4">
					<button class="btn btn-primary" id="submit" onclick="javascript:return false;">保存</button>
					<button class="btn btn-primary" style="display:none" id="update" onclick="javascript:return false;">修改</button>
					<button class="btn btn-primary" style="display:none" id="putaway" onclick="javascript:return false;">上架</button>
					<button class="btn btn-primary" id="preview" onclick="javascript:return false;">预览</button>
					<button class="btn btn-primary" id="flush" onclick="javascript:return false;">刷新</button>
				</div>
				<div class="col-md-4">
				</div>
			</div>
			<textarea id="descript" style="width: 100%; height: 700px; visibility: hidden;">
			</textarea>
		</div>
		
	</div>
	<script type="text/javascript">
		document.domain = "365020.com";
		(function($) {
			$(document).ready(function() {
				var cur_row;
				var cur_goodsId;
				var form = $('#addGoods_form');
				var limit_datagrid = $("#limit_datagrid").datagrid({
					fitColumns:true,
					singleSelect:true,
					url:framework.dynUrl+"/goodsarea/list.htm",
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
							if(value == "false") {
								return "否";
							} else {
								return "是";
							}
						}
					}, {
						field : "number",
						title : "限购数量",
						width:150
					} ] ]
					
				});
				$("#update").click(function(event){
					if(!priceCompare()){
						framework.alert("商品价格应该小于市场价");
						event.preventDefault();
						return;
					}
					var length = editor.html().length;
					if(length>=2000){
						framework.alert("输入字符不能超过2000个");
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
				$("#putaway").click(function(event){
					if(cur_goodsId){
						framework.startMask();
						$.post(framework.dynUrl+"/goods/update.htm",{id:cur_goodsId,isSale:1},function(d){
							if(d.success){
								framework.alert("上架成功");
							}else{
								framework.alert(d.errMsg);
							}
							framework.closeMask();
						},"json");
					}
					event.preventDefault();
				});
				$("#preview").click(function(event){
					if(cur_goodsId){
						framework.openWindowTab(framework.detailUrl+cur_goodsId);
					}else{
						framework.alert("先保存在预览");
					}
					event.preventDefault();
				});
				$("#flush").click(function(event){
					form.form("clear");
					cur_goodsId = undefined;
					cur_row = undefined;
					editor.html("");
					$("#sku").text("");
					limit_datagrid.datagrid("loadData",{total:0,rows:[]});
					$("#update").hide();
					$("#submit").show();
					$("#putaway").hide();
					$("#picture_div").find("img").attr("src","${staUrl }/image/default.jpg");
					event.preventDefault();
				});
				$("#add_button").click(function(event){
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
					event.preventDefault();
				});
				$("#delete_button").click(function(event){
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
					event.preventDefault();
				});
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
						        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
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
				
				$("#submit").click(function() {
					if(!priceCompare()){
						framework.alert("商品价格应该小于市场价");
						event.preventDefault();
						return;
					}
					var length = editor.html().length;
					if(length>=2000){
						framework.alert("输入字符不能超过2000个");
						event.preventDefault();
						return;
					}
					if(form.form("validate")){
						var paramArray = form.serializeArray();
						var param = {};
						$.each(paramArray,function(i,d){
							param[d.name] = d.value;
						});
						param.description = editor.html();
						param.isSale = 127;
						param.ga = JSON.stringify(limit_datagrid.datagrid("getData").rows);
						framework.startMask();
						$.post(framework.dynUrl+"/goods/add.htm",param,function(data){
							if(data.success){
								$("#update").show();
								$("#submit").hide();
								$("#putaway").show();
								$("#sku").text(data.result.sku)
								cur_goodsId = data.result.id;
								limit_datagrid.datagrid("load",{goodsId:cur_goodsId});
								framework.alert("保存成功");
							}else{
								framework.alert(data.errMsg);
							}
							framework.closeMask();
						},"json");
					}
				});
				
				function priceCompare(){
					var marketPrice = parseInt($("#marketPrice").numberbox("getValue"))||0;
					var price = parseInt($("#price").numberbox("getValue"))||0;
					if(marketPrice < price){
						return false;
					}
					return true;
				}
				
//	 			一级失去焦点时，如果内容为空，那么二级为空。后面的同理
				$("#oneId").next().children('input').blur(function(){
					$("#twoId").next().children('input').val('');
					$("div[id^='_easyui_combobox_i2']").empty();
				});
				$("#twoId").next().children('input').blur(function(){
					$("#threeId").next().children('input').val('');
					$("div[id^='_easyui_combobox_i3']").empty();
				});
			});})(jQuery);
		
	</script>
</body>
</html>