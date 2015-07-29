package net.sls.dao.activity;

import java.util.List;
import net.sls.dto.activity.Broadcast;
import net.sls.dto.activity.BroadcastExample;
import org.apache.ibatis.annotations.Param;

public interface BroadcastMapper {
    int countByExample(BroadcastExample example);

    int deleteByExample(BroadcastExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Broadcast record);

    int insertSelective(Broadcast record);

    List<Broadcast> selectByExample(BroadcastExample example);

    Broadcast selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Broadcast record, @Param("example") BroadcastExample example);

    int updateByExample(@Param("record") Broadcast record, @Param("example") BroadcastExample example);

    int updateByPrimaryKeySelective(Broadcast record);

    int updateByPrimaryKey(Broadcast record);
}