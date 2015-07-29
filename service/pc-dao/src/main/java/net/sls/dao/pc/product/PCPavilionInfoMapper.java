package net.sls.dao.pc.product;

import org.apache.ibatis.annotations.Param;

import net.sls.dto.pc.product.PavilionInfo;

public interface PCPavilionInfoMapper {

	PavilionInfo selectPavilionInfoByCode(@Param("pavilionCode") String pavilionCode);

}
