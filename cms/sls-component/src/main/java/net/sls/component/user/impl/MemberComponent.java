package net.sls.component.user.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sls.businessconstant.BusinessContant;
import net.sls.component.product.IAreaComponent;
import net.sls.component.user.IMemberComponent;
import net.sls.dao.ext.user.UserMapperExt;
import net.sls.dao.user.AmountLogMapper;
import net.sls.dao.user.AmountOrderMapper;
import net.sls.dao.user.UserMapper;
import net.sls.dto.user.AmountLog;
import net.sls.dto.user.AmountOrder;
import net.sls.dto.user.CmsUser;
import net.sls.dto.user.User;
import net.sls.dto.user.UserExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.framework.MD5Util;
import util.framework.SessionUtil;
import framework.exception.BusinessException;
import framework.web.Pager;

@Component
public class MemberComponent implements IMemberComponent {

	@Autowired
	private IAreaComponent areaComponent;
	@Autowired
	private UserMapperExt userMapperExt;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private AmountOrderMapper amountOrderMapper;
	@Autowired
	private AmountLogMapper amountLogMapper;

	@Override
	public Pager<List<User>> selectMemberInfos(Integer memberType,String code,String username, String mobile,Long pavilionId,Date startDate,Date endDate,Integer start, Integer number) {
		long count = userMapperExt.countUsersByFilter(memberType,code,username, mobile, pavilionId, startDate, endDate);
		List<User> list = userMapperExt.selectUsersByFilter(memberType,code,username, mobile, pavilionId, startDate, endDate, (start - 1) * number, number);
		Pager<List<User>> pager = new Pager<List<User>>(list, count);
		return pager;
	}

	@Override
	public List<Map<Object, Object>> selectUserInfo(String userName,
			String mobile) {
		return userMapperExt.selectUserInfo(userName, mobile);
	}

	@Override
	public List<User> selectMemberInfo(String userName, String mobile) {
		return userMapperExt.selectMemberInfo(userName, mobile);
	}

	@Override
	public User selectByPrimaryKey(Long id) {

		return userMapperExt.selectByPrimaryKey(id);
	}

	@Override
	public Map<Object, Object> selectUserById(Long id) {
		return userMapperExt.selectUserById(id);
	}

	@Override
	public void updateRePass(long userId, String repass) {
		User user = new User();
		user.setId(userId);
		user.setPassword(MD5Util.getMD5Str(repass));
		int i = userMapper.updateByPrimaryKeySelective(user);
		if (i != 1) {
			throw new BusinessException(2L);
		}
	}

	@Override
	public void updateUnbindMobile(long userId,String mobile) {
		int i = userMapperExt.updateUnbindMobile(userId,mobile);
		if (i != 1) {
			throw new BusinessException(2L);
		}
	}

	@Override
	public List<User> selectUserByUserName(String username) {
		UserExample e = new UserExample();
		e.createCriteria().andUserNameEqualTo(username);
		return userMapper.selectByExample(e);
	}

	@Override
	public void insertUser(User user) {
		userMapper.insertSelective(user);
	}

	@Override
	public Pager<List<AmountOrder>> selectAmountOrderList(long userId,
			int start, int number) {
		return new Pager<List<AmountOrder>>(userMapperExt.selectAmountOrders(
				userId, (start - 1) * number, number),
				userMapperExt.countAmountOrders(userId));
	}

	@Override
	public Pager<List<Map<String, Object>>> selectAmountLogList(long userId,
			Date startDate, Date endDate, int start, int number) {
		return new Pager<List<Map<String, Object>>>(
				userMapperExt.selectAmountLogs(userId, startDate, endDate,
						(start - 1) * number, number),
				userMapperExt.countAmountLogs(userId, startDate, endDate));
	}

	@Override
	public void updateCompleteAccountOrder(long amountOrderId) {
		int i = 0;
		AmountOrder ao = amountOrderMapper.selectByPrimaryKey(amountOrderId);
		User user = userMapper.selectByPrimaryKey(ao.getUserId());
		if (ao.getStatus().intValue() == BusinessContant.RECHARGE_STATUS_ERR) {
			i = i + userMapperExt.updateAccountOrderStatus(amountOrderId);
			i = i
					+ userMapperExt.updateMemberAmount(ao.getUserId(),
							ao.getMoney());
			AmountLog log = new AmountLog();
			log.setCreateTime(new Date());
			log.setCurAmount(user.getAmount().add(ao.getMoney()));
			log.setMoney(ao.getMoney());
			log.setOperator(((CmsUser) SessionUtil.get(BusinessContant.CMSUSER))
					.getId());
			log.setUserId(ao.getUserId());
			log.setType(BusinessContant.ACCOUNTLOGTYPE_INT);
			log.setRemark("财务强制充值");
			log.setOrigin(2);
			i = i + amountLogMapper.insertSelective(log);
			if (i != 3) {
				throw new BusinessException(21L);
			}
		}
	}

	@Override
	public boolean selectMobileIsExist(String mobile) {
		int num = userMapperExt.countByMobile(mobile);
		if (num >= 1) {
			return true;
		}
		return false;
	}

	@Override
	public List<Map<String, Object>> selectExportExcel(long userId,
			Date startDate, Date endDate) {
		return userMapperExt.excelAmountLogs(userId, startDate, endDate);
	}

	@Override
	public void updateMemberInfo(User user) {
		user.setPavilionCode(areaComponent.selectAreaCodeById(user.getPavilionId()));
		user.setUserName(null);
		int i = userMapper.updateByPrimaryKeySelective(user);
		if(i != 1){
			throw new BusinessException(2l);
		}
	}

	@Override
	public void updateFinanceRechange(long userId, BigDecimal money) {
		User user = userMapper.selectByPrimaryKey(userId);
		if(user.getMemberType() != 2){
			throw new BusinessException(33L);
		}
		int i = userMapperExt.financeRechange(userId, money);
		if(i != 1){
			throw new BusinessException(2l);
		}
		AmountLog log = new AmountLog();
		log.setCreateTime(new Date());
		log.setCurAmount(user.getAmount().add(money));
		log.setMoney(money);
		log.setOperator(((CmsUser) SessionUtil.get(BusinessContant.CMSUSER)).getId());
		log.setUserId(userId);
		log.setType(BusinessContant.ACCOUNTLOGTYPE_INT);
		log.setRemark("手拉手财务充值");
		log.setOrigin(2);
		i = amountLogMapper.insertSelective(log);
		if(i != 1){
			throw new BusinessException(2l);
		}
	}

	@Override
	public List<Map<String, Object>> selectExportExcel(List<Long> userIds,
			Date startDate, Date endDate) {
		return userMapperExt.excelAmountLogsByIds(userIds, startDate, endDate);
	}

	@Override
	public List<Map<String, Object>> selectAnalysisUser(long userId) {
		return userMapperExt.analysisUser(userId);
	}

	@Override
	public List<Map<String, Object>> selectExcelUser(Long pavilionId,
			Date startDate, Date endDate) {
		return userMapperExt.user_excel(pavilionId, startDate, endDate);
	}

	@Override
	public void updatePayPass(long userId) {
		User user = userMapper.selectByPrimaryKey(userId);
		if(user.getMobile() == null || user.getMobile().length() != 11){
			throw new BusinessException(34L);
		}
		String payPass = MD5Util.getMD5Str(user.getMobile().substring(5));
		User u = new User();
		u.setId(userId);
		u.setPayPassword(payPass);
		int i = userMapper.updateByPrimaryKeySelective(u);
		if(i != 1){
			throw new BusinessException(2L);
		}
	}

	@Override
	public void updateUserAmountOrderRefund(long userId, BigDecimal money, String orderNum) {
		User user = userMapper.selectByPrimaryKey(userId);
		int i = userMapperExt.updateUserAmountByOrderRefund(userId, money);
		if(i != 1){
			throw new BusinessException(36L);
		}
		AmountLog log = new AmountLog();
		log.setCreateTime(new Date());
		log.setCurAmount(user.getAmount().add(money));
		log.setMoney(money);
		log.setOperator(((CmsUser) SessionUtil.get(BusinessContant.CMSUSER)).getId());
		log.setUserId(userId);
		log.setType(BusinessContant.ACCOUNTLOGTYPE_INT);
		log.setRemark("退换货金额返还"+orderNum);
		log.setOrigin(2);
		i = amountLogMapper.insertSelective(log);
		if(i != 1){
			throw new BusinessException(2l);
		}
		
	}

}
