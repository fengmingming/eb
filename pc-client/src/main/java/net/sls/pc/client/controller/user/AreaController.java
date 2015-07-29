package net.sls.pc.client.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sls.pc.client.annotation.NoNeedUserLogin;
import net.sls.pc.client.util.Constants;
import net.sls.service.pc.product.IAreaService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import util.framework.FindServiceUtil;
import util.model.ComboxDto;
import framework.web.ReqBo;
import framework.web.ResBo;


/**
 * @author dyh 省市区功能
 *
 */
@Controller
@RequestMapping("area")
public class AreaController {
	@NoNeedUserLogin
	@RequestMapping(value = "getAreaList.htm")
	@ResponseBody
	public ResBo<List<ComboxDto>> getCmsUserInfos(
			HttpServletRequest request) {
		ReqBo reqBo = new ReqBo(request);
		IAreaService as = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_AREA, IAreaService.class);
		return as.getAreaList(reqBo);
	}
}
