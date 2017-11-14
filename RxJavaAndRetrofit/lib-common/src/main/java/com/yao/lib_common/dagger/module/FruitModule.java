package com.yao.lib_common.dagger.module;

import android.graphics.Color;

import com.yao.lib_common.dagger.model.Apple;
import com.yao.lib_common.dagger.model.Fruit;
import com.yao.lib_common.dagger.model.FruitInfo;
import com.yao.lib_common.dagger.annotation.IntNamed;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/13 下午5:44
 * @Version:
 */

@Module //1  注明本类属于Module
public class FruitModule {

    @Named("typeA")//提供Apple给对应的mFruitA
    @Provides //2 注明该方法是用来提供依赖对象的特殊方法
    public Fruit provideFruit() {
        return new Apple(Color.RED, 6);
    }

    @IntNamed(2)
    @Provides
    public Apple provideApple2(int color) {
        return new Apple(color, 5);
    }

    @Provides
    public int color() {
        return 65535;
    }

    @Named("typeB")//提供Banana给对应的mFruitB
    @Provides
    public Fruit provideApple1(FruitInfo fruitInfo) {
        return new Apple(fruitInfo);
    }

    @IntNamed(1)
    @Provides
    public Apple provideApple3(int color) {
        return new Apple(color, color);
    }

}
