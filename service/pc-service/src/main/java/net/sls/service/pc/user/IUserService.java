package net.sls.service.pc.user;

import java.util.List;

import net.sls.dto.pc.user.User;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IUserService {
	/**
	 *  普通会员注册时查询用户手机号是否存在
	 * @param mobile
	 * @return
	 */
	public ResBo<String> selectUserMobileIsExist(ReqBo reqBo);
	/**
	 * 普通会员注册时查询用户名是否存在
	 * @param userName
	 * @return
	 */
	public ResBo<String> selectUserNameIsExist(ReqBo reqBo);
	/**
	 *  普通会员注册或亭子服务人员注册
	 * @param reqBo
	 * @return
	 */
	public ResBo<User> register(ReqBo reqBo);
	/**
	 *  普通会员注册或亭子服务人员注册手机注册验证码
	 * @param reqBo
	 * @return
	 */
	public ResBo<String> registerMobileAuthCode(ReqBo reqBo);
	
	public ResBo<User> login(ReqBo reqBo);
	/**
	 * 支付密码设置
	 * @param reqBo
	 * @return
	 */
	public ResBo<User> updatePayPassword(ReqBo reqBo);
	/**
	 * 修改密码
	 * @param reqBo
	 * @return
	 */
	public ResBo<User> updatePassword(ReqBo reqBo);
	/**
	 * 修改User信息
	 * @param reqBo
	 * @return
	 */
	public ResBo<User> updateUser(ReqBo reqBo);
	/**
	 * 是否存在
	 * @param reqBo
	 * @return
	 */
	public ResBo<Integer> getPayPasswordIsExist(ReqBo reqBo);
	/**
	 * 获取User
	 * @param reqBo
	 * @return
	 */
	public ResBo<User> getUserByUserId(ReqBo reqBo);
	/**
	 * 根据手机号获取User
	 * @param reqBo
	 * @return
	 */
	public ResBo<User> getUserBymobile(ReqBo reqBo);
	/**
	 * 根据列名获取User
	 * @param reqBo
	 * @return
	 */
	public ResBo<User> getUserByCardNum(ReqBo reqBo);
	/**
	 * 根据亭子id和用户名字实现亭子给用户充值（转账）
	 * @param reqBo
	 * @return
	 */
	public ResBo<String> updateUserAmountByUser(ReqBo reqBo);
	
	/**
	 * 根据亭子用户亭子id，获取所有所属亭子的消费者
	 * @param reqbo
	 * @return
	 */
	public ResBo<Pager<List<User>>> getConsumerByPavilionId(ReqBo reqBo);
	
	/**
	 * 根据用户名或手机号查询用户信息
	 * @param reqbo
	 * @return
	 */
	public ResBo<List<User>> getConsumerByNameMobile(ReqBo reqBo);
}
