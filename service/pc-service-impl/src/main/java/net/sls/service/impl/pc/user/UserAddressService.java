package net.sls.service.impl.pc.user;

import java.util.List;

import net.sls.component.pc.user.IUserAddressComponent;
import net.sls.dto.pc.user.UserAddress;
import net.sls.service.pc.user.IUserAddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;
@Service("userAddressService")
public class UserAddressService implements IUserAddressService{
	@Autowired
	private IUserAddressComponent userAddressComponent;

	@Override
	public ResBo<UserAddress> insertUserAddress(ReqBo reqBo) {
		ResBo<UserAddress> resBo = new ResBo<UserAddress>();
		if (Boolean.TRUE.equals(reqBo.getReqModel(UserAddress.class).getIsdefault())) {
			userAddressComponent.updateUserAddressByUserId(reqBo.getReqModel(UserAddress.class).getUserId());
		}
		userAddressComponent.insertUserAddress(reqBo.getReqModel(UserAddress.class));
		resBo.setResult(reqBo.getReqModel(UserAddress.class));
		return resBo;
	}

	@Override
	public ResBo<UserAddress> updateUserAddress(ReqBo reqBo) {
		ResBo<UserAddress> resBo = new ResBo<UserAddress>();
		if (Boolean.TRUE.equals(reqBo.getReqModel(UserAddress.class).getIsdefault())) {
			userAddressComponent.updateUserAddressByUserId(reqBo.getReqModel(UserAddress.class).getUserId());
		}
		userAddressComponent.updateUserAddress(reqBo.getReqModel(UserAddress.class));
		resBo.setResult(reqBo.getReqModel(UserAddress.class));
		return resBo;
	}

	@Override
	public ResBo<Object> deleteUserAddressById(ReqBo reqBo) {
		userAddressComponent.deleteUserAddressById(reqBo.getParamLong("id"));
		return new ResBo<Object>();
	}

	@Override
	public ResBo<Pager<List<UserAddress>>> selectUserAddressListsByUserId(
			ReqBo reqBo) {
		return new ResBo<Pager<List<UserAddress>>>(userAddressComponent.selectUserAddressListsByUserId(reqBo.getParamLong("userId"),reqBo.getParamInt("page"),reqBo.getParamInt("rows")));
	}

	@Override
	public ResBo<UserAddress> getUserAddressById(ReqBo reqBo) {
		return new ResBo<UserAddress>(userAddressComponent.selectUserAddressLById(reqBo.getParamLong("id")));
	}

	@Override
	public ResBo<UserAddress> getDefaultUserAddressByUserId(ReqBo reqBo) {
		return new ResBo<UserAddress>(userAddressComponent.selectDefaultUserAddressLByUserId(reqBo.getParamLong("userId")));
	}
	
	@Override
	public ResBo<UserAddress> updateDefaultUserAddress(ReqBo reqBo) {
		userAddressComponent.updateUserAddressByUserId(reqBo.getParamLong("userId"));
		userAddressComponent.updateDefaultUserAddressByUserId(reqBo.getParamLong("id"));
		return new ResBo<UserAddress>(userAddressComponent.selectDefaultUserAddressLByUserId(reqBo.getParamLong("userId")));
	}
	
}
