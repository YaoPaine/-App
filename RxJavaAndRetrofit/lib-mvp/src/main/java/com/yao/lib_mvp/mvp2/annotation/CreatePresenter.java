package com.yao.lib_mvp.mvp2.annotation;

import com.yao.lib_mvp.mvp2.persenter.MvpBasePresenter;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/12/7 下午5:52
 * @Version:
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenter {
    Class<? extends MvpBasePresenter> value();
}
