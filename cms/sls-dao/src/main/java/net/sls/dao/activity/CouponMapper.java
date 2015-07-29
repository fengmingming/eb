package net.sls.dao.activity;

import java.util.List;
import net.sls.dto.activity.Coupon;
import net.sls.dto.activity.CouponExample;
import org.apache.ibatis.annotations.Param;

public interface CouponMapper {
    int countByExample(CouponExample example);

    int deleteByExample(CouponExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    List<Coupon> selectByExampleWithBLOBs(CouponExample example);

    List<Coupon> selectByExample(CouponExample example);

    Coupon selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Coupon record, @Param("example") CouponExample example);

    int updateByExampleWithBLOBs(@Param("record") Coupon record, @Param("example") CouponExample example);

    int updateByExample(@Param("record") Coupon record, @Param("example") CouponExample example);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKeyWithBLOBs(Coupon record);

    int updateByPrimaryKey(Coupon record);
}