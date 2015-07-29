package net.sls.service.activity;

import java.util.List;

import net.sls.dto.ext.product.GoodsCommentDto;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IGoodsCommentService {

	/**
	 * 查询商品的评论或追回评论
	 * 
	 * */
	public ResBo<Pager<List<GoodsCommentDto>>> selectGoodsComments(ReqBo reqBo);

	/**@author wangshaohui
	 * @Description: TODO 批量更新审核状态
	 * @param rb
	 * @return ResBo<?>
	 * @date 2015年5月5日 下午4:43:50
	 */
	public ResBo<?> updateBatchStatus(ReqBo rb);

	/**@author wangshaohui
	 * @Description: TODO 批量删除
	 * @param rb
	 * @return ResBo<?>
	 * @date 2015年5月5日 下午4:48:35
	 */
	public ResBo<?> deleteBatchStatus(ReqBo rb);
	
	/**@author wangshaohui
	 * @Description: TODO 回复
	 * @param rb
	 * @return ResBo<?>
	 * @date 2015年5月5日 下午4:48:35
	 */
	public ResBo<?> updateReply(ReqBo rb);
	
}
