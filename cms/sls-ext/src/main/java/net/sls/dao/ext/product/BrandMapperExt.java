package net.sls.dao.ext.product;

import java.util.Date;
import java.util.List;

import net.sls.dto.product.Brand;

import org.apache.ibatis.annotations.Param;

import util.model.ComboxDto;

public interface BrandMapperExt {
    
	public List<Brand> selectBySelective(@Param("id")Long id, @Param("name")String name, @Param("enName")String enName, 
			@Param("description")String description, 
			@Param("spell")String spell, @Param("keyword")String keys,
			@Param("url")String url, 
			@Param("img")String img, @Param("createTime")Date createTime, 
			@Param("start") Integer start, @Param("number") Integer number);
	
	public Long countBySelective(@Param("id")Long id, @Param("name")String name, @Param("enName")String enName, 
			@Param("description")String description, 
			@Param("spell")String spell, @Param("keyword")String keys,
			@Param("url")String url, 
			@Param("img")String img, @Param("createTime")Date createTime);
	
	public List<ComboxDto> selectBrandCombobox();
	
	/**@author wangshaohui
	 * @Description: TODO 品牌名的数量 
	 * @param name
	 * @return int
	 * @date 2015年1月23日 上午10:52:56
	 */
	public int countByName(String name);
	
}