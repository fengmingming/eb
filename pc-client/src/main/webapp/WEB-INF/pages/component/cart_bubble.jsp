<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<script type="text/javascript">
//更新头部购物车气泡数量
function updateTopBubble(num) {
	var _hd_mc_qp = $("#hd_mc_qp");
	if (_hd_mc_qp.html() != "") {
		var _cur_num = parseFloat(_hd_mc_qp.html());
		_hd_mc_qp.html(_cur_num + parseFloat(num)).show();
	} else {
		_hd_mc_qp.html(num).show();
	}
	
	maxBubble(_hd_mc_qp);
}
//更新右侧气泡数量
function updateRightBubble(num) {
	var _fc_d_a = $("#fc_d_a");
	if (_fc_d_a.html() != "") {
		var _cur_num = parseFloat(_fc_d_a.html());
		_fc_d_a.html(_cur_num + parseFloat(num)).show();
	} else {
		_fc_d_a.html(num).show();
	}
	
	maxBubble(_fc_d_a);
}
//超过100显示99+
function maxBubble(obj) {
	if (parseFloat(obj.html()) > 99) {
		obj.html("99<b style='position: relative; top: -1px;'>+</b>");
	}
}
</script>
