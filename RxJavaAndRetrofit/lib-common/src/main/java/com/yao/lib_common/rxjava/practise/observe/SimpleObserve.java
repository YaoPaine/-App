package com.yao.lib_common.rxjava.practise.observe;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/12/6 下午3:36
 * @Version:
 */

public abstract class SimpleObserve<T> implements Observer<T> {

    private String TAG = "SimpleObserve";
    protected T t;
    protected Disposable mDisposable;

    public SimpleObserve() {

    }

    public SimpleObserve(T t) {
        this.t = t;
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onError(Throwable e) {
        if (t != null) {
            Log.e(TAG, "onError: " + e + "\tt:" + t);
        } else {
            Log.e(TAG, "onError: " + e);
        }
    }

    @Override
    public void onComplete() {
        if (t != null) {
            Log.e(TAG, "onComplete: " + "\tt:" + t);
        } else {
            Log.e(TAG, "onComplete: ");
        }
    }
}
