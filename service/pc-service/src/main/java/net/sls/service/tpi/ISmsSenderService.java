package net.sls.service.tpi;

import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * Third Party Interface
 * 
 * 发送短信的接口
 * @author sls006
 *
 */
public interface ISmsSenderService {
	/**
	 * 发送短信到指定的的手机号
	 * @param mobileNumber
	 * @param Message
	 * @return
	 */
	public ResBo<String> send(ReqBo rb);
	
	/**
	 * Query the balance of SMS Provider.
	 * @return	String Description of balance.
	 */
	public String queryBalance();
	
}
