package com.yao.lib_common.rxjava.practise.subject;

import android.util.Log;

import com.yao.lib_common.rxjava.practise.observe.SimpleObserve;

import io.reactivex.Observable;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/12/6 下午6:04
 * @Version:
 */

public class Subjects {

    private final String TAG = "SimpleObserve";

    public void doPublishSubject() {
        //将事件发送到observer，如果先前已经漏掉的事件，不会重新发送到后注册的observer上
        PublishSubject<String> publisher = PublishSubject.create();
        publisher.subscribe(new SimpleObserve<String>("first") {
            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s + "\tt: " + t);
            }
        });
        publisher.onNext("1");
        publisher.onNext("2");
        publisher.subscribe(new SimpleObserve<String>("second") {
            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s + "\tt: " + t);
            }
        });
        publisher.onNext("3");
        publisher.onNext("4");
        publisher.onComplete();
    }

    public void doBehaviorSubject() {
//        BehaviorSubject<String> publisher = BehaviorSubject.createDefault("创建behavior时候带的消息");
        BehaviorSubject<String> publisher = BehaviorSubject.create();
        publisher.subscribe(new SimpleObserve<String>("first") {
            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s + "\tt: " + t);
            }
        });
        publisher.onNext("1");
        publisher.onNext("2");
        publisher.onNext("3");
        publisher.subscribe(new SimpleObserve<String>("second") {
            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s + "\tt: " + t);
            }
        });
        publisher.onNext("4");
        publisher.onNext("5");
        publisher.onComplete();
    }

    public void doReplaySubject() {
        ReplaySubject<String> publisher = ReplaySubject.create();
        publisher.subscribe(new SimpleObserve<String>("first") {
            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s + "\tt: " + t);
            }
        });
        publisher.onNext("1");
        publisher.onNext("2");
        publisher.onNext("3");
        publisher.subscribe(new SimpleObserve<String>("second") {
            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s + "\tt: " + t);
            }
        });
        publisher.onNext("4");
        publisher.onNext("5");
        publisher.onComplete();
    }

    public void doAsyncSubject() {
        AsyncSubject<String> asyncSubject = AsyncSubject.create();
        asyncSubject.subscribe(new SimpleObserve<String>("first") {
            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s + "\tt: " + t);
            }
        });
        asyncSubject.onNext("1");
        asyncSubject.onNext("2");
//        asyncSubject.onComplete();
        asyncSubject.onNext("3");
        asyncSubject.subscribe(new SimpleObserve<String>("second") {
            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s + "\tt: " + t);
            }
        });

        asyncSubject.onNext("4");
        asyncSubject.onNext("5");
        asyncSubject.onComplete();
    }

    public void doObserverSubject() {
        Observable<String> observable = Observable.fromArray("100", "103", "107");
        ReplaySubject<String> replay = ReplaySubject.create();
        observable.subscribe(replay);
        replay.subscribe(new SimpleObserve<String>() {
            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s);
            }
        });
    }
}
