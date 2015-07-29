package net.sls.component.activity.impl;

import java.util.List;

import net.sls.component.activity.IAddCommentComponent;
import net.sls.dao.ext.activity.AddCommentMapperExt;
import net.sls.dto.ext.product.GoodsCommentDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.web.Pager;

@Component
public class AddCommentComponent implements IAddCommentComponent {

	@Autowired
	private AddCommentMapperExt addCommentMapperExt;
	
	@Override
	public Pager<List<GoodsCommentDto>> selectGoodsAddComment(Long goodsId,
			String goodsName, Integer status, Integer start, Integer number) {
		long count = addCommentMapperExt.countGoodsAddComments(goodsId, goodsName, status);
		List<GoodsCommentDto> list = addCommentMapperExt.selectGoodsAddComments(goodsId, goodsName, status, start*number, number);
		Pager<List<GoodsCommentDto>> pager = new Pager<List<GoodsCommentDto>>(list,count);
		return pager;
	}

	@Override
	public int deleteBatchAddComment(Long[] ids) {
		return addCommentMapperExt.deleteBatchAddComment(ids);
	}

	@Override
	public int updateBatchAddStatus(Long[] ids) {
		return addCommentMapperExt.updateBatchAddStatus(ids);
	}

	@Override
	public int updateAddReply(Long id, String reply) {
		return addCommentMapperExt.updateAddReply(id, reply);
	}
}
