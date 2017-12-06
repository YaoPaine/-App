package com.yao.lib_common.rxjava.practise.operator;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @Description: 操作符
 * @Author: YaoPaine
 * @CreateDate: 2017/12/6 上午11:40
 * @Version:
 */

public class Operators {

    private final static String TAG = "Operators";

    @Inject
    public Operators() {

    }

    public void create() {
        Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        Log.e(TAG, "currentThread: " + Thread.currentThread().getName());
                        e.onNext(1);
                        e.onNext(2);
                        e.onError(new NullPointerException("what a storage love"));
                        e.onNext(3);
                        e.onComplete();
                        e.onNext(4);
                        boolean disposed = e.isDisposed();
                        Log.e(TAG, "disposed: " + disposed);
                        e.onNext(5);
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {

                    private Disposable mDisposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e(TAG, "onNext: " + integer);
                        if (integer == 4) {
                            mDisposable.dispose();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: ");
                    }
                });
    }
}
