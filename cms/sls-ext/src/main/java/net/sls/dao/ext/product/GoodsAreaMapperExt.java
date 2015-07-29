package net.sls.dao.ext.product;

import java.util.List;

import net.sls.dto.product.GoodsArea;

public interface GoodsAreaMapperExt {

	public int insert(List<GoodsArea> list);
	
	public int update(List<GoodsArea> list);
}