<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<%@include file="../commons/commons.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div>
		<p>温馨提示：右键之后，可以对功能进行增加、修改和刷新</p>
	</div>
	<div style="height: 500px;">
		<ul id="functioninit_tree" class="ztree"></ul>
	</div>

	<div id="function_menu">
	<ul>
		<li id="function_add" onclick="addTreeNode_back();">增加功能</li>
		<li id="function_update" onclick="updateTreeNode();">修改功能</li>
		<li id="function_refesh" >刷新</li>
	</ul>
	<div id="addFunctionDiv" class="easyui-window" title="增加功能" data-options="modal:true,closed:true">
			<form id="addFunctionForm" method="post" data-options="novalidate:true" action="${dynUrl }/function/add.htm">
				<table style="width:450px;height:100px">
					<tr> 
						<td>功能名:</td>
						<td><input class="easyui-textbox" type="text" name="functionName" 
							 style="width: 120px;height: 25px;" data-options="required:true,validType:'length[1,20]'"></input></td>
					</tr>
					<tr>
						<td>功能url:</td>
						<td><input class="easyui-textbox" type="text" name="url" 
							 style="width: 200px;height: 25px;" data-options="required:true,validType:'length[5,250]'"></input></td>
					</tr>
					<tr>
						<td>是否匹配创建权限（批量创建功能）:</td>
						<td><input type="checkbox" name="isImportant" value="true"/></td>
					</tr>
					<tr>
						<td>父功能:</td>
						<td><input class="easyui-textbox" type="text" readonly="readonly" name="name" ></input>
							<input type="hidden" name="fid"></td>
					</tr>
					<tr>
						<td>是否启用:</td>
						<td>
							<span><input type="radio" name="isUse" value="true" checked="checked"/>是</span>
							<span><input type="radio" name="isUse" value="false"/>否</span>
						</td>
					</tr>
					<tr>
						<td>是否重要功能:</td>
						<td>
							<span><input type="radio" name="isLog" value="true"/>是</span>
							<span><input type="radio" name="isLog" value="false" checked="checked"/>否</span>
						</td>
					</tr>
				</table>
			</form>
			<div style="text-align: center;">
			<a href="javascript:void(0)" class="btn btn-default" id="addFunction_submit">提交</a> 
			<a href="javascript:cancle_addFunction();" class="btn btn-default" id="addFunction_clear">取消</a>
		</div>
		</div>
		<div id="updateFunctionDiv" class="easyui-window" title="修改功能信息" data-options="modal:true,closed:true">
		<form id="updateFunctionForm" method="post" data-options="novalidate:true" action="${dynUrl }/function/update.htm">
			<input type="hidden" name="id"/>
			<table style="width:450px;height:100px">
				<tr>
						<td>功能名:</td>
						<td><input class="easyui-textbox" type="text" name="name" 
							data-options="required:true,validType:'length[2,100]'"></input></td>
					</tr>
					<tr>
						<td>功能url:</td>
						<td><input class="easyui-textbox" type="text" name="functionUrl"
							data-options="required:true,validType:'length[4,250]'"></input></td>
					</tr>
					<tr>
						<td>是否启用功能:</td>
						<td>
							<span><input type="radio" name="isUse" value="true" checked="checked"/>是</span>
							<span><input type="radio" name="isUse" value="false"/>否</span>
						</td>
					</tr>
					<tr>
						<td>是否重要功能:</td>
						<td>
							<span><input type="radio" name="isLog" value="true"/>是</span>
							<span><input type="radio" name="isLog" value="false" checked="checked"/>否</span>
						</td>
					</tr>
					<tr>
						<td>选择它的上一级功能:</td>
						<td>
							<input id="sel" type="text" readonly value=""
								style="width: 120px;" />
							<a id="menuBtn" href="javascript:;">选择</a>
							<div id="menuContent" class="menuContent"
								style="display: none; position: absolute;">
								<ul id="functionUpdate_tree" class="ztree" style="margin-top: 0; width: 160px;"></ul>
							</div>
						</td>
					</tr>
			</table>
		</form>
		<div style="text-align: center;">
			<a href="javascript:void(0)" class="btn btn-default" id="updateFunction_submit">提交</a> 
			<a href="javascript:cancle_updateFunction();" class="btn btn-default" id="updateFunction_clear">取消</a>
		</div>
	</div>
</div>
	<script type="text/javascript">
		var zTree, function_menu;
		(function($){
			$("#updateFunction_submit").click(function(){
				$('#updateFunctionForm').form('submit',{
					 onSubmit:function(){
						return $(this).form('enableValidation').form('validate');
					 },
					 success:function(data){
						 $('#updateFunctionForm').form('clear');
						 $("#updateFunctionDiv").window("close");
						 $('#selectFunctionPagation').datagrid("reload");
						 window.framework.dialog(data);
					 }
				 });
			});
			
			$("#updateFunction_clear").click(function(){
				 $('#updateFunctionForm').form('clear');
			});
			$("#addFunction_submit").click(function(){
				 $('#addFunctionForm').form('submit',{
					 onSubmit:function(){
						return $(this).form('enableValidation').form('validate');
					 },
					 success:function(data){
						 window.framework.dialog(data);
						 cancle_addFunction();
					 }
				 });
			});
			var setting={
				async:{
					dataType:"json",
					enable:true,
					url:window.framework.dynUrl+"/function/getnode.htm",
					type:'post',
					autoParam:['fid'],
					dataFilter:function(treeId, parentNode, responseData){
						var result = [];
						if(responseData.success){
							$.each(responseData.result,function(i,d){
								var map = {};
								map.id = "functioninit_tree_"+d.id;
								map.fid = d.id;
								map.name = d.name;
								map.isParent = !d.isLeaf;
								map.functionName = "";
								map.functionUrl = d.functionUrl;
								map.isUse = true;
								map.isLog = d.isLog;
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
			$.fn.zTree.init($("#functioninit_tree"), setting, [{id:'functioninit_tree_0',name:'功能树',isParent:true,fid:0,nocheck:true}]);
			zTree = $.fn.zTree.getZTreeObj("functioninit_tree");
			function_menu = $("#function_menu");
			$("#function_refesh").click(function() {
				hidefunction_menu();
				zTree.reAsyncChildNodes(node, "refresh");
			});
			$("#menuBtn").click(function() {
				$.fn.zTree.init($("#functionUpdate_tree"), setting, [{id:'functioninit_tree_0',name:'功能树',isParent:true,fid:0,nocheck:true}]);
			});
			
		})(jQuery);
		var node;
		function OnRightClick(event, treeId, treeNode) {
			node = treeNode;
			if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
				zTree.cancelSelectedNode();
				showfunction_menu("root", event.clientX, event.clientY);
			} else if (treeNode && !treeNode.noR) {
				zTree.selectNode(treeNode);
				showfunction_menu("node", event.clientX, event.clientY);
			}
		}
		function showfunction_menu(type, x, y) {
			$("#function_menu ul").show();
			if (type=="root") {
				$("#m_del").hide();
			} else {
				$("#m_del").show();
			}
			function_menu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});
			$("body").bind("mousedown", onBodyMouseDown);
		}
		function hidefunction_menu() {
			if (function_menu) function_menu.css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDown);
		}
		function onBodyMouseDown(event){
			if (!(event.target.id == "function_menu" || $(event.target).parents("#function_menu").length>0)) {
				function_menu.css({"visibility" : "hidden"});
			}
		}
		var addCount = 1;
		function addTreeNode_back() {                                   
			hidefunction_menu();
			$("#addFunctionForm").form("load",node);
			$("#addFunctionDiv").window("open");
		}
		function updateTreeNode() {
			hidefunction_menu();
			var map = {};
			map.id = node.fid;
			map.name = node.name;
			map.functionUrl = node.functionUrl;
			map.isUse = node.isUse;
			map.isLog = node.isLog;
			$("#updateFunctionForm").form("load",map);
            $("#updateFunctionDiv").window({title:"功能修改",closed:false,modal:true});
            $("#menuBtn").click();
		}
		function cancle_addFunction() {
			//增加页面消失
			$("#addFunctionDiv").window("close");
			$("#function_refesh").click();
		}
		function cancle_updateFunction() {
			//修改页面消失
			$("#updateFunctionDiv").window("close");
		}
	</SCRIPT>
	<style type="text/css">
 		div#function_menu {position:absolute; visibility:hidden; top:0; background-color: white; filter: alpha(opacity=100); opacity: 0.8;text-align: left;padding: 2px;}  
		div#function_menu ul li{
			margin: 1px 0;
			padding: 0 5px;
			cursor: pointer;
			list-style: none outside none;
			background-color: highlight;
		}
	</style>
</body>
</html>