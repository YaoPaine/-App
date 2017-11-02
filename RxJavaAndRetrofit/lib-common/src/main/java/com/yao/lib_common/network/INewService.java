package com.yao.lib_common.network;

import com.yao.lib_common.model.entity.news.NewsRequestModel;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/2 下午3:34
 * @Version:
 */

public interface INewService {

    @GET
    Observable<NewsRequestModel> getNewList(@Url String url, @QueryMap Map<String, Object> params);
}
