package net.sls.dao.activity;

import java.util.List;
import net.sls.dto.activity.DetailTip;
import net.sls.dto.activity.DetailTipExample;
import org.apache.ibatis.annotations.Param;

public interface DetailTipMapper {
    int countByExample(DetailTipExample example);

    int deleteByExample(DetailTipExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DetailTip record);

    int insertSelective(DetailTip record);

    List<DetailTip> selectByExample(DetailTipExample example);

    DetailTip selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DetailTip record, @Param("example") DetailTipExample example);

    int updateByExample(@Param("record") DetailTip record, @Param("example") DetailTipExample example);

    int updateByPrimaryKeySelective(DetailTip record);

    int updateByPrimaryKey(DetailTip record);
}