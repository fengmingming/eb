package net.sls.component.product;

import java.util.Date;
import java.util.List;

import net.sls.dto.product.Brand;
import util.model.ComboxDto;
import framework.web.Pager;

public interface IBrandComponent {

	public void insertSelective(Brand record);
	
	public void updateByPrimaryKeySelective(Brand record);
	
	public Pager<List<Brand>> selectBySelective(Long id, String name, String enName, String description, 
			String spell, String keyword, String url, String img, Date createTime, 
			Integer start, Integer number);
	
	public List<ComboxDto> selectBrandCombobox();
	
	/**@author wangshaohui
	 * @Description: TODO 查看品牌名是否存在
	 * @param name
	 * @return boolean
	 * @date 2015年1月23日 上午11:07:43
	 */
	public boolean selectNameIsExist(String name);
}
