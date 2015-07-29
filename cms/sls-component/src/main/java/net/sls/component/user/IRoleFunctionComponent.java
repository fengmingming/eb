package net.sls.component.user;

import java.util.List;

import net.sls.dto.user.RoleFunction;

public interface IRoleFunctionComponent {

	/**
	 * 
	 * 根据方法id列表和角色id查找加入权限管理的功能
	 * @param ids:List 方法id列表 roleId:Integer 角色id
	 * @return RoleFunction 
	 * */
	public List<RoleFunction> selectRoleFunctionByIds(Integer roleId,List<Integer> ids);
	
	
	/**
	 * 
	 * 根据功能id角色id新增角色关系
	 * @param fid:int 功能id rid:int 角色id
	 * */
	public void insertRoleFun(RoleFunction rf);
	
	/**
	 * 根据功能id角色id修改功能状态
	 * @param fid:int 功能id rid:int 角色id
	 * */
	public void updateRoleFun(RoleFunction rf);
}
