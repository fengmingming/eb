package net.sls.dao.activity;

import java.util.List;
import net.sls.dto.activity.Dynpage;
import net.sls.dto.activity.DynpageExample;
import org.apache.ibatis.annotations.Param;

public interface DynpageMapper {
    int countByExample(DynpageExample example);

    int deleteByExample(DynpageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Dynpage record);

    int insertSelective(Dynpage record);

    List<Dynpage> selectByExample(DynpageExample example);

    Dynpage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Dynpage record, @Param("example") DynpageExample example);

    int updateByExample(@Param("record") Dynpage record, @Param("example") DynpageExample example);

    int updateByPrimaryKeySelective(Dynpage record);

    int updateByPrimaryKey(Dynpage record);
}