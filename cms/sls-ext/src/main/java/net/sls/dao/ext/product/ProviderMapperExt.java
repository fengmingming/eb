package net.sls.dao.ext.product;

import java.util.Date;
import java.util.List;

import net.sls.dto.product.Provider;

import org.apache.ibatis.annotations.Param;

import util.model.ComboxDto;

public interface ProviderMapperExt {
	public List<Provider> selectProvidersByFilter(@Param("providerName") String providerName,
			@Param("address") String address,@Param("tel") String tel,
			@Param("fax") String fax,@Param("contactName") String contactName,
			@Param("isVerify") String isVerify,
			@Param("areaCode")String areaCode,
			@Param("createTime1") Date createTime1,@Param("createTime2") Date createTime2,
			@Param("modifyTime1") Date modifyTime1,@Param("modifyTime2") Date modifyTime2,
			@Param("start") Integer start,@Param("number") Integer number);
	
	public long countProvidersByFilter(@Param("providerName") String providerName,
			@Param("address") String address,@Param("tel") String tel,
			@Param("fax") String fax,@Param("contactName") String contactName,
			@Param("isVerify") String isVerify,
			@Param("areaCode")String areaCode,
			@Param("createTime1") Date createTime1,@Param("createTime2") Date createTime2,
			@Param("modifyTime1") Date modifyTime1,@Param("modifyTime2") Date modifyTime2);

	public List<ComboxDto> selectProviderList(@Param("areaCode") String areaCode,@Param("name")String name);

	/**@author wangshaohui
	 * @Description: TODO 查询名字是否存在
	 * @param providerName
	 * @return int
	 * @date 2015年4月8日 上午11:38:57
	 */
	public int selectByName(@Param("providerName") String providerName);
}
