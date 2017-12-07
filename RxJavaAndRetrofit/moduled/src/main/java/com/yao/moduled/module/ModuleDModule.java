package com.yao.moduled.module;

import com.yao.moduled.model.ModuleDModel;
import com.yao.moduled.presenter.ModuleDPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/12/7 下午3:30
 * @Version:
 */
@Module
public class ModuleDModule {

    @Singleton
    @Provides
    public ModuleDModel provideModuleDModel() {
        return new ModuleDModel();
    }

    @Singleton
    @Provides
    public ModuleDPresenter provideModuleDPresenter(ModuleDModel model) {
        return new ModuleDPresenter(model);
    }
}
