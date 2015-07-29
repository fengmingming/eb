package net.sls.component.user.impl;

import java.util.List;

import net.sls.component.user.IFunctionComponent;
import net.sls.dao.ext.user.FunctionMapperExt;
import net.sls.dao.user.FunctionMapper;
import net.sls.dto.user.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.exception.BusinessException;
import framework.web.Pager;

@Component
public class FunctionComponent implements IFunctionComponent{

	@Autowired
	public FunctionMapper functionMapper;
	
	@Autowired
	public FunctionMapperExt functionMapperExt;
	
	/*
	 * @author wangguojun
	 * @description: 添加功能
	 * @param function 
	 * @date 2014年12月9日 下午2:18:32
	 */
	@Override
	public void insertFunction(Function function) {
		
		int i = functionMapperExt.insertSelective(function);
		if(i != 1){
			throw new BusinessException(1L);
		}
	}

	/*
	 * @author wangguojun
	 * @description: 按父类节点id查询功能
	 * @param fatherId
	 * @return 
	 * @date 2014年12月9日 下午2:19:06
	 */
	@Override
	public List<Function> selectFunctionByFatherId(Integer fatherId) {
		
		return functionMapperExt.selectFunctionByPId(fatherId);
	}

	/*
	 * @author wangguojun
	 * @description: 可选择条件的修改功能
	 * @param function 
	 * @date 2014年12月9日 下午2:20:37
	 */
	@Override
	public void updateByPrimaryKeySelective(Function function) {
		
		int i = functionMapper.updateByPrimaryKeySelective(function);
		if(i != 1){
			throw new BusinessException(2L);
		}
	}

	/*
	 * @author wangguojun
	 * @description: 删除功能
	 * @param id 
	 * @date 2014年12月9日 下午2:22:52
	 */
	@Override
	public void deleteByPrimaryKey(Integer id) {
		
		int i = functionMapper.deleteByPrimaryKey(id);
		if(i != 1){
			throw new BusinessException(3L);
		}
		
	}

	/*
	 * @author wangguojun
	 * @description: 可选择条件的查询功能并且分页
	 * @param function
	 * @return 
	 * @date 2014年12月9日 下午2:23:01
	 */
	@Override
	public Integer countFunctionByFilter(Function function) {
		
		return functionMapperExt.countFunctionByFilter(function);
	}

	/*
	 * @author wangguojun
	 * @description: 查询总条数
	 * @param fatherId
	 * @param url
	 * @param name
	 * @param start
	 * @param number
	 * @return 
	 * @date 2014年12月9日 下午2:23:18
	 */
	@Override
	public Pager<List<Function>> selectFunctionByFilter(Integer fatherId,
			String url, String name) {
		Function function = new Function();
		function.setName(name);
		function.setFatherId(fatherId);
		function.setUrl(url);
		long count = functionMapperExt.countFunctionByFilter(function);
		List<Function> list = functionMapperExt.selectFunctionByFilter( fatherId, url, name);
		Pager<List<Function>> pager = new Pager<List<Function>>(list,count);
		return pager;
	}

	@Override
	public void insertBatchRoleFunction(int fid) {
		functionMapperExt.insertBatchRoleFun(fid);
	}

	@Override
	public boolean selectIsLog(String url) {
		Function f = functionMapperExt.selectFunctionByUrl(url);
		return f == null?false:f.getIsLog();
	}
	
}
