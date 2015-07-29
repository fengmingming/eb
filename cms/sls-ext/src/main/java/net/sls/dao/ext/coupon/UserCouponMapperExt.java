package net.sls.dao.ext.coupon;

import net.sls.dto.activity.UserCoupon;
import org.apache.ibatis.annotations.Param;

/**
 * Author : sls006
 */
import java.util.List;

public interface UserCouponMapperExt {

    long queryCount(@Param("pid")Long pid,@Param("code")String code);
    /** 分页查询 **/
    List<UserCoupon> queryUsrCouponByPId(@Param("pid")Long id,@Param("code")String code,@Param("idxStart") int idxStart,@Param("pgSize") int pgSize);
}