package com.yao.moduled.component;

import com.yao.moduled.ModuleDActivity;
import com.yao.moduled.module.ModuleDModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/12/7 下午3:34
 * @Version:
 */

@Singleton
@Component(modules = {ModuleDModule.class})
public interface ModuleDComponent {

    void injectActivity(ModuleDActivity activity);

}
