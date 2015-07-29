package net.sls.dao.activity;

import java.util.List;
import net.sls.dto.activity.GoodsTop;
import net.sls.dto.activity.GoodsTopExample;
import org.apache.ibatis.annotations.Param;

public interface GoodsTopMapper {
    int countByExample(GoodsTopExample example);

    int deleteByExample(GoodsTopExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GoodsTop record);

    int insertSelective(GoodsTop record);

    List<GoodsTop> selectByExample(GoodsTopExample example);

    GoodsTop selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GoodsTop record, @Param("example") GoodsTopExample example);

    int updateByExample(@Param("record") GoodsTop record, @Param("example") GoodsTopExample example);

    int updateByPrimaryKeySelective(GoodsTop record);

    int updateByPrimaryKey(GoodsTop record);
}