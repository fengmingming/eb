package imgupload;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class LoginAuth {

	private final static JedisPool pool = new JedisPool("redis.cache.365020.com", 6379);
	
	public static boolean isLogin(HttpServletRequest req,String token){
		String t = req.getParameter("token");
		if(t==null&&token!=null&&req.getCookies() != null){
			for(Cookie cookie:req.getCookies()){
				if(cookie.getName().equals(token)){
					t = cookie.getValue();
					break;
				}
			}
		}
		if(t == null){
			return false;
		}
		Jedis jedis = pool.getResource();
		boolean b = jedis.exists(t);
		pool.returnResource(jedis);
		return b;
	}
	
}
