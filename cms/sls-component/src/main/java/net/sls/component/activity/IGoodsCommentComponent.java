package net.sls.component.activity;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.sls.dto.ext.product.GoodsCommentDto;
import framework.web.Pager;

public interface IGoodsCommentComponent {


	/**@author wangshaohui
	 * @Description: TODO 查找商品的评论
	 * @param goodsId
	 * @param goodsName
	 * @param status
	 * @param start
	 * @param number
	 * @return Pager<List<Goods>>
	 * @date 2015年5月4日 上午10:42:55
	 */
	public Pager<List<GoodsCommentDto>> selectGoodsComment(Long goodsId,String goodsName,Integer status,Integer start,Integer number);

	/**@author wangshaohui
	 * @Description: TODO 批量更新状态
	 * @param ids void
	 * @date 2015年5月5日 下午4:58:03
	 */
	public int updateBatchStatus(Long[] ids);

	/**@author wangshaohui
	 * @Description: TODO 批量删除评论
	 * @param ids void
	 * @date 2015年5月5日 下午4:58:27
	 */
	public int deleteBatchComment(Long[] ids);
	
	/**@author wangshaohui
	 * @Description: TODO 评论下的回复
	 * @param id
	 * @return int
	 * @date 2015年5月5日 下午5:04:29
	 */
	public int updateReply(@Param("id")Long id,@Param("reply")String reply);

	
}
