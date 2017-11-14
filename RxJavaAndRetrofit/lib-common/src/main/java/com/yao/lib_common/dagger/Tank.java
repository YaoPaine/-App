package com.yao.lib_common.dagger;


import android.util.Log;

import com.yao.lib_common.dagger.annotation.IntNamed;
import com.yao.lib_common.dagger.component.DaggerMachineComponent;
import com.yao.lib_common.dagger.model.FruitJuiceMachine;

import javax.inject.Inject;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/14 上午9:48
 * @Version:
 */

public class Tank {

    @IntNamed(10)
    @Inject
    public FruitJuiceMachine juiceMachine1;

    @IntNamed(20)
    @Inject
    public FruitJuiceMachine juiceMachine2;

    public void init() {
        DaggerMachineComponent.create().inject(this);
        Log.e("TAG", "init: " + juiceMachine1);
        Log.e("TAG", "init: " + juiceMachine2);
    }
}
