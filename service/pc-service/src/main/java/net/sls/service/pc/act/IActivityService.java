package net.sls.service.pc.act;

import net.sls.dto.pc.product.CommodityDto;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IActivityService {
	/**
	 * 查询活动商品详情根据goodsId，actId
	 * 查到的内容包括：四张小图、一张大图、商品描述、库存
	 */
	public ResBo<CommodityDto> selectActivityGoodsDetail(ReqBo reqBo);
}
