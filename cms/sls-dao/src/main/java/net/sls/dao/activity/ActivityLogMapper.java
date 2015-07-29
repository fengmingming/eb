package net.sls.dao.activity;

import java.util.List;
import net.sls.dto.activity.ActivityLog;
import net.sls.dto.activity.ActivityLogExample;
import org.apache.ibatis.annotations.Param;

public interface ActivityLogMapper {
    int countByExample(ActivityLogExample example);

    int deleteByExample(ActivityLogExample example);

    int insert(ActivityLog record);

    int insertSelective(ActivityLog record);

    List<ActivityLog> selectByExample(ActivityLogExample example);

    int updateByExampleSelective(@Param("record") ActivityLog record, @Param("example") ActivityLogExample example);

    int updateByExample(@Param("record") ActivityLog record, @Param("example") ActivityLogExample example);
}