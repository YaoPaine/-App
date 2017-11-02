package com.yao.lib_common.model.entity.news;

import java.util.List;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/2 下午3:28
 * @Version:
 */

public class NewsRequestModel {
    private List<NewsBean> newsBeanList;

    public List<NewsBean> getNewsBeanList() {
        return newsBeanList;
    }

    public void setNewsBeanList(List<NewsBean> newsBeanList) {
        this.newsBeanList = newsBeanList;
    }
}
