package net.sls.service.product;

import java.util.List;

import net.sls.dto.product.Provider;
import util.model.ComboxDto;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * 供应商的服务层
 *
 */
public interface IProviderService {

	/**
	 * 
	 * 新增供应商
	 * @param Provider Provider供应商信息
	 * 
	 * */
	 public ResBo<Object> insertProvider(ReqBo reqBo);
	 
	 
	 /**
	  * 
	  * 删除供应商
	  * @param ProviderId ProviderId 供应商id
	  * 需要返回ResBo<Object>？ 用户service里有返回值
	  * */
	 public void deleteProvider(ReqBo reqBo);
	 
	 /**
	  * 
	  * 修改Provider供应商信息
	  * @param Provider 要修改的Provider供应商信息
	  * @return Provider 修改后的Provider供应商信息
	  * */
	 public ResBo<Provider> updateProvider(ReqBo reqBo);
		
		/**
		 * 
		 * 根据id查询用Provider供应商信息
		 * 
		 * */
	 public ResBo<Provider> selectProviderById(ReqBo reqBo);
	 
		/**
		 * 查询Provider供应商信息
		 * 
		 * */
		
	 public ResBo<Pager<List<Provider>>> selectProviders(ReqBo reqBo);
	 /**
	  * 查询Provider供应商列表
	  * @param reqBo
	  * @return
	  */
	 public ResBo<List<ComboxDto>> selectProviderList(ReqBo reqBo);
		
	 
}
