package com.yao.lib_common.dagger;

import android.util.Log;

import com.yao.lib_common.dagger.component.DaggerMachineComponent;
import com.yao.lib_common.dagger.component.MachineComponent;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/13 下午5:53
 * @Version:
 */

public class Container {

    public void init() {
//        DaggerFruitComponent.create().inject(this);
//        Log.e("TAG", "fruit: " + fruit.toString());
//        Log.e("TAG", "apple: " + apple.toString());
//        Log.e("TAG", "apple1: " + apple1.toString());
//        Log.e("TAG", "apple2: " + apple2.toString());

        MachineComponent machineComponent = DaggerMachineComponent.create();
        Tank tank1 = new Tank();
        machineComponent.inject(tank1);
        Tank tank2 = new Tank();
        machineComponent.inject(tank2);
        Tank tank3 = new Tank();
        machineComponent.inject(tank3);

        Log.e("TAG", "Container: " + tank1.juiceMachine1);
        Log.e("TAG", "Container: " + tank2.juiceMachine1);
        Log.e("TAG", "Container: " + tank3.juiceMachine1);

    }

}
