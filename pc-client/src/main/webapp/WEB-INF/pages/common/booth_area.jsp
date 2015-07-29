<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<script type="text/javascript" src="${imgUrl }/area.js"></script>
<script type="text/javascript">
$(function() {
	var _area_pmap = window.framework.area_pmap;
	
	var _province = $("#province"),
		_city = $("#city"),
		_district = $("#district"),
		_community = $("#community"),
		_pavilionId = $("#pavilionId"),
		_pavilionCode = $("#pavilionCode"),
		_pavilionId_div = $("#pavilionId_div");
	
	_province.change(function() {
		var p = _province.val();
		if (p != "") {
			getAreaList(_city, _area_pmap[p]);
		} else {
			_city.html("<option value=''>请选择市</option>");
		}
		_district.html("<option value=''>请选择区</option>");
		_community.html("<option value=''>请选择商圈</option>");
		_pavilionId.html("<option value=''>请选择自提点</option>");
		_pavilionId_div.html("请选择自提点");
		_pavilionCode.val("");
	});
	_city.change(function() {
		var p = _city.val();
		if (p != "") {
			getAreaList(_district, _area_pmap[p]);
		} else {
			_district.html("<option value=''>请选择区</option>");
		}
		_community.html("<option value=''>请选择商圈</option>");
		_pavilionId.html("<option value=''>请选择自提点</option>");
		_pavilionId_div.html("请选择自提点");
		_pavilionCode.val("");
	});
	_district.change(function() {
		var p = _district.val();
		if (p != "" && _area_pmap[p]) {
			getAreaList(_community, _area_pmap[p]);
		} else {
			_community.html("<option value=''>请选择商圈</option>");
		}
		_pavilionId.html("<option value=''>请选择自提点</option>");
		_pavilionId_div.html("请选择自提点");
		_pavilionCode.val("");
	});
	/*
	_community.change(function() {
		var p = _community.val();
		if (p != "" && _area_pmap[p]) {
			getAreaList(_pavilionId, _area_pmap[p]);
		} else {
			_pavilionId.html("<option value=''>请选择自提点</option>");
		}
		_pavilionCode.val("");
	});
	*/
	//此方法用来查询数据库里的数据
	_community.change(function() {
		var p = _community.val();
		if (p) {
			$.post(_rootUrl + "/address/getpavilionAreaById.htm", {pavilionId:p}, function(data, textStatus) {
				if (data.result.length) {
					var _tmp = "<option value=''>请选择自提点</option>";
					_pavilionId_div.html("请选择自提点");
					var item = data.result;
					for (var i = 0; i < item.length; i++) {
						_tmp += "<option value=" + item[i].pavilionId + " code=" + item[i].code + ">" + item[i].paName + "→" + item[i].name + "</option>";
					}
					_pavilionId.html("");
					_pavilionId.append($(_tmp));
				} else {
					_pavilionId.html("<option value=''>请选择自提点</option>");
					_pavilionId_div.html("请选择自提点");
				}
			},"json");
		} else {
			_pavilionId.html("<option value=''>请选择自提点</option>");
			_pavilionId_div.html("请选择自提点");
		}
		_pavilionCode.val("");
	});
	_pavilionId.change(function() {
		var p_obj = _pavilionId.find("option:selected");
		if (p_obj.val() != "") {
			_pavilionCode.val(p_obj.attr("code"));
			_pavilionId_div.html(p_obj.html().split("→")[1]);
		} else {
			_pavilionCode.val("");
			_pavilionId_div.html("请选择自提点");
		}
	});
	
	if ($("#flag").val() == "" || $("#flag").val() == "null" || $("#flag").val() == null) {getAreaList(_province, _area_pmap[1]);}
	
	//初始化省
	//getAreaList(_province, _area_pmap[1]);//省集
	
	//恢复地址
	//restoreValue(2, 52, 503, 3481, 3488, "");
});

function getAreaList(jobj, _array, callback) {
	var _tmp = "<option value=''>" + strName(jobj) + "</option>";
	if (_array) {
		for (var i = 0; i < _array.length; i++) {
			_tmp += "<option value=" + _array[i].id + " code=" + _array[i].code + ">" + _array[i].name + "</option>";
		}
	}
	jobj.html("");
	jobj.append($(_tmp));
	if (callback) {
		callback();
	}
}

function strName(jobj) {
	var str = "";
	if (jobj.attr("id") == "province") {
		str = "请选择省";
	} else if (jobj.attr("id") == "city") {
		str = "请选择市";
	} else if (jobj.attr("id") == "district") {
		str = "请选择区";
	} else if (jobj.attr("id") == "community") {
		str = "请选择商圈";
	} else if (jobj.attr("id") == "pavilionId") {
		str = "请选择自提点";
	}
	return str;
}

/*
function restoreValue(pe, cy, dt, cny, pd, pcd) {
	var _area_pmap = window.framework.area_pmap;
	var _province = $("#province"),
		_city = $("#city"),
		_district = $("#district"),
		_community = $("#community"),
		_pavilionId = $("#pavilionId"),
		_pavilionCode = $("#pavilionCode");
	getAreaList(_province, _area_pmap[1], function() {
		_province.val(pe);
		getAreaList(_city, _area_pmap[pe], function() {
			_city.val(cy);
			getAreaList(_district, _area_pmap[cy], function() {
				_district.val(dt);
				getAreaList(_community, _area_pmap[dt], function() {
					_community.val(cny);
					getAreaList(_pavilionId, _area_pmap[cny], function() {
						_pavilionId.val(pd);
						_pavilionCode.val(pcd);
					});
				});
			});
		});
	});
}
*/

//与上面的方法唯一的不同点，就是自提点数据是在数据库里查询的，不是json库里。。。
function restoreValue(pe, cy, dt, cny, pd, pcd) {
	var _area_pmap = window.framework.area_pmap;
	var _province = $("#province"),
		_city = $("#city"),
		_district = $("#district"),
		_community = $("#community"),
		_pavilionId = $("#pavilionId"),
		_pavilionCode = $("#pavilionCode"),
		_pavilionId_div = $("#pavilionId_div");
	getAreaList(_province, _area_pmap[1], function() {
		_province.val(pe);
		getAreaList(_city, _area_pmap[pe], function() {
			_city.val(cy);
			getAreaList(_district, _area_pmap[cy], function() {
				_district.val(dt);
				getAreaList(_community, _area_pmap[dt], function() {
					_community.val(cny);
					$.post(_rootUrl + "/address/getpavilionAreaById.htm", {pavilionId:cny}, function(data, textStatus) {
						if (data.result.length) {
							var _tmp = "<option value=''>请选择自提点</option>";
							var item = data.result;
							for (var i = 0; i < item.length; i++) {
								_tmp += "<option value=" + item[i].pavilionId + " code=" + item[i].code + ">" + item[i].paName + "→" + item[i].name + "</option>";
							}
							_pavilionId.html("");
							_pavilionId.append($(_tmp));
							
							_pavilionId.val(pd);
							_pavilionId_div.html(_pavilionId.find("option:selected").html().split("→")[1]);
							_pavilionCode.val(pcd);
						}
					}, "json");
				});
			});
		});
	});
}
</script>
<input type="hidden" id="flag" value="<%=request.getParameter("flag")%>" />
<tr>
	<td class="td_fs"><span class="r_star">*</span><span id="td_fs_add">收货地址：</span></td>
	<td class="aad_td ba_add_td">
		<select id="province" name="province" class="r_slt r_slt_w">
			<option value="">请选择省</option>
		</select>
		<select id="city" name="city" class="r_slt r_slt_w">
			<option value="">请选择市</option>
		</select>
		<select id="district" name="district" class="r_slt r_slt_w">
			<option value="">请选择区</option>
		</select>
	</td>
</tr>
<tr id="tr_pavilion_ads">
	<td>&nbsp;</td>
	<td class="add_td" id="bh_ads" style="position: relative;">
		<select id="community" name="community" class="r_slt r_slt_w0">
			<option value="">请选择商圈</option>
		</select>
		<div id="pavilionId_div" style="width: 179px; height: 32px; border: solid 1px #ddd; line-height: 32px; text-indent: 10px; font-size: 13px; color: #000; position: absolute; top: 8px; left: 184px;">请选择自提点</div>
		<select style="position: absolute; top: 7px; left: 184px; opacity: 0; filter: alpha(opacity=0);" id="pavilionId" name="pavilionId" class="r_slt r_slt_w0">
			<option value="">请选择自提点</option>
		</select>
		<input type="hidden" value="" id="pavilionCode" name="pavilionCode"/>
	</td>
</tr>

