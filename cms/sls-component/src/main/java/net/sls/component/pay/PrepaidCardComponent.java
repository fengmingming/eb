package net.sls.component.pay;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.web.Pager;
import net.sls.dao.ext.pay.PrepaidCardMapperExt;
import net.sls.dto.pay.PrepaidCard;
@Component("ppcCp")
public class PrepaidCardComponent implements IPrepaidCardComponent {	
	
	@Autowired
	private PrepaidCardMapperExt pcm;
	
	@Override
	public int insertPrepaidCard(List<PrepaidCard> lst) {
		
		return pcm.insertPrepaidCard(lst);
	}

	@Override
	public int updatePrepaidCardCheckStatus( String batch,Long[] ids, byte b) {
		// TODO Auto-generated method stub
		return pcm.updatePrepaidCardCheckStatus(batch,ids, b);
	}

	@Override
	public int updatePrepaidCardStatus(long id, byte b) {		
		return pcm.updatePrepaidCardStatus(id, b);
	}

	@Override
	public Long getMaxId() {		
		return pcm.queryMaxId();
	}

	@Override
	public Pager<List<PrepaidCard>> selectPrepaidCardList(PrepaidCard pc,Integer IdStart,Integer IdEnd,int pageIdx,int pageSize) 
	{		
		List<PrepaidCard> lst = null;
		// TODO Auto-generated method stub
		// 1. get the max(id)
		Long count = pcm.queryCount(pc);
		
		// 2. select the data of the page
		lst = pcm.queryPrepaidCard(pc,IdStart,IdEnd, (pageIdx-1)*pageSize, pageSize);	
		return new Pager<List<PrepaidCard>>(lst,count);
	}

	@Override
	public Pager<List<PrepaidCard>> selectBatchList(PrepaidCard pc,int pageIdx,int pageSize) {
		List<PrepaidCard> lst = null;
		// TODO Auto-generated method stub
		// 1. get the max(id)
		Long count = pcm.queryBatchCount(pc);
		
		// 2. select the data of the page
		lst = pcm.queryAllBatch(pc, (pageIdx-1)*pageSize, pageSize);		
		return new Pager<List<PrepaidCard>>(lst,count);
	}

	@Override
	public Long getCount(PrepaidCard pc) {		
		return pcm.queryCount(pc);
	}

	@Override
	public String selectBatchMaxNum(String date) {		
		return pcm.queryBatchMaxNum(date);
	}

	@Override
	public Map<String, Long> selectPrepaidCardStatistics() {
		Map<String,Long> map = new HashMap<String,Long>();
		map.put("count", pcm.selectPrepaidCardCount());
		map.put("activated", pcm.selectPrepaidCardActivated());
		map.put("recharged", pcm.selectPrepaidCardRecharged());
		map.put("invalid", pcm.selectPrepaidCardInvalid());
		return map;
	}

	@Override
	public Pager<List<Map<String, Object>>> selectPrepaidCardRechargedInfo(Date startDate, Date endDate, int page, int number) {
		return new Pager<List<Map<String,Object>>>(pcm.selectPrepaidCardRechargedInfo(startDate,endDate,(page-1)*number, number),pcm.selectPrepaidCardRechargedInfoCount(startDate,endDate));
	}	

}
