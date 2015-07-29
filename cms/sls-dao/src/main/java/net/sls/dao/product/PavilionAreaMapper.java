package net.sls.dao.product;

import java.util.List;
import net.sls.dto.product.PavilionArea;
import net.sls.dto.product.PavilionAreaExample;
import org.apache.ibatis.annotations.Param;

public interface PavilionAreaMapper {
    int countByExample(PavilionAreaExample example);

    int deleteByExample(PavilionAreaExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PavilionArea record);

    int insertSelective(PavilionArea record);

    List<PavilionArea> selectByExample(PavilionAreaExample example);

    PavilionArea selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PavilionArea record, @Param("example") PavilionAreaExample example);

    int updateByExample(@Param("record") PavilionArea record, @Param("example") PavilionAreaExample example);

    int updateByPrimaryKeySelective(PavilionArea record);

    int updateByPrimaryKey(PavilionArea record);
}