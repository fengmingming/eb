<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<style type="text/css">
#no_item_list { padding: 25px 0px 15px 0px; text-align: center;}
	#nil_cn { padding: 30px 20px; line-height: 33px; color: #ff7f00; display: inline-block; box-shadow: 0px 4px 4px #cbc7c4; border: solid 1px #e4e0dc; border-top: 0px; max-width: 700px;}
		#nil_cn_img { display: block; background: url(${staUrl }/images/inner.png) no-repeat 0px -518px; height: 33px; width: 45px; text-align: left; float: left;}
		#nil_cn span#nil_cn_txt { color: #666;}
		#nil_cn p { text-align: left; max-width: 655px; float: left; word-break: break-all;}
</style>
<div id="no_item_list">
	<div id="nil_cn" class="clear">
		<span id="nil_cn_img"></span>
		<p>
			<span>抱歉，暂时没有“</span>
			<span id="nil_cn_txt"><%=request.getParameter("content") %></span>
			<span>”记录</span>
		</p>
	</div>
</div>
