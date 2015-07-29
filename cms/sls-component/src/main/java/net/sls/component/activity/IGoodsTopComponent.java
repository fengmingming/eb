package net.sls.component.activity;

import java.util.List;
import java.util.Map;

import framework.web.Pager;
import net.sls.dto.activity.GoodsTop;

public interface IGoodsTopComponent {

	void insertGoodsTop(List<GoodsTop> goodsTopList);

	GoodsTop updateGoodsTop(GoodsTop bc);

	Pager<List<Map<Object,Object>>> selectGoodsTopList(String areaCode,
			Integer start, Integer number);

	void updateGoodsTopIsDel(List<Long> ids);

}
