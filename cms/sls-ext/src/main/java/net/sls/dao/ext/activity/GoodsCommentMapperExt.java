package net.sls.dao.ext.activity;

import java.util.List;

import net.sls.dto.ext.product.GoodsCommentDto;

import org.apache.ibatis.annotations.Param;


public interface GoodsCommentMapperExt {
	/**@author wangshaohui
	 * @Description: TODO 查找商品的评论，根据id、名称、状态
	 * @param goodsId
	 * @param goodsName
	 * @param status
	 * @param start
	 * @param number
	 * @return List<GoodsCommentDto>
	 * @date 2015年5月4日 上午10:33:21
	 */
	public List<GoodsCommentDto> selectGoodsComments(@Param("goodsId") Long goodsId,
			@Param("goodsName") String goodsName,@Param("status") Integer status,
			@Param("start") int start,@Param("number") int number);
	
	public long countGoodsComments(@Param("goodsId") Long goodsId,
			@Param("goodsName") String goodsName,@Param("status") Integer status);
	
	/**@author wangshaohui
	 * @Description: TODO 批量更新状态
	 * @param ids
	 * @return int
	 * @date 2015年5月5日 下午5:04:29
	 */
	public int updateBatchStatus(@Param("ids")Long[] ids);
	
	/**@author wangshaohui
	 * @Description: TODO 批量删除评论
	 * @param ids
	 * @return int
	 * @date 2015年5月5日 下午5:05:04
	 */
	public int deleteBatchComment(@Param("ids")Long[] ids);
	
	/**@author wangshaohui
	 * @Description: TODO 评论下的回复
	 * @param id
	 * @return int
	 * @date 2015年5月5日 下午5:04:29
	 */
	public int updateReply(@Param("id")Long id,@Param("reply")String reply);

}
