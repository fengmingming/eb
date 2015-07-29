package net.sls.dao.ext.activity;

import java.util.List;

import net.sls.dto.ext.product.GoodsCommentDto;

import org.apache.ibatis.annotations.Param;


public interface AddCommentMapperExt {

	/**@author wangshaohui
	 * @Description: TODO 查找商品的追加评论，根据id、名称、状态
	 * @param goodsId
	 * @param goodsName
	 * @param status
	 * @param start
	 * @param number
	 * @return List<GoodsCommentDto>
	 * @date 2015年5月4日 上午10:33:21
	 */
	public List<GoodsCommentDto> selectGoodsAddComments(@Param("goodsId") Long goodsId,
			@Param("goodsName") String goodsName,@Param("status") Integer status,
			@Param("start") int start,@Param("number") int number);
	
	public long countGoodsAddComments(@Param("goodsId") Long goodsId,
			@Param("goodsName") String goodsName,@Param("status") Integer status);

	/**@author wangshaohui
	 * @Description: TODO 批量更新追加状态
	 * @param ids
	 * @return int
	 * @date 2015年5月5日 下午5:04:29
	 */
	public int updateBatchAddStatus(@Param("ids")Long[] ids);
	
	/**@author wangshaohui
	 * @Description: TODO 批量删除追加评论
	 * @param ids
	 * @return int
	 * @date 2015年5月5日 下午5:05:04
	 */
	public int deleteBatchAddComment(@Param("ids")Long[] ids);

	/**@author wangshaohui
	 * @Description: TODO 追加评论下的回复
	 * @param id
	 * @return int
	 * @date 2015年5月5日 下午5:04:29
	 */
	public int updateAddReply(@Param("id")Long id,@Param("reply")String reply);

}
