<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="../commons/commons.jsp"%>
    <title>新增优惠券</title>
</head>
<body>
<div id="addNew">
    <form id="ff" method="post" action="addNew.htm">
        <%@include file="entry.jsp"%>
        <input type="hidden" id="limitCatVal" name="limitCat" value=""  />
    </form>
</div>
<div style="text-align:center;padding:5px">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">清空</a>
</div>
</body>
<script type="text/javascript">
    function submitForm(){
        var limCat;
        var _1st = $('#_1st').combobox("getValue");
        var _2th = $('#_2th').combobox("getValue");
        var _3rd = $('#_3rd').combobox("getValue");

        if(_3rd>0){
           limCat = _3rd;
        }else if(_2th>0){
            limCat = _2th;
        }else if(_1st>0){
            limCat = _1st;
        }

        // hidden value [limitCat:xxx]
        $('#limitCatVal').val(limCat);

        if($("#ff").form("validate")){
			var sta = $('#startDt').datetimebox('getValue');
			var end = $('#endDt').datetimebox('getValue');
            var stax = $('#validStartDt').datetimebox('getValue');
            var endx = $('#validEndDt').datetimebox('getValue');
			if(sta>end || stax>endx){
				framework.alert("起始日期不能大于结束日期");
				return;
			}
            if(sta>endx || end>endx){
                framework.alert("领用的起始时间及结束时间均应早于优惠券的有效期!");
                return;
            }
			var _name = $("#name").val();
			$.messager.confirm('','确定生成名称为[ '+ _name +' ]  的优惠券？',function(r){
				if(r){
					framework.startMask();
					$('#ff').form('submit',
						{
							success:function(data){
									framework.alert("成功生成优惠券"+ _name);
									framework.closeMask();
								}
						});
				}
			});
		}
    }
    function clearForm(){
        $('#ff').form('clear');
    }
</script>
</html>

