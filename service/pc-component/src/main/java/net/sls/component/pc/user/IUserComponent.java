package net.sls.component.pc.user;

import java.math.BigDecimal;
import java.util.List;

import framework.web.Pager;
import net.sls.dto.pc.user.User;

public interface IUserComponent {
	/**
	 * 查询手机号是否存在
	 * @param mobile
	 * @return
	 */
	public int selectUserMobileIsExist(String mobile);
	/**
	 * 插入用户名
	 * @param trim
	 * @return
	 */
	public User insertUser(User u);
	/**
	 * 查询用户
	 * @param paramStr  mobile
	 * @param md5Str   password
	 * @return
	 */
	public User selectUserByMobileOrPas(String mobile, String password);
	/**
	 * 查询用户名是否存在
	 * @param userName
	 * @return
	 */
	public int selectUserNameIsExist(String userName);
	/**
	 * 修改支付密码
	 * @param paramLong
	 * @param paramStr
	 * @return
	 */
	public void updatePayPassword(Long id, String payPassword);
	/**
	 * 查询密码
	 * @param paramLong
	 * @param paramStr
	 * @return
	 */
	public User selectUserByPassword(Long id, String password);
	/**
	 * 
	 * @param paramLong
	 * @param paramStr
	 */
	public void updatePassword(Long paramLong, String paramStr);
	/**
	 * 修改User
	 * @param User
	 * @return user
	 */
	public User updateUser(User reqModel);
	
	public User selectUserById(long userId);
	/**
	 * 根据手机号获取User
	 * @param mobile
	 * @return
	 */
	public User selectUserByMobile(String mobile);
	
	/**
	 * 根据卡号获取User
	 * @param cardNum
	 * @return
	 */
	public User selectUserByCardNum(String cardNum);
	
	/**
	 * 亭子用户通过余额给普通用户充值
	 * @return
	 */
	public void updateUserAmountByUser(Long pid, Long id, BigDecimal money, BigDecimal amount, BigDecimal pamount);
	
	/**
	 * 通过亭子id查询所属亭子的所有用户
	 * @param pid
	 * @return
	 */
	public Pager<List<User>> getConsumerByPavilionId(Long pid, int page, int rows);
	
	/**
	 * 根据用户名或手机号进行查询
	 * @param nameMobile
	 * @return
	 */
	public List<User> getConsumerByNameMobile(String nameMobile, Long pid);
}
