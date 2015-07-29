package net.sls.component.pc.user.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.sls.component.pc.user.IUserComponent;
import net.sls.dao.pc.user.PCAmountLogMapper;
import net.sls.dao.pc.user.PCUserMapper;
import net.sls.dto.pc.user.AmountLog;
import net.sls.dto.pc.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.framework.BusinessExceptionUtil;
import framework.exception.BusinessException;
import framework.web.Pager;
@Component
public class UserComponent implements IUserComponent{
	
	@Autowired
	private PCUserMapper userMapper;
	@Autowired
	private PCAmountLogMapper amountLogMapper;
	@Override
	public int selectUserMobileIsExist(String mobile) {
		return userMapper.countUserByMobile(mobile);
	}
	
	@Override
	public int selectUserNameIsExist(String userName) {
		return userMapper.countUserByUserName(userName);
	}

	@Override
	public User insertUser(User user) {
		int i = userMapper.insertUser(user);
		if(i != 1){
			throw new BusinessException(1L);
		}	
		return user;
	}

	@Override
	public User selectUserByMobileOrPas(String mobile, String password) {
		List<User> list = userMapper.selectUserByMobileOrPas(mobile,password);
		if(list.size() > 1){
			throw BusinessExceptionUtil.createBusinessException(7L, mobile);
		}
		if(list.size() == 1){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void updatePayPassword(Long id, String payPassword) {
		int i= userMapper.updatePayPassword(id,payPassword);
		if (i !=1) {
			throw new BusinessException(19L);
		}
	}

	@Override
	public User selectUserByPassword(Long id, String password) {
		return userMapper.selectUserByPassword(id,password);
	}

	@Override
	public void updatePassword(Long id, String password) {
		int i= userMapper.updatePassword(id,password);
		if (i !=1) {
			throw new BusinessException(22L);
		}
	}

	@Override
	public User updateUser(User user) {
		int i = userMapper.updateUser(user);
		if(i != 1){
			throw new BusinessException(24L);
		}
		return userMapper.selectUserByid(user.getId());
	}

	@Override
	public User selectUserById(long userId) {
		return userMapper.selectUserByid(userId);
	}

	@Override
	public User selectUserByMobile(String mobile) {
		return userMapper.selectUserByMobile(mobile);
	}
	
	@Override
	public User selectUserByCardNum(String cardNum) {
		return userMapper.selectUserByColumn("bindCardNum",cardNum);
	}

	@Override
	public void updateUserAmountByUser(Long pid, Long id,
			BigDecimal money, BigDecimal amount, BigDecimal pamount) {
		// TODO Auto-generated method stub
		int i = userMapper.updateUserBalance(pid,money);
		if(i != 1){
			throw new BusinessException(24L);
		}else{
			User user = userMapper.selectUserByid(pid);			
			AmountLog amountLog = new AmountLog();
			amountLog.setCreateTime(new Date());
			amountLog.setType(2);		
			amountLog.setCurAmount(user.getAmount().subtract(money));
			amountLog.setMoney(money);
			amountLog.setOperator(pid);
			amountLog.setRemark(pid+"亭子为"+id+"用户充值");
			amountLog.setUserId(pid);		
			int j = amountLogMapper.insertAmountLog(amountLog);
			if(j != 1){				
				throw new BusinessException(38L);
			}else{
				int k = userMapper.updateUserAmount(id,amount,money);
				if(k != 1){
					throw new BusinessException(24L);
				}else{
					amountLog.setCreateTime(new Date());
					amountLog.setType(1);		
					amountLog.setCurAmount(amount.add(money));
					amountLog.setMoney(money);
					amountLog.setOperator(pid);
					amountLog.setRemark(pid+"亭子为"+id+"用户充值");
					amountLog.setUserId(id);		
					int l = amountLogMapper.insertAmountLog(amountLog);
					if(l != 1){				
						throw new BusinessException(38L);
					}
				}
			}
			
		}
	}

	@Override
	public Pager<List<User>> getConsumerByPavilionId(Long pid, int page, int rows) {
		Pager<List<User>> pager = new Pager<List<User>>();
		pager.setTotal(userMapper.countConsumerByPavilionId(pid));
		pager.setEntry(userMapper.getConsumerByPavilionId(pid, (page-1)*rows, rows));
		return pager;
	}

	@Override
	public List<User> getConsumerByNameMobile(String nameMobile, Long pid) {
		List<User> list = userMapper.getConsumerByNameMobile(nameMobile, pid);
		if(list.size() == 1){
			return list;
		}
		return null;
	}
}
