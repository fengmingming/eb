package net.sls.service.pc.user;

import java.util.List;
import java.util.Map;

import net.sls.dto.pc.user.MyFavorite;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IMyFavoriteService {

	public ResBo<MyFavorite> insertMyFavorite(ReqBo reqBo);
	
	public ResBo<Pager<List<Map<Object,Object>>>> selectMyFavoriteListsByUserId(ReqBo reqBo);
	
	public ResBo<MyFavorite> IsExistMyFavorite(ReqBo reqBo);
	
	public ResBo<Boolean> cancelMyFavorite(ReqBo reqBo);
	
}
