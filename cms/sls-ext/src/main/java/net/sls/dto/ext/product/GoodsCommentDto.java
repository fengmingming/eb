package net.sls.dto.ext.product;

import net.sls.dto.activity.GoodsComment;

public class GoodsCommentDto extends GoodsComment{
	//评论id，如果是追加实体的话：id是追加id,goodsCommentId是评论id。
	private Long goodsCommentId;
	
	private String goodsName;

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Long getGoodsCommentId() {
		return goodsCommentId;
	}

	public void setGoodsCommentId(Long goodsCommentId) {
		this.goodsCommentId = goodsCommentId;
	}
	
	
}
