package net.sls.service.user;

import java.util.List;
import java.util.Map;

import net.sls.dto.user.Function;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IFunctionService {

	/**
	 * @author wangguojun
	 * @description: 添加功能
	 * @param reqBo
	 * @return ResBo<Object>
	 * @date 2014年12月9日 下午2:26:24
	 */
	public ResBo<Object> insertFunction(ReqBo reqBo);

	/**
	 * @author wangguojun
	 * @description: 按父类节点id查询功能
	 * @param reqBo
	 * @return ResBo<List<Function>>
	 * @date 2014年12月9日 下午2:29:08
	 */
	public ResBo<List<Function>> selectFunctionByFatherId(ReqBo reqBo);

	/**
	 * @author wangguojun
	 * @description: 可选择条件的修改功能
	 * @param reqBo
	 * @return ResBo<Object>
	 * @date 2014年12月9日 下午2:29:40
	 */
	public ResBo<Object> updateByPrimaryKeySelective(ReqBo reqBo);

	/**
	 * @author wangguojun
	 * @description: 删除功能
	 * @param reqBo
	 * @return ResBo<Object>
	 * @date 2014年12月9日 下午2:29:50
	 */
	public ResBo<Object> deleteByPrimaryKey(ReqBo reqBo);

	/**
	 * @author wangguojun
	 * @description: 可选择条件的查询功能并且分页
	 * @param reqBo
	 * @return ResBo<Pager<List<Function>>>
	 * @date 2014年12月9日 下午2:30:03
	 */
	public ResBo<Pager<List<Function>>> selectFunctionByFilter(ReqBo reqBo);

	/**
	 * @author wangguojun
	 * @description: 查询总条数
	 * @param reqBo
	 * @return ResBo<List<Function>>
	 * @date 2014年12月9日 下午2:30:21
	 */
	public ResBo<List<Function>> countFunctionByFilter(ReqBo reqBo);

	/**
	 * 根据父节点id查询子节点功能
	 * @param id:integer 父节点id 
	 * @return id:integer 功能id name:String 功能名称	 isUse:boolean 是否启用    isLeaf:boolean是否是叶子节点
	 * */
	public ResBo<List<Map<String, Object>>> selectFunctionsByPid(ReqBo reqBo); 

}
