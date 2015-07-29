
<%@ page contentType="text/html; charset=UTF-8" language="java"  isELIgnored="false" %>
<html>
<head>
    <%@include file="../commons/commons.jsp"%>
    <script type="text/javascript" src="${staUrl}/js/data/exportCSV.js"></script>
    <title></title>
</head>
<body>
<div class="easyui-panel" title="查询" style="width: 100%">
    <div style="padding: 10px 60px 20px 60px" role="form"
         class="form-inline">
        <div class="form-group">
            <label>名称：</label>
            <input id="name" class="easyui-textbox" type="text" name="name" />
        </div>
        <!--
        <div class="form-group">
            <label>门槛值：</label>
            <input id="minimum" class="easyui-textbox" type="text" name="minimum" />
        </div>
        -->
        <div class="form-group">
            <label>限制品类：</label>
            <input id="limitCat" class="easyui-textbox" type="text" name="limitCat" />
        </div>
        <div class="form-group">
            <label>限制类型：</label>
            <select id="limitType" class="easyui-combobox">
                <option value="">全部</option>
                <option value="1">注册优惠券</option>
                <option value="2">线下优惠券</option>
            </select>
        </div>
        <!--
        <div class="form-group">
            <label>开始时间：</label> <input id="validityStart"
                                        class="easyui-datebox" />
        </div>
        <div class="form-group">
            <label>结束时间：</label> <input id="validityEnd" class="easyui-datebox" />
        </div>
        -->
        <button id="qryEntity">查询</button>
    </div>

    <div id="entityList" style="padding: 10px 60px 20px 60px"></div>
    <div>
    	<label>优惠劵码：</label><input id="coupon_code"/><button onclick="query()">查询</button>
    </div>
    <div id="detailList" style="padding: 10px 60px 20px 60px"></div>
</div>

</body>
<script type="text/javascript">
	function query(){
		showDetail();
	}
    $(document).ready(function(){
        var gd=$('#entityList').datagrid({
            title : '对象列表',
            idField : 'id',
            pageSize : framework.pageNum,
            pageList : [ 20, 30, 40, 50 ],
            method : 'post',
            border : true,
            url : '${dynUrl}/coupon/query.htm',
            pagination : true,
            fitColumns : true,
            rownumbers : true,
            columns : [ [
                {
                    field : "name",
                    title : "名称",
                    width : 180
                },{
                    field : "parValue",
                    title : "面值",
                    width : 80
                },{
                    field : "minimum",
                    title : "门槛值",
                    width : 100
                },{
                    field : "total",
                    title : "发行总量",
                    width : 100
                },{
                    field : "num",
                    title : "剩余量",
                    width : 100
                },{
                    field : "limitType",
                    title : "限制类型",
                    width : 80,
                    formatter:function(value){
                        switch(parseInt(value)){
                            case 1:return "注册优惠券";
                            case 2:return "线下优惠券";
                        }
                    }
                },{
                    field : "validityStart",
                    title : "开始日期",
                    width : 150
                },{
                    field : "validityEnd",
                    title : "结束日期",
                    width : 150
                },{
                    field : "limitCatName",
                    title : "限制品类",
                    width : 80
                },{
                    field : "remark",
                    title : "备注",
                    width : 150
                },{
                    field : "genDate",
                    title : "生成日期",
                    width : 200
                },{
                    field : "startdate",
                    title : "开始领用日期",
                    width : 200
                },{
                    field : "enddate",
                    title : "领用结束日期",
                    width : 200
                },{
                    field : 'operate',
                    title : '操作',
                    width : 100,
                    align : 'left',
                    formatter : function(value,rowData,rowIndex) {
                        var qryId = rowData.id;
                        var a ='<button onclick="showDetail(\''+qryId+'\')">明细</button>&nbsp;&nbsp;';
                        return a;
                    }

                },{
                    field: 'aa',
                    title: '操作',
                    width: 60,
                    align: 'center',
                    formatter: function (value, rowData, rowIndex) {
                       return '<button onclick="delInLine(\'' + rowData.id + '\')">删除</button>'
                    }
                }, {
                    field: 'bb',
                    title: '导出',
                    width: 60,
                    align: 'center',
                    formatter: function (value, rowData, rowIndex) {
                        if(rowData.limitType == 2) // 2 means offline coupon
                        {
                            return '<button onclick="exportByPId(\'' + rowData.id + '\')">导出</button>'
                        }
                    }
                }
                ] ],

            onBeforeLoad : function(param) {
                            param.name = $.trim($("#name").val());
                            param.limitType = $.trim($.trim($("#limitType").combobox("getValue")));
                            /*
                            param.limitCat= $.trim($("#limitCat").val());
                            param.minimum = $.trim($("#minimum").val());
                            param.validityStart = $.trim($("#validityStart").datetimebox("getValue"));
                            param.validityEnd =  $.trim($("#validityEnd").datetimebox("getValue"));
                            */
                            },

            onLoadSuccess : function() {},
            onLoadError : function() {},
            onClickCell : function(rowIndex, field, value) {},

            onDblClickRow : function(rowIndex, rowData) {},
            onClickRow : function(rowIndex,rowData) {},
            loadFilter : function(data) {
                if (!data.success) {
                    framework.dialog(data);
                    return [];
                }
                else {
                    var result = {
                        total : data.result.total,
                        rows : data.result.entry
                    };
                    return result;
                }
            }
        });
        // pop up the window.

        showDetail = function(pid){
            var dd=$('#detailList').datagrid({
                title : '卡列表',
                idField : 'id',
                pageSize : framework.pageNum,
                pageList : [ 20, 30, 40, 50 ],
                method : 'post',
                border : true,
                url : '${dynUrl}/coupon/userCoupon.htm',
                pagination : true,
                fitColumns : true,
                rownumbers : true,
                columns : [ [
                    {
                        field : 'id',
                        title : 'id',
                        width : 80
                    },{
                        field : "code",
                        title : "优惠券号",
                        width : 200
                    },{
                    	field:"usrId",title:"是否领取",formatter:function(v){
                    		if(parseInt(v) == 0){
                    			return "未领取"
                    		}
                    		return "已领取";
                    	}
                    },{
                        field : "genDate",
                        title : "生成日期",
                        width : 150
                    },{
                        field : "consumptionDate",
                        title : "消费日期",
                        width : 150
                    },{
                        field : "orderNum",
                        title : "订单号",
                        width : 300
                    }
                ] ],

                pagination : true,

                onBeforeLoad : function(param) {
                	var code = $.trim($("#coupon_code").val());
                	if(code){
                		param.code = code;
                	}else{
                		param.pid = pid;
                	}
                },

                onLoadSuccess : function() {},
                onLoadError : function() {},
                onClickCell : function(rowIndex, field, value) {},

                onDblClickRow : function(rowIndex, rowData) {},
                onClickRow : function(rowIndex,rowData) {},
                loadFilter : function(data) {
                    if (!data.success) {
                        framework.dialog(data);
                        return [];
                    } else {
                        debugger;
                        var result = {
                            total : data.result.total,
                            rows : data.result.entry
                        };
                        return result;
                    }
                }
            });
        }
    });

    delInLine = function(id){
		$.messager.confirm('','确定删除？',function(r){
			if(r){
	    		var ids = [];
		    	ids[0]=id;
				framework.startMask();
				$.post(framework.dynUrl+"/coupon/del.htm",{ids:ids.join(",")},function(d){
					if(d.success){
			    		framework.closeMask();
						$('#entityList').datagrid("reload");
					}
                    else{
						framework.alert(d.errMsg);
					}
				},'json');
			}
            else{
				framework.closeMask();
			}
		});
	}

    exportByPId = function(pid){
        $.messager.confirm('','确定导出?',function(r){
            if(r){
                $.post(framework.dynUrl+"/coupon/getOfflineCoupon.htm",{pid:pid},function(d){
                            if(d.success){
                                framework.closeMask();

                                var data = d.result.entry; // $('#txt').val();
                                if(data == '')
                                    return;
                                if(data)
                                    JSONToCSVConvertor(data, "Coupon ID "+pid, true);
                            }
                            else{
                                framework.alert(d.errMsg);
                            }
                        }
                        ,'json'
                );
            }
            else{
                framework.closeMask();
            }
        });
    }

    $("#qryEntity").click(function(){
        $('#entityList').datagrid("reload");
    });

</script>
</html>
