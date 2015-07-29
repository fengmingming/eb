package net.sls.controller.member;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sls.businessconstant.BusinessContant;
import net.sls.dto.user.AmountOrder;
import net.sls.dto.user.User;
import net.sls.service.user.IMemberService;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import framework.exception.BusinessException;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * 
 * 后台用户管理功能
 * 
 * */
@Controller
@RequestMapping("member")
public class MemberController {
	
	@RequestMapping("initNavigater.htm")
	public String initmemberManage(){
		return "navigater/background";
	}
	
	@RequestMapping("search.htm")
	public String initmemberSearch(){
		return "member/searchMember";
	}
	/**
	 * 会员查询
	 * @param request
	 * @return
	 */
	@RequestMapping(value="memberSearch.htm")
	@ResponseBody
	public ResBo<Pager<List<User>>> getMemberInfos(HttpServletRequest request){   
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam(BusinessContant.OPERAREAID, SessionUtil.get(BusinessContant.OPERAREAID));
		IMemberService us = FindServiceUtil.findService(IMemberService.class);
		return us.selectMemberList(reqBo);
	}
	
	@RequestMapping("repassword.htm")
	@ResponseBody
	public ResBo<?> rePassword(@RequestParam("userid")Long userId,@RequestParam("repass") String repass){
		IMemberService us = FindServiceUtil.findService(IMemberService.class);
		return us.updateRePass(new ReqBo().setParam("userId", userId).setParam("repass", repass));
	}
	
	@RequestMapping("repaypass.htm")
	@ResponseBody
	public ResBo<?> rePayPassword(@RequestParam("userId")Long userId){
		IMemberService us = FindServiceUtil.findService(IMemberService.class);
		return us.updatePayPass(new ReqBo().setParam("userId", userId));
	}
	
	@RequestMapping("updatemember.htm")
	@ResponseBody
	public ResBo<?> updateMemberInfos(@ModelAttribute User user){
		IMemberService us = FindServiceUtil.findService(IMemberService.class);
		return us.updateMemberInfo(new ReqBo().setReqModel(user));
	}
	
	@RequestMapping("unbindmobile.htm")
	@ResponseBody
	public ResBo<?> deleteMobile(@RequestParam("userId") long userId,@RequestParam(value="mobile",required=false)String mobile){
		IMemberService us = FindServiceUtil.findService(IMemberService.class);
		return us.updateUnbindMobile(new ReqBo().setParam("userId", userId).setParam("mobile", mobile));
	}
	
	@RequestMapping("addmember.htm")
	@ResponseBody
	public ResBo<?> addMember(@ModelAttribute User user){
		user.setCreateTime(new Date());
		IMemberService us = FindServiceUtil.findService(IMemberService.class);
		return us.insertMember(new ReqBo(user));
	}
	
	@RequestMapping("isexist.htm")
	@ResponseBody
	public ResBo<Boolean> isExistUserName(@RequestParam("userName") String userName){
		IMemberService us = FindServiceUtil.findService(IMemberService.class);
		ResBo<User> rs = us.selectUserByUserName(new ReqBo().setParam("userName", userName));
		if(rs.getResult() == null){
			return new ResBo<Boolean>(false);
		}
		return new ResBo<Boolean>(true);
	}
	
	@RequestMapping("initaccount.htm")
	public String initAccountPage(@RequestParam("userId") long userId,Model model){
		model.addAttribute("userId", userId);
		return "member/account";
	}
	
	
	@RequestMapping("recharge.htm")
	@ResponseBody
	public ResBo<Pager<List<AmountOrder>>> recharge(HttpServletRequest req){
		IMemberService us = FindServiceUtil.findService(IMemberService.class);
		return us.selectAmountOrderList(new ReqBo(req));
	}
	
	@RequestMapping("log.htm")
	@ResponseBody
	public ResBo<Pager<List<Map<String,Object>>>> log(HttpServletRequest req){
		IMemberService us = FindServiceUtil.findService(IMemberService.class);
		return us.selectAmountLogList(new ReqBo(req));
	}
	
	@RequestMapping("updateaccountorder.htm")
	@ResponseBody
	public ResBo<?> updateMemberAccountOrder(@RequestParam("id") long id){
		IMemberService us = FindServiceUtil.findService(IMemberService.class);
		return us.updateAccountOrder(new ReqBo().setParam("id", id));
	}
	
	@RequestMapping("excel.htm")
	public void exportExcel(@RequestParam("userId") long userId,HttpServletRequest req,HttpServletResponse res) throws Exception{
		IMemberService service = FindServiceUtil.findService(IMemberService.class);
		ResBo<Workbook> resBo = service.exportExcel(new ReqBo(req));
		if(resBo.isSuccess()){
			res.setHeader("Content-Type","application/force-download");
	        res.setHeader("Content-Type","application/vnd.ms-excel;charset=utf8"); 
	        res.setHeader("Content-disposition", "attachment; filename="+userId+".xlsx");
			Workbook book = resBo.getResult();
			book.write(res.getOutputStream());
			res.getOutputStream().flush();
		}else{
			throw new BusinessException(18L);
		}
	}
	
	@RequestMapping("excels.htm")
	public void exportExcels(HttpServletRequest req,HttpServletResponse res) throws Exception{
		IMemberService service = FindServiceUtil.findService(IMemberService.class);
		ResBo<Workbook> resBo = service.exportExcels(new ReqBo(req));
		if(resBo.isSuccess()){
			res.setHeader("Content-Type","application/force-download");
	        res.setHeader("Content-Type","application/vnd.ms-excel;charset=utf8"); 
	        res.setHeader("Content-disposition", "attachment; filename=excel.xlsx");
			Workbook book = resBo.getResult();
			book.write(res.getOutputStream());
			res.getOutputStream().flush();
		}else{
			throw new BusinessException(18L);
		}
	}
	
	@RequestMapping("finance_recharge.htm")
	@ResponseBody
	public ResBo<?> financeRecharge(@RequestParam("userId")long userId,@RequestParam("money")BigDecimal money){
		IMemberService service = FindServiceUtil.findService(IMemberService.class);
		return service.financeRechange(new ReqBo().setParam("userId", userId).setParam("money", money));
	}
	
	
	@RequestMapping("excel_user.htm")
	public void user_excel(HttpServletRequest req,HttpServletResponse res) throws Exception{
		IMemberService service = FindServiceUtil.findService(IMemberService.class);
		ResBo<?> resBo = service.excel_user(new ReqBo(req));
		if(resBo.isSuccess()){
			res.setHeader("Content-Type","application/force-download");
	        res.setHeader("Content-Type","application/vnd.ms-excel;charset=utf8"); 
	        res.setHeader("Content-disposition", "attachment; filename=users.xlsx");
			Workbook book = (Workbook) resBo.getResult();
			book.write(res.getOutputStream());
			res.getOutputStream().flush();
		}else{
			throw new BusinessException(18L);
		}
	}
	
	@RequestMapping("userinfo.htm")
	@ResponseBody
	public ResBo<?> getUserInfo(HttpServletRequest req){
		IMemberService service = FindServiceUtil.findService(IMemberService.class);
		return service.getUserInfo(new ReqBo(req));
	}
}
