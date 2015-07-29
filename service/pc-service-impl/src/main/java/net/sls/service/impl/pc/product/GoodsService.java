package net.sls.service.impl.pc.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sls.component.pc.act.IActivitiesComponent;
import net.sls.component.pc.act.IActivityGoodsComponent;
import net.sls.component.pc.order.IOrderActGoodsInfoComponent;
import net.sls.component.pc.product.IAreaComponent;
import net.sls.component.pc.product.IGoodsComponent;
import net.sls.dto.pc.act.ActDto;
import net.sls.dto.pc.act.Activities;
import net.sls.dto.pc.act.ActivityGoods;
import net.sls.dto.pc.product.CommodityDto;
import net.sls.pc.search.SearchResultDto;
import net.sls.pc.search.Searcher;
import net.sls.service.pc.product.IGoodsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.model.ActEnum;
import util.model.Area;
import util.model.SearchSortEnum;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Service
public class GoodsService implements IGoodsService {

	@Autowired
	public IGoodsComponent goodsComponent;
	
	@Autowired
	public IAreaComponent areaComponent;
	
	@Autowired
	public IActivityGoodsComponent actGoodsComponent;
	
	@Autowired
	public IOrderActGoodsInfoComponent orderActGoodsInfoComponent;
	
	@Autowired
	public IActivityGoodsComponent agComponent;
	
	@Autowired
	public IActivitiesComponent actComponent;
	
	@Resource
	public Searcher solrSearch;
	
	@Override
	public ResBo<Pager<List<CommodityDto>>> selectProductPageList(ReqBo reqBo) {
		Integer id = reqBo.getParamInt("areaId");
		List<Integer> areaIds = new ArrayList<Integer>();
		util.model.Area area =	areaComponent.selectAreaById(id);
		//市id
		areaIds.add(id);
		//省id
		areaIds.add(area.getPid());
		//中国id
		areaIds.add(1);
		
		Pager<List<CommodityDto>> pager = goodsComponent.selectGoods
			(reqBo.getParamLong("first"), reqBo.getParamLong("second"), reqBo.getParamLong("third"),areaIds,reqBo.getParamInt("actType"), reqBo.getParamInt("hasGoods"),
				reqBo.getParamDouble("minPrice"), reqBo.getParamDouble("maxPrice"), reqBo.getParamInt("sortPrice"),		
				reqBo.getParamInt("sortNew"),reqBo.getParamInt("sortSale"),reqBo.getParamInt("currPage")-1, reqBo.getParamInt("num"));
		if(pager.getTotal() > 0){
			List<CommodityDto> commodityDtos = pager.getEntry();
			List<Long> goodsIds = new ArrayList<Long>();
			Map<Long, CommodityDto> commodityDtoMap = new HashMap<Long, CommodityDto>();
			for(CommodityDto commodityDto : commodityDtos){
				commodityDtoMap.put(commodityDto.getId(), commodityDto);
				goodsIds.add(commodityDto.getId());
			}
			List<ActivityGoods> ags = agComponent.selectUserActivityGoods(null, null, null, goodsIds, null);
			for(ActivityGoods ag : ags){
				CommodityDto commodityDto = commodityDtoMap.get(ag.getGoodsId());
				if(commodityDto != null){
					ActDto actDto = new ActDto();
					actDto.setActId(ag.getActId());
					actDto.setActType(ag.getActType());
					ActEnum act = ActEnum.getActEnum(ag.getActType());
					actDto.setActTypeName(act!=null?act.getName():"");
					actDto.setActBuyNumber(ag.getBuyNumber()==null?0:ag.getBuyNumber());
					actDto.setActPrice(ag.getActPrice());
					if(commodityDto.getActDto() == null){
						commodityDto.setActDto(new ArrayList<ActDto>());
					}
					commodityDto.getActDto().add(actDto);
				}
			}
		}
		
		return new ResBo<Pager<List<CommodityDto>>>(pager);
	}
	
	@Override
	public ResBo<CommodityDto> selectGoodsDetail(ReqBo reqBo) {
		Integer provinceId=reqBo.getParamInt("provinceId");
		Integer cityId=reqBo.getParamInt("cityId");
		Integer districtId=reqBo.getParamInt("districtId");
		Integer communityId=reqBo.getParamInt("communityId");
		Integer pavilionId=reqBo.getParamInt("pavilionId");
		Long goodId = reqBo.getParamLong("id");
		List<Long> goodsIds = new ArrayList<Long>();
		goodsIds.add(goodId);
		List<String> areaCodeList = new ArrayList<String>();
		Integer id = reqBo.getParamInt("areaId");
		Area area =	areaComponent.selectAreaById(id);
		if(area != null){
			//市id
			areaCodeList.add(area.getCode());
			//省id
			area = areaComponent.selectAreaById(area.getPid());
			if(area != null){
				areaCodeList.add(area.getCode());
			}
		}
		areaCodeList.add("1");
		
		List<ActivityGoods> actGoodsList= actGoodsComponent.selectActivityGoods(goodsIds,areaCodeList);
		
		CommodityDto goods = goodsComponent.selectGoods(reqBo.getParamInt("id"),provinceId,cityId,districtId,communityId,pavilionId);
		if (actGoodsList.size()>0) {
			List<ActDto> actDtoList= new ArrayList<ActDto>();
			for (ActivityGoods activityGoods : actGoodsList) {
				int number = orderActGoodsInfoComponent.selectNumber(activityGoods.getActId(),activityGoods.getGoodsId());
				Activities act=actComponent.selectActivities(activityGoods.getActId());
				ActDto actDto=new ActDto();
				actDto.setActBuyNumber(number);
				actDto.setActId(activityGoods.getActId());
				actDto.setActName(act.getActName());
				actDto.setActPrice(activityGoods.getActPrice());
				actDto.setActType(activityGoods.getActType());
				actDto.setActTypeName(ActEnum.getActEnum(activityGoods.getActType()).getName());
				actDto.setEndTime(activityGoods.getEndTime());
				actDto.setNow(new Date());
				actDto.setNumber(activityGoods.getNumber()); // 限购数量
				actDto.setStartTime(activityGoods.getStartTime());
				actDtoList.add(actDto);
			}
			goods.setActDto(actDtoList);
		}
		if(goods == null){
			return new ResBo<CommodityDto>(13L);
		}else{
			return new ResBo<CommodityDto>(goods);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResBo<Pager<List<CommodityDto>>> sreachGoods(ReqBo reqBo) {
		Integer id = reqBo.getParamInt("areaId");
		util.model.Area area =	areaComponent.selectAreaById(id);
		SearchSortEnum sort = null;
		Boolean asc = null;
		if(reqBo.getParamInt("sortPrice") != null){
			if(reqBo.getParamInt("sortPrice").intValue() == 0){
				sort = SearchSortEnum.PRICE;
				asc = Boolean.TRUE;
			}
			if(reqBo.getParamInt("sortPrice").intValue() == 1){
				sort = SearchSortEnum.PRICE;
				asc = Boolean.FALSE;
			}
		}
		if(reqBo.getParamInt("sortNew") != null){
			sort = SearchSortEnum.TIME;
		}
		if(reqBo.getParamInt("sortSale") != null){
			sort = SearchSortEnum.SALES;
		}
		SearchResultDto dto = solrSearch.query(reqBo.getParamStr("content"), area.getCode(), sort, asc, reqBo.getParamInt("currPage"), reqBo.getParamInt("num"));
		List<Long> goodsIds = new ArrayList<Long>();
		for(Map map:dto.getResponse().getDocs()){
			goodsIds.add(Long.valueOf(map.get("goodsId").toString()));
		}
		Pager<List<CommodityDto>> pager =  goodsComponent.selectGoodsByIds(goodsIds);
		pager.setTotal(dto.getTotal());
		if(pager.getTotal() > 0){
			List<CommodityDto> commodityDtos = pager.getEntry();
			Map<Long, CommodityDto> commodityDtoMap = new HashMap<Long, CommodityDto>();
			for(CommodityDto commodityDto : commodityDtos){
				commodityDtoMap.put(commodityDto.getId(), commodityDto);
				goodsIds.add(commodityDto.getId());
			}
			List<ActivityGoods> ags = agComponent.selectUserActivityGoods(null, null, null, goodsIds, null);
			for(ActivityGoods ag : ags){
				CommodityDto commodityDto = commodityDtoMap.get(ag.getGoodsId());
				if(commodityDto != null){
					ActDto actDto = new ActDto();
					actDto.setActId(ag.getActId());
					actDto.setActType(ag.getActType());
					ActEnum act = ActEnum.getActEnum(ag.getActType());
					actDto.setActTypeName(act!=null?act.getName():"");
					actDto.setActBuyNumber(ag.getBuyNumber()==null?0:ag.getBuyNumber());
					actDto.setActPrice(ag.getActPrice());
					if(commodityDto.getActDto() == null){
						commodityDto.setActDto(new ArrayList<ActDto>());
					}
					commodityDto.getActDto().add(actDto);
				}
			}
		}
		
		return new ResBo<Pager<List<CommodityDto>>>(pager);
	}

	@Override
	public ResBo<Pager<List<CommodityDto>>> selectDynpageGoodsPageList(
			ReqBo reqBo) {
		
		Pager<List<CommodityDto>> pager = goodsComponent.selectDynpageGoodsPageList
			(reqBo.getParamInt("dynpageId"), reqBo.getParamInt("currPage")-1, reqBo.getParamInt("num"));
		if(pager.getTotal() > 0){
			List<CommodityDto> commodityDtos = pager.getEntry();
			List<Long> goodsIds = new ArrayList<Long>();
			Map<Long, CommodityDto> commodityDtoMap = new HashMap<Long, CommodityDto>();
			for(CommodityDto commodityDto : commodityDtos){
				commodityDtoMap.put(commodityDto.getId(), commodityDto);
				goodsIds.add(commodityDto.getId());
			}
			List<ActivityGoods> ags = agComponent.selectUserActivityGoods(null, null, null, goodsIds, null);
			for(ActivityGoods ag : ags){
				CommodityDto commodityDto = commodityDtoMap.get(ag.getGoodsId());
				if(commodityDto != null){
					ActDto actDto = new ActDto();
					actDto.setActId(ag.getActId());
					actDto.setActType(ag.getActType());
					ActEnum act = ActEnum.getActEnum(ag.getActType());
					actDto.setActTypeName(act!=null?act.getName():"");
					actDto.setActBuyNumber(ag.getBuyNumber()==null?0:ag.getBuyNumber());
					actDto.setActPrice(ag.getActPrice());
					if(commodityDto.getActDto() == null){
						commodityDto.setActDto(new ArrayList<ActDto>());
					}
					commodityDto.getActDto().add(actDto);
				}
			}
		}
		
		return new ResBo<Pager<List<CommodityDto>>>(pager);
	}

	@Override
	public ResBo<Pager<List<CommodityDto>>> selectDynpageGoodsListByType(
			ReqBo reqBo) {
		Pager<List<CommodityDto>> pager = goodsComponent.selectDynpageGoodsListByType
				(reqBo.getParamInt("type"), reqBo.getParamInt("currPage")-1, reqBo.getParamInt("num"));
			if(pager.getTotal() > 0){
				List<CommodityDto> commodityDtos = pager.getEntry();
				List<Long> goodsIds = new ArrayList<Long>();
				Map<Long, CommodityDto> commodityDtoMap = new HashMap<Long, CommodityDto>();
				for(CommodityDto commodityDto : commodityDtos){
					commodityDtoMap.put(commodityDto.getId(), commodityDto);
					goodsIds.add(commodityDto.getId());
				}
				List<ActivityGoods> ags = agComponent.selectUserActivityGoods(null, null, null, goodsIds, null);
				for(ActivityGoods ag : ags){
					CommodityDto commodityDto = commodityDtoMap.get(ag.getGoodsId());
					if(commodityDto != null){
						ActDto actDto = new ActDto();
						actDto.setActId(ag.getActId());
						actDto.setActType(ag.getActType());
						ActEnum act = ActEnum.getActEnum(ag.getActType());
						actDto.setActTypeName(act!=null?act.getName():"");
						actDto.setActBuyNumber(ag.getBuyNumber()==null?0:ag.getBuyNumber());
						actDto.setActPrice(ag.getActPrice());
						if(commodityDto.getActDto() == null){
							commodityDto.setActDto(new ArrayList<ActDto>());
						}
						commodityDto.getActDto().add(actDto);
					}
				}
			}
			
			return new ResBo<Pager<List<CommodityDto>>>(pager);
	}
}
