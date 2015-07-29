package net.sls.dao.order;

import java.util.List;
import net.sls.dto.order.ExportOrderRecord;
import net.sls.dto.order.ExportOrderRecordExample;
import org.apache.ibatis.annotations.Param;

public interface ExportOrderRecordMapper {
    int countByExample(ExportOrderRecordExample example);

    int deleteByExample(ExportOrderRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ExportOrderRecord record);

    int insertSelective(ExportOrderRecord record);

    List<ExportOrderRecord> selectByExample(ExportOrderRecordExample example);

    ExportOrderRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ExportOrderRecord record, @Param("example") ExportOrderRecordExample example);

    int updateByExample(@Param("record") ExportOrderRecord record, @Param("example") ExportOrderRecordExample example);

    int updateByPrimaryKeySelective(ExportOrderRecord record);

    int updateByPrimaryKey(ExportOrderRecord record);
}