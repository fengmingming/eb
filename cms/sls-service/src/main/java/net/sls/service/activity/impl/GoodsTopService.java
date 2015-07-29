package net.sls.service.activity.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sls.businessconstant.BusinessContant;
import net.sls.component.activity.IGoodsTopComponent;
import net.sls.dto.activity.GoodsTop;
import net.sls.service.activity.IGoodsTopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;
@Service
public class GoodsTopService implements IGoodsTopService{
	@Autowired
	private IGoodsTopComponent igtc;
	@Override
	@SuppressWarnings("unchecked")
	public ResBo<?> insertGoodsTop(ReqBo reqBo) {
		List<Long> ids = reqBo.getParamT("ids", List.class);
		GoodsTop gt=null;
		List<GoodsTop> goodsTopList=new ArrayList<GoodsTop>();
		if(ids.size() > 0){
			//goodsComponent.updateBatchGoodsIsSale(ids, reqBo.getParamInt("isSale"));
			for (Long id : ids) {
				gt=new GoodsTop();
				gt.setGoodsId(id);
				gt.setAreaCode(reqBo.getParamStr("areaCode"));
				gt.setCmsUserId(reqBo.getParamLong("userId"));
				gt.setIsDel(reqBo.getParamInt("isDel"));
				gt.setSort(reqBo.getParamInt("sort"));
				goodsTopList.add(gt);
			}
			igtc.insertGoodsTop(goodsTopList);
		}
		return new ResBo<Object>();
	}
	@Override
	public ResBo<GoodsTop> updateGoodsTop(ReqBo reqBo) {
		GoodsTop bc=reqBo.getReqModel(GoodsTop.class);
		igtc.updateGoodsTop(bc);
		return new ResBo<GoodsTop>();
	}
	@Override
	public ResBo<Pager<List<Map<Object,Object>>>> selectGoodsTopList(ReqBo reqBo) {
		return new ResBo<Pager<List<Map<Object,Object>>>>(igtc.selectGoodsTopList(reqBo.getParamStr(BusinessContant.OPERAREAID),reqBo.getParamInt("page"),reqBo.getParamInt("rows")));
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public ResBo<?> updateGoodsTopIsDel(ReqBo reqBo) {
		List<Long> ids = reqBo.getParamT("ids", List.class);
		igtc.updateGoodsTopIsDel(ids);
		return new ResBo<Object>();
	}

}
