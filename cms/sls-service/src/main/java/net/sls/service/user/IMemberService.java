package net.sls.service.user;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import net.sls.dto.user.AmountOrder;
import net.sls.dto.user.User;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IMemberService {
	
	/**
	 * 查询会员用户信息
	 * 
	 * */
	
	public ResBo<List<User>> selectMemberInfo(ReqBo reqBo);
	
	 /**
	  * 
	  * 查询会员信息列表
	  * 
	  * */
	 public ResBo<Pager<List<User>>> selectMemberList(ReqBo reqBo);
	 
	 /**
	  * 重置密码
	  * */
	 public ResBo<?> updateRePass(ReqBo reqBo);
	 
	 public ResBo<?> updatePayPass(ReqBo reqBo);
	 
	 public ResBo<?> updateUnbindMobile(ReqBo reqBo);
	 
	 public ResBo<?> insertMember(ReqBo reqBo);
	 
	 public ResBo<User> selectUserByUserName(ReqBo reqBo); 
	 
	 public ResBo<Pager<List<AmountOrder>>> selectAmountOrderList(ReqBo reqBo);
	 
	 public ResBo<Pager<List<Map<String,Object>>>> selectAmountLogList(ReqBo reqBo);
	 
	 public ResBo<?> updateAccountOrder(ReqBo reqBo);
	 
	 public ResBo<Workbook> exportExcel(ReqBo reqBo);
	 
	 public ResBo<Workbook> exportExcels(ReqBo reqBo);
	 
	 public ResBo<?> updateMemberInfo(ReqBo reqBo);
	 
	 public ResBo<?> financeRechange(ReqBo reqBo);
	 
	 public ResBo<?> analysisUser(ReqBo reqBo);
	 
	 public ResBo<?> excel_user(ReqBo reqBo);
	 
	 public ResBo<?> getUserInfo(ReqBo reqBo);
	 
}
