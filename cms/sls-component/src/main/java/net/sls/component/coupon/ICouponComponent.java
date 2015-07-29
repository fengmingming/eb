package net.sls.component.coupon;

import framework.web.Pager;
import net.sls.dto.activity.UserCoupon;

import java.util.List;

public interface ICouponComponent<CouponExt> extends ICommonEntityComponent<CouponExt> {
    /*逻辑删除指定id集合的coupon对象，设置isDel 127*/
    int deleteEntityInLogic(long[] ids);

    /**
     * 查询已被领用的Coupon
     * 即：total >
     * @return
     */
    List<CouponExt> queryFetchedCoupon();


    /**
     * 查询是否在有效期内的列表
     * @param k
     * @param pgIdx
     * @param pgSize
     * @param onlyInUse
     * @return
     */
    Pager<List<CouponExt>> selectPage(CouponExt k,int pgIdx,int pgSize,boolean onlyInUse);
    Pager<List<UserCoupon>> selectUsrCouponByPId(Long pid,String code,int pgIdx,int pgSize);
}
