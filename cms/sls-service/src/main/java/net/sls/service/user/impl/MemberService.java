package net.sls.service.user.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sls.businessconstant.BusinessContant;
import net.sls.component.product.IAreaComponent;
import net.sls.component.user.IMemberComponent;
import net.sls.dto.user.AmountOrder;
import net.sls.dto.user.User;
import net.sls.service.user.IMemberService;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.framework.MD5Util;
import util.framework.StrUtil;
import framework.exception.BusinessException;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Service
public class MemberService implements IMemberService {

	@Autowired
	private IMemberComponent memberComponent;

	@Autowired
	private IAreaComponent areaComponent;

	@Override
	public ResBo<List<User>> selectMemberInfo(ReqBo reqBo) {
		return new ResBo<List<User>>(memberComponent.selectMemberInfo(reqBo
				.getParam("username").toString(), reqBo.getParam("mobile")
				.toString()));
	}

	@Override
	public ResBo<Pager<List<User>>> selectMemberList(ReqBo reqBo) {
		return new ResBo<Pager<List<User>>>(memberComponent.selectMemberInfos(
				reqBo.getParamInt("memberType"),
				reqBo.getParamStr(BusinessContant.OPERAREAID),
				reqBo.getParamStr("username"), reqBo.getParamStr("mobile"),
				reqBo.getParamLong("pavilionId"),
				reqBo.getParamDate("startDate", null, "yyyy-MM-dd"),
				reqBo.getParamDate("endDate", null, "yyyy-MM-dd"),
				reqBo.getParamInt("page"), reqBo.getParamInt("rows")));
	}

	@Override
	public ResBo<?> updateRePass(ReqBo reqBo) {
		memberComponent.updateRePass(reqBo.getParamLong("userId"),
				reqBo.getParamStr("repass"));
		return new ResBo<Object>();
	}

	@Override
	public ResBo<?> updateUnbindMobile(ReqBo reqBo) {
		memberComponent.updateUnbindMobile(reqBo.getParamLong("userId"),
				reqBo.getParamStr("mobile"));
		return new ResBo<Object>();
	}

	@Override
	public ResBo<?> insertMember(ReqBo reqBo) {
		User user = reqBo.getReqModel(User.class);
		user.setPassword(MD5Util.getMD5Str(user.getPassword()));
		if (memberComponent.selectUserByUserName(user.getUserName()).size() > 0) {
			return new ResBo<Object>(19L);
		}
		if (memberComponent.selectMobileIsExist(user.getMobile())) {
			return new ResBo<Object>(23L);
		}
		user.setPavilionCode(areaComponent.selectAreaCodeById(user
				.getPavilionId()));
		memberComponent.insertUser(user);
		return new ResBo<Object>();
	}

	@Override
	public ResBo<User> selectUserByUserName(ReqBo reqBo) {
		List<User> list = memberComponent.selectUserByUserName(reqBo
				.getParamStr("userName"));
		if (list.size() == 0) {
			return new ResBo<User>();
		} else if (list.size() > 1) {
			throw new BusinessException(20L, reqBo.getParamStr("userName"));
		} else {
			return new ResBo<User>(list.get(0));
		}

	}

	@Override
	public ResBo<Pager<List<AmountOrder>>> selectAmountOrderList(ReqBo reqBo) {
		return new ResBo<Pager<List<AmountOrder>>>(
				memberComponent.selectAmountOrderList(
						reqBo.getParamLong("userId"),
						reqBo.getParamInt("page"), reqBo.getParamInt("rows")));
	}

	@Override
	public ResBo<Pager<List<Map<String, Object>>>> selectAmountLogList(
			ReqBo reqBo) {
		return new ResBo<Pager<List<Map<String, Object>>>>(
				memberComponent.selectAmountLogList(
						reqBo.getParamLong("userId"),
						reqBo.getParamDate("startDate"),
						reqBo.getParamDate("endDate"),
						reqBo.getParamInt("page"), reqBo.getParamInt("rows")));
	}

	@Override
	public ResBo<?> updateAccountOrder(ReqBo reqBo) {
		memberComponent.updateCompleteAccountOrder(reqBo.getParamLong("id"));
		return new ResBo<Object>();
	}

	@Override
	public ResBo<Workbook> exportExcel(ReqBo reqBo) {
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("余额充值消费记录");
		Row row = sheet.createRow(0);
		row.createCell(0, XSSFCell.CELL_TYPE_STRING).setCellValue("用户名");
		row.createCell(1, XSSFCell.CELL_TYPE_STRING).setCellValue("金额");
		row.createCell(2, XSSFCell.CELL_TYPE_STRING).setCellValue("操作人");
		row.createCell(3, XSSFCell.CELL_TYPE_STRING).setCellValue("类型");
		row.createCell(4, XSSFCell.CELL_TYPE_STRING).setCellValue("创建时间");
		row.createCell(5, XSSFCell.CELL_TYPE_STRING).setCellValue("备注");
		List<Map<String, Object>> list = memberComponent.selectExportExcel(
				reqBo.getParamLong("userId"), reqBo.getParamDate("startDate"),
				reqBo.getParamDate("endDate"));
		int i = 1;
		for (Map<String, Object> map : list) {
			row = sheet.createRow(i);
			row.createCell(0, XSSFCell.CELL_TYPE_STRING).setCellValue(
					StrUtil.toString(map.get("userName")));
			row.createCell(1, XSSFCell.CELL_TYPE_STRING).setCellValue(
					StrUtil.toString(map.get("money")));
			row.createCell(2, XSSFCell.CELL_TYPE_STRING).setCellValue(
					StrUtil.toString(map.get("operator")));
			if ("1".equals(map.get("type").toString())) {
				row.createCell(3, XSSFCell.CELL_TYPE_STRING).setCellValue("存入");
			} else {
				row.createCell(3, XSSFCell.CELL_TYPE_STRING).setCellValue("支出");
			}
			row.createCell(4, XSSFCell.CELL_TYPE_STRING).setCellValue(
					StrUtil.toString(map.get("createTime")));
			row.createCell(5, XSSFCell.CELL_TYPE_STRING).setCellValue(
					StrUtil.toString(map.get("remark")));
			i++;
		}
		return new ResBo<Workbook>(wb);
	}

	@Override
	public ResBo<?> updateMemberInfo(ReqBo reqBo) {
		memberComponent.updateMemberInfo(reqBo.getReqModel(User.class));
		return new ResBo<Object>();
	}

	@Override
	public ResBo<?> financeRechange(ReqBo reqBo) {
		memberComponent.updateFinanceRechange(reqBo.getParamLong("userId"),
				reqBo.getParamDecimal("money"));
		return new ResBo<Object>();
	}

	@Override
	public ResBo<Workbook> exportExcels(ReqBo reqBo) {
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("余额充值消费记录");
		Row row = sheet.createRow(0);
		row.createCell(0, XSSFCell.CELL_TYPE_STRING).setCellValue("用户名");
		row.createCell(1, XSSFCell.CELL_TYPE_STRING).setCellValue("金额");
		row.createCell(2, XSSFCell.CELL_TYPE_STRING).setCellValue("操作人");
		row.createCell(3, XSSFCell.CELL_TYPE_STRING).setCellValue("类型");
		row.createCell(4, XSSFCell.CELL_TYPE_STRING).setCellValue("创建时间");
		row.createCell(5, XSSFCell.CELL_TYPE_STRING).setCellValue("备注");
		List<Long> ids = new ArrayList<Long>();
		String idsStr = reqBo.getParamStr("ids");
		for (String id : idsStr.split(",")) {
			ids.add(Long.valueOf(id));
		}
		List<Map<String, Object>> list = memberComponent.selectExportExcel(ids,
				reqBo.getParamDate("startDate", null, "yyyy-MM-dd"),
				reqBo.getParamDate("endDate", null, "yyyy-MM-dd"));
		int i = 1;
		for (Map<String, Object> map : list) {
			row = sheet.createRow(i);
			row.createCell(0, XSSFCell.CELL_TYPE_STRING).setCellValue(
					StrUtil.toString(map.get("userName")));
			row.createCell(1, XSSFCell.CELL_TYPE_STRING).setCellValue(
					StrUtil.toString(map.get("money")));
			row.createCell(2, XSSFCell.CELL_TYPE_STRING).setCellValue(
					StrUtil.toString(map.get("operator")));
			if ("1".equals(map.get("type").toString())) {
				row.createCell(3, XSSFCell.CELL_TYPE_STRING).setCellValue("存入");
			} else {
				row.createCell(3, XSSFCell.CELL_TYPE_STRING).setCellValue("支出");
			}
			row.createCell(4, XSSFCell.CELL_TYPE_STRING).setCellValue(
					StrUtil.toString(map.get("createTime")));
			row.createCell(5, XSSFCell.CELL_TYPE_STRING).setCellValue(
					StrUtil.toString(map.get("remark")));
			i++;
		}
		return new ResBo<Workbook>(wb);
	}

	@Override
	public ResBo<?> analysisUser(ReqBo reqBo) {
		return new ResBo<List<Map<String, Object>>>(
				memberComponent.selectAnalysisUser(reqBo.getParamLong("userId")));
	}

	@Override
	public ResBo<?> excel_user(ReqBo reqBo) {
		List<Map<String,Object>> list = memberComponent.selectExcelUser(reqBo.getParamLong("pavilionId"), reqBo.getParamDate("startDate", null, "yyyy-MM-dd"), reqBo.getParamDate("endDate", null, "yyyy-MM-dd"));
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet();
		Row row = sheet.createRow(0);
		row.createCell(0,XSSFCell.CELL_TYPE_STRING).setCellValue("用户名");
		row.createCell(1,XSSFCell.CELL_TYPE_STRING).setCellValue("亭子名称");
		row.createCell(2,XSSFCell.CELL_TYPE_STRING).setCellValue("注册时间");
		row.createCell(3,XSSFCell.CELL_TYPE_STRING).setCellValue("手机号");
		int i = 1;
		for(Map<String,Object> map:list){
			row = sheet.createRow(i);
			row.createCell(0,XSSFCell.CELL_TYPE_STRING).setCellValue(map.get("username").toString());
			if(map.get("pavilionName")!=null){
				row.createCell(1,XSSFCell.CELL_TYPE_STRING).setCellValue(map.get("pavilionName").toString());
			}
			if(map.get("createTime")!=null){
				row.createCell(2,XSSFCell.CELL_TYPE_STRING).setCellValue(map.get("createTime").toString());
			}
			if(map.get("mobile")!=null){
				row.createCell(3,XSSFCell.CELL_TYPE_STRING).setCellValue(map.get("mobile").toString());
			}
			i++;
		}
		return new ResBo<Workbook>(wb);
	}

	@Override
	public ResBo<?> updatePayPass(ReqBo reqBo) {
		memberComponent.updatePayPass(reqBo.getParamLong("userId"));
		return new ResBo<Object>();
	}

	@Override
	public ResBo<?> getUserInfo(ReqBo reqBo) {
		List<Map<Object,Object>> list = memberComponent.selectUserInfo(reqBo.getParamStr("userName"), reqBo.getParamStr("mobile"));
		ResBo<Map<Object,Object>> resBo = new ResBo<Map<Object,Object>>();
		if(list.size() > 0){
			resBo.setResult(list.get(0));
		}
		return resBo;
	}
}
