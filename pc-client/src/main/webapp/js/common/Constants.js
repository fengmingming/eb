/** 
* Edited by NotePad ++ JSLint
* 
* Create by : Liu Yingxian
* dozray@163.com
*
* 系统常量定义
*/

/*
if (!window['aT']) {
    window['aT'] = (new Date()).getTime();
}*/
if (!window['sls']) {
    window['sls'] = {};
}
if (!window['sls']['Constants']) {
    var Constants = (function () {
        // Private static attribute
        var constants = {
            /* BASE_HREF */
			BASE_HREF	:	"http://localhost:8080/pc-client",
			
			/* 必须项目的提示 */
            REQURED_TIP	:	"<span class='edit_red'>*</span>",
			
            /*默认的请求页面及页面大小*/
            DEFAULT_REQUEST_PAGE	:	1,
            DEFAULT_PAGE_SIZE		:	20,
			
            /*显示文本限制长度*/
            LIMIT_CONTENT_LENGTH	:	18,
			
            ErrorMsg	:	'<div id="errorMsg" class="widget-content corner-all">' +
		    '<h3 class="ui-widget-header ui-corner-all">Show</h3>' +
		    '<p>操作超时，请重试。</p></div>',
			
            SuccessMsg	:	'<div id="successMsg" class="widget-content corner-all">' +
		    '<h3 class="widget-header corner-all">Show</h3>' +
		    '<p>操作成功。</p></div>'
        };
        // constructor
        var ctor = function (constructorArgument) {
            ;
        };
        // Privileged static method
        ctor.getConstant = function (name) {
            return constants[name];
        };
        // Return
        return ctor;
    })();
};

