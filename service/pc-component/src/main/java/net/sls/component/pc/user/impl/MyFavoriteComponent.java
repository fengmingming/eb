package net.sls.component.pc.user.impl;

import java.util.List;
import java.util.Map;

import net.sls.component.pc.user.IMyFavoriteComponent;
import net.sls.dao.pc.user.PCMyFavoriteMapper;
import net.sls.dto.pc.user.MyFavorite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.exception.BusinessException;
import framework.web.Pager;
@Component
public class MyFavoriteComponent implements IMyFavoriteComponent{
	
	@Autowired
	private PCMyFavoriteMapper myFavoriteMapper;
	
	@Override
	public MyFavorite selectIsExistByGoodsId(Long goodsId,Long userId) {
		return myFavoriteMapper.selectIsExistByGoodsId(goodsId,userId);
	}

	@Override
	public MyFavorite insertMyFavorite(MyFavorite myFavorite) {
		int i = myFavoriteMapper.insertMyFavorite(myFavorite);
		if(i != 1){
			throw new BusinessException(15L);
		}
		return myFavorite;
	}

	@Override
	public Pager<List<Map<Object, Object>>> selectMyFavoriteListsByUserId(
			Long userId, String goodsName, Integer start, Integer number) {
		long count = myFavoriteMapper.countMyFavoriteByFilter(userId,goodsName);
		List<Map<Object, Object>> list = myFavoriteMapper.selectMyFavoriteListsByUserId(userId, goodsName, (start-1)*number, number);
		Pager<List<Map<Object, Object>>> pager = new Pager<List<Map<Object, Object>>>(list,count);
		return pager;
	}

	@Override
	public void updateMyFavorite(MyFavorite mf) {
		int i=myFavoriteMapper.updateMyFavorite(mf);
		if(!(i > 0)){
			throw new BusinessException(9L);
		}
	}

	@Override
	public Boolean updateMyFavoriteIsDel(Long goodsId, Long userId) {
		if(myFavoriteMapper.updateMyFavoriteIsDel(goodsId, userId) == 1){
			return true;
		}
		return false;
	}

}
