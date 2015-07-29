<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../commons/commons.jsp" %>
</head>
<body>
	<div class="easyui-panel" title="新增品牌" style="width: 100%;height:100%;">
		<div style="padding:50px 0 50px 30%">
			<form id="addbrandForm" method="post" data-options="novalidate:true" action="${dynUrl }/brand/add.htm">
				<table>
					<tr>
						<td>品牌名:</td>
						<td><input class="easyui-textbox" type="text" name="name"  style="width: 120px;height: 25px;"
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
 						<a href="javascript:void(0)" class="btn btn-default picture" id="img">上传品牌图片</a>
 						</td>
 						<td><input id="textimg" type="text" name="img" style='display:none;'/></td> 
 					</tr> 

			</table>
			<div><img id="logo" alt="" src=""></div>
			<div style="padding-left:70px">
				<a href="javascript:void(0)" class="btn btn-default" id="addbrand_submit">提交</a> 
				<a href="javascript:void(0)" class="btn btn-default" id="addbrand_clear">重置</a>
			</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		(function($){
			$('#img').ajaxUploadPrompt({
				url : window.framework.uploadUrl+"product&token="+framework.token,
				beforeSend : function () {
					framework.startMask();
				},
				onprogress : function (e) {
				},
				error : function (e) {
					console.log(e);
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
			$("#addbrand_submit").click(function(){
				$('#addbrandForm').form('submit',
					{
					 onSubmit:function(){
						 
						return $(this).form('enableValidation').form('validate');
					 },
					 success:function(data){
						 window.framework.dialog(data);
						 data = $.parseJSON(data);
						 if (data.success) {
							 var _paths = data.result.img;	
							 var $img = $("#logo");
							 var _url = window.framework.imgUrl + _paths;						
							 $img.attr("src",_url);
						 }
						
						
					 }
				 });
			});
			$("#addbrand_clear").click(function(){
				 $('#addbrandForm').form('clear');
				 var $img = $("#logo");
				 $img.attr("src","");
			});
		})(jQuery);
	</script>
</body>
</html>