package com.yao.lib_mvp.mvp2.persenter;

import android.os.Bundle;

import com.yao.lib_mvp.mvp2.view.IMvpBaseView;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/12/7 下午2:50
 * @Version:
 */

public interface IMvpBasePresenter<V extends IMvpBaseView> {

    /**
     * 将presenter 与view绑定
     */
    void attachView(V view);

    /**
     * 将presenter与view的关联解除
     */
    void detachView();

    /**
     * activity 意外销毁时调用
     *
     * @param outState
     */
    void onSaveInstanceState(Bundle outState);

    void onAttachMvpView(V view);

    void onCreatePresenter(Bundle savedInstanceState);

    void onStart();

    void onRestart();

    void onResume();

    void onPause();

    void onStop();

    void onDetachMvpView();

    void onDestroyPresenter();

    /**
     * @return 获取V层
     */
    V getMvpView();
}
