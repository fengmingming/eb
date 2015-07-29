package net.sls.component.pc.user;

import net.sls.dto.pc.pay.PrepaidCard;
import net.sls.dto.pc.user.AmountLog;
import net.sls.dto.pc.user.AmountOrder;
import net.sls.dto.pc.user.User;

public interface IAmountOrderComponent {

	void insertAmountOrder(AmountOrder ao);


	AmountOrder selectAmountOrderByVoucherOrderNum(String voucherOrderNum);

	void updateAmountOrderStatus(User u, AmountLog al, AmountOrder ao);


	void insertAmountOrderAndRechareCard(PrepaidCard prepaidCard, AmountLog al, User u);

}
