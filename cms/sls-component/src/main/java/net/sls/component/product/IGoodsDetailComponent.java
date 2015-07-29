package net.sls.component.product;

import net.sls.dto.product.GoodsDetail;

public interface IGoodsDetailComponent {

	public GoodsDetail selectGoodsDetailById(long goodsId);
	
}
