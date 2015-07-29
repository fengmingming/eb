package net.sls.service.activity.impl;

import java.util.List;

import net.sls.component.activity.IAddCommentComponent;
import net.sls.component.activity.IGoodsCommentComponent;
import net.sls.dto.ext.product.GoodsCommentDto;
import net.sls.service.activity.IGoodsCommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Service
public class GoodsCommentService implements IGoodsCommentService {

	@Autowired
	public IGoodsCommentComponent goodsCommentComponent;

	@Autowired
	public IAddCommentComponent addCommentComponent;

	@Override
	public ResBo<Pager<List<GoodsCommentDto>>> selectGoodsComments(ReqBo reqBo) {
		Long goodsId = reqBo.getParamLong("goodsId");
		String goodsName = reqBo.getParamStr("goodsName");
		Integer status = reqBo.getParamInt("status");
		Integer start = reqBo.getParamInt("page");
		Integer number = reqBo.getParamInt("rows");

		String isChecked = reqBo.getParamStr("isChecked");
		Pager<List<GoodsCommentDto>> pager = null;
		// 如果isChecked为false的话未选中，调用查询评论方法，为true的话，选中，调用追加评论方法。
		if ("false".equals(isChecked)) {

			pager = goodsCommentComponent.selectGoodsComment(goodsId,
					goodsName, status, start - 1, number);
		} else if ("true".equals(isChecked)) {
			pager = addCommentComponent.selectGoodsAddComment(goodsId,
					goodsName, status, start - 1, number);
		}

		return new ResBo<Pager<List<GoodsCommentDto>>>(pager);
	}

	@Override
	public ResBo<?> updateBatchStatus(ReqBo rb) {
		Long[] ids = (Long[]) rb.getParam("ids");
		String isChecked = rb.getParamStr("isChecked");
		// 如果isChecked为false的话未选中，更新评论表，为true的话，选中，更新追加评论表。
		if ("false".equals(isChecked)) {
			goodsCommentComponent.updateBatchStatus(ids);
		} else if ("true".equals(isChecked)) {
			addCommentComponent.updateBatchAddStatus(ids);
			
		}

		return new ResBo<Object>();
	}

	@Override
	public ResBo<?> deleteBatchStatus(ReqBo rb) {
		Long[] ids = (Long[]) rb.getParam("ids");
		String isChecked = rb.getParamStr("isChecked");
		// 如果isChecked为false的话未选中，更新评论表，为true的话，选中，更新追加评论表。
		if ("false".equals(isChecked)) {
			goodsCommentComponent.deleteBatchComment(ids);
		} else if ("true".equals(isChecked)) {
			addCommentComponent.deleteBatchAddComment(ids);
			
		}

		return new ResBo<Object>();
	}

	@Override
	public ResBo<?> updateReply(ReqBo rb) {
		Long id = rb.getParamLong("id");
		String reply = rb.getParamStr("reply");
		String isChecked = rb.getParamStr("isChecked");
		// 如果isChecked为false的话未选中，更新评论表，为true的话，选中，更新追加评论表。
		if ("false".equals(isChecked)) {
			goodsCommentComponent.updateReply(id, reply);
		} else if ("true".equals(isChecked)) {
			addCommentComponent.updateAddReply(id, reply);
			
		}

		return new ResBo<Object>();
	}

}
