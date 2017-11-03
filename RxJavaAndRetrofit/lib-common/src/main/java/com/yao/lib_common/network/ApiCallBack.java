package com.yao.lib_common.network;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/2 下午4:36
 * @Version:
 */

public abstract class ApiCallBack<M> implements Observer<M> {

    @Override
    public void onNext(M m) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }
}
