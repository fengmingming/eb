package net.sls.component.pay;

import java.util.Date;
import java.util.List;
import java.util.Map;

import framework.web.Pager;
import net.sls.dto.pay.PrepaidCard;

public interface IPrepaidCardComponent {
	
	// 取得最大的SN 当前的我们使用的是id	
	Long getMaxId();
	
	// 取得记录数
	Long getCount(PrepaidCard pc);
	
	String selectBatchMaxNum(String date);//YYMMDD
		
	
	// 批量插入系统生成的卡
	int insertPrepaidCard(List<PrepaidCard> lst);	
	
	// 修改卡的审核状态（依照批次）审核状态  1.新生成 2.审核中 3.已审核
	
	int updatePrepaidCardCheckStatus(String batch,Long[] ids, byte b);
	
	// 修改卡的状态 (依照卡id) 0.已冻结  1.已激活 2.已超期失效 3.已充值失效
	
	int updatePrepaidCardStatus(long id, byte b);
	
	Pager<List<PrepaidCard>> selectPrepaidCardList(PrepaidCard pc,Integer IdStart,Integer IdEnd,int start, int end);
	Pager<List<PrepaidCard>> selectBatchList(PrepaidCard pc, int start, int end);
	
	Map<String,Long> selectPrepaidCardStatistics();

	Pager<List<Map<String,Object>>> selectPrepaidCardRechargedInfo(Date startDate,Date endDate,int page,int number);
	
}
