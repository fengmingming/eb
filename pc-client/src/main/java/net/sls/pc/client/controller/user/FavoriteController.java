package net.sls.pc.client.controller.user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sls.pc.client.util.Constants;
import net.sls.service.pc.user.IMyFavoriteService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import util.framework.FindServiceUtil;
import util.framework.SessionUtil;
import framework.web.Pager;
import framework.web.ReqBo;

/**
 * 前端关注功能
 *
 */
@Controller
@RequestMapping("favorite")
public class FavoriteController {
	/**
	 * 进入我的关注页
	 * @param goodsName(查询需要)
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "toMyFavorite.htm")
	public ModelAndView toMyFavorite(HttpServletRequest request) throws UnsupportedEncodingException {
		IMyFavoriteService mfs = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_MYFAVORITE, IMyFavoriteService.class);
		Long userId = (Long)SessionUtil.get(Constants.SESSION_USER_ID);
		ReqBo reqBo = new ReqBo(request);
		Map<String, Object> query = new HashMap<String, Object>();
		if(reqBo.getParam("goodsName") != null){
			String goodsName = Constants.parseUTF8(reqBo.getParam("goodsName").toString());				
			reqBo.setParam("goodsName", goodsName);
			query.put("goodsName", goodsName);
		}		
		reqBo.setParam("userId", userId);
		if(reqBo.getParam("page") == null || !reqBo.getParam("page").toString().matches("[1-9][0-9]*")){
			reqBo.setParam("page", 1);
		}		
		reqBo.setParam("rows", Constants.ORDER_NUM_PAGE);
		Pager<List<Map<Object, Object>>> pager = mfs.selectMyFavoriteListsByUserId(reqBo).getResult();		
		long total_page = (pager.getTotal()+Constants.ORDER_NUM_PAGE-1)/Constants.ORDER_NUM_PAGE;
		List<Map<Object, Object>> favoriteList = pager.getEntry();	
		if(favoriteList.size() == 0 && reqBo.getParamInt("page") > total_page){
			if(total_page > 0){
				reqBo.setParam("page", total_page);
			}else{
				reqBo.setParam("page", 1);
			}			
			pager = mfs.selectMyFavoriteListsByUserId(reqBo).getResult();
			favoriteList = pager.getEntry();	
		}
		ModelAndView view =  new ModelAndView("user/my_favorite");
		view.addObject("favoriteList", favoriteList);		
		query.put("page", reqBo.getParam("page"));
		view.addObject("query", query);
		view.addObject("total_page", total_page);
		view.addObject(Constants.MENU_ID, Constants.MENU_MY_FAVORITE);
		return view;
	}
	
	/**
	 * 取消关注
	 * @param goodsId
	 * @throws IOException 
	 */
	@RequestMapping(value = "cancleFavorite.htm")
	public String cancleFavorite(HttpServletRequest request) throws IOException{
		IMyFavoriteService mfs = FindServiceUtil.findRemoteService(Constants.SERVICE_URL_MYFAVORITE, IMyFavoriteService.class);
		Long userId = (Long)SessionUtil.get(Constants.SESSION_USER_ID);
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("userId", userId);
		mfs.cancelMyFavorite(reqBo).getResult();		
		return "forward:/favorite/toMyFavorite.htm";
	}
}
