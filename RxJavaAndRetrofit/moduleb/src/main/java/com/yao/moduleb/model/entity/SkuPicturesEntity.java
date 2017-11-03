package com.yao.moduleb.model.entity;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/3 下午6:16
 * @Version:
 */

public class SkuPicturesEntity {
    private int proPictureId;
    private int productId;
    private String smallImg;
    private String mediumImg;
    private String largeImg;
    private int proAttrValueId;
    private int proAttrKeyId;

    public int getProPictureId() {
        return proPictureId;
    }

    public void setProPictureId(int proPictureId) {
        this.proPictureId = proPictureId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getSmallImg() {
        return smallImg;
    }

    public void setSmallImg(String smallImg) {
        this.smallImg = smallImg;
    }

    public String getMediumImg() {
        return mediumImg;
    }

    public void setMediumImg(String mediumImg) {
        this.mediumImg = mediumImg;
    }

    public String getLargeImg() {
        return largeImg;
    }

    public void setLargeImg(String largeImg) {
        this.largeImg = largeImg;
    }

    public int getProAttrValueId() {
        return proAttrValueId;
    }

    public void setProAttrValueId(int proAttrValueId) {
        this.proAttrValueId = proAttrValueId;
    }

    public int getProAttrKeyId() {
        return proAttrKeyId;
    }

    public void setProAttrKeyId(int proAttrKeyId) {
        this.proAttrKeyId = proAttrKeyId;
    }
}
