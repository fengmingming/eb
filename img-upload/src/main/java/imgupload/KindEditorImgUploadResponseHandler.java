package imgupload;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import com.fasterxml.jackson.databind.ObjectMapper;

public class KindEditorImgUploadResponseHandler implements
		IImgUploadResponseHandler { 
	
	private static final String path;
	
	static{
		path = ResourceBundle.getBundle("domain").getString("path_img");
	}

	@Override
	public void responseHandler(HttpServletRequest req,
			HttpServletResponse res, List<FileItem> items, List<String> paths,
			FileItem item, Exception e) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(e == null){
			map.put("error", 0);
			map.put("url", path+paths.get(0));
		}else{
			map.put("error", "1");
			map.put("message", e.getMessage());
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			new ObjectMapper().writeValue(out,map);
			req.setAttribute("result", out.toString());
			req.getRequestDispatcher("/WEB-INF/page/success.jsp").forward(req, res);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
