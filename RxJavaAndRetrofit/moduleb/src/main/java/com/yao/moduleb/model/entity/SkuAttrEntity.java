package com.yao.moduleb.model.entity;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/3 下午6:30
 * @Version:
 */

public class SkuAttrEntity {
    private int skuAttrId;
    private String sku;
    private int keyId;
    private String keyName;
    private int valueId;
    private String valueName;
    private boolean mainAttrFlag;

    public int getSkuAttrId() {
        return skuAttrId;
    }

    public void setSkuAttrId(int skuAttrId) {
        this.skuAttrId = skuAttrId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getKeyId() {
        return keyId;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public int getValueId() {
        return valueId;
    }

    public void setValueId(int valueId) {
        this.valueId = valueId;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public boolean isMainAttrFlag() {
        return mainAttrFlag;
    }

    public void setMainAttrFlag(boolean mainAttrFlag) {
        this.mainAttrFlag = mainAttrFlag;
    }
}
