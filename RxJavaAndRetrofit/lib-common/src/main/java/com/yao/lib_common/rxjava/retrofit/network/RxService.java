package com.yao.lib_common.rxjava.retrofit.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yao.lib_common.rxjava.retrofit.gson.BaseResultJsonDeserializer;
import com.yao.lib_common.rxjava.retrofit.gson.GsonUtils;
import com.yao.lib_common.rxjava.retrofit.gson.ResultJsonDeserializer;
import com.yao.lib_common.rxjava.retrofit.model.api.ApiConstants;
import com.yao.lib_common.rxjava.retrofit.model.entity.BaseResult;
import com.yao.lib_common.rxjava.retrofit.model.entity.Result;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/2 下午4:19
 * @Version:
 */

public class RxService {

    private static Retrofit mRetrofit;
    private static final int READ_TIMEOUT = 60;//读取超时时间,单位秒
    private static final int CONN_TIMEOUT = 50;//连接超时时间,单位秒

    public static Retrofit createRetrofit() {
        if (mRetrofit == null) {
            synchronized (RxService.class) {
                if (mRetrofit == null) {

                    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(loggingInterceptor)
                            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                            .connectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS)
                            .build();

//                    Gson gson = new GsonBuilder()
//                            .registerTypeHierarchyAdapter(BaseResult.class,
//                                    new BaseResultJsonDeserializer()).create();

//                    Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(Result.class,
//                            new ResultJsonDeserializer()).create();
                    Gson gson = GsonUtils.instance();

                    Retrofit.Builder builder = new Retrofit.Builder()
                            .client(okHttpClient);

                    builder.addConverterFactory(GsonConverterFactory.create(gson));

                    mRetrofit = builder
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .baseUrl(ApiConstants.JOLLY_CHIC_URL)
                            .build();
                }
            }
        }
        return mRetrofit;
    }
}
