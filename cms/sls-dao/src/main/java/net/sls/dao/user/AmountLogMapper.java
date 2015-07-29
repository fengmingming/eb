package net.sls.dao.user;

import java.util.List;
import net.sls.dto.user.AmountLog;
import net.sls.dto.user.AmountLogExample;
import org.apache.ibatis.annotations.Param;

public interface AmountLogMapper {
    int countByExample(AmountLogExample example);

    int deleteByExample(AmountLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AmountLog record);

    int insertSelective(AmountLog record);

    List<AmountLog> selectByExample(AmountLogExample example);

    AmountLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AmountLog record, @Param("example") AmountLogExample example);

    int updateByExample(@Param("record") AmountLog record, @Param("example") AmountLogExample example);

    int updateByPrimaryKeySelective(AmountLog record);

    int updateByPrimaryKey(AmountLog record);
}