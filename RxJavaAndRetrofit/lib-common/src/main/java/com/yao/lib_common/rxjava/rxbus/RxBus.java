package com.yao.lib_common.rxjava.rxbus;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/12/6 上午10:48
 * @Version:
 */

public class RxBus {

    private static volatile RxBus defaultInstance;
    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
    private final Subject<Object> bus;

    private RxBus() {
        bus = PublishSubject.create().toSerialized();
    }

    public static RxBus getDefault() {
        if (defaultInstance == null) {
            synchronized (RxBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new RxBus();
                }
            }
        }
        return defaultInstance;
    }

    /**
     * 发送一个事件
     *
     * @param o 事件
     */
    public void post(Object o) {
        bus.onNext(o);
    }

    /**
     * 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
     */
    public <T> Observable<T> toObservable(final Class<T> eventType) {
        return bus.ofType(eventType);
//        return bus.filter(new Predicate<Object>() {
//            @Override
//            public boolean test(Object o) throws Exception {
//                return eventType.isInstance(o);
//            }
//        }).cast(eventType);
    }
}
