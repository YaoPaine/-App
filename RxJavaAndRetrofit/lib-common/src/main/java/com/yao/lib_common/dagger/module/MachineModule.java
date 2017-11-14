package com.yao.lib_common.dagger.module;

import com.yao.lib_common.dagger.annotation.IntNamed;
import com.yao.lib_common.dagger.model.FruitJuiceMachine;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/14 上午9:24
 * @Version:
 */
@Module
public class MachineModule {

    //    @Singleton
    @IntNamed(10)
    @Provides
    @Singleton
    public FruitJuiceMachine provideMachine1() {
        return new FruitJuiceMachine();
    }


    @IntNamed(20)
    @Provides
    @Singleton
    public FruitJuiceMachine provideMachine2() {
        return new FruitJuiceMachine();
    }

}
