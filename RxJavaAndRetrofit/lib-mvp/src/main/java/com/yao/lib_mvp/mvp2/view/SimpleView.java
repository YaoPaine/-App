package com.yao.lib_mvp.mvp2.view;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/12/7 上午9:26
 * @Version:
 */

public interface SimpleView extends IView {
    //请求时展示加载
    void requestLoading();

    //请求成功 WeatherBean
    void resultSuccess(Object result);

    //请求失败
    void resultFailure(String result);
}
