package net.sls.service.user.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sls.component.user.IFunctionComponent;
import net.sls.dto.user.Function;
import net.sls.service.user.IFunctionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Service
public class FunctionService implements IFunctionService {

	@Autowired
	public IFunctionComponent functionComponent;

	/*
	 * @author wangguojun
	 * @description: 添加功能
	 * @param reqBo
	 * @return 
	 * @date 2014年12月9日 下午2:31:39
	 */
	@Override
	public ResBo<Object> insertFunction(ReqBo reqBo) {
		Function function = reqBo.getReqModel(Function.class);
		Date createTime = new Date(System.currentTimeMillis());
		function.setCreateTime(createTime);
		functionComponent.insertFunction(function);
		if(reqBo.getParamBoolean("isImportant", false)){
			functionComponent.insertBatchRoleFunction(function.getId());
		}
		return new ResBo<Object>();
	}

	/*
	 * @author wangguojun
	 * @description: 按父类节点id查询功能
	 * @param reqBo
	 * @return 
	 * @date 2014年12月9日 下午2:31:11
	 */
	@Override
	public ResBo<List<Function>> selectFunctionByFatherId(ReqBo reqBo) {
		Function function = reqBo.getReqModel(Function.class);
		List<Function> functions = functionComponent
				.selectFunctionByFatherId(function.getFatherId());
		return new ResBo<List<Function>>(functions);
	}

	/*
	 * @author wangguojun
	 * @description: 可选择条件的修改功能
	 * @param reqBo
	 * @return 
	 * @date 2014年12月9日 下午2:31:53
	 */
	@Override
	public ResBo<Object> updateByPrimaryKeySelective(ReqBo reqBo) {
		Function function = reqBo.getReqModel(Function.class);
		functionComponent.updateByPrimaryKeySelective(function);
		return new ResBo<Object>();
	}

	/*
	 * @author wangguojun
	 * @description: 删除功能
	 * @param reqBo
	 * @return 
	 * @date 2014年12月9日 下午2:32:09
	 */
	@Override
	public ResBo<Object> deleteByPrimaryKey(ReqBo reqBo) {
		Function function = reqBo.getReqModel(Function.class);
		functionComponent.deleteByPrimaryKey(function.getId());
		return new ResBo<Object>();
	}

	/*
	 * @author wangguojun
	 * @description: 可选择条件的查询功能并且分页
	 * @param reqBo
	 * @return 
	 * @date 2014年12月9日 下午2:32:20
	 */
	@Override
	public ResBo<Pager<List<Function>>> selectFunctionByFilter(ReqBo reqBo) {
		
		Integer fatherId = reqBo.getParamInt("fatherId");
		String url = reqBo.getParamStr("url");
		String name = reqBo.getParamStr("name");
//		Integer start = reqBo.getParamInt("page");
//		Integer number = reqBo.getParamInt("rows");
		return new ResBo<Pager<List<Function>>>(functionComponent.selectFunctionByFilter(fatherId, url, name));

	}

	/*
	 * @author wangguojun
	 * @description: 查询总条数
	 * @param reqBo
	 * @return 
	 * @date 2014年12月9日 下午2:32:37
	 */
	@Override
	public ResBo<List<Function>> countFunctionByFilter(ReqBo reqBo) {
		return null;
	}

	@Override
	public ResBo<List<Map<String, Object>>> selectFunctionsByPid(ReqBo reqBo) {
		List<Function> list = functionComponent.selectFunctionByFatherId(reqBo.getParamInt("id"));
		List<Integer> idList = new ArrayList<Integer>(list.size());
		for(Function f : list){
			idList.add(f.getId());
		}
		List<Map<String,Object>> rts = new ArrayList<Map<String,Object>>(list.size());
		Map<String,Object> map = null;
		for(Function f : list){
			map = new HashMap<String,Object>();
			map.put("id", f.getId());
			map.put("name", f.getName());
			map.put("functionUrl", f.getUrl());
			map.put("isLeaf", f.getIsLeaf());
			map.put("isLog", f.getIsLog());
			rts.add(map);
		}
		return new ResBo<List<Map<String, Object>>>(rts);
	}
}

