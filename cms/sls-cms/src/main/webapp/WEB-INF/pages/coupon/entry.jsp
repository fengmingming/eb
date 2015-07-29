<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <table>
        <tr>
            <td>名称</td>
            <td><input id="name" name="name" type="text" class="easyui-textbox" data-options="required:true"></td>
            <td>面值</td>
            <td><input id="parVal" name="parValue" type="text" class="easyui-numberbox" data-options="required:true, precision:0" > </td>
        </tr>
        <tr>
            <td>门槛值</td>
            <td><input id="minimum" name="minimum" type="text" class="easyui-numberbox" data-options="required:true,precision:0,min:0"></td>
            <td>发行总量</td>
            <td><input id="total" name="total" type="text" class="easyui-numberbox" data-options="required:true, precision:0"></td>
        </tr>
         <tr>
            <td>开始时间:</td>
            <td><input id="validStartDt" name="validityStart" class="easyui-datebox" data-options="required:true" /></td>
            <td>结束时间:</td>
            <td><input id="validEndDt" name="validityEnd" class="easyui-datebox" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>开始领取时间:</td>
            <td><input id="startDt" name="startdate" class="easyui-datebox" data-options="required:true" /></td>
            <td>结束领取时间:</td>
            <td><input id="endDt" name="enddate" class="easyui-datebox" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>限制类型</td>
            <td>
                <select class="easyui-combobox" name="limitType" data-options="required:true" style="width:185px">
                    <option value="1">注册优惠券</option>
                    <option value="2">线下优惠券</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>限制品类:</td>
            <td>
                <span>一级:</span>
                <select class="easyui-combobox" id="_1st" name="_1st"  style="width: 145px"
                    data-options="
                valueField:'v',
                textField:'k',
                url:'${dynUrl}/category/combobox.htm?pid=0',
                loadFilter:function(data){
                return data.result;
                },
                onSelect:function(record){
                    $('#_2th').combobox('clear');
                    $('#_2th').combobox('reload','${dynUrl}/category/combobox.htm?pid='+record.v);
                }
                "
                >

                </select>
            </td>
            <td><span>二级:</span>
                <select class="easyui-combobox" id="_2th" name="_2th" style="width:100px"
                       data-options="
                            valueField:'v',
                            textField:'k',
                            loadFilter:function(data){
                                return data.result;
                            },
                            onSelect:function(record){
                                $('#_3rd').combobox('clear');
                                $('#_3rd').combobox('reload','${dynUrl }/category/combobox.htm?pid='+record.v);
                            }
                       "
                        >
                </select>
            </td>
            <td><span>三级:</span>
                <select class="easyui-combobox" style="width:130px" id="_3rd" name="_3rd"
                    	data-options="valueField:'v',textField:'k',loadFilter:function(data){
						return data.result;
					}">
                </select>
            </td>
        </tr>

        <tr>
            <td>备注</td>
            <td colspan=3><input id="remark" name="remark" type="text" class="easyui-textbox" style="width:500px"></td>
        </tr>
    </table>
