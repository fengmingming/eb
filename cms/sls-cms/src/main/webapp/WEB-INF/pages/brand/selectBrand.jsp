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
		<form class="form-inline" role="form" id="selectBrandForm" method="post" data-options="novalidate:true" action="${dynUrl }/brand/brandList.htm">
		  <div class="form-group">
		    <label>品牌名：</label>
		    <input type="text" class="form-control" id="selectBrandForm_name" placeholder="输入品牌名" style="width: 120px;height: 25px;">
		  </div>
		  <div class="form-group">
		    <label>品牌网址：</label>
		    <input type="text" class="form-control" id="selectBrandForm_url" placeholder="输入网址" style="width: 120px;height: 25px;">
		  </div>
		  <button type="submit" class="btn btn-default" id="selectBrandSubmit" onclick="javascript:return false">查询</button>
		</form>
	</div>
	<div id="selectBrandPagation">
	</div> 
	<div id="updateBrandDiv" style="width:400px;height:400px;" class="easyui-window" title="修改品牌信息" data-options="modal:true,closed:true">
		<form id="updateBrandForm" method="post" data-options="novalidate:true" action="${dynUrl }/brand/update.htm" style="padding-left:30px;padding-top:30px">
				<input type="hidden" name="id"/>
				<table>
					<tr>
						<td>品牌名:</td>
						<td><input class="easyui-textbox" type="text" name="name"
							data-options="required:true,validType:'length[2,20]'"></input></td>
					</tr>

					<tr>
						<td>英文名:</td>
						<td><input class="easyui-textbox" type="text" name="enName" style="width: 120px;height: 25px;"
							data-options="required:true,validType:'length[2,20]'"></input></td>
					</tr>
					<tr>
						<td>描述:</td>
						<td><input class="easyui-textbox" type="text" name="description" style="width: 120px;height: 25px;"
						 data-options="required:true,validType:'length[2,250]'"></input></td>
					</tr>
					<tr>
						<td>拼音:</td>
						<td><input class="easyui-textbox" type="text" name="spell" style="width: 120px;height: 25px;"
						 data-options="required:true,validType:'length[2,20]'"></input></td>
					</tr>
					<tr>
						<td>关键字:</td>
						<td><input class="easyui-textbox" type="text" name="keyword" style="width: 120px;height: 25px;"
						 data-options="required:true,validType:'length[2,50]'"></input></td>
					</tr>
					
					<tr>
						<td>品牌网址:</td>
						<td><input class="easyui-textbox" type="text" name="url" style="width: 120px;height: 25px;"
						 data-options="required:true,validType:'length[2,50]'"></input></td>
					</tr>
					
					<tr> 
 						<td>品牌图片:</td> 
 						<td>
 						<a href="javascript:void(0)" class="btn btn-default picture" id="updateBrand_img">上传品牌图片</a>
 						</td>
 						<td><input id="textimg" type="text" name="img" style='display:none;'/></td> 
 					</tr>

				</table>
				<div style="padding-left:70px">
					<a href="javascript:void(0)" class="btn btn-default" id="updateBrand_submit">提交</a> 
					<a href="javascript:void(0)" class="btn btn-default" id="updateBrand_clear">重置</a>
				</div>
			</form>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$("#updateBrand_submit").click(function(){
				$('#updateBrandForm').form('submit',{
					 onSubmit:function(){
						return $(this).form('enableValidation').form('validate');
					 },
					 success:function(data){
						 $('#updateBrandForm').form('clear');
						 $("#updateBrandDiv").window("close");
						 $('#selectBrandPagation').datagrid("reload");
						 window.framework.dialog(data);
					 }
				 });
			});
			
			$('#updateBrand_img').ajaxUploadPrompt({
				url : window.framework.uploadUrl+"product&token="+framework.token,
				beforeSend : function () {
					framework.startMask();
				},
				onprogress : function (e) {
				},
				error : function (e) {
				},
				success : function (data) {
					try{ 
						data = $.parseJSON(data);
						if(data.success){
							framework.alert("品牌图片上传成功");
							$('#textimg').val(data.result[0]);
						}else{
							framework.alert(data.errMsg);
						}
					}catch(e){
						framework.alert(data);
					}
					framework.closeMask();
				}
			});
			
			$('#updateBrand_licensePhoto').ajaxUploadPrompt({
				url : window.framework.uploadUrl+"product&token="+framework.token,
				beforeSend : function () {
					framework.startMask();
				},
				onprogress : function (e) {
				},
				error : function (e) {
				},
				success : function (data) {
					try{ 
						data = $.parseJSON(data);
						if(data.success){textLicensePhoto
							framework.alert("执照图片上传成功");
							$('#textLicensePhoto').val(data.result[0]);
						}else{
							framework.alert(data.errMsg);
						}
					}catch(e){
						framework.alert(data);
					}
					framework.closeMask();
				}
			});
			
			
			$("#updateBrand_clear").click(function(){
				 $('#updateBrandForm').form('clear');
			});
			
			
			$('#selectBrandPagation').datagrid({ 
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
		        	param.name = $.trim($("#selectBrandForm_name").val());
		        	param.url = $.trim($("#selectBrandForm_url").val());
		        },
		        onLoadSuccess: function () {
		            
		        },
		        onLoadError: function () {
		            
		        },
		        onClickCell: function (rowIndex, field, value) {
		            
		        },
		        
		        onDblClickRow: function (rowIndex, rowData) {
		        	$("#updateBrandForm").form("load",rowData);
		        	$("#updateBrandDiv").window("open");
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
			
			$("#selectBrandSubmit").click(function(){
				$("#selectBrandForm").form("submit",{
					onSubmit:function(){
						$('#selectBrandPagation').datagrid("reload");
						return false;
					}
				});
			});
			
		});
		
	</script>
</body>
</html>