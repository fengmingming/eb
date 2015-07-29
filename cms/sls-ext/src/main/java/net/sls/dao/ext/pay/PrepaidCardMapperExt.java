package net.sls.dao.ext.pay;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sls.dto.pay.PrepaidCard;

import org.apache.ibatis.annotations.Param;

public interface PrepaidCardMapperExt {   
	
	// get the max id which means count(id)	
	Long queryMaxId();
	
	// get the count of object in database which matches the given prepaid card.
	Long queryCount(@Param("pc") PrepaidCard pc);
	
	String queryBatchMaxNum(@Param("date") String date); //YYMMDD
	
	// get the total of the objects in database which matches the given e.g. 
	long queryBatchCount(@Param("pc") PrepaidCard pc);
    // insert object in a batch
    int insertPrepaidCard(@Param("ppc")List<PrepaidCard> lst);
    
    List<PrepaidCard> queryPrepaidCard(@Param("pc") PrepaidCard pc,
    		@Param("IdStart") Integer IdStart,
    		@Param("IdEnd") Integer IdEnd,
    		@Param("start") int start,
    		@Param("end") int end
    		);   
    
    List<PrepaidCard> queryAllBatch(@Param("pc") PrepaidCard pc,
    		@Param("start") int start,
    		@Param("end") int end
    		); 	
	// set the prepaid card check status to another value by batch or id.
	int updatePrepaidCardCheckStatus(@Param("batch")String batch,@Param("ids")Long[] ids,@Param("status") byte status);
	
	int updatePrepaidCardStatus(@Param("id")long id, @Param("status") byte status);
	
	/**
	 * 查询充值卡总数
	 * */
	Long selectPrepaidCardCount();
	
	/**
	 * 查询已激活充值卡总数
	 * */
	Long selectPrepaidCardActivated();
	
	/**
	 * 查询已失效卡总数
	 * */
	Long selectPrepaidCardInvalid();
	
	/**
	 * 查询已充值的充值卡总数
	 * */
	Long selectPrepaidCardRecharged();
	
	
	Long selectPrepaidCardRechargedInfoCount(@Param("startDate") Date startDate,@Param("endDate") Date endDate);
	/**
	 * 查询已充值卡的信息 
	 * */
	List<Map<String,Object>> selectPrepaidCardRechargedInfo(@Param("startDate") Date startDate,@Param("endDate") Date endDate, @Param("start") long start,@Param("end") long end);
		
}