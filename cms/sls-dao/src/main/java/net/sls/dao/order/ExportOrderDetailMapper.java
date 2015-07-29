package net.sls.dao.order;

import java.util.List;
import net.sls.dto.order.ExportOrderDetail;
import net.sls.dto.order.ExportOrderDetailExample;
import org.apache.ibatis.annotations.Param;

public interface ExportOrderDetailMapper {
    int countByExample(ExportOrderDetailExample example);

    int deleteByExample(ExportOrderDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ExportOrderDetail record);

    int insertSelective(ExportOrderDetail record);

    List<ExportOrderDetail> selectByExample(ExportOrderDetailExample example);

    ExportOrderDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ExportOrderDetail record, @Param("example") ExportOrderDetailExample example);

    int updateByExample(@Param("record") ExportOrderDetail record, @Param("example") ExportOrderDetailExample example);

    int updateByPrimaryKeySelective(ExportOrderDetail record);

    int updateByPrimaryKey(ExportOrderDetail record);
}