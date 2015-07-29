package net.sls.component.product;

import java.util.Date;
import java.util.List;

import net.sls.dto.product.Provider;
import util.model.ComboxDto;
import framework.web.Pager;

/**
 * @author wsh 供应商的组件接口
 *
 */
public interface IProviderComponent {

	/**
	 * 新增供应商信息
	 * 
	 * @param Provider
	 */
	public void insertProvider(Provider Provider);

	/**
	 * 删除一个供应商信息
	 * 
	 * @param id
	 */
	public void deleteProvider(long id);

	/**
	 * 批量删除供应商信息
	 * 
	 * @param ids
	 */
	public void deleteProviders(List<Long> ids);

	/**
	 * 修改供应商信息
	 * 
	 * @param Provider
	 */
	public void updateProvider(Provider Provider);

	/**
	 * 根据id查找供应商信息
	 * 
	 * @param id
	 */
	public Provider selectByPrimaryKey(long id);

	/**
	 * 根据条件查询供应商信息列表
	 * @param providerName 供应商名字
	 * @param address 地址
	 * @param tel 联系方式
	 * @param fax 传真
	 * @param contactName 联系人名字
	 * @param isVerify 是否认证
	 * @param createTime1  创建范围起时间
	 * @param createTime2  创建范围始时间
	 * @param modifyTime1  修改范围起时间
	 * @param modifyTime2  修改范围始时间
	 * @param start 开始页
	 * @param number 一页放几条数据
	 * @return
	 */
	Pager<List<Provider>> selectProviders(String providerName, String address,
			String tel, String fax, String contactName, String isVerify,
			String areaCode, Date createTime1, Date createTime2,
			Date modifyTime1, Date modifyTime2, Integer start, Integer number);
	/**
	 * 查询供应商列表
	 * @param name 过滤条件
	 * @return
	 */
	public List<ComboxDto> selectProviderList(String areaCode,String name);

	/**@author wangshaohui
	 * @Description: TODO 查询供应商名字是否存在
	 * @param providerName
	 * @return boolean
	 * @date 2015年4月8日 上午11:35:10
	 */
	public boolean selectByName(String providerName);



}
