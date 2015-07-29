package net.sls.service.product;

import java.util.List;

import net.sls.dto.product.GoodsArea;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IGoodsAreaService {
	
	public ResBo<Pager<List<GoodsArea>>> selectAreaPager(ReqBo reqBo);
	
	public ResBo<?> deleteGoodsAreaById(ReqBo reqBo);

}
