package net.sls.mobile.client.controller;


import javax.servlet.http.HttpServletRequest;

import net.sls.mobile.client.annotation.NoNeedUserLogin;
import net.sls.mobile.client.util.Constants;
import net.sls.service.pc.product.IBroadcastService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * @author dyh 首页数据展示
 *
 */
@Controller
public class MainController {
	//M站首页
	@NoNeedUserLogin
	@RequestMapping(value = "index.htm")
	@ResponseBody
	public ResBo<?> index(HttpServletRequest request) {
		ReqBo reqBo = new ReqBo(request);
		//用户所在的省市
		Integer provinceId = reqBo.getParamInt("provinceId");
		Integer cityId = reqBo.getParamInt("cityId");
		String cityCode = reqBo.getParamStr("cityCode");
		if (cityId != null && provinceId != null && cityCode != null) {
			SessionUtil.replace(Constants.SESSION_PROVINCEID, provinceId);
			SessionUtil.replace(Constants.SESSION_CITYID, cityId);
			SessionUtil.replace(Constants.SESSION_CITYCODE, cityCode);
		}
		//轮播图
		reqBo.setParam("areaId", (Integer)SessionUtil.get(Constants.SESSION_CITYID));
		reqBo.setParam("areaCode", (String)SessionUtil.get(Constants.SESSION_CITYCODE));
		reqBo.setParam("channelId", 2);
		IBroadcastService bs = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_BROADCAST, IBroadcastService.class);
		return bs.getBroadcastList(reqBo);
	}

}
