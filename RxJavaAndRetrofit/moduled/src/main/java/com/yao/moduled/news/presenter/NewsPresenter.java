package com.yao.moduled.news.presenter;

import com.yao.lib_common.model.entity.news.NewsRequestModel;
import com.yao.moduled.news.model.Model;
import com.yao.moduled.news.model.NewsModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import rx.internal.util.SubscriptionList;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/2 下午12:17
 * @Version:
 */

public class NewsPresenter extends Presenter {

    private Model mModel;

    public NewsPresenter() {
        mModel = new NewsModel();
    }

    @Override
    public void subscribe() {

    }

    @Override
    protected SubscriptionList createSubscriptionList() {
        return null;
    }

    @Override
    public void loadNews(int type, int page) {
        addSubscription(mModel.loadNews("goods/detailNew", type), new Observer<NewsRequestModel>() {

            @Override
            public void onSubscribe(Disposable d) {
                getMvpView().showLoading();
            }

            @Override
            public void onNext(NewsRequestModel newsRequestModel) {
                getMvpView().addNews(newsRequestModel.getNewsBeanList());
            }

            @Override
            public void onError(Throwable e) {
                getMvpView().showErrorMsg(e.getMessage(), e.toString());
            }

            @Override
            public void onComplete() {
                getMvpView().hideLoading();
            }
        });
    }
}
