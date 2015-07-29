package net.sls.service.dict;

import java.util.List;

import net.sls.dto.product.Dict;
import util.model.ComboxDto;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * 字典的服务层
 *
 */
public interface IDictService {

	/**
	 * 
	 * 新增字典
	 * @param Dict dict字典信息
	 * 不用返回值？
	 * */
	 public ResBo<Object> insertDict(ReqBo reqBo);
	 
	 
	 /**
	  * 
	  * 删除字典
	  * @param dictId dictId 字典id
	  * 需要返回ResBo<Object>？ 用户service里有返回值
	  * */
	 public void deleteDict(ReqBo reqBo);
	 
	 /**
	  * 
	  * 修改dict字典信息
	  * @param dict 要修改的dict字典信息
	  * @return dict 修改后的dict字典信息
	  * */
	 public ResBo<Dict> updateDict(ReqBo reqBo);
		
		/**
		 * 
		 * 根据id查询用dict字典信息
		 * 
		 * */
	 public ResBo<Dict> selectDictById(ReqBo reqBo);
	 
		/**
		 * 查询字典信息
		 * 
		 * */
		
	 public ResBo<Pager<List<Dict>>> selectDicts(ReqBo reqBo);
		
	 public ResBo<List<ComboxDto>> selectDictCombox(ReqBo reqBo);
	 
	 public ResBo<List<ComboxDto>> selectDictComboxByType(ReqBo reqBo);
}
