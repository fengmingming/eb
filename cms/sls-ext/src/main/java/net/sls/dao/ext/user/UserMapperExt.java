package net.sls.dao.ext.user;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sls.dto.user.AmountOrder;
import net.sls.dto.user.User;

import org.apache.ibatis.annotations.Param;

public interface UserMapperExt {

	public List<User> selectUsersByFilter(@Param("memberType")Integer memberType,@Param("areaCode")String areaCode,@Param("userName") String username,@Param("mobile") String mobile,@Param("pavilionId")Long pavilionId,@Param("startDate")Date startDate,@Param("endDate")Date endDate,@Param("start") Integer start,@Param("number") Integer number);
	
	public long countUsersByFilter(@Param("memberType")Integer memberType,@Param("areaCode")String areaCode,@Param("userName") String username,@Param("mobile") String mobile, @Param("pavilionId")Long pavilionId,@Param("startDate")Date startDate,@Param("endDate")Date endDate);

	Map<Object,Object> selectUserById(@Param("id") Long id);

	public List<User> selectMemberInfo(@Param("userName") String userName, @Param("mobile") String mobile);
	
	public List<Map<Object,Object>> selectUserInfo(@Param("userName") String userName, @Param("mobile") String mobile);

	public User selectByPrimaryKey(@Param("id") Long id);
	
	public int updateUnbindMobile(@Param("userId")long userId,@Param("mobile")String mobile);
	
	public List<AmountOrder> selectAmountOrders(@Param("userId") Long userId,@Param("start") int start,@Param("number") int number);
	
	public List<Map<String,Object>> selectAmountLogs(@Param("userId") Long userId,@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("start") int start,@Param("number") int number);
	
	public long countAmountOrders(Long userId);
	
	public long countAmountLogs(@Param("userId") Long userId,@Param("startDate") Date startDate,@Param("endDate") Date endDate);
	
	public int updateAccountOrderStatus(long amountOrderId);
	
	public int updateMemberAmount(@Param("userId") long userId,@Param("money")BigDecimal money);

	/**@author wangshaohui
	 * @Description: TODO 手机号的数量
	 * @param mobile
	 * @return int
	 */
	public int countByMobile(String mobile);
	
	public List<Map<String,Object>> excelAmountLogs(@Param("userId") Long userId,@Param("startDate") Date startDate,@Param("endDate") Date endDate);
	
	public List<Map<String,Object>> excelAmountLogsByIds(@Param("ids") List<Long> userIds,@Param("startDate") Date startDate,@Param("endDate") Date endDate);
	
	public int financeRechange(@Param("userId")long userId,@Param("money")BigDecimal money);
	
	public int updateUserAmountByOrderRefund(@Param("userId")long userId,@Param("money")BigDecimal money);
	
	public List<Map<String,Object>> analysisUser(long userId);
	
	public List<Map<String,Object>> user_excel(@Param("pavilionId")Long pavilionId,@Param("startDate")Date startDate,@Param("endDate")Date endDate);
}
