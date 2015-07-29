package net.sls.controller.coupon;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;
import net.sls.dto.activity.Coupon;
import net.sls.dto.activity.UserCoupon;
import net.sls.service.coupon.ICouponService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import util.framework.FindServiceUtil;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sls006 on 2015/5/21.
 */
@Controller
@RequestMapping("/coupon")
public class CouponController {

    @InitBinder
    public void initBinder(HttpServletRequest request,ServletRequestDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    @RequestMapping("/addNew.htm")
    @ResponseBody
    public ResBo addNew(@ModelAttribute Coupon coupon){
    	if(coupon.getLimitType() == 2){
    		coupon.setNum(0);
    	}else{
    		coupon.setNum(coupon.getTotal());
    	}
        coupon.setIsDel(1);
        coupon.setGenDate(new Date());
        List lst = new ArrayList<Coupon>();
        lst.add(coupon);
        ReqBo rb = new ReqBo();
        rb.setReqModel(lst);
        ICouponService ics = FindServiceUtil.findService(ICouponService.class);
        int rtn = ics.insertEntity(rb).getResult();
        Boolean succeed = false;
        if(rtn == lst.size())
            succeed = true;
        ResBo<Boolean> rsb = new ResBo<Boolean>();
        rsb.setResult(succeed);
        return rsb;
    }

    /**
     *
     * @param coupon    描述优惠券
     * @param page
     * @param rows
     * @param onlyInUse
     * @return
     */
    @RequestMapping("/query.htm")
    @ResponseBody
    public ResBo<Pager<List<Coupon>>> query(@ModelAttribute Coupon coupon,@RequestParam("page")int page,
                                            @RequestParam("rows")int rows,@RequestParam(value = "onlyInUse",required = false)boolean onlyInUse){
        ReqBo rb = new ReqBo();
        rb.setReqModel(coupon);
        rb.setParam("pgIdx",page);
        rb.setParam("pgSize",rows);
        boolean oiu = onlyInUse || false;
        rb.setParam("onlyInUse",oiu);
        ICouponService ics = FindServiceUtil.findService(ICouponService.class);
        return ics.getPageByEntity(rb);
    }

    /**
     *
     * 通过优惠券号的pid查询所有此批优惠券
     * @param pid
     * @return
     */
    @RequestMapping("getOfflineCoupon")
    @ResponseBody
    public ResBo<Pager<List<UserCoupon>>> export(@RequestParam("pid") long pid) {
        ReqBo rb = new ReqBo();
        rb.setParam("pid",pid);
        rb.setParam("pgIdx",1);
        rb.setParam("pgSize",Integer.MAX_VALUE);
        ICouponService ics = FindServiceUtil.findService(ICouponService.class);
        return ics.getEntityByPId(rb);
    }


    @RequestMapping("userCoupon.htm")
    @ResponseBody
    public ResBo<Pager<List<UserCoupon>>> userCoupon(@RequestParam(value="pid",required=false)Long pid,@RequestParam(value="code",required=false)String code,@RequestParam("page")int pgIdx,@RequestParam("rows")int pgSize){

        ReqBo rb = new ReqBo();
        rb.setParam("pid",pid);
        rb.setParam("pgIdx",pgIdx);
        rb.setParam("pgSize",pgSize);
        rb.setParam("code", code);
        ICouponService ics = FindServiceUtil.findService(ICouponService.class);
        return ics.getEntityByPId(rb);
    }

    @RequestMapping("del.htm")
    @ResponseBody
    public ResBo<?> delByIds(@RequestParam("ids") String ids){
        String[] strIds = ids.split(",");
        int len = strIds.length;
        long[] l = new long[len];
        for(int i=0;i<len;i++){
            l[i]= Long.parseLong(strIds[i]);
        }
        ReqBo rb = new ReqBo();
        rb.setParam("ids",l);
        ICouponService ics = FindServiceUtil.findService(ICouponService.class);
        return ics.deleteEntity(rb);
    }


    @RequestMapping("/add.htm")
    public String add(){ return "coupon/addNew"; }
    @RequestMapping("/qry.htm")
    public String qry(){ return "coupon/query"; }
}
