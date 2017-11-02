package com.yao.moduled.news.presenter;

import com.yao.lib_mvp.presenter.BasePresenter;
import com.yao.moduled.news.view.NewsView;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/2 下午12:20
 * @Version:
 */

public abstract class Presenter extends BasePresenter<NewsView> {

    public abstract void loadNews(int type, int page);

}
