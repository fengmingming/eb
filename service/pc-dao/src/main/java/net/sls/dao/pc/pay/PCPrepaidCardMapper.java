package net.sls.dao.pc.pay;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import net.sls.dto.pc.pay.PrepaidCard;

public interface PCPrepaidCardMapper {

	PrepaidCard selectPrepaidCard(@Param("password") String password);

	int updateCardStatus(@Param("id") Integer id,@Param("cardStatus") Byte cardStatus,@Param("userId") Long userId);

}
