package imgupload;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ImageUtils.ImageUtil;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String basePath;
	private String token;

	public void init(ServletConfig config) throws ServletException {
		String relDir = config.getInitParameter("relDir");
		if (relDir == null) {
			throw new RuntimeException("relDir is null");
		}
		basePath = relDir;
		token = config.getInitParameter("token");
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
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
		String uri = req.getParameter("uri");
		if (uri == null) {
			ResBo<Object> resBo = new ResBo<Object>();
			resBo.setSuccess(false);
			resBo.setErrMsg("uri is null");
			new ObjectMapper().writeValue(resp.getOutputStream(), resBo);
			return;
		}
		File file = new File(basePath + uri);
		//删除压缩图片
		List<String> lst1 = ImageUtil.getImgs();
		for (String s : lst1) {
			File f = new File(basePath+"/"+s+uri);
			if (f.exists()&&file.isFile()) {
				f.delete();
			}
		}
		if (file.exists()&&file.isFile()) {
			file.delete();
		}
		new ObjectMapper().writeValue(resp.getOutputStream(),
				new ResBo<Object>());
		return;
	}

	protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doOptions(req, resp);
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type");
		resp.setHeader("Access-Control-Allow-Methods", "PUT,GET,POST,DELETE,OPTIONS");
	}
}