package net.sls.controller.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sls.businessconstant.BusinessContant;
import net.sls.service.user.IRoleService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import util.framework.StrUtil;
import util.model.ComboxDto;
import framework.web.ReqBo;
import framework.web.ResBo;

@Controller
@RequestMapping("role")
public class RoleController {
	
	@RequestMapping("getnodebypar.htm")
	@ResponseBody
	public ResBo<List<Map<String,Object>>> getFunTreeById(@RequestParam("fid") int pid,@RequestParam("rid") int roleId){
		Integer id = pid;
		ReqBo reqBo = new ReqBo();
		reqBo.setParam("id", id);
		reqBo.setParam("roleId", roleId);
		IRoleService roleService = FindServiceUtil.findService(IRoleService.class);
		return roleService.selectRoleFunByParId(reqBo);
	}
	
	@RequestMapping("updateRoleFun.htm")
	@ResponseBody
	public ResBo<?> updateRoleFun(HttpServletRequest req){
		IRoleService roleService = FindServiceUtil.findService(IRoleService.class);
		return roleService.updateRoleFun(new ReqBo(req));
	}
	
	@RequestMapping("rolenames.htm")
	@ResponseBody
	public List<ComboxDto> getRoleNames(){
		IRoleService roleService = FindServiceUtil.findService(IRoleService.class);
		return roleService.selectRoles(new ReqBo()).getResult();
	} 
	
	@RequestMapping("roleinit.htm")
	public String roleInit(){
		return "user/roleinit";
	}
	
	
	/**
	 * 返回指定角色对象的已选择的区域
	 * @param roleId
	 * @return
	 */
	@RequestMapping("rolearea.htm")
	@ResponseBody
	public ResBo<String> getAreaByRoleId(@RequestParam("rid") int roleId){
		ReqBo reqBo = new ReqBo();
		reqBo.setParam("roleId", roleId);
		IRoleService roleService = FindServiceUtil.findService(IRoleService.class);
		return roleService.selectRoleAreaById(reqBo);
	}
	
	/**
	 * 返回当前用户的管辖区域(从当前的session中直接返回)。
	 * Note : 当前的系统仅支持一个用户可分一个角色，一个用户不支持多个角色的实现 
	 * @return
	 */
	@RequestMapping("roarea.htm")
	@ResponseBody
	public ResBo<String> getAreaCodeByCurrentUsrId(){
		ResBo<String> rb =	new ResBo<String>();
		rb.setResult(StrUtil.toString(SessionUtil.get(BusinessContant.OPERAREAID)));
		return rb;
	}

	
	@SuppressWarnings("rawtypes")
	@RequestMapping("newarea.htm")
	@ResponseBody
	public ResBo updateRoleArea(@RequestParam("rid") int roleId,@RequestParam("codearea") String areaCode){
		ReqBo rb = new ReqBo();
		rb.setParam("roleId", roleId);
		rb.setParam("areacode", areaCode);
		IRoleService roleSvc = FindServiceUtil.findService(IRoleService.class);
		ResBo<?> resb = roleSvc.updateRoleAreaById(rb);
		return resb;
	}
	
	/**
	 * 返回当前用户在角色管理里选择的管辖区域。
	 * @return
	 */
	@RequestMapping("selectroarea.htm")
	@ResponseBody
	public ResBo<String> getAreaCodeBySelectId(@RequestParam("id") int roleId){
		ResBo<String> rb =	new ResBo<String>();
		ReqBo reqBo = new ReqBo();
		reqBo.setParam("roleId", roleId);
		IRoleService roleSvc = FindServiceUtil.findService(IRoleService.class);
		rb = roleSvc.selectRoleAreaById(reqBo);
		return rb;
	}
	
}
