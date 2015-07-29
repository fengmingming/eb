package net.sls.dto.pc.product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import net.sls.dto.pc.act.ActDto;

/**
 * 商品的传输类：商品内容、商品详情、品类等
 *
 */
public class CommodityDto implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;

    private String sku;

    private String goodsName;

    private Integer isSale;

    private BigDecimal price;

    private BigDecimal marketPrice;

    private String barCode;

    private String brandName;

    private String place;
    
    private String standard;

    private String remark;
    
    private String description;

    private String photoUrl1;

    private String photoUrl2;

    private String photoUrl3;

    private String photoUrl4;

    private String photoUrl;
    
    private Integer number;
    
    private Integer limittype;
    
    private String detailTip;
    
    private Integer monthNum;
    
    private List<ActDto> actDto; //商品参与的活动列表
    
    private String defPhotoUrl;
    
    public String getDefPhotoUrl() {
		return defPhotoUrl;
	}

	public void setDefPhotoUrl(String defPhotoUrl) {
		this.defPhotoUrl = defPhotoUrl;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Integer getIsSale() {
        return isSale;
    }

    public void setIsSale(Integer isSale) {
        this.isSale = isSale;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode == null ? null : barCode.trim();
    }

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhotoUrl1() {
		return photoUrl1;
	}

	public void setPhotoUrl1(String photoUrl1) {
		this.photoUrl1 = photoUrl1;
	}

	public String getPhotoUrl2() {
		return photoUrl2;
	}

	public void setPhotoUrl2(String photoUrl2) {
		this.photoUrl2 = photoUrl2;
	}

	public String getPhotoUrl3() {
		return photoUrl3;
	}

	public void setPhotoUrl3(String photoUrl3) {
		this.photoUrl3 = photoUrl3;
	}

	public String getPhotoUrl4() {
		return photoUrl4;
	}

	public void setPhotoUrl4(String photoUrl4) {
		this.photoUrl4 = photoUrl4;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getLimittype() {
		return limittype;
	}

	public void setLimittype(Integer limittype) {
		this.limittype = limittype;
	}

	public String getDetailTip() {
		return detailTip;
	}

	public void setDetailTip(String detailTip) {
		this.detailTip = detailTip;
	}

	public Integer getMonthNum() {
		return monthNum;
	}

	public void setMonthNum(Integer monthNum) {
		this.monthNum = monthNum;
	}

	public List<ActDto> getActDto() {
		return actDto;
	}

	public void setActDto(List<ActDto> actDto) {
		this.actDto = actDto;
	}
}