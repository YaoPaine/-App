package com.yao.lib_common.rxjava.retrofit.observer;

import android.util.Log;

import com.yao.lib_common.dagger.Container;
import com.yao.lib_common.dagger.Tank;
import com.yao.lib_common.rxjava.retrofit.exception.ApiException;

import io.reactivex.observers.DisposableObserver;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/2 下午4:36
 * @Version:
 */

public abstract class ApiCallBack<T> extends DisposableObserver<T> {

    private final String TAG = "ApiCallBack";

    @Override
    public void onComplete() {
        Log.e(TAG, "onComplete: ");
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: " + e);
        if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            Log.e(TAG, "message: " + apiException.getMessage());
        }
    }

    @Override
    public void onNext(T data) {
        if (data == null) {
            onSuccessIsNull();
        } else {
            onSuccessNotNull(data);
        }
//        new Container().init();
//        new Tank().init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
    }

    /**
     * 请求接口成功,但是data为null
     */
    public void onSuccessIsNull() {
        Log.e(TAG, "onSuccessIsNull: ");
    }

    /**
     * 请求成功，且data不为null
     *
     * @param t response data字段的数据
     */
    public void onSuccessNotNull(T t) {

    }

}
