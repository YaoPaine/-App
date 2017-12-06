package com.yao.lib_common.dagger.module;

import com.yao.lib_common.rxjava.practise.operator.Operators;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/12/6 上午11:59
 * @Version:
 */
@Module
public class OperatorModule {

    @Provides
    @Singleton
    public Operators provideOperators() {
        return new Operators();
    }
}
