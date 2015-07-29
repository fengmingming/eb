package ImageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.resizers.configurations.AlphaInterpolation;

public class ImageUtil {

	private final static List<String> imgs = new ArrayList<String>();

	static {
		imgs.add("58X58");
		imgs.add("200X200"); 
		imgs.add("430X430"); 
		imgs.add("800X800");
	}

	public static List<String> getImgs() {
		return imgs;
	}

	public static void imageDispatch(String relDir, List<String> paths) {
		try {
			for (String path : paths) {
				for (String s : imgs) {
					String[] ss = s.split("X");
					String cpath = relDir + "/" + s + path;
					createDirectory(cpath);
					Thumbnails
					.of(relDir + path).alphaInterpolation(AlphaInterpolation.QUALITY)
					.size(Integer.parseInt(ss[0]), Integer.parseInt(ss[1]))
					.toFile(cpath);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private synchronized static void createDirectory(String path){
		File file = null;
		int i = path.lastIndexOf("/");
		if(i != -1){
			path = path.substring(0, i);
			file = new File(path);
			if(!file.exists()){
				file.mkdirs();
			}
		}
	}

}
