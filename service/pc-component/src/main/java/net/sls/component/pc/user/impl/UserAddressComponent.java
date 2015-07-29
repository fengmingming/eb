package net.sls.component.pc.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.exception.BusinessException;
import framework.web.Pager;
import net.sls.component.pc.user.IUserAddressComponent;
import net.sls.dao.pc.user.PCUserAddressMapper;
import net.sls.dto.pc.user.UserAddress;
@Component
public class UserAddressComponent implements IUserAddressComponent{

	@Autowired
	private PCUserAddressMapper userAddressMapper;
	@Override
	public void updateUserAddressByUserId(Long userId) {
		int i=userAddressMapper.updateUserAddressByUserId(userId);
		if(i < 0){
			throw new BusinessException(3L);
		}
	}

	@Override
	public UserAddress insertUserAddress(UserAddress userAddress) {
		int i=userAddressMapper.insertUserAddress(userAddress);
		if(i != 1){
			throw new BusinessException(2L);
		}
		return userAddress;
	}

	@Override
	public UserAddress updateUserAddress(UserAddress userAddress) {
		int i=userAddressMapper.updateUserAddress(userAddress);
		if(i != 1){
			throw new BusinessException(4L);
		}
		return userAddress;
	}

	@Override
	public int deleteUserAddressById(Long id) {
		int i=userAddressMapper.deleteUserAddressById(id);
		return i;
	}

	@Override
	public Pager<List<UserAddress>> selectUserAddressListsByUserId(Long userId,
			Integer start, Integer number) {
		long count = userAddressMapper.countUserAddressByFilter(userId);
		List<UserAddress> list = userAddressMapper.selectUserAddressListsByUserId(userId, (start-1)*number, number);
		Pager<List<UserAddress>> pager = new Pager<List<UserAddress>>(list,count);
		return pager;
	}

	@Override
	public UserAddress selectUserAddressLById(Long id) {
		return userAddressMapper.selectUserAddressLById(id);
	}

	@Override
	public UserAddress selectDefaultUserAddressLByUserId(Long userId) {
		return userAddressMapper.selectDefaultUserAddressLByUserId(userId);
	}

	@Override
	public void updateDefaultUserAddressByUserId(Long id) {
		int i=userAddressMapper.updateDefaultUserAddressByUserId(id);
		if(i != 1){
			throw new BusinessException(20L);
		}
	}

}
