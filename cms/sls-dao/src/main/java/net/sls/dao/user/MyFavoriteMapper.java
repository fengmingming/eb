package net.sls.dao.user;

import java.util.List;
import net.sls.dto.user.MyFavorite;
import net.sls.dto.user.MyFavoriteExample;
import org.apache.ibatis.annotations.Param;

public interface MyFavoriteMapper {
    int countByExample(MyFavoriteExample example);

    int deleteByExample(MyFavoriteExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MyFavorite record);

    int insertSelective(MyFavorite record);

    List<MyFavorite> selectByExample(MyFavoriteExample example);

    MyFavorite selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MyFavorite record, @Param("example") MyFavoriteExample example);

    int updateByExample(@Param("record") MyFavorite record, @Param("example") MyFavoriteExample example);

    int updateByPrimaryKeySelective(MyFavorite record);

    int updateByPrimaryKey(MyFavorite record);
}