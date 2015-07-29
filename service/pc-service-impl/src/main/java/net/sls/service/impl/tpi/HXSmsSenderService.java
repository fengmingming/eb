package net.sls.service.impl.tpi;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Service;

import util.httpclient.HttpSend;
import framework.web.ReqBo;
import framework.web.ResBo;
import net.sls.service.tpi.ISmsSenderService;

/**
 * Third Party Interface Impl
 * 华兴软通短信发送功能
 * @author sls006
 *
 */

/**
 * ================================================================
 * 如何使用：
 * 1.发送短信使用如下方法：
 * SmsSender.send(String phoneNumber,String content);
 * Note: content的内容一定要经过短信平台的模板备案通过后方可使用。
 * 返回结果为String类型的字符串，如下为其示例
 * result=0&message=短信发送成功&smsid=20150115153502167
 * 一般不用处理返回结果
 * 
 * 2.查询余额使用如下方法：
 * SmsSender.queryBalance();
 * 返回的为String 类型的字符串。
 *

	try{		
		String s = SmsSender.send("13703799908","88239" );
		System.out.println(s);
		System.out.println(SmsSender.queryBalance());
	}catch(Exception e){
		e.printStackTrace();
	}
	===============================================================
*/
@Service("smsSenderService")
public class HXSmsSenderService implements ISmsSenderService{
	
	
	/**
	 * 
	 * @param phoneNumber	发送的手机号，多个手机号使用半角逗号分开，最多1000个
	 * @param content		经过平台方备案的模板，否则不能被用户接收
	 */
	public ResBo<String> send(ReqBo rb) {
		String strReg = HXConfig.REG_NUMBER;			// 注册号
		String strPwd = HXConfig.REG_PASSWORD;			// 密码
		String strSourceAdd = ""; 						// 子通道号，可为空（预留参数一般为空）
		String strPhone = rb.getParamStr("mobile");		// 发送到的手机号	
		String strContent="";							// 短信内容
		try {
			strContent = HttpSend.paraTo16(String.format(HXConfig.SMS_TEMPLATE, rb.getParam("message")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		StringBuilder sb = new StringBuilder();
		sb.append("reg=").append(strReg).append("&pwd=")
			.append(strPwd).append( "&sourceadd=").append(strSourceAdd).append("&phone=")
			.append(strPhone).append("&content=").append(strContent);
		
		String strRes = new String();
		strRes = HttpSend.postSend(HXConfig.SEND_URL, sb.toString());
		ResBo<String> rsb = new ResBo<String>(); 
		rsb.setResult(strRes);
		return rsb;
	}

	

	/**
	 * 查询余额
	 * @return
	 */
	public String queryBalance(){
		StringBuilder sb = new StringBuilder();
		sb.append("reg=").append(HXConfig.REG_NUMBER).append("&pwd=").append(HXConfig.REG_PASSWORD);
		return HttpSend.postSend(HXConfig.QUERY_BALANCE_URL, sb.toString());
	}

}
