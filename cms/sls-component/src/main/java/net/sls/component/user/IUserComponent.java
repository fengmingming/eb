package net.sls.component.user;

import java.util.List;
import java.util.Map;

import util.model.ComboxDto;
import framework.web.Pager;
import net.sls.dto.user.CmsUser;
import net.sls.dto.user.Role;
import net.sls.dto.user.RoleFunction;

public interface IUserComponent {

	/**
	 * 
	 * 通过角色id和url判断角色是否访问url的权限
	 * 
	 * */
	public RoleFunction selectRoleFunctionByUrl(int roleId,String url);
	
	/**
	 * 
	 * 根据用户名密码查询cms用户信息
	 * 
	 * */
	public CmsUser selectCmsUser(String username,String password);
	
	
	/**
	 * 
	 * 根据条件查询cms用户信息列表
	 * */
	public Pager<List<Map<String,Object>>> selectCmsUsers(String name,String mobile,String deparment,Integer roleId,Integer start,Integer number);
	
	/**
	 * 
	 * 根据用户名查询同样用户名的数量
	 * 
	 * */
	public int selectCmsUserIsExist(String name);
	
	/**
	 * 
	 * 修改cms用户信息
	 * 
	 * */
	public CmsUser updateCmsUser(CmsUser cmsUser);
	
	/**
	 * 
	 * 新增cms用户信息
	 * 
	 * */
	public CmsUser insertCmsUser(CmsUser cmsUser);
	
	/**
	 * 
	 * 分页查询
	 * 
	 * */
	public Pager<List<Role>> selectRoles(String name,int start,int number);
	/**
	 * 
	 * 新增角色信息
	 * 
	 * */
	public Role insertRole(Role role);
	/**
	 * 
	 * 修改角色信息
	 * 
	 * */
	public Role updateRole(Role role);
	
	/**
	 * 
	 * 查询所有角色
	 * */
	public List<ComboxDto> selectRolesAll();
	
}
