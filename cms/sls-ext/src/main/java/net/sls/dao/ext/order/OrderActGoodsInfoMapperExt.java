package net.sls.dao.ext.order;

import java.util.List;
import java.util.Map;

public interface OrderActGoodsInfoMapperExt {
    
	public List<Map<String,Object>> selectOrderActGoodsInfo(long orderId);
}