package net.sls.service.pc.user;

import java.util.List;

import net.sls.dto.pc.user.UserAddress;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IUserAddressService {
	/**
	 * 新增收货地址isdel 是 1
	 * @param reqBo
	 * @return
	 */
	public ResBo<UserAddress>  insertUserAddress(ReqBo reqBo);
	/**
	 * 获取收货信息 isdel 是 1
	 * @param reqBo
	 * @return
	 */
	public ResBo<UserAddress>  getUserAddressById(ReqBo reqBo);
	/**
	 * 获取收货默认地址 isdel 是 1
	 * @param reqBo
	 * @return
	 */
	public ResBo<UserAddress>  getDefaultUserAddressByUserId(ReqBo reqBo);
	/**
	 * 修改收货信息 isdel 是 1
	 * @param reqBo
	 * @return
	 */
	public ResBo<UserAddress>  updateUserAddress(ReqBo reqBo);
	/**
	 * 删除地址
	 * @param reqBo
	 * @return
	 */
	public ResBo<Object>  deleteUserAddressById(ReqBo reqBo);
	/**
	 * 收货地址列表含分页
	 * @param reqBo
	 * @return
	 */
	public ResBo<Pager<List<UserAddress>>>  selectUserAddressListsByUserId(ReqBo reqBo);
	/**
	 * 设置默认收货地址
	 * @param reqBo
	 * @return
	 */
	public ResBo<UserAddress> updateDefaultUserAddress(ReqBo reqBo);
}
