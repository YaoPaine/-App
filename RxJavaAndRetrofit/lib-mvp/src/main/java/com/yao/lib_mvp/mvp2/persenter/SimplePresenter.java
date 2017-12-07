package com.yao.lib_mvp.mvp2.persenter;

import com.yao.lib_common.rxjava.practise.observe.SimpleObserve;
import com.yao.lib_mvp.mvp2.model.SimpleModel;
import com.yao.lib_mvp.mvp2.model.entity.GoodsEntity;
import com.yao.lib_mvp.mvp2.view.SimpleView;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/12/7 上午9:43
 * @Version:
 */

public class SimplePresenter implements IPresenter {

    private SimpleView mSimpleView;
    private SimpleModel mSimpleModel;

    public SimplePresenter(SimpleView simpleView) {
        this.mSimpleView = simpleView;
        this.mSimpleModel = new SimpleModel();
    }

    public void clickRequest() {
        mSimpleModel.requestData().subscribe(new SimpleObserve<GoodsEntity>() {

            @Override
            public void onNext(GoodsEntity goodsEntity) {
                mSimpleView.resultSuccess(goodsEntity);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mSimpleView.resultFailure(e.toString());
            }
        });
    }
}
