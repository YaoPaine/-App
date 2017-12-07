package com.yao.lib_mvp.mvp2.persenter;

import com.yao.lib_common.rxjava.practise.observe.SimpleObserve;
import com.yao.lib_mvp.mvp2.model.SimpleModel;
import com.yao.lib_mvp.mvp2.model.entity.GoodsEntity;
import com.yao.lib_mvp.mvp2.view.SimpleView;

import javax.inject.Inject;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/12/7 上午9:43
 * @Version:
 */

public class SimplePresenter implements IMvpBasePresenter {

    private SimpleView mSimpleView;

    @Inject
    SimpleModel mSimpleModel;

    @Inject
    public SimplePresenter() {
//        attach(simpleView);
//        this.mSimpleModel = new SimpleModel();
    }

    @Inject
    public SimplePresenter(SimpleModel simpleModel) {
        this.mSimpleModel = simpleModel;
    }

    public void clickRequest() {
        if (mSimpleView == null) return;
        mSimpleView.requestLoading();
        mSimpleModel.requestData().subscribe(new SimpleObserve<GoodsEntity>() {

            @Override
            public void onNext(GoodsEntity goodsEntity) {
                if (mSimpleView != null)
                    mSimpleView.resultSuccess(goodsEntity);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mSimpleView != null)
                    mSimpleView.resultFailure(e.toString());
            }
        });
    }

    /**
     * 绑定
     *
     * @param view
     */
    public void attach(SimpleView view) {
        this.mSimpleView = view;
    }

    /**
     * 解除绑定
     */
    public void detach() {
        this.mSimpleView = null;
        interruptHttp();
    }

    /**
     * 取消网络请求
     */
    private void interruptHttp() {
        mSimpleModel.interruptHttp();
    }
}
