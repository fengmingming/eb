package net.sls.service.activity;

import java.util.List;
import java.util.Map;

import net.sls.dto.activity.GoodsTop;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IGoodsTopService {

	ResBo<?> insertGoodsTop(ReqBo reqBo);

	ResBo<GoodsTop> updateGoodsTop(ReqBo reqBo);

	ResBo<Pager<List<Map<Object,Object>>>> selectGoodsTopList(ReqBo reqBo);

	ResBo<?> updateGoodsTopIsDel(ReqBo reqBo);

}
