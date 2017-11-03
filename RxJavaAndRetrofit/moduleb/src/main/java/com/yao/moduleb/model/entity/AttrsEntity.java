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

}
