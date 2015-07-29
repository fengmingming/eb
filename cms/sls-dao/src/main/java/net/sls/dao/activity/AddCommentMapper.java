package net.sls.dao.activity;

import java.util.List;
import net.sls.dto.activity.AddComment;
import net.sls.dto.activity.AddCommentExample;
import org.apache.ibatis.annotations.Param;

public interface AddCommentMapper {
    int countByExample(AddCommentExample example);

    int deleteByExample(AddCommentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AddComment record);

    int insertSelective(AddComment record);

    List<AddComment> selectByExample(AddCommentExample example);

    AddComment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AddComment record, @Param("example") AddCommentExample example);

    int updateByExample(@Param("record") AddComment record, @Param("example") AddCommentExample example);

    int updateByPrimaryKeySelective(AddComment record);

    int updateByPrimaryKey(AddComment record);
}