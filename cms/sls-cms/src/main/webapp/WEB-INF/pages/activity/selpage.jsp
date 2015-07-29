<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../commons/commons.jsp"%>
</head>
<body>
	<div id="dynpage_dg" title="动态页列表"></div>
	<div id="dyngoods_dg" title="动态页商品列表"></div>
	<div id="look_win" class="easyui-window" data-options="title:'查看',closed:true,width:300,height:400">
		<div class="form">
			<div class="form-group">
				<img src="" class="img-thumbnail" id="cur_photo" style="width:200px;height:200px">
				<button class="btn btn-primary btn-block imgupload">上传图片</button>
			</div>
		</div>
		<button class="btn btn-primary btn-block" id="default_btn">设置为默认图片</button>
		<button class="btn btn-primary btn-block" id="submit_btn">保存</button>
	</div>
<script type="text/javascript">
	document.domain = "365020.com";
	$(document).ready(function(){
		var look_win = $("#look_win");
		var curPhoto = {};
		lookphoto = function(url,id,defUrl){
			curPhoto.photoUrl = url;
			curPhoto.id = id;
			curPhoto.defUrl = defUrl;
			if(url){
				url = framework.imgUrl + url;
			}else{
				url = framework.imgUrl + "/200X200" + defUrl;
			}
			$("#cur_photo").attr("src",url);
			look_win.window("open");
		}
		$("#submit_btn").click(function(){
			var param = {id:curPhoto.id};
			if(curPhoto.photoUrl){
				param.photoUrl = curPhoto.photoUrl;
				framework.startMask();
				$.post("${dynUrl}/dynpage/updGoods.htm",param,function(d){
					framework.closeMask();
	    			if(!d.success){
	    				framework.alert(d.errMsg);
	    			}else{
	    				dyngoods_dg.datagrid("reload");	
	    			}
	    		},"json");
			}
			look_win.window("close");
		});
		$("#default_btn").click(function(){
			framework.startMask();
			$.post("${dynUrl}/dynpage/updGoods.htm",{id:curPhoto.id},function(d){
				framework.closeMask();
    			if(!d.success){
    				framework.alert(d.errMsg);
    			}
    		},"json");
			look_win.window("close");
			dyngoods_dg.datagrid("reload");
		});
		$(".imgupload").each(function(){
			var obj = $(this);
			obj.ajaxUploadPrompt({
				url : window.framework.uploadUrl+"product&token="+framework.token,
				beforeSend : function () {
					window.framework.startMask();
				},
				onprogress : function (e) {
				},
				error : function (e) {
					framework.alert(e);
				},
				success : function (data) {
					try{
						data = $.parseJSON(data);
						if(data.success){
							obj.parent("div").find("img").attr("src",window.framework.imgUrl+data.result[0]);
							curPhoto.photoUrl = data.result[0];
						}else{
							window.framework.alert(data.errMsg);
						}
					}catch(e){
						framework.alert(data);
					}
					framework.closeMask();
				}
			});
		});	
		deleteGoods = function(id){
			framework.startMask();
			$.post("${dynUrl}/dynpage/deldgg.htm",{id:id},function(d){
				framework.closeMask();
				if(d.success){
					$("#dyngoods_dg").datagrid("reload");
				}else{
					framework.alert(d.errMsg);
				}
			},"json");
		}
		save = function(rowIndex){
			dynpage_dg.datagrid("endEdit",rowIndex);
		}
		update = function(rowIndex){
			dynpage_dg.datagrid("beginEdit",rowIndex);
		}
		_cur_row = undefined;
		var dyngoods_dg = undefined;
		var dynpage_dg = $("#dynpage_dg").datagrid({
			pageSize:framework.pageNum,
	        pageList:[20,30,40,50],
	        method: 'post',
	        border: true, 
	        url:'${dynUrl}/dynpage/sel.htm', 
	        pagination:true, 
	        singleSelect:true,
	        fitColumns:true,
	        rownumbers:true,
	        columns:[[{
	        	field:"id",title:"动态页id",width:50
	        },{
	        	field:"title",title:"动态页标题",width:200,editor:{type:"text",options:{required:true}}
	        },{
	        	field:"createtime",title:"创建时间",width:200
	        },{
	        	field:"type",title:"类型",width:150,formatter:function(v){
	        		switch(parseInt(v)){
	        		case 1:return "brand二级页";
	        		case 2:return "特价商品专区";
	        		case 3:return "有机蔬菜专区";
	        		case 4:return "进口水果专区";
	        		case 5:return "意大利食品专区";
	        		case 6:return "韩国食品专区";
	        		}	
	        	}
	        },{
	        	field:"isDel",title:"状态",width:70,formatter:function(){
	        		return "正常";
	        	},editor:{type:"combobox",options:{
	        		textField:"k",
	        		valueField:"v",
	        		data:[{k:"正常",v:1},{k:"删除",v:127}]
	        	}}
	        },{
	        	field:"field",title:"操作",formatter:function(v,rowData,rowIndex){
	        		return '<button onclick="save('+rowIndex+')">保存</button><button onclick="update('+rowIndex+')">修改</button>';
	        	}
	        }]],
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
	        },
	        onAfterEdit:function(rowIndex, rowData, changes){
	        	if(changes&&(changes.isDel||changes.title)){
	        		changes.id = rowData.id;
	        		framework.startMask();
	        		$.post("${dynUrl}/dynpage/upddg.htm",changes,function(d){
	        			framework.closeMask();
	        			if(d.success){
	        				dynpage_dg.datagrid("reload");
	        			}else{
	        				framework.alert(d.errMsg);
	        			}
	        		},"json");
	        	}
	        },
	        onDblClickRow:function(rowIndex,rowData){
	        	_cur_row = undefined;
	        	dyngoods_dg = $("#dyngoods_dg").datagrid({
	        		border: true,
	        		singleSelect:true,
	    	        fitColumns:true,
	    	        rownumbers:true,
	    	        method: 'post',
	    	        pageSize:framework.pageNum,
	    	        pageList:[20,30,40,50],
	    	        pagination:true, 
	    	        url:'${dynUrl}/dynpage/goods.htm?dgId='+rowData.id, 
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
	    	        },
	    	        onBeforeLoad:function(){
	    	        	_cur_row = undefined;
	    	        },
	    	        columns:[[{
	    	        	field:"goodsName",title:"商品名称",width:200
	    	        },{
	    	        	field:"isSale",title:"状态",width:50,formatter:function(value){
	    	        		if(value==1){return '上架'}else if(value==2){return '下架'} else {return '删除'}
	    	        	}
	    	        },{
	    	        	field:"sort",title:"排序",width:50,editor:{type:"numberbox",options:{min:0,max:999999,precision:0}}
	    	        },{
	    	        	field:"price",title:"价格",width:80
	    	        },{
	    	        	field:"marketPrice",title:"市场价格",width:80
	    	        },{
	    	        	field:"photoUrl",title:"图片",width:100,formatter:function(v,rowData){
	    	        		v = v||'';
	    	        		var defUrl = rowData.defUrl||'';
	    	        		return "<button onclick='lookphoto(\""+v+"\","+rowData.id+",\""+defUrl+"\")'>查看</button>";
	    	        	}
	    	        },{
	    	        	field:"id",title:"删除",formatter:function(value){
	    	        		return '<button onclick="deleteGoods('+value+')">删除</button>';
	    	        	}
	    	        }]],
	    	        onDblClickCell:function(rowIndex, field, value){
	    	        	if(field == "sort"){
	    	        		if(_cur_row != undefined){
	    	        			dyngoods_dg.datagrid("endEdit",_cur_row);
	    	        		}else{
	    	        			dyngoods_dg.datagrid("beginEdit",rowIndex);
	    	        		}
	    	        	}
	    	        },
	    	        onBeforeEdit:function(rowIndex, rowData){
	    	        	_cur_row = rowIndex;	
	    	        },
	    	        onAfterEdit:function(rowIndex, rowData, changes){
	    	        	_cur_row = undefined;
	    	        	if(changes&&changes.sort){
	    	        		$.post("${dynUrl}/dynpage/updGoods.htm",{id:rowData.id,sort:changes.sort},function(d){
	    	        			if(!d.success){
	    	        				framework.alert(d.errMsg);
	    	        			}
	    	        		},"json");
	    	        	}
	    	        },
	    	        onClickRow:function(){
	    	        	if(_cur_row != undefined){
	    	        		dyngoods_dg.datagrid("endEdit",_cur_row);
	    	        	}
	    	        }
	        	});
	        }
		});
	});
</script>	
</body>
</html>