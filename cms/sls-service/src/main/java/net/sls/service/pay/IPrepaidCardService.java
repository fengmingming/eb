package net.sls.service.pay;

import java.util.List;

import net.sls.dto.pay.PrepaidCard;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IPrepaidCardService {
	/**
	 * 
	 * @param rb 传入的参数为： 一个PrepaidCard对象，其属性包括 ：
	 * 面值，开始时间,结束时间，数据量，备注.在ReqBo key-value中的
	 * k值为prepaidcard;
	 * @return 生成的卡的列表
	 */
	ResBo<?> insertPrepaidCard(ReqBo rb);
	
	
	/**
	 * query the object of prepaid card with it's property
	 * @param rb
	 * @return	ResBo wrapper object, 
	 */
	
	ResBo<Pager<List<PrepaidCard>>> selectPrepaidCard(ReqBo rb);
	
	
	/**
	 * 
	 * @param rb
	 * @return
	 */

	ResBo<?> updatePrepaidCardStatus(ReqBo rb);
	
	
	/**
	 * upate a prepaid card object property 
	 * @param rb
	 * @return success or not,wrappered by ResBo object.
	 */
	ResBo<?> updatePrepaidCardCheckStatus(ReqBo rb);


	ResBo<Pager<List<PrepaidCard>>> selectBatch(ReqBo rb);


	public ResBo<?> exportExcel(ReqBo rb);
	
	public ResBo<?> getNextBatchNum();
	
	/**
	 * 充值卡统计功能
	 * 1.一共生成多少卡，2已经激活卡数，3已充值卡数
	 * */
	public ResBo<?> statistics();
	
	/**
	 * 充值信息
	 * */
	public ResBo<?> rechargedInfo(ReqBo reqBo);
	
}
