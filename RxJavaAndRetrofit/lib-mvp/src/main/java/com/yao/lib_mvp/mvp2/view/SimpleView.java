package com.yao.lib_mvp.mvp2.view;

import com.yao.lib_mvp.mvp2.model.entity.GoodsEntity;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/12/7 上午9:26
 * @Version:
 */

public interface SimpleView extends IMvpBaseView {
    //请求时展示加载
    void requestLoading();

    //请求成功 WeatherBean
    void resultSuccess(GoodsEntity result);

    //请求失败
    void resultFailure(String result);
}
