package net.sls.dao.activity;

import java.util.List;
import net.sls.dto.activity.ActivityGoods;
import net.sls.dto.activity.ActivityGoodsExample;
import org.apache.ibatis.annotations.Param;

public interface ActivityGoodsMapper {
    int countByExample(ActivityGoodsExample example);

    int deleteByExample(ActivityGoodsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityGoods record);

    int insertSelective(ActivityGoods record);

    List<ActivityGoods> selectByExample(ActivityGoodsExample example);

    ActivityGoods selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivityGoods record, @Param("example") ActivityGoodsExample example);

    int updateByExample(@Param("record") ActivityGoods record, @Param("example") ActivityGoodsExample example);

    int updateByPrimaryKeySelective(ActivityGoods record);

    int updateByPrimaryKey(ActivityGoods record);
}