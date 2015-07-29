package net.sls.service.user.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.model.ComboxDto;
import framework.exception.BusinessException;
import framework.web.ReqBo;
import framework.web.ResBo;
import net.sls.component.user.IFunctionComponent;
import net.sls.component.user.IRoleComponent;
import net.sls.component.user.IRoleFunctionComponent;
import net.sls.component.user.IUserComponent;
import net.sls.dto.user.Function;
import net.sls.dto.user.Role;
import net.sls.dto.user.RoleFunction;
import net.sls.service.user.IRoleService;

@Service
public class RoleService implements IRoleService{

	@Autowired
	private IFunctionComponent functionComponent;
	
	@Autowired
	private IRoleFunctionComponent roleFunctionComponent;
	
	@Autowired
	private IUserComponent userComponent;
	
	@Autowired
	private IRoleComponent roleComponent;

	@Override
	public ResBo<List<Map<String, Object>>> selectRoleFunByParId(ReqBo reqBo) {
		List<Function> list = functionComponent.selectFunctionByFatherId(reqBo.getParamInt("id"));
		List<Integer> idList = new ArrayList<Integer>(list.size());
		for(Function f : list){
			idList.add(f.getId());
		}
		List<RoleFunction> rfs = roleFunctionComponent.selectRoleFunctionByIds(reqBo.getParamInt("roleId"), idList.size() == 0?null:idList);
		Map<Integer,RoleFunction> rfsMap = new HashMap<Integer,RoleFunction>();
		for(RoleFunction rf:rfs){
			rfsMap.put(rf.getFunctionId(), rf);
		}
		List<Map<String,Object>> rts = new ArrayList<Map<String,Object>>(list.size());
		Map<String,Object> map = null;
		for(Function f : list){
			map = new HashMap<String,Object>();
			map.put("id", f.getId());
			map.put("name", f.getName());
			map.put("isUse", rfsMap.get(f.getId())==null?!f.getIsLog():rfsMap.get(f.getId()).getIsUse());
			map.put("isLeaf", f.getIsLeaf());
			rts.add(map);
		}
		return new ResBo<List<Map<String, Object>>>(rts);
	}

	@Override
	public ResBo<Boolean> updateRoleFun(ReqBo reqBo) {
		int rid = reqBo.getParamInt("rid");
		int fid = reqBo.getParamInt("fid");
		List<Integer> list = new ArrayList<Integer>();
		list.add(fid);
		List<RoleFunction> rlist = roleFunctionComponent.selectRoleFunctionByIds(rid, list);
		if(rlist.size() == 0){//不存在就新增，并且修改状态
			RoleFunction rf = new RoleFunction();
			rf.setFunctionId(fid);
			rf.setRoleId(rid);
			rf.setCreateTime(new Date());
			rf.setIsUse(reqBo.getParamBoolean("isUse"));
			roleFunctionComponent.insertRoleFun(rf);
		}else if(rlist.size() == 1){
			RoleFunction rf = rlist.get(0);
			rf.setModifyTime(new Date());
			rf.setIsUse(reqBo.getParamBoolean("isUse"));
			roleFunctionComponent.updateRoleFun(rf);
		}else{
			throw new BusinessException(13L, rid,fid);
		}
		return new ResBo<Boolean>();
	}

	@Override
	public ResBo<List<ComboxDto>> selectRoles(ReqBo reqBo) {
		return new ResBo<List<ComboxDto>>(userComponent.selectRolesAll());
	}

	@Override
	public ResBo<Role> selectRoleById(ReqBo reqBo) {
		return new ResBo<Role>(roleComponent.selectRoleById(reqBo.getParamInt("id")));
	}

	
	/**
	 * 返回指定roleId的管辖区哉的编码
	 */
	@Override
	public ResBo<String> selectRoleAreaById(ReqBo reqBo) {
		int rid = reqBo.getParamInt("roleId");
		String rst = getRoleAreaCodeById(rid);
		ResBo<String> rb = new ResBo<String>();
		if(rst != null){
			rb.setResult(rst);
			return rb;
		}
		else{
			return rb;
		}
	}
	
	
	

	@Override
	public ResBo<Integer> updateRoleAreaById(ReqBo reqBo) {
		int rid = reqBo.getParamInt("roleId");
		String areaCode = reqBo.getParamStr("areacode");
		Role r = new Role();
		r.setId(rid);
		r.setAreaCode(areaCode==null?"":areaCode);
		int i = roleComponent.updateRole(r);
		ResBo<Integer> rb = new ResBo<Integer>();
		rb.setResult(i);
		return rb;
	}
	
	private String getRoleAreaCodeById(int roleId){
		Role r = roleComponent.selectRoleById(roleId);
		if(r != null && r.getAreaCode() != null && r.getAreaCode().length()>0){
			return r.getAreaCode();
		}
		else{
			return null;
		}
	}

}
