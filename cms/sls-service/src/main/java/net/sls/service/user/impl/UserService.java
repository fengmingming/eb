package net.sls.service.user.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.framework.MD5Util;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;
import net.sls.component.user.IFunctionComponent;
import net.sls.component.user.IUserComponent;
import net.sls.dto.user.CmsUser;
import net.sls.dto.user.Function;
import net.sls.dto.user.Role;
import net.sls.dto.user.RoleFunction;
import net.sls.service.user.IUserService;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private IUserComponent userComponent;
	
	@Autowired
	private IFunctionComponent functionCom;
	
	@Override
	public ResBo<Boolean> isAuth(ReqBo reqBo) {
		String url = reqBo.getParamStr("url");
		String[] ss = url.split("/");
		if(ss.length == 4){
			StringBuilder sb = new StringBuilder("/");
			sb.append(ss[2]);
			sb.append("/");
			sb.append(ss[3]);
			url = sb.toString();
		}
		RoleFunction rf = userComponent.selectRoleFunctionByUrl(reqBo.getParamInt("roleId"), url);
		boolean result = false;
		if(rf != null&&rf.getIsUse()){
			result = true;
		}
		if(rf == null){
			result = !functionCom.selectIsLog(url);
		}
		return new ResBo<Boolean>(result);
	}

	@Override
	public ResBo<CmsUser> insertCmsUser(ReqBo reqBo) {
		CmsUser cu = reqBo.getReqModel(CmsUser.class);
		int count = userComponent.selectCmsUserIsExist(cu.getUserName().trim());
		if(count > 0){
			return new ResBo<CmsUser>(4,cu.getUserName());
		}
		userComponent.insertCmsUser(cu);
		return new ResBo<CmsUser>();
	}

	@Override
	public ResBo<CmsUser> updateCmsUser(ReqBo reqBo) {
		userComponent.updateCmsUser(reqBo.getReqModel(CmsUser.class));
		return new ResBo<CmsUser>();
	}
	
	@Override
	public ResBo<?> updateCmsUserPass(ReqBo reqBo){
		CmsUser cu = new CmsUser();
		cu.setId(reqBo.getParamLong("id"));
		cu.setPassword(reqBo.getParamStr("pass"));
		userComponent.updateCmsUser(cu);
		return new ResBo<Object>();
	}

	@Override
	public ResBo<Function> insertFunction(ReqBo reqBo) {
		
		return null;
	}

	@Override
	public ResBo<Function> updateFunction(ReqBo reqBo) {
		
		return null;
	}

	@Override
	public ResBo<Role> insertRole(ReqBo reqBo) {
		Pager<List<Role>> pager = userComponent.selectRoles(reqBo.getReqModel(Role.class).getRoleName(), 1, Integer.MAX_VALUE);
		if(pager.getTotal() > 0){
			return new ResBo<Role>(12L);
		}
		Role role = userComponent.insertRole(reqBo.getReqModel(Role.class));
		return new ResBo<Role>(role);
	}

	@Override
	public ResBo<?> updateRole(ReqBo reqBo) {
		userComponent.updateRole(reqBo.getReqModel(Role.class));
		return new ResBo<Object>();
	}

	@Override
	public ResBo<?> updateRoleFunction(ReqBo reqBo) {
		
		return null;
	}

	@Override
	public ResBo<Pager<List<Map<String,Object>>>> selectCmsUsers(ReqBo reqBo) {
		return new ResBo<Pager<List<Map<String,Object>>>>(userComponent.selectCmsUsers(reqBo.getParamStr("name"), reqBo.getParamStr("mobile"), reqBo.getParamStr("department"), reqBo.getParamInt("roleId"), reqBo.getParamInt("page")-1, reqBo.getParamInt("rows")));
	}

	@Override
	public ResBo<List<Function>> selectFunctions(ReqBo reqBo) {
		
		return null;
	}

	@Override
	public ResBo<Pager<List<Role>>> selectRoles(ReqBo reqBo) {
		return new ResBo<Pager<List<Role>>>(userComponent.selectRoles(reqBo.getParamStr("roleName"), reqBo.getParamInt("start")-1, reqBo.getParamInt("number")));
	}

	@Override
	public ResBo<CmsUser> selectCmsUserByNameOrPas(ReqBo reqBo) {
		CmsUser cu = userComponent.selectCmsUser(reqBo.getParamStr("username"), MD5Util.getMD5Str(reqBo.getParamStr("password")));
		if(cu == null){
			return new ResBo<CmsUser>(6);
		}
		return new ResBo<CmsUser>(cu);
	}

}
