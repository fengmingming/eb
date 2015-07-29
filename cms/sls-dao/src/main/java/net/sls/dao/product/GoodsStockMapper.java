package net.sls.dao.product;

import java.util.List;
import net.sls.dto.product.GoodsStock;
import net.sls.dto.product.GoodsStockExample;
import org.apache.ibatis.annotations.Param;

public interface GoodsStockMapper {
    int countByExample(GoodsStockExample example);

    int deleteByExample(GoodsStockExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GoodsStock record);

    int insertSelective(GoodsStock record);

    List<GoodsStock> selectByExample(GoodsStockExample example);

    GoodsStock selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GoodsStock record, @Param("example") GoodsStockExample example);

    int updateByExample(@Param("record") GoodsStock record, @Param("example") GoodsStockExample example);

    int updateByPrimaryKeySelective(GoodsStock record);

    int updateByPrimaryKey(GoodsStock record);
}