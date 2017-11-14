package com.yao.lib_common.dagger.component;

import com.yao.lib_common.dagger.Tank;
import com.yao.lib_common.dagger.module.MachineModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/14 上午9:35
 * @Version:
 */
@Singleton
@Component(modules = {MachineModule.class})
public interface MachineComponent {

    void inject(Tank tank);
}
