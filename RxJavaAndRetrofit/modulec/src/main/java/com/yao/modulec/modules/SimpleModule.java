package com.yao.modulec.modules;

import com.yao.lib_mvp.mvp2.model.SimpleModel;
import com.yao.lib_mvp.mvp2.persenter.SimplePresenter;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/12/7 下午12:24
 * @Version:
 */

@Module
public class SimpleModule {

    @Named("initItself")
    @Singleton
    @Provides
    public SimplePresenter provideSimplePresenter() {
        return new SimplePresenter();
    }

    @Singleton
    @Provides
    public SimpleModel provideSimpleModel() {
        return new SimpleModel();
    }

    @Named("initModel")
    @Singleton
    @Provides
    public SimplePresenter providesSimplePresenter(SimpleModel simpleModel) {
        return new SimplePresenter(simpleModel);
    }
}
