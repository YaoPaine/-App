package com.yao.lib_mvp.presenter;

import com.yao.lib_mvp.view.IView;

/**
 * 作者: heyao
 * 创建时间:2017/11/1
 * 功能描述:MVP的P层
 */

public interface IPresenter<T extends IView> {
    /**
     * 关联P与V（绑定，VIEW销毁适合解绑）
     */
    void attachView(T view);

    /**
     * 取消关联P与V（防止内存泄漏）
     */
    void detachView();

    /**
     * RX订阅
     */
    void subscribe();

    /**
     * RX取消订阅
     */
    void unSubscribe();
}
