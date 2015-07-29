package net.sls.dao.activity;

import java.util.List;
import net.sls.dto.activity.GoodsComment;
import net.sls.dto.activity.GoodsCommentExample;
import org.apache.ibatis.annotations.Param;

public interface GoodsCommentMapper {
    int countByExample(GoodsCommentExample example);

    int deleteByExample(GoodsCommentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GoodsComment record);

    int insertSelective(GoodsComment record);

    List<GoodsComment> selectByExample(GoodsCommentExample example);

    GoodsComment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GoodsComment record, @Param("example") GoodsCommentExample example);

    int updateByExample(@Param("record") GoodsComment record, @Param("example") GoodsCommentExample example);

    int updateByPrimaryKeySelective(GoodsComment record);

    int updateByPrimaryKey(GoodsComment record);
}