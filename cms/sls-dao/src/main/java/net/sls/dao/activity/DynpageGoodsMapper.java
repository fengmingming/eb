package net.sls.dao.activity;

import java.util.List;
import net.sls.dto.activity.DynpageGoods;
import net.sls.dto.activity.DynpageGoodsExample;
import org.apache.ibatis.annotations.Param;

public interface DynpageGoodsMapper {
    int countByExample(DynpageGoodsExample example);

    int deleteByExample(DynpageGoodsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DynpageGoods record);

    int insertSelective(DynpageGoods record);

    List<DynpageGoods> selectByExample(DynpageGoodsExample example);

    DynpageGoods selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DynpageGoods record, @Param("example") DynpageGoodsExample example);

    int updateByExample(@Param("record") DynpageGoods record, @Param("example") DynpageGoodsExample example);

    int updateByPrimaryKeySelective(DynpageGoods record);

    int updateByPrimaryKey(DynpageGoods record);
}