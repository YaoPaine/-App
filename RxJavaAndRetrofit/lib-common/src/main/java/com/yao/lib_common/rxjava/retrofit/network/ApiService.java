package com.yao.lib_common.rxjava.retrofit.network;

import com.yao.lib_common.rxjava.retrofit.model.entity.BaseResult;
import com.yao.lib_common.rxjava.retrofit.model.entity.news.NewsRequestModel;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/2 下午3:34
 * @Version:
 */

public interface ApiService {

    @GET
    Observable<NewsRequestModel> getNewList(@Url String url, @QueryMap Map<String, Object> params);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST
    Observable<BaseResult<String>> postWithStringParam(@Url String path, @Body String json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST
    Observable<BaseResult<String>> postWithMapParam(@Url String path, @Body Map<String, Object> map);
}
