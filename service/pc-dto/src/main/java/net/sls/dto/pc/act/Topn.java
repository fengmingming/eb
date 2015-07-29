package net.sls.dto.pc.act;

import java.io.Serializable;

/**
 * Top 10 列表的dto类
 * @author sls006
 *
 */
public class Topn implements Serializable{
	private static final long serialVersionUID = 1L;
	// 排序的序号
	private int index;
	// 商品的id
	private int goodId;
	
	// 商品的名称 
	private String goodName;
	
	// 商品在列表中展示时的图片url
	private String goodImgUrl;
		
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public String getGoodImgUrl() {
		return goodImgUrl;
	}
	public void setGoodImgUrl(String goodImgUrl) {
		this.goodImgUrl = goodImgUrl;
	}
	public String getGoodDetailUrl() {
		return goodDetailUrl;
	}
	public void setGoodDetailUrl(String goodDetailUrl) {
		this.goodDetailUrl = goodDetailUrl;
	}
	// 商品详情的url
	private String goodDetailUrl;
	
	// 城市id（ 每个城市都有TopN的排序）
	private int cityId;
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getGoodId() {
		return goodId;
	}
	public void setGoodId(int goodId) {
		this.goodId = goodId;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	
	
}
