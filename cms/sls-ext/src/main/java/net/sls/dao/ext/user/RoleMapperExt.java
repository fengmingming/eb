package net.sls.dao.ext.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import util.model.ComboxDto;
import net.sls.dto.user.Role;
import net.sls.dto.user.RoleFunction;

public interface RoleMapperExt {
	
	public int insertRoleReturnId(Role role);
	
	/**
	 * 根据角色id和功能ids查询功能列表
	 * */
	public List<RoleFunction> selectRoleFunctionByIds(@Param("roleId") Integer roleId,@Param("ids") List<Integer> ids);
	
	public List<ComboxDto> selectRolesAll();
}
