package net.sls.component.user;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sls.dto.user.AmountOrder;
import net.sls.dto.user.User;
import framework.web.Pager;

public interface IMemberComponent {

	/**
	 * 
	 * 根据条件查询会员用户信息列表
	 * */
	public Pager<List<User>> selectMemberInfos(Integer memberType,String areaCode,String username,String mobile,Long pavilionId,Date startDate,Date endDate,Integer start,Integer number);
	/**
	 * 
	 * 根据条件查询会员用户信息
	 * */
	public List<User> selectMemberInfo(String username,String mobile);
	/**
	 * 
	 * 根据条件查询会员用户信息
	 * */
	public List<Map<Object,Object>> selectUserInfo(String username,String mobile);
	
	public User selectByPrimaryKey(Long id);
	/**
	 * 根据userId查询会员用户信息
	 * @param id
	 * @return
	 */
	public Map<Object,Object> selectUserById(Long id);
	
	public void updateRePass(long userId,String repass);
	
	public void updateUnbindMobile(long userId,String mobile);
	
	public List<User> selectUserByUserName(String username);
	
	public void insertUser(User user);
	
	public Pager<List<AmountOrder>> selectAmountOrderList(long userId,int start,int number);
	
	public Pager<List<Map<String,Object>>> selectAmountLogList(long userId,Date startDate,Date endDate,int start,int number);
	
	public List<Map<String,Object>> selectExportExcel(long userId,Date startDate,Date endDate);
	
	public List<Map<String,Object>> selectExportExcel(List<Long> userIds,Date startDate,Date endDate);
	
	public void updateCompleteAccountOrder(long amountOrderId);
	
	/**@author wangshaohui
	 * @Description: TODO 查看手机号是否存在
	 * @param mobile
	 * @return boolean
	 */
	public boolean selectMobileIsExist(String mobile);
	
	public void updateMemberInfo(User user);
	
	public void updateFinanceRechange(long userId,BigDecimal money);
	
	public List<Map<String,Object>> selectAnalysisUser(long userId);
	
	public List<Map<String,Object>> selectExcelUser(Long pavilionId,Date startDate,Date endDate); 
	
	public void updatePayPass(long userId);
	
	public void updateUserAmountOrderRefund(long userId,BigDecimal money, String orderNum);
}
