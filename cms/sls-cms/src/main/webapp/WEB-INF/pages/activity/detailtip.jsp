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
	<div>
		<div role="form" class="form-inline">
			<div class="form-group">
				<label>范围：</label>
				<select id="type" class="easyui-combobox" style="width: 120px">
					<option value="1">单品</option>
					<option value="2">品牌</option>
					<option value="3">供货商</option>
					<option value="4">品类</option>
				</select>
			</div>
			<div class="form-group">
				<label>开始时间：</label>
				<input id="startdate" class="easyui-datetimebox" data-options="required:true" />
			</div>
			<div class="form-group">
				<label>结束时间：</label>
				<input id="enddate" class="easyui-datetimebox" data-options="required:true"/>
			</div>
			<button class="btn btn-primary" onclick="submit()">提交</button>
			<button class="btn btn-primary" onclick="flush()">刷新数据</button>
		</div>
		<div>
			<textarea id="remark" style="width: 100%; height: 200px; visibility: hidden;"></textarea>
		</div>
	</div>
	<div id="detailTip_link">
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="captureData()">抓取数据</a>
	</div>
	<div id="detailTip_dg">
	</div>
	<div id="goods">
		<div class="form-inline" role="form">
		  <div class="form-group">
		    <label>商品id：</label>
		    <input type="text" class="form-control" id="g_goodsId" placeholder="商品id" style="width: 120px;height: 25px;">
		  </div>
		  <div class="form-group">
		      <label>商品货号：</label>
		      <input class="form-control" type="text" id="g_sku" placeholder="商品货号" style="width: 120px;height: 25px;">
		  </div>
		   <div class="form-group">
		      <label>商品名称：</label>
		      <input class="form-control" type="text" id="g_name" placeholder="商品名称" style="width: 120px;height: 25px;">
		  </div>
		   <div class="form-group">
		      <label>供货商：</label>
		      <select class="easyui-combobox" id="g_provider" style="width:120px" data-options="valueField:'v',textField:'k',url:'${dynUrl }/provider/getProvidersList.htm',mode:'remote',method:'post'">
		      </select>
		  </div>
		  <div class="form-group">
		      <label>价格区间：</label>
			  <input class="easyui-numberbox" id="g_price" style="width:50px" data-options="min:0,precision:2"/>-<input class="easyui-numberbox" id="g_price2" style="width:50px" data-options="min:0,precision:2"/>		     
		  </div>
		  <button type="submit" class="btn btn-default" onclick="goodsSubmit()">查询</button>
		</div>
		<div id="goods_datagrid"></div>
	</div>
	<div id="provider" style="display:none">
		<div class="form-inline" role="form">
		  <div class="form-group">
		    <label>供应商：</label>
		    <input type="text" class="form-control" id="p_providerName" placeholder="输入供应商名字">
		  </div>
		  <div class="form-group">
		    <label>地址：</label>
		    <input type="text" class="form-control" id="p_address" placeholder="输入地址">
		  </div>
		  <div class="form-group">
		    <label>电话：</label>
		    <input type="text" class="form-control" id="p_tel" placeholder="输入联系方式">
		  </div>
		  <div class="form-group">
		    <label>传真：</label>
		    <input type="text" class="form-control" id="p_fax" placeholder="输入传真号">
		  </div>
		  <div class="form-group">
		    <label>联系人：</label>
		    <input type="text" class="form-control" id="p_contactName" placeholder="输入联系人名字">
		  </div>
		  <button type="submit" class="btn btn-default" onclick="providerSubmit()">查询</button>
		</div>
		<div id="provider_datagrid">
		</div>
	</div>
	<div id="brand" style="display:none">
		<div class="form-inline" role="form">
		  <div class="form-group">
		    <label>品牌名：</label>
		    <input type="text" class="form-control" id="b_name" placeholder="输入品牌名">
		  </div>
		  <div class="form-group">
		    <label>品牌网址：</label>
		    <input type="text" class="form-control" id="b_url" placeholder="输入网址">
		  </div>
		  <button type="submit" class="btn btn-default" onclick="brandSubmit()">查询</button>
		</div>
		<div id="brand_datagrid">
		</div>
	</div>
	<div id="category" style="display:none">
		<div class="form-inline">
			<div class="form-group">
				<label>一级分类：</label> <select class="easyui-combobox"
					id="oneId" name="oneId"
					data-options="valueField:'v',textField:'k',url:'${dynUrl}/category/combobox.htm?pid=0',
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
			<div class="form-group">
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
			<div class="form-group">
				<label>三级分类：</label> <select class="easyui-combobox" name="threeId"
					id="threeId" style="width: 120px"
					data-options="valueField:'v',textField:'k',loadFilter:function(data){
						return data.result;
					}">
				</select>
			</div>
			<button class="btn btn-primary" onclick="categoryAdd()">添加</button>
		</div>
		<div id="category_datagrid" style="display:none"></div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			goodsSubmit = function(){
				goods_datagrid.datagrid("load");
			}
			brandSubmit = function(){
				brand_datagrid.datagrid("load");
			}
			providerSubmit = function(){
				provider_datagrid.datagrid("load");
			}
			categoryAdd = function(){
				var one = $("#oneId");
				var two = $("#twoId");
				var three = $("#threeId");
				var oneObj = {},twoObj={},threeObj={};
				with(one){
					oneObj.name=combobox('getText');
					oneObj.value=combobox('getValue');
				}
				with(two){
					twoObj.name=combobox('getText');
					twoObj.value=combobox('getValue');
				}
				with(three){
					threeObj.name=combobox('getText');
					threeObj.value=combobox('getValue');
				}
				var id = threeObj.value||twoObj.value||oneObj.value;
				var name = threeObj.name||twoObj.name||oneObj.name;
				if(id){
					category_datagrid.datagrid("insertRow",{row:{id:id,name:name,oneName:oneObj.name,twoName:twoObj.name,threeName:threeObj.name}});
				}
			}
			var cur_select = undefined;
			captureData = function(){
				var startdate = $("#startdate").datetimebox("getValue");
				var enddate = $("#enddate").datetimebox("getValue");
				if(!(startdate&&enddate)){
					framework.alert("请选择开始时间和结束时间");
					return;
				}
				if(cur_select == undefined){
					cur_select = $("#type").combobox("getValue");
				}
				var data;
				switch(parseInt(cur_select)||0){
				case 1:data = goods_datagrid.datagrid("getChecked");break;
				case 2:data = brand_datagrid.datagrid("getChecked");break;
				case 3:data = provider_datagrid.datagrid("getChecked");break;
				case 4:data = category_datagrid.datagrid("getChecked");break;
				default : cur_select = undefined;
				}
				if(data){
					var ds = []
					var cur_data = detailTip_dg.datagrid("getData").rows;
					$.each(data,function(index,d){
						var isAdd = true;
						$.each(cur_data,function(i,d2){
							if(d2.activityId == d.id){
								isAdd = false;
							}
						});
						if(isAdd){
							ds.push(d);
						}
					});
					if(ds){
						$.each(ds,function(index, d){
							var map = {};
							map.typeName = $("#type").combobox("getText");
							map.type = cur_select;
							map.startdate = startdate;
							map.enddate = enddate;
							map.activityName = d.goodsName||d.name||providerName;
							map.activityId = d.id;
							detailTip_dg.datagrid("insertRow",{row:map});
						});
					}
				}
			}
			submit = function(){
				var param = {};
				param.startdate = $.trim($("#startdate").datetimebox("getValue"));
				param.enddate = $.trim($("#enddate").datetimebox("getValue"));
				if(!param.startdate||!param.enddate){
					framework.alert("时间范围必填");
					return;
				}
				param.type = cur_select;
				param.remark = $.trim(editor.html());
				if(!param.remark){
					framework.alert("内容必填");
					return;
				}
				param.activityIds = "";
				var datas = detailTip_dg.datagrid("getChecked");
				if(datas.length > 0){
					$.each(datas,function(i,d){
						param.activityIds = param.activityIds + d.activityId + ",";
					});
				}else{
					framework.alert("活动数据不能为空");
					return;
				}
				framework.startMask();
				$.post(framework.dynUrl+"/detailtip/insert.htm",param,function(d){
					if(d.success){
						framework.alert("保存成功");
						flush();
					}else{
						framework.alert(d.errMsg);
					}
					framework.closeMask();
				},"json");
			}
			flush = function(){
				cur_select = undefined;
				detailTip_dg.datagrid("loadData",{rows:[],total:0});
				editor.html("");
				category_datagrid&&category_datagrid.datagrid("loadData",{rows:[],total:0});
			}
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('#remark', {
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
					        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|',
					        'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
					        'anchor', 'link', 'unlink'
					],
					uploadJson:framework.detailUploadUrl+"goodsdescript&token="+framework.token,
					afterUpload : function(url,data,name) {
			            
			    	}
				});
			});
			$("#type").combobox({
				onSelect:function(record){
					if(cur_select == undefined){
						if(record.value){
							$('#goods').hide();
							$('#brand').hide();
							$('#provider').hide();
							$('#category').hide();
							switch(parseInt(record.value)){
								case 1:$('#goods').show();goodsFun();break;
								case 2:$('#brand').show();brandFun();break;
								case 3:$('#provider').show();providerFun();break;
								case 4:$('#category').show();categoryFun();break;
							}
						}
					}else{
						$("#type").combobox("setValue",cur_select);
					}
					
				}
			}).combobox("setValue",1);
			var detailTip_dg = $("#detailTip_dg").datagrid({
				title:"活动详情",
				singleSelect:true,
				toolbar:"#detailTip_link",
		        fitColumns:true,
		        rownumbers:true,
		        checkOnSelect:false,
		        selectOnCheck:false,
				columns:[[{
					field:"check",checkbox:true,width:50
				},{
					field:"typeName",title:"类型",width:50
				},{
					field:"startdate",title:"开始时间",width:100
				},{
					field:"enddate",title:"结束时间",width:100
				},{
					field:"activityName",title:"名称",width:100
				}]]
			});
			var goods_datagrid;
			function goodsFun(){
				goods_datagrid = $('#goods_datagrid').datagrid({ 
					title:"商品列表",
			        pageSize:framework.pageNum,
			        pageList:[20,30,40,50],
			        method: 'post',
			        border: true, 
			        url:'${dynUrl}/goods/filter.htm', 
			        pagination:true, 
			        singleSelect:true,
			        fitColumns:true,
			        rownumbers:true,
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
			        	param.goodsId = $.trim($("#g_goodsId").val());
			        	param.sku=$.trim($("#g_sku").val());
			        	param.name=$.trim($("#g_name").val());
			        	param.provider=$("#g_provider").combobox("getValue");
			        	param.price=$.trim($("#g_price").val());
			        	param.price2=$.trim($("#g_price2").val());
			        },
			        onLoadSuccess: function () {
			            
			        },
			        onLoadError: function () {
			            
			        },
			        onClickCell: function (rowIndex, field, value) {
			        	
			        },
			        onDblClickRow: function (rowIndex, rowData) {
			        	
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
			}
			
			goodsFun();
			
			var brand_datagrid;
			function brandFun(){
				brand_datagrid = $('#brand_datagrid').datagrid({ 
			        title:'品牌列表', 
			        pageSize:framework.pageNum,
			        pageList:[20,30,40,50],
			        method: 'post',
			        border: true, 
			        url:'${dynUrl}/brand/brandList.htm', 
			        pagination:true, 
			        fitColumns:true,
			        rownumbers:true,
			        columns:[[{
			        	field:"check",checkbox:true
			        },{
			        	field:"name",title:"品牌名",width:200
			        },{
			        	field:"enName",title:"英文名",width:300
			        },{
			        	field:"description",title:"描述",width:150
			        },{
			        	field:"spell",title:"拼音",width:150
			        },{
			        	field:"keyword",title:"关键字",width:100
			        },{
			        	field:"url",title:"品牌网址",width:150
			        },{
						title:"品牌图片",
						width:"100px",
						field:"img",
						formatter:function(value,rowData,rowIndex){
							if(rowData.id > 0){
								imgUrl = window.framework.imgUrl;
								return "<a href='"+imgUrl+rowData.img+"' target='_blank'>查询品牌图片</a>";
							}
							return "";
						}
					}
			        ]],
			        onBeforeLoad: function (param) {
			        	param.name = $.trim($("#b_name").val());
			        	param.url = $.trim($("#b_url").val());
			        },
			        onLoadSuccess: function () {
			            
			        },
			        onLoadError: function () {
			            
			        },
			        onClickCell: function (rowIndex, field, value) {
			            
			        },
			        
			        onDblClickRow: function (rowIndex, rowData) {
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
			}
			
			var provider_datagrid; 
			function providerFun(){
				provider_datagrid = $('#provider_datagrid').datagrid({ 
			        title:'供应商列表', 
			        pageSize:framework.pageNum,
			        pageList:[20,30,40,50],
			        method: 'post',
			        singleSelect:true,
			        border: true, 
			        url:'${dynUrl}/provider/infos.htm', 
			        pagination:true, 
			        fitColumns:true,
			        rownumbers:true,
			        columns:[[{
			        	field:"check",checkbox:true
			        },{
			        	field:"providerName",title:"供应商",width:200
			        },{
			        	field:"address",title:"地址",width:300
			        },{
			        	field:"tel",title:"电话",width:150
			        },{
			        	field:"fax",title:"传真",width:150
			        },{
			        	field:"contactName",title:"联系人",width:100
			        },{
			        	field:"isVerify",title:"是否认证",width:50,formatter:function(value){if(value)return '是';return '否'}
			        },{
			        	field:"createTime",title:"创建时间",width:200
			        },{
			        	field:"verifyTime",title:"认证时间",width:200
			        },{
			        	field:"modifyTime",title:"修改时间",width:200
			        },{
						title:"许可证管理",
						width:"100px",
						field:"licensePhoto",
						formatter:function(value,rowData,rowIndex){
							if(rowData.id > 0){
								imgUrl = window.framework.imgUrl;
								return "<a href='"+imgUrl+rowData.licensePhoto+"' target='_blank'>供应商执照</a>";
							}
							return "";
						}
					},{
						title:"LOGO管理",
						width:"100px",
						field:"logo",
						formatter:function(value,rowData,rowIndex){
							if(rowData.id > 0){
								imgUrl = window.framework.imgUrl;
								return "<a href='"+imgUrl+rowData.logo+"' target='_blank'>查询LOGO</a>";
							}
							return "";
						}
					}
			        ]],
			        onBeforeLoad: function (param) {
			        	param.providerName = $.trim($("#p_providerName").val());
			        	param.address = $.trim($("#p_address").val());
			        	param.tel = $.trim($("#p_tel").val());
			        	param.fax = $.trim($("#p_fax").val());
			        	param.contactName = $.trim($("#p_contactName").val());
			        },
			        onLoadSuccess: function () {
			            
			        },
			        onLoadError: function () {
			            
			        },
			        onClickCell: function (rowIndex, field, value) {
			            
			        },
			        
			        onDblClickRow: function (rowIndex, rowData) {
			        	
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
			}
			
			var category_datagrid;
			function categoryFun(){
				category_datagrid = $("#category_datagrid").datagrid({
						title:"品类列表",
						columns:[[{
							field:"check",checkbox:true,width:50
						},{
							field:"oneName",width:100,title:"一级分类"
						},{
							field:"twoName",width:100,title:"二级分类"
						},{
							field:"threeName",width:100,title:"三级分类"
						}]],
						fitColumns:true,
				        rownumbers:true
					});
			}
		});
	</script>
</body>
</html>