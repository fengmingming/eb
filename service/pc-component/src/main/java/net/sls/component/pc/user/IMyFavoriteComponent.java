package net.sls.component.pc.user;

import java.util.List;
import java.util.Map;

import framework.web.Pager;
import net.sls.dto.pc.user.MyFavorite;

public interface IMyFavoriteComponent {

	MyFavorite selectIsExistByGoodsId(Long goodsId, Long userId);

	MyFavorite insertMyFavorite(MyFavorite reqModel);

	Pager<List<Map<Object, Object>>> selectMyFavoriteListsByUserId(
			Long userId, String goodsName, Integer start, Integer number);

	void updateMyFavorite(MyFavorite mf);

	Boolean updateMyFavoriteIsDel(Long goodsId, Long userId);

}
