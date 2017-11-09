package com.yao.rxjavaandretrofit;

import android.text.TextUtils;

import com.yao.lib_common.gson.GsonUtils;
import com.yao.lib_common.model.entity.BaseResult;
import com.yao.lib_common.transformer.ResolveDataTransformer;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/9 下午8:12
 * @Version:
 */

public class Transformer<T> implements ObservableTransformer<BaseResult<String>, T> {
    private final String TAG = "GsonDesTransformer";
    private Class<T> mClazz;

    public Transformer(Class<T> Clazz) {
        this.mClazz = Clazz;
    }

    @Override
    public ObservableSource<T> apply(Observable<BaseResult<String>> upstream) {
        return upstream
                .compose(new ResolveDataTransformer<String>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<String, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(String data) throws Exception {
                        if (TextUtils.isEmpty(data)) {
                            return null;
                        }
                        T t = GsonUtils.instance().fromJson(data, mClazz);
                        return Observable.just(t);
                    }
                });
    }
}
