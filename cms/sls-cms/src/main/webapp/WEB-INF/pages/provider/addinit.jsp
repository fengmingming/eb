<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
 <!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../commons/commons.jsp"%>
<style type="text/css">
img{
	width:300px;
	height:400px;
	border:solid 1px #ccc;
}
</style>

</head>
<body>
	<div id="tt" class="easyui-tabs" style="width: 100%; height: 1000px;">
		<div title="基本信息" style="padding: 20px;">		
			<div>
				<form id="addProviderForm" method="post" action="${dynUrl }/provider/add.htm">
					<table>
						<tr>
							<td>供应商:</td>
							<td><input class="easyui-textbox" type="text"
								name="providerName" style="width: 120px;height: 25px;" 
								data-options="required:true,validType:'length[2,11]'"></input></td>
						</tr>

						<tr>
							<td>地址:</td>
							<td><input class="easyui-textbox" type="text" name="address" style="width: 120px;height: 25px;" 
								data-options="required:true,validType:'length[2,50]'"></input></td>
						</tr>
						<tr>
							<td>电话:</td>
							<td><input class="easyui-textbox" type="text" name="tel" style="width: 120px;height: 25px;" 
								data-options="required:true,validType:'phoneRex'"></input></td>
						</tr>
						<tr>
							<td>传真:</td>
							<td><input class="easyui-textbox" type="text" style="width: 120px;height: 25px;"  name="fax"></input></td>
						</tr>
						<tr>
							<td>联系人:</td>
							<td><input class="easyui-textbox" type="text"
								name="contactName" style="width: 120px;height: 25px;" 
								data-options="required:true,validType:'length[2,250]'"></input></td>
						</tr>	
						<tr>
							<td>通过认证:</td>
							<td><span>&nbsp&nbsp&nbsp&nbsp</span><input id="passed" type="checkbox"	name="isVerify" value="0"></input></td>
						</tr>
						
					</table>		
					<input id="ipt_logo" type="text" name="logo" style='display:none'/>
					<input id="ipt_lic" type="text" name="licensePhoto" style='display:none'/>			
					<input id="areaCode" type="text" name="areaCode" style='display:none'/>
				</form>
				
				<div style="margin: 20px 0px; width:100%; border:solid 2px red">
					<table id="imgs">
						<tr>
							<td><img id="Logo" alt="Vendor Logo" src="${staUrl }/image/default.jpg"></td>
							<td><img id="img00" alt="00" src="${staUrl }/image/default.jpg" class="licImg"></td>
							<td><img id="img01" alt="01" src="${staUrl }/image/default.jpg" class="licImg"></td>
							<td><img id="img02" alt="02" src="${staUrl }/image/default.jpg" class="licImg"></td>
							<td><img id="img03" alt="03" src="${staUrl }/image/default.jpg" class="licImg"></td>
							<td><img id="img04" alt="04" src="${staUrl }/image/default.jpg" class="licImg"></td>
							<td><img id="img05" alt="05" src="${staUrl }/image/default.jpg" class="licImg"></td>
						</tr>
						<tr>
							<td><a href="javascript:void(0)" class="btn btn-default picture" id="logo">上传LOGO</a></td>
							<td  colspan="6"><a href="javascript:void(0)" class="btn btn-default picture" id="batchUpload">上传执照</a></td>					
						</tr>
						<tr></tr>
					</table>	
				</div>
					
					
					<div style="padding-left: 70px">
						<a href="javascript:void(0)" class="btn btn-default"
							id="addProvider_submit">提交</a> <a href="javascript:void(0)"
							class="btn btn-default" id="addProvider_clear">重置</a>
					</div>
			</div>
				
		</div>
		
		
		<!-- <div title="执照与证件" closable="true" style="overflow: auto; padding: 20px;">	</div> -->
	</div>

	
	<script type="text/javascript">
		document.domain = "cms.365020.com";
		(function($) {
			$('#logo').ajaxUploadPrompt({
				url : window.framework.uploadUrl + "logo&token="+framework.token,				
				beforeSend : function() {
					framework.startMask();
				},
				onprogress : function(e) {
				},
				error : function(e) {
					console.log(e);
				},
				success : function(data) {						
					try {
						data = $.parseJSON(data);
						if (data.success) {
							var ph = data.result[0];
							var _url = window.framework.imgUrl+ ph;						
							$('#Logo').attr("src",_url);
							$("#ipt_logo").val(ph);
							framework.alert("logo图片上传成功");
						} else {
							framework.alert(data.errMsg);
						}
					} catch (e) {						
						framework.alert("error :" +e);
					}
					framework.closeMask();
				}
			});
			

			$("#batchUpload")
				.each(
					function() {
						var obj = $(this);
						obj.ajaxUploadPrompt({
							url : window.framework.uploadUrl + "licence&token="+framework.token,
								beforeSend : function() {
									window.framework.startMask();
								},
								multiple : 'multiple',
								onprogress : function(e) {
								},
								error : function(e) {
									framework.alert("error:"+e);
								},
								success : function(data) {									
									try {
										//debugger;
										var urls = "";
										data = $.parseJSON(data);
										if (data.success) {
											var _paths = data.result;											
											var $img = $(".licImg");		
											var loop = 0;
											if(_paths.length > $img.length)
												loop = $img.length;
											else
												loop = _paths.length;
											
											for(var i = 0; i < loop; i++){
												var url = window.framework.imgUrl + _paths[i];
												urls = urls + _paths[i] + ",";
												$img.eq(i).attr("src",url);		
											}
											//debugger;											
											$("#ipt_lic").val(urls.substring(0,urls.length-1));
										}
									}catch(e){
										framework.alert(data);
									}
									framework.closeMask();
								}
							});
					});
			$("#addProvider_submit").click(function() {
				$("#areaCode").val(window.framework.areaCode);
				var form = $('#addProviderForm');
				if(form.form('validate')){
					framework.startMask();
					$.post("${dynUrl }/provider/add.htm",form.serializeObj(),function(d){
						if(d.success){
							framework.alert("新增成功");
						}else{
							framework.alert(d.errMsg);
						}
						framework.closeMask();
					},"json");
				}
			});
			$("#addProvider_clear").click(function() {				
				$('#addProviderForm').form('clear');
			});
			
			$("#passed").click(function(){				
				if(this.checked)
					$("#passed").val('1');				
			});

		})(jQuery);

		//自定义验证
		$.extend($.fn.validatebox.defaults.rules, {
			phoneRex : {
				validator : function(value) {
					var rex = /^1[3|5|7|8]+\d{9}$/;
					var rex2 = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
					if (rex.test(value) || rex2.test(value)) {
						// alert('t'+value);
						return true;
					} else {
						//alert('false '+value);
						return false;
					}

				},
				message : '请输入正确手机格式'
			}
		});
	</script>
</body>
</html>