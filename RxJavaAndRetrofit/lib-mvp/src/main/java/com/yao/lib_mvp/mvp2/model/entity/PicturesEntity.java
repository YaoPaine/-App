package com.yao.lib_mvp.mvp2.model.entity;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/3 下午6:28
 * @Version:
 */

public class PicturesEntity {

    private int proPictureId;
    private int productId;
    private int version;
    private String smallImg;
    private String mediumImg;
    private String largeImg;

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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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
}
