package net.sls.component.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.exception.BusinessException;
import net.sls.component.user.IRoleFunctionComponent;
import net.sls.dao.ext.user.RoleMapperExt;
import net.sls.dao.user.RoleFunctionMapper;
import net.sls.dto.user.RoleFunction;

@Component
public class RoleFunctionComponent implements IRoleFunctionComponent{

	@Autowired
	private RoleMapperExt roleMapperExt;
	
	@Autowired
	private RoleFunctionMapper roleFuntionMapper;
	
	@Override
	public List<RoleFunction> selectRoleFunctionByIds(Integer roleId,
			List<Integer> ids) {
		return roleMapperExt.selectRoleFunctionByIds(roleId, ids);
	}

	@Override
	public void insertRoleFun(RoleFunction rf) {
		int i = roleFuntionMapper.insertSelective(rf);
		if(i != 1){
			throw new BusinessException(2L);
		}
	}

	@Override
	public void updateRoleFun(RoleFunction rf) {
		int i = roleFuntionMapper.updateByPrimaryKeySelective(rf);
		if(i != 1){
			throw new BusinessException(2L);
		}
	}
}
