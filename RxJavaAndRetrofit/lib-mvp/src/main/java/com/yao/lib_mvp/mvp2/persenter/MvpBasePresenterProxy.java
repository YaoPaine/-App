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
    private boolean mIsAttachView;
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

    /**
     * 绑定Presenter和view
     */
    public void onResume(V mvpView) {
        getMvpPresenter();
        if (mPresenter != null && !mIsAttachView) {
            mPresenter.attachView(mvpView);
            mIsAttachView = true;
        }
    }

    /**
     * 销毁Presenter持有的View
     */
    private void onDetachMvpView() {
        if (mPresenter != null && mIsAttachView) {
            mPresenter.onDetachMvpView();
            mIsAttachView = false;
        }
    }

    /**
     * 销毁Presenter
     */
    public void onDestroy() {
        if (mPresenter != null) {
            onDetachMvpView();
            mPresenter.onDestroyPresenter();
            mPresenter = null;
        }
    }

    /**
     * 意外销毁时保存数据
     *
     * @return
     */
    public Bundle onSaveInstanceState() {
        Bundle bundle = new Bundle();
        getMvpPresenter();
        if (mPresenter != null) {
            Bundle presenterBundle = new Bundle();
            mPresenter.onSaveInstanceState(presenterBundle);
            bundle.putBundle(PRESENTER_KEY, presenterBundle);
        }
        return bundle;
    }

    /**
     * 意外销毁后恢复
     *
     * @param savedInstanceState
     */
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        mBundle = savedInstanceState;
    }

}
