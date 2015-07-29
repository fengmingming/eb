package net.sls.service.coupon;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;
import net.sls.dto.activity.Coupon;
import net.sls.dto.activity.UserCoupon;

import java.util.List;

/**
 * Created by sls006 on 2015/5/21.
 * Basic svc impl
 */
public interface ICouponService {
    // add coupons and return added rows
    ResBo<Integer> insertEntity(ReqBo rb);
    ResBo<Integer> deleteEntity(ReqBo rb);
    ResBo<Pager<List<Coupon>>> getPageByEntity(ReqBo rb);
    ResBo<Pager<List<UserCoupon>>> getEntityByPId(ReqBo rb);
}
