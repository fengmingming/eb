package net.sls.service.pc.user;

import net.sls.dto.pc.pay.PrepaidCard;
import net.sls.dto.pc.user.AmountOrder;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IAmountOrderService {
	/**
	 * 充值前
	 * @param reqBo
	 * @return
	 */
	public ResBo<AmountOrder> rechargeBeforeAmount(ReqBo reqBo);
	/**
	 * 充值完成
	 * @param reqBo
	 * @return
	 */
	public ResBo<AmountOrder> rechargeAfterAmount(ReqBo reqBo);
	/**
	 * 获取AmountOrder根据充值
	 * @param reqBo
	 * @return
	 */
	public ResBo<AmountOrder> getRechargeByVoucherOrderNum(ReqBo reqBo);
	/**
	 * 充值卡充值
	 * @param reqBo
	 * @return
	 */
	public ResBo<PrepaidCard> rechargeCardAmount(ReqBo reqBo);
}
