<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../commons/commons.jsp" %>
<script type="text/javascript" src="${staUrl }/resources/highcharts/highcharts.js"></script>
</head>
<body>

<form class="form-inline" role="form" id="selectUserForm" method="post" data-options="novalidate:true" action="${dynUrl}/member/memberSearch.htm">
	  <div class="form-group">
	   	<select class="easyui-combobox" id="memberType" style="width: 120px">
	   		<option value="" selected="selected">请选择</option>
	   		<option value="1">普通用户</option>
	   		<option value="2">服务亭</option>
	   	</select>
	  </div>
	  <div class="form-group">
	    <label>会员姓名：</label>
	    <input type="text" style="width: 120px;height: 25px;"  class="form-control" id="member" placeholder="输入用户真实姓名">
	  </div>
	  <div class="form-group">
	      <label>电话：</label>
	      <input class="form-control" style="width: 120px;height: 25px;"  type="text" id="mobile" placeholder="输入用户手机号">
	  </div>
	  <div class="form-group">
	      <label>注册时间：</label>
	      <input class="easyui-datebox" id="startDate"/>到
	      <input class="easyui-datebox" id="endDate"/>
	  </div>
	  <div class="form-group">
	      <label>亭子：</label>
	      <select id="pavilionId_query" class="easyui-combobox" data-options="textField:'k',valueField:'v',mode:'remote',url:'${dynUrl }/order/pavilion.htm',loadFilter:function(data){data.result.push({k:'其他',v:'0'});return data.result}" style="width:120px">
		  </select>
	  </div>
	  <button type="submit" class="btn btn-default" id="selectUserSubmit" onclick="javascript:return false">查询</button>
	</form>
	
	<div id="tb" class="easyui-panel" title='会员用户列表'>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="addUser()">新增亭子用户</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="deletemobile()">更改用户手机号</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updatepass()">修改密码</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updatepaypass()">修改支付密码</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="useraccount()">账户操作记录</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="financeReChange()">账户充值</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="excel()">导出excel</a>
	</div>
	<div id="selectUserPagation">
	</div>
	<div class="easyui-window" data-options="closed:true,modal:true,width:500,height:600" title="客户信息录入" id="addUserWindow">
		<form id="addUserForm" method="post" action="${dynUrl }/member/addmember.htm" role="form" style="padding:20px 0px 0px 100px" style="width:350px">
			<div class="form-group">
				<label style="width:70px">用户名：</label>
				<input class="easyui-textbox" id="userName" name="userName" data-options="required:true" style="width:200px"/>
			</div>
			<div class="form-group">
				<label style="width:70px">密码：</label>
				<input class="easyui-textbox" name="password" type="password" data-options="required:true,validType:'length[6,20]'" style="width:200px"/>
			</div>
			<div class="form-group">
				<label style="width:70px">手机号：</label>
				<input class="easyui-textbox" name="mobile" data-options="prompt:'请输入一个手机号码',required:true,validType:'phoneRex'" style="width:200px"/>
			</div>
			<div class="form-group">
				<label style="width:70px">性别：</label>
				<select class="easyui-combobox" name="sex" style="width: 120px">
					<option value="1">男</option>
					<option value="2">女</option>
					<option value="9">其他</option>
				</select>
			</div>
			<div class="form-group">
				<label style="width:70px">头像：</label>
				<input type="hidden" name="photo"/>
				<img alt="头像" src="" style="width:200;height:200"/>
			</div>
			<div class="form-group">
				<label style="width:70px">email：</label>
				<input class="easyui-textbox" name="email" data-options="validType:'email'" style="width:200px"/>
			</div>
			<div class="form-group">
				<label style="width:70px">昵称：</label>
				<input class="easyui-textbox" name="alias" style="width:200px"/>
			</div>
			<div class="form-group">
				<label style="width:70px">省：</label>
				<select class="easyui-combobox" name="province" data-options="required:true,valueField:'v',textField:'k',data:framework.areaFilter(1,3),
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
				<label style="width:70px">市：</label>
				<select class="easyui-combobox" id="cityId" name="city" data-options="required:true,valueField:'v',textField:'k',
								onSelect:function(record){
									$('#districtId').combobox('clear')
									var r = framework.areaFilter(record.v,9);
									if(r){
										$('#districtId').combobox('loadData',r);
									}
								}"
								style="width: 120px"></select>
			</div>
			<div class="form-group">
				<label style="width:70px">区：</label>
				<select class="easyui-combobox" id="districtId" name="district" data-options="required:true,valueField:'v',textField:'k',
								onSelect:function(record){
									$('#communityId').combobox('clear')
									var r = framework.areaFilter(record.v,12);
									if(r){
										$('#communityId').combobox('loadData',r);
									}
								}"
								style="width: 120px"></select>
			</div>
			<div class="form-group">
				<label style="width:70px">商圈：</label>
				<select class="easyui-combobox" id="communityId" name="community" data-options="required:true,valueField:'v',textField:'k',
								onSelect:function(record){
									$('#pavilionId').combobox('clear')
									var r = framework.areaFilter(record.v,15);
									if(r){
										$('#pavilionId').combobox('loadData',r);
									}
								}"
								style="width: 120px"></select>
			</div>
			<div class="form-group">
				<label style="width:70px">亭子：</label>
				<select class="easyui-combobox" id="pavilionId" name="pavilionId" data-options="required:true,valueField:'v',textField:'k'" style="width: 120px"></select>
			</div>
			<div class="form-group">
				<label style="width:70px">用户类型：</label>
				<select class="easyui-combobox" name="memberType" style="width: 120px" data-options="required:true">
					<option value="2">服务亭</option>
				</select>
			</div>
		</form>
		<center>
			<button type="button" id="addUserSubmit" class="btn btn-primary">提交</button>
			<button type="button" id="addUserReset" class="btn btn-primary">重置</button>
		</center>
	</div>
	
	<div class="easyui-window" data-options="closed:true,modal:true,width:500,height:550" title="客户信息修改" id="upUserWindow">
		<form id="upUserForm" method="post" action="${dynUrl }/member/updatemember.htm" role="form" style="padding:20px 0px 0px 100px" style="width:350px">
			<input type="hidden" name="id"/>
			<div class="form-group">
				<label style="width:70px">用户名：</label>
				<input class="easyui-textbox" name="userName" data-options="required:true" style="width:200px" readonly="readonly"/>
			</div>
			<div class="form-group">
				<label style="width:70px">手机号：</label>
				<input class="easyui-textbox" name="mobile" data-options="prompt:'请输入一个手机号码',required:true,validType:'phoneRex'" style="width:200px"/>
			</div>
			<div class="form-group">
				<label style="width:70px">性别：</label>
				<select class="easyui-combobox" name="sex" style="width: 120px">
					<option value="1">男</option>
					<option value="2">女</option>
					<option value="9">其他</option>
				</select>
			</div>
			<div class="form-group">
				<label style="width:70px">头像：</label>
				<input type="hidden" name="photo"/>
				<img alt="头像" src="" style="width:200;height:200"/>
			</div>
			<div class="form-group">
				<label style="width:70px">email：</label>
				<input class="easyui-textbox" name="email" data-options="validType:'email'" style="width:200px"/>
			</div>
			<div class="form-group">
				<label style="width:70px">昵称：</label>
				<input class="easyui-textbox" name="alias" style="width:200px"/>
			</div>
			<div class="form-group">
				<label style="width:70px">省：</label>
				<select class="easyui-combobox" name="province" id="provinceId2" data-options="required:true,valueField:'v',textField:'k',data:framework.areaFilter(1,3),
								onSelect:function(record){
									$('#cityId2').combobox('clear');
									var r = framework.areaFilter(record.v,6);
									if(r){
										$('#cityId2').combobox('loadData',r);
									}
								}"
								style="width: 120px">
							</select>
			</div>
			<div class="form-group">
				<label style="width:70px">市：</label>
				<select class="easyui-combobox" id="cityId2" name="city" data-options="required:true,valueField:'v',textField:'k',
								onSelect:function(record){
									$('#districtId2').combobox('clear')
									var r = framework.areaFilter(record.v,9);
									if(r){
										$('#districtId2').combobox('loadData',r);
									}
								}"
								style="width: 120px"></select>
			</div>
			<div class="form-group">
				<label style="width:70px">区：</label>
				<select class="easyui-combobox" id="districtId2" name="district" data-options="required:true,valueField:'v',textField:'k',
								onSelect:function(record){
									$('#communityId2').combobox('clear')
									var r = framework.areaFilter(record.v,12);
									if(r){
										$('#communityId2').combobox('loadData',r);
									}
								}"
								style="width: 120px"></select>
			</div>
			<div class="form-group">
				<label style="width:70px">商圈：</label>
				<select class="easyui-combobox" id="communityId2" name="community" data-options="required:true,valueField:'v',textField:'k',
								onSelect:function(record){
									$('#pavilionId2').combobox('clear')
									var r = framework.areaFilter(record.v,15);
									if(r){
										$('#pavilionId2').combobox('loadData',r);
									}
								}"
								style="width: 120px"></select>
			</div>
			<div class="form-group">
				<label style="width:70px">亭子：</label>
				<select class="easyui-combobox" id="pavilionId2" name="pavilionId" data-options="required:true,valueField:'v',textField:'k'" style="width: 120px"></select>
			</div>
		</form>
		<button type="button" id="upUserSubmit" class="btn btn-primary btn-block">提交</button>
	</div>
	<div id="finance_window" title="财务充值" class="easyui-window" data-options="closed:true,modal:true,width:300,height:150">
		<form role="form" id="finance_form" action="${dynUrl }/member/finance_recharge.htm" method="post">
			<input name="userId" type="hidden"/>
			<div class="form-group">
				<label>充值用户：</label>
				<input class="easyui-textbox" name="name" readonly="readonly"/>
			</div>
			<div class="form-group">
				<label>充值金额：</label>
				<input class="easyui-numberbox" data-options="required:true,precision:2,min:0" name="money"/>
			</div>
		</form>
		<button class="btn btn-primary btn-block" id="finance_btn">充值</button>
	</div>
	<div id="analy_window" title="用户分析" class="easyui-window" data-options="closed:true,modal:true,width:900,height:900">
		<div id="analy_container"></div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			excel = function(){
				var pavilionId = $("#pavilionId_query").combobox("getValue");
				var startDate = $("#startDate").datebox("getValue");
				var endDate = $("#endDate").datebox("getValue");
				if(!startDate||!endDate){
					framework.alert("时间不能为空，且不能大于一个月");
					return;
				}
				var sdate = new Date(startDate);
				var edate = new Date(endDate);
				var l = edate.getTime() - sdate.getTime();
				if(l > 1000*60*60*24*31){
					framework.alert("时间间隔不能大于一个月");
					return;
				}
				var url = "${dynUrl}/member/excel_user.htm?startDate="+startDate+"&endDate="+endDate;
				if(pavilionId){
					url = url + "&pavilionId=" + pavilionId;
				}
				framework.openWindowTab(url);
			}
			var ana_win = $("#analy_window");
			analy = function(userId){
				framework.startMask();
				$.get("${dynUrl}/analysis/user.htm",{userId:userId},function(d){
					framework.closeMask();
					if(d.success){
						var re = d.result;
						var data = [];
						$.each(re,function(i,r){
							var name = (r.pavilionName?r.pavilionName:"其他") + "：订单总数" + r.num + "|订单总金额" + r.total
							data.push({name:name,y:r.num,total:r.total,pavilionId:r.pavilionId});
						});
						if(data.length>0){
							$('#analy_container').highcharts({ 
								chart: { 
									type: 'pie', options3d: { 
										enabled: true, alpha: 45, beta: 0 
									},
									width:700,
									height:700
								}, 
								title: { 
									text: '' 
								}, 
								tooltip: { 
									pointFormat: ''
								}, 
								series: [{ 
									type: 'pie', 
									name: '用户分析', 
									data: data
								}] 
							});
							ana_win.window("open");
						}else{
							framework.alert("用户不存在有效订单");
						}
					}else{
						framework.alert(d.errMsg);
					}
				},"json");
			};
			$("#addUserSubmit").click(function(event){
				var member = $("#userName").val();
				if($.trim(member)==""){
					framework.alert("会员名不能只输入空格");					
					return;
				}
				$("#addUserForm").form("submit",{
					onSubmit:function(){
						if($(this).form("validate")){
							framework.startMask();
							return true;
						}
						return false;
					},
					success:function(data){
						framework.closeMask();
						var data = framework.parseJSON(data);
						if(data.success){
							framework.alert("新增成功");
							$("#addUserWindow").window("close");
							$('#selectUserPagation').datagrid('reload');
						}else{
							framework.alert(data.errMsg);
						}
					}
				});
				event.stopPropagation();
			});
			$("#addUserReset").click(function(event){
				$("#addUserForm").form("reset");
				event.stopPropagation();
			});
			addUser = function(){
				$("#addUserForm").form("reset");
				$("#addUserWindow").window("open");
			};
			deletemobile = function(){
				var data = $('#selectUserPagation').datagrid("getSelected");
				if(data){
					$.messager.prompt('更改手机号', '确定更改用户“'+data.userName+'”的手机号码？', function(mobile){
						mobile = $.trim(mobile);
						if(mobile&&(isNaN(mobile)||mobile.length != 11)){
							framework.alert("手机号输入不正确");
							return;
						}
						if(mobile!=''){
							framework.startMask();
							$.post(window.framework.dynUrl+"/member/unbindmobile.htm",{userId:data.id,mobile:mobile},function(data){
								if(data.success){
									framework.alert("操作成功");
								}else{
									framework.alert(data.errMsg);
								}
								framework.closeMask();
								$('#selectUserPagation').datagrid('reload');
							},"json");
						}
					});
				}else{
					framework.alert("请选择用户项");					
				}
			};
			financeReChange = function(){
				var data = $('#selectUserPagation').datagrid("getSelected");
				if(data){
					if(data.memberType == 2){
						$("#finance_form").form("load",{userId:data.id,name:data.userName});
						$("#finance_window").window("open");
					}else{
						framework.alert("只能给亭子用户充值");
					}
				}else{
					framework.alert("请选择用户项");					
				}
			}
			$("#finance_btn").click(function(){
				$("#finance_form").form("submit",{
					onSubmit:function(){
						if($(this).form("validate")){
							framework.startMask();
							return true;
						}
						return false;
					},
					success:function(data){
						try{
							data = framework.parseJSON(data);
							if(data.success){
								framework.alert("充值成功");
							}else{
								framework.alert(data.errMsg);
							}
						}catch(e){
							framework.alert("json parse error");
						}
						$("#finance_form").form("reset");
						$("#finance_window").window("close");
						framework.closeMask();
						$('#selectUserPagation').datagrid("reload");
					}
				});
			});
			updatepass = function(){
				var data = $('#selectUserPagation').datagrid("getSelected");
				if(data){
					$.messager.prompt('修改用户密码', '请输入用户：'+data.userName+'的新密码', function(r){
						r=$.trim(r);
						if(r!=''){
							if(r.length >= 6&&r.length<=20){
								framework.startMask();
								$.post(window.framework.dynUrl+"/member/repassword.htm",{userid:data.id,repass:$.trim(r)},function(data){
									if(data.success){
										framework.alert("操作成功");
									}else{
										framework.alert(data.errMsg);
									}
									framework.closeMask();
									$('#selectUserPagation').datagrid('reload');
								},"json");
							}else{
								framework.alert("密码长度最少需要6位，最大不能超过20位");
							}
						}
					});
				}else{
					framework.alert("请选择用户项");					
				}
			};
			updatepaypass = function(){
				var data = $('#selectUserPagation').datagrid("getSelected");
				if(data){
					$.messager.confirm('','确认修改用户支付密码为手机号后六位',function(r){
						if(r){
							framework.startMask();
							$.post(window.framework.dynUrl+"/member/repaypass.htm",{userId:data.id},function(data){
								framework.closeMask();
								if(data.success){
									framework.alert("操作成功");
								}else{
									framework.alert(data.errMsg);
								}
							},"json");
						}
					});
				}else{
					framework.alert("请选择用户项");					
				}
			};
			useraccount = function(){
				var data = $('#selectUserPagation').datagrid("getSelected");
				if(data){
					framework.flushTabs(framework.dynUrl+"/member/initaccount.htm?userId="+data.id,"账户操作记录");
				}else{
					framework.alert("请选择用户项");					
				}
			};
			$('#selectUserPagation').datagrid({ 
		        pageSize:framework.pageNum,
		        method: 'post',
		        toolbar:"#tb",
		        border: true, 
		        url:'${dynUrl}/member/memberSearch.htm', 
		        pagination:true, 
		        fitColumns:true,
		        singleSelect:true,
		        rownumbers:true,
		        columns:[[{
		        	field:"userName",title:"用户名",width:100
		        },{
		        	field:"memberType",title:"会员类型",formatter:function(value){
		        		if(value == 1){
		        			return "普通用户";
		        		}
		        		if(value == 2){
		        			return "服务亭人员";
		        		}
		        	},width:100
		        },{
		        	field:"mobile",title:"手机号",width:100
		        },{
		        	field:"id",title:"用户分析",formatter:function(v,data){
		        		if(data.memberType == 1){
		        			return "<button onclick='analy("+v+")'>分析</button>";
		        		}
		        		return "";
		        	}
		        },{
		        	field:"alias",title:"昵称",width:100
		        },{
		        	field:"email",title:"email",width:100
		        },{
		        	field:"sex",title:"性别",width:50,formatter:function(value){switch(value){case 1:return "男";case 2:return "女";default: return "其他";}},width:50
		        },{
		        	field:"province",title:"省",formatter:function(value){return window.framework.area_map[value];},width:50
		        },{
		        	field:"city",title:"市",formatter:function(value){return window.framework.area_map[value];},width:50
		        },{
		        	field:"district",title:"区",formatter:function(value){return window.framework.area_map[value];},width:50
		        },{
		        	field:"community",title:"商圈",formatter:function(value){return window.framework.area_map[value];},width:50
		        },{
		        	field:"pavilionId",title:"亭子",formatter:function(value){return window.framework.area_map[value];},width:50
		        },{
		        	field:"amount",title:"用户总金额",width:50
		        },{
		        	field:"points",title:"消费积分"
		        }]],
		        onBeforeLoad: function (param) {
		        	param.username = $.trim($("#member").val());
		        	param.mobile = $.trim($("#mobile").val());
		        	param.memberType = $.trim($("#memberType").combobox("getValue"));
		        	param.pavilionId = $("#pavilionId_query").combobox("getValue");
		        	param.startDate = $("#startDate").datebox("getValue");
		        	param.endDate = $("#endDate").datebox("getValue");
		        },
		        onLoadSuccess: function (data) {
		        	
		        },
		        onLoadError: function () {
		            
		        },
		        onClickCell: function (rowIndex, field, value) {
		            
		        },
		        onDblClickRow:function(rowIndex,rowData){
	        		$("#upUserForm").form("clear");
	        		$("#upUserForm").form("load",rowData);
	        		$("#provinceId2").combobox("loadData",framework.areaFilter(1,3));
	        		$("#provinceId2").combobox("setValue",rowData.province);
	        		$("#cityId2").combobox("loadData",framework.areaFilter(rowData.province,6));
	        		$("#cityId2").combobox("setValue",rowData.city);
		        	$("#districtId2").combobox("loadData",framework.areaFilter(rowData.city,9));
		        	$("#districtId2").combobox("setValue",rowData.district);
	        		$("#communityId2").combobox("loadData",framework.areaFilter(rowData.district,12));
	        		$("#communityId2").combobox("setValue",rowData.community);
	        		$("#pavilionId2").combobox("loadData",framework.areaFilter(rowData.community,15));
	        		$("#pavilionId2").combobox("setValue",rowData.pavilionId);
	        		$("#upUserWindow").window("open");
		        },
		        loadFilter:function(data){
		        	if(!data.success){
		        		framework.dialog(data);
		        		return {};
		        	}else{
		        		var result = {
			        			total:data.result.total,
			        			rows:data.result.entry,
			        	};
			        	return result;
		        	}
		        }
		    });
			$("#upUserSubmit").click(function(){
				$("#upUserForm").form("submit",{
					onSubmit:function(){
						if($(this).form("validate")){
							framework.startMask();
							return true;
						}
						return false;
					},
					success:function(d){
						framework.closeMask();
						d = jQuery.parseJSON(d);
						if(d.success){
							$("#upUserWindow").window("close");
							$('#selectUserPagation').datagrid('reload');
						}else{
							framework.alert(d.errMsg);
						}
					}
				});
			});
			$("#selectUserSubmit").click(function(){
				$('#selectUserPagation').datagrid('reload');
			});
		});

	</script>
</body>
</html>

