package net.sls.dao.product;

import java.util.List;
import net.sls.dto.product.ShopCart;
import net.sls.dto.product.ShopCartExample;
import org.apache.ibatis.annotations.Param;

public interface ShopCartMapper {
    int countByExample(ShopCartExample example);

    int deleteByExample(ShopCartExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShopCart record);

    int insertSelective(ShopCart record);

    List<ShopCart> selectByExample(ShopCartExample example);

    ShopCart selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShopCart record, @Param("example") ShopCartExample example);

    int updateByExample(@Param("record") ShopCart record, @Param("example") ShopCartExample example);

    int updateByPrimaryKeySelective(ShopCart record);

    int updateByPrimaryKey(ShopCart record);
}