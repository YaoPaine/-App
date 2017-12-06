package com.yao.moduled.news;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yao.lib_common.rxjava.retrofit.model.entity.news.NewsBean;
import com.yao.lib_mvp.base.BaseActivity;
import com.yao.moduled.R;
import com.yao.moduled.news.view.NewsView;

import java.util.List;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/2 上午9:24
 * @Version:
 */

public class NewListActivity extends BaseActivity implements NewsView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_d_activity_news);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void showLoading(String msg, int progress) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void showErrorMsg(String msg, String content) {

    }

    @Override
    public void close() {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void addNews(List<NewsBean> newsList) {

    }

    @Override
    public void showLoadFailMsg(String msg) {

    }
}
