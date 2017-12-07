package com.yao.lib_mvp.mvp2.model.entity;

import java.util.List;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/3 下午6:16
 * @Version:
 */

public class AttrValuesEntity {
    /**
     * valueId : 19
     * value : black
     * valueSort : 2
     * skuPictures : [{"proPictureId":725,"productId":46,"version":null,"smallImg":"https://ceshi.fileresource.vipsouq.net/yks-resource/screen/2017-07-13/20170713170410934_Zmj7_min.jpg","mediumImg":"https://ceshi.fileresource.vipsouq.net/yks-resource/screen/2017-07-13/20170713170410934_Zmj7_mid.jpg","largeImg":"https://ceshi.fileresource.vipsouq.net/yks-resource/screen/2017-07-13/20170713170410934_Zmj7.jpg","proAttrValueId":19,"proAttrKeyId":5},{"proPictureId":726,"productId":46,"version":null,"smallImg":"https://ceshi.fileresource.vipsouq.net/yks-resource/screen/2017-07-13/20170713170411752_rZ6M_min.jpg","mediumImg":"https://ceshi.fileresource.vipsouq.net/yks-resource/screen/2017-07-13/20170713170411752_rZ6M_mid.jpg","largeImg":"https://ceshi.fileresource.vipsouq.net/yks-resource/screen/2017-07-13/20170713170411752_rZ6M.jpg","proAttrValueId":19,"proAttrKeyId":5}]
     */

    private int valueId;
    private String value;
    private int valueSort;
    private List<SkuPicturesEntity> skuPictures;

    public int getValueId() {
        return valueId;
    }

    public void setValueId(int valueId) {
        this.valueId = valueId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getValueSort() {
        return valueSort;
    }

    public void setValueSort(int valueSort) {
        this.valueSort = valueSort;
    }

    public List<SkuPicturesEntity> getSkuPictures() {
        return skuPictures;
    }

    public void setSkuPictures(List<SkuPicturesEntity> skuPictures) {
        this.skuPictures = skuPictures;
    }
}
