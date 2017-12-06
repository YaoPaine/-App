package com.yao.lib_common.rxjava.retrofit.transformer;

import com.yao.lib_common.rxjava.retrofit.exception.ApiException;
import com.yao.lib_common.rxjava.retrofit.model.entity.BaseResult;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/9 下午9:21
 * @Version:
 */

public class ResolveDataTransformer<T> implements ObservableTransformer<BaseResult<T>, T> {

    @Override
    public ObservableSource<T> apply(Observable<BaseResult<T>> upstream) {

        return upstream
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).
                        flatMap(new Function<BaseResult<T>, ObservableSource<T>>() {
                            @Override
                            public ObservableSource<T> apply(BaseResult<T> baseResult) throws Exception {
                                int code = baseResult.getCode();
                                String message = baseResult.getMessage();
                                if (code == BaseResult.getSuccessCod()) {
                                    return Observable.just(baseResult.getData());
                                }
                                throw new ApiException(code, message);
                            }
                        });
    }
}
