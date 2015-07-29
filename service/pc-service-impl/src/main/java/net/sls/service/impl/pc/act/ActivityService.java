package net.sls.service.impl.pc.act;

import net.sls.component.pc.act.IActivityGoodsComponent;
import net.sls.component.pc.order.IOrderActGoodsInfoComponent;
import net.sls.component.pc.product.IAreaComponent;
import net.sls.component.pc.product.IGoodsComponent;
import net.sls.dto.pc.product.CommodityDto;
import net.sls.service.pc.act.IActivityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.web.ReqBo;
import framework.web.ResBo;
@Service("activityService")
public class ActivityService implements IActivityService{

	@Autowired
	public IGoodsComponent goodsComponent;
	
	@Autowired
	public IAreaComponent areaComponent;
	@Autowired
	public IActivityGoodsComponent actGoodsComponent;
	@Autowired
	public IOrderActGoodsInfoComponent orderActGoodsInfoComponent;
	
	@Override
	public ResBo<CommodityDto> selectActivityGoodsDetail(ReqBo reqBo) {
		return null;
	}

}
