package com.yao.lib_mvp.mvp2.persenter;

import android.os.Bundle;

import com.yao.lib_mvp.mvp2.view.IMvpBaseView;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/12/7 下午6:33
 * @Version:
 */

public class MvpBasePresenterProxy<V extends IMvpBaseView<P>, P extends MvpBasePresenter<V>> implements PresenterProxyInterface<V, P> {
    /**
     * 获取onSaveInstanceState中bundle的key
     */
    private static final String PRESENTER_KEY = "presenter_key";

    private P mPresenter;
    private Bundle mBundle;
    private PresenterMvpFactory<V, P> mFactory;

    public MvpBasePresenterProxy(PresenterMvpFactory<V, P> factory) {
        this.mFactory = factory;
    }

    @Override
    public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
        if (mPresenter != null) {
            throw new IllegalArgumentException("这个方法只能在getMvpPresenter()之前调用，如果Presenter已经创建则不能再修改");
        }
        this.mFactory = presenterFactory;
    }

    @Override
    public PresenterMvpFactory<V, P> getPresenterFactory() {
        return mFactory;
    }

    @Override
    public P getMvpPresenter() {
        if (mFactory != null) {
            if (mPresenter == null) {
                mPresenter = mFactory.createMvpPresenter();
                mPresenter.onCreatePresenter(mBundle == null ? null : mBundle.getBundle(PRESENTER_KEY));
            }
        }
        return mPresenter;
    }


}
