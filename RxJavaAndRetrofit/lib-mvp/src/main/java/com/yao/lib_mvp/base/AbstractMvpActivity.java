package com.yao.lib_mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yao.lib_mvp.mvp2.persenter.MvpBasePresenter;
import com.yao.lib_mvp.mvp2.view.IMvpBaseView;

import javax.inject.Inject;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/12/7 下午2:58
 * @Version:
 */

public abstract class AbstractMvpActivity<V extends IMvpBaseView<P>, P extends MvpBasePresenter<V>>
        extends BaseActivity implements IMvpBaseView<P> {

    @Inject
    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        beforeSetContentView();
    }

    protected abstract void beforeSetContentView();

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        afterSetContentView();
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }

        if (mPresenter == null) throw new NullPointerException("mPresenter is null");
        //总感觉这里写得不够优雅
        mPresenter.attachView((V) this);
    }

    protected abstract void afterSetContentView();

    protected abstract P createPresenter();

    @Override
    protected void onStart() {
        super.onStart();
        if (mPresenter != null) {
            mPresenter.onStart();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mPresenter != null) {
            mPresenter.onRestart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.onStop();
        }
    }

    /**
     * 获取Presenter
     *
     * @return 返回子类创建的Presenter
     */
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
