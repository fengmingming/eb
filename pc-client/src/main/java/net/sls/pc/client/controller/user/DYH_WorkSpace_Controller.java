package net.sls.pc.client.controller.user;

import javax.servlet.http.HttpServletRequest;

import net.sls.pc.client.annotation.NoNeedUserLogin;
import net.sls.pc.client.util.Constants;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("dyh")
public class DYH_WorkSpace_Controller {
	/*
	 * 说明：此controller文件，是为了访问我新增的页面用的，因为，起初，我并不是十分确定访问我新增页面的controller
	 * 应该写到哪个controller文件里，所以，我临时写到了这个“DYH_WorkSpace_Controller.java”文件里，这样的话，我
	 * 也可以不用影响到各位开发者写的controller文件，然后，还能方便的让各位开发者选择考虑，访问新增页面的controller
	 * 完美的添加到各位开发者自己的controller文件里，^_^
	 * 
	 * 各位开发者，只需要“复制”下面的代码，粘贴到你们需要的controller文件里，再稍作修改即可
	 * 
	 * 与此类似的还有css文件夹和pages文件夹下的DYH_WorkSpace目录
	 */
	
	/**
	 * 临时页面***我的优惠券
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "toMyCoupon.htm")
	public ModelAndView toMyCoupon(HttpServletRequest request){
		ModelAndView view = new ModelAndView("DYH_WorkSpace/my_coupon");
		
		view.addObject(Constants.MENU_ID, "my_coupon");
		return view;
	}
	
	/**
	 * 临时页面***退换货
	 * @param request
	 * @return
	 */
	@NoNeedUserLogin
	@RequestMapping(value = "toGoodsRefund.htm")
	public ModelAndView toGoodsRefund(HttpServletRequest request){
		ModelAndView view = new ModelAndView("DYH_WorkSpace/goods_refund");
		
		//view.addObject(Constants.MENU_ID, "my_coupon");
		return view;
	}
}
