package net.sls.component.dict;

import java.util.List;

import util.model.ComboxDto;
import framework.web.Pager;
import net.sls.dto.product.Dict;

/**
 * @author 
 * 字典的组件接口
 *
 */
public interface IDictComponent {
	/**
	 * 新增字典信息
	 * @param dict
	 */
	public void insertDict(Dict dict);
	
	/**
	 * 删除一个字典信息
	 * @param id
	 */
	public void deleteDict(long id);
	
	/**
	 * 批量删除字典信息
	 * @param ids
	 */
	public void deleteDicts(List<Long> ids);
	
	/**
	 * 修改字典信息
	 * @param dict
	 */
	public void updateDict(Dict dict);
	
	/**
	 * 根据id查找字典信息
	 * @param id
	 */
	public Dict selectDict(long id);
	
	/**
	 * 根据name查找字典信息
	 * @param name
	 */
	public List<Dict> selectDict(String name);
	
	/**
	 * 
	 * 根据条件查询字典信息列表
	 * */
	public Pager<List<Dict>> selectDicts(String code,String name,Integer type,Integer start,Integer number);
	
	/**
	 * 
	 * 类型列表
	 * 
	 * */
	public List<ComboxDto> selectDictCombox();
	
	public List<ComboxDto> selectDictComboxByType(Integer type,String key);
	
}
