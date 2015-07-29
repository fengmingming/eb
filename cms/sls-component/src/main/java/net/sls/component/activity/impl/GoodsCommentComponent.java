package net.sls.component.activity.impl;

import java.util.List;

import net.sls.component.activity.IGoodsCommentComponent;
import net.sls.dao.ext.activity.GoodsCommentMapperExt;
import net.sls.dto.ext.product.GoodsCommentDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import framework.web.Pager;

@Component
public class GoodsCommentComponent implements IGoodsCommentComponent {

	@Autowired
	private GoodsCommentMapperExt goodsCommentMapperExt;
	
	@Override
	public Pager<List<GoodsCommentDto>> selectGoodsComment(Long goodsId,
			String goodsName, Integer status, Integer start, Integer number) {
		long count = goodsCommentMapperExt.countGoodsComments(goodsId, goodsName, status);
		List<GoodsCommentDto> list = goodsCommentMapperExt.selectGoodsComments(goodsId, goodsName, status, start*number, number);
		Pager<List<GoodsCommentDto>> pager = new Pager<List<GoodsCommentDto>>(list,count);
		return pager;
	}

	@Override
	public int updateBatchStatus(Long[] ids) {
		return goodsCommentMapperExt.updateBatchStatus(ids);
	}

	@Override
	public int deleteBatchComment(Long[] ids) {
		return goodsCommentMapperExt.deleteBatchComment(ids);
	}

	@Override
	public int updateReply(Long id, String reply) {
		return goodsCommentMapperExt.updateReply(id, reply);
	}
}
