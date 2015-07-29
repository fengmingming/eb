package net.sls.service.user;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;

import net.sls.dto.user.CmsUser;
import net.sls.dto.user.Function;
import net.sls.dto.user.Role;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IUserService {
	
	/**
	 * 
	 * 根据用户名密码查询用cms户信息
	 * 
	 * */
	public ResBo<CmsUser> selectCmsUserByNameOrPas(ReqBo reqBo);
	
	/**
	 * 
	 * 判断用户是否具有某个权限
	 * @param userId 用户id url 相对url
	 * 
	 * */
	//@Cacheable("cache60")
	 public ResBo<Boolean> isAuth(ReqBo reqBo);
	
	/**
	 * 查询cmsuser用户信息
	 * 
	 * */
	
	public ResBo<Pager<List<Map<String,Object>>>> selectCmsUsers(ReqBo reqBo);
	
	/**
	 * 
	 * 新增cms用户
	 * @param cmsuser cms用户信息
	 * 
	 * */
	 public ResBo<CmsUser> insertCmsUser(ReqBo reqBo);
	 
	 /**
	  * 
	  * 修改cms用户信息
	  * @param cmsuser 要修改的cms用户信息
	  * @return cmsuser 修改后的cms用户信息
	  * */
	 public ResBo<CmsUser> updateCmsUser(ReqBo reqBo);
	 
	 /**
	  * 修改cmsuser用户密码
	  * 
	  * */
	 public ResBo<?> updateCmsUserPass(ReqBo reqBo); 
	 
	 /**
	  * 
	  * 查询功能信息列表
	  * 
	  * */
	 public ResBo<List<Function>> selectFunctions(ReqBo reqBo);
	 
	 /**
	  * 
	  * 新增功能
	  * @param Function 功能信息
	  * @return Function 功能信息
	  * */
	 public ResBo<Function> insertFunction(ReqBo reqBo);
	 
	 /**
	  * 
	  * 修改功能
	  * @param Function 要修改的function信息
	  * @return Function 修改后的function
	  * 
	  * */
	 public ResBo<Function> updateFunction(ReqBo reqBo);
	 
	 /**
	  * 
	  * 查询角色信息列表
	  */
	 public ResBo<Pager<List<Role>>> selectRoles(ReqBo reqBo);
	 
	 /**
	  * 
	  * 新增角色
	  * @param Role 角色信息
	  * @return Role 新增后的角色信息
	  * */
	 public ResBo<Role> insertRole(ReqBo reqBo);
	 
	 /**
	  * 
	  * 修改角色
	  * @param reqBo 要修改的字段
	  * @return role 修改后的角色信息
	  * */
	 public ResBo<?> updateRole(ReqBo reqBo);
	 
	 /**
	  * 
	  * 修改角色功能
	  * @param roleId 角色id List<functionId> 改变的功能id列表
	  * 
	  * */
	 public ResBo<?> updateRoleFunction(ReqBo reqBo);
	 
}
