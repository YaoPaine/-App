package com.yao.moduled.presenter;

import android.util.Log;

import com.yao.lib_mvp.mvp2.persenter.MvpBasePresenter;
import com.yao.moduled.view.ModuleDView;
import com.yao.moduled.model.ModuleDModel;

import javax.inject.Inject;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/12/7 下午3:25
 * @Version:
 */

public class ModuleDPresenter extends MvpBasePresenter<ModuleDView> {

    @Inject
    ModuleDModel mModel;

    @Inject
    public ModuleDPresenter(ModuleDModel model) {
        Log.e("TAG", "ModuleDPresenter: " + model);
    }


}
