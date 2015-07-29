<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<%@include file="../commons/commons.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div style="padding:10px">
		<form class="form-inline" role="form" id="selectPavilionInfoForm" method="post" data-options="novalidate:true" action="${dynUrl }/pavilionInfo/selectPavilion.htm">
		  <div class="form-group">
		    <label>亭子识别号：</label>
		    <input type="text" style="width: 120px;height: 25px;"  class="form-control" id="selectPavilionInfoForm_pavilionCode" placeholder="输入亭子识别号">
		  </div>
		  <div class="form-group">
		      <label>亭子编号：</label>
		      <input class="form-control" style="width: 120px;height: 25px;"  type="text" id="selectPavilionInfoForm_pavilionSn" placeholder="输入亭子编号">
		  </div>
		  <div class="form-group">
		      <label>负责人姓名：</label>
		      <input class="form-control" style="width: 120px;height: 25px;"  type="text" id="selectPavilionInfoForm_name" placeholder="输入负责人姓名">
		  </div>
		  <div class="form-group">
		      <label>亭子电话：</label>
		      <input class="form-control" style="width: 120px;height: 25px;"  type="text" id="selectPavilionInfoForm_mobile" placeholder="输入亭子电话">
		  </div>
		  
		  <button type="submit" class="btn btn-default" id="selectPavilionInfoSubmit" onclick="javascript:return false">查询</button>
		</form>
	</div>
	<div id="selectPavilionInfoPagation">
	</div>
	<div id="pavilion_window" title="亭子信息维护" class="easyui-window" data-options="top:100,closed:true,width:400,height:400,modal:true,closable:true" >
		<form id="pavilion_form" role="form" action="${dynUrl }/pavilionInfo/addOrUpdate.htm" class="easyui-form" method="post" style="padding-left:50px">
			<input type="hidden" name="id"/>
			<div class="form-group">
				<label>区域编码：</label>
				<input type="text" name="pavilionCode" style="width: 120px;height: 25px;"  class="easyui-textbox" readonly="readonly" data-options="required:true" style="width:200px"/>
			</div>
			<div class="form-group">
				<label>亭子编号：</label>
				<input type="text" name="pavilionSn" style="width: 120px;height: 25px;"  class="easyui-textbox" data-options="required:true,validType:'length[2,20]'" style="width:200px"/>
			</div>
			<div class="form-group">
				<label>亭子电话：</label>
				<input type="text" name="mobile" style="width: 120px;height: 25px;"  class="easyui-textbox" data-options="required:true" style="width:200px"/>
			</div>
			<div class="form-group">
				<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮箱：</label>
				<input type="text" name="email" style="width: 120px;height: 25px;"  class="easyui-textbox" data-options="validType:'email'" style="width:200px"/>
			</div>
			<div class="form-group">
				<label>&nbsp;&nbsp;&nbsp;&nbsp;负责人：</label>
				<input type="text" name="name" style="width: 120px;height: 25px;"  class="easyui-textbox" data-options="required:true,validType:'length[2,10]'" style="width:200px"/>
			</div>
			<div class="form-group">
				<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;性别：</label>
				<select  name="sex" style="width:120px">
					<option value="1">男</option>
					<option value="2">女</option>
					<option value="9">其他</option>
				</select>
			</div>
			<div class="form-group">
				<label>&nbsp;&nbsp;&nbsp;&nbsp;身份证：</label>
				<input type="text" name="cartId" style="width: 120px;height: 25px;"  class="easyui-textbox" data-options="required:true" style="width:200px"/>
			</div>
			<div class="form-group">
				<label>详细地址：</label>
				<input type="text" name="detailHome" class="easyui-textbox" data-options="required:true" style="width:120px"/>
			</div>
		</form>
		<div>
			<button class="btn btn-primary btn-block" id="pavilion_form_submit" onclick="return false;">提交</button>
		</div>
	</div> 
	<script type="text/javascript">
		$(document).ready(function(){
			$('#selectPavilionInfoPagation').datagrid({ 
		        title:'亭子列表', 
		        pageSize:framework.pageNum,
		        pageList:[20,30,40,50],
		        method: 'post',
		        border: true, 
		        singleSelect:true,
		        url:'${dynUrl}/pavilionInfo/selectPavilion.htm', 
		        pagination:true, 
		        fitColumns:true,
		        rownumbers:true,
		        columns:[[{
		        	field:"pavilionCode",title:"亭子识别号",width:50
		        },{
		        	field:"pavilionSn",title:"亭子编号",width:50
		        },{
		        	field:"provinceName",title:"省",width:50
		        },{
		        	field:"cityName",title:"城市 ",width:50
		        },{
		        	field:"districtName",title:"地区 ",width:50
		        },{
		        	field:"communityName",title:"社区 ",width:50
		        },{
		        	field:"coordinate",title:"亭子坐标 ",width:50
		        },{
		        	field:"mobile",title:"亭子电话",width:50
		        },{
		        	field:"email",title:"邮箱",width:50
		        },{
		        	field:"name",title:"名字",width:50
		        },{
		        	field:"sex",title:"性别",width:50
		        },{
		        	field:"cartId",title:"身份证",width:50
		        },{
		        	field:"detailHome",title:"详细地址",width:50
		        },{
		        	field:"createTime",title:"创建时间",width:50
		        },{
		        	field:"modifyTime",title:"修改时间",width:50
		        }]],
		        onBeforeLoad: function (param) {
		        	param.pavilionCode = $.trim($("#selectPavilionInfoForm_pavilionCode").val());
		        	param.pavilionSn = $.trim($("#selectPavilionInfoForm_pavilionSn").val());
		        	param.mobile = $.trim($("#selectPavilionInfoForm_mobile").val());
		        	param.name = $.trim($("#selectPavilionInfoForm_name").val());
		        },
		        onLoadSuccess: function () {
		            
		        },
		        onLoadError: function () {
		            
		        },
		        onClickCell: function (rowIndex, field, value) {
		            
		        },
		        
		        onDblClickRow:function(rowIndex,rowData){
		        	if(rowData.id){
		        		$("#pavilion_form").form("clear");
		        		$("#pavilion_form").form("load",rowData);
			        	$("#pavilion_window").window("open");
		        	}else{
		        		framework.alert("亭子数据异常");
		        	}
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
			
			$("#selectPavilionInfoSubmit").click(function(){
				$("#selectPavilionInfoForm").form("submit",{
					onSubmit:function(){
						$('#selectPavilionInfoPagation').datagrid("reload");
						return false;
					}
				});
			});
			
			$("#pavilion_form_submit").click(function(){
				$("#pavilion_form").form("submit",{
					success:function(data){
						data = jQuery.parseJSON(data);
						if(data.success){
							$("#pavilion_window").window("close");
							$('#selectPavilionInfoPagation').datagrid("reload");
						}else{
							framework.alert(data.errMsg);
						}
					}
				});
			});
			
		});
		
	</script>
</body>
</html>