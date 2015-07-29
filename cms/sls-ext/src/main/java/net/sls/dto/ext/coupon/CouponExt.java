package net.sls.dto.ext.coupon;

import net.sls.dto.activity.Coupon;

/**
 * Created by dozray on 2015/6/19.
 */
public class CouponExt extends Coupon {
    public String getLimitCateName() {
        return limitCateName;
    }

    public void setLimitCateName(String limitCateName) {
        this.limitCateName = limitCateName;
    }

    private String limitCateName;

}
