package net.sls.dao.ext.coupon;

import net.sls.dto.activity.Coupon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICouponExt {
    int insertCoupon(Coupon c);
    long queryCount();
    /** 查询 **/
    long queryCountByEntity(@Param("c")Coupon c,@Param("oiu") boolean onlyInUse);
    int deleteEntityInLogic(@Param("ids") long[] ids);
    List<Coupon> queryByIds(@Param("ids") long[] ids);
    List<Coupon> queryFetchedCoupon();
    /** 分页查询 **/
    List<Coupon> queryPageByEntity(@Param("c") Coupon c,@Param("idxStart") int idxStart,@Param("pgSize") int pgSize,
                                   @Param("oiu") boolean onlyInUse);
    List<Coupon> queryPage(@Param("idxStart") int idxStart,@Param("pgSize") int pgSize);
}
