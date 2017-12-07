package com.yao.lib_mvp.mvp2.persenter;

import com.yao.lib_mvp.mvp2.view.IMvpBaseView;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/12/7 上午9:24
 * @Version:
 */

public abstract class MvpBasePresenter<V extends IMvpBaseView> implements IMvpBasePresenter<V> {

    protected V IView;

    @Override
    public void attachView(V view) {
        this.IView = view;
    }

    @Override
    public void detachView() {
        this.IView = null;
    }

    @Override
    public V getMvpView() {
        return IView;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }
}