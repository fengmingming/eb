package net.sls.service.product;

import net.sls.dto.product.GoodsDetail;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IGoodsDetailService {

	public ResBo<GoodsDetail> selectGoodsDetailById(ReqBo reqBo);
	
}
