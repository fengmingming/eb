package net.sls.dao.pc.user;

import org.apache.ibatis.annotations.Param;

import net.sls.dto.pc.user.AmountOrder;

public interface PCAmountOrderMapper {

	int insertAmountOrder(AmountOrder ao);

	AmountOrder selectAmountOrderByVoucherOrderNum(@Param("voucherOrderNum") String voucherOrderNum);

	int updateAmountOrderStatus(AmountOrder ao);

}
