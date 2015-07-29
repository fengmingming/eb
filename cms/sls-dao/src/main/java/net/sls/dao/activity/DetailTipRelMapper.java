package net.sls.dao.activity;

import java.util.List;
import net.sls.dto.activity.DetailTipRel;
import net.sls.dto.activity.DetailTipRelExample;
import org.apache.ibatis.annotations.Param;

public interface DetailTipRelMapper {
    int countByExample(DetailTipRelExample example);

    int deleteByExample(DetailTipRelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DetailTipRel record);

    int insertSelective(DetailTipRel record);

    List<DetailTipRel> selectByExample(DetailTipRelExample example);

    DetailTipRel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DetailTipRel record, @Param("example") DetailTipRelExample example);

    int updateByExample(@Param("record") DetailTipRel record, @Param("example") DetailTipRelExample example);

    int updateByPrimaryKeySelective(DetailTipRel record);

    int updateByPrimaryKey(DetailTipRel record);
}