package net.sls.component.coupon;

import framework.web.Pager;
import net.sls.dao.activity.CouponMapper;
import net.sls.dao.activity.UserCouponMapper;
import net.sls.dao.ext.coupon.ICouponExt;
import net.sls.dao.ext.coupon.UserCouponMapperExt;
import net.sls.dto.activity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/**
 * Created by sls006 on 2015/5/21.
 */
@SuppressWarnings("restriction")
@Component
public class CouponComponent implements ICouponComponent<Coupon> {

    private static final int CODE_LEN = 10;

    @Autowired
    private CouponMapper cp ;
    @Autowired
    private ICouponExt ce;
    @Autowired
    private UserCouponMapper ucm;
    @Autowired
    private UserCouponMapperExt ucmx;

    @Override
    public int insertEntity(List<Coupon> lst) {
        int i = 0;
        HashSet<String> set = null;
        for (Coupon c: lst){
            int type = c.getLimitType();
            switch (type){
                case 1:     // 注册优惠券
                    cp.insert(c);
                    break;
                case 2:     // 线下优惠券
                    int cnt = c.getTotal();
                    ce.insertCoupon(c);
                    Long pid = c.getId();
                    // 生成指定数量的线下优惠券
                    set = new HashSet<String>();
                    do{
                        String rad = util.framework.MD5Util.getMD5Str(System.currentTimeMillis() + "");
                        int len = pid.toString().length();
                        int lit = CODE_LEN -len;
                        len = lit>0?lit:-lit;
                        int xrad = new Random().nextInt(0x10);
                        rad = rad.substring(xrad,xrad+len);
                        set.add(pid+rad);
                    }while (set.size() < cnt);

                    // add to insert fk table
                    UserCoupon uc = new UserCoupon();
                    for(String s : set){
                        uc.setCode(s);
                        uc.setCouponId(c.getId());
                        uc.setGenDate(c.getGenDate());
                        uc.setUsrId(0L);
                        ucm.insert(uc);
                    }
                    break;
                default:
                    break;
            }
            i++;
        }
        return i;
    }

    @Override
    public int deleteEntity(List<Coupon> lst) {
        int i = 0;
        for(Coupon c:lst){
            cp.deleteByPrimaryKey(c.getId());
            i++;
        }
        return i;
    }

    @Override
    public int deleteEntityInLogic(long[] ids) {
        return ce.deleteEntityInLogic(ids);
    }

    @Override
    public List<Coupon> queryFetchedCoupon() {
        return ce.queryFetchedCoupon();
    }

    @Override
    public long queryCount(Coupon coupon) {
        return ce.queryCount();
    }

    @Override
    public int deleteByIds(Coupon coupon, long[] ids) {
        int i = 0;
        for(;i<ids.length;i++){
            cp.deleteByPrimaryKey(ids[i]);
        }
        return i+1;
    }

    @Override
    public List<Coupon> queryByIds(Coupon coupon, long[] ids) {
        throw new NotImplementedException();//"This mathod is not Implemnet this version");
    }

    @Override
    public Pager<List<Coupon>> selectPage(Coupon coupon, int pgIdx, int pgSize) {
        return selectPage(coupon,pgIdx,pgSize,false);
    }

    @Override
    public Pager<List<Coupon>> selectPage(Coupon coupon, int pgIdx, int pgSize,boolean onlyInUse) {
        if (null != coupon){
            long cnt = ce.queryCountByEntity(coupon,onlyInUse);
            List<Coupon> lst = ce.queryPageByEntity(coupon,(pgIdx-1)*pgSize,pgSize,onlyInUse);
            return new Pager<List<Coupon>> (lst,cnt);
        }
        else {
            long cnt = ce.queryCount();
            return new Pager<List<Coupon>>(ce.queryPage((pgIdx - 1) * pgSize, pgSize),cnt) ;
        }
    }

    @Override
    public Pager<List<UserCoupon>> selectUsrCouponByPId(Long pid,String code,int pgIdx,int pgSize) {
        long cnt = ucmx.queryCount(pid,code);
        List<UserCoupon> ucl = ucmx.queryUsrCouponByPId(pid,code,(pgIdx-1)*pgSize,pgSize);
        Pager<List<UserCoupon>> pgr = new Pager<List<UserCoupon>>();
        pgr.setEntry(ucl);
        pgr.setTotal(cnt);
        return pgr;
    }
}
