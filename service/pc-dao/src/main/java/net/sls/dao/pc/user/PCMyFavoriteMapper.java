package net.sls.dao.pc.user;

import java.util.List;
import java.util.Map;

import net.sls.dto.pc.user.MyFavorite;

import org.apache.ibatis.annotations.Param;

public interface PCMyFavoriteMapper {

	MyFavorite selectIsExistByGoodsId(@Param("goodsId") Long goodsId,@Param("userId") Long userId);

	int insertMyFavorite(MyFavorite myFavorite);

	long countMyFavoriteByFilter(@Param("userId") Long userId,@Param("goodsName") String goodsName);

	List<Map<Object, Object>> selectMyFavoriteListsByUserId(@Param("userId") Long userId,@Param("goodsName") String goodsName, @Param("start") Integer start,@Param("number") Integer number);

	int updateMyFavorite(MyFavorite mf);

	int updateMyFavoriteIsDel(@Param("goodsId") Long goodsId,@Param("userId")  Long userId);

}
