<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../commons/commons.jsp" %>
</head>
<body>
	图片上传例子：<button id="promptzone" class="btn btn-primary">click me</button>
	<script type="text/javascript">
	jQuery(function ($) {
		$('#promptzone').ajaxUploadPrompt({
			url : window.framework.uploadUrl+"product&token="+framework.token,
			beforeSend : function () {
				framework.startMask();
			},
			onprogress : function (e) {
				/* if (e.lengthComputable) {
					var percentComplete = e.loaded / e.total;
					
				} */
			},
			error : function (e) {
				console.log(e);
			},
			success : function (data) {
				try{
					data = $.parseJSON(data);
					if(data.success){
						framework.alert(data.result);
					}else{
						framework.alert(data.errMsg);
					}
				}catch(e){
					framework.alert(data);
				}
				framework.closeMask();
			}
		});
	});
	</script>
</body>
</html>