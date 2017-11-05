package com.yao.moduleb.model.entity;

import java.util.List;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/3 下午6:12
 * @Version:
 */

public class GoodsEntity {


    /**
     * id : 46
     * name :  Umi Smartphone
     * description : <h2 style="color: rgb(0, 0, 0);">Features:<br>100% Brand new and high quality!<br>Dual SIM Card Dual Standby.<br>5.5" 2.5D HD multi-point capacitive touch screen with 1280 * 720 resolution, quite sensitive.<br>Dual Cameras, 2MP front camera, 8MP back camera with flashlight and auto focus.<br>1GB RAM and 8GB ROM.<br>MTK6580 Quad Core 1.3GHz CPU, Android 5.1.<br>Support Book, Radio Tuner, Messaging, Wallpapers, Calendar, Calculator, Clock, etc.<br>Specifications:<br>Color: Black; Golden<br>Display: 5.5"<br>Screen Resolution: 1280*720 pixels<br>CPU: "MediaTek MT6580 Quad-Core Cortex®-A53™ 1.3GHz x 4"<br>RAM: 1GB<br>ROM: 8GB<br>Front Camera: 2.0MP GC2355 &nbsp;camera,Front flashlight<br>Back Camera: for Sony IMX179,8MP, Max Pixels13MP, with dual flashlight<br>Battery: 2500 MAH&nbsp;<br>Dimensions: 153.8*77*7.9mm<br>Plug type: EU<br>Package included:<br>1 x &nbsp;1280*720 HD UMI ROME X 5.5" Android 5.1 Bluetooth Smartphone Quad-Core 1.3GHz<br>1 x USB Cable<br>1 x Charger(EU)<br>1 x User manual &nbsp; &nbsp;</h2>
     * keyword : Umi Smartphone
     * seo : Umi Smartphone
     * brandLogo : /yks-resource/screen/2017-07-11/20170711154855078_Fh8v.jpg
     * brandName : NULL
     * brandShortName : NULL
     * brandSeo : NULL
     * categoryId : 917
     * clickCount : 524
     * favorite : false
     * favoriteCount : 99
     * createTime : 1499950900000
     * updateTime : 1509703672000
     * attrs : [{"keyId":5,"name":"color","keySort":1,"type":1,"mainAttrFlag":true,"attrValues":[{"valueId":19,"value":"black","valueSort":2,"skuPictures":[{"proPictureId":725,"productId":46,"version":null,"smallImg":"https://ceshi.fileresource.vipsouq.net/yks-resource/screen/2017-07-13/20170713170410934_Zmj7_min.jpg","mediumImg":"https://ceshi.fileresource.vipsouq.net/yks-resource/screen/2017-07-13/20170713170410934_Zmj7_mid.jpg","largeImg":"https://ceshi.fileresource.vipsouq.net/yks-resource/screen/2017-07-13/20170713170410934_Zmj7.jpg","proAttrValueId":19,"proAttrKeyId":5},{"proPictureId":726,"productId":46,"version":null,"smallImg":"https://ceshi.fileresource.vipsouq.net/yks-resource/screen/2017-07-13/20170713170411752_rZ6M_min.jpg","mediumImg":"https://ceshi.fileresource.vipsouq.net/yks-resource/screen/2017-07-13/20170713170411752_rZ6M_mid.jpg","largeImg":"https://ceshi.fileresource.vipsouq.net/yks-resource/screen/2017-07-13/20170713170411752_rZ6M.jpg","proAttrValueId":19,"proAttrKeyId":5}]}]},{"keyId":6,"name":"internal memory","keySort":3,"type":2,"mainAttrFlag":false,"attrValues":[{"valueId":388,"value":"8g","valueSort":21,"skuPictures":null}]},{"keyId":333,"name":"ram","keySort":21,"type":2,"mainAttrFlag":false,"attrValues":[{"valueId":385,"value":"1g","valueSort":23,"skuPictures":null}]}]
     * pictures : [{"proPictureId":5444,"productId":46,"version":16276,"smallImg":"https://ceshi.fileresource.vipsouq.net/yks-resource/screen/2017-07-13/20170713170036351_YThP_min.jpg","mediumImg":"https://ceshi.fileresource.vipsouq.net/yks-resource/screen/2017-07-13/20170713170036351_YThP_mid.jpg","largeImg":"https://ceshi.fileresource.vipsouq.net/yks-resource/screen/2017-07-13/20170713170036351_YThP.jpg"},{"proPictureId":5445,"productId":46,"version":29543,"smallImg":"https://ceshi.fileresource.vipsouq.net/yks-resource/screen/2017-07-13/20170713170037319_KHxx_min.jpg","mediumImg":"https://ceshi.fileresource.vipsouq.net/yks-resource/screen/2017-07-13/20170713170037319_KHxx_mid.jpg","largeImg":"https://ceshi.fileresource.vipsouq.net/yks-resource/screen/2017-07-13/20170713170037319_KHxx.jpg"}]
     * skus : [{"skuId":21,"quantity":47,"sku":"1000029","originPrice":106.19,"activePrice":null,"discount":null,"activityStatus":0,"attrs":[{"skuAttrId":1709,"sku":"1000029","keyId":5,"keyName":"color","valueId":19,"valueName":"black","mainAttrFlag":true},{"skuAttrId":1710,"sku":"1000029","keyId":6,"keyName":"internal memory","valueId":388,"valueName":"8g","mainAttrFlag":false},{"skuAttrId":1711,"sku":"1000029","keyId":333,"keyName":"ram","valueId":385,"valueName":"1g","mainAttrFlag":false}]}]
     * specific : [{"name":"Operating system","values":"Android"}]
     * commentContent : {"hasComment":false,"averageStar":0,"totalComments":0,"comments":null}
     */

    private int id;
    private String name;
    private String description;
    private String keyword;
    private String seo;
    private String brandLogo;
    private String brandName;
    private String brandShortName;
    private String brandSeo;
    private int categoryId;
    private int clickCount;
    private boolean favorite;
    private int favoriteCount;
    private long createTime;
    private long updateTime;
    private CommentContentEntity commentContent;
    private List<AttrsEntity> attrs;
    private List<PicturesEntity> pictures;
    private List<SkuEntity> skus;
    private List<SpecificEntity> specific;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSeo() {
        return seo;
    }

    public void setSeo(String seo) {
        this.seo = seo;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandShortName() {
        return brandShortName;
    }

    public void setBrandShortName(String brandShortName) {
        this.brandShortName = brandShortName;
    }

    public String getBrandSeo() {
        return brandSeo;
    }

    public void setBrandSeo(String brandSeo) {
        this.brandSeo = brandSeo;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public CommentContentEntity getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(CommentContentEntity commentContent) {
        this.commentContent = commentContent;
    }

    public List<AttrsEntity> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<AttrsEntity> attrs) {
        this.attrs = attrs;
    }

    public List<PicturesEntity> getPictures() {
        return pictures;
    }

    public void setPictures(List<PicturesEntity> pictures) {
        this.pictures = pictures;
    }

    public List<SkuEntity> getSkus() {
        return skus;
    }

    public void setSkus(List<SkuEntity> skus) {
        this.skus = skus;
    }

    public List<SpecificEntity> getSpecific() {
        return specific;
    }

    public void setSpecific(List<SpecificEntity> specific) {
        this.specific = specific;
    }
}
