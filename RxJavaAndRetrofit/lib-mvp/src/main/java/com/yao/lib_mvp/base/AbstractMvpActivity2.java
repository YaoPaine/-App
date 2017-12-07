package com.yao.lib_mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yao.lib_mvp.mvp2.persenter.MvpBasePresenter;
import com.yao.lib_mvp.mvp2.persenter.MvpBasePresenterProxy;
import com.yao.lib_mvp.mvp2.persenter.PresenterMvpFactory;
import com.yao.lib_mvp.mvp2.persenter.PresenterMvpFactoryImpl;
import com.yao.lib_mvp.mvp2.persenter.PresenterProxyInterface;
import com.yao.lib_mvp.mvp2.view.IMvpBaseView;

/**
 * Created by heyao on 12/7/17.
 */

public class AbstractMvpActivity2<V extends IMvpBaseView<P>, P extends MvpBasePresenter<V>> extends BaseActivity
        implements PresenterProxyInterface<V, P> {
    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";
    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    private MvpBasePresenterProxy<V, P> mProxy = new MvpBasePresenterProxy<>(PresenterMvpFactoryImpl.<V, P>createFactory(getClass()));

    @Override
    public void setPresenterFactory(PresenterMvpFactory<V, P> factory) {
        mProxy.setPresenterFactory(factory);
    }

    @Override
    public PresenterMvpFactory<V, P> getPresenterFactory() {
        return mProxy.getPresenterFactory();
    }

    @Override
    public P getMvpPresenter() {
        return mProxy.getMvpPresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_SAVE_KEY, mProxy.onSaveInstanceState());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mProxy.onResume((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mProxy.onDestroy();
    }
}
