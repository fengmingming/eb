package net.sls.component.product;

import java.util.List;

import net.sls.dto.product.GoodsArea;
import framework.web.Pager;

public interface IGoodsAreaComponent {

	public Pager<List<GoodsArea>> selectAreaPager(long goodsId);
	
	public void deleteGoodsAreaById(long id);
}
