package net.sls.service.user;

import java.util.List;
import java.util.Map;

import net.sls.dto.user.Role;
import util.model.ComboxDto;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IRoleService {

	/**
	 * 
	 * 根据父节点id查询子节点功能中加入权限的id列表
	 * @param id:integer 父节点id roleId:Integer 角色id
	 * @return id:integer 功能id name:String 功能名称	 isUse:boolean 是否启用    isLeaf:boolean是否是叶子节点
	 * */
	
	public ResBo<List<Map<String,Object>>> selectRoleFunByParId(ReqBo reqBo);
	
	/**
	 * 
	 *
	 * 
	 * 根据功能id角色id新增角色关系
	 * @param fid:int 功能id rid:int 角色id isUse:boolean 是否使用
	 * 
	 * 
	 * */
	public ResBo<Boolean> updateRoleFun(ReqBo reqBo);
	
	/**
	 * 
	 * 查询所有角色名称
	 * 
	 * */
	public ResBo<List<ComboxDto>> selectRoles(ReqBo reqBo);
	
	public ResBo<Role> selectRoleById(ReqBo reqBo);

	// 返回指定roleId的角色可管理的区域
	public ResBo<String> selectRoleAreaById(ReqBo reqBo);
	
	// update the region of the specified roleId
	public ResBo<?> updateRoleAreaById(ReqBo rb);
}
