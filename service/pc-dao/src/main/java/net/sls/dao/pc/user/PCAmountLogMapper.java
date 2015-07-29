package net.sls.dao.pc.user;

import java.util.List;
import java.util.Map;

import net.sls.dto.pc.user.AmountLog;

import org.apache.ibatis.annotations.Param;

public interface PCAmountLogMapper {

	long countAmountLogByUserId(@Param("userId") Long userId,
			@Param("type") Integer type);

	List<Map<Object, Object>> selectAmountLogByUserId(
			@Param("userId") Long userId, @Param("type") Integer type,
			@Param("start") Integer start, @Param("number") Integer number);

	int insertAmountLog(AmountLog log);


}
