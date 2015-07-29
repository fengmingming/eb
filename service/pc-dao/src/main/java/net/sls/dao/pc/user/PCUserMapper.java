package net.sls.dao.pc.user;

import java.math.BigDecimal;
import java.util.List;

import net.sls.dto.pc.user.User;

import org.apache.ibatis.annotations.Param;

public interface PCUserMapper {

	int countUserByMobile(@Param("mobile") String mobile);

	int insertUser(User user);

	List<User> selectUserByMobileOrPas(@Param("mobile") String mobile,@Param("password") String password);

	int countUserByUserName(@Param("userName") String userName);

	User selectUserByid(@Param("id") Long id);

	int updatePayPassword(@Param("id") Long id,@Param("payPassword") String payPassword);

	User selectUserByPassword(@Param("id") Long id,@Param("password") String password);

	int updatePassword(@Param("id") Long id,@Param("password") String password);

	int updateUser(User user);
	
	int updateUserBalance(@Param("userId") long userId,@Param("price") BigDecimal useBalancePrice);

	int updateUserAmount(@Param("id") Long id,@Param("amount") BigDecimal amount,@Param("price") BigDecimal money);

	User selectUserByMobile(@Param("mobile") String mobile);
	
	User selectUserByColumn(@Param("colName") String colName ,@Param("_val") String _val);
	
	User updateUserById(@Param("colName") String colName ,@Param("_val") String _val);
	
	Long countConsumerByPavilionId(@Param("pid") Long pid);
	
	List<User> getConsumerByPavilionId(@Param("pid") Long pid, @Param("start") int start, @Param("number") int number);
	
	List<User> getConsumerByNameMobile(@Param("nameMobile") String nameMobile, @Param("pid") Long pid);
}
