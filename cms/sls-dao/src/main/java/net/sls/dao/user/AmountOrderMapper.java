package net.sls.dao.user;

import java.util.List;
import net.sls.dto.user.AmountOrder;
import net.sls.dto.user.AmountOrderExample;
import org.apache.ibatis.annotations.Param;

public interface AmountOrderMapper {
    int countByExample(AmountOrderExample example);

    int deleteByExample(AmountOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AmountOrder record);

    int insertSelective(AmountOrder record);

    List<AmountOrder> selectByExample(AmountOrderExample example);

    AmountOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AmountOrder record, @Param("example") AmountOrderExample example);

    int updateByExample(@Param("record") AmountOrder record, @Param("example") AmountOrderExample example);

    int updateByPrimaryKeySelective(AmountOrder record);

    int updateByPrimaryKey(AmountOrder record);
}