package net.sls.dao.pc.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.sls.dto.pc.user.UserAddress;

public interface PCUserAddressMapper {

	int insertUserAddress(UserAddress userAddress);

	int updateUserAddressByUserId(@Param("userId") Long userId);

	int updateUserAddress(UserAddress userAddress);

	int deleteUserAddressById(@Param("id") Long id);

	long countUserAddressByFilter(@Param("userId") Long userId);

	List<UserAddress> selectUserAddressListsByUserId(@Param("userId") Long userId,@Param("start") Integer start,@Param("number") Integer number);

	UserAddress selectUserAddressLById(@Param("id") Long id);

	UserAddress selectDefaultUserAddressLByUserId(@Param("userId") Long userId);
	
	int updateDefaultUserAddressByUserId(@Param("id") Long id);
	
}
