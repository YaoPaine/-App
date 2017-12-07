package com.yao.lib_mvp.mvp2.model.entity;

import java.util.List;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/3 下午6:29
 * @Version:
 */

public class SkuEntity {

    private int skuId;
    private int quantity;
    private String sku;
    private double originPrice;
    private double activePrice;
    private double discount;
    private int activityStatus;
    private List<SkuAttrEntity> attrs;
    private String skuCombination;

    public int getSkuId() {
        return skuId;
    }

    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public double getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(double originPrice) {
        this.originPrice = originPrice;
    }

    public double getActivePrice() {
        return activePrice;
    }

    public void setActivePrice(double activePrice) {
        this.activePrice = activePrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(int activityStatus) {
        this.activityStatus = activityStatus;
    }

    public List<SkuAttrEntity> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<SkuAttrEntity> attrs) {
        this.attrs = attrs;
    }

    public String getSkuCombination() {
        return skuCombination;
    }

    public void setSkuCombination(String skuCombination) {
        this.skuCombination = skuCombination;
    }
}
