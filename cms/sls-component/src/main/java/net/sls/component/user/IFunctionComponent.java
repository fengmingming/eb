package net.sls.component.user;

import java.util.List;

import net.sls.dto.user.Function;
import framework.web.Pager;

public interface IFunctionComponent {
	
	/**
	 * @author wangguojun
	 * @description: 添加功能
	 * @param function void
	 * @date 2014年12月9日 下午2:08:56
	 */
	public void insertFunction(Function function); 
	
	/**
	 * @author wangguojun
	 * @description: 按父类节点id查询功能
	 * @param fatherId
	 * @return List<Function>
	 * @date 2014年12月9日 下午2:11:20
	 */
	public List<Function> selectFunctionByFatherId(Integer fatherId);
	
	/**
	 * @author wangguojun
	 * @description: 可选择条件的修改功能
	 * @param function void
	 * @date 2014年12月9日 下午2:14:30
	 */
	public void updateByPrimaryKeySelective(Function function);
	
	/**
	 * @author wangguojun
	 * @description: 删除功能
	 * @param id void
	 * @date 2014年12月9日 下午2:15:31
	 */
	public void deleteByPrimaryKey(Integer id);
	
	/**
	 * @author wangguojun
	 * @description: 可选择条件的查询功能并且分页
	 * @param fatherId
	 * @param url
	 * @param name
	 * @return Pager<List<Function>>
	 * @date 2014年12月9日 下午2:16:21
	 */
	public Pager<List<Function>> selectFunctionByFilter(Integer fatherId,String url,String name);

	/**
	 * @author wangguojun
	 * @description: 查询总条数
	 * @param function
	 * @return Integer
	 * @date 2014年12月9日 下午2:17:26
	 */
	public Integer countFunctionByFilter(Function function);
	
	public void insertBatchRoleFunction(int fid);
	
	public boolean selectIsLog(String url);
	
}
