package imgupload;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DefaultImgUploadResponseHandler implements IImgUploadResponseHandler{
	
	@Override
	public void responseHandler(HttpServletRequest req,
			HttpServletResponse res, List<FileItem> items, List<String> paths,
			FileItem item, Exception e) {
		ResBo<List<String>> resBo = null;
		if(e == null){
			resBo = new ResBo<List<String>>(paths);
		}else{
			resBo = new ResBo<List<String>>(null,e.getMessage());
		}
		try {
			new ObjectMapper().writeValue(res.getOutputStream(), resBo);
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
	}
	
}
