package net.sls.service.impl.pc.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sls.component.pc.user.IMyFavoriteComponent;
import net.sls.dto.pc.user.MyFavorite;
import net.sls.service.pc.user.IMyFavoriteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;
@Service("myFavoriteService")
public class MyFavoriteService implements IMyFavoriteService{
	@Autowired
	private IMyFavoriteComponent myFavoriteComponent;

	@Override
	public ResBo<MyFavorite> insertMyFavorite(ReqBo reqBo) {
		ResBo<MyFavorite> resBo = new ResBo<MyFavorite>();
		MyFavorite myFavorite=reqBo.getReqModel(MyFavorite.class);
		MyFavorite mf = myFavoriteComponent.selectIsExistByGoodsId(myFavorite.getGoodsId(),myFavorite.getUserId());
		if(mf !=null && mf.getIsDel() == 1){
			return new ResBo<MyFavorite>(14,reqBo.getReqModel(MyFavorite.class).getGoodsId());
		}
		if (mf != null && mf.getIsDel() == 127) {
			mf.setIsDel(myFavorite.getIsDel());
			mf.setCreateTime(new Date());
			myFavoriteComponent.updateMyFavorite(mf);
			resBo.setResult(mf);
		}else{
			myFavorite.setCreateTime(new Date());
			myFavoriteComponent.insertMyFavorite(myFavorite);
			resBo.setResult(myFavorite);
		}
		return resBo;
	}

	@Override
	public ResBo<Pager<List<Map<Object,Object>>>> selectMyFavoriteListsByUserId(ReqBo reqBo) {
		return new ResBo<Pager<List<Map<Object,Object>>>>(myFavoriteComponent.selectMyFavoriteListsByUserId(reqBo.getParamLong("userId"),reqBo.getParamStr("goodsName"),reqBo.getParamInt("page"),reqBo.getParamInt("rows")));
	}
	
	@Override
	public ResBo<MyFavorite> IsExistMyFavorite(ReqBo reqBo) {
		ResBo<MyFavorite> resBo = new ResBo<MyFavorite>();
		MyFavorite mf = myFavoriteComponent.selectIsExistByGoodsId(reqBo.getParamLong("goodsId"),reqBo.getParamLong("userId"));
		resBo.setResult(mf);
		return resBo;
	}

	@Override
	public ResBo<Boolean> cancelMyFavorite(ReqBo reqBo) {
		return new ResBo<Boolean>(myFavoriteComponent.updateMyFavoriteIsDel(reqBo.getParamLong("goodsId"),reqBo.getParamLong("userId")));
	}
	
}
