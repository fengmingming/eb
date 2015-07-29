package net.sls.dao.product;

import java.util.List;
import net.sls.dto.product.GoodsArea;
import net.sls.dto.product.GoodsAreaExample;
import org.apache.ibatis.annotations.Param;

public interface GoodsAreaMapper {
    int countByExample(GoodsAreaExample example);

    int deleteByExample(GoodsAreaExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GoodsArea record);

    int insertSelective(GoodsArea record);

    List<GoodsArea> selectByExample(GoodsAreaExample example);

    GoodsArea selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GoodsArea record, @Param("example") GoodsAreaExample example);

    int updateByExample(@Param("record") GoodsArea record, @Param("example") GoodsAreaExample example);

    int updateByPrimaryKeySelective(GoodsArea record);

    int updateByPrimaryKey(GoodsArea record);
}