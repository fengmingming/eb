package net.sls.dao.pay;

import java.util.List;
import net.sls.dto.pay.PrepaidCard;
import net.sls.dto.pay.PrepaidCardExample;
import org.apache.ibatis.annotations.Param;

public interface PrepaidCardMapper {
    int countByExample(PrepaidCardExample example);

    int deleteByExample(PrepaidCardExample example);

    int insert(PrepaidCard record);

    int insertSelective(PrepaidCard record);

    List<PrepaidCard> selectByExample(PrepaidCardExample example);

    int updateByExampleSelective(@Param("record") PrepaidCard record, @Param("example") PrepaidCardExample example);

    int updateByExample(@Param("record") PrepaidCard record, @Param("example") PrepaidCardExample example);
}