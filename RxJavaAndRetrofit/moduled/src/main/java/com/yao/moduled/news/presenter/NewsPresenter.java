package com.yao.moduled.news.presenter;

import com.yao.moduled.news.model.Model;
import com.yao.moduled.news.model.NewsModel;

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
    public void loadNews(int type, int page) {
//        addSubscription(mModel.loadNews("goods/detailNew", type), new ApiCallBack<String>(){});
    }
}
