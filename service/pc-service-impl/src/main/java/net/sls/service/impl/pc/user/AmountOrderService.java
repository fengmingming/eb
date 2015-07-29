package net.sls.service.impl.pc.user;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import net.sls.commons.businessconstant.BusinessContant;
import net.sls.component.pc.order.impl.OrderComponent;
import net.sls.component.pc.pay.IPrepaidCardComponent;
import net.sls.component.pc.user.IAmountOrderComponent;
import net.sls.component.pc.user.IUserComponent;
import net.sls.dto.pc.pay.PrepaidCard;
import net.sls.dto.pc.user.AmountBeanDto;
import net.sls.dto.pc.user.AmountLog;
import net.sls.dto.pc.user.AmountOrder;
import net.sls.dto.pc.user.User;
import net.sls.service.pc.user.IAmountOrderService;

//import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Encoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.model.AmountLogTypeEnum;
import framework.web.ReqBo;
import framework.web.ResBo;
@Service("amountOrderService")
public class AmountOrderService implements IAmountOrderService{
	
	private Logger log = LoggerFactory.getLogger(OrderComponent.class);
	
	@Autowired
	private IAmountOrderComponent aoc;
	@Autowired
	private IUserComponent uc;
	@Autowired
	private IPrepaidCardComponent pCardC;
	@Override
	public ResBo<AmountOrder> rechargeBeforeAmount(ReqBo reqBo){
		ResBo<AmountOrder> resBo=new ResBo<AmountOrder>();
		AmountOrder ao=reqBo.getReqModel(AmountOrder.class);
		ao.setVoucherOrderNum(createVoucherOrderNum());
		ao.setCreateTime(new Date());
		ao.setStatus(BusinessContant.AMOUNTORDER_FAILED);
		aoc.insertAmountOrder(ao);
		resBo.setResult(ao);
		return resBo;
	}
	
	@Override
	public ResBo<AmountOrder> rechargeAfterAmount(ReqBo reqBo){
		ResBo<AmountOrder> resBo=new ResBo<AmountOrder>();
		AmountBeanDto abd = reqBo.getReqModel(AmountBeanDto.class);
		AmountOrder ao = aoc.selectAmountOrderByVoucherOrderNum(abd.getVoucherOrderNum());
		if (ao.getMoney().compareTo(abd.getMoney())== 0) {
			ao.setStatus(BusinessContant.AMOUNTORDER_SUCCESS);
			ao.setThirdOrderNum(abd.getThirdOrderNum());
			ao.setThirdOrderNum(abd.getThirdOrderNum());
		}else{
			ao.setStatus(BusinessContant.AMOUNTORDER_FAILED);
			log.error("rechargeAfterAmount failed userId = "+ ao.getUserId() +" voucherOrderNum = "+ao.getVoucherOrderNum()+" thirdOrderNum= "+ao.getThirdOrderNum()+" money = "+ao.getMoney() );
			return new ResBo<AmountOrder>(39,"");
		}
		User u=uc.selectUserById(ao.getUserId());
		AmountLog al=new AmountLog();
		al.setCreateTime(new Date());
		al.setMoney(abd.getMoney());
		al.setCurAmount(u.getAmount());
		al.setOperator(u.getId());
		al.setUserId(u.getId());
		al.setType(AmountLogTypeEnum.in.getCode());
		al.setRemark("资金充值");
		aoc.updateAmountOrderStatus(u,al,ao);
		resBo.setResult(ao);
		return resBo;
	}
	
	@Override
	public ResBo<AmountOrder> getRechargeByVoucherOrderNum(ReqBo reqBo){
		ResBo<AmountOrder> resBo=new ResBo<AmountOrder>();
		AmountOrder ao = aoc.selectAmountOrderByVoucherOrderNum(reqBo.getParamStr("voucherOrderNum"));
		resBo.setResult(ao);
		return resBo;
	}
	
	@SuppressWarnings("restriction")
	@Override
	public ResBo<PrepaidCard> rechargeCardAmount(ReqBo reqBo){
		ResBo<PrepaidCard> resBo=new ResBo<PrepaidCard>();
		Long userId=reqBo.getParamLong("userId");
		String password = reqBo.getParamStr("password");
		BASE64Encoder  base64 = new BASE64Encoder ();  
		PrepaidCard prepaidCard = pCardC.selectPrepaidCard(base64.encode(password.getBytes()));
		User u=uc.selectUserById(userId);
		if (prepaidCard == null) {
			return new ResBo<PrepaidCard>(44L);
		}
		prepaidCard.setCardStatus(BusinessContant.RECHARGECARD_CARDSTATUS_DISABLE);;
		AmountLog al=new AmountLog();
		al.setCreateTime(new Date());
		al.setMoney(new BigDecimal(prepaidCard.getParValue()));
		al.setCurAmount(u.getAmount().add(new BigDecimal(prepaidCard.getParValue())));
		al.setOperator(u.getId());
		al.setUserId(u.getId());
		al.setRemark(BusinessContant.AMOUNTLOG_CARDREMARK+BusinessContant.AMOUNTLOG_LINE+prepaidCard.getParValue()+BusinessContant.AMOUNTLOG_CARDREMARK);
		al.setType(AmountLogTypeEnum.in.getCode());
		//u.setAmount(u.getAmount());
		aoc.insertAmountOrderAndRechareCard(prepaidCard,al,u);
		resBo.setResult(prepaidCard);
		return resBo;
	}
	
	/**
	 * 充值订单号
	 * @return
	 */
	private String createVoucherOrderNum(){
		String s = UUID.randomUUID().toString();
		return  s.replaceAll("-", "");
	}
	
}
