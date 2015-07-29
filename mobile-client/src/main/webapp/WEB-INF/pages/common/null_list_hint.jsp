<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<style type="text/css">
	.mark_logo { width: 52px; height: 52px; background: url(${staUrl }/images/sls_mz.png) no-repeat -40px -437px; background-size: 500px; margin: auto; margin-top: 70px;}
	.mark_txt { color: #666; font-size: 14px; text-align: center; margin-top: 20px;}
	.sls_logo { background: url(${staUrl }/images/sls_mz.png) no-repeat 0px 5px;; background-size: 500px; width: 40px; height: 40px; margin: auto; margin-top: 120px; margin-bottom: 5px;}
	.pd_txt { text-align: center; font-size: 14px; color: #666;}
</style>
<div class="mark_logo"></div>
<p class="mark_txt">抱歉！暂时没有“<%=request.getParameter("content") %>”记录</p>
<div class="sls_logo"></div>
<p class="pd_txt">把服务送到家门口</p>
