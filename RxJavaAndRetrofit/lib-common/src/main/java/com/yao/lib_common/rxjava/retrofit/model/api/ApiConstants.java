package com.yao.lib_common.rxjava.retrofit.model.api;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/2 下午4:27
 * @Version:
 */

public interface ApiConstants {

    String HOST = "https://ceshi.api.vipsouq.net";
    String BASE_URL = HOST + "/api/gate/";

    String JOLLY_CHIC_URL = "http://app.jollychic.com/";

    String ADDRESS = "address/";
    /**
     * 获取Region/Province/State
     */
    String getRegionByParentNew = ADDRESS + "getRegionByParentNew.do";


}
