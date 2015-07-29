package net.sls.dao.pc.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PCPavilionAreaMapper {

	List<Map<Object, Object>> selectpavilionAreasById(@Param("pavilionId") Integer pavilionId);
    
}