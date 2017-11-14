package com.yao.moduled.news.model;

import com.yao.lib_common.retrofit.model.entity.news.NewsRequestModel;
import com.yao.lib_mvp.model.BaseModel;

import io.reactivex.Observable;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/2 下午3:27
 * @Version:
 */

public abstract class Model extends BaseModel {

    public abstract Observable<NewsRequestModel> loadNews(String url, int type);

}
