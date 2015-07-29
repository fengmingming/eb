package net.sls.component.activity;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.sls.dto.ext.product.GoodsCommentDto;
import framework.web.Pager;

public interface IAddCommentComponent {


	/**@author wangshaohui
	 * @Description: TODO 查找商品的追加评论
	 * @param goodsId
	 * @param goodsName
	 * @param status
	 * @param start
	 * @param number
	 * @return Pager<List<GoodsCommentDto>>
	 * @date 2015年5月4日 上午10:42:55
	 */
	public Pager<List<GoodsCommentDto>> selectGoodsAddComment(Long goodsId,String goodsName,Integer status,Integer start,Integer number);

	/**@author wangshaohui
	 * @Description: TODO 批量删除追加评论
	 * @param ids void
	 * @date 2015年5月5日 下午4:58:57
	 */
	public int deleteBatchAddComment(Long[] ids);

	/**@author wangshaohui
	 * @Description: TODO 批量更新追加评论
	 * @param ids void
	 * @date 2015年5月5日 下午4:59:22
	 */
	public int updateBatchAddStatus(Long[] ids);
	
	/**@author wangshaohui
	 * @Description: TODO 追加评论下的回复
	 * @param id
	 * @return int
	 * @date 2015年5月5日 下午5:04:29
	 */
	public int updateAddReply(@Param("id")Long id,@Param("reply")String reply);

}
