package net.sls.dao.activity;

import java.util.List;
import net.sls.dto.activity.Activities;
import net.sls.dto.activity.ActivitiesExample;
import org.apache.ibatis.annotations.Param;

public interface ActivitiesMapper {
    int countByExample(ActivitiesExample example);

    int deleteByExample(ActivitiesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Activities record);

    int insertSelective(Activities record);

    List<Activities> selectByExample(ActivitiesExample example);

    Activities selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Activities record, @Param("example") ActivitiesExample example);

    int updateByExample(@Param("record") Activities record, @Param("example") ActivitiesExample example);

    int updateByPrimaryKeySelective(Activities record);

    int updateByPrimaryKey(Activities record);
}