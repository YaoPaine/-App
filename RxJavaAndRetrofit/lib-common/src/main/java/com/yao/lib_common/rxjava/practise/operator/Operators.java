package com.yao.lib_common.rxjava.practise.operator;

import android.util.Log;

import com.yao.lib_common.rxjava.practise.observe.SimpleObserve;
import com.yao.lib_common.rxjava.practise.transform.SimpleTransformer;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

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
//                        e.onError(new NullPointerException("what a storage love"));
                        e.onNext(3);
//                        e.onComplete();
                        e.onNext(4);
                        e.onNext(5);
                        boolean disposed = e.isDisposed();
                        Log.e(TAG, "disposed: " + disposed);
                        e.onNext(6);
                    }
                }).compose(new SimpleTransformer<Integer>())
                .subscribe(new SimpleObserve<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        Log.e(TAG, "onNext: " + integer + "\t" + Thread.currentThread().getName());
                        if (integer == 4) {
                            mDisposable.dispose();
                            Log.e(TAG, "onNext: " + mDisposable.isDisposed());
                        }
                    }
                });
    }

    public void from() {
//        Observable
//                .fromArray(1, 2, 3, 4, 5)
//                .compose(new SimpleTransformer<Integer>())
//                .subscribe(new SimpleObserve<Integer>() {
//                    @Override
//                    public void onNext(Integer integer) {
//                        Log.e(TAG, "onNext: " + integer + "\t" + Thread.currentThread().getName());
//                        if (integer == 4) {
//                            mDisposable.dispose();
//                            Log.e(TAG, "onNext: " + mDisposable.isDisposed());
//                        }
//                    }
//                });

//        Integer[] items = {0, 1, 2, 3, 4, 5};
//        Observable.fromArray(items)
//                .compose(new SimpleTransformer<Integer>())
//                .subscribe(new SimpleObserve<Integer>() {
//                    @Override
//                    public void onNext(Integer integer) {
//                        Log.e(TAG, "onNext: " + integer + "\t" + Thread.currentThread().getName());
//                    }
//                });

        ExecutorService executor = Executors.newCachedThreadPool();
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Log.e(TAG, String.format("callable run in thread %s", Thread.currentThread().getName()));
                return "run in callable call function";
            }
        };

        FutureTask<String> futureTask = new FutureTask<>(callable);
        Log.e(TAG, "instance future task");
        Observable<String> observable = Observable.fromFuture(futureTask);
        Log.e(TAG, "instance observable");
        executor.submit(callable);
        executor.shutdown();
        observable
                .compose(new SimpleTransformer<String>())
                .subscribe(new SimpleObserve<String>() {
                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "onNext: " + String.format("observer onNext run in thread %s ", Thread.currentThread().getName()));
                        Log.e(TAG, "onNext: " + String.format("observer get msg %s ", s));
                    }
                });
    }

    public void just() {
        Observable<String> observable = Observable.just(firstFunction(), secondFunction());
        //使用just，会在创建observable时，立即执行function。
        //在注册subscriber之后，会将结果发射到该subscriber中
        try {
            Thread.sleep(1000 * 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        observable.compose(new SimpleTransformer<String>())
                .subscribe(new SimpleObserve<String>() {
                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, ":\t" + String.format("run next %s ", s));
                    }
                });
    }

    private String secondFunction() {
        Log.d(TAG, String.format("run in firstFunction ,cur thread is %s", Thread.currentThread().toString()));
        return "do in some function1";
    }

    private String firstFunction() {
        Log.d(TAG, String.format("run in firstFunction ,cur thread is %s", Thread.currentThread().toString()));
        return "do in some function2";
    }

    public void never() {
        Observable.never().compose(new SimpleTransformer<>()).subscribe(new SimpleObserve<Object>() {

            @Override
            public void onNext(Object o) {
                Log.e(TAG, "onNext: " + o);
            }
        });
    }

    public void empty() {
        Observable.empty()
                .compose(new SimpleTransformer<>())
                .subscribe(new SimpleObserve<Object>() {
                    @Override
                    public void onNext(Object o) {
                        Log.e(TAG, "onNext: " + o);
                    }
                });
    }

    public void error() {
        Observable.error(new NullPointerException("不能为空的好吗"))
                .compose(new SimpleTransformer<>())
                .subscribe(new SimpleObserve<Object>() {
                    @Override
                    public void onNext(Object o) {
                        Log.e(TAG, "onNext: " + o);
                    }
                });
    }
}
