package com.yao.lib_mvp.mvp2.persenter;

import com.yao.lib_mvp.mvp2.annotation.CreatePresenter;
import com.yao.lib_mvp.mvp2.view.IMvpBaseView;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/12/7 下午5:54
 * @Version:
 */

public class PresenterMvpFactoryImpl<V extends IMvpBaseView<P>, P extends MvpBasePresenter<V>> implements PresenterMvpFactory<V, P> {

    private Class<P> mPresenterClass;

    private PresenterMvpFactoryImpl(Class<P> presenterClass) {
        this.mPresenterClass = presenterClass;
    }

    public static <V extends IMvpBaseView<P>, P extends MvpBasePresenter<V>> PresenterMvpFactoryImpl<V, P> createFactory(Class<?> clazz) {

        CreatePresenter createPresenter = clazz.getAnnotation(CreatePresenter.class);
        Class<P> aClass = null;
        if (createPresenter != null) {
            aClass = (Class<P>) createPresenter.value();
        }

        return aClass == null ? null : new PresenterMvpFactoryImpl<>(aClass);
    }

    @Override
    public P createMvpPresenter() {

        try {
            return this.mPresenterClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("Presenter创建失败!，检查是否声明了@CreatePresenter(xx.class)注解");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Presenter创建失败!，检查是否声明了@CreatePresenter(xx.class)注解");
        }
    }
}
