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
		<form class="form-inline" role="form" id="selectProviderForm" method="post" data-options="novalidate:true" action="${dynUrl }/provider/infos.htm">
		  <div class="form-group">
		    <label>供应商：</label>
		    <input type="text"  style="width: 120px;height: 25px;" class="form-control" id="selectProviderForm_providerName" placeholder="输入供应商名字">
		  </div>
		  <div class="form-group">
		    <label>地址：</label>
		    <input type="text" style="width: 120px;height: 25px;"  class="form-control" id="selectProviderForm_address" placeholder="输入地址">
		  </div>
		  <div class="form-group">
		    <label>电话：</label>
		    <input type="text" style="width: 120px;height: 25px;"  class="form-control" id="selectProviderForm_tel" placeholder="输入联系方式">
		  </div>
		  <div class="form-group">
		    <label>传真：</label>
		    <input type="text" style="width: 120px;height: 25px;"  class="form-control" id="selectProviderForm_fax" placeholder="输入传真号">
		  </div>
		  <div class="form-group">
		    <label>联系人：</label>
		    <input type="text" style="width: 120px;height: 25px;"  class="form-control" id="selectProviderForm_contactName" placeholder="输入联系人名字">
		  </div>
		  <button type="submit" class="btn btn-default" id="selectProviderSubmit" onclick="javascript:return false">查询</button>
		</form>
	</div>
	<div id="selectProviderPagation">
	</div> 
	<div id="updateProviderDiv" style="width:100%;height:600px;" class="easyui-window" title="修改供应商信息" data-options="modal:true,closed:true">
		<form id="updateProviderForm" method="post" data-options="novalidate:true" action="${dynUrl }/provider/update.htm" style="padding-left:30px;padding-top:30px">
				<input type="hidden" name="id"/>
				<table>
					<tr>
						<td>供应商:</td>
						<td><input class="easyui-textbox" type="text" style="width: 120px;height: 25px;"  name="providerName"
							data-options="required:true,validType:'length[2,11]'"></input></td>
					</tr>

					<tr>
						<td>地址:</td>
						<td><input class="easyui-textbox" type="text" name="address"
							 style="width: 120px;height: 25px;" data-options="required:true,validType:'length[2,50]'"></input></td>
					</tr>
					<tr>
						<td>电话:</td>
						<td><input class="easyui-textbox" type="text" name="tel"
						  style="width: 120px;height: 25px;" data-options="required:true,validType:'mobile'"></input></td>
					</tr>
					<tr>
						<td>传真:</td>
						<td><input class="easyui-textbox" type="text" name="fax"
						  style="width: 120px;height: 25px;"></input></td>
					</tr>
					<tr>
						<td>联系人:</td>
						<td><input class="easyui-textbox" type="text" name="contactName"
						  style="width: 120px;height: 25px;" data-options="required:true,validType:'length[2,250]'"></input></td>
					</tr>
		
					<tr>
						<td>通过认证:</td>
						<td><span>&nbsp&nbsp&nbsp&nbsp</span><input id="chkpass" type="checkbox"></input></td>
					</tr>
				</table>
				<input id="_passed" type="text" name="isVerify" style='display:none;'/>
				<input id="textLogo" type="text" name="logo" style='display:none;'/>
				<input id="ipt_lic" type='text' name='licensePhoto' style='display:none;'/>
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
				
				<div style="padding-left:70px">
					<a href="javascript:void(0)" class="btn btn-default" id="updateProvider_submit">提交</a> 
					<a href="javascript:void(0)" class="btn btn-default" id="updateProvider_clear">重置</a>
				</div>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$("#updateProvider_submit").click(function(){
				$('#updateProviderForm').form('submit',{
					 onSubmit:function(){
						$("#areaCode").val(window.framework.areaCode);
						return $(this).form('enableValidation').form('validate');
					 },
					 success:function(data){
						 $('#updateProviderForm').form('clear');
						 $("#updateProviderDiv").window("close");
						 $('#selectProviderPagation').datagrid("reload");
						 window.framework.dialog(data);
					 }
				 });
			});
			
			$("#chkpass").click(function(){				
				if(this.checked)
					$("#_passed").val('1');	
				else
					$("#_passed").val('0');	
			});			
			
			$('#logo').ajaxUploadPrompt({
				url : window.framework.uploadUrl + "logo&token="+framework.token,
				beforeSend : function () {
					framework.startMask();
				},
				onprogress : function (e) {					
				},
				error : function (e) {
					//console.log(e);
				},
				success : function (data) {
					try{ 
						debugger;
						var urls = "";
						data = $.parseJSON(data);
						if(data.success){
							debugger;			
							var ph = data.result[0];
							var _url = window.framework.imgUrl+ ph;						
							$('#Logo').attr("src",_url);
							$("#textLogo").val(ph);
							framework.alert("上传成功!");
						}else{
							framework.alert(data.errMsg);
						}
					}catch(e){
						framework.alert(data);
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
										urls = urls.substring(0,urls.length-1);
										$("#ipt_lic").val(urls);										
									}
								}catch(e){
									framework.alert(data);
								}
								framework.closeMask();
							}
						});
				});
			
			
			$("#updateProvider_clear").click(function(){
				 $('#updateProviderForm').form('clear');
			});
			
			
			$('#selectProviderPagation').datagrid({ 
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
		        	field:"isVerify",title:"是否认证",width:50,
		        	
		        	formatter: function (value) {		        		
	                    if (value) {		                    	
	                        return '<span style="color:green">Y</span>';  
	                    }  
	                    else {  
	                        return '<span style="color:red">N</span>';  
	                    }  
	                }        
		        	
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
							//uploadUrl = window.framework.uploadUrl;
							imgUrl = window.framework.imgUrl;
							//u = uploadUrl.substring(0, uploadUrl.indexOf(/img/, 0));
							//console.log(window.framework.imgUrl);
							return "<a href='"+imgUrl+rowData.licensePhoto+"' target='_blank'>供应商执照</a>";
						}
						return "";
					}
				},{
					title:"LOGO管理",
					width:"100px",
					field:"logo",
					formatter:function(value,rowData,rowIndex){
						//console.log(rowData);
						if(rowData.id > 0){
							imgUrl = window.framework.imgUrl;
							return "<a href='"+imgUrl+rowData.logo+"' target='_blank'>查询LOGO</a>";
						}
						return "";
					}
				}
		        ]],
		        onBeforeLoad: function (param) {
		        	param.providerName = $.trim($("#selectProviderForm_providerName").val());
		        	param.address = $.trim($("#selectProviderForm_address").val());
		        	param.tel = $.trim($("#selectProviderForm_tel").val());
		        	param.fax = $.trim($("#selectProviderForm_fax").val());
		        	param.contactName = $.trim($("#selectProviderForm_contactName").val());
		        	param.areaCode = window.framework.areaCode;
		        },
		        onLoadSuccess: function () {
		            
		        },
		        onLoadError: function () {
		            
		        },
		        onClickCell: function (rowIndex, field, value) {
		            
		        },
		        
		        onDblClickRow: function (rowIndex, rowData) {
		        	$("#updateProviderForm").form("load",rowData);
		        		        	
		        	// For UI only,Note the value is set to #_passed to avoid the form serialize.
		        	if(rowData.isVerify)
		        		$('#chkpass')[0].checked = true;
		        	else
		        		$('#chkpass')[0].checked = false;
		        		        	
		        	// Set the logo and license imgs to the window
		        	var _url = window.framework.imgUrl + rowData.logo;						
					$('#Logo').attr("src",_url);
		        	
		        	var lic = rowData.licensePhoto;
		        	if(lic && lic.length>0){
		        		var imgs = rowData.licensePhoto.split(",");	
		        		var $img = $(".licImg");		
						var loop = 0;
						if(imgs.length > $img.length)
							loop = $img.length;
						else
							loop = imgs.length;
						
						for(var i = 0; i < loop; i++){
							var url = window.framework.imgUrl + imgs[i];						
							$img.eq(i).attr("src",url);		
						}			
		        	}
		        	
		        	$("#updateProviderDiv").window("open");
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
			
			$("#selectProviderSubmit").click(function(){
				$("#selectProviderForm").form("submit",{					
					onSubmit:function(){
						
						$('#selectProviderPagation').datagrid("reload");
						return false;
					}
				});
			});
		});
		
	</script>
</body>
</html>