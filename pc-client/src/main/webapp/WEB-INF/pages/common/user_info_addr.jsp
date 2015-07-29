<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<script type="text/javascript" src="${imgUrl }/area.js"></script>
<script type="text/javascript">
	function restoreValue(pro, cit, dis, com, pav){
		var province = window.framework.area_map[pro];
		var city = window.framework.area_map[cit];
		var district = window.framework.area_map[dis];
		if(!district){
			district = '';
		}
		var community = window.framework.area_map[com];
		if(!community){
			community = '';
		}
		var pavilion = window.framework.area_map[pav];
		if(!pavilion){
			pavilion = '';
		}
		$("#td_pavilion").html(province+"&nbsp"+city+"&nbsp"+district+"&nbsp"+community+"&nbsp"+pavilion);
	}
</script>
<tr>
	<td class="td_fs">所属亭子：</td>
	<td id="td_pavilion"></td>
</tr>
	