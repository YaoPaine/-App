package com.yao.lib_mvp.mvp2.persenter;

import com.yao.lib_mvp.mvp2.view.IMvpBaseView;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/12/7 下午2:50
 * @Version:
 */

public interface IMvpBasePresenter<T extends IMvpBaseView> {

    /**
     * 将presenter 与view绑定
     */
    void attachView(T view);

    /**
     * 将presenter与view的关联解除
     */
    void detachView();

    /**
     * @return 获取V层
     */
    T getMvpView();

    void onStart();

    void onRestart();

    void onResume();

    void onPause();

    void onStop();
}
