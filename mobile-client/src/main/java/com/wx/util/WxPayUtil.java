package com.wx.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.dom4j.io.SAXReader;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.wx.config.WxPayConfig;

import util.framework.MD5Util;

public class WxPayUtil {	
	/**
	 * map转成xml
	 * @param arr
	 * @return
	 */
	public static String MapToXml(SortedMap<String, String> arr) {
		String xml = "<xml>";		
		Iterator<Entry<String, String>> iter = arr.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			String key = entry.getKey();
			String val = entry.getValue();
			if ("attach".equalsIgnoreCase(key)||"body".equalsIgnoreCase(key)||"sign".equalsIgnoreCase(key)){
				xml += "<" + key + "><![CDATA[" + val + "]]></" + key + ">";
			} else				
				xml += "<" + key + ">" + val + "</" + key + ">";
		}
		xml += "</xml>";
		return xml;
	}
	/**
	 * 创建sign签名
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String createSign(SortedMap<String, String> parameters, String key){
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            String v = (String)entry.getValue();
            if(null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        String sign = MD5Util.getMD5Str(sb.toString()).toUpperCase();
        return sign;
    }
	
	/**
	 * 商户生成的随机字符串
	 * 字符串类型，32个字节以下
	 * @return
	 */
	public static String CreateNoncestr() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String res = "";
		for (int i = 0; i < 16; i++) {
			Random rd = new Random();
			res += chars.charAt(rd.nextInt(chars.length()));
		}
		return res;
	}
	/**
	 * 获得prepayid
	 * @param packageParams
	 * @return
	 */
	@SuppressWarnings({ "deprecation", "rawtypes" })
	public static String getPrePayId(SortedMap<String, String> packageParams){
		String xml = WxPayUtil.MapToXml(packageParams);
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
		//HttpPost httpost= HttpClientConnectionManager.getPostMethod(WxPayConfig.prepayid_url);
		HttpPost httpost = new HttpPost(WxPayConfig.prepayid_url); 
		String prepay_id = "";
	    try {
			 httpost.setEntity(new StringEntity(xml, "UTF-8"));
			 HttpResponse response = client.execute(httpost);
		     String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");		     
		    if(jsonStr.indexOf("FAIL")!=-1){
		    	return prepay_id;
		    }
		    Map<String, String> map = doXMLParse(jsonStr);
		    prepay_id  = (String) map.get("prepay_id");
		    return prepay_id;
	    } catch (Exception e) {
			e.printStackTrace();
		}finally{
			client.close();
		}
	    return prepay_id;
	}
	    
	/**
	 * 解析xml    
	 * @param strxml
	 * @return
	 * @throws Exception
	 */
    @SuppressWarnings("rawtypes")
	public static SortedMap<String, String> doXMLParse(String strxml) throws Exception {
		if(null == strxml || "".equals(strxml)) {
			return null;
		}		
		//Map<String, String> m = new HashMap<String, String>();
		SortedMap<String, String> m = new TreeMap<String, String>();
		InputStream in = new ByteArrayInputStream(strxml.getBytes());
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if(children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getChildrenText(children);
			}			
			m.put(k, v);
		}
		
		//关闭流
		in.close();
		
		return m;
	}    
    /**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		
		return sb.toString();
	}	
	/**
	 * 通知微信发送成功，收到信息，防止微信定期重新发送通知
	 * @param return_code
	 * @param return_msg
	 * @return
	 */
	public static String setXML(String return_code, String return_msg) {
		return "<xml><return_code><![CDATA[" + return_code+ "]]></return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
	}	                	               
}
