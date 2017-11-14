package com.yao.lib_common.dagger.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/13 下午8:18
 * @Version:
 */
@Qualifier //必须，表示IntNamed是用来做区分用途
@Documented //规范要求是Documented，当然不写也问题不大，但是建议写，做提示作用
@Retention(RetentionPolicy.RUNTIME) //规范要求是Runtime级别
public @interface IntNamed {
    int value();
}
