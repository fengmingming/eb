package net.sls.service.coupon;

import java.util.*;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;
import net.sls.component.coupon.ICouponComponent;
import net.sls.dto.activity.Coupon;
import net.sls.dto.activity.UserCoupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sls006 on 2015/5/21.
 */
@Service
public class CouponService implements  ICouponService{

    @SuppressWarnings("rawtypes")
	@Autowired
    private ICouponComponent ic ;
    @SuppressWarnings("unchecked")
	@Override
    public ResBo<Integer> insertEntity(ReqBo rb) {
        List<Coupon> lst = (List<Coupon>) rb.getReqModel();
        Integer i= ic.insertEntity(lst);
        ResBo<Integer> rsb = new ResBo<Integer>();
        rsb.setResult(i);
        return rsb;
    }

    @Override
    public ResBo<Integer> deleteEntity(ReqBo rb) {
        long[] ids = (long[]) rb.getParam("ids");
        /*
        List<Coupon> cs = ic.queryFetchedCoupon();
        StringBuilder sb = new StringBuilder();
        int len = ids.length;
        for(int i=0; i<len;i++)
        {
            for(Coupon c:cs){
                if (c.getId() == ids[i]){
                    sb.append(c.getId());
                    sb.append(",");
                }
            }
        }
        if(sb.length()>0){
            ResBo<Integer> rsb = new ResBo<Integer>();
            rsb.setResult(0);
            rsb.setErrMsg("已领用Id 为 "+ rsb.toString()+"不能删除");
            return rsb;
        }*/
        Integer i = ic.deleteEntityInLogic(ids);
        ResBo<Integer> rsb = new ResBo<Integer>();
        rsb.setResult(i);
        return rsb;
    }


    @SuppressWarnings("unchecked")
	@Override
    public ResBo<Pager<List<Coupon>>> getPageByEntity(ReqBo rb) {
        Coupon c = (Coupon) rb.getReqModel();
        boolean onlyInUse = rb.getParamBoolean("onlyInUse");
        Pager<List<Coupon>> lst = ic.selectPage(c,rb.getParamInt("pgIdx"),rb.getParamInt("pgSize"),onlyInUse);
        ResBo<Pager<List<Coupon>>> rsb = new ResBo<Pager<List<Coupon>>>();
        rsb.setResult(lst);
        return rsb;
    }

    @SuppressWarnings("unchecked")
	@Override
    public ResBo<Pager<List<UserCoupon>>> getEntityByPId(ReqBo rb) {
        Long pid = rb.getParamLong("pid");
        int pgIdx = rb.getParamInt("pgIdx");
        int pgSize = rb.getParamInt("pgSize");
        String code = rb.getParamStr("code");
        Pager<List<UserCoupon>> lst = ic.selectUsrCouponByPId(pid,code,pgIdx,pgSize);
        ResBo<Pager<List<UserCoupon>>> rsb = new ResBo<Pager<List<UserCoupon>>>();
        rsb.setResult(lst);
        return rsb;
    }


}
