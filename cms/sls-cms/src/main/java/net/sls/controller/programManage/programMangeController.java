package net.sls.controller.programManage;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sls.dto.programManage.Interfase;
import net.sls.dto.programManage.Methods;
import net.sls.dto.programManage.Params;
import net.sls.service.programManage.IInterfaseService;
import net.sls.service.programManage.IMethodsService;
import net.sls.service.programManage.IParamsService;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import util.framework.FindServiceUtil;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;
import framework.web.ResResult;

@Controller
@RequestMapping("programManage")
public class programMangeController {
	
	@RequestMapping("initInterFaceTest.htm")
	public String initmemberManage(){
		return "programManage/interFaceTest";
	}
	
	/**
	 * 接口查询
	 * @param request
	 * @return
	 */
	@RequestMapping(value="searchInterface.htm")
	@ResponseBody
	public ResBo<Pager<List<Interfase>>> getInterfaces(HttpServletRequest request){   
		ReqBo reqBo = new ReqBo(request);
		IInterfaseService iis = FindServiceUtil.findService(IInterfaseService.class);
		return iis.selectInterfacesList(reqBo);
	}
	
	/**
	 * 添加接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addInterfaces.htm")
	@ResponseBody
	public ResResult<Long> addInterfaces(@ModelAttribute Interfase interfase){   
		IInterfaseService iis = FindServiceUtil.findService(IInterfaseService.class);
		ResBo<Interfase> rb = iis.insertInterfaces(new ReqBo(interfase));
		if(rb.isSuccess()){
			return new ResResult<Long>(rb.getResult().getId());
		}
		return new ResResult<Long>(rb.getErrCode(),rb.getErrMsg());
	}
	/**
	 *  修改接口信息
	 * */
	@RequestMapping("updateInterfaces.htm")
	@ResponseBody
	public ResBo<?> updateInterfaces(@ModelAttribute Interfase interfaces){
		IInterfaseService iis = FindServiceUtil.findService(IInterfaseService.class);
		return iis.updateInterfaces(new ReqBo(interfaces));
	}
	
	@RequestMapping("methods.htm")
	public String initmethods(){
		return "programManage/methods";
	}
	
	/**
	 * 接口下方法列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="methodList.htm")
	@ResponseBody
	public ResBo<Pager<List<Methods>>> getMethodList(HttpServletRequest request){   
		ReqBo reqBo = new ReqBo(request);
		IMethodsService iis = FindServiceUtil.findService(IMethodsService.class);
		return iis.selectMethodsList(reqBo);
	}
	
	@RequestMapping("methodInfo.htm")
	public String initmethodInfo(){
		return "programManage/methodInfo";
	}
	
	@RequestMapping(value="methodInfos.htm")
	@ResponseBody
	public ResBo<List<Map<Object,Object>>> getMethodInfoList(HttpServletRequest request){   
		ReqBo reqBo = new ReqBo(request);
		IParamsService ips = FindServiceUtil.findService(IParamsService.class);
		return ips.selectParamListBymethodId(reqBo);
	}
	
	@RequestMapping("paramsInfo.htm")
	public String initparamsInfo(){
		return "programManage/paramsInfo";
	}
	
	/**
	 * 添加方法
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addMethods.htm")
	@ResponseBody
	public ResResult<Long> addMethods(@ModelAttribute Methods methods){   
		IMethodsService iis = FindServiceUtil.findService(IMethodsService.class);
		ResBo<Methods> rb = iis.insertMethods(new ReqBo(methods));
		if(rb.isSuccess()){
			return new ResResult<Long>(rb.getResult().getId());
		}
		return new ResResult<Long>(rb.getErrCode(),rb.getErrMsg());
	}
	/**
	 *  修改方法信息
	 * */
	@RequestMapping("updateMethods.htm")
	@ResponseBody
	public ResBo<?> updateMethods(@ModelAttribute Methods methods){
		IMethodsService iis = FindServiceUtil.findService(IMethodsService.class);
		return iis.updateMethods(new ReqBo(methods));
	}
	
	@RequestMapping(value="selectParamsList.htm")
	@ResponseBody
	public ResBo<Pager<List<Params>>> getParamsList(HttpServletRequest request){   
		ReqBo reqBo = new ReqBo(request);
		IParamsService ips = FindServiceUtil.findService(IParamsService.class);
		return ips.selectParamsPagerBymethodId(reqBo);
	}
	
	@RequestMapping("addParams.htm")
	public String addParams(){
		return "programManage/addParams";
	}
	
	/**
	 * 添加参数
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addParamInfo.htm")
	@ResponseBody
	public ResResult<Long> addParamInfo(@ModelAttribute Params params){   
		IParamsService ips = FindServiceUtil.findService(IParamsService.class);
		ResBo<Params> rb = ips.insertParams(new ReqBo(params));
		if(rb.isSuccess()){
			return new ResResult<Long>(rb.getResult().getId());
		}
		return new ResResult<Long>(rb.getErrCode(),rb.getErrMsg());
	}
	/**
	 *  修改参数信息
	 * */
	@RequestMapping("updateParams.htm")
	@ResponseBody
	public ResBo<?> updateParams(@ModelAttribute Params params){
		IParamsService ips = FindServiceUtil.findService(IParamsService.class);
		return ips.updateParams(new ReqBo(params));
	}
	
	@RequestMapping(value="programTest.htm")
	@ResponseBody
	public Object getProgramTest(HttpServletRequest request) throws Exception{   
		ReqBo reqBo = new ReqBo(request);
		String interfase= request.getParameter("interfase");
		String url = request.getParameter("url");
		String methodName = request.getParameter("methodName");
		Class<?> clazz = Class.forName(interfase);
		Object obj = FindServiceUtil.findRemoteService(url, clazz);
		Method m = clazz.getDeclaredMethod(methodName, new Class<?>[]{ReqBo.class});
		Object robj = m.invoke(obj, reqBo);
		return robj;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="programModelTest.htm")
	@ResponseBody
	public <T> Object getProgramModelTest(HttpServletRequest request) throws Exception{   
		ReqBo reqBo = new ReqBo(request);
		String interfase= request.getParameter("interfase");
		String url = request.getParameter("url");
		String methodName = request.getParameter("methodName");
		String model = request.getParameter("model");
		HashMap<String,Object> map = new HashMap<String,Object>();
	    Enumeration<?> names = request.getParameterNames();
	    while (names.hasMoreElements())
	    {
	      String name = (String) names.nextElement();
	      map.put(name, request.getParameterValues(name));
	    }
	   
//	    map.remove(url);
	    Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator(); 
	    Class<?> bean = Class.forName(model);
	    T b= (T) bean.newInstance();
        reqBo.setReqModel(b);
        while(it.hasNext()){  
            Map.Entry<String, Object> entry=it.next();  
            String key=entry.getKey();  
            if(key.equals("url")){  
                it.remove();       
            }  
            if(key.equals("interfase")){  
                it.remove();       
            } 
            if(key.equals("model")){  
                it.remove();       
            } 
            if(key.equals("methodName")){  
                it.remove();       
            } 
            BeanUtils.setProperty(b, key, entry.getValue());
        }  
		System.out.println(reqBo.getReqModel().getClass());
		Class<?> clazz = Class.forName(interfase);
		Object obj = FindServiceUtil.findRemoteService(url, clazz);
		Method m = clazz.getDeclaredMethod(methodName, new Class<?>[]{ReqBo.class});
		Object robj = m.invoke(obj, reqBo);
		return robj;
	}
	
	
	@RequestMapping(value="programModelTest2.htm")
	@ResponseBody
	public <T> Object getProgramModelTest2(HttpServletRequest request) throws Exception{   
		ReqBo reqBo = new ReqBo(request);
		String interfase= request.getParameter("interfase");
		String url = request.getParameter("url");
		String methodName = request.getParameter("methodName");
		String ms = request.getParameter("models");
		//得到多个model的数组
		String[] models = ms.split(","); 
		//得到页面所有参数的map集合
		HashMap<String, Object> map = getParamsMap(request);
	   
		for (String model : models) {
			//把map中参数放到bean中，并且把bean加到reqBo中
			getBean(reqBo, model, map);  
		}
		System.out.println(reqBo.getReqModel().getClass());
		//得到接口
		Class<?> clazz_inter = Class.forName(interfase);
		//通过url和接口生成service层
		Object obj_service = FindServiceUtil.findRemoteService(url, clazz_inter);
		//从接口里找到这个方法
		Method method = clazz_inter.getDeclaredMethod(methodName, new Class<?>[]{ReqBo.class});
		//启动这个方法，参数--service层、reqBo    reqBo里包括所有的model          和其他的参数
		Object robj = method.invoke(obj_service, reqBo);
		return robj;
	}

	private <T> void getBean(ReqBo reqBo, String model,
			HashMap<String, Object> map) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException {
		Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator(); 
		//根据model生成bean
	    Class<?> bean = Class.forName(model);
	    @SuppressWarnings("unchecked")
	    //实例化bean
		T b= (T) bean.newInstance();
	    //得到bean里的属性
	    Field[] fs = b.getClass().getDeclaredFields();
	    //把属性名放到attrs里
	    List<String> attrs = new ArrayList<String>();
	    for (Field f : fs) {
			String attr = f.getName();
			attrs.add(attr);
		}
	    //把实例出来的bean对象放到reqBo中
        reqBo.setReqModel(b);
        //如果页面上的参数在bean下的attrs里的话，再将它设置到bean的属性里
        while(it.hasNext()){  
            Map.Entry<String, Object> entry=it.next();  
            String key=entry.getKey();  
            if(attrs.contains(key)){  
            	BeanUtils.setProperty(b, key, entry.getValue());
            }  
        }
	}

	private HashMap<String, Object> getParamsMap(HttpServletRequest request) {
		HashMap<String,Object> map = new HashMap<String,Object>();
	    Enumeration<?> names = request.getParameterNames();
	    while (names.hasMoreElements())
	    {
	      String name = (String) names.nextElement();
	      map.put(name, request.getParameterValues(name));
	    }
		return map;
	}
	
	
}
