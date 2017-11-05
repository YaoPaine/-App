package com.yao.moduleb.model.entity;

import java.util.List;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/3 下午6:15
 * @Version:
 */

public class AttrsEntity {

    /**
     * keyId : 5
     * name : color
     * keySort : 1
     * type : 1
     * mainAttrFlag : true
     * attrValues : [{"valueId":19,"value":"black","valueSort":2,"skuPictures":[{"proPictureId":725,"productId":46,"version":null,"smallImg":"https://ceshi.fileresource.vipsouq.net/yks-resource/screen/2017-07-13/20170713170410934_Zmj7_min.jpg","mediumImg":"https://ceshi.fileresource.vipsouq.net/yks-resource/screen/2017-07-13/20170713170410934_Zmj7_mid.jpg","largeImg":"https://ceshi.fileresource.vipsouq.net/yks-resource/screen/2017-07-13/20170713170410934_Zmj7.jpg","proAttrValueId":19,"proAttrKeyId":5},{"proPictureId":726,"productId":46,"version":null,"smallImg":"https://ceshi.fileresource.vipsouq.net/yks-resource/screen/2017-07-13/20170713170411752_rZ6M_min.jpg","mediumImg":"https://ceshi.fileresource.vipsouq.net/yks-resource/screen/2017-07-13/20170713170411752_rZ6M_mid.jpg","largeImg":"https://ceshi.fileresource.vipsouq.net/yks-resource/screen/2017-07-13/20170713170411752_rZ6M.jpg","proAttrValueId":19,"proAttrKeyId":5}]}]
     */

    private int keyId;
    private String name;
    private int keySort;
    private int type;
    private boolean mainAttrFlag;
    private List<AttrValuesEntity> attrValues;

    public int getKeyId() {
        return keyId;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKeySort() {
        return keySort;
    }

    public void setKeySort(int keySort) {
        this.keySort = keySort;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isMainAttrFlag() {
        return mainAttrFlag;
    }

    public void setMainAttrFlag(boolean mainAttrFlag) {
        this.mainAttrFlag = mainAttrFlag;
    }

    public List<AttrValuesEntity> getAttrValues() {
        return attrValues;
    }

    public void setAttrValues(List<AttrValuesEntity> attrValues) {
        this.attrValues = attrValues;
    }
}
