package net.sls.service.impl.pc.user;

import java.util.Date;
import java.util.List;

import net.sls.component.pc.product.IAreaComponent;
import net.sls.component.pc.user.IUserComponent;
import net.sls.dto.pc.user.User;
import net.sls.service.pc.user.IUserService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Service("userService")
public class UserService implements IUserService{
	
	@Autowired
	private IUserComponent userComponent;
	@Autowired
	private IAreaComponent areaComponent;
	@Override
	public ResBo<User> register(ReqBo reqBo) {
		ResBo<User> resBo = new ResBo<User>();
		User u = reqBo.getReqModel(User.class);
		u.setCreateTime(new Date());
		if(u.getPavilionId() != null){
			u.setPavilionCode(areaComponent.selectByPrimaryKey(u.getPavilionId()).getCode());
		}
		userComponent.insertUser(u);
		u=userComponent.selectUserById(u.getId());
		resBo.setResult(u);
		return resBo;
	}
	
	@Override
	public ResBo<String> selectUserMobileIsExist(ReqBo reqBo) {
		ResBo<String> resBo = new ResBo<String>();
		int count = userComponent.selectUserMobileIsExist(reqBo.getParamStr("mobile"));
		if(count > 0 ){
			return new ResBo<String>(1,reqBo.getParamStr("mobile"));
		}
		resBo.setResult(reqBo.getParamStr("mobile"));
		return resBo;
	}
	
	@Override
	public ResBo<String> selectUserNameIsExist(ReqBo reqBo) {
		ResBo<String> resBo = new ResBo<String>();
		int countName = userComponent.selectUserNameIsExist(reqBo.getParamStr("userName"));
		if(countName > 0 ){
			return new ResBo<String>(16,reqBo.getParamStr("userName"));
		}
		resBo.setResult(reqBo.getParamStr("userName"));
		return resBo;
	}
	@Override
	public ResBo<String> registerMobileAuthCode(ReqBo reqBo) {
		ResBo<String> resBo = new ResBo<String>();
		int count = userComponent.selectUserMobileIsExist(reqBo.getParamStr("mobile"));
		if(count > 0){
			return new ResBo<String>(1,reqBo.getParamStr("mobile"));
		}
		resBo.setResult(reqBo.getParamStr("mobile"));
		return resBo;
	}

	@Override
	public ResBo<User> login(ReqBo reqBo) {
		ResBo<User> resBo = new ResBo<User>();
		User u=userComponent.selectUserByMobileOrPas(reqBo.getParamStr("mobile"), reqBo.getParamStr("password"));
		if(u == null){
			return new ResBo<User>(6);
		}
		resBo.setResult(u);
		return resBo;
	}
	
	@Override
	public ResBo<User> updatePayPassword(ReqBo reqBo) {
		userComponent.updatePayPassword(reqBo.getParamLong("id"), reqBo.getParamStr("payPassword"));
		return new ResBo<User>();
	}
	@Override
	public ResBo<User> updatePassword(ReqBo reqBo){
		User u=userComponent.selectUserByPassword(reqBo.getParamLong("id"), reqBo.getParamStr("oldPassword"));
		if (u == null) {
			return new ResBo<User>(21);
		}
		userComponent.updatePassword(reqBo.getParamLong("id"), reqBo.getParamStr("password"));
		return new ResBo<User>();
	}
	@Override
	public ResBo<User> updateUser(ReqBo reqBo){
		ResBo<User> resBo = new ResBo<User>();
		User u=userComponent.updateUser(reqBo.getReqModel(User.class));
		resBo.setResult(u);
		return resBo;
	}
	@Override
	public ResBo<Integer> getPayPasswordIsExist(ReqBo reqBo){
		ResBo<Integer> resBo = new ResBo<Integer>();
		User u=userComponent.selectUserById(reqBo.getParamLong("id"));
		if (StringUtils.isBlank(u.getPayPassword())) {
			resBo.setResult(0);
		}else{
			resBo.setResult(1);
		}
		return resBo;
	}

	@Override
	public ResBo<User> getUserByUserId(ReqBo reqBo) {
		ResBo<User> resBo = new ResBo<User>();
		User u=userComponent.selectUserById(reqBo.getParamLong("userId"));
		resBo.setResult(u);
		return resBo;
	}
	
	@Override
	public ResBo<User> getUserBymobile(ReqBo reqBo) {
		ResBo<User> resBo = new ResBo<User>();
		User u=userComponent.selectUserByMobile(reqBo.getParamStr("mobile"));
		resBo.setResult(u);
		return resBo;
	}

	@Override
	public ResBo<User> getUserByCardNum(ReqBo reqBo) {
		// TODO Auto-generated method stub
		ResBo<User> resBo = new ResBo<User>();
		User u = userComponent.selectUserByCardNum(reqBo.getParamStr("cardNum"));
		resBo.setResult(u);
		return resBo;
	}

	@Override
	public ResBo<String> updateUserAmountByUser(ReqBo reqBo) {
		// TODO Auto-generated method stub
		ResBo<String> resBo = new ResBo<String>();
		userComponent.updateUserAmountByUser(reqBo.getParamLong("userId"), reqBo.getParamLong("id"), reqBo.getParamDecimal("money"),  reqBo.getParamDecimal("amount"),  reqBo.getParamDecimal("pamount"));
		resBo.setResult(reqBo.getParamDecimal("money").toString());
		return resBo;
	}

	@Override
	public ResBo<Pager<List<User>>> getConsumerByPavilionId(ReqBo reqBo) {
		ResBo<Pager<List<User>>> resBo = new ResBo<Pager<List<User>>>();
		Pager<List<User>> pager = userComponent.getConsumerByPavilionId(reqBo.getParamLong("pid"), reqBo.getParamInt("page"), reqBo.getParamInt("rows"));
		resBo.setResult(pager);
		return resBo;
	}

	@Override
	public ResBo<List<User>> getConsumerByNameMobile(ReqBo reqBo) {
		ResBo<List<User>> resBo = new ResBo<List<User>>();
		resBo.setResult(userComponent.getConsumerByNameMobile(reqBo.getParamStr("nameMobile"), reqBo.getParamLong("pid")));
		return resBo;
	}
}
