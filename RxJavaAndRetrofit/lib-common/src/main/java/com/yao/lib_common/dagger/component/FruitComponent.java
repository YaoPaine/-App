package com.yao.lib_common.dagger.component;

import com.yao.lib_common.dagger.Container;
import com.yao.lib_common.dagger.module.FruitModule;

import dagger.Component;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/13 下午5:50
 * @Version:
 */

@Component(modules = {FruitModule.class}) //3 指明Component在哪些Module中查找依赖
public interface FruitComponent {//4 接口，自动生成实现

    void inject(Container container);//5  注入方法，在Container中调用
}
