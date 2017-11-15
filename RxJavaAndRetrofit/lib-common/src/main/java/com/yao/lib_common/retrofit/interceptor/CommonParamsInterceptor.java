package com.yao.lib_common.retrofit.interceptor;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import okhttp3.*;
import okio.Buffer;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @Description: retrofit的公共参数
 * @Author: YaoPaine
 * @CreateDate: 2017/11/15 上午10:14
 * @Version:
 */

public class CommonParamsInterceptor implements Interceptor {

    private Map<String, String> mCommonParams;

    public CommonParamsInterceptor(@NonNull Map<String, String> commonParams) {
        this.mCommonParams = commonParams;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request newRequest = originalRequest;

        String method = originalRequest.method();
        RequestBody requestBody = originalRequest.body();
        MediaType mediaType = requestBody.contentType();
        if (TextUtils.equals(method, "GET") || !MediaType.parse("Content-Type: application/json;charset=UTF-8").equals(mediaType)) {
            HttpUrl.Builder builder = originalRequest.url().newBuilder();
            Set<String> keySet = mCommonParams.keySet();
            for (String key : keySet) {
                builder.addEncodedQueryParameter(key, mCommonParams.get(key)).build();
            }
            HttpUrl httpUrl = builder.build();
            newRequest = originalRequest.newBuilder().url(httpUrl).build();

        } else if (TextUtils.equals(method, "POST")) {
            if (MediaType.parse("Content-Type: application/json;charset=UTF-8").equals(mediaType)) {
                Buffer bufferedSink = new Buffer();
                requestBody.writeTo(bufferedSink);
                String requestStr = bufferedSink.readUtf8();

                try {
                    JSONObject jsonObject = new JSONObject(requestStr);
                    Set<String> keySet = mCommonParams.keySet();
                    for (String key : keySet) {
                        jsonObject.put(key, mCommonParams.get(key));
                    }
                    RequestBody newRequestBody = RequestBody.create(mediaType, jsonObject.toString());
                    newRequest = originalRequest.newBuilder().post(newRequestBody).build();
                } catch (Exception e) {

                }
            }
        }

        return chain.proceed(newRequest);
    }
}
