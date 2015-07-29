package net.sls.dto.product;

public class GoodsDetail {
    private Long id;

    private Long goodsId;

    private String description;

    private String photoUrl1;

    private String photoUrl2;

    private String photoUrl3;

    private String photoUrl4;

    private String photoUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getPhotoUrl1() {
        return photoUrl1;
    }

    public void setPhotoUrl1(String photoUrl1) {
        this.photoUrl1 = photoUrl1 == null ? null : photoUrl1.trim();
    }

    public String getPhotoUrl2() {
        return photoUrl2;
    }

    public void setPhotoUrl2(String photoUrl2) {
        this.photoUrl2 = photoUrl2 == null ? null : photoUrl2.trim();
    }

    public String getPhotoUrl3() {
        return photoUrl3;
    }

    public void setPhotoUrl3(String photoUrl3) {
        this.photoUrl3 = photoUrl3 == null ? null : photoUrl3.trim();
    }

    public String getPhotoUrl4() {
        return photoUrl4;
    }

    public void setPhotoUrl4(String photoUrl4) {
        this.photoUrl4 = photoUrl4 == null ? null : photoUrl4.trim();
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl == null ? null : photoUrl.trim();
    }
}