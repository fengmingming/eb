package imgupload;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

public interface IImgUploadResponseHandler {
	
	/**
	 * 
	 * @param
	 * */
	public void responseHandler(HttpServletRequest req,HttpServletResponse res,List<FileItem> items,List<String> paths,FileItem item, Exception e);

}
