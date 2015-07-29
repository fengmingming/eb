package net.sls.mobile.client.filter;

import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sls.dto.pc.user.User;
import net.sls.mobile.client.util.Constants;
import net.sls.mobile.client.util.CookieUtil;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import util.framework.SessionUtil;

/**
 * 修改session内容的拦截器
 * @author huzeyu 2015-01-29
 *
 */
public class SessionFilter implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//session中的省市信息为空，则需要设置默认的省市信息或者用户的省市信息
		if(SessionUtil.get(Constants.SESSION_PROVINCEID) == null || 
				SessionUtil.get(Constants.SESSION_CITYID) == null ||
				SessionUtil.get(Constants.SESSION_CITYCODE) == null){
			if(SessionUtil.get(Constants.SESSION_USER_INFO) != null){
				User user = (User)SessionUtil.get(Constants.SESSION_USER_INFO);
				SessionUtil.replace(Constants.SESSION_PROVINCEID, user.getProvince());
				SessionUtil.replace(Constants.SESSION_CITYID, user.getCity());
				SessionUtil.replace(Constants.SESSION_CITYCODE, user.getPavilionCode());
			}else{
				SessionUtil.replace(Constants.SESSION_PROVINCEID, Constants.DEFAULT_PROVINCEID);
				SessionUtil.replace(Constants.SESSION_CITYID, Constants.DEFAULT_CITYID);
				SessionUtil.replace(Constants.SESSION_CITYCODE, Constants.DEFAULT_CITYCODE);
			}
		}
		
		//session中的最后登录用户为空
		if(SessionUtil.get(Constants.COOKIE_LAST_USER) == null){
			Cookie cookie = CookieUtil.getCookie(request, Constants.COOKIE_LAST_USER);
			if(cookie != null){
				SessionUtil.set(Constants.COOKIE_LAST_USER, URLDecoder.decode(cookie.getValue(), "UTF-8"));
			}
		}
		return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
