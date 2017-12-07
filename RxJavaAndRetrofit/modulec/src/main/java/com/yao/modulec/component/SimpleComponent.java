package com.yao.modulec.component;

import com.yao.modulec.ModuleCActivity;
import com.yao.modulec.modules.SimpleModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/12/7 下午12:23
 * @Version:
 */

@Singleton
@Component(modules = {SimpleModule.class})
public interface SimpleComponent {

    void injectActivity(ModuleCActivity activity);

//    void injectPresenter(SimplePresenter presenter);
}
