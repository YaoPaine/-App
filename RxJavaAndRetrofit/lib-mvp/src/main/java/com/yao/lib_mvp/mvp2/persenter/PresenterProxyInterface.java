package com.yao.lib_mvp.mvp2.persenter;

import com.yao.lib_mvp.mvp2.view.IMvpBaseView;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/12/7 下午6:12
 * @Version:
 */

public interface PresenterProxyInterface<V extends IMvpBaseView<P>, P extends MvpBasePresenter<V>> {

    /**
     * 设置创建Presenter的工厂
     */
    void setPresenterFactory(PresenterMvpFactory<V, P> factory);

    /**
     * 获取Presenter的工厂类
     */
    PresenterMvpFactory<V, P> getPresenterFactory();

    /**
     * 获取创建的Presenter
     */
    P getMvpPresenter();
}
