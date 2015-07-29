package net.sls.component.user.impl;

import java.util.List;
import java.util.Map;

import net.sls.component.user.IUserComponent;
import net.sls.dao.ext.user.CmsUserMapperExt;
import net.sls.dao.ext.user.RoleMapperExt;
import net.sls.dao.user.CmsUserMapper;
import net.sls.dao.user.RoleMapper;
import net.sls.dto.user.CmsUser;
import net.sls.dto.user.CmsUserExample;
import net.sls.dto.user.Role;
import net.sls.dto.user.RoleExample;
import net.sls.dto.user.RoleFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.framework.BusinessExceptionUtil;
import util.model.ComboxDto;
import framework.exception.BusinessException;
import framework.web.Pager;

@Component
public class UserComponent implements IUserComponent{
	
	@Autowired
	private CmsUserMapperExt cmsUserMapperExt;
	
	@Autowired
	private CmsUserMapper cmsUserMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private RoleMapperExt roleMapperExt;
	

	@Override
	public RoleFunction selectRoleFunctionByUrl(int roleId, String url) {
		return cmsUserMapperExt.selectRoleFunctionByUrl(roleId, url);
	}

	@Override
	public Pager<List<Map<String,Object>>> selectCmsUsers(String name, String mobile,
			String department, Integer roleId, Integer start, Integer number) {
		long count = cmsUserMapperExt.countCmsUsersByFilter(name, mobile, department, roleId);
		List<Map<String,Object>> list = cmsUserMapperExt.selectCmsUsersByFilter(name, mobile, department, roleId, start*number, number);
		Pager<List<Map<String,Object>>> pager = new Pager<List<Map<String,Object>>>(list,count);
		return pager;
	}

	@Override
	public CmsUser updateCmsUser(CmsUser cmsUser) {
		int i = cmsUserMapper.updateByPrimaryKeySelective(cmsUser);
		if(i != 1){
			throw new BusinessException(2L);
		}
		return cmsUser;
	}

	@Override
	public CmsUser insertCmsUser(CmsUser cmsUser) {
		int i = cmsUserMapper.insertSelective(cmsUser);
		if(i != 1){
			throw new BusinessException(1L);
		}
		return cmsUser;
	}


	@Override
	public Pager<List<Role>> selectRoles(String name, int start, int number) {
		RoleExample e = new RoleExample();
		if(name == null){
			e.createCriteria().andRoleNameLike("%%");
		}else{
			e.createCriteria().andRoleNameLike("%"+name+"%");
		}
		List<Role> list = roleMapper.selectByExample(e);
		long count = roleMapper.countByExample(e);
		return new Pager<List<Role>>(list,count);
	}

	@Override
	public Role insertRole(Role role) {
		int i = roleMapperExt.insertRoleReturnId(role);
		if(i!=1){
			throw new BusinessException(1L);
		}
		return role;
	}

	@Override
	public Role updateRole(Role role) {
		int i = roleMapper.updateByPrimaryKeySelective(role);
		if(i!=1){
			throw new BusinessException(2L);
		}
		return role;
	}

	@Override
	public int selectCmsUserIsExist(String name) {
		CmsUserExample e = new CmsUserExample();
		e.createCriteria().andUserNameEqualTo(name);
		return cmsUserMapper.countByExample(e);
	}

	@Override
	public CmsUser selectCmsUser(String username, String password) {
		CmsUserExample e = new CmsUserExample();
		e.createCriteria().andUserNameEqualTo(username).andPasswordEqualTo(password).andStateEqualTo(1);
		List<CmsUser> list = cmsUserMapper.selectByExample(e);
		if(list.size() > 1){
			throw BusinessExceptionUtil.createBusinessException(5L, username);
		}
		if(list.size() == 1){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<ComboxDto> selectRolesAll() {
		return roleMapperExt.selectRolesAll();
	}

	
}
