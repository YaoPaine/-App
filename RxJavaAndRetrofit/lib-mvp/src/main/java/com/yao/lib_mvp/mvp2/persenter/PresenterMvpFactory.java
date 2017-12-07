package com.yao.lib_mvp.mvp2.persenter;

import com.yao.lib_mvp.mvp2.view.IMvpBaseView;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/12/7 下午5:49
 * @Version:
 */

public interface PresenterMvpFactory<V extends IMvpBaseView<P>, P extends MvpBasePresenter<V>> {

    /**
     * 创建Presenter的接口方法
     *
     * @return 需要创建的Presenter
     */
    P createMvpPresenter();

}
