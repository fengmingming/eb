package net.sls.component.pc.user;

import java.util.List;

import framework.web.Pager;
import net.sls.dto.pc.user.UserAddress;

public interface IUserAddressComponent {
	/**
	 * 更新(非删除)默认地址
	 * @param userId
	 * @param isdel
	 * @param isdefault
	 * @return
	 */
	public void updateUserAddressByUserId(Long userId);
	/**
	 * 新增收货地址
	 * @param reqModel
	 * @return
	 */
	public UserAddress insertUserAddress(UserAddress userAddress);
	/**
	 * 更新地址
	 * @param userAddress
	 * @return
	 */
	public UserAddress updateUserAddress(UserAddress userAddress);
	/**
	 * 删除地址(逻辑删除)
	 * @param paramLong
	 * @return
	 */
	public int deleteUserAddressById(Long id);
	/**
	 * 查询收货人地址列表含分页
	 * @param paramLong  userId 用户主键
	 * @param paramInt  isDel  1
	 * @param paramInt  start
	 * @param paramInt2 number  
	 * @return
	 */
	public Pager<List<UserAddress>> selectUserAddressListsByUserId(
			Long userId,Integer start, Integer number);
	/**
	 * 获取UserAddress
	 * @param id
	 * @return UserAddress
	 */
	public UserAddress selectUserAddressLById(Long id);
	/**
	 * 设置默认UserAddress
	 * @param userId
	 * @return
	 */
	public void updateDefaultUserAddressByUserId(Long id);
	/**
	 * 默认UserAddress
	 * @param userId
	 * @return
	 */
	public UserAddress selectDefaultUserAddressLByUserId(Long paramLong);
	
	
}
