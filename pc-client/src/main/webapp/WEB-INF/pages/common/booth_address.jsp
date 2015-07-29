<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
$(function() {
	var _province = $("#province"),
		_city = $("#city"),
		_district = $("#district"),
		_community = $("#community"),
		_pavilionId = $("#pavilionId"),
		_pavilionCode = $("#pavilionCode");
	
	if ($("#flag").val() == "" || $("#flag").val() == "null" || $("#flag").val() == null) {getAreaList(1, _province);}
	
	_province.change(function() {
		var p = $(this).val();
		if (p != "") {
			getAreaList(p, _city);
		}
		_city.html("<option value=''>请选择市</option>");
		_district.html("<option value=''>请选择区</option>");
		_community.html("<option value=''>请选择商圈</option>");
		_pavilionId.html("<option value=''>请选择服务亭</option>");
		_pavilionCode.val("");
	});
	_city.change(function() {
		var p = $(this).val();
		if (p != "") {
			getAreaList(p, _district);
		}
		_district.html("<option value=''>请选择区</option>");
		_community.html("<option value=''>请选择商圈</option>");
		_pavilionId.html("<option value=''>请选择服务亭</option>");
		_pavilionCode.val("");
	});
	_district.change(function() {
		var p = $(this).val();
		if (p != "") {
			getAreaList(p, _community);
		}
		_community.html("<option value=''>请选择商圈</option>");
		_pavilionId.html("<option value=''>请选择服务亭</option>");
		_pavilionCode.val("");
	});
	_community.change(function() {
		var p = $(this).val();
		if (p != "") {
			getAreaList(p, _pavilionId);
		}
		_pavilionId.html("<option value=''>请选择服务亭</option>");
		_pavilionCode.val("");
	});
	_pavilionId.change(function() {
		var p = $(this).val();
		if (p != "") {
			_pavilionCode.val(p);
		} else {
			_pavilionCode.val("");
		}
	});
	
	//restoreValue(2, 52, 503, 3417, 3418, 3418);
});
function getAreaList(pid, jobj, callback) {
	if(pid == ""){
		return;
	}
	$.post(_rootUrl + "/area/getAreaList.htm",{pid:pid},
			function(data) {
		if (data.success) {
			var str = "<option value=''>" + strName(jobj) + "</option>",
				p = data.result;
			for (var i = 0; i < p.length; i++) {
				str += "<option value='" + p[i].v + "'>" + p[i].k + "</option>";
			}
			jobj.html("");
			jobj.append($(str));
			if (callback) {
				callback();
			}
		}
	},"json");
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
		str = "请选择服务亭";
	}
	return str;
}
function restoreValue(pe, cy, dt, cny, pd, pcd) {
	getAreaList(1, $("#province"), function() {
		$("#province").val(pe);
		getAreaList(pe, $("#city"), function() {
			$("#city").val(cy);
			getAreaList(cy, $("#district"), function() {
				$("#district").val(dt);
				getAreaList(dt, $("#community"), function() {
					$("#community").val(cny);
					getAreaList(cny, $("#pavilionId"), function() {
						$("#pavilionId").val(pd);
						$("#pavilionCode").val(pcd);
					});
				});
			});
		});
	});
}
</script>
<input type="hidden" id="flag" value="<%=request.getParameter("flag")%>" />
<tr>
	<td class="td_fs"><span class="r_star">*</span>服务亭地址：</td>
	<td>
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
<tr>
	<td>&nbsp;</td>
	<td id="bh_ads">
		<select id="community" name="community" class="r_slt r_slt_w0">
			<option value="">请选择商圈</option>
		</select>
		<select id="pavilionId" name="pavilionId" class="r_slt r_slt_w0">
			<option value="">请选择服务亭</option>
		</select>
		<input type="hidden" value="" id="pavilionCode" name="pavilionCode"/>
	</td>
</tr>

