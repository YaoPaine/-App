package com.yao.moduled.news.view;


import com.yao.lib_common.model.entity.news.NewsBean;
import com.yao.lib_mvp.view.IView;

import java.util.List;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/1 下午8:43
 * @Version:
 */

public interface NewsView extends IView {
    void addNews(List<NewsBean> newsList);

    void showLoadFailMsg(String msg);
}
