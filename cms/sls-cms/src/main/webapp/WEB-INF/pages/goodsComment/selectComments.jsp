<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@include file="../commons/commons.jsp"%>
<title>check</title>
</head>
<body>
	<div class="easyui-panel" title="查询评论" style="width: 100%">
		<div style="padding: 10px 60px 20px 60px" role="form"
			class="form-inline">
			<div class="form-group">
				<label>商品id：</label>
				<input id="goodsId" class="easyui-textbox" type="text" name="goodsId" />
			</div>
			<div class="form-group">
				<label>商品名称：</label>
				<input id="goodsName" class="easyui-textbox" type="text" name="goodsName" />
			</div>
			<div class="form-group">
				<label>审核状态：</label> 
				<select id="status" class="easyui-combobox" name="status">
					<option value=""></option>
					<option value="0">未审核</option>
					<option value="1">已审核</option>
				</select>
			</div>
			<div class="form-group">
				<input type="checkbox" id="isChecked" name="isChecked" />
				<label>是否追加评论</label>
			</div>
			&nbsp;&nbsp;
				<button type="submit" class="btn btn-primary" id="selectCommentSubmit" onclick="javascript:return false">查询</button>
		</div>

		<div id="tb" style="height:auto" title="评论列表" class="easyui-panel">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="batchCheck()">批量审核</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="batchDelete()">批量删除</a>
		</div>
		<div id="commentList" style="padding: 10px 60px 20px 60px"></div>
	</div>
	

	<script>
		$(document).ready(
			function() {
				var curIndex;
				save = function(){
					if(curIndex != undefined){
						datagrid.datagrid("endEdit",curIndex);
					}
				};
				var datagrid=$('#commentList').datagrid({						
						idField : 'id',
						pageSize : framework.pageNum,
						pageList : [ 20, 30, 40, 50 ],
						method : 'post',
						border : true,
						url : '${dynUrl}/goodsComment/queryComments.htm',									
						pagination:true, 
				        singleSelect:true,
				        fitColumns:true,
				        rownumbers:true,
				        toolsbar:"#tb",
				        checkOnSelect:false,
				        selectOnCheck:false,
						
						columns : [ [
							{
								field:"check",checkbox:true
							},{
								field : "goodsName",
								title : "商品名称",
								width : 300
							},{
								field : "content",
								title : "评论",
								width : 300
							},{
								field : "grade",
								title : "评分",
								width : 80
							},{
								field : "isAnonymous",
								title : "是否匿名",
								formatter : function(value) {
									if(value == 0) {
										return "是";
									} else {
										return "否";
									}
								},
								width : 150
							},{
								field : "isVerify",
								title : "是否审核",
								formatter : function(value) {
									if(value == 0) {
										return "未审核";
									} else {
										return "已审核";
									}
								},
								width : 150
							},{
								field : "pics",
								title : "查看晒图",
								formatter : function(value) {
									if(value != null && value!="") {
										return "<button onclick='seePics(\""+value+"\")'>查看晒图</button>";
									} 
								},
								width : 200
							},{
								field : "createTime",
								title : "评论时间",
								width : 200
							},{
								field : "reply",
								title : "回复",
								editor: { type: 'validatebox', options: { required: true}},
								width : 300
							}
							] ],
							
						pagination : true,

						onBeforeLoad : function(param) {
										param.goodsId = $.trim($("#goodsId").val());										
										param.goodsName = $.trim($("#goodsName").val());										
										param.status = $.trim($("#status").combobox("getValue"));										
										param.isChecked = $.trim($("#isChecked").prop("checked"));	
									},
						
						onLoadSuccess : function() {},
						onLoadError : function() {},
						onClickCell : function(rowIndex, field, value) {
							save();
						},
						
						onBeforeEdit:function(rowIndex, rowData){
				        	curIndex = rowIndex;
				        },
				        onDblClickCell: function (rowIndex, field, value) {
				        	if(field == "reply"){
				        		if(curIndex == undefined){
					        		datagrid.datagrid("beginEdit",rowIndex);
					        	}else{
					        		datagrid.datagrid("endEdit",curIndex);
					        		datagrid.datagrid("beginEdit",rowIndex);
					        	}
				        	}
				        },
				        onAfterEdit:function(rowIndex, rowData, changes){
				        	curIndex = undefined;
				        	if(changes.reply){
				        		framework.startMask();
				        		var isChecked = $.trim($("#isChecked").prop("checked"));
				        		$.post(window.framework.dynUrl+"/goodsComment/updateReply.htm",{reply:changes.reply,id:rowData.id,isChecked:isChecked},function(data){
				        			if(data.success){
				        				
				        			}else{
				        				framework.alert(data.errMsg);
				        			}
				        			framework.closeMask();
				        		},"json");
				        	}
				        },
				        
						onDblClickRow : function(rowIndex, rowData) {},
						onClickRow : function(rowIndex,rowData) {},
						loadFilter : function(data) {
													if (!data.success) {
														framework.dialog(data);
														return [];
													} else {
														var result = {
															total : data.result.total,
															rows : data.result.entry,
														};
														return result;
													}
												}
											});
							
							
				$("#selectCommentSubmit").click(function(){					
					$('#commentList').datagrid("reload");
				});		
				
				// 获取选中的ids
				batchCheck = function(){
					var checkDatas = datagrid.datagrid("getChecked");
					if(checkDatas.length > 0){
						$.messager.confirm('','确定审核所选的评论',function(r){
							if(r){
								framework.startMask();	
								var ids = [];
								var isChecked = $.trim($("#isChecked").prop("checked"));
								$.each(checkDatas,function(index,data){
									ids.push(data.id);
								});
								$.post(framework.dynUrl+"/goodsComment/batchCheck.htm",{ids:ids.join(","),isChecked:isChecked },function(d){
									if(d.success){
										datagrid.datagrid("reload");
									}else{
										framework.alert(d.errMsg);
									}
									
								},"json");
								framework.closeMask();
							}							
						});
					}
				}
				
				// 获取选中的ids
				batchDelete = function(){
					var checkDatas = datagrid.datagrid("getChecked");
					if(checkDatas.length > 0){
						$.messager.confirm('','确定删除所选的评论',function(r){
							if(r){
								framework.startMask();	
								var ids = [];
								var isChecked = $.trim($("#isChecked").prop("checked"));
								$.each(checkDatas,function(index,data){
									ids.push(data.id);
								});
								$.post(framework.dynUrl+"/goodsComment/batchDelete.htm",{ids:ids.join(","),isChecked:isChecked },function(d){
									if(d.success){
										datagrid.datagrid("reload");
									}else{
										framework.alert(d.errMsg);
									}
									
								},"json");
								framework.closeMask();
							}							
						});
					}
				}
				
	});
		function seePics(pics){
			pics = pics.split(",");
			var imgs="<h4 align='center'>图片内容</h4>";
			imgs+="<table align='center'><tr>";
			for(var e in pics){
				var pic = window.framework.imgUrl+pics[e];
				imgs+="<td><img height=80 width=80 src='"+pic+"' />&nbsp;&nbsp;</td>";
			}
			imgs+="</tr></table>";
			framework.alert(imgs);
// 			console.log($(".panel window messager-window").attr("style"));
		}
	</script>

</body>
</html>