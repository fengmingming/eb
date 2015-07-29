package net.sls.component.user.impl;

import net.sls.component.user.IRoleComponent;
import net.sls.dao.user.RoleMapper;
import net.sls.dto.user.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleComponent implements IRoleComponent{

	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public Role selectRoleById(int roleId) {
		return roleMapper.selectByPrimaryKey(roleId);
	}
	
	
	public int updateRole(Role r){
		return roleMapper.updateByPrimaryKeySelective(r);		
	}
}
