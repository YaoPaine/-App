package com.yao.lib_common.retrofit.interceptor;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Description: 设置基础header
 * @Author: YaoPaine
 * @CreateDate: 2017/11/15 上午10:05
 * @Version:
 */

public class BaseHeaderInterceptor implements Interceptor {

    private Map<String, String> headers;

    public BaseHeaderInterceptor(Map<String, String> headers) {
        if (headers == null) throw new NullPointerException("headers can not be null");
        this.headers = headers;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request.Builder builder = chain.request().newBuilder();
        if (this.headers.size() > 0) {
            Set<String> keySet = headers.keySet();
            for (String key : keySet) {
                builder.addHeader(key, headers.get(key)).build();
            }
        }

        return chain.proceed(builder.build());
    }
}
