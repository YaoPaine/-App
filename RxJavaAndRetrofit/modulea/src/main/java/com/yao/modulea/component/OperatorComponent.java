package com.yao.modulea.component;

import com.yao.lib_common.dagger.module.OperatorModule;
import com.yao.modulea.ModuleAActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/12/6 下午12:00
 * @Version:
 */
@Singleton
@Component(modules = {OperatorModule.class})
public interface OperatorComponent {

    void inject(ModuleAActivity activity);
}
