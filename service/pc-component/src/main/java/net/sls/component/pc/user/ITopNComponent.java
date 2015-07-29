package net.sls.component.pc.user;

import java.util.List;
import java.util.Map;


public interface ITopNComponent {
	List<Map<Object, Object>> selectTopNGoodsByCityId(String areaCode);
}
