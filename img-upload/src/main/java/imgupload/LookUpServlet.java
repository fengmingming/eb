package imgupload;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LookUpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String basePath;
	private String token;
	
	public void init(ServletConfig config) throws ServletException{
		String relDir = config.getInitParameter("relDir");
		token = config.getInitParameter("token");
		if(relDir == null){
			throw new RuntimeException("relDir is null");
		}
		basePath = relDir;
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type");
		resp.setHeader("Access-Control-Allow-Methods", "PUT,GET,POST,DELETE,OPTIONS");
		if(token != null&&!LoginAuth.isLogin(req, token)){
			ResBo<String> resBo = new ResBo<String>();
			resBo.setSuccess(false);
			resBo.setErrMsg("user is not exist");
			new ObjectMapper().writeValue(resp.getOutputStream(), resBo);
			return ;
		}
		String date = req.getParameter("date");
		String prifix = req.getParameter("prifix");
		if(date == null){
			ResBo<String> resBo = new ResBo<String>();
			resBo.setSuccess(false);
			resBo.setErrMsg("date is null");
			new ObjectMapper().writeValue(resp.getOutputStream(), resBo);
			return ;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy/MM/dd");
		Date d = null;
		String path = null;
		try {
			d = format.parse(date);
			path = format2.format(d);
		} catch (ParseException e) {
			ResBo<String> resBo = new ResBo<String>();
			resBo.setSuccess(false);
			resBo.setErrMsg(e.getMessage());
			new ObjectMapper().writeValue(resp.getOutputStream(), resBo);
			return ;
		}
		path = "/"+prifix+"/"+path;
		String relPath = basePath+path;
		File file = new File(relPath);
		File[] files = file.listFiles();
		List<String> list = new ArrayList<String>();
		if(files != null){
			for(File f:files){
				list.add(path+"/"+f.getName());
			}
		}
		new ObjectMapper().writeValue(resp.getOutputStream(), new ResBo<List<String>>(list));
		return ;
	}
	
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doOptions(req, resp);
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type");
		resp.setHeader("Access-Control-Allow-Methods", "PUT,GET,POST,DELETE,OPTIONS");
	}
	
}
