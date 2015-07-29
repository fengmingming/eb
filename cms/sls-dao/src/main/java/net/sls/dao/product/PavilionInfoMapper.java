package net.sls.dao.product;

import java.util.List;
import net.sls.dto.product.PavilionInfo;
import net.sls.dto.product.PavilionInfoExample;
import org.apache.ibatis.annotations.Param;

public interface PavilionInfoMapper {
    int countByExample(PavilionInfoExample example);

    int deleteByExample(PavilionInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PavilionInfo record);

    int insertSelective(PavilionInfo record);

    List<PavilionInfo> selectByExample(PavilionInfoExample example);

    PavilionInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PavilionInfo record, @Param("example") PavilionInfoExample example);

    int updateByExample(@Param("record") PavilionInfo record, @Param("example") PavilionInfoExample example);

    int updateByPrimaryKeySelective(PavilionInfo record);

    int updateByPrimaryKey(PavilionInfo record);
}