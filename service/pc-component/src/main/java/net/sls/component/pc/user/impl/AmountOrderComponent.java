package net.sls.component.pc.user.impl;

import java.math.BigDecimal;
import java.util.Date;

import net.sls.component.pc.user.IAmountOrderComponent;
import net.sls.dao.pc.pay.PCPrepaidCardMapper;
import net.sls.dao.pc.user.PCAmountLogMapper;
import net.sls.dao.pc.user.PCAmountOrderMapper;
import net.sls.dao.pc.user.PCUserMapper;
import net.sls.dto.pc.pay.PrepaidCard;
import net.sls.dto.pc.user.AmountLog;
import net.sls.dto.pc.user.AmountOrder;
import net.sls.dto.pc.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.exception.BusinessException;

@Component
public class AmountOrderComponent implements IAmountOrderComponent {

	@Autowired
	private PCAmountOrderMapper aom;
	@Autowired
	private PCAmountLogMapper alm;
	@Autowired
	private PCUserMapper um;
	@Autowired
	private PCPrepaidCardMapper prepaidCardM;

	public void insertAmountOrder(AmountOrder ao) {
		int i = aom.insertAmountOrder(ao);
		if (i != 1) {
			throw new BusinessException(35L);
		}
	}

	@Override
	public AmountOrder selectAmountOrderByVoucherOrderNum(String voucherOrderNum) {
		return aom.selectAmountOrderByVoucherOrderNum(voucherOrderNum);
	}

	@Override
	public void updateAmountOrderStatus(User u, AmountLog al, AmountOrder ao) {
		int i = um.updateUserAmount(u.getId(),u.getAmount(),al.getMoney());
		if (i != 1) {
			throw new BusinessException(37L);
		}
		i = aom.updateAmountOrderStatus(ao);
		if (i != 1) {
			throw new BusinessException(35L);
		}
		i = alm.insertAmountLog(al);
		if (i != 1) {
			throw new BusinessException(38L);
		}
	}

	@Override
	public void insertAmountOrderAndRechareCard(
			PrepaidCard prepaidCard, AmountLog al, User u) {
		int i = um.updateUserAmount(u.getId(),u.getAmount(),new BigDecimal(prepaidCard.getParValue()));
		if (i != 1) {
			throw new BusinessException(45L);
		}
		i = alm.insertAmountLog(al);
		if (i != 1) {
			throw new BusinessException(38L);
		}
		i = prepaidCardM.updateCardStatus(prepaidCard.getId(),prepaidCard.getCardStatus(),u.getId());
		if (i != 1) {
			throw new BusinessException(46L);
		}
	}

}
