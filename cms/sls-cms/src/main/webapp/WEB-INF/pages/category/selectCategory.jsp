<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	div#category_menu {position:absolute; visibility:hidden; top:0; background-color: white; filter: alpha(opacity=100); opacity: 0.8;text-align: left;padding: 2px;}  
	div#category_menu ul li{
		margin: 1px 0;
		padding: 0 5px;
		cursor: pointer;
		list-style: none outside none;
		background-color: highlight;
	}
</style>
<%@include file="../commons/commons.jsp" %>
</head>
<body>
	<div>
		<p>温馨提示：右键之后，可以对品类进行增加、修改和刷新</p>
	</div>
	<div style="height: 500px;">
		<ul id="categoryinit_tree" class="ztree"></ul>
	</div>

	<div id="category_menu">
	<ul>
		<li id="category_add" onclick="addTreeNode_back();">增加品类</li>
		<li id="category_update" onclick="updateTreeNode();">修改品类</li>
		<li id="category_refesh" >刷新</li>
	</ul>
	<div id="addCategoryDiv" class="easyui-window" title="增加品类" data-options="modal:true,closed:true">
			<form id="addCategoryForm" method="post" data-options="novalidate:true" action="${dynUrl }/category/add.htm">
				<input type="hidden" name="fid" /><input type="hidden" name="type" />
				<table style="width:450px;height:100px">
					<tr> 
						<td>品类名:</td>
						<td><input class="easyui-textbox" type="text" name="categoryName" style="width: 120px;height: 25px;" data-options="required:true,validType:'length[1,20]'"></input></td>
					</tr>
					<tr> 
						<td>code:</td>
						<td><input class="easyui-textbox" type="text" readonly="readonly" name="code" style="width: 120px;height: 25px;"></input></td>
					</tr>
				</table>
			</form>
			<div style="text-align: center;">
				<a href="javascript:void(0)" class="btn btn-default btn-lg btn-block" id="addCategory_submit">提交</a> 
			</div>
		</div>
		<div id="updateCategoryDiv" class="easyui-window" title="修改品类信息" data-options="modal:true,closed:true,width:500,height:600">
		<form id="updateCategoryForm" method="post" data-options="novalidate:true" action="${dynUrl }/category/update.htm">
			<input type="hidden" name="id"/>
			<input type="hidden" name="type" id="cur_type"/>
			<table style="width:450px;height:100px">
				<tr>
					<td>品类名:</td>
					<td><input class="easyui-textbox" type="text" style="width: 120px;height: 25px;" name="name" data-options="required:true,validType:'length[2,100]'"></input></td>
				</tr>
			<!--<tr>
					<td>选择它的上一级品类:</td>
					<td>
						<input id="sel" type="text" readonly="readonly" style="width: 120px;" />
						<input  name="parentId" id="parentId" type="hidden"/>
						<a id="menuBtn" style="visibility: hidden;" href="javascript:void(0);">选择</a>
						<ul id="categoryUpdate_tree" class="ztree" style="margin-top: 0; width: 160px;"></ul>
					</td>
				</tr>-->
			</table>
		</form>
		<div style="text-align: center;">
			<a href="javascript:void(0)" class="btn btn-default" id="updateCategory_submit">提交</a> 
			<a href="javascript:cancle_updateCategory();" class="btn btn-default" id="updateCategory_clear">取消</a>
		</div>
	</div>
</div>
	<script type="text/javascript">
		var zTree, category_menu,update_zTree=null;
		(function($){
			$("#addCategory_submit").click(function(){
				 $('#addCategoryForm').form('submit',{
					 onSubmit:function(){
						return $(this).form('enableValidation').form('validate');
					 },
					 success:function(data){
						 window.framework.dialog(data);
						 cancle_addCategory();
					 }
				 });
			});
			var setting={
				async:{
					dataType:"json",
					enable:true,
					url:window.framework.dynUrl+"/category/getnode.htm",
					type:'post',
					autoParam:['fid'],
					dataFilter:function(treeId, parentNode, responseData){
						var result = [];
						if(responseData.success){
							$.each(responseData.result,function(i,d){
								var map = {};
								map.id = "categoryinit_tree_"+d.id;
								map.fid = d.id;
								map.name = d.name;
								map.isParent = !d.isLeaf;
								map.isUse = true;
								map.code=d.code;
								map.type = parentNode.type + 1;
								result.push(map);
							});
						}
						return result;
					}
				},
				callback: {
					onRightClick: OnRightClick,
					onExpand: function(event, treeId, treeNode){
					
					},
					beforeAsync:function(treeId, treeNode){
						
					},
				},
			};
			var setting2 = {
					async:{
						dataType:"json",
						enable:true,
						url:window.framework.dynUrl+"/category/getnode.htm",
						type:'post',
						autoParam:['fid'],
						dataFilter:function(treeId, parentNode, responseData){
							var result = [];
							if(responseData.success){
								$.each(responseData.result,function(i,d){
									var map = {};
									map.id = "categoryupdate_tree_"+d.id;
									map.fid = d.id;
									map.name = d.name;
									map.isParent = !d.isLeaf;
									map.isUse = true;
									map.type = parentNode.type + 1;
									result.push(map);
								});
							}
							return result;
						}
					},
					callback: {
						onClick:onClickHandler,
						onExpand: function(event, treeId, treeNode){
						
						},
						beforeAsync:function(treeId, treeNode){
							
						},
					},
				};
			$.fn.zTree.init($("#categoryinit_tree"), setting, [{id:'categoryinit_tree_0',name:'品类树',isParent:true,fid:0,nocheck:true,type:0}]);
			zTree = $.fn.zTree.getZTreeObj("categoryinit_tree");
			category_menu = $("#category_menu");
			$("#category_refesh").click(function() {
				hidecategory_menu();
				zTree.reAsyncChildNodes(node, "refresh");
			});
			$("#menuBtn").click(function() {
				$.fn.zTree.init($("#categoryUpdate_tree"), setting2, [{id:'categoryupdate_tree_0',name:'品类树',isParent:true,fid:0,nocheck:true,type:0}]);
				update_zTree = $.fn.zTree.getZTreeObj("#categoryUpdate_tree");
			});
			$("#updateCategory_submit").click(function(){
				$('#updateCategoryForm').form('submit',{
					 onSubmit:function(){
						return $(this).form('enableValidation').form('validate');
					 },
					 success:function(data){
						 $('#updateCategoryForm').form('clear');
						 $("#updateCategoryDiv").window("close");
						 $.fn.zTree.init($("#categoryinit_tree"), setting, [{id:'categoryinit_tree_0',name:'品类树',isParent:true,fid:0,nocheck:true,type:0}]);
						 zTree = $.fn.zTree.getZTreeObj("categoryinit_tree");
						 window.framework.dialog(data);
					 }
				 });
			});
			$("#updateCategory_clear").click(function(){
				 $('#updateCategoryForm').form('clear');
			});
		})(jQuery);
			
		var node;
		function OnRightClick(event, treeId, treeNode) {
			node = treeNode;
			//if(treeNode.fid == 0){
			//	return ;
			//}
			showcategory_menu(event.clientX, event.clientY);
		}
		function showcategory_menu(x, y) {
			$("#category_menu ul").show();
			category_menu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});
			$("body").bind("mousedown", onBodyMouseDown);
		}
		function hidecategory_menu() {
			if (category_menu) category_menu.css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDown);
		}
		function onBodyMouseDown(event){
			if (!(event.target.id == "category_menu" || $(event.target).parents("#category_menu").length>0)) {
				category_menu.css({"visibility" : "hidden"});
			}
		}
		var addCount = 1;
		function addTreeNode_back() {
			if(node.type >= 3){//目前只支持3级
				framework.alert("目前类目只支持到3级");
				return;
			}
			hidecategory_menu();
			var map = {};
			map.type = node.type+1;
			map.fid = node.fid;
			var codestr;
			$.ajax({
			   type: "post",
			   url: framework.dynUrl + "/category/CategoryCode.htm?pid="+node.fid,
			   cache:false,		   
			   success: function(data){
				   var result = (JSON.parse(data)).result;
				   if(map.type == 1){
					   if(result == null){
						   map.code="10";
					   }else{
						   codestr = parseInt(result.code)+1;
						   map.code=codestr;
					   }
				   }else if(map.type == 2){
					   if(result == null){
						   map.code=node.code+"001";
					   }else{
						   codestr = parseInt(result.code)+1;
						   map.code=codestr;
					   }
				   }else if(map.type == 3){
					   if(result == null){
						   map.code=node.code+"001";
					   }else{
						   codestr = parseInt(result.code)+1;
						   map.code=codestr;
					   }
				   }
				   
				   $("#addCategoryForm").form("clear");
				   $("#addCategoryForm").form("load",map);
				   $("#addCategoryDiv").window("open");
			  }
			});
		}
		function updateTreeNode() {
			hidecategory_menu();
			var map = {};
			map.id = node.fid;
			map.name = node.name;
			map.isUse = node.isUse;
			map.type = node.type;
			map.parentId = node.parentId;
			$("#updateCategoryForm").form("load",map);
            $("#updateCategoryDiv").window({title:"品类修改",closed:false,modal:true});
            $("#menuBtn").click();
		}
		function cancle_addCategory() {
			//增加页面消失
			$("#addCategoryDiv").window("close");
			$("#category_refesh").click();
		}
		function cancle_updateCategory() {
			//修改页面消失
			$("#updateCategoryDiv").window("close");
		}
		
		function onClickHandler(e, treeId, treeNode) {
			if(treeNode.type == 3){
				framework.alert("目前只支持3级类目");
				return;
			}
			$("#sel").attr("value", treeNode.name);
			$("#parentId").attr("value", treeNode.fid);
			$("#cur_type").attr("value", treeNode.type+1);
		}

		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}
	</SCRIPT>
</body>
</html>