<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>手拉手触屏版</title>
	<%@include file="../common/common.jsp" %>
	<link rel='stylesheet' href='${staUrl }/css/order/check_order.css' type='text/css' />
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
						if(data.status == "302"){
							location.href=data.location;
							return ;
						}
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
			//restoreValue(2, 52, 503, 3417, 3418, 3418);
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
		
		var addOrupdate = function(){
			if(!$.trim($("#receiver").val())){
				alert("收货人姓名不能为空");
				return;
			}
			if(!$.trim($("#province").val())){
				alert("省不能为空");
				return;
			}
			if(!$.trim($("#city").val())){
				alert("市不能为空");
				return;
			}
			if(!$.trim($("#district").val())){
				alert("区不能为空");
				return;
			} 
			if(!$.trim($("#community").val())){
				alert("商圈不能为空");
				return;
			}
			if(!$.trim($("#pavilionId").val())){
				alert("自提点不能为空");
				return;
			}
			if(!$.trim($("#addressDetail").val())){
				alert("详细地址不能为空");
				return;
			}
			if(!/^1[3|5|7|8][0-9]\d{8}$/.test($.trim($("#mobile").val()))){
				alert("请输入正确的手机号");
				return;
			}
			$.post("${dynUrl }/address/saveOrUpdateAddress.htm",$("#pop_div").serialize(),function(data){
				if(data.status == "302"){
					location.href=data.location;
					return ;
				}
				if(data.success){
					window.location.href = "${dynUrl}/pcenter/address/index.htm";
				}else{
					alert(data.errMsg);
				}
			},"json");
		};
		$(document).ready(function(){
			restoreValue('${address.province}','${address.city}','${address.district}','${address.community}','${address.pavilionId}');
		});
	</script>
</head>

<body>
	<div class="viewport">
		<div id="header">
			<div class="header_txt">收货地址</div>
			<div class="h_back" onclick="history.go(-1);"></div>
			<div class="h_opt"></div>
		</div>
		<!-- 新增/修改地址信息弹出框 -->
		<form id="pop_div">
			<ul class="clear">
				<li class="co_pd_w">收货人</li>
				<li><input class="inp_shr" type="text" id="receiver" name="receiver" maxlength="5" value="${address.receiver }"/></li>
			</ul>
			<ul class="clear">
				<li class="co_pd_w">手机号码</li>
				<li><input class="inp_sjhm " type="text" id="mobile" name="mobile" maxlength="11" value="${address.mobile }"/></li>
			</ul>
			<ul class="clear">
				<li class="co_pd_w">省</li>
				<li>
					<select id="province" name="province">
						<option value="">-选择省份-</option>
					</select>
				</li>
			</ul>
			<ul class="clear">
				<li class="co_pd_w">市</li>
				<li>
					<select id="city" name="city">
						<option value="">-选择市区-</option>
					</select>
				</li>
			</ul>
			<ul class="clear">
				<li class="co_pd_w">区/县</li>
				<li>
					<select id="district" name="district">
						<option value="">-选择区/县-</option>
					</select>
				</li>
			</ul>
			<ul class="clear">
				<li class="co_pd_w">商圈</li>
				<li>
					<select id="community" name="community">
						<option value="">-选择商圈-</option>
					</select>
				</li>
			</ul>
			<ul class="clear">
				<li class="co_pd_w">自提点</li>
				<li style="position: relative;">
					<div id="pavilionId_div" style="width: 150px; height: 23px; border: solid 1px #ddd; line-height: 23px; text-indent: 10px; font-size: 13px; color: #000; position: absolute; top: 8px;">请选择自提点</div>
					<select id="pavilionId" name="pavilionId" style="width:150px; position: absolute; top: 8px; opacity: 0; filter: alpha(opacity=0);" >
						<option value="">-选择自提点-</option>
					</select>
					<input type="hidden" value="" id="pavilionCode" name="pavilionCode"/>
				</li>
			</ul>
			<ul class="clear">
				<li class="co_pd_w">详细地址</li>
				<li><input class="inp_xxdz" type="text" id="addressDetail" name="addressDetail" maxlength="254" value="${address.addressDetail }"/></li>
			</ul>
			<input type="hidden" name="id" value="${address.id }"/>
		</form>
		<a class="co_ent" onclick="addOrupdate()" id="aa_ent">确认</a>
	</div>
</body>
</html>