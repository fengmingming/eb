package imgupload;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import ImageUtils.ImageUtil;

public class ImgUploadServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private int maxbytes = 1024*1000;
	private int tmpbytes = 1024*800;
	private String tmpDir;
	private String relDir;
	private String token;
	private Map<String,String> dirPrefixs = new HashMap<String,String>();
	private IImgUploadResponseHandler handler;
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		String maxbytes = config.getInitParameter("maxbytes");
		String tmpbytes = config.getInitParameter("tmpbytes");
		String handler = config.getInitParameter("handler");
		tmpDir = config.getInitParameter("tmpDir");
		relDir = config.getInitParameter("relDir");
		token = config.getInitParameter("token");
		if(maxbytes != null){
			this.maxbytes = Integer.parseInt(maxbytes);
		}
		if(tmpbytes != null){
			this.tmpbytes = Integer.parseInt(tmpbytes);
		}
		if(relDir == null|| !(new File(relDir)).isDirectory()){
			throw new ServletException("relDir not set in web.xml or relDir directory not exist");
		}
		if(relDir.endsWith("/")){
			relDir = relDir.substring(0, relDir.length()-1);
		}
		try{
			if(handler != null){
				this.handler = (IImgUploadResponseHandler) Class.forName(handler).newInstance();
			}else{
				this.handler = (IImgUploadResponseHandler) Class.forName("imgupload.DefaultImgUploadResponseHandler").newInstance();
			}
		}catch(Exception e){
			throw new ServletException(e);
		}
		Enumeration<String> enums = config.getInitParameterNames();
		while(enums.hasMoreElements()){
			String name = enums.nextElement();
			if("maxbytes".equals(name)||"tmpDir".equals(name)||"tmpbytes".equals(name)||"handler".equals(name)||"relDir".equals(name)){
				continue;
			}
			dirPrefixs.put(name, config.getInitParameter(name));
		}
		if(dirPrefixs.size() == 0){
			throw new ServletException("store dirPrefixs not init");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		        throws ServletException, IOException{
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type");
		resp.setHeader("Access-Control-Allow-Methods", "PUT,GET,POST,DELETE,OPTIONS");
		if(token!=null&&!LoginAuth.isLogin(req, token)){
			handler.responseHandler(req, resp, null, null, null, new RuntimeException("user is not exist"));
			return;
		}
		if("create".equals(req.getParameter("create"))){
			List<String> paths = new ArrayList<String>();
			paths.add(req.getParameter("path"));
			try{
				ImageUtil.imageDispatch(relDir,paths);
				handler.responseHandler(req, resp, null, null, null, null);
			}catch(Exception e){
				e.printStackTrace();
				handler.responseHandler(req, resp, null, null, null, e);
			}
			return;
		}
		String uploadType = req.getParameter("upload_type");
		if(uploadType == null){
			handler.responseHandler(req, resp, null, null, null, new RuntimeException("request params is not contain upload_type"));
			return;
		}
		String dirPrefix = dirPrefixs.get(uploadType);
		if(dirPrefix == null){
			handler.responseHandler(req, resp, null, null, null, new RuntimeException("do not find dirPrefix by upload_type"));
			return;
		}
		if(!ServletFileUpload.isMultipartContent(req)){
			handler.responseHandler(req, resp, null, null, null, new RuntimeException("request is not multipart content"));
			return;
		}
		List<FileItem> items = null;
		final List<String> paths = new ArrayList<String>();
		FileItem curItem = null;
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			if(tmpDir != null){
				factory.setRepository(new File(tmpDir));
				factory.setSizeThreshold(tmpbytes);
			}
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(maxbytes);
			items = upload.parseRequest(req);
			if(items != null&&items.size() > 0){
				for(FileItem item: items){
					curItem = item;
					if(!item.isFormField()){
						String path = createStoreFile(dirPrefix,item.getName());
						FileUtils.copyInputStreamToFile(item.getInputStream(), new File(relDir+path));
						paths.add(path);
					}
				}
				String small = req.getParameter("small");
				if ("small".equals(small)&&paths.size() > 0) {
					new Thread(){
						public void run(){
							ImageUtil.imageDispatch(relDir,paths);
						}
					}.start();
				}
			}else{
				handler.responseHandler(req, resp, null, null, null, new RuntimeException("file is null in request"));
				return;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			handler.responseHandler(req, resp, items, paths, curItem, e);
			return;
		}
		handler.responseHandler(req, resp, items, paths, null, null);
	}
	
	protected String createStoreFile(String dirPrefix, String filename) throws Exception{
		int index = filename.lastIndexOf(".");
		String surfix = filename.substring(index);
		filename = UUID.randomUUID().toString().replace("-", "")+surfix;
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		String monthStr = null;
		if(month < 10){
			monthStr = "0"+month;
		}else{
			monthStr = month+"";
		}
		int day = cal.get(Calendar.DAY_OF_MONTH);
		String dayStr = null;
		if(day < 10){
			dayStr = "0"+day;
		}else{
			dayStr = day+"";
		}
		StringBuilder sb = new StringBuilder();
		if(dirPrefix.startsWith("/")){
			sb.append(dirPrefix);
		}else{
			sb.append("/");
			sb.append(dirPrefix);
		}
		if(!dirPrefix.endsWith("/")){
			sb.append("/");
		}
		sb.append(year);
		sb.append("/");
		sb.append(monthStr);
		sb.append("/");
		sb.append(dayStr);
		sb.append("/");
		sb.append(filename);
		return sb.toString();
	}
	
	protected String getMD5Str(String strs){
		 char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
       try {
           byte[] btInput = strs.getBytes();
           MessageDigest mdInst = MessageDigest.getInstance("MD5");
           mdInst.update(btInput);
           byte[] md = mdInst.digest();
           int j = md.length;
           char str[] = new char[j * 2];
           int k = 0;
           for (int i = 0; i < j; i++) {
               byte byte0 = md[i];
               str[k++] = hexDigits[byte0 >>> 4 & 0xf];
               str[k++] = hexDigits[byte0 & 0xf];
           }
           return new String(str);
       } catch (Exception e) {
          throw new RuntimeException("md5 error",e);
       }
	}
	
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doOptions(req, resp);
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type");
		resp.setHeader("Access-Control-Allow-Methods", "PUT,GET,POST,DELETE,OPTIONS");
	}
	
	protected String distPath(String dirPrefix){
		
		return dirPrefix.substring(0, dirPrefix.lastIndexOf("/"));
	}
	
}
